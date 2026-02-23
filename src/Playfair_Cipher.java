import java.util.ArrayList;
public class Playfair_Cipher {
    private final char [][]matrix =new char[5][5];
    //index array of X's that added to balance the plaintext;
    private final ArrayList<Integer> x = new ArrayList<>();
    //index array of J's that replaced by i 
    private final ArrayList<Integer> j =  new ArrayList<>(); 
    
    private void setMatrix (String Key)
    {
        String M_letters =(Key.toLowerCase().replaceAll("[^ a-z]","").replace("j","i")+"abcdefghiklmnopqrstuvwxyz");
        StringBuilder uniqe=new StringBuilder();
        for (char c : M_letters.toCharArray())
        {
            if(uniqe.indexOf(String.valueOf(c))==-1) {
                uniqe.append(c);
            }
        }
        for(int i=0;i<5;i++)
        {
            for(int j=0;j<5;j++)
            {
                matrix[i][j]=uniqe.charAt( (i+j)+(4*i));
            }

        }
    }
    
    private String encrypt_2_chars(char a,char b)
    {
        int[] index_of_a =new int[2];
        int[] index_of_b =new int[2];
        
        if(a=='j')
        {
            a='i';
        }
        if(b=='j')
        {
            b='i';
        }
        
        for(int i=0;i<5;i++)
        {
            for(int j=0;j<5;j++)
            {
                if(a==matrix[i][j])
                {
                     index_of_a[0]=i;
                     index_of_a[1]=j;
                }
                if(b==matrix[i][j])
                {
                    index_of_b[0]=i;
                    index_of_b[1]=j;
                }
                
            }
        }
        char out_a,out_b;
        if(index_of_a[0]==index_of_b[0])
        {
         out_a=matrix[index_of_a[0]][(index_of_a[1]+1)%5];
         out_b=matrix[index_of_b[0]][(index_of_b[1]+1)%5];
        } else if (index_of_a[1]==index_of_b[1]) {
            out_a=matrix[(index_of_a[0]+1)%5][index_of_a[1]];
            out_b=matrix[(index_of_b[0]+1)%5][index_of_b[1]];
        }
        else{
            out_a=matrix[index_of_a[0]][index_of_b[1]];
            out_b=matrix[index_of_b[0]][index_of_a[1]];
        }
        StringBuilder s=new StringBuilder();
        s.append(out_a);
        s.append(out_b);
        return s.toString();
    }

    public String encrypt (String plaintext,String key)
    {
        StringBuilder text =new StringBuilder();
        text.append(plaintext.toLowerCase());
        
        setMatrix(key);
        
        for(int i=0;i<text.length();i+=2)
        {
            if(i+1<text.length()) {
                if (text.charAt(i) == text.charAt(i + 1)) {
                    text.insert(i + 1, 'x');
                    x.add(i + 1);
                }
            }
        }
       
        if(text.length()%2==1)
        {
            text.append('x');
            x.add(text.length()-1);
        }
        StringBuilder result =new StringBuilder();
        for(int i=0;i<text.length();i+=2)
        {
            if(text.charAt(i)=='j')
            {
                j.add(i);
            }
            if(text.charAt(i+1)=='j')
            {
                j.add(i+1);
            }
            
            result.append(encrypt_2_chars(text.charAt(i), text.charAt(i+1) ));
        }
        return result.toString();
    }
    
    public String decrypt(String ciphertext , String key)
    {
        return "";
    }
    
}
