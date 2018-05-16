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
package com.minsx.appmanager.web.service.impl;

import com.minsx.appmanager.core.task.Task;
import com.minsx.appmanager.core.task.TaskManager;
import com.minsx.appmanager.web.entity.Application;
import com.minsx.appmanager.web.repository.ApplicationRepository;
import com.minsx.appmanager.web.service.api.ApplicationService;
import com.minsx.appmanager.web.util.ApplicationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    ApplicationRepository applicationRepository;

    @Override
    public Application saveTask(Task task) {
        Application application = applicationRepository.findByAppName(task.getAppName());
        if (application == null) application = new Application();
        ApplicationUtils.flushApplication(application, task);
        return applicationRepository.save(application);
    }

    @Override
    public ResponseEntity<?> addApplication(Application application) {
        Application app = applicationRepository.findByAppName(application.getAppName());
        if (app != null) {
            return new ResponseEntity<>("应用已存在", HttpStatus.BAD_REQUEST);
        } else {
            app = applicationRepository.saveAndFlush(application);
            return new ResponseEntity<Object>(app, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> deleteApplication(String appName) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateApplication(Application application) {
        return null;
    }

    @Override
    public ResponseEntity<?> getApplication(String appName) {
        Task task = TaskManager.getTask(appName);
        if (task == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Object>((Application) task, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> listApplications() {
        List<Application> applications = new ArrayList<>();
        TaskManager.consume((appName, task) -> applications.add((Application) task));
        return new ResponseEntity<Object>(applications, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> stopAllApps() {
        List<Application> applications = new ArrayList<>();
        TaskManager.consume((appName, task) -> {
            if (task.isRunning()) task.stop();
            applications.add((Application) task);
        });
        applicationRepository.saveAll(applications);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
