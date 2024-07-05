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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

public class ContactUsController implements Initializable {

    // Inisialisasi Text Field
    @FXML private TextField fullName;
    @FXML private TextField email;
    @FXML private TextField message;

    // Inisialisasi Button
    @FXML private Button sendButton;

    // Import Hyperlink
    @FXML private Hyperlink linkHome;

    // Import OpenScene
    OpenScene openScene = new OpenScene();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sendButton.setOnAction(this::handleSendMessage);
    }

    // Untuk Balik Ke Homepage
    @FXML
    public void Home(ActionEvent event) {
        if (linkHome != null) {
            openScene.openScene("Homepage2", linkHome);
        }
    }

     private void handleSendMessage(ActionEvent event) {
        if (fullName.getText().isEmpty() || email.getText().isEmpty() || message.getText().isEmpty()) {
            showAlert("Please Fill All The Fields");
        } else {
            saveMessageToXML();
            showAlert("Message sent successfully");
            clearFields();
        }
    }

    private void saveMessageToXML() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // Create root element
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("messages");
            doc.appendChild(rootElement);

            // Create message element
            Element messageElement = doc.createElement("message");
            rootElement.appendChild(messageElement);

            // Add child elements
            addChildElement(doc, messageElement, "fullName", fullName.getText());
            addChildElement(doc, messageElement, "email", email.getText());
            addChildElement(doc, messageElement, "messageText", message.getText());

            // Write to XML file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("message.xml"));
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error saving message: " + e.getMessage());
        }
    }

    private void addChildElement(Document doc, Element parent, String elementName, String textContent) {
        Element element = doc.createElement(elementName);
        element.setTextContent(textContent);
        parent.appendChild(element);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        fullName.clear();
        email.clear();
        message.clear();
    }
}
