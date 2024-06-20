import java.net.*;

/**
 * Klasse fuer einen SpielServer. Der Spielserver bietet die Moeglickeit ein
 * Spiel gegen den Server zu spielen. Bei dem Spiel muss man eine zufaellige Zahl
 * zwischen
 * 
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
        bGateway.erzeugeTabelle();
        bList = new List<Benutzer>();
        vGateway = new VerlaufGateway();
        vGateway.erzeugeTabelle();
        nList = new List<Nachricht>();
    }

    /**
     * Diese Methode der Server-Klasse wird hiermit ueberschrieben.
     * Der angemeldete Client bekommt die Meldung, dass er angenommen wurde.
     */

    public void processNewConnection(String pClientIP, int pClientPort) {
        this.send(pClientIP, pClientPort, "CON erfolgreich");
    }

    /**
     * Hier wird die Fallunterscheidung behandelt, die im Protokoll festgelegt
     * wurde.
     */
    public void processMessage(String pClientIP, int pClientPort, String pMessage) {
        String inhalt = "";
        switch (gibBefehlsbereich(pMessage)) {
            case "USR": {
                String name = wortAn(pMessage, 1);
                String passwort = wortAn(pMessage, 2);

                if (existiertBenutzer(name)) {
                    if (hatBenutzerRichtigesPasswort(name, passwort)) {
                        this.send(pClientIP, pClientPort, "USR willkommen");
                        this.send(pClientIP, pClientPort, vGateway.gibStringVerlaufListe());
                    } else {
                        this.send(pClientIP, pClientPort, "E01 falsche Anmeldedaten");
                    }
                } else {
                    this.send(pClientIP, pClientPort, "E01 falsche Anmeldedaten");
                }
                break;
            }

            case "REG": 
                {
                //String name = wortAn(pMessage, 1);
                //String passwort = wortAn(pMessage, 2);
                
                //if (existiertBenutzer(name) == false) {
                    //neuerBenutzer(name, passwort);
                    this.send(pClientIP, pClientPort, "REG erfolgreich"); //+ vGateway.gibStringVerlaufListe());
                //} else {
                    //this.send(pClientIP, pClientPort, "E02 Benutzername schon vorhanden");
                
                break;
            }

            case "MES":
                {
                inhalt = gibTextbereich(pMessage);
                String name = "";

                if (inhalt.isEmpty() == false) {
                    vGateway.neueNachricht(inhalt, name);
                    this.send(pClientIP, pClientPort, "MES Nachricht erhalten");
                } else {
                    this.send(pClientIP, pClientPort, "E03 Nachricht leer");
                }
                break;
            }

            case "SND": 
                {
                this.sendToAll(inhalt);
                break;
            }

            case "QUT": 
                {
                this.send(pClientIP, pClientPort, "QUT erfolgreich");
                processClosingConnection(pClientIP, pClientPort);
                break;
            }
            default:
            {
                this.send(pClientIP, pClientPort, pMessage);
            }
        }
    }

    /**
     * Diese Methode der Server-Klasse wird hiermit ueberschrieben.
     * Die Verbindung wird beendet und aus der Liste der Clients gestrichen.
     */
    public void processClosingConnection(String pClientIP, int pClientPort) {
        this.send(pClientIP, pClientPort, "QUT erfolgreich");
        this.closeConnection(pClientIP, pClientPort);
    }
    /*

    /**
     * Main-Methode die den Server auf Port 1024 startet.
     */
    /*
    public static void main(String[] args) {
        MServer es = new MServer(2000);
    }
    */

    /**
     * Diese Methode gibt den Befehl zurueck die die message beinhaltet
     * 
     * @param message
     * 
     * @return Befehl
     */
    private String gibBefehlsbereich(String message) {
        return message.split(" ")[0];
    }

    /**
     * Diese Methode gibt den Text zurueck die die message beinhaltet
     * 
     * @param message
     * 
     * @return Text
     */
    private String gibTextbereich(String message) {
        String[] messageArray = message.split(" ");
        String text = "";
        for (int i = 1; i < messageArray.length; i++) {
            text = text + " " + messageArray[i];
        }
        return text;
    }

    /**
     * Gibt das Wort an der übergebenen Stelle.
     * 
     * @param message
     * @param stelle
     * @return ergebnis welches Wort an der Stelle steht
     */
    private String wortAn(String message, int stelle) {
        String ergebnis = "";
        return ergebnis;
    }

    /**
     * Diese Methode holt den Nachrichtenverlauf aus der Datenbank.
     * 
     * @author
     * @return ergebnis mit Nachrichtenverlauf
     * @version 04.06.24
     */
    public List<Nachricht> holeNachrichtenAusDB() {
        List<Nachricht> ergebnis = vGateway.gibVerlaufListe();
        return ergebnis;
    }

    /**
     * Diese Methode schaut in der DB ob dieser Benutzername schon einmal angemeldet
     * war.
     * 
     * @author
     * @param name der Benutzername
     *             return boolean ob er schon existiert
     * @version 04.06.24
     */
    public boolean existiertBenutzer(String name) {
        List<Benutzer> liste = bGateway.gibBenutzerListe();

        liste.toFirst();
        while (liste.hasAccess()) {
            if (liste.getContent().gibName().equals(name)) {
                return true;
            } else {
                liste.next();
            }
        }
        return false;
    }

    /**
     * Diese Methode schaut in der DB ob das Passort von diesem Benutzername richtig
     * ist.
     * 
     * @author
     * @param name     der Benutzername
     * @param passwort das Passwort
     *                 return boolean ob es richtig ist
     * @version 04.06.24
     */
    public boolean hatBenutzerRichtigesPasswort(String name, String passwort) {
        List<Benutzer> liste = bGateway.gibBenutzerListe();

        liste.toFirst();
        while (liste.hasAccess()) {
            if (liste.getContent().gibName().equals(name)) {
                if (liste.getContent().gibPasswort().equals(passwort)) {
                    return true;
                }
            } else {
                liste.next();
            }
        }
        return false;
    }

    /**
     * Diese Methode legt einen neuen Benutzer in der DB an.
     * 
     * @author
     * @param name     der Benutzername
     * @param passwort das Passwort
     * @version 04.06.24
     */
    public void neuerBenutzer(String name, String passwort) {
        bGateway.neuerBenutzer(name, passwort);
    }
}
