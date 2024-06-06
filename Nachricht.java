
/**
 * Beschreiben Sie hier die Klasse Nachricht.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Nachricht
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private String inhalt;
    private String absender;
    /**
     * Konstruktor fuer Objekte der Klasse Nachricht
     */
    public Nachricht(String inhalt, String absender)
    {
        this.inhalt = inhalt;
        this.absender = absender;
    }

    /**
     * Diese Methode gibt den Inhalt einer Nachricht zurück
     * @author:
     * @version:  04.06.2024
     * @return: Inhalt der Nachricht
     */
    public String gibInhalt()
    {
        return inhalt;
    }
}
