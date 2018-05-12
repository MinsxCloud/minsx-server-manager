package com.minsx.appmanager.window;

import org.eclipse.swt.events.MenuDetectEvent;
import org.eclipse.swt.events.MenuDetectListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.widgets.MessageBox;

public class ListenerManager {

    public static void addListeners() {

        MainWindow.SHELL.addShellListener(new ShellAdapter() {
            @Override
            public void shellIconified(ShellEvent e) {
                WindowOperator.setMainWindowDisVisible();
            }

            @Override
            public void shellClosed(ShellEvent e) {
                MessageBox mb = new MessageBox(MainWindow.SHELL, 4 | 32 | 256);
                mb.setText("提示");
                mb.setMessage("您确定要退出吗?");
                int rc = mb.open();
                if (e.doit == (rc == 32)) {
                    /*ApplicationManager.exit();*/
                } else if (e.doit == (rc == 256)) {
                    e.doit = false;
                }
            }
        });

        // 右下角左键单击图标事件
        MainWindow.TRAY_ITEM.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            }
        });

        // 在系统栏图标点击鼠标右键时的事件
        MainWindow.TRAY_ITEM.addMenuDetectListener(new MenuDetectListener() {
            @Override
            public void menuDetected(MenuDetectEvent e) {
                MainWindow.MAIN_MENU.setVisible(true);
            }
        });

        // 右下角退出程序菜单监听事件
        MainWindow.EXIT_MENU_ITEM.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                MessageBox mb = new MessageBox(MainWindow.SHELL, 4 | 32 | 256);
                mb.setText("提示");
                mb.setMessage("您确定要退出吗?");
                int rc = mb.open();
                if (e.doit == (rc == 32)) {
                } else if (e.doit == (rc == 256)) {
                    e.doit = false;
                }
            }
        });
    }

}
