
package appcontrolescolar.util;

import javafx.scene.control.Alert;

public class Utilidades {
    
    public static void mostrarAlestaSimple(String titulo, String mensaje, Alert.AlertType tipo){
        Alert alertaSimple = new Alert(tipo);
        alertaSimple.setTitle(titulo);
        alertaSimple.setContentText(mensaje);
        alertaSimple.setHeaderText(null);
        alertaSimple.showAndWait();
    }
}
