/**
 * Diese Klasse setzt das Entwurfsmuster DataTableGateway um. Dabei stellt es alle Datenbankrelevanten Funktionen, die die Anwendung benötigt 
 * zur Verfügung. Erweiterungen und Einschränkungen sind möglich.
 * 
 * @author Henning Ainödhofer
 * @version 10.04.2018
 */
public class BenutzerGateway
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private DatabaseConnector db;
    private List<Benutzer> bList;

    /**
     * Konstruktor für Objekte der Klasse HighscoreGateway
     */
    public BenutzerGateway()
    {
        // Instanzvariable initialisieren
        db = null;
    }
    /**
     * Diese Methode gibt die Liste aller Benutzer zurück, die im System eingetragen sind
     * @author:
     * @version: 04.06.2024
     * @return: Liste der Benutzer
     */

    public List gibBenutzerListe()
    {
        return bList;
    }
    /**
     * Diese Methode fügt der Datenbank einen neuen Benutzer hinzu
     * 
     * @author:
     * @version: 06.06.2024
     * @param: Neuer Benutzer
     */
    public void neuerBenutzer(String name, String passwort)
    {
        Benutzer neu = new Benutzer(name, passwort, true);
        bList.append(neu);
        verbinde();
        db.executeStatement("INSERT INTO benutzer (name, passwort, status) VALUES ('"+name+"', '"+passwort+"', ");
        beende();
    }
     /**
     * Diese Methode erzeugt die Tabelle highscore, wenn diese nicht schon exisitiert.
     */
    public void erzeugeTabelle()
    {
         verbinde();
         db.executeStatement("Create table if not exists benutzer (id INTEGER PRIMARY KEY AUTOINCREMENT, name text, passwort text, status boolean)");
         beende();
    }
    
    /**
     * Diese Methode stellt eine Verbindung zur Datenbank her.
     */
    private void verbinde()
    {
        if(db == null)
        {
            db = new DatabaseConnector("",0,"BenutzerDB","","");
        }
    }
    
    /**
     * Diese Methode beendet die Verbindung zur Datenbank.
     */
    private void beende()
    {
        if(db != null)
        {
            db.close();
            db = null;
        }
    }
}
