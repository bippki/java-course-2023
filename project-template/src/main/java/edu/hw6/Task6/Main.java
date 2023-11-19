package edu.hw6.Task6;

import java.io.*;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;

public class Main {
    public static void main(String[] args) {
        for (int port = 0; port < 49151; port++) {
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                System.out.print("TCP");
                System.out.print(" " + port);
                System.out.println(" " +getServiceName(port));
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                DatagramSocket datagramSocket = new DatagramSocket(port);
                System.out.print("UDP");
                System.out.print(" "+port);
                System.out.println(" " +getServiceName(port));
                datagramSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getServiceName(int port) {
        return switch (port) {
            case 135 -> "ERMAR";
            case 137 -> "NetBIOS Naming Service";
            case 138 -> "NetBIOS Datagram Service";
            case 139 -> "NetBIOS Session Service";
            case 445 -> "Microsoft-DS Active Directory";
            case 843 -> "Adobe Flash";
            case 1900 -> "Simple Service Discovery Protocol (SSDP) Dynamic Web Service Discovery";
            case 3702, 42420, 5050 -> "UDP";
            case 5040, 5939, 6463, 6942, 17680 -> "TCP";
            case 5353 -> "Multicast DNS";
            case 5355 -> "Link-Local Multicast Name Resolution (LLMINR)";
            case 17500 -> "Dropbox";
            case 27017 -> "MongoDB";
            default -> "Unknown";
        };
    }
}
