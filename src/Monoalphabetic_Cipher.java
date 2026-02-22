import java.util.Map;
import java.util.HashMap;

public class Monoalphabetic_Cipher {

    public String encrypt (String plaintext,String key)
    {
        String alpha ="abcdefghijklmnopqrstuvwxyz";
        Map<Character,Character>  map=new HashMap<>();

        for (int i=0 ; i<26 ; i++)
        {
            map.put(alpha.charAt(i),key.charAt(i));
        }
        
        StringBuilder result = new StringBuilder();
        
        for (char c : plaintext.toLowerCase().toCharArray())
        {
            result.append(map.getOrDefault(c,c));
        }
        
        return result.toString();
    }
    
    
    
    public String decrypt (String ciphertext,String key)
    {
        String alpha ="abcdefghijklmnopqrstuvwxyz";
        Map <Character,Character> reverse_map = new HashMap<>();
        
        for (int i=0;i<26;i++)
        {
            reverse_map.put(key.charAt(i),alpha.charAt(i));
        }
        StringBuilder result =new StringBuilder();
        for (char c : ciphertext.toLowerCase().toCharArray())
        {
            result.append(reverse_map.getOrDefault(c,c));
        }
        
        return result.toString();
    }
 
    // attack function uses  Frequency Analysis on long text put we now work in words

}
