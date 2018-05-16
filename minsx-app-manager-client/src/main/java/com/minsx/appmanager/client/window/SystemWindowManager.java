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
package com.minsx.appmanager.client.window;

import org.eclipse.swt.widgets.Display;

public class SystemWindowManager {

    public final static SystemWindow SYSTEM_WINDOW = new SystemWindow();

    /**
     * @wbp.parser.entryPoint
     */
    public static void open() {
        SystemWindow.DISPLAY = Display.getDefault();
        SystemWindow.createContents();
        ListenerManager.createListeners();
        SystemWindow.SHELL.layout();
        while (!SystemWindow.SHELL.isDisposed()) {
            if (!SystemWindow.DISPLAY.readAndDispatch()) {
                SystemWindow.DISPLAY.sleep();
            }
        }
    }

    public static void close() {
        SystemWindowOperator.setIcoVisible(false);
        SystemWindow.SHELL.close();
        SystemWindow.DISPLAY.close();
    }


}
