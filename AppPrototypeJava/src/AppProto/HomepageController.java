package AppProto;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class HomepageController implements Initializable {
   
    // Import Searchbar
    @FXML
    private TextField searchButton;

    @FXML
    private ContextMenu searchSuggest;

    // Import Button
    @FXML
    private Button catAlam;
    @FXML
    private Button catBudaya;
    @FXML
    private Button catReligi;
    @FXML private Button news;


    // Wisata
    @FXML private Button moreMalioboro;
    @FXML private Button moreTugu;
    @FXML private Button moreBukbin;
    @FXML private Button moreKlangon;
    @FXML private Button moreHeha;
    @FXML private Button moreObelix;
    @FXML private Button morePrambanan;

    // Import Hyperlink
    @FXML
    private Hyperlink linkProfile;
    @FXML
    private Hyperlink linkWishlist;
    @FXML
    private Hyperlink linkContactUs;
    @FXML
    private Hyperlink linkHelpCenter;
    @FXML
    private Hyperlink linkNotif;


    // Tambahkan elemen baru untuk foto profil dan nama pengguna
    @FXML private ImageView profileImage;
    @FXML private Text userName;

    // Import Bagian Openscene 
    private OpenScene openScene = new OpenScene();

    // Membuat array Destinasi
    private List<Destinasi> listDestinasi = new ArrayList<>();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadDestinasi();
        loadUserProfile();
    }

    private void loadDestinasi() {
        listDestinasi.add(new Destinasi(
            "Jalan Malioboro",
            "Sosromenduran, Gedong Tengen, Kota Yogyakarta, Daerah Istimewa Yogyakarta",
            "/AppProto/ImageSRC/Malioboro1.jpg",
            "Malioboro merupakan nama salah satu jalan di pusat Kota Yogyakarta. Jalan Malioboro itu sendiri merupakan salah satu jalan dari tiga jalan di Kota Yogyakarta yang membentang dari Tugu Yogyakarta hingga ke perempatan Kantor Pos Yogyakarta. Secara keseluruhan terdiri dari Jalan Pangeran Mangkubumi, Jalan Malioboro, dan Jalan Jend. A. Yani. Jalan Malioboro merupakan poros Garis Imajiner Kraton Yogyakarta.",
            "24 Jam"
        ));
        listDestinasi.add(new Destinasi(
            "Pantai Parangtritis",
            "Parangtritis, Kretek, Bantul Regency, Special Region of Yogyakarta",
            "/AppProto/ImageSRC/Parangtritis.jpg",
            "Pantai Parangtritis adalah tempat wisata yang terletak di Desa Parangtritis, Kecamatan Kretek, Kabupaten Bantul, Daerah Istimewa Yogyakarta. Jaraknya kurang lebih 27 km dari pusat Kota Yogyakarta. Pantai ini terkenal dengan pemandangan matahari terbenamnya yang indah dan aktivitas wisata seperti naik delman atau motor ATV di sepanjang pantai.",
            "06.00 - 17.00 WIB"
        ));
        listDestinasi.add(new Destinasi(
            "Puncak Mangunan",
            "Jl. Imogiri - Dlingo, Bantul",
            "/AppProto/ImageSRC/Mangunan1.jpg",
            "Kebun Buah Mangunan di Bantul, Yogyakarta, menawarkan pesona alam yang memukau dengan kebun-kebun buah yang teratur di lereng perbukitan. Pengunjung dapat menikmati panorama indah perbukitan dan sawah hijau, serta menikmati suasana sejuk dari hutan pinus di sekitarnya. Tempat ini juga terkenal dengan spot-spot foto yang menarik dan menyediakan berbagai buah segar langsung dari kebunnya.",
            "05.00 - 18.00 WIB"
        ));
    }

    // Untuk Profile dan Username user
    private void loadUserProfile() {
        try {
            XStream xstream = new XStream(new StaxDriver());
            xstream.allowTypes(new Class[]{RegUser.class});
            FileInputStream fis = new FileInputStream("users.xml");
            RegUser currentUser = (RegUser) xstream.fromXML(fis);
            fis.close();

            // Set nama pengguna
            userName.setText(currentUser.getNama());

            // Load foto profil
            File profilePic = new File("profile_pic.jpg");
            if (profilePic.exists()) {
                Image image = new Image(new FileInputStream(profilePic));
                profileImage.setImage(image);
            } else {
                // Gunakan gambar default jika tidak ada foto profil
                profileImage.setImage(new Image(getClass().getResourceAsStream("/AppProto/ImageSRC/ProfileTemplate.png")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Tambahkan method baru untuk memperbarui profil setelah kembali dari halaman profil
    public void refreshProfile() {
        loadUserProfile();
    }

    @FXML
    private void handleSearchAction(ActionEvent event) {
        String searchTerm = searchButton.getText().trim().toLowerCase();
        Destinasi result = listDestinasi.stream()
                .filter(d -> d.getNamaDestinasi().toLowerCase().contains(searchTerm))
                .findFirst()
                .orElse(null);

        if (result != null) {
            showSearchResult(result);
        }
    }

    

    private void showSearchResult(Destinasi result) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Hasil.fxml"));
            Parent root = loader.load();

            HasilController hasilController = loader.getController();
            hasilController.setResult(result);

            Stage stage = (Stage) searchButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    


    // Untuk Pergi Ke Profile
    @FXML
    public void Profile(ActionEvent event) {
        if (linkProfile != null) {
            openScene.openScene("Profile", linkProfile);
        }
    }

    // Untuk Pergi Ke Wishlist
    @FXML
    public void Wishlist(ActionEvent event) {
        if (linkWishlist != null) {
            openScene.openScene("Wishlist", linkWishlist);
        }
    }

    // Untuk Pergi Ke Help Center
    @FXML
    public void HelpCenter(ActionEvent event) {
        if (linkHelpCenter != null) {
            openScene.openScene("Helpcenter", linkHelpCenter);
        }
    }

    // Untuk Pergi Ke Contact Us
    @FXML
    public void ContactUs(ActionEvent event) {
        if (linkContactUs != null) {
            openScene.openScene("ContactUs", linkContactUs);
        }
    }

    // Untuk Pergi Ke News
    @FXML
    public void News(ActionEvent event) {
        if (news != null) {
            openScene.openScene("News", news);
        }
    }

    // Untuk Pergi Ke Notification
    @FXML
    public void Notification(ActionEvent event) {
        if (linkNotif != null) {
            openScene.openScene("Notification", linkNotif);
        }
    }

    // Untuk Pergi Ke Malioboro
    @FXML
    public void pageMalioboro(ActionEvent event) {
        if (moreMalioboro != null) {
            openScene.openScene("PageMalio", moreMalioboro);
        }
    }
    // Untuk Pergi Ke Tugu
    @FXML
    public void pageTugu(ActionEvent event) {
        if (moreTugu != null) {
            openScene.openScene("PageTugu", moreTugu);
        }
    }
    // Untuk Pergi Ke Bukit bintang
    @FXML
    public void pageBukitBintang(ActionEvent event) {
        if (moreBukbin!= null) {
            openScene.openScene("PageBukbin", moreBukbin);
        }
    }
    // Untuk Pergi Ke Klangon
    @FXML
    public void pageBukitKlangon(ActionEvent event) {
        if (moreKlangon != null) {
            openScene.openScene("PageBukitkKlangon", moreKlangon);
        }
    }
    // Untuk Pergi Ke Heha
    @FXML
    public void pageHeha(ActionEvent event) {
        if (moreHeha != null) {
            openScene.openScene("PageHeha", moreHeha);
        }
    }
    // Untuk Pergi Ke Obelix
    @FXML
    public void pageObelix(ActionEvent event) {
        if (moreObelix != null) {
            openScene.openScene("PageObelix", moreObelix);
        }
    }
    // Untuk Pergi Ke Prambanan
    @FXML
    public void pagePrambanan(ActionEvent event) {
        if (morePrambanan != null) {
            openScene.openScene("PagePrambanan", morePrambanan);
        }
    }

    // Untuk Pergi Ke Category Religi
    @FXML
    public void CategoryReligi(ActionEvent event) {
        if (catReligi != null) {
            openScene.openScene("WisataReligi", catReligi);
        }
    }

    // Untuk Pergi Ke Category Alam
    @FXML
    public void CategoryAlam(ActionEvent event) {
        if (catAlam != null) {
            openScene.openScene("WisataAlam", catAlam);
        }
    }

    // Untuk Pergi Ke Category Budaya
    @FXML
    public void CategoryBudaya(ActionEvent event) {
        if (catBudaya != null) {
            openScene.openScene("WisataBudaya", catBudaya);
        }
    }


    // Untuk keluar dari App
    @FXML   
    private void exit (ActionEvent event) {
        System.exit(0);
    }

}
