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
package com.minsx.appmanager.client.core;

import java.io.IOException;

public class SystemUtils {

    public static Boolean openWeb(String URI) {
        Boolean opened = true;
        try {
            java.net.URI uri = new java.net.URI(URI);
            java.awt.Desktop.getDesktop().browse(uri);
        } catch (Exception e) {
            try {
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + URI);
            } catch (IOException e1) {
                try {
                    String cmd = "cmd /c start iexplore %s";
                    Runtime.getRuntime().exec(String.format(cmd, URI));
                } catch (Exception e2) {
                    opened = false;
                }
            }
        }
        return opened;
    }


}
