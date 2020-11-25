import edu.duke.*;
import java.util.*; 

public class tester {
    
    public void testCaesar()
    {
        CaesarCipher ab = new CaesarCipher(15);
        FileResource file = new FileResource("VigenereTestData/titus-small.txt");
        String message = file.asString();
        System.out.println("original message\n "+message+"\n\n");
        System.out.println("encrypted message\n "+ab.encrypt(message)+"\n\n");
        System.out.println("decrypted message\n "+ab.decrypt(ab.encrypt(message))+"\n\n");
    }
    
    public void testVigenere()
    {
        VigenereCipher ab = new VigenereCipher(new int[]{17, 14, 12, 4});
        FileResource file = new FileResource("VigenereTestData/titus-small.txt");
        String message = file.asString();
        System.out.println("original message\n "+message+"\n\n");
        System.out.println("encrypted message\n "+ab.encrypt(message)+"\n\n");
        System.out.println("decrypted message\n "+ab.decrypt(ab.encrypt(message))+"\n\n");
    }
    
    public void testVigBreaker()
    {
        VigenereBreaker ab = new VigenereBreaker();
        FileResource file = new FileResource("VigenereTestData/secretmessage1.txt");
        String message = file.asString();
        System.out.println(Arrays.toString(ab.tryKeyLength(message, 4, 'e')));
        
    }
}
