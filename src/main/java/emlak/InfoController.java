package emlak;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.File;
import java.util.ArrayList;

public class InfoController {
    @FXML private BorderPane infoRoot;
    @FXML private HBox imageRow;
    @FXML private ImageView resimImageView;
    @FXML private Label imageStatusLabel;
    @FXML private Button prevButton, nextButton;
    private ArrayList<String> resimler = new ArrayList<>();
    private int resimSirasi;

    @FXML
    private Label ilanBasligiLabel;
    @FXML
    private Label metrekareLabel;
    @FXML
    private Label odaSayisiLabel;
    @FXML
    private Label fiyatLabel;
    @FXML
    private Label ilanTarihiLabel;
    @FXML
    private Label ilceLabel;

    // Ek Alanlar
    @FXML
    private Label ilanTuruLabel;
    @FXML
    private Label kategoriLabel;
    @FXML
    private Label altKategoriLabel;
    @FXML
    private Label binaYasiLabel;
    @FXML
    private Label bulunduguKatLabel;
    @FXML
    private Label katSayisiLabel;
    @FXML
    private Label isitmaLabel;
    @FXML
    private Label banyoSayisiLabel;
    @FXML
    private Label mutfakLabel;
    @FXML
    private Label balkonLabel;
    @FXML
    private Label asansorLabel;
    @FXML
    private Label esyaliLabel;
    @FXML
    private Label kullanimDurumuLabel;
    @FXML
    private Label siteIcerisindeLabel;
    @FXML
    private Label tapuDurumuLabel;
    @FXML
    private Label kimdenLabel;
    @FXML
    private Label adresLabel;

    /**
     * Seçilen ilan verilerini etiketlere yükler.
     */
    public void setListing(Listing listing) {
        ilanBasligiLabel.setText(listing.getIlanBasligi());
        metrekareLabel.setText(String.valueOf(listing.getMetrekare()));
        odaSayisiLabel.setText(listing.getOdaSayisi());
        fiyatLabel.setText(String.valueOf(listing.getFiyat()));
        ilanTarihiLabel.setText(listing.getIlanTarihi().toString());
        ilceLabel.setText(listing.getIlce());

        // Ek alanlar
        ilanTuruLabel.setText(listing.getIlanTuru());
        kategoriLabel.setText(listing.getKategori());
        altKategoriLabel.setText(listing.getAltKategori());
        binaYasiLabel.setText(String.valueOf(listing.getBinaYasi()));
        bulunduguKatLabel.setText(listing.getBulunduguKat());
        katSayisiLabel.setText(String.valueOf(listing.getKatSayisi()));
        isitmaLabel.setText(listing.getIsitma());
        banyoSayisiLabel.setText(String.valueOf(listing.getBanyoSayisi()));
        mutfakLabel.setText(listing.getMutfak());
        balkonLabel.setText(listing.getBalkon());
        asansorLabel.setText(listing.isAsansorVarMi() ? "Var" : "Yok");
        esyaliLabel.setText(listing.isEsyaliMi() ? "Esyalı" : "Eşyasız");
        kullanimDurumuLabel.setText(listing.getKullanimDurumu());
        siteIcerisindeLabel.setText(listing.isSiteIcerisindeMi() ? "Evet" : "Hayır");
        tapuDurumuLabel.setText(listing.getTapuDurumu());
        kimdenLabel.setText(listing.getKimden());
        adresLabel.setText(listing.getAdres());

        resimler.clear();
        if (listing.getResim1() != null && !listing.getResim1().isBlank()) resimler.add(listing.getResim1());
        if (listing.getResim2() != null && !listing.getResim2().isBlank()) resimler.add(listing.getResim2());
        resimSirasi = 0;
        imageRow.setVisible(!resimler.isEmpty());
        imageRow.setManaged(!resimler.isEmpty());
        resmiGoster();
    }
    private void resmiGoster() {
        resimImageView.setImage(null);
        prevButton.setDisable(resimler.size() < 2);
        nextButton.setDisable(resimler.size() < 2);
        if (resimler.isEmpty()) {
            imageStatusLabel.setText("Bu ilana resim eklenmemiş.");
            return;
        }
        File dosya = new File(resimler.get(resimSirasi));
        if (!dosya.isFile()) {
            imageStatusLabel.setText("Resim dosyası bulunamadı.");
            return;
        }
        Image resim = new Image(dosya.toURI().toString());
        resimImageView.setImage(resim);
        imageStatusLabel.setText(resim.isError() ? "Resim okunamadı." : (resimSirasi + 1) + " / " + resimler.size());
    }

    @FXML private void showPreviousImage() {
        if (resimler.isEmpty()) return;
        resimSirasi--;
        if (resimSirasi < 0) resimSirasi = resimler.size() - 1;
        resmiGoster();
    }

    @FXML private void showNextImage() {
        if (resimler.isEmpty()) return;
        resimSirasi++;
        if (resimSirasi == resimler.size()) resimSirasi = 0;
        resmiGoster();
    }

    @FXML private void closeWindow() { ((Stage) infoRoot.getScene().getWindow()).close(); }
    @FXML private void minimizeWindow() { ((Stage) infoRoot.getScene().getWindow()).setIconified(true); }
}
