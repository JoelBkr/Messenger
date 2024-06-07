
/**
 * @author 
 * @version 
 */
public class Verschluesselung
{
    List<String> alph;
    public Verschluesselung()
    {
        alph = new List<String>();
        String a = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0;i<a.length();i++)
        {
            alph.append(String.valueOf(a.charAt(i)));
        }
    }
    public String verschluesseln(String kText, int schluessel)
    {
        String vText = new String();
        for(int i = 0;i<kText.length();i++)
        {
            char akt = kText.charAt(i);
            
            alph.toFirst();
            while(!alph.getContent().equals(String.valueOf(akt)))
            {
                alph.next();
            }
            for(int j = 0;j<schluessel;j++)
            {
                alph.next();
                if(!alph.hasAccess())
                {
                    alph.toFirst();
                }
            }
            vText = vText + alph.getContent(); 
            
        }
        return vText;
    }

    public String entschluesseln(String gText, int schluessel)
    {
        String eText = new String();
        for(int i = 0;i<gText.length();i++)
        {
            char akt = gText.charAt(i);
            
            
            alph.toFirst();
            while(!alph.getContent().equals(String.valueOf(akt)))
            {
                alph.next();
            }
            for(int j = 0;j<26-schluessel;j++)
            {
                alph.next();
                if(!alph.hasAccess())
                {
                    alph.toFirst();
                }
            }
            eText = eText + alph.getContent();
        }
        return eText;
    }

}
