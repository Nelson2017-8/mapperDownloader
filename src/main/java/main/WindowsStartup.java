/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WindowsStartup {
    public static String comando_add = "REG ADD HKEY_CURRENT_USER";
    public static String comando_remove = "REG DELETE HKEY_CURRENT_USER";

    public static String addServicesOnWindows(String nameService, String SO, String rutaFile) {
        String commandRegister = "";
        String response = "";
        commandRegister = switch (SO) {
            case "XP" -> "\\Software\\" + nameService + " /v " + nameService + " /t REG_SZ /d";
            default -> "\\Software\\Microsoft\\Windows\\CurrentVersion\\Run /v " + nameService + " /t REG_SZ /d";
        };

        try {
            String comando = comando_add + commandRegister + " " + "\"" + rutaFile + "\" /f";
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", comando);
            Process proceso = builder.start();
            BufferedReader read = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String linea;
            while ((linea = read.readLine()) != null) {
                response += linea + "\n";
            }
        } catch (IOException ex) {
            response = ex.getLocalizedMessage();
        }
        return response;
    }

    public static String removeServicesOnWindows(String nameService, String SO) {
        String commandRemove = "";
        String response = "";
        commandRemove = switch (SO) {
            case "XP" -> "\\Software\\Pepsi /v " + nameService + " /f";
            default -> "\\Software\\Microsoft\\Windows\\CurrentVersion\\Run /v " + nameService + " /f";
        };

        try {
            String comando = comando_remove + commandRemove;
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", comando);
            Process proceso = builder.start();
            BufferedReader read = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String linea;
            while ((linea = read.readLine()) != null) {
                response += linea + "\n";
            }
        } catch (IOException ex) {
            response = ex.getLocalizedMessage();
        }
        return response;
    }
}