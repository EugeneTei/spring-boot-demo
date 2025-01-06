package com.ele.demo.shoalter;

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
public class ChatroomController {

    private final UpsertChatroomService upsertChatroomService;

    @PatchMapping("/sync")
    ResponseDto upsert(@RequestBody UpsertChatroomRequestDto upsertChatroomRequestDto) {
        upsertChatroomService.upsert(upsertChatroomRequestDto);
        return ResponseDto.success("upsert chatroom list to ScyllaDB");
    }
}
