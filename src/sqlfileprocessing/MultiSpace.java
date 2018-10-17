package sqlfileprocessing;

import java.io.*;

public class MultiSpace {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String x = "00000000062CTN  00000000930KG 00000000000CM NNING BO";
		
		x = x.replaceAll("\\s{1,}", ",");
		
		System.out.println("x is " + x);

	}

}
