package com.ele.demo.batchCompare.dto;

import com.ele.demo.springDataCassandra.pojo.UserSource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DifferentCompareRequestDto {

    private Integer itemId;

    private UserSource userSource;

    private List<Integer> userIdList;

    private LocalDateTime time;
}
