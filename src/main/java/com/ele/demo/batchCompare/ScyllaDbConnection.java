package com.ele.demo.batchCompare;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;

import java.io.File;
import java.net.InetSocketAddress;

public class ScyllaDbConnection {

    public static CqlSession connect() {
        DriverConfigLoader loader = DriverConfigLoader.fromFile(new File("setup/application.conf"));
        return CqlSession.builder()
                .withConfigLoader(loader)
                .addContactPoint(new InetSocketAddress("127.0.0.1", 9042))
                .withLocalDatacenter("datacenter1")
                .withKeyspace("example")
                .build();
    }
}
