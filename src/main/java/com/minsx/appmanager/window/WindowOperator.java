package com.minsx.appmanager.window;

import org.eclipse.swt.widgets.MessageBox;

public class WindowOperator {

    public static void setIcoVisible(final boolean visible) {
        MainWindow.TRAY.getItem(0).setVisible(visible);
    }

    public static void setMainWindowDisVisible() {
        try {
            MainWindow.SHELL.setVisible(false);
            MainWindow.SHELL.setMinimized(true);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showMessage(String message) {
        MainWindow.DISPLAY.asyncExec(() -> {
            MessageBox messageBox = new MessageBox(MainWindow.SHELL);
            messageBox.setMessage(message);
            messageBox.setText("提示信息 : ");
            messageBox.open();
        });
    }

}
