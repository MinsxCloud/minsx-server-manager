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
package com.minsx.appmanager.web.initial;

import com.minsx.appmanager.core.task.TaskManager;
import com.minsx.appmanager.web.entity.Application;
import com.minsx.appmanager.web.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebServerInitial implements CommandLineRunner {

    @Autowired
    ApplicationRepository applicationRepository;

    @Override
    public void run(String... args) throws Exception {
        addNewApplication();
        initialTaskPool();
    }


    public void initialTaskPool() {
        List<Application> applications = applicationRepository.findAll();
        TaskManager.initialTasks(applications);
    }


    public void addNewApplication() {
        Application application = applicationRepository.findByAppName("Web Server");
        if (application == null) application = new Application();
        application.setAppName("Web Server");
        application.setCommand("cmd /c Run.bat");
        application.setInPath("E:/Temp/RunTest/MsAuthServer");
        applicationRepository.save(application);
    }


}
