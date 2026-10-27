package emlak;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;
import java.util.UUID;

public class MusterilerController {
    @FXML private BorderPane customerRoot;
    @FXML private TableView<Musteri> customerTable;
    @FXML private TableColumn<Musteri, String> nameColumn, phoneColumn, emailColumn, noteColumn;
    @FXML private TextField nameField, phoneField, emailField, customerSearch;
    @FXML private TextArea noteField;
    @FXML private Label customerStatus;

    private HashMap<String, Musteri> musteriler = new HashMap<>();
    private DataManager dataManager = new DataManager();
    private String secilenId;
    private boolean verilerYuklendi;

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getAdSoyad()));
        phoneColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTelefon()));
        emailColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getEposta()));
        noteColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNot()));
        customerTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        customerTable.setPlaceholder(new Label("Müşteri bulunamadı."));
        customerTable.getSelectionModel().selectedItemProperty().addListener((obs, eski, secilen) -> {
            if (secilen == null) return;
            secilenId = secilen.getId();
            nameField.setText(secilen.getAdSoyad());
            phoneField.setText(secilen.getTelefon());
            emailField.setText(secilen.getEposta());
            noteField.setText(secilen.getNot());
        });
        customerSearch.textProperty().addListener((obs, eski, yeni) -> listele());
        try {
            musteriler = dataManager.loadCustomers();
            verilerYuklendi = true;
            listele();
        } catch (IOException e) {
            customerStatus.setText(e.getMessage());
        }
    }

    private void listele() {
        if (!verilerYuklendi) return;
        ArrayList<Musteri> sonuc = new ArrayList<>();
        String aranan = customerSearch.getText().trim().toLowerCase(Locale.forLanguageTag("tr-TR"));
        for (Musteri musteri : musteriler.values()) {
            String metin = musteri.getAdSoyad() + " " + musteri.getTelefon() + " " + musteri.getEposta() + " " + musteri.getNot();
            if (metin.toLowerCase(Locale.forLanguageTag("tr-TR")).contains(aranan)) {
                sonuc.add(musteri);
            }
        }
        sonuc.sort(Comparator.comparing(Musteri::getAdSoyad));
        customerTable.setItems(FXCollections.observableArrayList(sonuc));
        customerTable.sort();
        customerStatus.setText(sonuc.size() + " / " + musteriler.size() + " müşteri");
    }

    @FXML
    private void newCustomer() {
        secilenId = null;
        customerTable.getSelectionModel().clearSelection();
        nameField.clear();
        phoneField.clear();
        emailField.clear();
        noteField.clear();
    }

    @FXML
    private void saveCustomer() {
        if (!verilerYuklendi) { uyari("Kayıt dosyası okunamadığı için işlem yapılamıyor."); return; }
        if (nameField.getText().isBlank() || phoneField.getText().isBlank()) {
            uyari("Müşteri adı ve telefon boş olamaz.");
            return;
        }
        String id = secilenId == null ? UUID.randomUUID().toString() : secilenId;
        Musteri musteri = new Musteri(id, nameField.getText().trim(), phoneField.getText().trim(),
                emailField.getText().trim(), noteField.getText().trim());
        HashMap<String, Musteri> yeniListe = new HashMap<>(musteriler);
        yeniListe.put(id, musteri);
        try {
            dataManager.saveCustomers(yeniListe);
            musteriler = yeniListe;
            newCustomer();
            listele();
        } catch (IOException e) { uyari(e.getMessage()); }
    }

    @FXML
    private void deleteCustomer() {
        Musteri secilen = customerTable.getSelectionModel().getSelectedItem();
        if (secilen == null) { uyari("Önce tablodan bir müşteri seçiniz."); return; }
        Alert onay = new Alert(Alert.AlertType.CONFIRMATION, "Seçili müşteri silinsin mi?", ButtonType.YES, ButtonType.NO);
        onay.setHeaderText(null);
        if (onay.showAndWait().orElse(ButtonType.NO) != ButtonType.YES) return;
        HashMap<String, Musteri> yeniListe = new HashMap<>(musteriler);
        yeniListe.remove(secilen.getId());
        try {
            dataManager.saveCustomers(yeniListe);
            musteriler = yeniListe;
            newCustomer();
            listele();
        } catch (IOException e) { uyari(e.getMessage()); }
    }

    private void uyari(String mesaj) {
        Alert alert = new Alert(Alert.AlertType.WARNING, mesaj);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    @FXML private void closeWindow() { ((Stage) customerRoot.getScene().getWindow()).close(); }
}
