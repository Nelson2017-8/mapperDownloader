package main;

import java.awt.AWTException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static main.ProgramaMinimizado.settings;
import main.ui.uxLinks;
import main.ui.ux;


public class Tasks {
    public Settings settings = null;
    public Browser browser = null;
    public ux interfaz = null;
    public uxLinks interfazLinks = null;
    
    public Tasks(Settings settings){
        this.settings = settings;
    }
    
    public void Sqlite() throws SQLException {
        conn conexion = new conn();
        if (conexion.createTableServicio()) {
            conexion.insertService();
        }
    }
    
    public void Browser () throws IOException {
        browser = new Browser(settings);
    }
    
    public void CopyLinksDownloader(String url, int numCap, String Server) {
        settings.links = null;
        switch (Server) {
            case "AnimeFLV" -> {
                browser.AnimeFLVCopyLinksDownloader(url, numCap);
            }
            default -> {
                if(interfaz == null){
                    StartUX();
                }
                
                JOptionPane.showMessageDialog(null, "El Servidor \""+Server+"\" no es válido");
            }
        }
        
        if(settings.links != null){
            StartUXLinks();
        }else{
            System.err.println(settings.links);
            JOptionPane.showMessageDialog(null, "settings links esta vacio, ocurrio un error");
        }
    }
    public void StartUX(){
        interfaz = new ux(this, settings);
        interfaz.setVisible(true);
    }
    public void StartUXLinks(){
        interfazLinks = new uxLinks(settings.links, settings);
        interfazLinks.setVisible(true);
    }
    public void CloseDriver () {
        if (browser != null){
            browser.driver.quit();
        }
    }
    
}
