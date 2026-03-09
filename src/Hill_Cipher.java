import java.security.Key;

public class Hill_Cipher {
    
    public int[][] prepair_plaintext (String P,int [][] K)
    {
        int det =get_det(K);
        if(det==0 || det%2==0 || det %13 ==0)
        {
            throw new RuntimeException("key matrix must be not dividable by 2 or 13 and determinant not = 0 ");
        }
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
     
    
    public int get_det (int [][] matrix)
    {
        int n= matrix.length;
        int det =0;
        if(n==1)
            return matrix[0][0];
        else if(n==2)
        {
            return matrix[0][0]*matrix[1][1] -matrix[0][1]*matrix[1][0];
        }
        else 
            for(int i=0;i< matrix.length;i++)
            {
               det+= Math.pow(-1,i)*matrix[0][i]*get_det(get_submatrix(matrix,i));
            }
            return det;
    }
    
    
    public int[][] get_submatrix (int [][] matrix,int column)
    {
        int [][] k=new int[matrix.length-1][matrix.length-1];
        for(int i=0;i<matrix.length-1;i++)
        {
            for (int j=0;j<matrix.length-1;j++)
            {
                if(j<column) {
                    k[i][j] = matrix[i + 1][j];
                }
                else  {
                    k[i][j] = matrix[i + 1][j+1];
                }
            }
        }
        
        return k ;
    }
    

}
