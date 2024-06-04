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
        this.send(pClientIP, pClientPort, "+OK Verbindung hergestellt");
    }

    /**
     * Hier wird die Fallunterscheidung behandelt, die im Protokoll festgelegt wurde.
     */
    public void processMessage(String pClientIP, int pClientPort, String pMessage){ 
        switch(gibBereich(pMessage,0))
        {
                //Hier muss das Protokoll umgesetzt werden
            case "STR":
                {
                    if(gibBereich(pMessage,1) != "")
                    {
                        spieleOnline.append(new Spiel(pClientIP, pClientPort, gibZufallszahl(), gibBereich(pMessage,1))); 
                        this.send(pClientIP, pClientPort, "+OK Willkommen " + gibBereich(pMessage,1) + ", errate meine Zahl");
                    }
                    else
                    {
                        this.send(pClientIP, pClientPort, "-E1 Name fehlt");
                    }
                    break;
                }
            case "RAT":
                {
                    if(gibBereich(pMessage,1) != "")
                    {
                        int zahl = Integer.parseInt(gibBereich(pMessage,1));
                        if(zahl == gibZahlVonSpiel(pClientIP))
                        {
                            this.send(pClientIP, pClientPort, "TRU Die Zahl war richtig");
                            this.DBhighscore.hinzufuegen(gibNameVonSpiel(pClientIP), gibVersucheVonSpiel(pClientIP));
                        }
                        else if(zahl > 20 || zahl < 0)
                        {
                            this.send(pClientIP, pClientPort, " -E2 Die Zahl liegt nicht zwischen 0 und 20");
                        }
                        else
                        {
                            this.send(pClientIP, pClientPort, "FLS Die zahl war leider falsch");
                        }
                        versucheErhoehenVonSpiel(pClientIP);
                    }
                    else
                    {
                        this.send(pClientIP, pClientPort, "-E3 Keine Zahl");
                    }
                    break; 
                }
            case "GHC":
                {
                    this.send(pClientIP, pClientPort, "GHC " + this.generiereStringAusList(DBhighscore.holeZehn()));
                    break;
                }
            case "END":
                {
                    this.send(pClientIP, pClientPort, "END Tsch�ss");
                    closeConnection(pClientIP, pClientPort);
                    break;
                }

            default:
                {
                    this.send(pClientIP, pClientPort, "-E0 Falsche Angaben"); 
                    break;
                }
        }

    }

    /**
     * Diese Methode der Server-Klasse wird hiermit ueberschrieben.
     * Die Verbindung wird beendet und aus der Liste der Clients gestrichen.
     */
    public void processClosingConnection(String pClientIP, int pClientPort){
        this.send(pClientIP, pClientPort, "EXT complete");
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
     * Diese Methode gibt den Befehl zur�ck die die message beinhaltet
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
     * Diese Methode gibt den Text zur�ck die die message beinhaltet
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
}
