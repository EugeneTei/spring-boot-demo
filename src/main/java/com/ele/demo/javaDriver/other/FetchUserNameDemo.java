package com.ele.demo.javaDriver.other;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

public class FetchUserNameDemo {

    public static void main(String[] args) {

        try (CqlSession session = CqlSession.builder().build()) {
            ResultSet rs = session.execute("select * from baeldung.User");
            Row row = rs.one();
            String name = row.getString("name");

            System.out.println("Hello " + name);
        }
    }
}
