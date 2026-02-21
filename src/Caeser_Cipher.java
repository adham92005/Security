import java.util.Locale;

public class Caeser_Cipher {
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
}
