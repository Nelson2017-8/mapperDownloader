/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Utils {

    public static void checkFileOrDir(String path) {
        File fileOrDir = new File(path);

        if (fileOrDir.exists()) { // Verificar si el archivo o carpeta existe
            if (fileOrDir.isDirectory()) { // Verificar si es una carpeta
                System.out.println("La carpeta existe: " + path);
            } else { // Si no es una carpeta, es un archivo
                System.out.println("El archivo existe: " + path);
            }
        } else { // Si no existe
            System.out.println("El archivo o carpeta no existe: " + path);
        }
    }
    // verifica si tiene internet la maquina
    public static boolean checkConexionRed() {
        try {
            InetAddress address = InetAddress.getByName("www.google.com");
            if (address.isReachable(5000)) {
                System.out.println("Hay conexión a internet");
                return true;
            } else {
                System.out.println("No hay conexión a internet");
            }
        } catch (UnknownHostException e) {
            System.err.println("No se pudo resolver el host");
        } catch (Exception e) {
            System.err.println("Error al verificar la conexión a internet");
        }
        return false;
    }
    
    public static String formattedDate(String fechaHoraString) throws ParseException{
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm a");
        Date date = parser.parse(fechaHoraString);
        //System.out.println(formatter.format(date));
        
        return formatter.format(date);
    } 
    public static String getPlatfKey(String cadena){
        String respuesta = "";
        switch (cadena) {
            case "VDT", "DolarToday" -> respuesta = "Dólar Today";
            case "VMO", "Monitor Dolar Venezuela" -> respuesta = "Monitor Dólar";
            case "VES", "BCV" -> respuesta = "Banco central de Venezuela (BCV)";
            case "VCRIP" -> respuesta = "Cripto Dolar";
            case "VBIN" -> respuesta = "Binance";
            case "VEPAY" -> respuesta = "PayPal";
            case "VEUPH" -> respuesta = "Dólar Uphold";
            case "VEAMZ" -> respuesta = "Amazon Gift Card";
        }
        
        return respuesta;
    }
    public static String formattedStringPrice(String price){
        String formatted = price;
        formatted = formatted.trim();
        formatted = formatted.replace("Bs", "");
        formatted = formatted.replace(".", "");
        formatted = formatted.trim();
        
        // reducir la cadena a 5 caracteres
        if(formatted.length() > 5){
            formatted = formatted.substring(0, 5);
        }
        
        return formatted;
    }
    public static Map<Integer, Object> createMap(List<String> names, List<String> values) {
        Map<Integer, Object> map = new HashMap<>();
        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            String value = values.get(i);
            Object obj;
            obj = new Object[]{ name, value };
            map.put(i, obj);
        }
        return map;
    }
    
    public static String getPath(String file){
        String path = new File(file).getAbsolutePath();
        return path;
    }
    public static String getPath(){
        String path = new File("").getAbsolutePath();
        return path;
    }
    // Tu variable de tipo JsonArray con la data
    public static void saveJson(JsonArray data){

        // Ruta y nombre del archivo .json donde se guardará la data
        String filePath = "data.json";

        // Objeto Gson para formatear la data en formato JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Guardar la data en el archivo .json
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(data, writer);
            System.out.println("Data guardada exitosamente en el archivo .json.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Objeto deserializeJson(String json) {
        final Gson gson = new Gson();
        final Objeto dollarGroup = gson.fromJson(json, Objeto.class);
        
        System.out.println(dollarGroup.getId());
        return dollarGroup;
    }
    public static JsonArray decodeJsonArray(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, JsonArray.class);
    }
    
    public static JsonObject decodeJsonObject(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, JsonObject.class);
    }
    
    //String json = "[{\"id\":3}, {\"id\":4}]";
    public static List<Objeto> deserializeJsonGson(String json) {
        

        // Definir el tipo de objeto deseado utilizando TypeToken
        Type tipoLista = new TypeToken<List<Objeto>>(){}.getType();

        // Deserializar el objeto JSON en una lista de objetos Java
        List<Objeto> listaObjetos = new Gson().fromJson(json, tipoLista);

        // Acceder a los objetos individualmente
        for (Objeto objeto : listaObjetos) {
            System.out.println("ID: " + objeto.getId());
        }
        return listaObjetos;
    }

    // Clase para representar el objeto en Java
    public static class Objeto {
        private int id;

        public int getId() {
            return id;
        }
    }
    
    
    
    
    public static final boolean isServicesAdd() throws FileNotFoundException, IOException {
        boolean response = false;
        var con = new conn();
        response = con.isServices();
        con = null;
        return response;
    }

    public static final boolean addServiceConfig() throws FileNotFoundException, IOException, SQLException {
        boolean response = false;
        var con = new conn();
        response = con.insertService();
        con = null;
        return response;
    }
    
    public static boolean isMegaLink(String link) throws MalformedURLException {
        URL url = new URL(link);
        String host = url.getHost();
        return host.endsWith(".mega.nz");
    }
}
