package com.utildev.examples.researchrxandroid;

import java.util.ArrayList;
import java.util.List;

class DataSource {
    static List<Task> createTaskList() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Task 1", true, 0));
        tasks.add(new Task("Task 2", true, 1));
        tasks.add(new Task("Task 3", true, 2));
        tasks.add(new Task("Task 4", false, 3));
        tasks.add(new Task("Task 5", true, 4));
        tasks.add(new Task("Task 6", false, 5));
        tasks.add(new Task("Task 7", true, 6));
        tasks.add(new Task("Task 8", false, 7));
        tasks.add(new Task("Task 9", true, 8));
        tasks.add(new Task("Task 10", false, 9));
        tasks.add(new Task("Task 10", false, 9));
        return tasks;
    }
}
