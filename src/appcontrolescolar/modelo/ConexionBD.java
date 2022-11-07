/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appcontrolescolar.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

public class ConexionBD {
    private static String driver="com.mysql.jdbc.Driver";
    
    private static String bd = "controlescolar";
    
    private static String ip = "localhost";
    
    private static String puerto = "3306";
    
    private static String urlConexion = "jdbc:mysql://"+ip+":"+puerto
            +"/"+bd+"?allowPublicKeyRetrieval=true&useSSL=false";
    
    private static String usuario = "ControlEscolarAdm";
    private static String password = "Fun33M0nk335";
    
    public static Connection abrirConexionBD(){
        Connection conexionBD = null;
        try {
            Class.forName(driver);
            conexionBD = DriverManager.getConnection(bd, usuario, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return conexionBD;
    }
}
