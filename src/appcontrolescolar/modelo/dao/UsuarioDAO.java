/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appcontrolescolar.modelo.dao;

import appcontrolescolar.modelo.ConexionBD;
import appcontrolescolar.modelo.pojo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    public static Usuario verificarUsuario(String usuario, String password) throws SQLException{
        Usuario usuarioSesion = null;
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
                usuarioSesion = new Usuario();
                if(resultadoConsulta.next()){
                    usuarioSesion.setIdUsuario(resultadoConsulta.getInt("idUsuario"));
                    usuarioSesion.setNombre(resultadoConsulta.getString("nombre"));
                    usuarioSesion.setApellidoPaterno(resultadoConsulta.getString("apellidoPaterno"));
                    usuarioSesion.setApellidoMaterno(resultadoConsulta.getString("apellidoMaterno"));
                }else{
                    usuarioSesion.setIdUsuario(-1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally{
                conexionBD.close();
            }
        }
        return usuarioSesion;
    }
}
