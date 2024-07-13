package AppProto;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import javafx.application.Platform;

public class TicketPaymentController implements Initializable {

    @FXML private TextField masukEmail;
    @FXML private TextField masukNoTelepon;
    @FXML private TextField masukNamaPertama;
    @FXML private TextField masukNamaTerakhir;
    @FXML private TextField masukTanggalBooking;
    @FXML private ChoiceBox<String> paymentMethodChoiceBox;
    @FXML private TextField jumlahTiket;
    @FXML private TextField masukNomorPhone;
    @FXML private Hyperlink linkBack;
    @FXML private Button payButton;

    private OpenScene openScene = new OpenScene();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        payButton.setOnAction(this::handlePayment);
        paymentMethodChoiceBox.getItems().addAll("ShopeePay", "Gopay");
    }

    public void setBookedService(String service) {
        masukTanggalBooking.setText(service);
    }

    @FXML
    public void Back(ActionEvent event) {
        if (linkBack != null) {
            openScene.openScene("Homepage2", linkBack);
        }
    }

    public static boolean paymentMade = false;

    private void handlePayment(ActionEvent event) {
        if (areAllFieldsFilled()) {
            showLoadingAlert();
            PauseTransition pause = new PauseTransition(Duration.seconds(5));
            pause.setOnFinished(e -> {
                Platform.runLater(() -> {
                    saveDataToXML();
                    paymentMade = true;
                    showSuccessAlert();
                });
            });
            pause.play();
        } else {
            showAlert("Please Fill All The Requirements");
        }
    }

    private boolean areAllFieldsFilled() {
        return !masukEmail.getText().isEmpty() &&
               !masukNoTelepon.getText().isEmpty() &&
               !masukNamaPertama.getText().isEmpty() &&
               !masukNamaTerakhir.getText().isEmpty() &&
               !masukTanggalBooking.getText().isEmpty() &&
               paymentMethodChoiceBox.getValue() != null &&
               !masukNomorPhone.getText().isEmpty();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showLoadingAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Processing");
        alert.setHeaderText(null);
        alert.setContentText("Please wait");
        alert.show();
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(e -> alert.close());
        pause.play();
    }

    private void showSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Your Payment Succeeded");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                openScene.openScene("Homepage2", payButton);
            }
        });
    }

    private void saveDataToXML() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("ticket_payment");
            doc.appendChild(rootElement);

            addElement(doc, rootElement, "email", masukEmail.getText());
            addElement(doc, rootElement, "telephone", masukNoTelepon.getText());
            addElement(doc, rootElement, "firstName", masukNamaPertama.getText());
            addElement(doc, rootElement, "lastName", masukNamaTerakhir.getText());
            addElement(doc, rootElement, "bookingDate", masukTanggalBooking.getText());
            addElement(doc, rootElement, "paymentMethod", paymentMethodChoiceBox.getValue());
            addElement(doc, rootElement, "numberOfTickets", jumlahTiket.getText());
            addElement(doc, rootElement, "phoneNumber", masukNomorPhone.getText());

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("ticketPaymentUser.xml"));
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addElement(Document doc, Element parent, String name, String value) {
        Element element = doc.createElement(name);
        element.appendChild(doc.createTextNode(value));
        parent.appendChild(element);
    }
}