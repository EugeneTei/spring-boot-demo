package com.ele.demo.springDataCassandra;

import com.ele.demo.springDataCassandra.pojo.Chatroom;
import com.ele.demo.springDataCassandra.pojo.ChatroomCompositeKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatroomRepository extends CassandraRepository<Chatroom, ChatroomCompositeKey> {
}
