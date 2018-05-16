package com.minsx.appmanager.client.window;

import org.eclipse.swt.widgets.MessageBox;

public class SystemWindowOperator {

    public static void setIcoVisible(final boolean visible) {
        SystemWindow.TRAY.getItem(0).setVisible(visible);
    }

    public static void setMainWindowVisible(boolean visible) {
        SystemWindow.SHELL.setVisible(visible);
        SystemWindow.SHELL.setMinimized(!visible);
    }

    public static void showMessage(String message, Boolean... isAsync) {
        if (isAsync.length == 0 || !isAsync[0]) {
            MessageBox messageBox = new MessageBox(SystemWindow.SHELL);
            messageBox.setMessage(message);
            messageBox.setText("提示信息 : ");
            messageBox.open();
        } else {
            SystemWindow.DISPLAY.asyncExec(() -> {
                MessageBox messageBox = new MessageBox(SystemWindow.SHELL);
                messageBox.setMessage(message);
                messageBox.setText("提示信息 : ");
                messageBox.open();
            });
        }
    }

}
