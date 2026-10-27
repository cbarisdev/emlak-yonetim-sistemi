package emlak;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class EmlakYonetimController {
    @FXML private BorderPane mainRoot;
    @FXML private TableView<Listing> resultTableView;
    @FXML private TableColumn<Listing, String> ilanBasligiColumn, odaSayisiColumn, ilceColumn;
    @FXML private TableColumn<Listing, Double> metrekareColumn, fiyatColumn;
    @FXML private TableColumn<Listing, LocalDate> ilanTarihiColumn;
    @FXML private TextField searchField, fiyatMinField, fiyatMaxField, MetrekareMinField, MetrekareMaxField;
    @FXML private ComboBox<String> sortBox;
    @FXML private Label statusLabel;
    @FXML private ListView<String> konutSatilikList, konutKiralikList, isYeriSatilikList, isYeriKiralikList;
    @FXML private ToggleGroup kimdenToggleGroup, ilanTarihiToggleGroup;
    @FXML private CheckBox adresAkcadag, adresArapgir, adresArguvan, adresBattalgazi, adresDarende, adresDogansehir, adresDoganyol, adresHekimhan, adresKale, adresKuluncak, adresPuturge, adresYazihan, adresYesilyurt;
    @FXML private CheckBox oda1Plus1CheckBox, oda1Point5Plus1CheckBox, oda2Plus0CheckBox, oda2Plus1CheckBox, oda2Point5Plus1CheckBox, oda2Plus2CheckBox, oda3Plus0CheckBox, oda3Plus1CheckBox, oda3Point5Plus1CheckBox, oda3Plus2CheckBox, oda3Plus3CheckBox, oda4Plus0CheckBox, oda4Plus1CheckBox;
    @FXML private CheckBox BinaYasi0, BinaYasi1, BinaYasi2, BinaYasi3, BinaYasi4, BinaYasi5Plus;
    @FXML private CheckBox bkBodrum, bkZemin, bkBahceKati, bkGirisKati, bkMustakil, bkCatiKati, bk1, bk2, bk3, bk4, bk5, bk6, bk7, bk8;
    @FXML private CheckBox ks1, ks2, ks3, ks4, ks5, ks6, ks7, ks8;
    @FXML private CheckBox isitmaYOK, isitmaSoba, isitmaDoğalgazSobasi, isitmaKatKaloriferi, isitmaMerkezi, isitmaKombi_dogalgaz, isitmaKombi_elektrik, isitmaYerden;
    @FXML private CheckBox bs1, bs2, bs3;
    @FXML private CheckBox MutfakA, MutfakK;
    @FXML private CheckBox BalkonV, BalkonY;
    @FXML private CheckBox AsansorV, AsansorY;
    @FXML private CheckBox EsyaliE, EsyaliH;
    @FXML private CheckBox kdb, kdK, kdM;
    @FXML private CheckBox siE, siH;
    @FXML private CheckBox tdKatMulkiyeti, tdHisseliTapu, tdArsaTapulu, tdYurtDisiTapulu, tdTapuKaydiYok;
    @FXML private RadioButton itS3, itS7, itS15, itS30;

    private HashMap<String, Listing> listings = new HashMap<>();
    private TreeMap<Double, List<Listing>> listingsByPrice = new TreeMap<>();
    private DataManager dataManager = new DataManager();
    private String secilenKategori = "";
    private String secilenTur = "";
    private String secilenAltKategori = "";
    private boolean secimTemizleniyor;
    private boolean verilerYuklendi;

    @FXML
    public void initialize() {
        ilanBasligiColumn.setCellValueFactory(c -> c.getValue().ilanBasligiProperty());
        metrekareColumn.setCellValueFactory(c -> c.getValue().metrekareProperty().asObject());
        odaSayisiColumn.setCellValueFactory(c -> c.getValue().odaSayisiProperty());
        fiyatColumn.setCellValueFactory(c -> c.getValue().fiyatProperty().asObject());
        ilanTarihiColumn.setCellValueFactory(c -> c.getValue().ilanTarihiProperty());
        ilceColumn.setCellValueFactory(c -> c.getValue().ilceProperty());
        resultTableView.setPlaceholder(new Label("İlan bulunamadı."));
        resultTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        sortBox.getItems().addAll("Tarih: Yeni → Eski", "Fiyat: Artan", "Fiyat: Azalan", "İlçe", "İlan Türü", "Başlık");
        sortBox.setValue("Tarih: Yeni → Eski");

        String[] konutTurleri = {"Villa", "Müstakil Ev", "Daire", "Rezidans", "Arsa", "Tarla", "Bahçe"};
        String[] isYeriTurleri = {"Akaryakıt İstasyonu", "Apartman Dairesi", "Atölye", "AVM", "Büfe",
                "Büro ve Ofis", "Cafe ve Bar", "Çiftlik", "Depo ve Antrepo", "Düğün Salonu",
                "Dükkan ve Mağaza", "Enerji Santrali", "Fabrika ve Üretim Tesisi", "Garaj ve Park Yeri",
                "İmalathane", "İş Hanı Katı ve Ofisi", "Kantin", "Kır ve Kahvaltı Bahçesi", "Kıraathane",
                "Komple Bina", "Maden Ocağı", "Otopark / Garaj", "Oto Yıkama ve Kuaför",
                "Pastane, Fırın ve Tatlıcı", "Pazar Yeri", "Plaza", "Plaza Katı ve Ofisi",
                "Radyo İstasyonu ve TV Kanalı", "Restoran ve Lokanta", "Rezidans Katı ve Ofisi",
                "Sağlık Merkezi", "Sinema ve Konferans Salonu", "SPA, Hamam ve Sauna", "Spor Tesisi"};
        kategoriListesiAyarla(konutSatilikList, "Konut", "Satılık", konutTurleri);
        kategoriListesiAyarla(konutKiralikList, "Konut", "Kiralık", konutTurleri);
        kategoriListesiAyarla(isYeriSatilikList, "İş Yeri", "Satılık", isYeriTurleri);
        kategoriListesiAyarla(isYeriKiralikList, "İş Yeri", "Kiralık", isYeriTurleri);

        resultTableView.setRowFactory(tablo -> {
            TableRow<Listing> satir = new TableRow<>();
            satir.setOnMouseClicked(event -> {
                if (!satir.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                    bilgiGoster(satir.getItem());
                }
            });
            MenuItem bilgi = new MenuItem("Detaylı Bilgi");
            MenuItem guncelle = new MenuItem("Güncelle");
            MenuItem sil = new MenuItem("Sil");
            bilgi.setOnAction(e -> bilgiGoster(satir.getItem()));
            guncelle.setOnAction(e -> formAc(satir.getItem()));
            sil.setOnAction(e -> ilanSil(satir.getItem()));
            ContextMenu menu = new ContextMenu(bilgi, guncelle, sil);
            satir.emptyProperty().addListener((obs, eski, bos) -> satir.setContextMenu(bos ? null : menu));
            return satir;
        });

        try {
            listings = dataManager.loadData();
            verilerYuklendi = true;
            fiyatListesiniOlustur();
            btnAra();
        } catch (IOException e) {
            statusLabel.setText(e.getMessage());
            // Dosya okunamadıysa boş listeyi yanlışlıkla dosyanın üstüne yazma.
        }
        sortBox.setOnAction(e -> {
            resultTableView.getSortOrder().clear();
            btnAra();
        });
    }

    private void kategoriListesiAyarla(ListView<String> liste, String kategori, String tur, String[] degerler) {
        liste.getItems().addAll(degerler);
        liste.getSelectionModel().selectedItemProperty().addListener((obs, eski, secilen) -> {
            if (secimTemizleniyor || secilen == null) return;
            secimTemizleniyor = true;
            for (ListView<String> diger : Arrays.asList(konutSatilikList, konutKiralikList, isYeriSatilikList, isYeriKiralikList)) {
                if (diger != liste) diger.getSelectionModel().clearSelection();
            }
            secimTemizleniyor = false;
            secilenKategori = kategori;
            secilenTur = tur;
            secilenAltKategori = secilen;
            btnAra();
        });
    }

    private void fiyatListesiniOlustur() {
        listingsByPrice.clear();
        for (Listing ilan : listings.values()) {
            if (!listingsByPrice.containsKey(ilan.getFiyat())) {
                listingsByPrice.put(ilan.getFiyat(), new ArrayList<>());
            }
            listingsByPrice.get(ilan.getFiyat()).add(ilan);
        }
    }

    @FXML
    private void btnAra() {
        if (!verilerYuklendi) return;
        try {
            double minFiyat = sinirOku(fiyatMinField, 0);
            double maxFiyat = sinirOku(fiyatMaxField, Double.MAX_VALUE);
            double minAlan = sinirOku(MetrekareMinField, 0);
            double maxAlan = sinirOku(MetrekareMaxField, Double.MAX_VALUE);
            if (minFiyat > maxFiyat || minAlan > maxAlan) {
                throw new IllegalArgumentException("En düşük değer, en yüksek değerden büyük olamaz.");
            }
            LocalDate ilkTarih = null;
            if (itS3.isSelected()) ilkTarih = LocalDate.now().minusDays(3);
            else if (itS7.isSelected()) ilkTarih = LocalDate.now().minusDays(7);
            else if (itS15.isSelected()) ilkTarih = LocalDate.now().minusDays(15);
            else if (itS30.isSelected()) ilkTarih = LocalDate.now().minusDays(30);

            ArrayList<Listing> sonuc = new ArrayList<>();
            String aranan = searchField.getText().trim().toLowerCase(Locale.forLanguageTag("tr-TR"));
            for (List<Listing> ayniFiyatliIlanlar : listingsByPrice.subMap(minFiyat, true, maxFiyat, true).values()) {
                for (Listing ilan : ayniFiyatliIlanlar) {
                    String metin = (ilan.getIlanBasligi() + " " + ilan.getAdres() + " " + ilan.getIlce()).toLowerCase(Locale.forLanguageTag("tr-TR"));
                    if (!metin.contains(aranan)) continue;
                    if (!secilenKategori.isEmpty() && !secilenKategori.equals(ilan.getKategori())) continue;
                    if (!secilenTur.isEmpty() && !secilenTur.equals(ilan.getIlanTuru())) continue;
                    if (!secilenAltKategori.isEmpty() && !secilenAltKategori.equals(ilan.getAltKategori())) continue;
                    if (ilan.getMetrekare() < minAlan || ilan.getMetrekare() > maxAlan) continue;
                    if (ilkTarih != null && ilan.getIlanTarihi().isBefore(ilkTarih)) continue;
                    if (!ozelliklerUygun(ilan)) continue;
                    sonuc.add(ilan);
                }
            }

            String siralama = sortBox.getValue();
            if (siralama.equals("Fiyat: Azalan")) Collections.reverse(sonuc);
            else if (siralama.equals("Tarih: Yeni → Eski")) sonuc.sort(Comparator.comparing(Listing::getIlanTarihi).reversed());
            else if (siralama.equals("İlçe")) sonuc.sort(Comparator.comparing(Listing::getIlce));
            else if (siralama.equals("İlan Türü")) sonuc.sort(Comparator.comparing(Listing::getIlanTuru));
            else if (siralama.equals("Başlık")) sonuc.sort(Comparator.comparing(Listing::getIlanBasligi));
            resultTableView.setItems(FXCollections.observableArrayList(sonuc));
            resultTableView.sort();
            statusLabel.setText(sonuc.size() + " / " + listings.size() + " ilan");
        } catch (IllegalArgumentException e) {
            uyari(e.getMessage());
        }
    }

    private boolean ozelliklerUygun(Listing ilan) {
        if (!secimeUygun(ilan.getIlce(), adresAkcadag, adresArapgir, adresArguvan, adresBattalgazi, adresDarende, adresDogansehir, adresDoganyol, adresHekimhan, adresKale, adresKuluncak, adresPuturge, adresYazihan, adresYesilyurt)) return false;
        if (!secimeUygun(ilan.getOdaSayisi(), oda1Plus1CheckBox, oda1Point5Plus1CheckBox, oda2Plus0CheckBox, oda2Plus1CheckBox, oda2Point5Plus1CheckBox, oda2Plus2CheckBox, oda3Plus0CheckBox, oda3Plus1CheckBox, oda3Point5Plus1CheckBox, oda3Plus2CheckBox, oda3Plus3CheckBox, oda4Plus0CheckBox, oda4Plus1CheckBox)) return false;
        if (!secimeUygun(ilan.getBinaYasi() >= 5 ? "5+" : String.valueOf(ilan.getBinaYasi()), BinaYasi0, BinaYasi1, BinaYasi2, BinaYasi3, BinaYasi4, BinaYasi5Plus)) return false;
        if (!secimeUygun(ilan.getBulunduguKat(), bkBodrum, bkZemin, bkBahceKati, bkGirisKati, bkMustakil, bkCatiKati, bk1, bk2, bk3, bk4, bk5, bk6, bk7, bk8)) return false;
        if (!secimeUygun(String.valueOf(ilan.getKatSayisi()), ks1, ks2, ks3, ks4, ks5, ks6, ks7, ks8)) return false;
        if (!secimeUygun(ilan.getIsitma(), isitmaYOK, isitmaSoba, isitmaDoğalgazSobasi, isitmaKatKaloriferi, isitmaMerkezi, isitmaKombi_dogalgaz, isitmaKombi_elektrik, isitmaYerden)) return false;
        if (!secimeUygun(String.valueOf(ilan.getBanyoSayisi()), bs1, bs2, bs3)) return false;
        if (!secimeUygun(ilan.getMutfak(), MutfakA, MutfakK)) return false;
        if (!secimeUygun(ilan.getBalkon(), BalkonV, BalkonY)) return false;
        if (!secimeUygun(ilan.isAsansorVarMi() ? "Var" : "Yok", AsansorV, AsansorY)) return false;
        if (!secimeUygun(ilan.isEsyaliMi() ? "Evet" : "Hayır", EsyaliE, EsyaliH)) return false;
        if (!secimeUygun(ilan.getKullanimDurumu(), kdb, kdK, kdM)) return false;
        if (!secimeUygun(ilan.isSiteIcerisindeMi() ? "Evet" : "Hayır", siE, siH)) return false;
        if (!secimeUygun(ilan.getTapuDurumu(), tdKatMulkiyeti, tdHisseliTapu, tdArsaTapulu, tdYurtDisiTapulu, tdTapuKaydiYok)) return false;
        RadioButton kimden = (RadioButton) kimdenToggleGroup.getSelectedToggle();
        return kimden == null || kimden.getText().equals(ilan.getKimden());
    }

    private boolean secimeUygun(String deger, CheckBox... secenekler) {
        boolean secimVar = false;
        for (CheckBox secenek : secenekler) {
            if (secenek.isSelected()) {
                secimVar = true;
                if (secenek.getText().equals(deger)) return true;
            }
        }
        return !secimVar;
    }

    private double sinirOku(TextField alan, double varsayilan) {
        if (alan.getText().trim().isEmpty()) return varsayilan;
        double deger = Double.parseDouble(alan.getText().trim().replace(',', '.'));
        if (!Double.isFinite(deger) || deger < 0) throw new IllegalArgumentException("Filtrelerde geçerli bir sayı giriniz.");
        return deger;
    }

    @FXML
    private void btnTemizle() {
        CheckBox[] secenekler = {
            adresAkcadag, adresArapgir, adresArguvan, adresBattalgazi, adresDarende, adresDogansehir,
            adresDoganyol, adresHekimhan, adresKale, adresKuluncak, adresPuturge, adresYazihan,
            adresYesilyurt, oda1Plus1CheckBox, oda1Point5Plus1CheckBox, oda2Plus0CheckBox, oda2Plus1CheckBox, oda2Point5Plus1CheckBox,
            oda2Plus2CheckBox, oda3Plus0CheckBox, oda3Plus1CheckBox, oda3Point5Plus1CheckBox, oda3Plus2CheckBox, oda3Plus3CheckBox,
            oda4Plus0CheckBox, oda4Plus1CheckBox, BinaYasi0, BinaYasi1, BinaYasi2, BinaYasi3,
            BinaYasi4, BinaYasi5Plus, bkBodrum, bkZemin, bkBahceKati, bkGirisKati,
            bkMustakil, bkCatiKati, bk1, bk2, bk3, bk4,
            bk5, bk6, bk7, bk8, ks1, ks2,
            ks3, ks4, ks5, ks6, ks7, ks8,
            isitmaYOK, isitmaSoba, isitmaDoğalgazSobasi, isitmaKatKaloriferi, isitmaMerkezi, isitmaKombi_dogalgaz,
            isitmaKombi_elektrik, isitmaYerden, bs1, bs2, bs3, MutfakA,
            MutfakK, BalkonV, BalkonY, AsansorV, AsansorY, EsyaliE,
            EsyaliH, kdb, kdK, kdM, siE, siH,
            tdKatMulkiyeti, tdHisseliTapu, tdArsaTapulu, tdYurtDisiTapulu, tdTapuKaydiYok
        };
        for (CheckBox secenek : secenekler) secenek.setSelected(false);
        kimdenToggleGroup.selectToggle(null);
        ilanTarihiToggleGroup.selectToggle(null);
        searchField.clear();
        fiyatMinField.clear();
        fiyatMaxField.clear();
        MetrekareMinField.clear();
        MetrekareMaxField.clear();
        secimTemizleniyor = true;
        konutSatilikList.getSelectionModel().clearSelection();
        konutKiralikList.getSelectionModel().clearSelection();
        isYeriSatilikList.getSelectionModel().clearSelection();
        isYeriKiralikList.getSelectionModel().clearSelection();
        secimTemizleniyor = false;
        secilenKategori = "";
        secilenTur = "";
        secilenAltKategori = "";
        btnAra();
    }

    public void addListing(Listing ilan) throws IOException {
        if (!verilerYuklendi) throw new IOException("Kayıt dosyası okunamadığı için işlem yapılamıyor.");
        HashMap<String, Listing> yeniListe = new HashMap<>(listings);
        yeniListe.put(ilan.getId(), ilan);
        dataManager.saveData(yeniListe);
        listings = yeniListe;
        fiyatListesiniOlustur();
        btnAra();
    }

    public void updateListingData(String id, Listing ilan) throws IOException {
        if (!listings.containsKey(id)) throw new IOException("Güncellenecek ilan bulunamadı.");
        ilan.setId(id);
        addListing(ilan);
    }

    @FXML private void btnEkle() { formAc(null); }
    @FXML private void btnGuncelle() {
        Listing secilen = secilenIlan();
        if (secilen != null) formAc(secilen);
    }

    private void formAc(Listing ilan) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("veriEkle_Guncelle.fxml"));
            Parent root = loader.load();
            VeriEkleGuncelleController controller = loader.getController();
            controller.setMainController(this);
            if (ilan != null) controller.loadListingForUpdate(ilan);
            Main.openWindow(mainRoot.getScene().getWindow(), "İlan Ekle / Güncelle", root).showAndWait();
        } catch (IOException e) { uyari(e.getMessage()); }
    }

    @FXML private void btnBilgi() {
        Listing secilen = secilenIlan();
        if (secilen != null) bilgiGoster(secilen);
    }

    private void bilgiGoster(Listing ilan) {
        if (ilan == null) return;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("info.fxml"));
            Parent root = loader.load();
            InfoController controller = loader.getController();
            controller.setListing(ilan);
            Main.openWindow(mainRoot.getScene().getWindow(), ilan.getIlanBasligi(), root).showAndWait();
        } catch (IOException e) { uyari(e.getMessage()); }
    }

    @FXML private void btnSil() {
        Listing secilen = secilenIlan();
        if (secilen != null) ilanSil(secilen);
    }

    private void ilanSil(Listing ilan) {
        if (ilan == null) return;
        Alert onay = new Alert(Alert.AlertType.CONFIRMATION, "Seçili ilan silinsin mi?", ButtonType.YES, ButtonType.NO);
        onay.setHeaderText(null);
        if (onay.showAndWait().orElse(ButtonType.NO) != ButtonType.YES) return;
        try {
            HashMap<String, Listing> yeniListe = new HashMap<>(listings);
            yeniListe.remove(ilan.getId());
            dataManager.saveData(yeniListe);
            listings = yeniListe;
            fiyatListesiniOlustur();
            btnAra();
        } catch (IOException e) { uyari(e.getMessage()); }
    }

    private Listing secilenIlan() {
        Listing secilen = resultTableView.getSelectionModel().getSelectedItem();
        if (secilen == null) uyari("Önce tablodan bir ilan seçiniz.");
        return secilen;
    }

    @FXML private void btnKaydet() {
        if (!verilerYuklendi) { uyari("Kayıt dosyası okunamadığı için kaydedilemiyor."); return; }
        try {
            dataManager.saveData(listings);
            statusLabel.setText("Tüm ilanlar kaydedildi.");
        } catch (IOException e) { uyari(e.getMessage()); }
    }

    @FXML private void btnMusteriler() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("musteriler.fxml"));
            Main.openWindow(mainRoot.getScene().getWindow(), "Müşteriler", root).showAndWait();
        } catch (IOException e) { uyari(e.getMessage()); }
    }

    private void uyari(String mesaj) {
        Alert alert = new Alert(Alert.AlertType.WARNING, mesaj);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    @FXML private void closeWindow() { ((Stage) mainRoot.getScene().getWindow()).close(); }
    @FXML private void minimizeWindow() { ((Stage) mainRoot.getScene().getWindow()).setIconified(true); }
}


