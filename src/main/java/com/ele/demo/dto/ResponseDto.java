package com.ele.demo.dto;

import lombok.Builder;
import lombok.Value;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Builder
@Value
public class ResponseDto<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    String status;
    T data;
    List<String> messageList;

    public static <T> ResponseDto<T> success(T data) {
        return new ResponseDto<>("success", data, new ArrayList<>());
    }

    public static ResponseDto<Void> warn(List<String> messageList) {
        return new ResponseDto<>("warn", null, messageList);
    }

    public static ResponseDto<Void> warn(String message) {
        return new ResponseDto<>("warn", null, List.of(message));
    }

    public static ResponseDto<Void> error(List<String> messageList) {
        return new ResponseDto<>("warn", null, messageList);
    }

    public static ResponseDto<Void> error(String message) {
        return new ResponseDto<>("error", null, List.of(message));
    }
}
