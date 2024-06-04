
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
    

    // Dienste

}
