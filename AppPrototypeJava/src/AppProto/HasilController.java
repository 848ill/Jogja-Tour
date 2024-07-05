package AppProto;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import javafx.scene.control.Hyperlink;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.text.Text;

public class HasilController implements Initializable {


    @FXML private ImageView imageView;
    @FXML private Text judulText;
    @FXML private Text deskripsiText;
    @FXML private Text lokasiText;
    @FXML private Text bukaText;
    
    @FXML
    private Hyperlink linkHome;

    

    // Import Bagian Openscene 
    private OpenScene openScene = new OpenScene();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }



    // Untuk Balik Ke Homepage
    @FXML
    public void Home(ActionEvent event) {
        if (linkHome != null) {
            openScene.openScene("Homepage2", linkHome);
        }
    }

    // Untuk Mengatur Template Scene
    public void setResult(Destinasi destinasi) {
        judulText.setText(destinasi.getNamaDestinasi());
        deskripsiText.setText(destinasi.getDeskripsi());
        lokasiText.setText("Lokasi: " + destinasi.getLokasi());
        bukaText.setText("Buka: " + destinasi.getWaktuBuka());
        
        Image image = new Image(getClass().getResourceAsStream(destinasi.getImageUrl()));
        imageView.setImage(image);
    }



}
