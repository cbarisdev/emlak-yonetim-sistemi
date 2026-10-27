package emlak;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.time.LocalDate;
import javafx.scene.layout.BorderPane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

public class VeriEkleGuncelleController {

    @FXML
    private TextField ilanBasligiField;

    @FXML
    private TextField metrekareField;

    @FXML
    private ComboBox<String> odaSayisiComboBox;

    @FXML
    private TextField fiyatField;

    @FXML
    private ComboBox<String> ilceComboBox;

    @FXML
    private CheckBox kiralikcheck, satilikcheck;

    @FXML
    private CheckBox binaYasi0, binaYasi1, binaYasi2, binaYasi3, binaYasi4, binaYasi5Plus;

    @FXML
    private Button minimizeButton, closeButton;

    // Bulunduğu Kat alanında kullanılan CheckBox'lar
    @FXML
    private CheckBox bkBodrum;
    @FXML
    private CheckBox bkZemin;
    @FXML
    private CheckBox bkBahceKati;
    @FXML
    private CheckBox bkGirisKati;
    @FXML
    private CheckBox bkMustakil;
    @FXML
    private CheckBox bkCatiKati;
    @FXML
    private CheckBox bk1;
    @FXML
    private CheckBox bk2;
    @FXML
    private CheckBox bk3;
    @FXML
    private CheckBox bk4;
    @FXML
    private CheckBox bk5;
    @FXML
    private CheckBox bk6;
    @FXML
    private CheckBox bk7;
    @FXML
    private CheckBox bk8;

    // Kat sayısı için CheckBox'lar
    @FXML
    private CheckBox ks1;
    @FXML
    private CheckBox ks2;
    @FXML
    private CheckBox ks3;
    @FXML
    private CheckBox ks4;
    @FXML
    private CheckBox ks5;
    @FXML
    private CheckBox ks6;
    @FXML
    private CheckBox ks7;
    @FXML
    private CheckBox ks8;

    // Isıtma için CheckBox'lar
    @FXML
    private CheckBox isitmaYOK;
    @FXML
    private CheckBox isitmaSoba;
    @FXML
    private CheckBox isitmaDogalgazSobasi;
    @FXML
    private CheckBox isitmaKatKaloriferi;
    @FXML
    private CheckBox isitmaMerkezi;
    @FXML
    private CheckBox isitmaKombiDogalgaz;
    @FXML
    private CheckBox isitmaKombiElektrik;
    @FXML
    private CheckBox isitmaYerden;

    // Banyo sayısı için CheckBox'lar
    @FXML
    private CheckBox bs1;
    @FXML
    private CheckBox bs2;
    @FXML
    private CheckBox bs3;

    // Mutfak tipi için CheckBox'lar
    @FXML
    private CheckBox mutfakA;
    @FXML
    private CheckBox mutfakK;

    // Balkon için CheckBox'lar
    @FXML
    private CheckBox balkonV;
    @FXML
    private CheckBox balkonY;

    // Asansör için CheckBox'lar
    @FXML
    private CheckBox asansorV;
    @FXML
    private CheckBox asansorY;

    // Eşyalı için CheckBox'lar
    @FXML
    private CheckBox esyaliE;
    @FXML
    private CheckBox esyaliH;

    // Kullanım Durumu için CheckBox'lar
    @FXML
    private CheckBox kdb; // Boş
    @FXML
    private CheckBox kdK; // Kiracılı
    @FXML
    private CheckBox kdM; // Mülk Sahibi

    // Site İçerisinde için CheckBox'lar
    @FXML
    private CheckBox siE; // Evet
    @FXML
    private CheckBox siH; // Hayır

    // Tapu Durumu için CheckBox'lar
    @FXML
    private CheckBox tdKatMulkiyeti;
    @FXML
    private CheckBox tdHisseliTapu;
    @FXML
    private CheckBox tdArsaTapulu;
    @FXML
    private CheckBox tdYurtDisiTapulu;
    @FXML
    private CheckBox tdTapuKaydiYok;

    // Kimden için ToggleGroup ve RadioButton'lar
    @FXML
    private ToggleGroup kimdenToggleGroup;

    @FXML private RadioButton kimdenSahibinden;
    @FXML private RadioButton kimdenInsaatFirmasindan;
    @FXML private RadioButton kimdenBankadan;
    @FXML private RadioButton kimdenTurizmIsletmesinden;

