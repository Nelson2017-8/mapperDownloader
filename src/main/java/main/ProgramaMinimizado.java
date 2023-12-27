/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package main;


import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProgramaMinimizado {
    public static String appName = "";
    public static Settings settings = null;
    public Tasks tasks = null;
    
    
    public void main(Settings sett) throws IOException, Exception {
        settings = sett;
        tasks = new Tasks(settings);
        appName = settings.appName;
        // inicia tarea de base de datos
        //tasks.Sqlite();
        
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();

            Image icon = Toolkit.getDefaultToolkit().getImage(settings.iconApp); // Reemplaza con la ruta a tu icono
            
            PopupMenu menu = new PopupMenu();
            
            MenuItem openBrowser = new MenuItem("Abrir navegador");
            MenuItem donwloader = new MenuItem("Anime Maper");
            MenuItem salirItem = new MenuItem("Salir");
            
            openBrowser.addActionListener((ActionEvent e) -> {
                try {
                    tasks.CloseDriver();
                    tasks.Browser(); // inicia el navegador
                } catch (IOException ex) {
                    Logger.getLogger(ProgramaMinimizado.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            donwloader.addActionListener((ActionEvent e) -> {
                tasks.StartUX(); // inicia la interfaz
            });
            
            salirItem.addActionListener((ActionEvent e) -> {
                tasks.CloseDriver();
                System.exit(0);
            });
            
            menu.add(openBrowser);
            menu.add(donwloader);
            menu.add(salirItem);

            TrayIcon trayIcon = new TrayIcon(icon, appName, menu);
            trayIcon.setImageAutoSize(true);

            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                System.err.println("Error al agregar el icono al System Tray");
            }

            tasks.Browser(); // inicia el navegador
            tasks.StartUX(); // inicia la interfaz
        } else {
            System.err.println("El System Tray no es soportado en este sistema");
        }
        
    }

}