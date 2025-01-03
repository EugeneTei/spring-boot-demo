package com.ele.demo.shoalter.dto;

import com.ele.demo.shoalter.pojo.MessageSource;
import com.ele.demo.shoalter.pojo.UserSource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertChatroomRequestDto {

    private MessageSource messageSource;

    private Long chatroomId;

    private UserSource userSource;


    private List<Long> userIdList;

    private LocalDateTime lastModifiedDateTime;
}
