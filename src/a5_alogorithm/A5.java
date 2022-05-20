package a5_alogorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class A5 {

    private ShiftRegister R1;
    private ShiftRegister R2;
    private ShiftRegister R3;

    private String sessionKey;
    private String frameKey;

    private String sessionKeyAsStream;
    private String frameKeyAsStream;

    public A5(String sessionKey, String frameKey) {
        this.sessionKey = sessionKey;
        this.frameKey = frameKey;

        R1 = new ShiftRegister(19, 8, new ArrayList<>(Arrays.asList(13, 16, 17, 18)));
        R2 = new ShiftRegister(22, 10, new ArrayList<>(Arrays.asList(20, 21)));
        R3 = new ShiftRegister(23, 10, new ArrayList<>(Arrays.asList(7, 20, 21, 22)));
        sessionKeyAsStream = Utils.convertStringToStreamOfBits(sessionKey);
        frameKeyAsStream = Utils.convertStringToStreamOfBits(frameKey);

        if (sessionKeyAsStream.length() < 64) {
            throw new IllegalArgumentException("Session Key must contains 64 bit at least");
        }

        if (frameKeyAsStream.length() < 22) {
            throw new IllegalArgumentException("Frame Key must contains 22 bit at least");
        }

    }

    public String encrypt(String plainText) {
        String encryptTextAsStream = "";
        String keyStream = getKeyStream();
        String plainTextAsStream = Utils.convertStringToStreamOfBits(plainText);
        for (int i = 0; i < plainTextAsStream.length(); i += 228) {
            String block = "";
            if (i + 228 < plainTextAsStream.length()) {
                block = plainTextAsStream.substring(i, i + 228);
            } else {
                block = plainTextAsStream.substring(i);
            }
            encryptTextAsStream += Utils.performXORBetweenTwoStrings(block, keyStream);
        }

        System.out.println("encryptTextAsStream :" + encryptTextAsStream);
        return Utils.convertStreamOfBitsToString(encryptTextAsStream);
    }

    public String decrypt(String cipherText) {
        String plainText = "";
        String cipherTextAsStream = Utils.convertStringToStreamOfBits(cipherText);
        String keyStream = getKeyStream();
        for (int i = 0; i < cipherTextAsStream.length(); i += 228) {
            String block = "";
            if (i + 228 < cipherTextAsStream.length()) {
                block = cipherTextAsStream.substring(i, i + 228);
            } else {
                block = cipherTextAsStream.substring(i);
            }
            plainText += Utils.performXORBetweenTwoStrings(block, keyStream);
        }
        return Utils.convertStreamOfBitsToString(plainText);

    }

    private void performXORBetweenRegistersAndKey(String keyStream, int length) {

        for (int i = 0; i < length; i++) {

            int keyBit = Integer.parseInt(String.valueOf(keyStream.charAt(i)));
            R1.shifLeftToRightWithInitialFirstIndex(keyBit ^ R1.getXORBetweenTappedBits());
            R2.shifLeftToRightWithInitialFirstIndex(keyBit ^ R2.getXORBetweenTappedBits());
            R3.shifLeftToRightWithInitialFirstIndex(keyBit ^ R3.getXORBetweenTappedBits());

            R1.printRegisterValues();
            R2.printRegisterValues();
            R3.printRegisterValues();

        }

    }

    private String performIrregularClocking(int count) {
        String keyStream = "";
        for (int i = 0; i < count; i++) {
            keyStream += Utils.getXORBetweenListOfBits(new ArrayList<>(Arrays.asList(
                    R1.getLastRegisterValue(),
                    R2.getLastRegisterValue(),
                    R3.getLastRegisterValue()
            )));

            int major = Utils.majority(R1.getClockBitValue(), R2.getClockBitValue(), R3.getClockBitValue());
            if (R1.getClockBitValue() == major) {
                R1.shifLeftToRightWithInitialFirstIndex(R1.getXORBetweenTappedBits());
            }

            if (R2.getClockBitValue() == major) {
                R2.shifLeftToRightWithInitialFirstIndex(R2.getXORBetweenTappedBits());
            }

            if (R3.getClockBitValue() == major) {
                R3.shifLeftToRightWithInitialFirstIndex(R3.getXORBetweenTappedBits());
            }
        }
        return keyStream;
    }

    private String getKeyStream() {
        // step1
        performXORBetweenRegistersAndKey(sessionKeyAsStream, 64);

        // step2
        performXORBetweenRegistersAndKey(frameKeyAsStream, 22);

        // step3       
        performIrregularClocking(100);
        // step4
        String keyStream = performIrregularClocking(228);
        System.out.println("keyStream: " + keyStream);
        return keyStream;
    }
}
