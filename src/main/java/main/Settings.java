package main;

import javax.swing.JOptionPane;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Settings {
    public String path = null; // ruta base
    public String appName = null;
    public String version = null;
    public String iconApp = null;
    public String resources = null;
    public String images = null;
    public String nameDB = null;
    public String pathDB = null;
    public String chromiumFile = null;
    public String chromiumDriver = null;
    public String logsFile = null;
    public boolean hiddenBrowser = false;
    public String extensions = null;
    public String links = null;
    private String configINI = null;
    
    public Settings(){
        path = Utils.getPath();
        configINI = path + "\\config.ini";
        this.readFileINI();
        //nameApp = "Scraper Server";
        //version = "1.0.0";
        //iconApp = path+"\\resources\\images\\icon.png";

    }
    private void readFileINI() {
        Properties propiedades = new Properties();
        try {
            try (FileInputStream archivoINI = new FileInputStream(configINI)) {
                propiedades.load(archivoINI);
            }
        } catch (IOException e) {
            String error = "Error al leer el archivo INI: " + e.getMessage();
            Themes.windowsNative();
            JOptionPane.showMessageDialog(null, error);
            System.err.println(error);
            System.exit(0);
            return;
        }
        
        // general
        appName = propiedades.getProperty("nameApp");
        version = propiedades.getProperty("version");
        iconApp = path + "\\" + propiedades.getProperty("iconApp");
        resources = path + "\\" + propiedades.getProperty("resources");
        images = path + "\\" + propiedades.getProperty("images");
        
        // database
        nameDB = propiedades.getProperty("nameDB");
        pathDB = path + "\\" + propiedades.getProperty("pathDB");
        
        // chromium
        chromiumFile = path + "\\" + propiedades.getProperty("chromiumFile");
        chromiumDriver = path + "\\" + propiedades.getProperty("chromiumDriver");
        
        // logs
        logsFile = path + "\\" + propiedades.getProperty("logsFile");
        
        // config
        hiddenBrowser = Boolean.parseBoolean(propiedades.getProperty("hiddenBrowser") + "");
        
        // Por ejemplo, si el archivo .ini tiene el siguiente contenido:
        // extensiones=extension1.crx,extension2.crx,src/test/resources/extensions/webextensions-selenium-example.crx
        extensions = propiedades.getProperty("extensions");
        
        
        /**System.out.println(path);
        System.out.println(nameApp);
        System.out.println(version);
        System.out.println(iconApp);
        System.out.println(resources);
        System.out.println(images);
        System.out.println(nameDB);
        System.out.println(pathDB);
        System.out.println(chromiumFile);
        System.out.println(chromiumDriver);
        System.out.println(logsFile);
        
        Utils.checkFileOrDir(path); //
        Utils.checkFileOrDir(nameApp); //
        Utils.checkFileOrDir(version); //
        Utils.checkFileOrDir(iconApp); //
        Utils.checkFileOrDir(resources); //
        Utils.checkFileOrDir(images); //
        Utils.checkFileOrDir(nameDB); //
        Utils.checkFileOrDir(pathDB); //
        Utils.checkFileOrDir(chromiumFile); //
        Utils.checkFileOrDir(chromiumDriver); //
        Utils.checkFileOrDir(logsFile); //
        System.exit(0);**/

    }
}
