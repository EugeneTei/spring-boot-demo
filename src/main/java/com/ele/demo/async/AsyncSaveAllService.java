package com.ele.demo.async;

import com.ele.demo.springDataCassandra.pojo.Chatroom;
import com.ele.demo.springDataCassandra.ChatroomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
@Service
public class AsyncSaveAllService {

    private final ChatroomRepository chatroomRepository;

    @Async("taskExecutor1")
    public CompletableFuture<Long> saveAll(List<Chatroom> chatroomList) {

//        log.info("Async Thread： {}" ,Thread.currentThread().getName());

        long start = System.currentTimeMillis();

        chatroomRepository.saveAll(chatroomList);

        long executionTime = System.currentTimeMillis() - start;
        double seconds = executionTime / 1000.0;

//        log.info("Async Thread：{} completed. Inserted {} chatrooms in {} seconds.", Thread.currentThread().getName(), chatroomList.size(), seconds);

        return CompletableFuture.completedFuture(executionTime);
    }
}
