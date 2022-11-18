
package appcontrolescolar.alumno;

import appcontrolescolar.modelo.dao.FacultadDAO;
import appcontrolescolar.modelo.pojo.Carrera;
import appcontrolescolar.modelo.pojo.Facultad;
import appcontrolescolar.util.Utilidades;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

public class FXMLFormularioAlumnoController implements Initializable {

    @FXML
    private Label lbInicial;
    @FXML
    private TextField tfMatricula;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApellidoPaterno;
    @FXML
    private TextField tfApellidoMaterno;
    @FXML
    private TextField tfCorreo;
    @FXML
    private TextField tfFechaNacimiento;
    @FXML
    private ComboBox<Facultad> cbFacultad;
    @FXML
    private ComboBox<Carrera> cbCarrera;
    @FXML
    private ImageView imgFoto;

    private File archivoFoto;

    private ObservableList<Facultad> listaFacultades;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarListaFacultades();
    }    

    private void cargarListaFacultades(){
        listaFacultades = FXCollections.observableArrayList();
        try {
            ArrayList<Facultad> facultadesBD = FacultadDAO.recuperarFacultades();
            listaFacultades.addAll(facultadesBD);
            cbFacultad.setItems(listaFacultades);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    @FXML
    private void clicSeleccionFoto(ActionEvent event) {
        FileChooser seleccion = new FileChooser();
        seleccion.setTitle("Selecciona la imagen del alumno");
        FileChooser.ExtensionFilter filtro = 
                new FileChooser.ExtensionFilter("archivos PNG, (+.png), (+.JPG), "
                    + "(+.JPNG)", "*.PNG", "*.JPG", "*.JPNG");
        seleccion.getExtensionFilters().add(filtro);
        Stage escenarioBase = (Stage)imgFoto.getScene().getWindow();
        archivoFoto = seleccion.showOpenDialog(escenarioBase);
        if (archivoFoto != null){
            try {
                BufferedImage bffImagen = ImageIO.read(archivoFoto);
                Image imgFile = SwingFXUtils.toFXImage(bffImagen, null);
                imgFoto.setImage(imgFile);
            } catch (IOException e) {
                Utilidades.mostrarAlestaSimple("Error en la selección", 
                        "Seleccione otra imagen por favor", Alert.AlertType.ERROR);
            }
        }else{
            
        }
                
    }

    @FXML
    private void clicGuardar(ActionEvent event) {
    }

    @FXML
    private void clicCancelar(ActionEvent event) {
    }
    
}
