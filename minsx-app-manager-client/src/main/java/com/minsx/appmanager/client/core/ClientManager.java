/*
 *
 *  * Copyright 2018 https://www.minsx.com
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
package com.minsx.appmanager.client.core;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.minsx.appmanager.client.window.SystemWindowManager;

public class ClientManager {

    public static void start(String args[]) {
        SystemWindowManager.open();
    }

    public static void exit() {
        SystemWindowManager.close();
        stopAllApps();
        System.exit(0);
    }


    public static void stopAllApps() {
        try {
            HttpResponse<String> response = Unirest.get("http://localhost:8695/system/stopAllApps")
                    .header("cache-control", "no-cache")
                    .asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }


}
