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

public class WisataBudayaController {
    @FXML
    private ComboBox<String> locationComboBox;

    @FXML
    private VBox placeList;
    private List<TourismPlace> allPlaces;

    @FXML
    private Button viewDetailButton;

    @FXML 
    private Hyperlink buyTicketButton;

    @FXML
    private Hyperlink linkHome;

    @FXML
    private Hyperlink linkBack;

    @FXML 
    private Hyperlink linkTicketPayment;

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
        HBox placeBox = new HBox(20);
        placeBox.getStyleClass().add("bgColorTempatWisata");
        placeBox.setPadding(new javafx.geometry.Insets(15));
        placeBox.setUserData(place);

        ImageView imageView = new ImageView(new Image(place.getImageUrl()));
        imageView.setFitHeight(150);
        imageView.setFitWidth(200);
    
        VBox detailsBox = new VBox(5);
        detailsBox.getStyleClass().add("MainColor");
        detailsBox.setAlignment(Pos.TOP_LEFT);
    
        Label titleLabel = new Label(place.getName());
        titleLabel.getStyleClass().add("JudulTempatWisata");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
    
        Label locationLabel = new Label(place.getLocation());
        locationLabel.getStyleClass().add("textAbu");
    
        Label descriptionLabel = new Label(place.getDescription());
        descriptionLabel.getStyleClass().add("DescriptionText");
        descriptionLabel.setWrapText(true);
        descriptionLabel.setStyle("-fx-font-size: 14px;");
    
        Label openTimeLabel = new Label("Open : " + place.getOpenTime());
        openTimeLabel.getStyleClass().add("textAbu");
    
        Label ratingLabel = new Label("Rating : " + place.getRating());
        ratingLabel.setStyle("-fx-font-style: italic;");
    
        VBox buttonBox = new VBox(10);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
    
        Hyperlink viewDetailButton = new Hyperlink("View Detail");
        viewDetailButton.getStyleClass().add("ViewDetailButton");;
        viewDetailButton.setOnAction(e -> showDetailView((TourismPlace) placeBox.getUserData()));
        viewDetailButton.setStyle("-fx-background-radius : 10;\r\n" + //
                        "    -fx-padding: 5 10 5 10;\r\n" + //
                        "    -fx-pref-width: 146px;\r\n" + //
                        "    -fx-pref-height: 4px;\r\n" + //
                        "    -fx-background-color:  #4CAF50 ;\r\n" + //
                        "    -fx-font-family : \"Arial\" ;\r\n" + //
                        "    -fx-alignment: Center;\r\n" + //
                        "    -fx-font-size : 10pt ;\r\n" + //
                        "    -fx-text-fill : white;\r\n" + //
                        "    -fx-font-weight: bold;");

        Hyperlink buyTicketButton = new Hyperlink("Buy Ticket");
        buyTicketButton.getStyleClass().add("BuyTicketButton");;
        buyTicketButton.setOnAction(e -> openScene.openScene("TicketWisata", buyTicketButton));
        buyTicketButton.setStyle("-fx-background-radius : 20;\r\n" + //
                        "    -fx-padding: 5 10 5 10;\r\n" + //
                        "    -fx-pref-width: 146px;\r\n" + //
                        "    -fx-pref-height: 4px;\r\n" + //
                        "    -fx-background-color:  #4169e1 ;\r\n" + //
                        "    -fx-font-family : \"Arial\" ;\r\n" + //
                        "    -fx-font-size : 12pt ;\r\n" + //
                        "    -fx-alignment: Center;\r\n" + //
                        "    -fx-text-fill : white;\r\n" + //
                        "    -fx-font-weight: bold;");

        buttonBox.getChildren().addAll(viewDetailButton, buyTicketButton);

        detailsBox.getChildren().addAll(titleLabel, locationLabel, descriptionLabel, openTimeLabel, ratingLabel);

        HBox.setHgrow(detailsBox, javafx.scene.layout.Priority.ALWAYS);

        placeBox.getChildren().addAll(imageView, detailsBox, buttonBox);

