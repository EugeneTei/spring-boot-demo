package com.ele.demo.javaDriver;

import com.ele.demo.dto.ResponseDto;
import com.ele.demo.dto.UpsertChatroomRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/driver")
@RestController
@Slf4j
public class DriverController {

    private final DriverSyncUpsertService syncUpsertService;

    @PatchMapping("/sync")
    ResponseDto sync(@RequestBody UpsertChatroomRequestDto upsertChatroomRequestDto) {
        syncUpsertService.upsert(upsertChatroomRequestDto);
        return ResponseDto.success("sync upsert chatroom list to ScyllaDB");
    }
}
