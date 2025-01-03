package com.ele.demo.shoalter;

import com.ele.demo.shoalter.pojo.Chatroom;
import com.ele.demo.shoalter.pojo.UserSource;
import org.springframework.data.cassandra.repository.MapIdCassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ChatroomRepository extends MapIdCassandraRepository<Chatroom> {

    @Query("""
             SELECT chatroom_id, message_source, last_modified_date_time FROM chatroom
             WHERE user_source = :#{#userSource.name()} AND user_id = :userId AND last_modified_date_time < :queryEndDateTime
             LIMIT 10
            """)
    List<Chatroom> findByUserSourceAndUserIdAndQueryEndDateTime(@Param("userSource") UserSource userSource, Long userId, LocalDateTime queryEndDateTime);
}
