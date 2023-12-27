/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import java.sql.SQLException;

/**
 *
 * @author Nelson
 */
public class Main {

    public static void main(String[] args) throws SQLException, Exception {        
        Settings settings = new Settings();
        ProgramaMinimizado program = new ProgramaMinimizado();
        program.main(settings);
    }
}
