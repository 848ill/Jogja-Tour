package AppProto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class ProfileController implements Initializable{

    // Inisialisasi Text
    @FXML private TextField name;
    @FXML private TextField userName;
    @FXML private TextField dateBirth;
    @FXML private TextField email1;
    @FXML private PasswordField password1;
    @FXML private Label nameTitle;

    // Inisialisasi Button
    @FXML private Button changeProf;
    @FXML private Button saveProf;

    // Inisialisasi Image
    @FXML private ImageView fotoProfile;


    // Link Back To Home
    @FXML
    private Hyperlink linkHome;

    // Import OpenScene
    private OpenScene openScene = new OpenScene();
    private RegUser currentUser;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadUserData();
        disableEditing(true);
    }


    private void loadUserData() {
        try {
            XStream xstream = new XStream(new StaxDriver());
            xstream.allowTypes(new Class[]{RegUser.class});
            FileInputStream fis = new FileInputStream("users.xml");
            currentUser = (RegUser) xstream.fromXML(fis);
            fis.close();

            name.setText(currentUser.getNama());
            userName.setText(currentUser.getNama()); // Assuming username is the same as name
            email1.setText(currentUser.getEmail());
            dateBirth.setText(currentUser.getBirthDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
            nameTitle.setText(currentUser.getNama());
            password1.setText(currentUser.getPassword());

            // Load profile picture if exists
            loadProfilePicture();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadProfilePicture() {
        File profilePic = new File("profile_pic.jpg");
        System.out.println("Loading profile pic from: " + profilePic.getAbsolutePath());
        if (profilePic.exists()) {
            System.out.println("Profile pic file exists");
            try (FileInputStream fis = new FileInputStream(profilePic)) {
                Image image = new Image(fis);
                Platform.runLater(() -> {
                    // Lepaskan gambar lama jika ada
                    if (fotoProfile.getImage() != null) {
                        fotoProfile.getImage().cancel();
                    }
                    fotoProfile.setImage(null);
                    fotoProfile.setImage(image);
                    fotoProfile.getParent().layout();
                    System.out.println("Profile pic loaded successfully");
                });
            } catch (IOException e) {
                System.out.println("Failed to load profile picture");
                e.printStackTrace();
            }
        } else {
            System.out.println("Profile pic file does not exist, loading default");
            // Load gambar default jika diperlukan
        }
    }

    private void updateProfilePicture(File newPicture) {
        try {
            File destFile = new File("profile_pic.jpg");
            
            // Coba hapus file lama jika ada
            if (destFile.exists()) {
                try {
                    Files.delete(destFile.toPath());
                } catch (IOException e) {
                    System.out.println("Failed to delete existing profile picture: " + e.getMessage());
                    // Lanjutkan meskipun gagal menghapus
                }
            }
            
            // Salin file baru
            Files.copy(newPicture.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Profile picture updated successfully");
            
            // Reload gambar profil
            loadProfilePicture();
        } catch (IOException e) {
            System.out.println("Failed to update profile picture: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void onChangeProfilePictureButtonClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Profile Picture");
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            updateProfilePicture(selectedFile);
        }
    }

    //  Mengatur apakah field dapat diedit atau tidak.
    private void disableEditing(boolean disable) {
        name.setEditable(!disable);
        userName.setEditable(!disable);
        email1.setEditable(!disable);
        dateBirth.setEditable(!disable);
        password1.setEditable(!disable);
        saveProf.setDisable(disable);
    }

    // Mengaktifkan mode edit ketika tombol "Edit Profile" ditekan.
    @FXML
    private void onChangeProfClicked(ActionEvent event) {
        disableEditing(false);
    }

    

    // Untuk Menyimpan Perubahan Data Profile
    @FXML
    private void onSaveProfClicked(ActionEvent event) {
        try {
            currentUser.setNama(name.getText());
            currentUser.setEmail(email1.getText());
            currentUser.setBirthDate(LocalDate.parse(dateBirth.getText()));
            currentUser.setPassword(password1.getText());

            XStream xstream = new XStream(new StaxDriver());
            xstream.allowTypes(new Class[]{RegUser.class});
            String xml = xstream.toXML(currentUser);

            FileOutputStream fos = new FileOutputStream("users.xml");
            fos.write(xml.getBytes());
            fos.close();

            nameTitle.setText(currentUser.getNama());
            disableEditing(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    // Untuk Balik Ke Homepage
    @FXML
    public void Home(ActionEvent event) {
        if (linkHome != null) {
            openScene.openScene("Homepage2", linkHome);
        }
    }
}
