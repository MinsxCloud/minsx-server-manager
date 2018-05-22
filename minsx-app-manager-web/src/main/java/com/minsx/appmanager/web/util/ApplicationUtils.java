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
package com.minsx.appmanager.web.util;

import com.minsx.appmanager.core.task.Task;
import com.minsx.appmanager.web.entity.Application;

public class ApplicationUtils {

    public static void flushApplication(Application application, Task task) {
        application.setInPath(task.getInPath());
        application.setCommand(task.getCommand());
        application.setBeginTime(task.getBeginTime());
        application.setEndTime(task.getEndTime());
        application.setBufferLength(task.getBufferLength());
        application.setCharset(task.getCharset());
        application.setEnvironments(task.getEnvironments());
        application.setErrBuffer(task.getErrBuffer());
        application.setOutBuffer(task.getOutBuffer());
        application.setStopOnErr(task.getStopOnErr());
    }





}
