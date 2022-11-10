/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appcontrolescolar.modelo.dao;

import appcontrolescolar.modelo.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Aaron
 */
public class UsuarioDAO {
    public static void verificarUsuario(String usuario, String password){
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if(conexionBD != null){
            try {
                String consulta = "SELECT idUsuario, nombre, apellidoPaterno,  "
                        + "apellidoMaterno FROM usuario WHERE username = ? and "
                        + "password = ?";
                PreparedStatement consultaLogin = conexionBD.prepareStatement(consulta);
                consultaLogin.setString(1, usuario);
                consultaLogin.setString(2, password);
                ResultSet resultadoConsulta = consultaLogin.executeQuery();
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
