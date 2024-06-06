import java.net.*;

/**
 * Klasse fuer einen SpielServer. Der Spielserver bietet die Moeglickeit ein Spiel gegen den Server zu spielen. Bei dem Spiel muss man eine zuf�llige Zahl
 * zwischen 
 * @author Henning Ainoedhofer
 * @version 21.03.2017
 */

public class MServer extends Server {

    private BenutzerGateway bGateway;
    private List<Benutzer> bList;
    private VerlaufGateway vGateway;
    private List<Nachricht> nList;
    public MServer(int p) {
        super(p);
        bGateway = new BenutzerGateway();
        bList = new List<Benutzer>();
        vGateway = new VerlaufGateway();
        nList = new List<Nachricht>();
    }

    /**
     * Diese Methode der Server-Klasse wird hiermit ueberschrieben.
     * Der angemeldete Client bekommt die Meldung, dass er angenommen wurde.
     */

    public void processNewConnection(String pClientIP, int pClientPort){
        this.send(pClientIP, pClientPort, "CON erfolgreich");
    }

    /**
     * Hier wird die Fallunterscheidung behandelt, die im Protokoll festgelegt wurde.
     */
    public void processMessage(String pClientIP, int pClientPort, String pMessage){ 

        switch(wortAn(pMessage, 0)) {
               case "USR": {
                String name = wortAn(pMessage, 1);
                String passwort = wortAn(pMessage, 2);
                
                if(existiertBenutzer(name)) {
                    if(hatBenutzerRichtigesPasswort(name, passwort)) {
                        system.out.println("USR willkommen");
                        return vGateway.gibVerlaufListe();
                    }
                    else {
                        system.out.println("E01 falsche Anmeldedaten");
                    }
                }
                else {
                        system.out.println("E01 falsche Anmeldedaten");
                    }
            }

            case "REG": {
                String name = wortAn(pMessage, 1);
                String passwort = wortAn(pMessage, 2);

                if(existiertBenutzer(name) == false) {
                    neuerBenutzer(name, passwort);
                system.out.println("REG erfolgreich");
                return vGateway.gibVerlaufListe();
                } else {
                    system.out.println("E02 Benutzername schon vorhanden");
                }
            }

            case "MES": {
                String inhalt = gibTextbereich();

                if(inhalt.isEmpty == false) {
                    sytsem.out.println("MES Nachricht erhalten");
                } else {
                    sytsem.out.println("E03 Nachricht leer");
                }
            }
        }
    }

    /**
     * Diese Methode der Server-Klasse wird hiermit ueberschrieben.
     * Die Verbindung wird beendet und aus der Liste der Clients gestrichen.
     */
    public void processClosingConnection(String pClientIP, int pClientPort){
        this.send(pClientIP, pClientPort, "QUT erfolgreich");
        this.closeConnection(pClientIP, pClientPort);
    }

    /**
     * Main-Methode die den Server auf Port 1024 startet.
     */
    public static void main(String [] args)
    {
        MServer es = new MServer(2000);
    }

    /**
     * Diese Methode gibt den Befehl zurueck die die message beinhaltet
     * 
     * @param message
     * 
     * @return Befehl
     */
    private String gibBefehlsbereich(String message)
    {
        return message.split(" ")[0];
    }

    /**
     * Diese Methode gibt den Text zurueck die die message beinhaltet
     * 
     * @param message
     * 
     * @return Text
     */
    private String gibTextbereich(String message)
    {
        String [] messageArray = message.split(" ");
        String text = "";
        for(int i = 1; i < messageArray.length; i++)
        {
            text = text+" "+ messageArray[i];
        }
        return text;
    }

    /**
     * Diese Methode druckt die Higscoreliste auf der Konsole aus.
     * @param message
     */
    private String wortAn(String message, int stelle){
        String ergebnis = "";
        return ergebnis;
    }
    
    /**
     * Diese Methode holt den Nachrichtenverlauf aus der Datenbank.
     * @author
     * @return ergebnis mit Nachrichtenverlauf
     * @version 04.06.24
     */
    public List<Nachricht> holeNachrichtenAusDB() {
        List<Nachricht> ergebnis = null;
        return ergebnis;
    }
    
    /**
     * Diese Methode schaut in der DB ob dieser Benutzername schon einmal angemeldet war.
     * @author
     * @param name der Benutzername
     * return boolean ob er schon existiert
     * @version 04.06.24
     */
    public boolean existiertBenutzer(String name) {
        return false;
    }
    
    /**
     * Diese Methode schaut in der DB ob das Passort von diesem Benutzername richtig ist.
     * @author
     * @param name der Benutzername
     * @param passwort das Passwort
     * return boolean ob es richtig ist
     * @version 04.06.24
     */
    public boolean hatBenutzerRichtigesPasswort(String name, String passwort) {
        return false;
    }

    /**
     * Vielleicht nicht mehr notwendig
     * @author
     * @param name der Benutzername
     * @version 04.06.24
     */
    public boolean eintragErstellen(String name) {
        return false;
    }
    
    /**
     * Diese Methode legt einen neuen Benutzer in der DB an.
     * @author
     * @param name der Benutzername
     * @param passwort das Passwort
     * @version 04.06.24
     */
    public void neuerBenutzer(String name, String passwort) {
        bGateway.neuerBenutzer(name, passwort);
    }
}
