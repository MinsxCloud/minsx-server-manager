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

import com.minsx.appmanager.core.task.exeption.ShellInternalException;
import com.minsx.framework.common.shell.core.Shell;

import java.util.Date;

public class DefaultTask implements Task {

    private Shell shell;
    private String appName;
    private String command;
    private String[] environments;
    private String inPath;
    private String charset = "UTF-8";
    private final StringBuffer out = new StringBuffer();
    private final StringBuffer err = new StringBuffer();
    private Date beginDateTime;
    private Date endDateTime;
    private Integer bufferLength = 5000;
    private Boolean stopOnErr = false;

    @Override
    public Boolean isRunning() {
        return shell != null && shell.isRunning();
    }

    @Override
    public void start() {
        shell = Shell.build(command).onOut((line, operator) -> {
            out.append(line).append("\n");
            handleStringBuffer(out);
        }).onErr((line, operator) -> {
            err.append(line).append("\n");
            handleStringBuffer(err);
            if (stopOnErr) {
                operator.stop();
            }
        }).onException(e -> {
            shell.stop();
            throw new ShellInternalException(String.format("run [%s] catch internal exception", appName), e, this);
        }).charset(charset)
                .sync(false)
                .environments(environments)
                .inPath(inPath).logged(true);
        shell.run();
        beginDateTime = new Date();
    }

    @Override
    public void reStart() {
        this.stop();
        this.start();
    }

    @Override
    public void stop() {
        shell.stop();
        endDateTime = new Date();
    }


    private void handleStringBuffer(StringBuffer buffer) {
        int outLength = buffer.length() - bufferLength;
        if (outLength > 0) {
            buffer.delete(0, outLength);
        }
    }


    public Shell getShell() {
        return shell;
    }

    public void setShell(Shell shell) {
        this.shell = shell;
    }

    @Override
    public String getAppName() {
        return appName;
    }

    @Override
    public void setAppName(String appName) {
        this.appName = appName;
    }

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    public void setCommand(String command) {
        this.command = command;
    }

    @Override
    public String[] getEnvironments() {
        return environments;
    }

    @Override
    public void setEnvironments(String[] environments) {
        this.environments = environments;
    }

    @Override
    public String getInPath() {
        return inPath;
    }

    @Override
    public void setInPath(String inPath) {
        this.inPath = inPath;
    }

    @Override
    public String getCharset() {
        return charset;
    }

    @Override
    public void setCharset(String charset) {
        this.charset = charset;
    }

    @Override
    public StringBuffer getOut() {
        return out;
    }

    @Override
    public StringBuffer getErr() {
        return err;
    }

    @Override
    public Date getBeginDateTime() {
        return beginDateTime;
    }

    public void setBeginDateTime(Date beginDateTime) {
        this.beginDateTime = beginDateTime;
    }

    @Override
    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    @Override
    public Integer getBufferLength() {
        return bufferLength;
    }

    @Override
    public void setBufferLength(Integer bufferLength) {
        this.bufferLength = bufferLength;
    }

    @Override
    public Boolean getStopOnErr() {
        return stopOnErr;
    }

    @Override
    public void setStopOnErr(Boolean stopOnErr) {
        this.stopOnErr = stopOnErr;
    }

}
