import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//       String plaintext ="Computer";
//       int key = 8;
//       Caeser_Cipher obj =new Caeser_Cipher();
//       String c =obj.encrypt(plaintext,key);
//       String p =obj.decrypt(c,key);
//       obj.Attack(c);


//        String Key ="dqperstafwxgobvhnclizumlyj";
//        String plaintext ="Adhoom";
//        Monoalphabetic_Cipher obj = new Monoalphabetic_Cipher();
//        String c = obj.encrypt(plaintext,Key);
//        String main = obj.decrypt(c,Key);
//        System.out.printf("the plaintext is %s incrypted to %s then decrypted to the main text \'%s\'",plaintext,c,main);

//          String key ="ballon";
//          String plaintext ="aadhhojjma";
//          Playfair_Cipher obj =new Playfair_Cipher();
//          String c = obj.encrypt(plaintext,key);
//          String p = obj.decrypt(c,key);
//          System.out.printf("the result of encrypt %s is \'%s\' , plaintext is %s",plaintext,c,p);
        Hill_Cipher obj = new Hill_Cipher();
        int ar[][] = {
                {17, 17, 5},
                {21, 18, 21},
                {2, 2, 19}
        };
        String ct =obj.encrypt("pay more money", ar);
        System.out.println(ct);
        System.out.println(obj.decrypt(ct, ar));
    }
}