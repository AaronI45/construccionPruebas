/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appcontrolescolar.modelo.dao;

import appcontrolescolar.modelo.ConexionBD;
import appcontrolescolar.modelo.pojo.Alumno;
import appcontrolescolar.modelo.pojo.ResultadoOperacion;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
    
    public static ResultadoOperacion registrarAlumno(Alumno alumnoNuevo, File foto) throws SQLException{
        ResultadoOperacion respuesta = new ResultadoOperacion();
        respuesta.setError(true);
        respuesta.setNumeroFilasAfectadas(-1);
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if(conexionBD != null){
            try {
                String sentencia = "INSERT INTO alumno (matricula, nombre, apellidoPaterno, "
                        + "apellidoMaterno, correo, fechaNacimiento, idCarrera, foto) "
                        + "VALUES (?,?,?,?,?,?,?,?)";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(sentencia);
                prepararSentencia.setString(1, alumnoNuevo.getMatricula());
                prepararSentencia.setString(2, alumnoNuevo.getNombre());
                prepararSentencia.setString(3, alumnoNuevo.getApellidoPaterno());
                prepararSentencia.setString(4, alumnoNuevo.getApellidoMaterno());
                prepararSentencia.setString(5, alumnoNuevo.getCorreo());
                prepararSentencia.setString(6, alumnoNuevo.getFechaNacimiento());
                prepararSentencia.setInt(7, alumnoNuevo.getIdCarrera());
                
                FileInputStream fotoAlumno = new FileInputStream(foto);
                prepararSentencia.setBlob(8, fotoAlumno);
                int numeroFilas = prepararSentencia.executeUpdate();
                if (numeroFilas > 0){
                    respuesta.setError(false);
                    respuesta.setMensaje("Alumno registrado correctamente.");
                    respuesta.setNumeroFilasAfectadas(numeroFilas);
                }else{
                    respuesta.setMensaje("No se pudo registrar la informacion del alumno.");
                }
            } catch (SQLException e) {
                respuesta.setMensaje(e.getMessage());
            }catch(FileNotFoundException f){
                respuesta.setMensaje(f.getMessage());
            }
            finally{
                conexionBD.close();
            }
        }else{
            respuesta.setMensaje("Por el momento no hay conexion a la base de datos");
        }
        return respuesta;
    }
    
    //TODO elminiar
    //TODO editar por id
}
