import java.io.*;
import java.security.*;
import java.util.Base64;
import javax.crypto.Cipher;

/**
 * Die Klasse RSAverschluesselung implementiert das Kryptomodul-Interface und
 * bietet Methoden zur RSA-Verschlüsselung und -Entschlüsselung von Texten.
 * Sie beinhaltet Methoden zum Laden und Speichern von Schlüsseln.
 * 
 * @version 1.0
 */
public class RSAverschluesselung implements Kryptomodul {
    private KeyPair keyPair; // Schlüsselpaar für RSA-Verschlüsselung
    private final String KEY_FILE = "rsa_keypair.ser"; // Datei, in der das Schlüsselpaar gespeichert wird

    /**
     * Konstruktor der Klasse RSAverschluesselung.
     * Versucht, ein vorhandenes Schlüsselpaar zu laden. Falls nicht vorhanden, wird ein neues Schlüsselpaar generiert.
     */
    public RSAverschluesselung() {
        loadKey();
        if (keyPair == null) {
            generateKeyPair();
        }
    }

    /**
     * Verschlüsselt den gegebenen Klartext und gibt den verschlüsselten Text als Base64-codierten String aus.
     * 
     * @param klartext Der zu verschlüsselnde Klartext.
     */
    @Override
    public void verschluesseln(String klartext) {
        try {
            Cipher cipher = Cipher.getInstance("RSA"); // Erstellt eine Cipher-Instanz für RSA
            cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic()); // Initialisiert die Cipher im Verschlüsselungsmodus mit dem öffentlichen Schlüssel
            byte[] encryptedBytes = cipher.doFinal(klartext.getBytes()); // Verschlüsselt den Klartext
            String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes); // Kodiert die verschlüsselten Bytes in Base64
            System.out.println("Verschlüsselter Text: " + encryptedText); // Gibt den verschlüsselten Text aus
        } catch (Exception e) {
            e.printStackTrace(); // Gibt den Fehlerstack bei einer Ausnahme aus
        }
    }

    /**
     * Entschlüsselt den gegebenen geheimen Text und gibt den entschlüsselten Text aus.
     * 
     * @param geheimtext Der zu entschlüsselnde Text (Base64-codiert).
     */
    @Override
    public void entschluesseln(String geheimtext) {
        try {
            Cipher cipher = Cipher.getInstance("RSA"); // Erstellt eine Cipher-Instanz für RSA
            cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate()); // Initialisiert die Cipher im Entschlüsselungsmodus mit dem privaten Schlüssel
            byte[] decodedBytes = Base64.getDecoder().decode(geheimtext); // Dekodiert den Base64-codierten geheimen Text
            byte[] decryptedBytes = cipher.doFinal(decodedBytes); // Entschlüsselt die Bytes
            String decryptedText = new String(decryptedBytes); // Wandelt die entschlüsselten Bytes in einen String um
            System.out.println("Entschlüsselter Text: " + decryptedText); // Gibt den entschlüsselten Text aus
        } catch (Exception e) {
            e.printStackTrace(); // Gibt den Fehlerstack bei einer Ausnahme aus
        }
    }

    /**
     * Lädt das Schlüsselpaar aus einer Datei.
     * Wenn die Datei nicht existiert oder ein Fehler auftritt, wird kein Schlüsselpaar geladen.
     */
    @Override
    public void loadKey() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(KEY_FILE))) {
            keyPair = (KeyPair) ois.readObject(); // Liest das Schlüsselpaar aus der Datei
        } catch (FileNotFoundException e) {
            System.out.println("Key file not found, generating a new key pair."); // Meldung, wenn die Datei nicht gefunden wird
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(); // Gibt den Fehlerstack bei einer Ausnahme aus
        }
    }

    /**
     * Speichert das Schlüsselpaar in einer Datei.
     */
    @Override
    public void saveKey() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(KEY_FILE))) {
            oos.writeObject(keyPair); // Schreibt das Schlüsselpaar in die Datei
        } catch (IOException e) {
            e.printStackTrace(); // Gibt den Fehlerstack bei einer Ausnahme aus
        }
    }

    /**
     * Generiert ein neues Schlüsselpaar für die RSA-Verschlüsselung und speichert es.
     */
    private void generateKeyPair() {
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA"); // Erstellt einen KeyPairGenerator für RSA
            keyPairGen.initialize(2048); // Initialisiert den KeyPairGenerator mit einer Schlüssellänge von 2048 Bit
            keyPair = keyPairGen.generateKeyPair(); // Generiert das Schlüsselpaar
            saveKey(); // Speichert das generierte Schlüsselpaar
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace(); // Gibt den Fehlerstack bei einer Ausnahme aus
        }
    }

    /**
     * Hauptmethode zum Testen der RSA-Verschlüsselung und -Entschlüsselung.
     * 
     * @param args Kommandozeilenargumente (nicht verwendet).
     */
    public static void main(String[] args) {
        RSAverschluesselung rsa = new RSAverschluesselung(); // Erstellt eine Instanz der Klasse RSAverschluesselung
        String klartext = "Hallo, dies ist ein Test."; // Definiert den Klartext
        System.out.println("Klartext: " + klartext); // Gibt den Klartext aus
        rsa.verschluesseln(klartext); // Verschlüsselt den Klartext
        
        String geheimtext = "..."; // Hier den verschlüsselten Text einfügen
        rsa.entschluesseln(geheimtext); // Entschlüsselt den geheimen Text
    }
}
