package com.ele.demo.batchCompare;

import com.ele.demo.batchCompare.dto.DifferentCompareRequestDto;
import com.ele.demo.batchCompare.dto.SameCompareRequestDto;
import com.ele.demo.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/compare-partition")
@RestController
@Slf4j
public class ComparePartitionController {

    private final SameCompareService sameCompareService;
    private final DifferentCompareService differentCompareService;

    @PatchMapping("/same")
    ResponseDto same(@RequestBody SameCompareRequestDto sameDto) {
        sameCompareService.same(sameDto);
        return ResponseDto.success("success");
    }

    @PatchMapping("/different")
    ResponseDto different(@RequestBody DifferentCompareRequestDto differentDto) {
        differentCompareService.different(differentDto);
        return ResponseDto.success("success");
    }
}
