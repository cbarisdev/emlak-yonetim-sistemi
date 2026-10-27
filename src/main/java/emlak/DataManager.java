package emlak;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class DataManager {
    private final Path ilanDosyasi = Path.of("data.hot");
    private final Path musteriDosyasi = Path.of("musteri.hot");

    public HashMap<String, Listing> loadData() throws IOException {
        HashMap<String, Listing> ilanlar = new HashMap<>();
        if (!Files.exists(ilanDosyasi)) {
            return ilanlar;
        }

        try (BufferedReader okuyucu = Files.newBufferedReader(ilanDosyasi)) {
            String satir;
            int satirNo = 0;
            while ((satir = okuyucu.readLine()) != null) {
                satirNo++;
                if (satir.isBlank()) continue;
                try {
                    Listing ilan = Listing.fromString(satir);
                    if (ilanlar.containsKey(ilan.getId())) {
                        throw new IllegalArgumentException("Aynı ilan kimliği tekrar ediyor.");
                    }
                    ilanlar.put(ilan.getId(), ilan);
                } catch (IllegalArgumentException e) {
                    throw new IOException("data.hot dosyasında geçersiz kayıt, satır: " + satirNo, e);
                }
            }
        }
        return ilanlar;
    }

    public void saveData(HashMap<String, Listing> ilanlar) throws IOException {
        ArrayList<String> satirlar = new ArrayList<>();
        for (Listing ilan : ilanlar.values()) {
            String satir = ilan.toString();
            metniKontrolEt(satir, 26);
            satirlar.add(satir);
        }
        try (BufferedWriter yazici = Files.newBufferedWriter(ilanDosyasi)) {
            for (String satir : satirlar) {
                yazici.write(satir);
                yazici.newLine();
            }
        }
    }

    public HashMap<String, Musteri> loadCustomers() throws IOException {
        HashMap<String, Musteri> musteriler = new HashMap<>();
        if (!Files.exists(musteriDosyasi)) {
            return musteriler;
        }
        try (BufferedReader okuyucu = Files.newBufferedReader(musteriDosyasi)) {
            String satir;
            while ((satir = okuyucu.readLine()) != null) {
                if (satir.isBlank()) continue;
                try {
                    Musteri musteri = Musteri.fromString(satir);
                    if (musteriler.containsKey(musteri.getId())) {
                        throw new IllegalArgumentException("Aynı müşteri kimliği tekrar ediyor.");
                    }
                    musteriler.put(musteri.getId(), musteri);
                } catch (IllegalArgumentException e) {
                    throw new IOException("musteri.hot dosyasında geçersiz kayıt var.", e);
                }
            }
        }
        return musteriler;
    }

    public void saveCustomers(HashMap<String, Musteri> musteriler) throws IOException {
        ArrayList<String> satirlar = new ArrayList<>();
        for (Musteri musteri : musteriler.values()) {
            String satir = musteri.toString();
            metniKontrolEt(satir, 5);
            satirlar.add(satir);
        }
        try (BufferedWriter yazici = Files.newBufferedWriter(musteriDosyasi)) {
            for (String satir : satirlar) {
                yazici.write(satir);
                yazici.newLine();
            }
        }
    }

    private void metniKontrolEt(String satir, int alanSayisi) throws IOException {
        if (satir.contains("\n") || satir.contains("\r") || satir.split("\\|", -1).length != alanSayisi) {
            throw new IOException("Metin alanlarında | karakteri veya satır sonu kullanmayınız.");
        }
    }
}
