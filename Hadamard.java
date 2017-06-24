import java.util.Arrays;
import java.io.*;
import java.util.*;
import java.io.FileReader;
public class Hadamard
{
  
    
    private static int[][] hadCode;
    private static int k; // order of hadamard matrix
    
    //constructor
    public Hadamard(int order)
    {
        this.k = order;
        
        // Initialize a Hadamard encoder/decoder which
        // holds a Hadamard code HC(k) of order k
        hadCode = hadCod2(k);
        System.out.println(hadCode.length+" "+hadCode[0].length); //(2^(k+1)*2^k)
    }
    
    /*
     * Fastly generating Hadamard matrix
     * Algorithm from:
     * http://introcs.cs.princeton.edu/java/14array/Hadamard.java.html
     */
    // Upper half of a hadamard code is a Hadamard matrix of order k : H(k)
    private int[][] hadMx2(int k)
    {
        // compute matrix size N
        int N = new Double(Math.pow(2, k)).intValue();
        int[][] had = new int[N][N];

        // initialize Hadamard matrix of order N
        had[0][0] = 1;
        for (int n = 1; n < N; n += n)
        {
            for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < n; j++)
                {
                    had[i+n][j]   =  had[i][j];
                    had[i][j+n]   =  had[i][j];
                    had[i+n][j+n] =  (had[i][j] + 1) % 2; //1 -> 0 || 0 -> 1
                }
            }
        }
        
        return had;
    }
    
    /*
     * Bottom half of a hadamard code
     * is the negation of a Hadamard matrix: -H(k)
     */
    private int[][] hadMxNeg2(int k)
    {
        int N = new Double(Math.pow(2, k)).intValue();
        int[][] had = new int[N][N];

        // initialize Hadamard matrix of order N
        had[0][0] = 0;
        for (int n = 1; n < N; n += n)
        {
            for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < n; j++)
                {
                    had[i+n][j]   =  had[i][j];
                    had[i][j+n]   =  had[i][j];
                    had[i+n][j+n] =  (had[i][j] + 1) % 2; //1 -> 0 || 0 -> 1
                }
            }
        }
        return had;
    }
    

      /*
     * Concatenate array vertically
     */
    private int[][] vConcat(int[][] a, int[][] b)
    {
        if(a[0].length <= b[0].length) //b has to have equal or more columns than a
        {
            int[][] rslt = new int[a.length + b.length][a[0].length];
            
            for(int j = 0; j < a[0].length; ++j)
            {
                int ai = 0;
                int bi = 0;
                
                while(ai < a.length)
                {
                    rslt[ai][j] = a[ai++][j];
                }

                while(bi < b.length)
                {
                    rslt[ai + bi][j] = b[bi++][j];
                }
            }
            return rslt;
        }
        else
        {
            return null;
        }
    }
    


    // Let H(k) be theHadamard code of order k, then
    // hadamard code HC(k) consists of H(k) as upper half and -H(k) as bottom half
    private int[][] hadCod2(int k)
    {
        int[][] h = hadMx2(k);
        int[][] hNeg = hadMxNeg2(k);
        
        return vConcat(h, hNeg);
    }
    
 

    //encoding process is explained in http://homepages.math.uic.edu/~leon/mcs425-s08/handouts/Hadamard_codes.pdf
    public String encode(String word)
    {
        //if word length > (k + 1) bit it cannot be encoded
        if (word.length() > (k + 1)) return null;
        
        //convert word from binary string to integer
        int index = Integer.parseInt(word, 2);
        
        //get codeword at given row index
        int[] cw = hadCode[index];
        
        String codeword = "";
        
        int i = 0;
        while(i < cw.length)
        {
            codeword += cw[i++];
        }
      
        return codeword;
    }
    
    
    //decoding process by weight calculation is explained in http://homepages.math.uic.edu/~leon/mcs425-s08/handouts/Hadamard_codes.pdf
    public String decode(String codeword)
    {
        //if codeword length != 2^k bit then it cannot be decoded
        if(codeword.length() != new Double(Math.pow(2, k)).intValue()){//System.out.println("It happened"); 
        return Integer.toString(codeword.length());}
        
        int[] cw = new int[codeword.length()];
        for(int i = 0; i < codeword.length(); ++i)
        {
            cw[i] = Integer.parseInt(codeword.substring(i, i+1));
        }
        
        //compute the weight of each index to original message
        //index = (row number) of Hadamard code
        int[] weight = weight(cw, hadCode);
        
       
        int ans=-1;
        int difference = new Double(Math.pow(2,k)).intValue()/4-1;
        int min = Integer.MAX_VALUE;

        for(int i = 0;i<weight.length;i++)
        {
           if(weight[i]<min)
           {
            min = weight[i];
            ans = i;
           }
        }
        // if (min > difference)
        //     return "Can't be corrected";
        
        String dec = Integer.toBinaryString(ans);
        if(dec.length()<k+1)
        {
            while(dec.length()<k+1)
            {
                dec = "0"+dec;
            }
        }
        return dec;
    }
    

    public int[] weight(int[]v, int[][]h)
    {
        int [] r = new int[h.length];
        // 
        for(int i = 0 ;i< h.length;i++)
        {
            for(int j = 0 ;j<h[0].length;j++)
            {
                if(v[j]!=h[i][j])
                {
                    r[i]++;
                }
            }
        }
        return r;
    }
    
   
  
  

    public static void main(String[] args) 
    {   
        try{

        int k = 7;  // but message length is k+1 i.e. 8bits which is the size of pixel value as well.
        
        //this generates the hadamard matrix of size 2n*n where n = 2^k 
        Hadamard had = new Hadamard(k);

        //reads all the pixel values present in binary from bin.txt it will have, img.length*img.height*3 for 3 channels
        //number of values.
        BufferedReader br  = new BufferedReader(new FileReader("bin.txt"));
       

        String msg = "";
       
        //this is to write the codeword generated from binary pixel values into codeword.txt 
        PrintWriter out = new PrintWriter("codeword.txt");
       
        //reading the bin.txt each line one by one and processing 
        while(true)
        {
            
            msg = br.readLine();
            if(msg == null || msg.length()==0)
            {
           
                break;
            }

            String cw = had.encode(msg);

            //cw has encoded msg writing it to file codeword.txt
             out.println(cw);
             
        }
        
        out.close();


        //to write the decoded pixel values in binary.
        out = new PrintWriter("decoded.txt");

        //reading the codewords from codeword.txt then introduce or not some errors in the codeword,
        //decode it and then write in decoded.txt
        br  = new BufferedReader(new FileReader("codeword.txt"));
        

    //---------------------------------channel simulation for error introduction ---------------------------//
        //options for number of codewords to be changed and number of error bit to be introduced is given by user.

        BitFlip flip; 
        int c= 0;
        int changeCodewords = Integer.parseInt(args[0]);
        int changeBits = Integer.parseInt(args[1]);
        while(true)
        { 
            //reading single codeword at a time.
            msg = br.readLine();
            if(msg == null || msg.length()==0)
            {
                break;
            }

            // introducing errors here
            if(c %100 == 0&&c<=changeCodewords)
            {
            
            flip = new BitFlip(changeBits,msg);
            String newCode = flip.xor();
           
           
            msg = newCode;
            // System.out.println(msg.length());
            
            }

            c++;
            int len = 0;
            // if(msg.compareTo("")!=0)
            String cw = had.decode(msg);
       
           
             out.println(cw);
             // if(count == 12000)
             //    break;
            
           
        }
     
        out.close();
    }
     catch(Exception e)
     {
        e.printStackTrace();
     }
}
}