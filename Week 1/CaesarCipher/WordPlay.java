
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {

     public static boolean isVowel(char  ch)
     {
        String vowel = "aeiouAEIOU";
        
        return -1 != vowel.indexOf(ch);
        
     }
     
     public static String replaceVowels(String phrase, char ch) 
     {
        StringBuilder replacer = new StringBuilder(phrase);
        for( int i =0; i<replacer.length();i++)
        {
            if ( isVowel(replacer.charAt(i)) )
            {
                replacer.setCharAt(i, ch);
            }
        }
        
        return replacer.toString();
     }
     
    public static String emphasize(String phrase, char ch)
    {
        
       StringBuilder replacer = new StringBuilder(phrase);
       for( int i =0; i<replacer.length();i++)
       {
           char lower = replacer.charAt(i);
           char higher;
           if(Character.isLowerCase(lower))
           {   
               higher = Character.toUpperCase(lower);
           }
           else
           {
               higher = lower;
               lower = Character.toLowerCase(lower);
           }
           
           if ( lower == ch || higher == ch)
           {
        	   if ( i %2 ==0)
        		   replacer.setCharAt(i, '*');
        	   else
        		   replacer.setCharAt(i, '+');
           }
       }
       return replacer.toString();
    }

     
}
