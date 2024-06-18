/**
* @author 
* @version 
*/
 
public interface Kryptomodul
{
    public String verschluesseln(String klartext);
    
    public String entschluesseln(String geheimtext);
    
    public int loadKey();
    
    public void saveKey();
}

