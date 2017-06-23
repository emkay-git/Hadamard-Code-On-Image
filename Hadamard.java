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
    
  
    /*
     * Hadamard encode
     */
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
    
    /*
     * Hadamard decode
     */
    public String decode(String codeword)
    {
        //if codeword length != 2^k bit then it cannot be decoded
        if(codeword.length() != new Double(Math.pow(2, k)).intValue()) return null;
        
        int[] cw = new int[codeword.length()];
        for(int i = 0; i < codeword.length(); ++i)
        {
            cw[i] = Integer.parseInt(codeword.substring(i, i+1));
        }
        
        //compute the weight of each index to original message
        //index = (row number) of Hadamard code
        int[] weight = weight(cw, hadCode);
        
        //get the position of the greatest weight
        int idx = 0;
        for (int i = 0; i < weight.length; ++i) idx = (Math.abs(weight[i]) > Math.abs(weight[idx])? i : idx);
        if (weight[idx] < 0) idx += weight.length;
        
        String word = Integer.toBinaryString(idx);
        return word;
    }
    
  
    private int[] weight(int[] v, int[][] h)
    {
        int[] r = new int[h[0].length];
        // System.out.println(h[0].length);
        // 0 -> -1 for v
        int i = 0;
        while(i < v.length) v[i] = (v[i++] == 0? -1 : 1);
        
        //dot product of v and h
        for(int hj = 0; hj < h[0].length; ++hj)
        {
            for(int vj = 0; vj < v.length; ++vj)
            {
                //h is hadamard code, to get hT simply swap row/column index
                //so instead of using hT[vj][hj]
                //h[hj][vj] is used because: h[i][j] = hT[j][i]
                r[hj] += (v[vj] * h[hj][vj]);
            }
        }
        
        return r;
    }
    
   
  
  

    /*
     * Main method. For testing only
     */
    public static void main(String[] args) 
    {   
        try{
        int k = 8;
        Hadamard had = new Hadamard(k);
        BufferedReader br  = new BufferedReader(new FileReader("bin.txt"));
        // System.out.println(hadCode[0].length+" "+hadCode.length);


        // String[] msg = .split(System.getProperty("line.separator"));
        String msg = "";
        int count = 0;
        PrintWriter out = new PrintWriter("codeword.txt");
        while(true)
        {
            count++;
            msg = br.readLine();
            if(msg == null || msg.length()==0)
            {
            //     if(msg == null)
            //     System.out.println(count);
            // else 
            //     System.out.println(count+" lol");
                break;
            }
            // if(msg.compareTo("")!=0)
            String cw = had.encode(msg);
             out.println(cw);
             // if(count == 12000)
             //    break;
        }
        out.close();

        out = new PrintWriter("decoded.txt");
        br  = new BufferedReader(new FileReader("codeword.txt"));
        BitFlip flip; 
        int c= 0;
        while(true)
        {
            
            msg = br.readLine();
            if(msg == null || msg.length()==0)
            {
                break;
            }

            //introducing errors here
            if(c %10 == 0)
            {
                
            flip = new BitFlip(93,msg);
            String newCode = flip.xor();
            // System.out.println(msg);
            // System.out.println(newCode);
            msg = newCode;
            
            }
            c++;
            int len = 0;
            // if(msg.compareTo("")!=0)
            String cw = had.decode(msg);
            // if(cw==null||cw.length()==0)
            //     {len = 0;
            // cw = "";}
            // else 
                len = cw.length();

             int a = 8 - len;
            while(a-->0)
            {
                cw="0"+cw;
            }
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