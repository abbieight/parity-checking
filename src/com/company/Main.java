package com.company;

import java.util.ArrayList;

public class Main {

    public static void sendMessage(String text) {
        // simulates sending a message
        // with a single-bit transmission error
        System.out.println("Sending: " + text);
        ArrayList<Boolean> data;
        data = Normal.encodeMessage(text);
        System.out.print("Encoded: ");
        Normal.displayBinary(data);

        // corrupt message during 'transmission'
        data = Normal.flipABit(data);

        System.out.print("Received:");
        Normal.displayBinary(data);
        String receivedText = Normal.decodeMessage(data);
        System.out.print("Decoded: ");
        System.out.println(receivedText);
    }

    public static void sendMessageWithParity(String text) {
        // simulates sending a message
        // with a single-bit transmission error
        System.out.println("Sending: " + text);
        ArrayList<Boolean> data;
        data = Parity.encodeMessage(text);
        System.out.print("Encoded: ");
        Parity.displayBinary(data);

        // corrupt message during 'transmission'
        data = Parity.flipABit(data);

        System.out.print("Received:");
        Parity.displayBinary(data);
        String receivedText = Parity.decodeMessage(data);
        System.out.print("Decoded: ");
        System.out.println(receivedText);
    }


    public static void main(String[] args) {
        // demonstrate error correction/detection

        sendMessageWithParity("hello world");

    }

}

