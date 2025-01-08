package com.ele.demo.batchCompare;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BatchStatement;
import com.datastax.oss.driver.api.core.cql.BatchStatementBuilder;
import com.datastax.oss.driver.api.core.cql.DefaultBatchType;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.ele.demo.batchCompare.dto.SameCompareRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class SameCompareService {

    public void same(SameCompareRequestDto requestDto) {

//        setLog();

        try (CqlSession session = ScyllaDbConnection.connect()) {

            BatchStatementBuilder batchBuilder = BatchStatement.builder(DefaultBatchType.LOGGED);
            generateBatchState(batchBuilder, requestDto);

            long start = System.currentTimeMillis();
            session.execute(batchBuilder.build());
            long executionTime = System.currentTimeMillis() - start;

            log.info("batch statement completed. Inserted {} items in {} milliseconds.", requestDto.getItemIdList().size(), executionTime);
        }
    }

    private void generateBatchState(BatchStatementBuilder batchBuilder, SameCompareRequestDto requestDto) {

        String userSource = requestDto.getUserSource().name();
        Integer userId = requestDto.getUserId();
        Instant timestamp = Instant.now();
        List<Integer> itemIdList = requestDto.getItemIdList();

        for (Integer itemId : itemIdList) {
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

    private void setLog() {
        System.setProperty("datastax-java-driver.advanced.request-tracker.class", "RequestLogger");
        System.setProperty("datastax-java-driver.advanced.request-tracker.logs.success.enabled", "true");
        System.setProperty("datastax-java-driver.advanced.request-tracker.logs.slow.enabled", "true");
        System.setProperty("datastax-java-driver.advanced.request-tracker.logs.error.enabled", "true");

        System.setProperty("datastax-java-driver.advanced.request-tracker.logs.show-values", "true");
        System.setProperty("datastax-java-driver.advanced.request-tracker.logs.max-value-length", "100");
        System.setProperty("datastax-java-driver.advanced.request-tracker.logs.max-values", "100");
    }
}
