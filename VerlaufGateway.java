
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
    public List<Nachricht> gibVerlaufListe()
    {
        verbinde();
        List <Nachricht> n = new List();
        db.executeStatement("Select inhalt, name from verlauf");
        QueryResult ergebnis = db.getCurrentQueryResult();
        if(ergebnis != null)
        {
            for(int i = 0; i < ergebnis.getRowCount(); i++)
            {
                n.append(new Nachricht(ergebnis.getData()[i][0], ergebnis.getData()[i][1]));
            }
        }
        beende();
        return n;
    }

    /**
     * Diese Methode fügt eine neue Nachricht dem Verlauf hinzu
     * 
     * @author:
     * @version: 06.06.2024
     * @param: neue Nachricht
     */
    public void neueNachricht(String nachricht, String name)
    {
        Nachricht neu = new Nachricht(nachricht, name);
        nList.append(neu);
        verbinde();
        db.executeStatement("INSERT INTO verlauf (inhalt, name) VALUES ('"+nachricht+"', '"+name+"'");
        beende();
    }

    /**
     * Diese Methode erzeugt die Tabelle highscore, wenn diese nicht schon exisitiert.
     */
    public void erzeugeTabelle()
    {
        verbinde();
        db.executeStatement("Create table if not exists verlauf (id INTEGER PRIMARY KEY AUTOINCREMENT, inhalt text, name text FOREIGN KEY(name) REFERENCE benutzer(name))");
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

    // Dienste
}
