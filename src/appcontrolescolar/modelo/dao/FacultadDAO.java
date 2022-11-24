/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appcontrolescolar.modelo.dao;

import appcontrolescolar.modelo.ConexionBD;
import appcontrolescolar.modelo.pojo.Facultad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FacultadDAO {
    public static ArrayList<Facultad> recuperarFacultades() throws SQLException{
        ArrayList <Facultad> facultades = null;
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if(conexionBD != null){
            try {
                String consulta = "SELECT idFacultad, nombre FROM Facultad";
                PreparedStatement consultaFacultad = conexionBD.prepareStatement(consulta);
                ResultSet resultadoConsulta = consultaFacultad.executeQuery();
                facultades = new ArrayList<>();
                while(resultadoConsulta.next()){
                    Facultad aux = new Facultad();
                    aux.setIdFacultad(resultadoConsulta.getInt("idFacultad"));
                    aux.setNombre(resultadoConsulta.getString("nombre"));
                    facultades.add(aux);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally{
                conexionBD.close();
            }
        }
        return facultades;
    }
}

