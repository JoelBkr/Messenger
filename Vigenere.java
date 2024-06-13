import java.io.*;

/**
 * Die Klasse Vigenere implementiert das Kryptomodul-Interface und
 * bietet Methoden zur Vigenère-Verschlüsselung und -Entschlüsselung von Texten.
 * Sie beinhaltet Methoden zum Laden und Speichern des Schlüssels.
 * 
 * @version 1.0
 */
public class Vigenere implements Kryptomodul {
    private String key; // Schlüssel für die Vigenère-Verschlüsselung
    private final String KEY_FILE = "vigenere_key.txt"; // Datei, in der der Schlüssel gespeichert wird

    /**
     * Konstruktor der Klasse Vigenere.
     * Versucht, einen vorhandenen Schlüssel zu laden. Falls nicht vorhanden, wird ein Standard-Schlüssel verwendet.
     */
    public Vigenere() {
        loadKey();
        if (key == null) {
            key = "DEFAULTKEY"; // Standard-Schlüssel, falls kein Schlüssel geladen werden konnte
        }
    }

    /**
     * Verschlüsselt den gegebenen Klartext mit dem Vigenère-Schlüssel.
     * 
     * @param klartext Der zu verschlüsselnde Klartext.
     */
    @Override
    public void verschluesseln(String klartext) {
        String encryptedText = vigenereEncrypt(klartext, key);
        System.out.println("Verschlüsselter Text: " + encryptedText);
    }

    /**
     * Entschlüsselt den gegebenen geheimen Text mit dem Vigenère-Schlüssel.
     * 
     * @param geheimtext Der zu entschlüsselnde Text.
     */
    @Override
    public void entschluesseln(String geheimtext) {
        String decryptedText = vigenereDecrypt(geheimtext, key);
        System.out.println("Entschlüsselter Text: " + decryptedText);
    }

    /**
     * Lädt den Vigenère-Schlüssel aus einer Datei.
     * Wenn die Datei nicht existiert oder ein Fehler auftritt, wird kein Schlüssel geladen.
     */
    @Override
    public void loadKey() {
        try (BufferedReader reader = new BufferedReader(new FileReader(KEY_FILE))) {
            key = reader.readLine();
        } catch (FileNotFoundException e) {
            System.out.println("Key file not found, using default key.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Speichert den Vigenère-Schlüssel in einer Datei.
     */
    @Override
    public void saveKey() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(KEY_FILE))) {
            writer.write(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Verschlüsselt den Text mit dem Vigenère-Verfahren.
     * 
     * @param text Der zu verschlüsselnde Text.
     * @param key Der Schlüssel zur Verschlüsselung.
     * @return Der verschlüsselte Text.
     */
    private String vigenereEncrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        key = key.toUpperCase();
        text = text.toUpperCase();
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c < 'A' || c > 'Z') continue; // Überspringt Nicht-Buchstaben
            result.append((char)((c + key.charAt(j) - 2 * 'A') % 26 + 'A'));
            j = ++j % key.length();
        }
        return result.toString();
    }

    /**
     * Entschlüsselt den Text mit dem Vigenère-Verfahren.
     * 
     * @param text Der zu entschlüsselnde Text.
     * @param key Der Schlüssel zur Entschlüsselung.
     * @return Der entschlüsselte Text.
     */
    private String vigenereDecrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        key = key.toUpperCase();
        text = text.toUpperCase();
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c < 'A' || c > 'Z') continue; // Überspringt Nicht-Buchstaben
            result.append((char)((c - key.charAt(j) + 26) % 26 + 'A'));
            j = ++j % key.length();
        }
        return result.toString();
    }

    /**
     * Hauptmethode zum Testen der Vigenère-Verschlüsselung und -Entschlüsselung.
     * 
     * @param args Kommandozeilenargumente (nicht verwendet).
     */
    public static void main(String[] args) {
        Vigenere vigenere = new Vigenere(); // Erstellt eine Instanz der Klasse Vigenere
        String klartext = "Hallo, dies ist ein Test."; // Definiert den Klartext
        System.out.println("Klartext: " + klartext); // Gibt den Klartext aus
        vigenere.verschluesseln(klartext); // Verschlüsselt den Klartext
        
        String geheimtext = "..."; // Hier den verschlüsselten Text einfügen
        vigenere.entschluesseln(geheimtext); // Entschlüsselt den geheimen Text
    }
}
