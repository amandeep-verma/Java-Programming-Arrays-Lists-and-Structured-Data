import edu.duke.*;
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarBreaker {
    
    public static int[] countLetters(String message)
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
    
    public static String decrypt(String encrypted)
    {
        
        int[] freq= countLetters(encrypted);
        int max = maxIndex(freq);
        int dkey = max-4;
        if (max < 4)
            dkey = 26-(4-max);
            
        return CaesarCipher.encrypt(encrypted, 26- dkey);
    }
    
    public static String halfOfString(String message,int start)
    {
        StringBuilder st = new StringBuilder();
        for(int i = start; i < message.length(); i=i+2)
        {
            st = st.append(message.charAt(i));
        }
        return st.toString();
    }
    
    public static int getKey(String s)
    {
        int[] freq= countLetters(s);
        int maxI = maxIndex(freq);
        return maxI;
    }
    
    public static void decryptTwoKeys(String encrypted)
    {
        String even = halfOfString(encrypted,0);
        String odd = halfOfString(encrypted,1);
        int key1 = getKey(even);
        int key2 = getKey(odd);
        
        if (key1 >4)
            key1 = key1-4;
        else
            key1 = 26 - (4-key1);
            
        if (key2> 4)
            key2 = key2-4;
        else
            key2 = 26 - (4-key2);
            
        System.out.println("key1- "+key1 +" ,key2- "+key2 );
        
        System.out.println(CaesarCipher.encryptTwoKeys(encrypted,26- key1, 26-key2));
    }
    
    public static void testDecrypt()
    {
         FileResource res = new FileResource("data/wordsLotsOfEs.txt");
         String enc = CaesarCipher.encrypt(res.asString(), 23);
         System.out.println(enc);
         System.out.println(decrypt(enc));
    }
    
    public static void testDecryptTwoKeys()
    {
        FileResource res = new FileResource();
        String enc = res.asString();
        //String enc = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
        decryptTwoKeys(enc);
     }
}
