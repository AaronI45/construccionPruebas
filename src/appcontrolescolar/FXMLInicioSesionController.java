/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package appcontrolescolar;

import appcontrolescolar.modelo.dao.UsuarioDAO;
import appcontrolescolar.modelo.pojo.Usuario;
import appcontrolescolar.util.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
        try {
            Usuario usuarioSesion = UsuarioDAO.verificarUsuario(noPersonal, password);
            if(usuarioSesion.getIdUsuario() > 0)
                irPantallaPrincipal(usuarioSesion.toString());
            else
                Utilidades.mostrarAlestaSimple("Credenciales incorrectas", 
                        "El numero de personal y/o contrase??a es incorrecto, favor", 
                        Alert.AlertType.WARNING);
        } catch (SQLException | NullPointerException e) {
                Utilidades.mostrarAlestaSimple("Error de conexi??n", "Hubo un error "
                        + "en el proceso de comunicaci??n, intentelo m??s tarde", 
                        Alert.AlertType.ERROR);
        }
    }
    
    private void irPantallaPrincipal(String nombre){
        try{
            Utilidades.mostrarAlestaSimple("Bienvenido", "Credenciales correctas, bienvenido "+nombre+" al sistema", 
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
    
}
