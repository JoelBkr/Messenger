/**
* @author 
* @version 
*/
 
public interface Kryptomodul
{
    public void verschluesseln(String klartext);
    
    public void entschluesseln(String geheimtext);
    
    public void loadKey();
    
    public void saveKey();
}

