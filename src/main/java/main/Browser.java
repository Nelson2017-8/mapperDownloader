/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.swing.JOptionPane;
import main.ui.uxLinks;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Browser {
    public Settings settings = null;
    public WebDriver driver = null;
    public int total = 0;
    public String[] Servidores = null;
    public String[] Formatos = null;
    public String[][] Links = null;
    
    public Browser (Settings settings) throws IOException {
        this.settings = settings;

        // opciones chrome
        ChromeOptions options = new ChromeOptions();
        
        // Carga la extensión AdBlock Plus
        if(this.settings.extensions != null){
            // Cargamos las extensiones
            for (String extension : this.settings.extensions.split(",")) {
                // extension => "src/test/resources/extensions/webextensions-selenium-example.crx"
                Path path = Paths.get(extension);
                File extensionFilePath = new File(path.toUri());
                options.addExtensions(extensionFilePath);
            }
        }

        // especifico la ruta del chrome a usar
        options.setBinary(settings.chromiumFile);
        
        // modo silencio, quita la interfaz grafica para que el usuario no la vea
        if(this.settings.hiddenBrowser){
            //options.addArguments("--headless=new");
        }
        
        System.setProperty("webdriver.chrome.driver", settings.chromiumDriver);
        driver = new ChromeDriver(options);
    }
    
    // Necesita una url para ingresar y copiar los links
    public void AnimeFLVCopyLinksDownloader(String urlAnimeFLVCap1, int numberCaps) {
        //
        String urlAnimeFLV = urlAnimeFLVCap1;
        
        // Navegar a la página que deseas raspar
        driver.get(urlAnimeFLV);
        
        // reinicio las variables
        total = 0;
        Servidores = null;
        Formatos = null;
        Links = new String[numberCaps][];
        settings.links = null;
        
        int i = 0;
        boolean ciclo = true;
        while(ciclo){
            ciclo = NavegatorCopyLinksDownloader(i, numberCaps);
            i += 1;
            if(numberCaps == i){
                ciclo = false;
                //JOptionPane.showMessageDialog(null, "El proceso termino");
            }
        }
        formatLinks();
    }
    public void StartUXLinks(){
        uxLinks interfazLinks = new uxLinks(settings.links, settings);
        interfazLinks.setVisible(true);
    }
    public boolean NavegatorCopyLinksDownloader(int iterador, int max){
        try{
            // button "Descargar episodio"
            driver.findElement(By.cssSelector("#XpndCn > div.CpCnB > div.CapOptns.ClFx.show > span.BtnNw.Dwnd.BxSdw.fa-download.AAShwHdd-lnk")).click();
            int i = 0;
            
            if(Servidores==null){
                // Encuentra los elementos que coincidan con el selector
                List<WebElement> servidorWebElement = driver.findElements(By.cssSelector("#DwsldCn > div > table > tbody > tr > td:nth-child(1)"));
                if(total==0){
                    total = servidorWebElement.size();
                }
                Servidores = new String[total];
                
                // Recorre los elementos encontrados
                for (WebElement elemento : servidorWebElement) {
                    Servidores[i] = elemento.getText();
                    i += 1;
                }
            }
            
            if(Formatos==null){
                // Encuentra los elementos que coincidan con el selector
                List<WebElement> formatoWebElement = driver.findElements(By.cssSelector("#DwsldCn > div > table > tbody > tr > td:nth-child(3)"));
                if(total==0){
                    total = formatoWebElement.size();
                }
                Formatos = new String[total];
                i = 0;

                // Recorre los elementos encontrados
                for (WebElement elemento : formatoWebElement) {
                    Formatos[i] = elemento.getText();
                    i += 1;
                }
            }
            
            // document.querySelectorAll("#DwsldCn > div > table > tbody > tr > td:nth-child(4) > a");
            // Encuentra los elementos que coincidan con el selector
            List<WebElement> linksWebElement = driver.findElements(By.cssSelector("#DwsldCn > div > table > tbody > tr > td:nth-child(4) > a"));
            String[] LinksLocal = new String[total];
            i = 0;
            
            // Recorre los elementos encontrados
            for (WebElement elemento : linksWebElement) {
                // Recorre los elementos encontrados
                LinksLocal[i] = elemento.getAttribute("href");
                i += 1;
            }
            this.Links[iterador] = LinksLocal;
           
            if((iterador+1) < max){
                try{
                    // pasa a la siguiente pagina
                    driver.findElement(By.cssSelector("#XpndCn > div.CpCnB > div.CapOptns.ClFx.show > div > a.CapNvNx.fa-chevron-right")).click();
                }
                catch(Exception ex){
                    // Termino de mapear todos los links
                    JOptionPane.showConfirmDialog(null, "Error NavegatorCopyLinksDownloader Exption:" + ex.getMessage());
                    return false;
                }
            }else{
                return false;
            }
            
        }catch(HeadlessException ex){
            JOptionPane.showConfirmDialog(null, "Error NavegatorCopyLinksDownloader:" + ex.getMessage());
        }
        return true;
    }
    
    public void formatLinks(){
        String formattedLinks = "";
        
        String[] groupsLinks = new String[total];
        String[] titleLinks = new String[total];

        if (Links != null) { // Comprobamos que no sea null
            for (String[] fila : Links) {
                // Bucle externo para las filas
                int count = 0;
                for (String fila2 : fila) {
                    System.out.println("Link: " + fila2);
                    if(groupsLinks[count]==null){
                        groupsLinks[count] = fila2 + "\n";
                    }else{
                        groupsLinks[count] += fila2 + "\n";
                    }
                    
                    try {
                        titleLinks[count] = String.format("%s (%s) \n", Servidores[count], Formatos[count]);
                    } catch (Exception ex) {
                        titleLinks[count] = "Servidor desconocido \n";
                    }
                    count += 1;
                }
            }
            formattedLinks += "Capitulos:\n\n";
            for(int i =0; i< total; i++){
                formattedLinks += String.format("%s", titleLinks[i]);
                formattedLinks += String.format("%s", groupsLinks[i]);
                formattedLinks += "\n\n";
            }
        } else {
            System.out.println("El array Links es null");
        }
        settings.links = formattedLinks;
    }

}
