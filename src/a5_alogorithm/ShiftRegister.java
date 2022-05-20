package a5_alogorithm;

import java.util.ArrayList;

public class ShiftRegister {

    private int registerLength;
    private int clockingBit;
    private ArrayList<Integer> tappedBits;
    private int[] register;

    public ShiftRegister(int registerLength, int clockingBit, ArrayList<Integer> tappedBits) {
        this.registerLength = registerLength;
        this.clockingBit = clockingBit;
        this.tappedBits = tappedBits;
        register = new int[registerLength];
        for (int i = 0; i < registerLength; i++) {
            register[i] = 0;
        }
    }

    public void shifLeftToRightWithInitialFirstIndex(int value) {
        for (int i = registerLength - 1; i >= 1; i--) {
            register[i] = register[i - 1];
        }
        register[0] = value;
    }

    public int getXORBetweenTappedBits() {
        int result = register[tappedBits.get(0)];

        for (int i = 1; i < tappedBits.size(); i++) {
            result = result ^ register[tappedBits.get(i)];
        }

        return result;
    }

    public int getRegisterLength() {
        return registerLength;
    }

    public int getClockingBit() {
        return clockingBit;
    }

    public int getClockBitValue() {
        return register[this.clockingBit];
    }

    public int getLastRegisterValue() {
        return register[this.registerLength - 1];
    }

    public ArrayList<Integer> getTappedBits() {
        return tappedBits;
    }

    public void printRegisterValues() {
        for (int i = 0; i < registerLength; i++) {
            System.out.print(register[i] + "  ");
        }
        System.out.println("");
    }

    @Override
    public String toString() {
        String details = "";
        details += "Register Length : " + this.registerLength + "\n";
        details += "Register clocked bit : " + this.clockingBit + "\n";
        details += "Register tapped bits : " + this.tappedBits.toString() + "\n";

        return details;
    }

}
