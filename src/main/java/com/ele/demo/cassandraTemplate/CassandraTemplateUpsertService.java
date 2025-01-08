package com.ele.demo.cassandraTemplate;

import com.ele.demo.WriteTxtService;
import com.ele.demo.dto.UpsertChatroomRequestDto;
import com.ele.demo.springDataCassandra.pojo.Chatroom;
import com.ele.demo.springDataCassandra.pojo.ChatroomCompositeKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CassandraTemplateUpsertService {

    private final CassandraTemplate cassandraTemplate;
    private final WriteTxtService writeTxtService;

    public void upsert(UpsertChatroomRequestDto upsertChatroomRequestDto) {

        List<Chatroom> chatroomList = new ArrayList<>();

        for (Integer userId : upsertChatroomRequestDto.getUserIdList()) {
            Chatroom chatroom = generateChatroom(upsertChatroomRequestDto, userId);
            chatroomList.add(chatroom);
        }

        long start = System.currentTimeMillis();
        cassandraTemplate.batchOps().insert(chatroomList).execute();
        long executionTime = System.currentTimeMillis() - start;

        log.info("batch statement By CassandraTemplate. Inserted {} records in {} milliseconds.", upsertChatroomRequestDto.getUserIdList().size(), executionTime);

        String fileName =   upsertChatroomRequestDto.getUserIdList().size() + "record.txt";

        writeTxtService.appendToRootFile(fileName, (executionTime + "+"));
    }

    private Chatroom generateChatroom(UpsertChatroomRequestDto requestDto,Integer userId) {

        ChatroomCompositeKey key = new ChatroomCompositeKey(requestDto.getUserSource(), userId, requestDto.getItemId());

        return Chatroom.builder()
                .key(key)
                .time(requestDto.getTime())
                .build();
    }
}
