/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package appcontrolescolar.alumno;

import appcontrolescolar.util.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.stage.Modality;
import javafx.stage.Stage;


public class FXMLAdminAlumnosController implements Initializable {

    @FXML
    private TextField tfBuscar;
    @FXML
    private TableView<?> tvAlumnos;
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


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    

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
