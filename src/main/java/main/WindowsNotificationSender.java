package main;

import java.awt.*;
import java.awt.TrayIcon.MessageType;

public class WindowsNotificationSender {
    public Settings settings = null;
    
    public WindowsNotificationSender(Settings settings){
        this.settings = settings;
    }
    public void sendNotification(String title, String message) throws AWTException {
        if (SystemTray.isSupported()) {
            WindowsNotificationSender td = new WindowsNotificationSender(settings);
            td.displayTray(title, message);
        } else {
            System.err.println("System tray not supported!");
        }
    }
    public void sendNotification(String title, String message, String messageType) throws AWTException {
        if (SystemTray.isSupported()) {
            WindowsNotificationSender td = new WindowsNotificationSender(settings);
            td.displayTray(title, message, messageType);
        } else {
            System.err.println("System tray not supported!");
        }
    }

    public void displayTray(String title, String message, String messageType) throws AWTException {
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage(settings.iconApp);
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);
        
        MessageType mt = MessageType.INFO;
        switch (messageType) {
            case "info" -> mt = MessageType.INFO;
            case "error" -> mt = MessageType.ERROR;
            case "warning" -> mt = MessageType.WARNING;
            case "none" -> mt = MessageType.NONE;
        }

        trayIcon.displayMessage(title, message, mt);
    }
    public void displayTray(String title, String message) throws AWTException {
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage(settings.iconApp);
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);
        
        trayIcon.displayMessage(title, message, MessageType.INFO);
    }
    
}