        return placeBox;
    }

    private List<TourismPlace> getAllTourismPlaces() {
        List<TourismPlace> places = new ArrayList<>();
        places.add(new TourismPlace("Tugu Yogyakarta", "Kota Yogyakarta", "Landmark ikonik kota Yogyakarta yang menjadi simbol sejarah.", "24 jam", "4.6/5", "Monumen", "AppProto/ImageSRC/tugu_yogyakarta.png"));
        places.add(new TourismPlace("Jalan Malioboro", "Kota Yogyakarta", "Pusat perbelanjaan tradisional dan jantung kota Yogyakarta.", "24 jam", "4.5/5", "Jalan", "AppProto/ImageSRC/jalan_malioboro.jpg"));
        places.add(new TourismPlace("Titik Nol Yogyakarta", "Kota Yogyakarta", "Pusat kota Yogyakarta dengan arsitektur kolonial yang indah.", "24 jam", "4.4/5", "Landmark", "AppProto/ImageSRC/titik_nol.jpeg"));
        places.add(new TourismPlace("Monumen Yogya Kembali", "Kabupaten Sleman", "Monumen yang mengenang perjuangan rakyat Yogyakarta.", "08:00 - 16:00", "4.5/5", "Museum", "AppProto/ImageSRC/monumen_jogja_kembali.jpg"));
        places.add(new TourismPlace("Museum Sonobudoyo", "Kota Yogyakarta", "Museum yang menyimpan koleksi budaya Jawa yang lengkap.", "08:00 - 20:00", "4.3/5", "Museum", "AppProto/ImageSRC/museum_sonobudoyo.jpg"));
        places.add(new TourismPlace("Merapi Volcano Museum", "Kabupaten Sleman", "Museum yang menampilkan sejarah dan informasi tentang Gunung Merapi.", "08:00 - 17:00", "4.4/5", "Museum", "AppProto/ImageSRC/volcano_merapi_museum.jpg"));
        places.add(new TourismPlace("Taman Sari", "Kota Yogyakarta", "Situs bersejarah bekas taman kerajaan Kesultanan Yogyakarta.", "09:00 - 15:00", "4.5/5", "Situs Sejarah", "AppProto/ImageSRC/taman_sari.jpeg"));
        places.add(new TourismPlace("Museum Ullun Sentalu", "Kabupaten Sleman", "Museum budaya Jawa yang menampilkan sejarah keluarga kerajaan Mataram.", "08:30 - 16:00", "4.7/5", "Museum", "AppProto/ImageSRC/museum_ullen_sentalu.jpg"));
        places.add(new TourismPlace("Desa Wisata Tembi", "Kabupaten Bantul", "Desa wisata yang menawarkan pengalaman budaya Jawa yang autentik.", "08:00 - 17:00", "4.2/5", "Desa Wisata", "AppProto/ImageSRC/desa_wisata_temmbi.jpg"));
        places.add(new TourismPlace("Desa Wisata Bobung", "Kabupaten Gunung Kidul", "Desa pengrajin topeng kayu dan batik kayu.", "08:00 - 16:00", "4.3/5", "Desa Wisata", "AppProto/ImageSRC/desa_wisata_bobung.jpeg"));
        places.add(new TourismPlace("Desa Wisata Kalibiru", "Kabupaten Kulon Progo", "Desa wisata dengan pemandangan alam yang menakjubkan dari ketinggian.", "07:00 - 17:00", "4.6/5", "Wisata Alam", "AppProto/ImageSRC/desa_wisata_kalibiru.jpeg"));
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
    private void viewDetailButton() {
        if (placeList.getChildren().isEmpty()) {
            return;
        }
        HBox selectedPlaceBox = (HBox) placeList.getChildren().stream()
            .filter(node -> node.getStyleClass().contains("selected"))
            .findFirst()
            .orElse(null);

        if (selectedPlaceBox != null) {
            TourismPlace place = (TourismPlace) selectedPlaceBox.getUserData();
            showDetailView(place);
        } else {
            showNoResultsMessage();
        }
    }

    @FXML
    public void Home() {
        openScene.openScene("Homepage2", linkHome);
    }

    @FXML
    public void About() {
        openScene.openScene("WisataBudaya", linkBack);
    }

    @FXML
    public void TicketPayment() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Price of a ticket");
        alert.setHeaderText(null);
        alert.setContentText("Rp15.000,00");
        alert.showAndWait();
        openScene.openScene("TicketWisata", buyTicketButton);
    }

    @FXML
    public void onLocationChanged() {
        String selectedLocation = locationComboBox.getValue();
        filterAndDisplayPlaces(selectedLocation);
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
