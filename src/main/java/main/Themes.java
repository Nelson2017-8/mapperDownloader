/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Nelson: Más informacion https://stackoverflow.com/questions/3954616/java-look-and-feel-lf
 */
public class Themes {
    
    private static void theme(String theme){
        try {
            UIManager.setLookAndFeel(theme);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    public static void windowsNative(){
        theme("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    }
    
    public static void uiNative(){
        theme(UIManager.getSystemLookAndFeelClassName());
    }
    public static void nimbus(){
        theme("javax.swing.plaf.nimbus.NimbusLookAndFeel");
    }
    public static void metal(){
        theme("javax.swing.plaf.metal.MetalLookAndFeel");
    }
    public static void cde(){
        theme("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
    }
    public static void windows(){
        theme("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    }
    public static void windowsClassic(){
        theme("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
    }
    
    public static void allTheme(){
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            System.out.println(info.getName());
        }
    }
}
