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
		while(256-str.length()>0){
			str = "0"+str;
		}


	}
	String xor(){
		BigInteger k1 = new BigInteger(str,2);
		BigInteger k2 = new BigInteger(bin,2);
		return (k1.xor(k2)).toString(2);
			
	}
	// public static void main(String[] args) {
	// 	BitFlip b = new BitFlip(2);
	// 	System.out.println(b.xor(bin));
	// }
}