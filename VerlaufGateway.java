
/**
 * @author 
 * @version 
 */
public class VerlaufGateway
{
    private DatabaseConnector db;
    private List <Nachricht> nList;
 
    public VerlaufGateway()
    {
        db = null;

    }
     /**
     * Diese Methode gibt die Liste der Nachrichten zurück, die in der Vergangenheit gesendet worden
     * @author:
     * @version: 04.06.2024
     * @return: Liste der Nachrichten
     */
    public List gibVerlaufListe()
    {
        return nList;
    }
    /**
     * Diese Methode fügt eine neue Nachricht dem Verlauf hinzu
     * 
     * @author:
     * @version: 06.06.2024
     * @param: neue Nachricht
     */
    public void neueNachricht(String nachricht)
    {
        Nachricht neu = new Nachricht(nachricht);
        nList.append(neu);
        verbinde();
        db.executeStatement("INSERT INTO verlauf (inhalt) VALUES ('"+nachricht+"'");
        beende();
    }
     /**
     * Diese Methode erzeugt die Tabelle highscore, wenn diese nicht schon exisitiert.
     */
    public void erzeugeTabelle()
    {
         verbinde();
         db.executeStatement("Create table if not exists verlauf (id INTEGER PRIMARY KEY AUTOINCREMENT, inhalt text, benutzerID)");
         beende();
    }
    
    /**
     * Diese Methode stellt eine Verbindung zur Datenbank her.
     */
    private void verbinde()
    {
        if(db == null)
        {
            db = new DatabaseConnector("",0,"VerlaufDB","","");
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
    

    // Dienste

}
