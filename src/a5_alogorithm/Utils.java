package a5_alogorithm;

import java.math.BigInteger;
import java.util.ArrayList;

public class Utils {

    static public String convertStringToStreamOfBits(String text) {
        String binary = new BigInteger(text.getBytes()).toString(2);
        binary = fillStringZeros(binary, 8 - binary.length() % 8);
        return binary;
    }

    static public String convertStreamOfBitsToString(String binary) {
//        String text = new String(new BigInteger(binary, 2).toByteArray());
        
        int I = 0;
        String s = "";
        String cipher = "";
        while (I<binary.length()-1){
            while (s.length()<8)
                s += binary.charAt(I++);
            char c = (char)Integer.parseInt(s,2);
            cipher += c;
            s = "";
        }
        
        
        
        
        
        
        return cipher;
    }

    

    public static String fillStringZeros(String bits, int count) {
        String zeros = "";
        for (int i = 0; i < count; i++) {
            zeros += "0";
        }
        return zeros + bits;
    }

    public static int majority(int x, int y, int z) {
        return x + y + z >= 2 ? 1 : 0;
    }

    public static int getXORBetweenListOfBits(ArrayList<Integer> list) {
        int result = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            result = result ^ list.get(i);
        }
        return result;
    }

    public static int getXOR(int x, int y) {
        return x ^ y;
    }

    public static String performXORBetweenTwoStrings(String first, String second) {
        String result = "";
        int length = Math.min(first.length(), second.length());
        for (int i = 0; i < length; i++) {
            int x = first.charAt(i) == '0' ? 0 : 1;
            int y = second.charAt(i) == '0' ? 0 : 1;
            int res = getXOR(x, y);
            result += res;
        }
        return result;
    }

}