    @FXML
    private TextField adresField;

    @FXML
    private TextField resim1Field;

    @FXML
    private TextField resim2Field;

    @FXML private BorderPane formRoot;
    @FXML private Canvas canvasResim;
    @FXML private ToggleGroup turToggleGroup;
    private Listing currentListing;
    private int resimSirasi;

    @FXML
    public void initialize() {
        tekSecimYap(kiralikcheck, satilikcheck);
        tekSecimYap(binaYasi0, binaYasi1, binaYasi2, binaYasi3, binaYasi4, binaYasi5Plus);
        tekSecimYap(bkBodrum, bkZemin, bkBahceKati, bkGirisKati, bkMustakil, bkCatiKati, bk1, bk2, bk3, bk4, bk5, bk6, bk7, bk8);
        tekSecimYap(ks1, ks2, ks3, ks4, ks5, ks6, ks7, ks8);
        tekSecimYap(isitmaYOK, isitmaSoba, isitmaDogalgazSobasi, isitmaKatKaloriferi, isitmaMerkezi, isitmaKombiDogalgaz, isitmaKombiElektrik, isitmaYerden);
        tekSecimYap(bs1, bs2, bs3);
        tekSecimYap(mutfakA, mutfakK);
        tekSecimYap(balkonV, balkonY);
        tekSecimYap(asansorV, asansorY);
        tekSecimYap(esyaliE, esyaliH);
        tekSecimYap(kdb, kdK, kdM);
        tekSecimYap(siE, siH);
        tekSecimYap(tdKatMulkiyeti, tdHisseliTapu, tdArsaTapulu, tdYurtDisiTapulu, tdTapuKaydiYok);


        resim1Field.textProperty().addListener((obs, eski, yeni) -> resmiGoster());
        resim2Field.textProperty().addListener((obs, eski, yeni) -> resmiGoster());
        resmiGoster();
    }

    private void tekSecimYap(CheckBox... secenekler) {
        for (CheckBox secenek : secenekler) {
            secenek.setOnAction(event -> {
                if (secenek.isSelected()) {
                    for (CheckBox diger : secenekler) {
                        if (diger != secenek) diger.setSelected(false);
                    }
                }
            });
        }
    }

    @FXML
    private void btnResim() {
        FileChooser secici = new FileChooser();
        secici.setTitle("En fazla iki resim seçiniz");
        secici.getExtensionFilters().add(new FileChooser.ExtensionFilter("Resimler", "*.png", "*.jpg", "*.jpeg"));
        List<File> dosyalar = secici.showOpenMultipleDialog(formRoot.getScene().getWindow());
        if (dosyalar == null || dosyalar.isEmpty()) return;
        if (dosyalar.size() > 2) {
            showAlert(Alert.AlertType.WARNING, "Resim seçimi", "En fazla iki resim seçebilirsiniz.");
            return;
        }
        resimSirasi = 0;
        resim1Field.setText(dosyalar.get(0).getAbsolutePath());
        resim2Field.setText(dosyalar.size() == 2 ? dosyalar.get(1).getAbsolutePath() : "");
        resmiGoster();
    }

    private String resmiKaydet(String yol) throws IOException {
        if (yol == null || yol.isBlank()) return "";
        if (currentListing != null && (yol.equals(currentListing.getResim1()) || yol.equals(currentListing.getResim2()))) return yol;
        Path kaynak = Path.of(yol);
        if (!kaynak.isAbsolute()) return yol;
        Files.createDirectories(Path.of("resimler"));
        String ad = kaynak.getFileName().toString();
        String uzanti = ad.substring(ad.lastIndexOf('.'));
        Path hedef = Path.of("resimler", UUID.randomUUID() + uzanti);
        Files.copy(kaynak, hedef, StandardCopyOption.REPLACE_EXISTING);
        return hedef.toString();
    }

