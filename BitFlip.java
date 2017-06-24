// this program flips the last n bits of a codeword. option is available as to how many bits have to be flipped in main program.


import java.util.*;
import java.math.BigInteger;
class BitFlip{
	String str;
	String bin;
	public BitFlip(int t,String bin){
		this.bin = bin;
		this.str = "";
		while(t-->0){
			str = str+"1";
		}
		while(128-str.length()>0){
			str = "0"+str;
		}


	}
	String xor(){
	
		BigInteger k1 = new BigInteger(str,2);
		BigInteger k2 = new BigInteger(bin,2);
		String a=  (k1.xor(k2)).toString(2);
		if(a.length()<bin.length()){
			while(128-a.length()>0){
			a = "0"+a;
		}

			}
			return a;
	}

}