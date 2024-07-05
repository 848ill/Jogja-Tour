package AppProto;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;

public class HelpCenterController implements Initializable{

    @FXML private Hyperlink linkHome;

    
    OpenScene openScene = new OpenScene();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
    }

    
    // Untuk Balik Ke Homepage
    @FXML
    public void Home(ActionEvent event) {
        if (linkHome != null) {
            openScene.openScene("Homepage2", linkHome);
        }
    }
}
