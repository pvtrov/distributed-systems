package excercise_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class JavaUdpClient {

    public String sendAndReceiveMessage(String message){
        int portNumber = 9008;

        try (DatagramSocket socket = new DatagramSocket()){
            InetAddress address = InetAddress.getByName("localhost");
            byte[] sendBuffer = message.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address, portNumber);
            socket.send(sendPacket);

            byte[] receiveBuffer = new byte[1024];
            Arrays.fill(receiveBuffer, (byte) 0);

            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            socket.receive(receivePacket);

            return new String(
                    receivePacket.getData(), receivePacket.getOffset(), receivePacket.getLength(), StandardCharsets.UTF_8
            );
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public JavaUdpClient(){
        System.out.println("----- JAVA UDP CLIENT -----");
    }

    public static void main(String args[]) {
        JavaUdpClient client = new JavaUdpClient();
        try (Scanner scanner = new Scanner(new BufferedReader((new InputStreamReader(System.in))))) {
            while (true) {
                String message = scanner.nextLine();
                String response = client.sendAndReceiveMessage(message);
                System.out.println("Server responsed: " + response);
            }
        }

    }

}
