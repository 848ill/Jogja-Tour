package AppProto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.fxml.FXML;

import javafx.geometry.Pos;

import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;

public class WisataAlamController {
    @FXML
    private ComboBox<String> locationComboBox;

    @FXML
    private VBox placeList;
    private List<TourismPlace> allPlaces;

    @FXML
    private Hyperlink viewDetailButton;

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
        places.add(new TourismPlace("Heha Sky View", "Kabupaten Gunung Kidul", "Tempat yang cocok untuk menikmati pemandangan kota dari ketinggian.", "07:00 - 22:00", "4.5/5", "Bukit", "AppProto/ImageSRC/heha_sky_view.jpg"));
        places.add(new TourismPlace("Obelix Hills", "Kabupaten Gunung Kidul", "Nikmati pemandangan sunset dan city lights yang memukau di wisata perbukitan", "07:00 - 22:00", "4.7/5", "Bukit", "AppProto/ImageSRC/obelix_hills.jpg"));
        places.add(new TourismPlace("Ledok Sambi", "Kabupaten Sleman", "Tempat yang nyaman untuk berkemah dan menikmati alam di sekitar sungai.", "07:00 - 18:00", "4.6/5", "Sungai", "AppProto/ImageSRC/ledok_sambi.jpeg"));
        places.add(new TourismPlace("Bunker Kaliadem Merapi", "Kabupaten Sleman", "Saksi bisu kedahsyatan letusan Gunung Merapi.", "08:00 - 17:00", "4.4/5", "Bukit", "AppProto/ImageSRC/bunker_kaliadem.jpg"));
        places.add(new TourismPlace("Pantai Parangtritis", "Kabupaten Bantul", "Pantai terkenal dengan pemandangan sunset yang indah.", "08:00 - 18:00", "4.5/5", "Pantai", "AppProto/ImageSRC/parangtritis.jpg"));
        places.add(new TourismPlace("Pantai Depok", "Kabupaten Bantul", "Pantai dengan kuliner seafood yang lezat.", "06:00 - 18:00", "4.2/5", "Pantai", "AppProto/ImageSRC/pantai_depok.jpg"));
        places.add(new TourismPlace("Pantai Wediombo", "Kabupaten Gunung Kidul", "Pantai dengan pasir putih dan ombak yang cocok untuk surfing.", "06:00 - 18:00", "4.8/5", "Pantai", "AppProto/ImageSRC/pantai_wediombo.jpg"));
        places.add(new TourismPlace("Pantai Watulawang", "Kabupaten Gunung Kidul", "Pantai yang terkenal dengan formasi batu karangnya yang unik.", "06:00 - 18:00", "4.3/5", "Pantai", "AppProto/ImageSRC/pantai_watulawang.jpg"));
        places.add(new TourismPlace("Pantai Congot", "Kabupaten Kulon Progo", "Pantai dengan pemandangan muara sungai yang indah.", "06:00 - 18:00", "4.1/5", "Pantai", "AppProto/ImageSRC/pantai_congot.png"));
        places.add(new TourismPlace("Pantai Glagah", "Kabupaten Kulon Progo", "Pantai dengan laguna dan dermaga beton yang khas.", "06:00 - 18:00", "4.4/5", "Pantai", "AppProto/ImageSRC/pantai_gelagah.jpeg"));
        places.add(new TourismPlace("Lembah Oyo", "Kabupaten Gunung Kidul", "Lembah dengan pemandangan sungai dan tebing", "06:00 - 17:00", "4.7/5", "Lembah", "AppProto/ImageSRC/lembah_oyo.jpg"));
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
    public void About() {
        openScene.openScene("WisataAlam", linkBack);
    }

    @FXML
    public void viewDetailButtonAction() {
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
    public void TicketPayment() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Price of a ticket");
        alert.setHeaderText(null);
        alert.setContentText("Rp25.000,00");
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
