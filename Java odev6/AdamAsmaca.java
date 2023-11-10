// 10 Elemanlı bir şehir isimlerinden oluşan bir dizi tanımlayınız. Programın rastgele listeden bir şehir seçip karakter sayısı kadar
// _ _ _ _ _ _  oluşturan kullanıcı tarafından tahmin edilmeye çalışan Adam Asmaca programın Java programını yazınız.
import java.util.Random;
import java.util.Scanner;

public class AdamAsmaca {

    // Oyun için şehir isimlerini içeren bir dizi
    private static final String[] CITIES = {"Ankara", "İstanbul", "İzmir", "Bursa", "Adana", "Antalya", "Trabzon", "Eskişehir", "Konya", "Samsun"};

    public static void main(String[] args) {
        Random random = new Random();
        // Diziden rastgele bir şehir seçilir
        String selectedcity = CITIES[random.nextInt(CITIES.length)].toLowerCase();
        int wordLenght = selectedcity.length();
        char[] secretWord = new char[wordLenght];

        // Gizli kelimenin her harfi için '_' karakteriyle başlatılır
        for (int i = 0; i < wordLenght; i++) {
            secretWord[i] = '_';
        }

        Scanner scanner = new Scanner(System.in);
        int point = 100;

        System.out.println("Adam Asmaca oyununa hoş geldiniz!");

        // Oyunun başlama zamanı
        long startTime = System.currentTimeMillis();

        // Oyun döngüsü
        while (true) {
            System.out.println("Gizli kelime: " + new String(secretWord));
            System.out.print("Harf tahmini girin: ");
            String guess = scanner.nextLine().toLowerCase();

            // Kullanıcının geçersiz girişi kontrol edilir
            if (guess.length() != 1 || !Character.isLetter(guess.charAt(0))) {
                System.out.println("Geçersiz giriş. Lütfen bir harf girin.");
                continue;
            }

            char character = guess.charAt(0);

            // Kullanıcının tahmininin doğru olup olmadığı kontrol edilir
            if (selectedcity.indexOf(character) != -1) {
                for (int i = 0; i < wordLenght; i++) {
                    if (selectedcity.charAt(i) == character) {
                        secretWord[i] = character;
                    }
                }

                // Kullanıcı kelimeyi tamamen doğru tahmin ettiğinde oyunu bitirir
                if (new String(secretWord).equals(selectedcity)) {
                    System.out.println("Tebrikler! Doğru şehri buldunuz: " + selectedcity);
                    break;
                }
            } else {
                // Yanlış tahmin durumunda puan düşer
                System.out.println("Yanlış tahmin. Puanınız düşüyor.");
                point -= 10;
            }

            // Geçen süreye göre puanlama yapılır
            long passingTime = (System.currentTimeMillis() - startTime) / 1000;

            // Puanlama, geçen süreye göre yapılır
            if (passingTime <= 20) {
                point += 5;
            } else if (passingTime <= 30) {
                point -= 10;
            }

            // Puan sıfır veya altına düşerse oyunu kaybeder
            if (point <= 0) {
                System.out.println("Üzgünüm, puanınız sıfıra düştü. Oyunu kaybettiniz.");
                break;
            }

            // Oyunun durumu ekrana yazdırılır
            System.out.println("Geçen süre: " + passingTime + " saniye");
            System.out.println("Puanınız: " + point);
        }

        // Scanner kapatılır
        scanner.close();
    }
}

