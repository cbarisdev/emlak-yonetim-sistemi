# Emlak Yönetim Sistemi

JavaFX ve FXML ile geliştirilmiş masaüstü emlak yönetim uygulaması.

Projenin ilk sürümü 17 Aralık 2024 tarihinde geliştirilmiştir.

## Özellikler

- İlan ekleme, güncelleme, silme ve detay görüntüleme
- İlan özelliklerine göre filtreleme ve fiyat sıralaması
- İlanlara iki resim ekleme ve resimler arasında geçiş
- Müşteri kayıtlarını ekleme, güncelleme, silme ve arama

Veriler veritabanı yerine `data.hot` ve `musteri.hot` adlı metin dosyalarında saklanır. İlanlar bellekte HashMap ile tutulur; fiyat sıralamasında TreeMap kullanılır. Kaydetme sırasında bütün kayıtlar dosyaya yeniden yazılır.

## Çalıştırma

JDK 23 veya daha yenisi gereklidir.

1. IntelliJ IDEA'da `pom.xml` dosyasını proje olarak açın.
2. Proje JDK'sını seçin ve Maven bağımlılıklarının yüklenmesini bekleyin.
3. `Main.java` sınıfını çalıştırın.

Alternatif: `mvn javafx:run`

Örnek giriş bilgileri: **admin / 123**.

Kayıt dosyaları ilk kaydetmede oluşur. Sayılarda binlik ayraç kullanmayın; ondalık ayraç olarak nokta veya virgül yazabilirsiniz. Metin alanlarında dosya ayıracı olan `|` karakterini kullanmayın.
