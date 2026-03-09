import java.security.Key;

public class Hill_Cipher {
    
    public int[][] prepair_plaintext (String P,int [][] K)
    {
        StringBuilder plaintext=new StringBuilder();
        plaintext.append(P.toLowerCase());
        int pcolumn=Math.ceilDiv(P.length(), K.length);
        int len =pcolumn*K.length;
        for(int i=P.length();i<len;i++)
        {
            plaintext.append('x');
        }

        int [][]Pmatrix =new int[K.length][pcolumn];
        for (int j=0;j<pcolumn;j++)
        {
            for(int i=0;i<K.length;i++)
            {
                Pmatrix[i][j] =  (plaintext.charAt(i+(K.length*j)))-'a';
            }
        }
        return  Pmatrix;
    }
    
    
    
    public String encrypt(String p,int [][] key)
    {
       int [][] pmatrix= prepair_plaintext(p,key);
       int pcolumn=Math.ceilDiv(p.length(), key.length);
       int [][] cmatrix = new int[key.length][pcolumn];
       StringBuilder ciphertext =new StringBuilder();
       char c;
       int letter;
     
       for(int i=0;i< key.length;i++)
       {
           for(int j=0;j<pcolumn;j++)
           {
               for(int k=0;k< key.length;k++)
               {
                   cmatrix[i][j] += key[i][k]*pmatrix[k][j];
               }
               cmatrix[i][j]%=26;
           }
       }
        for(int j=0;j<pcolumn;j++) {
            for (int i = 0; i < key.length; i++) {

                letter = (cmatrix[i][j] % 26) + 'a';
                c = (char) letter;
                ciphertext.append(c);
            }
        }
        
        return ciphertext.toString();
    }

}
