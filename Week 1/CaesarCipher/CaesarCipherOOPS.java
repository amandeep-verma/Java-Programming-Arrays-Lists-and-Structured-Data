
/**
 * Write a description of CaesarCipherOOPS here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherOOPS {

    private String alph;
    private String shiftedAlph;
    private int mainKey;
    
    CaesarCipherOOPS(int key)
    {
        alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlph = alph.substring(key) + alph.substring(0,key);
        mainKey = key;
    }
    
    public String encrypt(String message)
    {
        StringBuilder encrypted = new StringBuilder(message);
        for( int i =0; i<message.length();i++)
	{
        	int pos = alph.indexOf(message.charAt(i));
        	if(pos != -1)
        	{
        		encrypted.setCharAt(i, shiftedAlph.charAt(pos));
        	}
        	
        	pos = alph.toLowerCase().indexOf(message.charAt(i));
        	if(pos != -1)
        	{
        		encrypted.setCharAt(i, shiftedAlph.toLowerCase().charAt(pos));
        	}
	}
	return encrypted.toString();
    }
    
    public String decrypt(String input)
    {
        
        CaesarCipherOOPS cc = new CaesarCipherOOPS(26 - mainKey); 
        return cc.encrypt(input);
    }
}
