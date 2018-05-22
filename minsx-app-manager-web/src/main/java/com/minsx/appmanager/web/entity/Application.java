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
package com.minsx.appmanager.web.entity;

import com.minsx.appmanager.core.task.exeption.TaskRestartException;
import com.minsx.appmanager.core.task.exeption.ShellInternalException;
import com.minsx.appmanager.core.task.Task;
import com.minsx.appmanager.core.task.exeption.TaskStartException;
import com.minsx.appmanager.core.task.exeption.TaskStopException;
import com.minsx.framework.common.shell.core.Shell;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "application")
public class Application extends BaseEntity implements Serializable, Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Integer id;

    private String appName;
    private String command;
    private String environments;
    private String inPath;
    private Date beginTime;
    private Date endTime;
    private String charset = "UTF-8";
    private Boolean stopOnErr = false;
    private Integer bufferLength = 5000;

    @Column(columnDefinition = "text")
    private StringBuffer outBuffer = new StringBuffer();

    @Column(columnDefinition = "text")
    private StringBuffer errBuffer = new StringBuffer();

    @Transient
    private final static String SPLIT_STR = ";";
    @Transient
    private Shell shell;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return environments == null ? null : environments.split(SPLIT_STR);
    }

    @Override
    public void setEnvironments(String[] environments) {
        if (environments != null) {
            StringBuilder str = new StringBuilder();
            for (String e : environments) {
                str.append(e).append(SPLIT_STR);
            }
            str.delete(str.length() - 1, str.length());
            this.environments = str.toString();
        }
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
    public StringBuffer getOutBuffer() {
        return outBuffer;
    }

    public void setOutBuffer(StringBuffer outBuffer) {
        this.outBuffer = outBuffer;
    }

    @Override
    public StringBuffer getErrBuffer() {
        return errBuffer;
    }

    public void setErrBuffer(StringBuffer errBuffer) {
        this.errBuffer = errBuffer;
    }

    @Override
    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    @Override
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    @Override
    public Boolean isRunning() {
        return shell != null && shell.isRunning();
    }

    @Override
    public void start() {
        if (isRunning()) {
            throw new TaskStartException("the application has been started, can't start again", this);
        }
        shell = Shell.build(command).onOut((line, operator) -> {
            outBuffer.append(line).append("<br/>");
            handleStringBuffer(outBuffer);
        }).onErr((line, operator) -> {
            errBuffer.append(line).append("<br/>");
            handleStringBuffer(errBuffer);
            if (stopOnErr) {
                operator.stop();
            }
        }).onException(e -> {
            shell.stop();
            throw new ShellInternalException(String.format("run [%s] catch internal exception", appName), e, this);
        }).charset(charset)
                .sync(false)
                .environments(getEnvironments())
                .inPath(inPath).logged(true);
        shell.run();
        beginTime = new Date();
    }

    @Override
    public void reStart() {
        if (isRunning()) {
            stop();
            start();
        } else {
            throw new TaskRestartException("the application has not been started, can't restart", this);
        }
    }

    @Override
    public void stop() {
        if (isRunning()) {
            shell.stop();
            endTime = new Date();
        } else {
            throw new TaskStopException("the application has not been started, can't stop", this);
        }
    }

    private void handleStringBuffer(StringBuffer buffer) {
        int outLength = buffer.length() - bufferLength;
        if (outLength > 0) {
            buffer.delete(0, outLength);
        }
    }


}
