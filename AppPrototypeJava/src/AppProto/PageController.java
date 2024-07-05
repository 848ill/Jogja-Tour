package AppProto;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;

public class PageController implements Initializable {

    // Fitur Love
    @FXML private Button loveUnfill;
    @FXML private Button loveFill;
    @FXML private Button tuguLoveUnfill;
    @FXML private Button tuguLoveFill;
    @FXML private Button bukbinLoveUnfill;
    @FXML private Button bukbinLoveFill;
    @FXML private Button klangonLoveUnfill;
    @FXML private Button klangonLoveFill;
    @FXML private Button hehaLoveUnfill;
    @FXML private Button hehaLoveFill;
    @FXML private Button obelixLoveUnfill;
    @FXML private Button obelixLoveFill;
    @FXML private Button prambananLoveUnfill;
    @FXML private Button prambananLoveFill;


    @FXML private Hyperlink linkPProfileAgen;
    @FXML private Hyperlink linkHome;

    private OpenScene openScene = new OpenScene();
    private static List<WishlistItem> wishlistItems = new ArrayList<>();
    private boolean isMalioboroInWishlist = false;
    private boolean isTuguInWishlist = false;
    private boolean isBukbinInWishlist = false;
    private boolean isKlangonInWishlist = false;
    private boolean isHehaInWishlist = false;
    private boolean isObelixInWishlist = false;
    private boolean isPrambananInWishlist = false;

    private WishController wishController;

    public void setWishController(WishController wishController) {
        this.wishController = wishController;
    }

    @FXML
    public void toggleMalioboroWishlist() {
        toggleWishlist("Malioboro");
    }

    @FXML
    public void toggleTuguWishlist() {
        toggleWishlist("Tugu");
    }

    @FXML
    public void toggleBukbinWishlist() {
        toggleWishlist("Bukit Bintang");
    }

    @FXML
    public void toggleKlangonWishlist() {
        toggleWishlist("Bukit Klangon");
    }

    @FXML
    public void toggleHehaWishlist() {
        toggleWishlist("Heha");
    }

    @FXML
    public void toggleObelixWishlist() {
        toggleWishlist("Obelix");
    }

    @FXML
    public void togglePrambananWishlist() {
        toggleWishlist("Prambanan");
    }

    private void toggleWishlist(String location) {
        if (location.equals("Malioboro")) {
            if (isMalioboroInWishlist) {
                removeFromWishlist("Malioboro");
            } else {
                addMalioboroToWishlist();
            }
            isMalioboroInWishlist = !isMalioboroInWishlist;
        } else if (location.equals("Tugu")) {
            if (isTuguInWishlist) {
                removeFromWishlist("Tugu");
            } else {
                addTuguToWishlist();
            }
            isTuguInWishlist = !isTuguInWishlist;
        } else if (location.equals("Bukit Bintang")) {
            if (isBukbinInWishlist) {
                removeFromWishlist("Bukit Bintang");
            } else {
                addBukbinToWishlist();
            }
            isBukbinInWishlist = !isBukbinInWishlist;
        } else if (location.equals("Bukit Klangon")) {
            if (isKlangonInWishlist) {
                removeFromWishlist("Bukit Klangon");
            } else {
                addKlangonToWishlist();
            }
            isKlangonInWishlist =!isKlangonInWishlist;
        } else if (location.equals("Heha")){
            if (isHehaInWishlist) {
                removeFromWishlist("Heha");
            } else {
                addHehaToWishlist();
            }
            isHehaInWishlist =!isHehaInWishlist;
        } else if (location.equals("Obelix")) {
            if (isObelixInWishlist) {
                removeFromWishlist("Obelix");
            } else {
                addObelixToWishlist();
            }
            isObelixInWishlist =!isObelixInWishlist;
        } else if (location.equals("Prambanan")) {
            if (isPrambananInWishlist) {
                removeFromWishlist("Prambanan");
            } else {
                addPrambananToWishlist();
            }
            isPrambananInWishlist =!isPrambananInWishlist;
        }
        updateWishlistButton(location);
    }

    private void addMalioboroToWishlist() {
        WishlistItem item = new WishlistItem(
            "ImageSRC/MalioKursi.jpg",
            "Jalan Malioboro",
            "Malioboro, salah satu tempat wisata yang tawarkan wisata belanja & budaya."
        );
        wishlistItems.add(item);
        updateWishlist();
    }

    private void addTuguToWishlist() {
        WishlistItem item = new WishlistItem(
            "ImageSRC/tugu1.jpg",
            "Tugu",
            "Tugu Yogyakarta, salah satu tempat wisata yang tawarkan wisata belanja & budaya."
        );
        wishlistItems.add(item);
        updateWishlist();
    }

    private void addBukbinToWishlist() {
        WishlistItem item = new WishlistItem(
            "ImageSRC/bukbin1.jpg",
            "Bukit Bintang",
            "Bukit Bintang, salah satu tempat wisata yang tawarkan wisata belanja & budaya."
        );
        wishlistItems.add(item);
        updateWishlist();
    }

