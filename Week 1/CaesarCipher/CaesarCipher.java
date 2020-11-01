import edu.duke.*;
import java.io.*;
/**
* Write a description of CaesarCipher here.
* 
* @author (your name) 
* @version (a version number or a date)
*/
public class CaesarCipher {
    public static String encrypt(String message, int key)
    {
	String alphabetsH = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	String hackH  = alphabetsH.substring(key) + alphabetsH.substring(0,key);
	StringBuilder encrypted = new StringBuilder(message);
	
	for( int i =0; i<message.length();i++)
	{
        	int pos = alphabetsH.indexOf(message.charAt(i));
        	if(pos != -1)
        	{
        		encrypted.setCharAt(i, hackH.charAt(pos));
        	}
        	
        	pos = alphabetsH.toLowerCase().indexOf(message.charAt(i));
        	if(pos != -1)
        	{
        		encrypted.setCharAt(i, hackH.toLowerCase().charAt(pos));
                }
	}
	
	return encrypted.toString();
        
    }
    
    public static String encryptTwoKeys(String input, int key1,int key2)
    {
        StringBuilder encrypted = new StringBuilder(input);
	for( int i =0; i<input.length();i++)
	{
		if(i%2 ==0)
			encrypted.setCharAt(i, encrypt(Character.toString(input.charAt(i)), key1).charAt(0));
		else
			encrypted.setCharAt(i, encrypt(Character.toString(input.charAt(i)), key2).charAt(0));
		
	}
	
	return encrypted.toString();
    }
    
    public static void testCaesar()
    {
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key =15;
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }
    
    public static void testCaesartwokeys()
    {
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key1 =26-14;
        int key2 =26-24;
        String encrypted = encryptTwoKeys(message, key1, key2);
        System.out.println("key is " + key1+" , " + key2+ "\n" + encrypted);
    }
}
