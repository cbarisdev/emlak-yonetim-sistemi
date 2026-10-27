package emlak;

import javafx.beans.property.*;
import java.time.LocalDate;

public class Listing {
    private StringProperty id;
    private StringProperty ilanBasligi;
    private DoubleProperty metrekare;
    private StringProperty odaSayisi;
    private DoubleProperty fiyat;
    private ObjectProperty<LocalDate> ilanTarihi;
    private StringProperty ilce;

    // Ek Alanlar
    private StringProperty ilanTuru;
    private StringProperty kategori;
    private StringProperty altKategori;
    private IntegerProperty binaYasi;
    private StringProperty bulunduguKat;
    private IntegerProperty katSayisi;
    private StringProperty isitma;
    private IntegerProperty banyoSayisi;
    private StringProperty mutfak;
    private StringProperty balkon;
    private BooleanProperty asansorVarMi;
    private BooleanProperty esyaliMi;
    private StringProperty kullanimDurumu;
    private BooleanProperty siteIcerisindeMi;
    private StringProperty tapuDurumu;
    private StringProperty kimden;
    private StringProperty adres;
    private StringProperty resim1;
    private StringProperty resim2;

    // Constructor
    public Listing(String id, String ilanBasligi, double metrekare, String odaSayisi, double fiyat, LocalDate ilanTarihi, String ilce,
                   String ilanTuru, String kategori, String altKategori, int binaYasi, String bulunduguKat, int katSayisi,
                   String isitma, int banyoSayisi, String mutfak, String balkon, boolean asansorVarMi, boolean esyaliMi,
                   String kullanimDurumu, boolean siteIcerisindeMi, String tapuDurumu, String kimden, String adres,
                   String resim1, String resim2) {
        this.id = new SimpleStringProperty(id);
        this.ilanBasligi = new SimpleStringProperty(ilanBasligi);
        this.metrekare = new SimpleDoubleProperty(metrekare);
        this.odaSayisi = new SimpleStringProperty(odaSayisi);
        this.fiyat = new SimpleDoubleProperty(fiyat);
        this.ilanTarihi = new SimpleObjectProperty<>(ilanTarihi);
        this.ilce = new SimpleStringProperty(ilce);

        this.ilanTuru = new SimpleStringProperty(ilanTuru);
        this.kategori = new SimpleStringProperty(kategori);
        this.altKategori = new SimpleStringProperty(altKategori);
        this.binaYasi = new SimpleIntegerProperty(binaYasi);
        this.bulunduguKat = new SimpleStringProperty(bulunduguKat);
        this.katSayisi = new SimpleIntegerProperty(katSayisi);
        this.isitma = new SimpleStringProperty(isitma);
        this.banyoSayisi = new SimpleIntegerProperty(banyoSayisi);
        this.mutfak = new SimpleStringProperty(mutfak);
        this.balkon = new SimpleStringProperty(balkon);
        this.asansorVarMi = new SimpleBooleanProperty(asansorVarMi);
        this.esyaliMi = new SimpleBooleanProperty(esyaliMi);
        this.kullanimDurumu = new SimpleStringProperty(kullanimDurumu);
        this.siteIcerisindeMi = new SimpleBooleanProperty(siteIcerisindeMi);
        this.tapuDurumu = new SimpleStringProperty(tapuDurumu);
        this.kimden = new SimpleStringProperty(kimden);
        this.adres = new SimpleStringProperty(adres);
        this.resim1 = new SimpleStringProperty(resim1);
        this.resim2 = new SimpleStringProperty(resim2);
    }

    // Getters ve Setters
    public String getId() { return id.get(); }
    public StringProperty idProperty() { return id; }
    public void setId(String id) { this.id.set(id); }

    public String getIlanBasligi() { return ilanBasligi.get(); }
    public StringProperty ilanBasligiProperty() { return ilanBasligi; }
    public void setIlanBasligi(String ilanBasligi) { this.ilanBasligi.set(ilanBasligi); }

    public double getMetrekare() { return metrekare.get(); }
    public DoubleProperty metrekareProperty() { return metrekare; }
    public void setMetrekare(double metrekare) { this.metrekare.set(metrekare); }

