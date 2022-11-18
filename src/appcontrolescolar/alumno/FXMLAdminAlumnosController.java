/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package appcontrolescolar.alumno;

import appcontrolescolar.modelo.dao.AlumnoDAO;
import appcontrolescolar.modelo.dao.FacultadDAO;
import appcontrolescolar.modelo.pojo.Alumno;
import appcontrolescolar.modelo.pojo.Facultad;
import appcontrolescolar.util.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class FXMLAdminAlumnosController implements Initializable {

    @FXML
    private TextField tfBuscar;
    @FXML
    private TableView<Alumno> tvAlumnos;
    @FXML
    private TableColumn colMatricula;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colApellidoPaterno;
    @FXML
    private TableColumn colApellidoMaterno;
    @FXML
    private TableColumn colCorreo;
    @FXML
    private TableColumn colCarrera;
    @FXML
    private TableColumn colFacultad;

    ObservableList<Alumno> listaAlumnos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaAlumnos = FXCollections.observableArrayList();
        configurarTabla();
        cargarDatosTabla();
        //pruebaFacultad();
    }    
    
    private void configurarTabla(){
        colMatricula.setCellValueFactory(new PropertyValueFactory("matricula"));
        colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        colApellidoPaterno.setCellValueFactory(new PropertyValueFactory("apellidoPaterno"));
        colApellidoMaterno.setCellValueFactory(new PropertyValueFactory("apellidoMaterno"));
        colCorreo.setCellValueFactory(new PropertyValueFactory("correo"));
        colCarrera.setCellValueFactory(new PropertyValueFactory("carrera"));
        colFacultad.setCellValueFactory(new PropertyValueFactory("facultad"));
    }
    
    private void cargarDatosTabla(){
        try {
            ArrayList<Alumno> alumnoBD = AlumnoDAO.obtenerAlumnos();
            listaAlumnos.addAll(alumnoBD);
            tvAlumnos.setItems(listaAlumnos);
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
    }
    
//    private void pruebaFacultad(){
//        try {
//            ArrayList<Facultad> facultades = FacultadDAO.recuperarFacultades();
//            for (int i = 0 ; i<facultades.size(); i++){
//                System.out.println(facultades.get(i).getNombre());
//            }
//        } catch (SQLException | NullPointerException e) {
//            e.printStackTrace();
//        }
//    }
    
    @FXML
    private void clicBtnAgregar(ActionEvent event) {
        irFormulario();
    }

    @FXML
    private void clicBtnModificar(ActionEvent event) {
    }

    @FXML
    private void clicBtnEliminar(ActionEvent event) {
    }
    
    private void irFormulario(){
        try{
            Parent vista = FXMLLoader.load(getClass().getResource("FXMLFormularioAlumno.fxml"));
            Scene escenaFormulario = new Scene(vista);
            Stage escenarioNuevo = new Stage();
            escenarioNuevo.setScene(escenaFormulario);
            escenarioNuevo.initModality(Modality.APPLICATION_MODAL);
            escenarioNuevo.showAndWait();
        }catch(IOException e){
            Utilidades.mostrarAlestaSimple("Error", "No se puede mostrar la pantalla de formulario", 
                    Alert.AlertType.ERROR);
        }
    }
}
