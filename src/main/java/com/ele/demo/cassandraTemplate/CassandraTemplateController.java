package com.ele.demo.cassandraTemplate;

import com.ele.demo.dto.ResponseDto;
import com.ele.demo.dto.UpsertChatroomRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/cassandra-template")
@RestController
@Slf4j
public class CassandraTemplateController {

    private final CassandraTemplateUpsertService cassandraTemplateUpsertService;

    @PatchMapping("/sync")
    ResponseDto sync(@RequestBody UpsertChatroomRequestDto upsertChatroomRequestDto) {
        cassandraTemplateUpsertService.upsert(upsertChatroomRequestDto);
        return ResponseDto.success("success");
    }
}
