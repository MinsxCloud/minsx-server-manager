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
package com.minsx.appmanager.core.task.exeption;

import com.minsx.appmanager.core.task.Task;

public class TaskStartException extends RuntimeException {

    private Task task;

    public TaskStartException(Task task) {
        this.task = task;
    }

    public TaskStartException(String message, Task task) {
        super(message);
        this.task = task;
    }

    public TaskStartException(String message, Throwable cause, Task task) {
        super(message, cause);
        this.task = task;
    }

    public TaskStartException(Throwable cause, Task task) {
        super(cause);
        this.task = task;
    }

    public TaskStartException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Task task) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.task = task;
    }
}
