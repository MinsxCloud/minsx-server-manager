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
package com.minsx.appmanager.web.service.api;

import com.minsx.appmanager.core.task.Task;
import com.minsx.appmanager.web.entity.Application;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ApplicationService {

    Application saveTask(Task task);

    ResponseEntity<?> addApplication(Application application);

    ResponseEntity<?> deleteApplication(String appName);

    ResponseEntity<?> updateApplication(Application application);

    ResponseEntity<?> getApplication(String appName);

    ResponseEntity<?> listApplications();

    ResponseEntity<?> stopAllApps();

    ResponseEntity<?> startApp(String appName);

    ResponseEntity<?> reStartApp(String appName);

    ResponseEntity<?> stopApp(String appName);

    ResponseEntity<?> getApp(String appName);
}
