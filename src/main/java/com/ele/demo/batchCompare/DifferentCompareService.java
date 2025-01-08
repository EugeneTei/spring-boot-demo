package com.ele.demo.batchCompare;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BatchStatement;
import com.datastax.oss.driver.api.core.cql.BatchStatementBuilder;
import com.datastax.oss.driver.api.core.cql.DefaultBatchType;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.ele.demo.batchCompare.dto.DifferentCompareRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class DifferentCompareService {


    public void different(DifferentCompareRequestDto requestDto) {

        try (CqlSession session = ScyllaDbConnection.connect()) {
            BatchStatementBuilder batchBuilder = BatchStatement.builder(DefaultBatchType.UNLOGGED);
            generateBatchState(batchBuilder, requestDto);

            long start = System.currentTimeMillis();
            session.execute(batchBuilder.build());
            long executionTime = System.currentTimeMillis() - start;

            log.info("batch statement completed. Inserted {} users in {} milliseconds.", requestDto.getUserIdList().size(), executionTime);
        }
    }

    private void generateBatchState(BatchStatementBuilder batchBuilder, DifferentCompareRequestDto requestDto) {

        String userSource = requestDto.getUserSource().name();
        Integer itemId = requestDto.getItemId();
        Instant timestamp = Instant.now();
        List<Integer> userIdList = requestDto.getUserIdList();

        for (Integer userId : userIdList) {
            batchBuilder.addStatement(
                    SimpleStatement.newInstance(
                            """
                                    INSERT INTO example.compare
                                    (user_source, user_id, item_id, time) 
                                    VALUES
                                    (?, ?, ?, ?)
                                    """,
                            userSource, userId, itemId, timestamp
                    )
            );
        }
    }
}
