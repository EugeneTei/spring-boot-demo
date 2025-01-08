package com.ele.demo.springDataCassandra.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table("chatroom")
public class Chatroom {

    @PrimaryKey
    private ChatroomCompositeKey key;

    @Column("time")
    private LocalDateTime time;
}
