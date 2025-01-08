package com.ele.demo.springDataCassandra;

import com.ele.demo.dto.UpsertChatroomRequestDto;
import com.ele.demo.springDataCassandra.pojo.Chatroom;
import com.ele.demo.springDataCassandra.pojo.ChatroomCompositeKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class SpringDataCassandraSyncUpsertService {

    private final ChatroomRepository chatroomRepository;

    public void upsert(UpsertChatroomRequestDto upsertChatroomRequestDto) {

        List<Chatroom> chatroomList = new ArrayList<>();

        for (Integer userId : upsertChatroomRequestDto.getUserIdList()) {
            Chatroom chatroom = generateChatroom(upsertChatroomRequestDto, userId);
            chatroomList.add(chatroom);
        }

        long start = System.currentTimeMillis();
        chatroomRepository.saveAll(chatroomList);
        long executionTime = System.currentTimeMillis() - start;

        log.info("saveAll() By Spring Data Cassandra. Inserted {} records in {} milliseconds.", chatroomList.size(), executionTime);
    }

    private Chatroom generateChatroom(UpsertChatroomRequestDto requestDto, Integer userId) {

        ChatroomCompositeKey key = new ChatroomCompositeKey(requestDto.getUserSource(), userId, requestDto.getItemId());

        return Chatroom.builder()
                .key(key)
                .time(requestDto.getTime())
                .build();
    }
}
