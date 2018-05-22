package com.minsx.appmanager.client.window;

import com.minsx.appmanager.client.core.ClientManager;
import com.minsx.appmanager.client.core.SystemUtils;
import com.minsx.appmanager.core.task.TaskManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.MessageBox;

import java.text.SimpleDateFormat;

public class ListenerManager {


    private final static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void createListeners() {


        SystemWindow.TRAY_ITEM.addListener(SWT.MouseHover, event -> {
            StringBuffer status = new StringBuffer();
            status.append("AppName").append("  ").append("BeginTime").append("\n");
            status.append("").append("\n");
            TaskManager.consume((appName, task) -> {
                status.append(appName).append(" ").append(SDF.format(task.getBeginTime())).append("\n");
            });
            status.deleteCharAt(status.length() - 1);
            SystemWindow.TRAY_ITEM.setToolTipText(status.toString());
        });


        // The left click of the lower right the icon event
        SystemWindow.TRAY_ITEM.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                SystemUtils.openWeb("http://localhost:8695/overview");
            }
        });

        // The left click of the lower right app details menu event
        SystemWindow.APP_DETAILS_MENU_ITEM.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                SystemUtils.openWeb("http://localhost:8695/overview");
            }
        });

        // The left click of the lower right setting menu event
        SystemWindow.SETTING_MENU_ITEM.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                SystemUtils.openWeb("http://localhost:8695/setting");
            }
        });

        // Right key at the lower right the icon event
        SystemWindow.TRAY_ITEM.addMenuDetectListener(e -> SystemWindow.MENU.setVisible(true));

        // Exit the program menu event at the lower right
        SystemWindow.EXIT_MENU_ITEM.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                MessageBox mb = new MessageBox(SystemWindow.SHELL, 4 | 32 | 256);
                mb.setText("提示");
                mb.setMessage("您确定要退出吗?");
                int rc = mb.open();
                if (e.doit == (rc == 32)) {
                    ClientManager.exit();
                } else if (e.doit == (rc == 256)) {
                    e.doit = false;
                }
            }
        });
    }

}
