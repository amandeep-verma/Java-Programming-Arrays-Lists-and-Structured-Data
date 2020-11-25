import java.util.*;
import edu.duke.*;
import java.io.*;
import java.util.HashSet;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder answer = new StringBuilder();
        for(int i = whichSlice;i<message.length(); i=i+totalSlices)
        {
            answer.append(message.charAt(i));
        }
        return answer.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for (int i=0; i<key.length;i++)
        {
            String message = sliceString(encrypted,i,klength);
            CaesarCracker ab = new CaesarCracker(mostCommon);
            key[i]=ab.getKey(message);
        }
        return key;
    }

    public void breakVigenere () {
        System.out.println("Select the file to be encrypted");
        FileResource file = new FileResource("VigenereTestData/secretmessage3.txt");
        String message = file.asString();
        
        HashMap<String, HashSet<String>> languagesDict = new HashMap<String, HashSet<String>>();
        System.out.println("Select the directory storing the words of languages");
        DirectoryResource dr = new DirectoryResource();
        for( File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            System.out.println("Reading dictionary for "+f.getName());
            languagesDict.put(f.getName(), readDictionary(fr));
        }
        
        breakForAllLangs(message, languagesDict);
    }
    
     public HashSet<String> readDictionary(FileResource fr)
     {
         HashSet<String> a = new HashSet<String>();
         for(String line:fr.lines())
         {
             a.add(line.toLowerCase());
         }
         return a;
     }
     
    public int countWords(String message,HashSet<String> dictionary)
    {
         String[] words = message.split("\\W+");
         int totalWords=0;
         for(String a:words)
         {
            if(dictionary.contains(a.toLowerCase()))
                totalWords++;
         }
         return totalWords;
    }

    public char mostCommonCharIn(HashSet<String> dictionary)
    {
        // the limits of this array can be extended to decrypt the message in other languages which are not written using english alphabet, 
        // if so make sure your dictonaries are also not written using english alphabets.
        int[] freqChar = new int[26];
        
        for(String words: dictionary)
        {
            for(char ch:words.toCharArray())
            {
               // checks only english lower case words, we have already converted all words in dictionary to lower cases.
               if(ch<='z' && ch >= 'a')
               {
                   freqChar[ch -'a']++;
               }
            }
        }
        
        int maxFreqPos = 0;
        int maxFreq=0;
        for(int i =0; i<freqChar.length; i++)
        {
            if(freqChar[i]> maxFreq)
            {
                maxFreq = freqChar[i];
                maxFreqPos = i;
            }
        }
        char ch = (char)('a'+ maxFreqPos);
        System.out.println("most common char is "+ch);
        return ch;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages)
    {
        int maxMatch=0;
        String langmatch = null;
        for(String lang: languages.keySet())
        {
            System.out.println("\nFor language "+ lang);
            HashSet<String> dictInLang = languages.get(lang);
            String decrInLang = breakForLanguage(encrypted, dictInLang);
            int wordsInDictionary = countWords(decrInLang, dictInLang);
            if(wordsInDictionary > maxMatch)
            {
                maxMatch= wordsInDictionary;
                langmatch= lang;
            }
        }
        System.out.println("\n\nThe language most matched is "+ langmatch);
        System.out.println(breakForLanguage(encrypted, languages.get(langmatch)));
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary)
    {
        int maxMatch=0;
        int keyL=0;
        VigenereCipher ab;
        char mostCommonChar = mostCommonCharIn(dictionary);
        
        for(int i=1;i<101;i++)
        {
            int[] keys = tryKeyLength(encrypted, i, mostCommonChar);
            ab = new VigenereCipher(keys);
            String decrypted = ab.decrypt(encrypted); // check if the need is to make it StringBuffer
            int wordsInDictionary = countWords(decrypted,dictionary);
            if(wordsInDictionary > maxMatch)
            {
                maxMatch= wordsInDictionary;
                keyL=i;
            }
        }
        int[] keys =tryKeyLength(encrypted, keyL, mostCommonChar);
        ab = new VigenereCipher(keys);
        String decrypted = ab.decrypt(encrypted);
        System.out.println("Key length = "+keys.length);
        System.out.println("The keys is "+Arrays.toString(keys));
        System.out.println("Words Matched = "+maxMatch +" out of total = "+decrypted.split("\\W+").length );
        return decrypted;
    }
}
