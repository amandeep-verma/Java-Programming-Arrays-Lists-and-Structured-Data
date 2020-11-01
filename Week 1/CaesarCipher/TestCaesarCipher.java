import edu.duke.*;
import java.io.*;
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCaesarCipher {
    
    private static int[] countLetters(String message)
    {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] count = new int[26];
        for (int k=0; k<message.length(); k++)
        {
            char ch = Character.toLowerCase(message.charAt(k));
            int pos = alph.indexOf(ch);
            if (pos != -1)
            {
                count[pos]++;
            }
        }
        return count;
    }
    
    public static int maxIndex(int[] freq)
    {
        int max=0;
        for (int k=0; k<freq.length; k++)
        {
            if(freq[max]<freq[k])
                max= k;
        }
        return max;
    }
    
    public static void simpleTests()
    {
        FileResource fr = new FileResource();
        CaesarCipherOOPS cs = new CaesarCipherOOPS(18);
        String original = fr.asString();
        String encrypted = cs.encrypt(original);
        String decrypted = cs.decrypt(encrypted);
        
        System.out.println("original "+ original);
        System.out.println("encrypted "+ encrypted);
        System.out.println("deencrypted "+ decrypted);
        
        String decrypted2 = breakCaesarCipher(encrypted);
        System.out.println("deencrypted2 "+ decrypted2);
    }
    
    public static String breakCaesarCipher(String encrypted)
    {
        int[] freq= countLetters(encrypted);
        int max = maxIndex(freq);
        int dkey = max-4;
        if (max < 4)
            dkey = 26-(4-max);
            
        CaesarCipherOOPS cs = new CaesarCipherOOPS(26 - dkey);
        return cs.encrypt(encrypted);
    }
    
    
}