    public String getOdaSayisi() { return odaSayisi.get(); }
    public StringProperty odaSayisiProperty() { return odaSayisi; }
    public void setOdaSayisi(String odaSayisi) { this.odaSayisi.set(odaSayisi); }

    public double getFiyat() { return fiyat.get(); }
    public DoubleProperty fiyatProperty() { return fiyat; }
    public void setFiyat(double fiyat) { this.fiyat.set(fiyat); }

    public LocalDate getIlanTarihi() { return ilanTarihi.get(); }
    public ObjectProperty<LocalDate> ilanTarihiProperty() { return ilanTarihi; }
    public void setIlanTarihi(LocalDate ilanTarihi) { this.ilanTarihi.set(ilanTarihi); }

    public String getIlce() { return ilce.get(); }
    public StringProperty ilceProperty() { return ilce; }
    public void setIlce(String ilce) { this.ilce.set(ilce); }

    // Ek alanlar için Getters ve Setters
    public String getIlanTuru() { return ilanTuru.get(); }
    public StringProperty ilanTuruProperty() { return ilanTuru; }
    public void setIlanTuru(String ilanTuru) { this.ilanTuru.set(ilanTuru); }

    public String getKategori() { return kategori.get(); }
    public StringProperty kategoriProperty() { return kategori; }
    public void setKategori(String kategori) { this.kategori.set(kategori); }

    public String getAltKategori() { return altKategori.get(); }
    public StringProperty altKategoriProperty() { return altKategori; }
    public void setAltKategori(String altKategori) { this.altKategori.set(altKategori); }

    public int getBinaYasi() { return binaYasi.get(); }
    public IntegerProperty binaYasiProperty() { return binaYasi; }
    public void setBinaYasi(int binaYasi) { this.binaYasi.set(binaYasi); }

    public String getBulunduguKat() { return bulunduguKat.get(); }
    public StringProperty bulunduguKatProperty() { return bulunduguKat; }
    public void setBulunduguKat(String bulunduguKat) { this.bulunduguKat.set(bulunduguKat); }

    public int getKatSayisi() { return katSayisi.get(); }
    public IntegerProperty katSayisiProperty() { return katSayisi; }
    public void setKatSayisi(int katSayisi) { this.katSayisi.set(katSayisi); }

    public String getIsitma() { return isitma.get(); }
    public StringProperty isitmaProperty() { return isitma; }
    public void setIsitma(String isitma) { this.isitma.set(isitma); }

    public int getBanyoSayisi() { return banyoSayisi.get(); }
    public IntegerProperty banyoSayisiProperty() { return banyoSayisi; }
    public void setBanyoSayisi(int banyoSayisi) { this.banyoSayisi.set(banyoSayisi); }

    public String getMutfak() { return mutfak.get(); }
    public StringProperty mutfakProperty() { return mutfak; }
    public void setMutfak(String mutfak) { this.mutfak.set(mutfak); }

    public String getBalkon() { return balkon.get(); }
    public StringProperty balkonProperty() { return balkon; }
    public void setBalkon(String balkon) { this.balkon.set(balkon); }

    public boolean isAsansorVarMi() { return asansorVarMi.get(); }
    public BooleanProperty asansorVarMiProperty() { return asansorVarMi; }
    public void setAsansorVarMi(boolean asansorVarMi) { this.asansorVarMi.set(asansorVarMi); }

    public boolean isEsyaliMi() { return esyaliMi.get(); }
    public BooleanProperty esyaliMiProperty() { return esyaliMi; }
    public void setEsyaliMi(boolean esyaliMi) { this.esyaliMi.set(esyaliMi); }

    public String getKullanimDurumu() { return kullanimDurumu.get(); }
    public StringProperty kullanimDurumuProperty() { return kullanimDurumu; }
    public void setKullanimDurumu(String kullanimDurumu) { this.kullanimDurumu.set(kullanimDurumu); }

    public boolean isSiteIcerisindeMi() { return siteIcerisindeMi.get(); }
    public BooleanProperty siteIcerisindeMiProperty() { return siteIcerisindeMi; }
    public void setSiteIcerisindeMi(boolean siteIcerisindeMi) { this.siteIcerisindeMi.set(siteIcerisindeMi); }

