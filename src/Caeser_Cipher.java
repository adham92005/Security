import java.util.Locale;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

public class Caeser_Cipher {
    private TreeSet<String> dictionary = new TreeSet<>();

    public void loadDictionary(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String word;
            while ((word = br.readLine()) != null) {
                dictionary.add(word.trim().toLowerCase());
            }
            System.out.println("Dictionary loaded with " + dictionary.size() + " words.");
        } catch (IOException e) {
            System.err.println("Error reading dictionary file: " + e.getMessage());
        }
    }


    public  String  encrypt(String p,int k)
    {
        StringBuilder c=new StringBuilder();
        for(char f : p.toCharArray())
        {
            char base = Character.isUpperCase(f)?'A':'a' ;

            if (Character.isLetter(f))
            {
                c.append((char) (((f-base+k)%26)+base));
            }
            else
                c.append((char) (f));
        }
        return c.toString();
    }
    
    
    public  String decrypt(String c,int key)
    {
        return encrypt(c,26-(key%26));
    }
    
    
    public void Attack (String c)
    {
        loadDictionary("words_alpha.txt");
        boolean found = false;

        for(int key = 1;key <26 ;key++)
        {
            String trial = decrypt(c, key);
            if (dictionary.contains(trial.toLowerCase())) {
                System.out.println("------------------------------------");
                System.out.println("MATCH FOUND!");
                System.out.println("Key: " + key);
                System.out.println("Decrypted Text: " + trial);
                System.out.println("------------------------------------");
                found = true;

            }

        }
        if (!found) {
            System.out.println("Attack finished. No matches found in dictionary.");
        }
    }
}
