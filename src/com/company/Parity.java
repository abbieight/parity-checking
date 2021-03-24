package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Parity{

    public static ArrayList<Boolean> parityChecker = new ArrayList<>();

    public static ArrayList<Boolean> encodeMessage(String text) {
        ArrayList<Boolean> binaryData = new ArrayList<>();
        byte[] asciiValues = text.getBytes(); // convert chars to ASCII bytes
        for (byte value: asciiValues) {
            boolean evenParity;
            // convert each ascii value to a sequence of 8 bits

            for (int bit=0; bit<8; bit++) {
                binaryData.add(isSet(value, bit));
            }

            int y = value ^ (value >> 1);
            y = y ^ (y >> 2);
            y = y ^ (y >> 4);
            y = y ^ (y >> 8);
            y = y ^ (y >> 16);

            evenParity = (y & 1) > 0;
            parityChecker.add(evenParity);
            binaryData.add(evenParity);
        }
        return binaryData;
    }

    // tests if value has a 1 at position 'bit'
    public static boolean isSet(int value, int bit){
        return (value&(1<<bit))!=0;
    }

    // returns a byte with the required bit set
    public static byte set(byte value, int bit){
        return (byte)(value|(1<<bit));
    }

    public static void displayBinary(ArrayList<Boolean> data) {
        int byteCounter = 0;
        for (Boolean bit: data) {
            if (bit) {
                System.out.print("1");
            } else {
                System.out.print("0");
            }
            byteCounter++;
            if (byteCounter % 9 == 0) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    public static boolean checkParity(int value){
        int y = value ^ (value >> 1);
        y = y ^ (y >> 2);
        y = y ^ (y >> 4);
        y = y ^ (y >> 8);
        y = y ^ (y >> 16);

        return (y & 1) > 0;
    }

    // convert a stream of bits into ASCII chars
    public static String decodeMessage(ArrayList<Boolean> data) {
        // get each group of 8 bits and assemble into a byte
        StringBuilder text = new StringBuilder();
        int counter = 0;
        for (int j=0; j<data.size()-11; j=j+9) {
            byte ascii = 0;
            for (int i = 0; i < 10; i++) {
                // DEBUG System.out.print(data.get(i+j) ? "1" : "0");
                if (data.get(i+j)) {
                    ascii = set(ascii, i);
                }
            }

            if (!checkParity((int)ascii) == parityChecker.get(counter)) {
                text.append("[PARITY ERROR]");
            } else {
                text.append(Character.toString((char) ascii));
            }


            // DEBUG System.out.println(" " + ascii + " = " + text);

            counter++;
        }
        return text.toString();
    }

    // invert 1 bit at random in the array
    public static ArrayList<Boolean> flipABit(ArrayList<Boolean> data) {
        Random random = new Random();
        int bitToChange = random.nextInt(data.size());
        data.set(bitToChange, !data.get(bitToChange));
        return data;
    }
}


