package com.ele.demo.shoalter;

import com.ele.demo.shoalter.pojo.Chatroom;
import com.ele.demo.shoalter.pojo.ChatroomCompositeKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatroomRepository extends CassandraRepository<Chatroom, ChatroomCompositeKey> {

}
