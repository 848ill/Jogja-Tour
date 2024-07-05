package AppProto;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;

public class ProfileAgenController implements Initializable {

    // Inisialisasi Combobox
    @FXML private ComboBox<String> servicesComboBox;

    // Inisialisasi Button
    @FXML private Button bookingNowButton;

    @FXML Hyperlink linkHome;

    // Import OpenScene
    OpenScene openScene = new OpenScene();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Inisialisasi ComboBox
        servicesComboBox.getItems().addAll("Tour Guide", "Car Rental", "Hotels");
    }

    @FXML
    private void handleBookingNow(ActionEvent event) {
        String selectedService = servicesComboBox.getValue();
        if (selectedService != null) {
            openScene.openScene("Payment",bookingNowButton, selectedService);;
        }
    }

    // Untuk Balik Ke Homepage
    @FXML
    public void Home(ActionEvent event) {
        if (linkHome != null) {
            openScene.openScene("Homepage2", linkHome);
        }
    }
}
