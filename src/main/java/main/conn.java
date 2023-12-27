/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package main;

import java.sql.*;
/**
 *
 * @author Nelson
 */
public class conn {

    
    // conexion
    public static Connection conectar(){
        Connection connection = null;
        Settings settings = new Settings();
        try{
            // carga el controlador 
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + settings.pathDB);
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
       
    
    // cerrar base de datos
    public static void cerrar(Connection connection){
        try{
            if(connection != null){
                connection.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    
    public boolean createTableServicio() {
        try (Connection connection = conectar()) {
            Statement statement = connection.createStatement();
            
            
            String sql = "CREATE TABLE IF NOT EXISTS SERVICE "
                    + "(id CHAR(1) PRIMARY KEY  NOT NULL,"
                    + " estado  CHAR(20))";
            statement.executeUpdate(sql);
            statement.close();
            connection.close();
            System.out.println("Tabla creada");
            
            return true;
            
        } 
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public boolean insertService() throws SQLException {
        boolean response = false;
        try (Connection connection = conectar()) {
            Statement stmt = connection.createStatement();
            
            
            createTableServicio();
            connection.setAutoCommit(false);
            System.out.println("Opened database successfully");
            String sql = "INSERT INTO SERVICE (id,estado) "
                    + "VALUES ('1', 'OK');";
            stmt.executeUpdate(sql);
            stmt.close();
            connection.commit();
            connection.close();
            System.out.println("Records created successfully");
            response = true;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        
   
        return response;
    }

    public boolean isServices() {
        boolean response = false;
        try (Connection connection = conectar()) {
            Statement stmt = connection.createStatement();
            
            createTableServicio();
            connection.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM SERVICE where id = '1';");
            while (rs.next()) {
                response = true;
            }
            rs.close();
            stmt.close();
            connection.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
 
        return response;
    }
}