    private void resmiGoster() {
        GraphicsContext kalem = canvasResim.getGraphicsContext2D();
        double genislik = canvasResim.getWidth();
        double yukseklik = canvasResim.getHeight();
        kalem.setFill(Color.web("#e8f5e9"));
        kalem.fillRect(0, 0, genislik, yukseklik);
        String yol = resimSirasi == 0 ? resim1Field.getText() : resim2Field.getText();
        if (yol == null || yol.isBlank() || !new File(yol).isFile()) {
            kalem.setFill(Color.DARKGREEN);
            kalem.fillText("Resim ekle ile resim seçebilirsiniz.", 20, yukseklik / 2);
            return;
        }
        Image resim = new Image(new File(yol).toURI().toString());
        if (resim.isError()) return;
        double oran = Math.min(genislik / resim.getWidth(), yukseklik / resim.getHeight());
        double resimGenislik = resim.getWidth() * oran;
        double resimYukseklik = resim.getHeight() * oran;
        kalem.drawImage(resim, (genislik - resimGenislik) / 2, (yukseklik - resimYukseklik) / 2, resimGenislik, resimYukseklik);
    }

    @FXML private void previousImage() { resimSirasi = 0; resmiGoster(); }
    @FXML private void nextImage() { resimSirasi = resim2Field.getText().isBlank() ? 0 : 1; resmiGoster(); }
    @FXML private void clearImages() { resim1Field.clear(); resim2Field.clear(); resimSirasi = 0; resmiGoster(); }

    private String currentListingId = null;
    private EmlakYonetimController mainController;

