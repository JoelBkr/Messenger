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
            case "CON":
                {
                    System.out.println(gibTextbereich(message));
                }
            case "USR":
                {
                    System.out.println(wortAn(message, 1));
                    druckeVerlauf(wortAn(message, 2));
                }
            case "E01":
                {
                     System.out.println(gibTextbereich(message));
                }
            case "REG":
                {
                    System.out.println(wortAn(message, 1));
                    druckeVerlauf(wortAn(message, 2));
                }
            case "E02":
                {
                    System.out.println(gibTextbereich(message));
                }
            case "MES":
                {
                    System.out.println(gibTextbereich(message));
                }
            case "E03":
                {
                    System.out.println(gibTextbereich(message));
                }
            case "SND":
                {
                    System.out.println(gibTextbereich(message));
                }    
            case "QUT":
                {
                    System.out.println(gibTextbereich(message));
                }        
                
        }
        
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
        String ergebnis = message.split(" ")[stelle];
        return ergebnis;
    }
    /**
     * Diese Methode druckt den Nachrichtenverlauf aus
     * 
     * @author:
     * @version: 06.06.2024
     * @param: Nachrichtenverlauf
     */
    private void druckeVerlauf(String verlauf)
    {
       String [] vArray = verlauf.split(":"); 
       for (int i = 0;i<vArray.length;i++)
       {
           System.out.println(vArray[i]);
           System.out.println("-----");
       }
    }
}
