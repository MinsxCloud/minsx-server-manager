/*
 *
 *  * Copyright © 2017-2018 minsx.com All rights reserved
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */
package com.minsx.appmanager.core.task;

import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class TaskManager {

    private final static HashMap<String, Task> TASK_POOL = new HashMap<>();

    public static Task getTask(String appName) {
        return TASK_POOL.get(appName);
    }

    public static Task addTask(Task task) {
        TASK_POOL.put(task.getAppName(), task);
        return task;
    }

    public static void initialTasks(List<? extends Task> tasks) {
        tasks.forEach(t -> TASK_POOL.put(t.getAppName(), t));
    }

    public static Task addAndRunTask(Task task) {
        TASK_POOL.put(task.getAppName(), task);
        task.start();
        return task;
    }

    public static Task stopTask(String appName) {
        Task task = TASK_POOL.get(appName);
        if (task != null && task.isRunning()) task.stop();
        return task;
    }

    public static Task stopAndRemoveTask(String appName) {
        Task task = TASK_POOL.get(appName);
        if (task != null && task.isRunning()) task.stop();
        return TASK_POOL.remove(appName);
    }

    public static void stopAll() {
        TASK_POOL.forEach((appName, task) -> {
            if (task.isRunning()) task.stop();
        });
    }

    public static void stopAndRemoveAll() {
        TASK_POOL.forEach((appName, task) -> {
            if (task.isRunning()) task.stop();
        });
        TASK_POOL.clear();
    }

    public static HashMap<String, Task> getAllTasks() {
        return TASK_POOL;
    }

    public static void consume(BiConsumer<? super String, ? super Task> action) {
        TASK_POOL.forEach(action);
    }

}
