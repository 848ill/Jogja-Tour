package AppProto;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

// import untuk menangani format xml
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class ControllerJava implements Initializable {
    
    @FXML
    private BorderPane loginForm;
    
    @FXML
    private TextField inputEmail;

    @FXML
    private PasswordField inputPassword;

    @FXML
    private Button loginButton;

    @FXML
    private Hyperlink buttonSignUp;
    
    @FXML
    private Hyperlink buttonForgot;

    // Import Bagian Openscene
    private OpenScene openScene = new OpenScene();
  

    // Bagian Login 
    @FXML
    public void adminLogi(ActionEvent event) {
        try {
            Alert alert;

            if (inputEmail.getText().isEmpty() || inputPassword.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Please enter your E-mail and Password");
                alert.showAndWait();
            } else {
                XStream xstream = new XStream(new StaxDriver());
                xstream.allowTypes(new Class[] { RegUser.class });

                RegUser loadedUser = (RegUser)  xstream.fromXML(new java.io.File("users.xml"));

                if (inputEmail.getText().equals(loadedUser.getEmail()) && inputPassword.getText().equals(loadedUser.getPassword())) {
                    // jika data user ditemukan di datUser
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Massage");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login!");
                    alert.showAndWait();

                    openScene.openScene("Homepage2", loginButton);
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Massage");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong E-mail or Password");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Untuk pindah ke register scene
    @FXML
    public void register(ActionEvent event) {
        openScene.openScene("RegisterUser", buttonSignUp);
    }

    // Untuk keluar dari App
    @FXML   
    private void exit (ActionEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
