package com.ele.demo.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table("user")
public class User {

    @PrimaryKey
    @Column("id")
    private Long id;

    @Column("name")
    private String name;
}
