package com.ele.demo.javaDriver;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BatchStatement;
import com.datastax.oss.driver.api.core.cql.BatchStatementBuilder;
import com.datastax.oss.driver.api.core.cql.DefaultBatchType;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.ele.demo.batchCompare.ScyllaDbConnection;
import com.ele.demo.dto.UpsertChatroomRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Slf4j
@Service
public class DriverSyncUpsertService {

    public void upsert(UpsertChatroomRequestDto requestDto) {

        try (CqlSession session = ScyllaDbConnection.connect()) {

            BatchStatementBuilder batchBuilder = BatchStatement.builder(DefaultBatchType.LOGGED);
            generateBatchState(batchBuilder, requestDto);

            long start = System.currentTimeMillis();
            session.execute(batchBuilder.build());
            long executionTime = System.currentTimeMillis() - start;

            log.info("batch statement By Official Driver. Inserted {} records in {} milliseconds.", requestDto.getUserIdList().size(), executionTime);
        }
    }

    private void generateBatchState(BatchStatementBuilder batchBuilder, UpsertChatroomRequestDto requestDto) {

        String userSource = requestDto.getUserSource().name();
        Integer itemId = requestDto.getItemId();
        Instant timestamp = Instant.now();
        List<Integer> userIdList = requestDto.getUserIdList();

        for(Integer userId : userIdList) {
            batchBuilder.addStatement(
                    SimpleStatement.newInstance(
                            """
                                    INSERT INTO example.chatroom
                                    (user_source, user_id, item_id, time)
                                    VALUES
                                    (?, ?, ?, ?);
                                    """,
                            userSource, userId, itemId, timestamp
                    )
            );
        }
    }
}
