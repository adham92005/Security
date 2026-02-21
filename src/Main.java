import java.util.Scanner;
public class Main {
    public static void main(String[] args)
    {
        Scanner scanner =new Scanner(System.in);
       String plaintext ="Computer";
       int key = 8;
       Caeser_Cipher obj =new Caeser_Cipher();
       String c =obj.encrypt(plaintext,key);
       String p =obj.decrypt(c,key);
       System.out.printf("the encrypt of %s is %s and decrypt is %s ",plaintext,c,p);
    }
}