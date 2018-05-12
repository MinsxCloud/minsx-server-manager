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
package com.minsx.appmanager.window;

import com.minsx.appmanager.api.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;

public class MainWindow implements Window {

    public static Shell SHELL;
    public static Display DISPLAY;
    public static Tray TRAY;
    public static TrayItem TRAY_ITEM;
    public static Menu MAIN_MENU;
    public static MenuItem EXIT_MENU_ITEM;

    /**
     * @wbp.parser.entryPoint
     */
    @Override
    public void open() {
        try {
            DISPLAY = Display.getDefault();
            createContents();
            SHELL.layout();
            while (!SHELL.isDisposed()) {
                if (!DISPLAY.readAndDispatch()) {
                    DISPLAY.sleep();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void show() {

    }

    public void createContents() {
        SHELL = new Shell(DISPLAY, SWT.NONE);
        SHELL.setImage(SWTManager.getResourceImage("logo.ico"));
        SHELL.setSize(0, 0);
        SHELL.setText("SYSTEM_NAME");
        // 构造系统栏控件(用于系统菜单容器)
        TRAY = DISPLAY.getSystemTray();
        TRAY_ITEM = new TrayItem(TRAY, SWT.NONE);
        TRAY_ITEM.setVisible(true);
        TRAY_ITEM.setToolTipText(SHELL.getText());
        TRAY_ITEM.setImage(SWTManager.getResourceImage("logo.ico"));
        // 系统菜单
        MAIN_MENU = new Menu(SHELL, SWT.POP_UP);
        // 初始化各应用程序菜单
        // 菜单分隔线
        new MenuItem(MAIN_MENU, SWT.SEPARATOR);
        // 退出菜单
        EXIT_MENU_ITEM = new MenuItem(MAIN_MENU, SWT.PUSH);
        EXIT_MENU_ITEM.setText("退出放疗云");
        /*EXIT_MENU_ITEM.setImage(SWTManager.getResourceImage("exit.png"));*/
    }


}