    private void addKlangonToWishlist() {
        WishlistItem item = new WishlistItem(
            "ImageSRC/bukitKlangon1.jpg",
            "Bukit Klangon",
            "Bukit Klangon, salah satu tempat wisata yang tawarkan wisata belanja & budaya."
        );
        wishlistItems.add(item);
        updateWishlist();
    }

    private void addHehaToWishlist() {
        WishlistItem item = new WishlistItem(
            "ImageSRC/heha1.jpg",
            "Heha",
            "Heha, salah satu tempat wisata yang tawarkan wisata belanja & budaya."
        );
        wishlistItems.add(item);
        updateWishlist();
    }

    private void addObelixToWishlist() {
        WishlistItem item = new WishlistItem(
            "ImageSRC/obelix1.jpg",
            "Obelix",
            "Obelix, salah satu tempat wisata yang tawarkan wisata belanja & budaya."
        );
        wishlistItems.add(item);
        updateWishlist();
    }

    private void addPrambananToWishlist() {
        WishlistItem item = new WishlistItem(
            "ImageSRC/prambanan1.jpg",
            "Prambanan",
            "Prambanan, salah satu tempat wisata yang tawarkan wisata belanja & budaya."
        );
        wishlistItems.add(item);
        updateWishlist();
    }

    private void removeFromWishlist(String title) {
        wishlistItems.removeIf(item -> item.getTitle().equals(title));
        updateWishlist();
    }

    private void updateWishlistButton(String location) {
        if (location.equals("Malioboro")) {
            if (loveUnfill != null) loveUnfill.setVisible(!isMalioboroInWishlist);
            if (loveFill != null) loveFill.setVisible(isMalioboroInWishlist);
        } else if (location.equals("Tugu")) {
            if (tuguLoveUnfill != null) tuguLoveUnfill.setVisible(!isTuguInWishlist);
            if (tuguLoveFill != null) tuguLoveFill.setVisible(isTuguInWishlist);
        } else if (location.equals("Bukit Bintang")) {
            if (bukbinLoveUnfill != null) bukbinLoveUnfill.setVisible(!isBukbinInWishlist);
            if (bukbinLoveFill != null) bukbinLoveFill.setVisible(isBukbinInWishlist);
        } else if (location.equals("Bukit Klangon")) {
            if (klangonLoveUnfill != null) klangonLoveUnfill.setVisible(!isKlangonInWishlist);
            if (klangonLoveFill != null) klangonLoveFill.setVisible(isKlangonInWishlist);
        } else if (location.equals("Heha")) {
            if (hehaLoveUnfill != null) hehaLoveUnfill.setVisible(!isHehaInWishlist);
            if (hehaLoveFill != null) hehaLoveFill.setVisible(isHehaInWishlist);
        } else if (location.equals("Obelix")) {
            if (obelixLoveUnfill != null) obelixLoveUnfill.setVisible(!isObelixInWishlist);
            if (obelixLoveFill != null) obelixLoveFill.setVisible(isObelixInWishlist);
        } else if (location.equals("Prambanan")) {
            if (prambananLoveUnfill != null) prambananLoveUnfill.setVisible(!isPrambananInWishlist);
            if (prambananLoveFill != null) prambananLoveFill.setVisible(isPrambananInWishlist);
        }
    }

    private void updateWishlist() {
        if (wishController != null) {
            wishController.updateWishlist();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Hanya panggil updateWishlistButton jika tombol-tombol sudah diinisialisasi
        if (loveUnfill != null && loveFill != null) {
            updateWishlistButton("Malioboro");
        }
        if (tuguLoveUnfill != null && tuguLoveFill != null) {
            updateWishlistButton("Tugu");
        }
        if (bukbinLoveUnfill != null && bukbinLoveFill != null) {
            updateWishlistButton("Bukit Bintang");
        }
        if (klangonLoveUnfill != null && klangonLoveFill != null) {
            updateWishlistButton("Bukit Klangon");
        }
        if (hehaLoveUnfill != null && hehaLoveFill != null) {
            updateWishlistButton("Heha");
        }
        if (obelixLoveUnfill != null && obelixLoveFill != null) {
            updateWishlistButton("Obelix");
        }
        if (prambananLoveUnfill != null && prambananLoveFill != null) {
            updateWishlistButton("Prambanan");
        }
    }

    // Balik Ke Homepage
    @FXML
    public void Home(ActionEvent event) {
        if (linkHome != null) {
            openScene.openScene("Homepage2", linkHome);
        }
    }

    // Pergi Ke Profile Agen
    @FXML
    public void GotoProfileAgen(ActionEvent event) {
        if (linkPProfileAgen != null) {
            openScene.openScene("ProfileAgen", linkPProfileAgen);
        }
    }

    public static List<WishlistItem> getWishlistItems() {
        return wishlistItems;
    }
}