    /**
     * Bir ilanı güncellemek için mevcut ilan verilerini form alanlarına yükler.
     */
    public void loadListingForUpdate(Listing listing) {
        currentListing = listing;
        currentListingId = listing.getId();
        for (javafx.scene.control.Toggle toggle : turToggleGroup.getToggles()) {
            RadioButton radio = (RadioButton) toggle;
            String kategori = radio.getId().startsWith("isYeri") ? "İş Yeri" : "Konut";
            radio.setSelected(kategori.equals(listing.getKategori()) && radio.getText().equals(listing.getAltKategori()));
        }
        ilanBasligiField.setText(listing.getIlanBasligi());
        metrekareField.setText(String.valueOf(listing.getMetrekare()));
        odaSayisiComboBox.setValue(listing.getOdaSayisi());
        fiyatField.setText(String.valueOf(listing.getFiyat()));
        ilceComboBox.setValue(listing.getIlce());

        // İlan Türü
        if (listing.getIlanTuru().equalsIgnoreCase("Kiralık")) {
            kiralikcheck.setSelected(true);
            satilikcheck.setSelected(false);
        } else if (listing.getIlanTuru().equalsIgnoreCase("Satılık")) {
            satilikcheck.setSelected(true);
            kiralikcheck.setSelected(false);
        }

        // Bina Yaşı
        int binaYasi = listing.getBinaYasi();
        switch (binaYasi) {
            case 0: binaYasi0.setSelected(true); break;
            case 1: binaYasi1.setSelected(true); break;
            case 2: binaYasi2.setSelected(true); break;
            case 3: binaYasi3.setSelected(true); break;
            case 4: binaYasi4.setSelected(true); break;
            case 5:
            default:
                binaYasi5Plus.setSelected(true);
                break;
        }

        // Bulunduğu Kat
        String bulunduguKat = listing.getBulunduguKat();
        switch (bulunduguKat) {
            case "Bodrum Katı": bkBodrum.setSelected(true); break;
            case "Zemin Katı": bkZemin.setSelected(true); break;
            case "Bahçe Katı": bkBahceKati.setSelected(true); break;
            case "Giriş Katı": bkGirisKati.setSelected(true); break;
            case "Müstakil Katı": bkMustakil.setSelected(true); break;
            case "Çatı Katı": bkCatiKati.setSelected(true); break;
            case "1": bk1.setSelected(true); break;
            case "2": bk2.setSelected(true); break;
            case "3": bk3.setSelected(true); break;
            case "4": bk4.setSelected(true); break;
            case "5": bk5.setSelected(true); break;
            case "6": bk6.setSelected(true); break;
            case "7": bk7.setSelected(true); break;
            case "8": bk8.setSelected(true); break;
            default: break;
        }

        // Kat Sayısı
        int katSayisi = listing.getKatSayisi();
        switch (katSayisi) {
            case 1: ks1.setSelected(true); break;
            case 2: ks2.setSelected(true); break;
            case 3: ks3.setSelected(true); break;
            case 4: ks4.setSelected(true); break;
            case 5: ks5.setSelected(true); break;
            case 6: ks6.setSelected(true); break;
            case 7: ks7.setSelected(true); break;
            case 8: ks8.setSelected(true); break;
            default: break;
        }

        // Isıtma
        String isitma = listing.getIsitma();
        switch (isitma) {
            case "Yok": isitmaYOK.setSelected(true); break;
            case "Soba": isitmaSoba.setSelected(true); break;
            case "Doğalgaz Sobası": isitmaDogalgazSobasi.setSelected(true); break;
            case "Kat Kaloriferi": isitmaKatKaloriferi.setSelected(true); break;
            case "Merkezi": isitmaMerkezi.setSelected(true); break;
            case "Kombi (Doğalgaz)": isitmaKombiDogalgaz.setSelected(true); break;
            case "Kombi (Elektrik)": isitmaKombiElektrik.setSelected(true); break;
            case "Yerden Isıtma": isitmaYerden.setSelected(true); break;
            default: break;
        }

        // Banyo Sayısı
        int banyoSayisi = listing.getBanyoSayisi();
        switch (banyoSayisi) {
            case 1: bs1.setSelected(true); break;
            case 2: bs2.setSelected(true); break;
            case 3: bs3.setSelected(true); break;
            default: break;
        }

        // Mutfak
        String mutfak = listing.getMutfak();
        switch (mutfak) {
            case "Açık (Amerikan)": mutfakA.setSelected(true); break;
            case "Kapalı": mutfakK.setSelected(true); break;
            default: break;
        }

        // Balkon
        String balkon = listing.getBalkon();
        switch (balkon) {
            case "Var": balkonV.setSelected(true); break;
            case "Yok": balkonY.setSelected(true); break;
            default: break;
        }

        // Asansör
        boolean asansorVarMi = listing.isAsansorVarMi();
        if (asansorVarMi) {
            asansorV.setSelected(true);
        } else {
            asansorY.setSelected(true);
        }

        // Eşyalı
        boolean esyaliMi = listing.isEsyaliMi();
        if (esyaliMi) {
            esyaliE.setSelected(true);
        } else {
            esyaliH.setSelected(true);
        }

        // Kullanım Durumu
        String kullanimDurumu = listing.getKullanimDurumu();
        switch (kullanimDurumu) {
            case "Boş": kdb.setSelected(true); break;
            case "Kiracılı": kdK.setSelected(true); break;
            case "Mülk Sahibi": kdM.setSelected(true); break;
            default: break;
        }

        // Site İçerisinde
        boolean siteIcerisindeMi = listing.isSiteIcerisindeMi();
        if (siteIcerisindeMi) {
            siE.setSelected(true);
        } else {
            siH.setSelected(true);
        }

        // Tapu Durumu
        String tapuDurumu = listing.getTapuDurumu();
        switch (tapuDurumu) {
            case "Kat Mülkiyeti": tdKatMulkiyeti.setSelected(true); break;
            case "Hisseli Tapu": tdHisseliTapu.setSelected(true); break;
            case "Arsa Tapulu": tdArsaTapulu.setSelected(true); break;
            case "Yurt Dışı Tapulu": tdYurtDisiTapulu.setSelected(true); break;
            case "Tapu Kaydı Yok": tdTapuKaydiYok.setSelected(true); break;
            default: break;
        }

        // Kimden
        String kimden = listing.getKimden();
        switch (kimden) {
            case "Sahibinden": kimdenSahibinden.setSelected(true); break;
            case "İnşaat Firmasından": kimdenInsaatFirmasindan.setSelected(true); break;
            case "Bankadan": kimdenBankadan.setSelected(true); break;
            case "Turizm İşletmesinden": kimdenTurizmIsletmesinden.setSelected(true); break;
            default: break;
        }

        // Adres
        String adres = listing.getAdres();
        adresField.setText(adres);

        // Resimler
        String resim1 = listing.getResim1();
        String resim2 = listing.getResim2();
        resim1Field.setText(resim1);
        resim2Field.setText(resim2);
        resmiGoster();
    }

