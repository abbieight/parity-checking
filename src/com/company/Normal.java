package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Normal {
    // contains the non-error checking version of the routines

    // convert the text string into a stream of bits
    // representing the ASCII values of each char
    public static ArrayList<Boolean> encodeMessage(String text) {
        ArrayList<Boolean> binaryData = new ArrayList<Boolean>();
        byte[] asciiValues = text.getBytes(); // convert chars to ASCII bytes
        for (byte value: asciiValues) {
            // convert each ascii value to a sequence of 8 bits
            for (int bit=0; bit<8; bit++) {
                binaryData.add(isSet(value, bit));
            }
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

    // print out the string representing the binary sequence
    public static void displayBinary(ArrayList<Boolean> data) {
        int byteCounter = 0;
        for (Boolean bit: data) {
            if (bit) {
                System.out.print("1");
            } else {
                System.out.print("0");
            }
            byteCounter++;
            if (byteCounter % 8 == 0) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    // convert a stream of bits into ASCII chars
    public static String decodeMessage(ArrayList<Boolean> data) {
        // get each group of 8 bits and assemble into a byte
        String text = "";
        for (int j=0; j<data.size(); j=j+8) {
            byte ascii = 0;
            for (int i = 0; i < 8; i++) {
                // DEBUG System.out.print(data.get(i+j) ? "1" : "0");
                if (data.get(i+j)) {
                    ascii = set(ascii, i);
                }
            }
            text = text + Character.toString((char) ascii);
            // DEBUG System.out.println(" " + ascii + " = " + text);
        }
        return text;
    }

    // invert 1 bit at random in the array
    public static ArrayList<Boolean> flipABit(ArrayList<Boolean> data) {
        Random random = new Random();
        int bitToChange = random.nextInt(data.size());
        data.set(bitToChange, !data.get(bitToChange));
        return data;
    }

}

