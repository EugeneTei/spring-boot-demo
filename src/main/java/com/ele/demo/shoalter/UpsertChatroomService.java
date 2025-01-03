package com.ele.demo.shoalter;

import com.ele.demo.shoalter.dto.UpsertChatroomRequestDto;
import com.ele.demo.shoalter.pojo.Chatroom;
import com.ele.demo.shoalter.pojo.ChatroomCompositeKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class UpsertChatroomService {

    private final ChatroomRepository chatroomRepository;

    public void upsert(UpsertChatroomRequestDto upsertChatroomRequestDto) {

        List<Chatroom> chatroomList = new ArrayList<>();

        for (Long userId : upsertChatroomRequestDto.getUserIdList()) {
            Chatroom chatroom = generateChatroom(upsertChatroomRequestDto, userId);
            chatroomList.add(chatroom);
        }

        log.info("chatroomList List Size = {}", upsertChatroomRequestDto);

        long start = System.currentTimeMillis();

        chatroomRepository.saveAll(chatroomList);

        long executionTime = System.currentTimeMillis() - start;
        double seconds = executionTime / 1000.0;

        log.info("saveAll completed. Inserted {} chatrooms in {} seconds.", chatroomList.size(), seconds);
    }

    private Chatroom generateChatroom(UpsertChatroomRequestDto upsertChatroomRequestDto, Long userId) {

        ChatroomCompositeKey key = new ChatroomCompositeKey(upsertChatroomRequestDto.getUserSource(), userId);

        return Chatroom.builder()
                .primaryKey(key)
                .messageSource(upsertChatroomRequestDto.getMessageSource())
                .chatroomId(upsertChatroomRequestDto.getChatroomId())
                .lastModifiedDateTime(upsertChatroomRequestDto.getLastModifiedDateTime())
                .build();
    }
}
