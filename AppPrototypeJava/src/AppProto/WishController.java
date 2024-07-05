package AppProto;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class WishController implements Initializable {

    @FXML private Hyperlink linkHome;
    @FXML private AnchorPane wishKosong;
    @FXML private AnchorPane wishIsi;
    @FXML private ImageView fotoWisata, fotoWisata2, fotoWisata3, fotoWisata4, fotoWisata5, fotoWisata6;
    @FXML private Text judulWisata, judulWisata2, judulWisata3, judulWisata4, judulWisata5, judulWisata6;
    @FXML private Text deskWisata, deskWisata2, deskWisata3, deskWisata4, deskWisata5, deskWisata6;

    private OpenScene openScene = new OpenScene();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateWishlist();
    }

    public void updateWishlist() {
        List<WishlistItem> items = PageController.getWishlistItems();
        
        if (items.isEmpty()) {
            wishKosong.setVisible(true);
            wishIsi.setVisible(false);
        } else {
            wishKosong.setVisible(false);
            wishIsi.setVisible(true);
            populateWishlist(items);
        }
    }

    private void populateWishlist(List<WishlistItem> items) {
        ImageView[] imageViews = {fotoWisata, fotoWisata2, fotoWisata3, fotoWisata4, fotoWisata5, fotoWisata6};
        Text[] titles = {judulWisata, judulWisata2, judulWisata3, judulWisata4, judulWisata5, judulWisata6};
        Text[] descriptions = {deskWisata, deskWisata2, deskWisata3, deskWisata4, deskWisata5, deskWisata6};

        for (int i = 0; i < imageViews.length; i++) {
            if (i < items.size()) {
                WishlistItem item = items.get(i);
                try {
                    String imageUrl = getClass().getResource(item.getImagePath()).toExternalForm();
                    imageViews[i].setImage(new Image(imageUrl));
                } catch (NullPointerException | IllegalArgumentException e) {
                    System.err.println("Error loading image: " + item.getImagePath());
                    e.printStackTrace();
                    imageViews[i].setImage(new Image("ImageSRC/default.jpg"));
                }
                titles[i].setText(item.getTitle());
                descriptions[i].setText(item.getDescription());
                imageViews[i].setVisible(true);
                titles[i].setVisible(true);
                descriptions[i].setVisible(true);
            } else {
                imageViews[i].setVisible(false);
                titles[i].setVisible(false);
                descriptions[i].setVisible(false);
            }
        }
    }

    @FXML
    public void Home() {
        openScene.openScene("Homepage2", linkHome);
    }
}