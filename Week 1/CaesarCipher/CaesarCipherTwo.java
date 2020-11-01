
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwo {

    private String alphabet, shiftedAlphabet1, shiftedAlphabet2;
    // private int key1, key2;
    
    CaesarCipherTwo(int key1, int key2)
    {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);
        // this.key1 = key1;
        // this.key2 = key2;
    }
    
    
    public String encrypt(String input)
    {
        StringBuilder encrypted = new StringBuilder(input); 
	for( int i =0; i<input.length();i++)
	{
	    int pos = alphabet.indexOf(input.charAt(i));
	    if (pos != -1)
	    {
	       if(i%2 ==0)
               {
                   encrypted.setCharAt(i, shiftedAlphabet1.charAt(pos));
               }
               else
                   encrypted.setCharAt(i, shiftedAlphabet2.charAt(pos));
	    }
	    pos = alphabet.toLowerCase().indexOf(input.charAt(i));
	    if (pos != -1)
	    {
	       if(i%2 ==0)
               {
                   encrypted.setCharAt(i, shiftedAlphabet1.toLowerCase().charAt(pos));
               }
               else
                   encrypted.setCharAt(i, shiftedAlphabet2.toLowerCase().charAt(pos));
	    }
	}
	return encrypted.toString();
    }
    
    public String decrypt(String input)
    {
        StringBuilder decrypted = new StringBuilder(input); 
        
	for( int i =0; i<input.length();i++)
	{
	    if (i %2 == 0)
	    {
	       int pos = shiftedAlphabet1.indexOf(input.charAt(i));
	       if (pos != -1)
	           decrypted.setCharAt(i, alphabet.charAt(pos));
	       
	       pos = shiftedAlphabet1.toLowerCase().indexOf(input.charAt(i));
	       if (pos != -1)
	           decrypted.setCharAt(i, alphabet.toLowerCase().charAt(pos));
	    }
	    else
	    {
	       int pos = shiftedAlphabet2.indexOf(input.charAt(i));
	       if (pos != -1)
	           decrypted.setCharAt(i, alphabet.charAt(pos));
	       
	       pos = shiftedAlphabet2.toLowerCase().indexOf(input.charAt(i));
	       if (pos != -1)
	           decrypted.setCharAt(i, alphabet.toLowerCase().charAt(pos));
	    }
	}
	return decrypted.toString();
    }
    
}
