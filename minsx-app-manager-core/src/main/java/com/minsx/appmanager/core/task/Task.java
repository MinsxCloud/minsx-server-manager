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

import java.util.Date;

public interface Task {

    String getAppName();

    void setAppName(String appName);

    String getCommand();

    void setCommand(String command);

    String[] getEnvironments();

    void setEnvironments(String[] environments);

    String getInPath();

    void setInPath(String inPath);

    String getCharset();

    void setCharset(String charset);

    StringBuffer getOutBuffer();

    StringBuffer getErrBuffer();

    Date getBeginTime();

    Date getEndTime();

    Integer getBufferLength();

    void setBufferLength(Integer bufferLength);

    Boolean getStopOnErr();

    void setStopOnErr(Boolean stopOnErr);

    Boolean isRunning();

    void start();

    void reStart();

    void stop();

}
