/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appcontrolescolar.modelo.dao;

import appcontrolescolar.modelo.ConexionBD;
import appcontrolescolar.modelo.pojo.Alumno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Aaron
 */
public class AlumnoDAO {
    public static ArrayList<Alumno> obtenerAlumnos() throws SQLException{
        ArrayList<Alumno> alumnosBD = null;
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if (conexionBD != null){
            try {
                String consulta = "SELECT `idAlumno`, alumno.nombre, `apellidoPaterno`, "
                        + "`apellidoMaterno`, `matricula`, `correo`, `fechaNacimiento`, "
                        + "alumno.idCarrera, carrera.nombre as 'carrera', carrera.idFacultad, "
                        + "facultad.nombre as 'facultad' "
                        +       "FROM `alumno` "
                        +       "INNER JOIN carrera ON alumno.idCarrera = carrera.idCarrera "
                        +       "INNER JOIN facultad ON carrera.idFacultad = facultad.idFacultad ";
                PreparedStatement consultaObtenerTodo = conexionBD.prepareStatement(consulta);
                ResultSet resultadoConsulta = consultaObtenerTodo.executeQuery();
                alumnosBD = new ArrayList<>();
                while(resultadoConsulta.next()){
                    Alumno temp = new Alumno();
                    temp.setIdAlumno(resultadoConsulta.getInt("idAlumno"));
                    temp.setNombre(resultadoConsulta.getString("nombre"));
                    temp.setApellidoPaterno(resultadoConsulta.getString("apellidoPaterno"));
                    temp.setApellidoMaterno(resultadoConsulta.getString("apellidoMaterno"));
                    temp.setMatricula(resultadoConsulta.getString("matricula"));
                    temp.setCorreo(resultadoConsulta.getString("correo"));
                    temp.setFechaNacimiento(resultadoConsulta.getString("fechaNacimiento"));
                    temp.setIdCarrera(resultadoConsulta.getInt("idCarrera"));
                    temp.setCarrera(resultadoConsulta.getString("carrera"));
                    temp.setIdFacultad(resultadoConsulta.getInt("idFacultad"));
                    temp.setFacultad(resultadoConsulta.getString("facultad"));
                    alumnosBD.add(temp);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }
        return alumnosBD;
    }
}
