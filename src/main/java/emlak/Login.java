package emlak;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Login {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void loginBtn(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if ("admin".equals(username.trim()) && "123".equals(password)) {
            try {
                // emlakyonetim.fxml dosyasını maksimum boyutta aç
                Main.changeScene("emlakyonetim.fxml", true);
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Hata", "Emlak Yönetim ekranı yüklenemedi.");
            }
        } else {
            showAlert("Hatalı Giriş", "Kullanıcı adı veya şifre yanlış!");
        }
    }
    @FXML
    private void closeBtn() {
        System.exit(0); // Uygulamayı kapat
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

