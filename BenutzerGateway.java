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
        verbinde();
        List <Benutzer> b = new List();
        db.executeStatement("Select * from benutzer");
        QueryResult ergebnis = db.getCurrentQueryResult();
        if(ergebnis != null)
        {
            for(int i = 0; i < ergebnis.getRowCount(); i++)
            {
                b.append(new Benutzer(ergebnis.getData()[i][0], ergebnis.getData()[i][1], true));
            }
        }
        beende();
        return b;
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
        verbinde();
        db.executeStatement("INSERT INTO benutzer (name, passwort) VALUES ('"+name+"', '"+passwort+"')");
        beende();
    }
     /**
     * Diese Methode erzeugt die Tabelle highscore, wenn diese nicht schon exisitiert.
     */
    public void erzeugeTabelle()
    {
         verbinde();
         db.executeStatement("Create table if not exists benutzer (name text PRIMARY KEY, passwort text)");
         beende();
    }
    
    /**
     * Diese Methode stellt eine Verbindung zur Datenbank her.
     */
    private void verbinde()
    {
        if(db == null)
        {
            db = new DatabaseConnector("",0,"DB","","");
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
