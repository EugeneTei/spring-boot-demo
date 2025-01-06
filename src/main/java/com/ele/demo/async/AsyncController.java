package com.ele.demo.async;

import com.ele.demo.shoalter.dto.ResponseDto;
import com.ele.demo.shoalter.dto.UpsertChatroomRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
public class AsyncController {

    private final AsyncUpsertChatroomService asyncUpsertChatroomService;

    @PatchMapping("/async")
    ResponseDto upsert(@RequestBody UpsertChatroomRequestDto upsertChatroomRequestDto) {
        asyncUpsertChatroomService.upsert(upsertChatroomRequestDto);
        return ResponseDto.success("async upsert chatroom list to ScyllaDB");
    }
}
