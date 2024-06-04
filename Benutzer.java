
/**
 * Beschreiben Sie hier die Klasse benutzer.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Benutzer
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private String name, passwort;
    private boolean angemeldet;

    /**
     * Konstruktor fuer Objekte der Klasse benutzer
     */
    public Benutzer(String name, String passwort, boolean angemeldet)
    {
        this.name = name;
        this.passwort = passwort;
        this.angemeldet = angemeldet;
    }

    /**
     * Diese Methode gibt den Namen eines Benutzers zurück
     * 
     * @author
     * @version 04.06.2024
     * @return Name des Benutzers
     */
    public String gibName()
    {
        return name;
    }
    /**
     * Diese Methode gibt das Passwort eines Benutzers zurück
     * 
     * @author: 
     * @version: 04.06.2024
     * @return: Passwort des Benutzers
     */
    public String gibPasswort()
    {
        return passwort;
    }
    /**
     * Diese Methode gibt zurück, ob ein Benutzer angemeldet ist
     * 
     * @author:
     * @version: 04.06.2024
     * @return: Online-Status
     */
    public boolean gibAngemeldet()
    {
        return angemeldet;
    }
    /**
     * Diese Methode verändert den Namen eines Benutzers
     * 
     * @author:
     * @version: 04.06.2024
     * @param: Neuer Name
     */
    public void setzeName(String name)
    {
        this.name = name;
    }
    /**
     * Diese Methode verändert das Passwort eines Benutzers
     * 
     * @author:
     * @version: 04.06.2024
     * @param: Neues Passwort
     */
    public void setzePasswort(String passwort)
    {
        this.passwort = passwort;
    }
    /**
     * Diese Methode verändert den Online Status eines Benutzers
     * @author:
     * @version: 04.06.2024
     * @param: Neuer Online-Status
     */
    public void setzeAngemeldet(boolean angemeldet)
    {
        this.angemeldet = angemeldet;
    }
}
