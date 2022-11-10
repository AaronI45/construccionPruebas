/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package appcontrolescolar;

import appcontrolescolar.modelo.ConexionBD;
import appcontrolescolar.util.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class FXMLInicioSesionController implements Initializable {
    
    @FXML
    private TextField txtNoPersonal;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label lbAlertaPassword;
    @FXML
    private Label lbAlertaNoPersonal;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void clicBttnIniciarSesion(ActionEvent event) {
        String noPersonal = txtNoPersonal.getText();
        String password = txtPassword.getText();
        boolean valido = true;
        
        if(noPersonal.isEmpty()){
            valido = false;
            lbAlertaNoPersonal.setText("Este campo es requerido.");
        }
        if(password.isEmpty()){
            valido = false;
            lbAlertaPassword.setText("Este campo es requerido.");
        }
        if(valido)
            verificarCredencialesUsuario(noPersonal, password);
    }
    
    private void verificarCredencialesUsuario(String noPersonal, String password){
        //temp
        Connection conexionPrueba = ConexionBD.abrirConexionBD();
        if(conexionPrueba != null){
            Utilidades.mostrarAlestaSimple("Conexion correcta", "conexion Lista", 
                    Alert.AlertType.INFORMATION);
            irPantallaPrincipal();
        }
    }
    
    private void irPantallaPrincipal(){
        try{
            //Cualquier que no sea un .class es un recurso
            //Si se quiere mantener la navegacion se debe mantener el mismo escenario, pero pasando la escena a uno nuevo y despues regresando la escena al mismo escenario
            //Si no se quiere mantener la navegacion se debe crear otro escenario
            Utilidades.mostrarAlestaSimple("Bienvenido", "Credenciales correctas, bienvenido al sistema", 
                Alert.AlertType.INFORMATION);
            Parent vista = FXMLLoader.load(getClass().getResource("FXMLPrincipal.fxml"));
            Scene escenaPrincipal = new Scene(vista);
            Stage escenarioBase = (Stage) txtNoPersonal.getScene().getWindow();//Casteo es una conversion
            escenarioBase.setScene(escenaPrincipal);
            escenarioBase.show();
        }catch(IOException excepcion){
            excepcion.printStackTrace();
            Utilidades.mostrarAlestaSimple("Error", "No se puede mostrar la pantalla principal", 
                    Alert.AlertType.ERROR);
        }
    }
    
    /*
    **Con escemarios distintos
    */
    public void irPantallaPrincipal2(){
        try{
            //Cualquier que no sea un .class es un recurso
            //Si se quiere mantener la navegacion se debe mantener el mismo escenario, pero pasando la escena a uno nuevo y despues regresando la escena al mismo escenario
            //Si no se quiere mantener la navegacion se debe crear otro escenario

            Parent vista = FXMLLoader.load(getClass().getResource("FXMLPrincipal.fxml"));
            Scene escenaPrincipal = new Scene(vista);
            //Stage escenarioBase = (Stage) txtNoPersonal.getScene().getWindow();//Casteo es una conversion
            Stage escenarioBase = new Stage();
            escenarioBase.setScene(escenaPrincipal);
            escenarioBase.show();
        }catch(IOException excepcion){
            excepcion.printStackTrace();
        }
    }
    
}
