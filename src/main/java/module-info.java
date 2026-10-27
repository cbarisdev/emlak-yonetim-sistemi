module emlak {
    requires javafx.controls;
    requires javafx.fxml;

    opens emlak to javafx.fxml;
    exports emlak;
}
