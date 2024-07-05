package AppProto;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;

public class NotificationController  implements Initializable{

    @FXML private AnchorPane notifKosong;
    @FXML private AnchorPane notifIsi;
    @FXML private Button seeDetail;
    @FXML private Hyperlink linkHome;

    OpenScene openScene = new OpenScene();

    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (PaymentController.paymentMade) {
            notifKosong.setVisible(false);
            notifIsi.setVisible(true);
        } else {
            notifKosong.setVisible(true);
            notifIsi.setVisible(false);
        }

        seeDetail.setOnAction(this::showPaymentDetails);
    }

    // Menampilkan Informasi Payment tadi
    private void showPaymentDetails(ActionEvent event) {
        try {
            File xmlFile = new File("paymentUser.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            String email = getElementValue(doc, "email");
            String firstName = getElementValue(doc, "firstName");
            String lastName = getElementValue(doc, "lastName");
            String telephone = getElementValue(doc, "telephone");
            String dateOfBirth = getElementValue(doc, "dateOfBirth");
            String cardName = getElementValue(doc, "cardName");
            String bookedService = getElementValue(doc, "bookedService");

            String message = String.format("Email: %s\nFirst Name: %s\nLast Name: %s\nTelephone: %s\n" +
                    "Date of Birth: %s\nCard Name: %s\nBooked Service: %s",
                    email, firstName, lastName, telephone, dateOfBirth, cardName, bookedService);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Payment Information");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  Method Agar getElement Bekerja
    private String getElementValue(Document doc, String elementName) {
        return doc.getElementsByTagName(elementName).item(0).getTextContent();
    }


    // Untuk Balik Ke Homepage
    @FXML
    public void Home(ActionEvent event) {
        if (linkHome != null) {
            openScene.openScene("Homepage2", linkHome);
        }
    }
}
