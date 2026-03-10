import java.security.Key;

public class Hill_Cipher {
    
    public int[][] prepair_text (String P,int [][] K)
    {
        if(K.length!=K[0].length)
        {
            throw new RuntimeException("key matrix must be a square matrix");
        }
        
        int det =get_det(K);
        if(det==0 || det%2==0 || det %13 ==0)
        {
            throw new RuntimeException("key matrix must be not dividable by 2 or 13 and determinant not = 0 ");
        }
       
        StringBuilder text=new StringBuilder();
        String textstring =P.toLowerCase().replaceAll("[^a-z]","");
        text.append(textstring);
        int pcolumn=Math.ceilDiv(textstring.length(), K.length);
        int len =pcolumn*K.length;
        for(int i=textstring.length();i<len;i++)
        {
            text.append('x');
        }

        int [][]textmatrix =new int[K.length][pcolumn];
        for (int j=0;j<pcolumn;j++)
        {
            for(int i=0;i<K.length;i++)
            {
                textmatrix[i][j] =  (text.charAt(i+(K.length*j)))-'a';
            }
        }
        return  textmatrix;
    }
    
    
    
    public String encrypt(String p,int [][] key)
    {
        String ptextstring =p.toLowerCase().replaceAll("[^a-z]","");
       int [][] pmatrix= prepair_text(ptextstring,key);
       int pcolumn=Math.ceilDiv(ptextstring.length(), key.length);
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
               det+= Math.pow(-1,i)*matrix[0][i]*get_det(get_submatrix(matrix,0,i));
            }
            return det;
    }
    
    
    public int[][] get_submatrix (int [][] matrix,int row ,int column)
    {
        int [][] k=new int[matrix.length-1][matrix.length-1];
        for(int i=0;i<matrix.length-1;i++)
        {
            for (int j=0;j<matrix.length-1;j++)
            {
                if(j<column&&i<row) {
                    k[i][j] = matrix[i][j];
                } 
                else if (j<column&&i>=row) {
                    k[i][j] = matrix[i+1][j];
                } 
                else if (j>=column&& i<row) {
                    k[i][j] = matrix[i][j+1];
                } else  {
                    k[i][j] = matrix[i + 1][j+1];
                }
            }
        }
        
        return k ;
    }
    
    public int [][] get_inverse (int matrix [][])
    {
        int mtrx [][] = new int[matrix.length][matrix.length] ;
        int mtrxbefortranspose [][] = new int[matrix.length][matrix.length] ;
        int det = get_det(matrix)%26;
        if (det < 0) det += 26;
        int inverse_det =0 ;
        for (int i=1;i<=26;i++)
        {
            if(det*i %26 == 1)
            {
                inverse_det =i;
                break;
            }
        }
        
        if(matrix.length == 2 && matrix[0].length ==2)
        {
            mtrx[0][0] = matrix[1][1];
            mtrx[1][1] = matrix[0][0];
            mtrx[0][1] = -matrix[0][1];
            mtrx[1][0] = -matrix[1][0];
        }
        
        else {
            for (int i=0;i< matrix.length;i++)
            {
                for (int j=0;j<matrix[0].length;j++)
                {
                    mtrxbefortranspose[i][j]=(int)Math.pow(-1,i+j)*get_det(get_submatrix(matrix,i,j));
                }
            }
            for (int i=0;i< matrix.length;i++)
            {
                for (int j=0;j<matrix[0].length;j++)
                {
                    mtrx[i][j] = mtrxbefortranspose[j][i];
                }
            }
            
        }

        for (int i=0;i< matrix.length;i++)
        {
            for (int j=0;j<matrix[0].length;j++)
            {
                mtrx[i][j] *=inverse_det;
            }
        }
        return mtrx;
    }

    public String decrypt (String c , int [][] key)
    {
        String ctextstring =c.toLowerCase().replaceAll("[^a-z]","");
        int [][] cmatrix= prepair_text(ctextstring,key);
        int ccolumn=Math.ceilDiv(ctextstring.length(), key.length);
        int [][] pmatrix = new int[key.length][ccolumn];
        StringBuilder plaintext =new StringBuilder();
        int [][] key_inverse =get_inverse(key);
        char ch;
        int letter;

        for(int i=0;i< key.length;i++)
        {
            for(int j=0;j<ccolumn;j++)
            {
                for(int k=0;k< key.length;k++)
                {
                    pmatrix[i][j] += key_inverse[i][k]*cmatrix[k][j];
                }
                pmatrix[i][j]%=26;
            }
        }
        for(int j=0;j<ccolumn;j++) {
            for (int i = 0; i < key.length; i++) {

                if((pmatrix[i][j] % 26)<0)
                {
                    letter = (pmatrix[i][j] % 26) + 26+'a';
                }
                else {
                    letter = (pmatrix[i][j] % 26) + 'a';
                }
                ch = (char) letter;
                plaintext.append(ch);
            }
        }

        return plaintext.toString();
    }

}
