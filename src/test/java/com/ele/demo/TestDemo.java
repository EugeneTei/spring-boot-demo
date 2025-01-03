package com.ele.demo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDemo {

    private ScylladbApplication scylladbApplication;

    @BeforeAll
    void setUp() {
        scylladbApplication = new ScylladbApplication("baeldung", "User");
    }

    @Test
    public void givenKeySpaceAndTable_whenInsertData_thenShouldBeAbleToFindData() {
        User user = new User(10, "John");
        scylladbApplication.addNewUser(user);

        List<User> userList = scylladbApplication.getUsersByUserName("John");
        assertEquals(1, userList.size());
        assertEquals("John", userList.get(0).getName());
        assertEquals(10, userList.get(0).getId());

    }

    @Test
    public void givenKeySpaceAndTable_whenInsertData_thenRowCountIncreases() {
        int initialCount = scylladbApplication.getAllUserNames().size();
        User user = new User(11, "Doe");
        scylladbApplication.addNewUser(user);

        int expectedCount = initialCount + 1;
        int updatedCount = scylladbApplication.getAllUserNames().size();
        assertEquals(expectedCount, updatedCount);

    }
}
