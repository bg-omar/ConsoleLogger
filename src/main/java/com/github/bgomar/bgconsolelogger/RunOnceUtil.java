package com.github.bgomar.bgconsolelogger;

import java.util.HashSet;
import java.util.Set;

public class RunOnceUtil {

    private static Set<String> executedTasks = new HashSet<>();

    public static void runOnce(String taskKey, Runnable task) {
        if (!executedTasks.contains(taskKey)) {
            task.run();
            executedTasks.add(taskKey);
        }
    }
}