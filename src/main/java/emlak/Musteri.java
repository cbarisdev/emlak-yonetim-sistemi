package emlak;

public class Musteri {
    private String id;
    private String adSoyad;
    private String telefon;
    private String eposta;
    private String not;

    public Musteri(String id, String adSoyad, String telefon, String eposta, String not) {
        this.id = id;
        this.adSoyad = adSoyad;
        this.telefon = telefon;
        this.eposta = eposta;
        this.not = not;
    }

    public String getId() { return id; }
    public String getAdSoyad() { return adSoyad; }
    public String getTelefon() { return telefon; }
    public String getEposta() { return eposta; }
    public String getNot() { return not; }

    @Override
    public String toString() {
        return id + "|" + adSoyad + "|" + telefon + "|" + eposta + "|" + not;
    }

    public static Musteri fromString(String satir) {
        String[] alanlar = satir.split("\\|", -1);
        if (alanlar.length != 5) {
            throw new IllegalArgumentException("Müşteri kaydı 5 alan içermelidir.");
        }
        return new Musteri(alanlar[0], alanlar[1], alanlar[2], alanlar[3], alanlar[4]);
    }
}
