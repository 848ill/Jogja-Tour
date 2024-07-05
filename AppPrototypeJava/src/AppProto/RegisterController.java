package AppProto;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class RegisterController implements Initializable{

    @FXML
    private TextField inputNama;

    @FXML
    private TextField inputEmaill;

    @FXML
    private DatePicker inputDate;

    @FXML
    private PasswordField inputPasswordd;

    @FXML
    private Button buttonCreateAccount;
 
    // Import Bagian Openscene 
    private OpenScene openScene = new OpenScene();

    // Bagian Register
    @FXML
    private void createAccount(ActionEvent event) {
        // Membuat variabel
        String name = inputNama.getText();
        String email = inputEmaill.getText();
        LocalDate birthDate = inputDate.getValue();
        String password = inputPasswordd.getText();

        try {
            // Validasi agar semua data terisi 
            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || birthDate == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Massage");
                alert.setHeaderText(null);
                alert.setContentText("All fields must be filled in");
                alert.showAndWait();
            } else {
                RegUser newUser = new RegUser(name, birthDate, email, password);

                XStream xstream = new XStream(new StaxDriver());
                xstream.allowTypes(new Class[] {RegUser.class});

                String xml = xstream.toXML(newUser);

                try {
                    // Menyimpan data ke file XML
                    String filePath = "users.xml";
                    FileOutputStream output = new FileOutputStream(filePath);
                    output.write(xml.getBytes());   
                    output.close();

                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Success Created Account");
                    alert.setHeaderText(null);
                    alert.showAndWait();

                    openScene.openScene("Login", buttonCreateAccount);
                }catch (IOException e){
                    System.out.println("Failed to save the file");
                    System.exit(0);
                }
            } 
                
        } catch (Exception e){
            e.printStackTrace();
        }

    }
    


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
}
