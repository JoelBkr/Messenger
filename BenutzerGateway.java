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
}
