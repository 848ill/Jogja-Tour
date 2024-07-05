package AppProto;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.fxml.FXML;

import javafx.geometry.Pos;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class WisataReligiController {
    @FXML
    private ComboBox<String> locationComboBox;


    @FXML
    private VBox placeList;

    private List<TourismPlace> allPlaces;

    @FXML private Hyperlink linkHome;

    OpenScene openScene =  new OpenScene();

    @FXML
    public void initialize() {
        initializeComboBoxes();
        allPlaces = getAllTourismPlaces();
    }

    private void initializeComboBoxes() {
        locationComboBox.getItems().addAll(
            "All Locations",
            "Kabupaten Gunung Kidul",
            "Kabupaten Sleman",
            "Kabupaten Bantul",
            "Kabupaten Kulon Progo",
            "Kabupaten Magelang",
            "Kota Yogyakarta"
        );
        locationComboBox.setValue("All Locations");

        locationComboBox.setOnAction(event -> onLocationChanged());
    }


    @FXML
    public void onExploreButtonClicked() {
        String selectedLocation = locationComboBox.getValue();

        filterAndDisplayPlaces(selectedLocation);
    }

    private void filterAndDisplayPlaces(String selectedLocation) {
        placeList.getChildren().clear();

        List<TourismPlace> filteredPlaces = allPlaces.stream()
                .filter(place -> (selectedLocation.equals("All Locations") || place.getLocation().equals(selectedLocation)))
                .collect(Collectors.toList());

        if (filteredPlaces.isEmpty()) {
            showNoResultsMessage();
        } else {
            filteredPlaces.forEach(place -> placeList.getChildren().add(createPlaceBox(place)));
        }
    }

    private void showNoResultsMessage() {
        Label noResultsLabel = new Label("Tidak ada hasil yang ditemukan untuk pencarian ini.");
        noResultsLabel.getStyleClass().add("no-results-message");
        placeList.getChildren().add(noResultsLabel);
    }

    private HBox createPlaceBox(TourismPlace place) {
        HBox placeBox = new HBox(10);
        placeBox.getStyleClass().add("bgColorTempatWisata");

        ImageView imageView = new ImageView(new Image(place.getImageUrl()));
        imageView.setFitHeight(150);
        imageView.setFitWidth(200);

        VBox detailsBox = new VBox(5);
        detailsBox.getStyleClass().add("MainColor");

        Label titleLabel = new Label(place.getName());
        titleLabel.getStyleClass().add("JudulTempatWisata");

        Label locationLabel = new Label(place.getLocation());
        locationLabel.getStyleClass().add("textAbu");

        Label descriptionLabel = new Label(place.getDescription());
        descriptionLabel.getStyleClass().add("DescriptionText");
        descriptionLabel.setWrapText(true);

        Label openTimeLabel = new Label("Buka: " + place.getOpenTime());
        openTimeLabel.getStyleClass().add("textAbu");

        Label ratingLabel = new Label("Rating: " + place.getRating());
        ratingLabel.setStyle("-fx-font-style: italic;");

        Button detailButton = new Button("View Details");
        detailButton.getStyleClass().add("LihatDetailText");
        detailButton.setOnAction(e -> showDetailView(place));
       
        // Membuat HBox untuk tombol-tombol
        HBox buttonBox = new HBox(10); // Spacing 10 antara tombol
        buttonBox.setAlignment(Pos.CENTER_LEFT);
    
        buttonBox.getChildren().addAll(detailButton);

        detailsBox.getChildren().addAll(titleLabel, locationLabel, descriptionLabel, openTimeLabel, ratingLabel, buttonBox);

        placeBox.getChildren().addAll(imageView,detailsBox);

        return placeBox;
    }

    private List<TourismPlace> getAllTourismPlaces() {
        List<TourismPlace> places = new ArrayList<>();
        // Tambahkan tempat wisata di sini. Contoh:
        places.add(new TourismPlace("Candi Borobudur", "Kabupaten Magelang", "Candi Buddha terbesar di dunia yang merupakan situs warisan dunia UNESCO.", "06:00 - 17:00", "4.7/5", "Candi", "AppProto/ImageSRC/borobudur.jpeg"));
        places.add(new TourismPlace("Candi Prambanan", "Kabupaten Sleman", "Kompleks candi Hindu terbesar di Indonesia.", "06:00 - 17:00", "4.6/5", "Candi", "AppProto/ImageSRC/prambanan1.jpg"));
        places.add(new TourismPlace("Pura Vaikuntha Vyomantara", "Kabupaten Sleman", "Pura Hindu dengan arsitektur unik di lereng Merapi.", "06:00 - 18:00", "4.5/5", "Pura", "AppProto/ImageSRC/pura_vaikuntha_vyomantara.jpeg"));
        places.add(new TourismPlace("Candi Ratu Boko", "Kabupaten Sleman", "Situs arkeologi bersejarah dengan pemandangan spektakuler Candi Prambanan.", "06:00 - 17:30", "4.5/5", "Candi", "AppProto/ImageSRC/ratu_boko.jpeg"));
        places.add(new TourismPlace("Candi Sambisari", "Kabupaten Sleman", "Candi Hindu kuno yang tersembunyi di bawah permukaan tanah selama berabad-abad.", "08:00 - 17:00", "4.4/5", "Candi", "AppProto/ImageSRC/sambisari.jpeg"));
        places.add(new TourismPlace("Candi Kalasan", "Kabupaten Sleman", "Candi Buddha tertua di Yogyakarta dengan arsitektur yang indah.", "06:00 - 17:00", "4.3/5", "Candi", "AppProto/ImageSRC/kalasan.jpeg"));
        places.add(new TourismPlace("Masjid Gede Kauman", "Kota Yogyakarta", "Masjid tertua dan terbesar di Yogyakarta dengan arsitektur Jawa yang khas.", "04:00 - 21:00", "4.6/5", "Masjid", "AppProto/ImageSRC/masjid_gede_kauman.jpeg"));
        places.add(new TourismPlace("Gereja Ganjuran", "Kabupaten Bantul", "Gereja unik arsitektur Jawa yang menggabungkan budaya lokal dan Katolik.", "06:00 - 18:00", "4.5/5", "Gereja", "AppProto/ImageSRC/gereja_ganjuran.jpg"));
        places.add(new TourismPlace("Sendang Sono", "Kabupaten Kulon Progo", "Tempat ziarah Katolik yang tenang dengan mata air suci dan lingkungan asri..", "06:00 - 18:00", "4.5/5", "Tempat Ziarah", "AppProto/ImageSRC/sendang_sono.jpeg" ));
        places.add(new TourismPlace("Gua Maria Tritis", "Kabupaten Gunung Kidul", "Tempat ziarah Katolik di dalam gua alami dengan pemandangan alam yang indah.", "06:00 - 18:00", "4.6/5", "Tempat Ziarah", "AppProto/ImageSRC/gua_maria_tritis.jpeg"));
        return places;
    }

    // Untuk Menunjukan Detail Tempat Wisata
    private void showDetailView(TourismPlace place) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Detail " + place.getName());
        alert.setHeaderText(null);
        StringBuilder content = new StringBuilder();
        content.append("Nama: ").append(place.getName()).append("\n");
        content.append("Lokasi: ").append(place.getLocation()).append("\n");
        content.append("Deskripsi: ").append(place.getDescription()).append("\n");
        content.append("Jam Buka: ").append(place.getOpenTime()).append("\n");
        content.append("Rating: ").append(place.getRating()).append("\n");
        content.append("Tipe Objek: ").append(place.getObject());
        alert.setContentText(content.toString());
        alert.showAndWait();
    }

    

    @FXML
    public void Home() {
        openScene.openScene("Homepage2", linkHome);
    }

    @FXML
    public void onLocationChanged() {
        onExploreButtonClicked();
    }

    @FXML
    public void onObjectChanged() {
        onExploreButtonClicked();
    }

    @FXML
    public void resetFilters() {
        locationComboBox.setValue("All Locations");;
        onExploreButtonClicked();
    }

    

    
}

