package emlak;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import java.io.IOException;

public class Main extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.setTitle("Giriş Ekranı");
        stage.setResizable(false);
        stage.show();
    }

    public static void changeScene(String fxmlFile, boolean maximize) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource(fxmlFile));
        primaryStage.setScene(new Scene(root));
        if (maximize) {
            Rectangle2D alan = Screen.getPrimary().getVisualBounds();
            primaryStage.setX(alan.getMinX());
            primaryStage.setY(alan.getMinY());
            primaryStage.setWidth(alan.getWidth());
            primaryStage.setHeight(alan.getHeight());
            primaryStage.setTitle("Emlak Yönetim Sistemi");
        } else {
            primaryStage.sizeToScene();
            primaryStage.centerOnScreen();
        }
    }

    public static Stage openWindow(Window owner, String title, Parent root) {
        Stage stage = new Stage(StageStyle.UNDECORATED);
        stage.initOwner(owner);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        Rectangle2D alan = Screen.getPrimary().getVisualBounds();
        stage.setWidth(Math.min(1100, alan.getWidth() - 40));
        stage.setHeight(Math.min(850, alan.getHeight() - 40));
        stage.centerOnScreen();
        return stage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
