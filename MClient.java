import javax.swing.*;
/**
 * Klasse fuer einen SpielClient
 * @author Henning Ainoedhofer
 * @version 21.3.2017
 */

public class MClient extends Client { 
    public MClient(String ip, int p) {
        super(ip, p);
    }

    /**
     * Diese Methode der Server-Klasse wird hiermit ueberschrieben.
     * Der Client gibt die erhaltende Meldung, auf dem Textfeld aus.
     */

    public void processMessage(String message){
        switch(gibBefehlsbereich(message))
        {
            case "+OK":
            {
                System.out.println(gibTextbereich(message));
                break;
            }
            
            case "FLS":
            {
                System.out.println(gibTextbereich(message));
                break;
            }
            
            case "TRU":
            {
                System.out.println(gibTextbereich(message));
                break;
            }
                
            case "GHC":
            {
                System.out.println(message);
                break;
            }
            
            case "END":
            {
                System.out.println(gibTextbereich(message));    
                break;
            }
            
            case "-E1":
            {
                System.out.println(gibTextbereich(message));
                break;
            }
            
            case "-E2":
            {
                System.out.println(gibTextbereich(message));
                break;
            }
            
            case "-E3":
            {
                System.out.println(gibTextbereich(message));
                break;
            }
            
            default:
            {
                System.out.println("Befehl falsch. Bitte richtigen Befehl eintippen.");
                break;
            }
        }
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
