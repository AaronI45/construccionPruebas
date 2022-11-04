/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package appcontrolescolar;

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
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author franz
 */
public class FXMLPrincipalController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicAdministradorAlumnos(ActionEvent event) {
            try {
            Parent vista = FXMLLoader.load(getClass().getResource("alumno/FXMLAdminAlumnos.fxml"));
            Scene escenaAdmin = new Scene (vista);
            Stage escenarioNuevo = new Stage();
            escenarioNuevo.setScene(escenaAdmin);
            escenarioNuevo.initModality(Modality.APPLICATION_MODAL);
            escenarioNuevo.showAndWait();
        } catch (IOException ex) {
            Utilidades.mostrarAlestaSimple("Error", "Error al cargar el administrador de alumnos", 
                        Alert.AlertType.ERROR);
        }
    }
    
}
