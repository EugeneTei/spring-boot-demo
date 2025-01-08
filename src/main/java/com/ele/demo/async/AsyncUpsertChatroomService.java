package com.ele.demo.async;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AsyncUpsertChatroomService {

//    private final AsyncSaveAllService asyncSaveAllService;
//
//    public void upsert(UpsertChatroomRequestDto upsertChatroomRequestDto) {
//
//        List<Chatroom> chatroomList = new ArrayList<>();
//
//        for (Long userId : upsertChatroomRequestDto.getUserIdList()) {
//            Chatroom chatroom = generateChatroom(upsertChatroomRequestDto, userId);
//            chatroomList.add(chatroom);
//        }
//
//        List<List<Chatroom>> partitionList = Lists.partition(chatroomList, 100);
//
//        List<CompletableFuture<Long>> futures = new ArrayList<>();
//
//        for (List<Chatroom> subChatroomList : partitionList) {
//            futures.add(asyncSaveAllService.saveAll(subChatroomList));
//        }
//
//       CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
//                .thenApply(v -> {
//                    long totalDuration = futures.stream()
//                            .mapToLong(CompletableFuture::join)
//                            .sum();
//
//                    long avgDuration = totalDuration / 100;
//                    log.info("Thread {} saveAll completed. Inserted {} chatrooms in {} ms.", Thread.currentThread().getName(), chatroomList.size(), avgDuration);
//
//                    return avgDuration;
//                });
//    }
//
//    private Chatroom generateChatroom(UpsertChatroomRequestDto requestDto, Long userId) {
//
//        ChatroomCompositeKey key = new ChatroomCompositeKey(requestDto.getUserSource(), userId, requestDto.getChatroomId());
//
//        return Chatroom.builder()
//                .primaryKey(key)
//                .lastModifiedDateTime(requestDto.getLastModifiedDateTime())
//                .build();
//    }
}
