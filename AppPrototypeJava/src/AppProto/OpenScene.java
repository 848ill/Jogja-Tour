package AppProto;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.input.MouseEvent;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class OpenScene {
    private double x, y;

    public void openScene(String sceneName, Node node, String... params) {
        try {
            // Mendapatkan stage saat ini
            Stage stage = (Stage) node.getScene().getWindow();

            // Membuka scene baru
            FXMLLoader loader = new FXMLLoader(getClass().getResource(sceneName + ".fxml"));
            Parent root = loader.load();

            // Membuat BorderPane sebagai root container
            BorderPane borderPane = new BorderPane();
            borderPane.setCenter(root);

            Scene scene = new Scene(borderPane);

            borderPane.setOnMousePressed((MouseEvent event) -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });

            borderPane.setOnMouseDragged((MouseEvent event) -> {
                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);
            });

            // Jika kembali ke Homepage, refresh profil
            if (sceneName.equals("Homepage2")) {
                HomepageController controller = loader.getController();
                controller.refreshProfile();
            }

            // Jika membuka Payment, set layanan yang dipilih
            if (sceneName.equals("Payment") && params.length > 0) {
                PaymentController controller = loader.getController();
                controller.setBookedService(params[0]);
            }

            // Menambahkan fitur close
            stage.setOnCloseRequest(event -> {
                System.out.println("Stage is closing");
            });

            stage.setTitle("Nusa Karya");
            stage.setScene(scene);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}