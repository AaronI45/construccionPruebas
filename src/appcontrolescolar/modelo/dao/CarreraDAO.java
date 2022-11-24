/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appcontrolescolar.modelo.dao;

import appcontrolescolar.modelo.ConexionBD;
import appcontrolescolar.modelo.pojo.Carrera;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarreraDAO {
    public static ArrayList<Carrera> obtenerCarreraFacultad(int idFacultad) throws SQLException{
        ArrayList<Carrera> carreras = null;
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if (conexionBD != null){
            try {
                String consulta = "Select idCarrera, nombre, codigo FROM carrera "
                        + "WHERE idFacultad = ?";
                PreparedStatement prepararConsulta = conexionBD.prepareCall(consulta);
                prepararConsulta.setInt(1, idFacultad);
                ResultSet resultadoConsulta = prepararConsulta.executeQuery();
                carreras = new ArrayList<>();
                while (resultadoConsulta.next()){
                    Carrera carrera = new Carrera();
                    carrera.setIdCarrera(resultadoConsulta.getInt("idCarrera"));
                    carrera.setNombre(resultadoConsulta.getString("nombre"));
                    carrera.setCodigo(resultadoConsulta.getString("codigo"));
                    carreras.add(carrera);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally{
                conexionBD.close();
            }
        }
        return carreras;
    }
}