    /**
     * Yeni bir ilan eklemek veya mevcut bir ilanı güncellemek için kaydet butonuna basıldığında çağrılır.
     */
    @FXML
    private void handleSave(ActionEvent event) {
        // Yeni bir Listing nesnesi oluştur
        try {
            // İlan Başlığı kontrolü ve atanması
            String ilanBasligi = ilanBasligiField.getText().trim();
            if (ilanBasligi.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Hata", "İlan başlığı boş olamaz.");
                return;
            }

            // Metrekare kontrolü ve atanması
            double metrekare;
            try {
                metrekare = Double.parseDouble(metrekareField.getText().trim().replace(',', '.'));
            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Hata", "Metrekare yalnızca sayı olmalıdır.");
                return;
            }

            // Oda Sayısı kontrolü ve atanması
            String odaSayisi = odaSayisiComboBox.getValue();
            if (odaSayisi == null || odaSayisi.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Hata", "Oda sayısını seçiniz.");
                return;
            }

            // Fiyat kontrolü ve atanması
            double fiyat;
            try {
                fiyat = Double.parseDouble(fiyatField.getText().trim().replace(',', '.'));
            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Hata", "Fiyat yalnızca sayı olmalıdır.");
                return;
            }

            if (!Double.isFinite(metrekare) || metrekare <= 0 || !Double.isFinite(fiyat) || fiyat <= 0) {
                showAlert(Alert.AlertType.ERROR, "Hata", "Fiyat ve metrekare sıfırdan büyük olmalıdır.");
                return;
            }

            // İlçe kontrolü ve atanması
            String ilce = ilceComboBox.getValue();
            if (ilce == null || ilce.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Hata", "İlçe seçiniz.");
                return;
            }

            // İlan Türü kontrolü ve atanması (Kiralık / Satılık)
            String ilanTuru;
            if (kiralikcheck.isSelected() && satilikcheck.isSelected()) {
                showAlert(Alert.AlertType.ERROR, "Hata", "Hem Kiralık hem Satılık seçilemez.");
                return;
            }
            if (kiralikcheck.isSelected()) {
                ilanTuru = "Kiralık";
            } else if (satilikcheck.isSelected()) {
                ilanTuru = "Satılık";
            } else {
                showAlert(Alert.AlertType.ERROR, "Hata", "Lütfen ilan türünü seçiniz.");
                return;
            }

            // Bina Yaşı kontrolü ve atanması
            int binaYasi;
            if (binaYasi0.isSelected()) {
                binaYasi = 0;
            } else if (binaYasi1.isSelected()) {
                binaYasi = 1;
            } else if (binaYasi2.isSelected()) {
                binaYasi = 2;
            } else if (binaYasi3.isSelected()) {
                binaYasi = 3;
            } else if (binaYasi4.isSelected()) {
                binaYasi = 4;
            } else if (binaYasi5Plus.isSelected()) {
                binaYasi = 5;
            } else {
                showAlert(Alert.AlertType.ERROR, "Hata", "Lütfen bina yaşını seçiniz.");
                return;
            }

            if (binaYasi == 5 && currentListing != null && currentListing.getBinaYasi() >= 5) {
                binaYasi = currentListing.getBinaYasi();
            }

            // Bulunduğu Kat
            String bulunduguKat = null;
            if (bkBodrum.isSelected()) bulunduguKat = "Bodrum Katı";
            else if (bkZemin.isSelected()) bulunduguKat = "Zemin Katı";
            else if (bkBahceKati.isSelected()) bulunduguKat = "Bahçe Katı";
            else if (bkGirisKati.isSelected()) bulunduguKat = "Giriş Katı";
            else if (bkMustakil.isSelected()) bulunduguKat = "Müstakil Katı";
            else if (bkCatiKati.isSelected()) bulunduguKat = "Çatı Katı";
            else if (bk1.isSelected()) bulunduguKat = "1";
            else if (bk2.isSelected()) bulunduguKat = "2";
            else if (bk3.isSelected()) bulunduguKat = "3";
            else if (bk4.isSelected()) bulunduguKat = "4";
            else if (bk5.isSelected()) bulunduguKat = "5";
            else if (bk6.isSelected()) bulunduguKat = "6";
            else if (bk7.isSelected()) bulunduguKat = "7";
            else if (bk8.isSelected()) bulunduguKat = "8";

            if (bulunduguKat == null) {
                showAlert(Alert.AlertType.ERROR, "Hata", "Lütfen bulunduğu katı seçiniz.");
                return;
            }

            // Kat Sayısı
            int katSayisi = -1;
            if (ks1.isSelected()) katSayisi = 1;
            else if (ks2.isSelected()) katSayisi = 2;
            else if (ks3.isSelected()) katSayisi = 3;
            else if (ks4.isSelected()) katSayisi = 4;
            else if (ks5.isSelected()) katSayisi = 5;
            else if (ks6.isSelected()) katSayisi = 6;
            else if (ks7.isSelected()) katSayisi = 7;
            else if (ks8.isSelected()) katSayisi = 8;

            if (katSayisi == -1 && currentListing != null) {
                katSayisi = currentListing.getKatSayisi();
            }
            if (katSayisi == -1) {
                showAlert(Alert.AlertType.ERROR, "Hata", "Lütfen kat sayısını seçiniz.");
                return;
            }

            // Isıtma
            String isitma = null;
            if (isitmaYOK.isSelected()) isitma = "Yok";
            else if (isitmaSoba.isSelected()) isitma = "Soba";
            else if (isitmaDogalgazSobasi.isSelected()) isitma = "Doğalgaz Sobası";
            else if (isitmaKatKaloriferi.isSelected()) isitma = "Kat Kaloriferi";
            else if (isitmaMerkezi.isSelected()) isitma = "Merkezi";
            else if (isitmaKombiDogalgaz.isSelected()) isitma = "Kombi (Doğalgaz)";
            else if (isitmaKombiElektrik.isSelected()) isitma = "Kombi (Elektrik)";
            else if (isitmaYerden.isSelected()) isitma = "Yerden Isıtma";

            if (isitma == null) {
                showAlert(Alert.AlertType.ERROR, "Hata", "Lütfen ısıtma tipini seçiniz.");
                return;
            }

            // Banyo Sayısı
            int banyoSayisi = -1;
            if (bs1.isSelected()) banyoSayisi = 1;
            else if (bs2.isSelected()) banyoSayisi = 2;
            else if (bs3.isSelected()) banyoSayisi = 3;

            if (banyoSayisi == -1) {
                showAlert(Alert.AlertType.ERROR, "Hata", "Lütfen banyo sayısını seçiniz.");
                return;
            }

            // Mutfak
            String mutfak = null;
            if (mutfakA.isSelected()) mutfak = "Açık (Amerikan)";
            else if (mutfakK.isSelected()) mutfak = "Kapalı";

            if (mutfak == null) {
                showAlert(Alert.AlertType.ERROR, "Hata", "Lütfen mutfak tipini seçiniz.");
                return;
            }

            // Balkon
            String balkon = null;
            if (balkonV.isSelected()) balkon = "Var";
            else if (balkonY.isSelected()) balkon = "Yok";

            if (balkon == null) {
                showAlert(Alert.AlertType.ERROR, "Hata", "Lütfen balkon var/yok seçiniz.");
                return;
            }

            // Asansör
            boolean asansorVarMi = false;
            if (asansorV.isSelected()) asansorVarMi = true;
            else if (asansorY.isSelected()) asansorVarMi = false;
            else {
                showAlert(Alert.AlertType.ERROR, "Hata", "Lütfen asansör var mı seçiniz.");
                return;
            }

            // Eşyalı
            boolean esyaliMi = false;
            if (esyaliE.isSelected()) esyaliMi = true;
            else if (esyaliH.isSelected()) esyaliMi = false;
            else {
                showAlert(Alert.AlertType.ERROR, "Hata", "Lütfen eşyalı seçeneğini belirleyiniz.");
                return;
            }

            // Kullanım Durumu
            String kullanimDurumu = null;
            if (kdb.isSelected()) kullanimDurumu = "Boş";
            else if (kdK.isSelected()) kullanimDurumu = "Kiracılı";
            else if (kdM.isSelected()) kullanimDurumu = "Mülk Sahibi";
            else {
                showAlert(Alert.AlertType.ERROR, "Hata", "Lütfen kullanım durumunu seçiniz.");
                return;
            }

            // Site İçerisinde
            boolean siteIcerisindeMi = false;
            if (siE.isSelected()) siteIcerisindeMi = true;
            else if (siH.isSelected()) siteIcerisindeMi = false;
            else {
                showAlert(Alert.AlertType.ERROR, "Hata", "Lütfen site içerisinde mi seçiniz.");
                return;
            }

            // Tapu Durumu
            String tapuDurumu = null;
            if (tdKatMulkiyeti.isSelected()) tapuDurumu = "Kat Mülkiyeti";
            else if (tdHisseliTapu.isSelected()) tapuDurumu = "Hisseli Tapu";
            else if (tdArsaTapulu.isSelected()) tapuDurumu = "Arsa Tapulu";
            else if (tdYurtDisiTapulu.isSelected()) tapuDurumu = "Yurt Dışı Tapulu";
            else if (tdTapuKaydiYok.isSelected()) tapuDurumu = "Tapu Kaydı Yok";
            else {
                showAlert(Alert.AlertType.ERROR, "Hata", "Lütfen tapu durumunu seçiniz.");
                return;
            }

            // Kimden
            RadioButton selectedKimden = (RadioButton) kimdenToggleGroup.getSelectedToggle();
            if (selectedKimden == null) {
                showAlert(Alert.AlertType.ERROR, "Hata", "Lütfen kimden seçiniz.");
                return;
            }
            String kimden = selectedKimden.getText();

            // Adres
            if (adresField == null) {
                showAlert(Alert.AlertType.ERROR, "Hata", "Adres alanı bulunamadı.");
                return;
            }

            String adres = adresField.getText().trim();
            if (adres.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Hata", "Adres boş olamaz.");
                return;
            }

            // Resimler
            String resim1 = resim1Field.getText().trim();
            String resim2 = resim2Field.getText().trim();
            resim1Field.setText(resim1);
            resim2Field.setText(resim2);

            // Yeni bir ID atanır (eğer yeni kayıt ise)
            String id = currentListingId != null ? currentListingId : java.util.UUID.randomUUID().toString();

            RadioButton secilenTur = (RadioButton) turToggleGroup.getSelectedToggle();
            if (secilenTur == null) {
                showAlert(Alert.AlertType.ERROR, "Hata", "Konut veya İş Yeri türünü seçiniz.");
                return;
            }
            String kategori = secilenTur.getId().startsWith("isYeri") ? "İş Yeri" : "Konut";
            String altKategori = secilenTur.getText();
            resim1 = resmiKaydet(resim1);
            resim2 = resmiKaydet(resim2);

            // Listing nesnesini oluştur
            Listing listing = new Listing(
                    id,
                    ilanBasligi,
                    metrekare,
                    odaSayisi,
                    fiyat,
                    currentListing == null ? LocalDate.now() : currentListing.getIlanTarihi(),
                    ilce,
                    ilanTuru,
                    kategori,
                    altKategori,
                    binaYasi,
                    bulunduguKat,
                    katSayisi,
                    isitma,
                    banyoSayisi,
                    mutfak,
                    balkon,
                    asansorVarMi,
                    esyaliMi,
                    kullanimDurumu,
                    siteIcerisindeMi,
                    tapuDurumu,
                    kimden,
                    adres,
                    resim1,
                    resim2
            );

            // Ana kontrol sınıfı ile işlem yapılır
            if (mainController == null) {
                showAlert(Alert.AlertType.ERROR, "Hata", "Ana kontrol sınıfına erişilemedi.");
                return;
            }

            if (currentListingId == null) {
                // Yeni ilan ekleme
                mainController.addListing(listing);

            } else {
                // Mevcut ilan güncelleme
                mainController.updateListingData(currentListingId, listing);

            }

            // Pencereyi kapatma
            Stage stage = (Stage) formRoot.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Hata", "Bir hata oluştu: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * İptal butonuna basıldığında pencereyi kapatır.
     */
    @FXML
    private void handleCancel(ActionEvent event) {
        Stage stage = (Stage) formRoot.getScene().getWindow();
        stage.close();
    }

    /**
     * Pencereyi simge durumuna küçültür.
     */
    @FXML
    private void minimizeWindow(ActionEvent event) {
        Stage stage = (Stage) formRoot.getScene().getWindow();
        stage.setIconified(true);
    }

    /**
     * Pencereyi kapatır.
     */
    @FXML
    private void closeWindow(ActionEvent event) {
        Stage stage = (Stage) formRoot.getScene().getWindow();
        stage.close();
    }

    /**
     * Ana kontrolcüyü ayarlar.
     */
    public void setMainController(EmlakYonetimController ctrl) {
        this.mainController = ctrl;
    }

    /**
     * Alert gösterir.
     */
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}