    public String getTapuDurumu() { return tapuDurumu.get(); }
    public StringProperty tapuDurumuProperty() { return tapuDurumu; }
    public void setTapuDurumu(String tapuDurumu) { this.tapuDurumu.set(tapuDurumu); }

    public String getKimden() { return kimden.get(); }
    public StringProperty kimdenProperty() { return kimden; }
    public void setKimden(String kimden) { this.kimden.set(kimden); }

    public String getAdres() { return adres.get(); }
    public StringProperty adresProperty() { return adres; }
    public void setAdres(String adres) { this.adres.set(adres); }

    public String getResim1() { return resim1.get(); }
    public StringProperty resim1Property() { return resim1; }
    public void setResim1(String resim1) { this.resim1.set(resim1); }

    public String getResim2() { return resim2.get(); }
    public StringProperty resim2Property() { return resim2; }
    public void setResim2(String resim2) { this.resim2.set(resim2); }

    // toString() metodu: Tüm alanları serileştir
    @Override
    public String toString() {
        return id.get() + "|" +
                ilanBasligi.get() + "|" +
                metrekare.get() + "|" +
                odaSayisi.get() + "|" +
                fiyat.get() + "|" +
                (ilanTarihi.get() != null ? ilanTarihi.get().toString() : "") + "|" +
                ilce.get() + "|" +
                ilanTuru.get() + "|" +
                kategori.get() + "|" +
                altKategori.get() + "|" +
                binaYasi.get() + "|" +
                bulunduguKat.get() + "|" +
                katSayisi.get() + "|" +
                isitma.get() + "|" +
                banyoSayisi.get() + "|" +
                mutfak.get() + "|" +
                balkon.get() + "|" +
                asansorVarMi.get() + "|" +
                esyaliMi.get() + "|" +
                kullanimDurumu.get() + "|" +
                siteIcerisindeMi.get() + "|" +
                tapuDurumu.get() + "|" +
                kimden.get() + "|" +
                adres.get() + "|" +
                resim1.get() + "|" +
                resim2.get();
    }

    // fromString() metodu: Serileştirilmiş veriden nesne oluşturma
    public static Listing fromString(String line) {
        String[] parts = line.split("\\|", -1);
        if (parts.length != 26) { // 7 Listing + 19 ekstra alan
            // Hatalı veri formatı
            throw new IllegalArgumentException("İlan kaydı 26 alan içermelidir.");
        }

        String id = parts[0];
        String ilanBasligi = parts[1];
        double metrekare = Double.parseDouble(parts[2]);
        String odaSayisi = parts[3];
        double fiyat = Double.parseDouble(parts[4]);
        LocalDate ilanTarihi = parts[5].isEmpty() ? LocalDate.now() : LocalDate.parse(parts[5]);
        String ilce = parts[6];
        String ilanTuru = parts[7];
        String kategori = parts[8];
        String altKategori = parts[9];
        int binaYasi = Integer.parseInt(parts[10]);
        String bulunduguKat = parts[11];
        int katSayisi = Integer.parseInt(parts[12]);
        String isitma = parts[13];
        int banyoSayisi = Integer.parseInt(parts[14]);
        String mutfak = parts[15];
        String balkon = parts[16];
        boolean asansorVarMi = Boolean.parseBoolean(parts[17]);
        boolean esyaliMi = Boolean.parseBoolean(parts[18]);
        String kullanimDurumu = parts[19];
        boolean siteIcerisindeMi = Boolean.parseBoolean(parts[20]);
        String tapuDurumu = parts[21];
        String kimden = parts[22];
        String adres = parts[23];
        String resim1 = parts[24];
        String resim2 = parts[25];

        return new Listing(id, ilanBasligi, metrekare, odaSayisi, fiyat, ilanTarihi, ilce,
                ilanTuru, kategori, altKategori, binaYasi, bulunduguKat, katSayisi,
                isitma, banyoSayisi, mutfak, balkon, asansorVarMi, esyaliMi,
                kullanimDurumu, siteIcerisindeMi, tapuDurumu, kimden, adres,
                resim1, resim2);
    }
}

