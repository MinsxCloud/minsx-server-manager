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
package com.minsx.appmanager.client.window;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;

public class SystemWindow {

    public static Shell SHELL;
    public static Display DISPLAY;
    public static Tray TRAY;
    public static TrayItem TRAY_ITEM;
    public static Menu MENU;
    public static MenuItem EXIT_MENU_ITEM, APP_DETAILS_MENU_ITEM, SETTING_MENU_ITEM;

    public static void createContents() {
        SHELL = new Shell(DISPLAY, SWT.NONE);
        SHELL.setImage(SWTManager.getResourceImage("Logo.ico"));
        SHELL.setSize(0, 0);
        SHELL.setText("应用管理系统");
        //system bar icon control
        TRAY = DISPLAY.getSystemTray();
        TRAY_ITEM = new TrayItem(TRAY, SWT.NONE);
        TRAY_ITEM.setVisible(true);
        TRAY_ITEM.setText(SHELL.getText());
        TRAY_ITEM.setToolTipText(SHELL.getText());
        TRAY_ITEM.setImage(SWTManager.getResourceImage("Logo.ico"));
        //menu container
        MENU = new Menu(SHELL, SWT.POP_UP);
        //app details menu item
        APP_DETAILS_MENU_ITEM = new MenuItem(MENU, SWT.PUSH);
        APP_DETAILS_MENU_ITEM.setText("应用详情");
        APP_DETAILS_MENU_ITEM.setImage(SWTManager.getResourceImage("Details.png"));
        //setting menu item
        SETTING_MENU_ITEM = new MenuItem(MENU, SWT.PUSH);
        SETTING_MENU_ITEM.setText("系统设置");
        SETTING_MENU_ITEM.setImage(SWTManager.getResourceImage("Setting.png"));
        //exit menu item
        EXIT_MENU_ITEM = new MenuItem(MENU, SWT.PUSH);
        EXIT_MENU_ITEM.setText("退出程序");
        EXIT_MENU_ITEM.setImage(SWTManager.getResourceImage("Exit.png"));


    }


}
