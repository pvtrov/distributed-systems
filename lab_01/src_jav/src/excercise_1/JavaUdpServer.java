package excercise_1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class JavaUdpServer {

    public static void main(String args[])
    {
        System.out.println("----- JAVA UDP SERVER -----");
        int portNumber = 9008;

        try (DatagramSocket socket = new DatagramSocket(portNumber)){
            byte[] receiveBuffer = new byte[1024];

            while (true) {
                Arrays.fill(receiveBuffer, (byte) 0);
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(receivePacket);

                InetAddress senderAddress = receivePacket.getAddress();

                String msg = new String(receivePacket.getData(), receivePacket.getOffset(), receivePacket.getLength(), StandardCharsets.UTF_8);
                System.out.println("received msg: " + msg + " from: " + senderAddress.getHostAddress());

                byte[] replyBuffer = "thank you for your message".getBytes();
                DatagramPacket replyPacket = new DatagramPacket(replyBuffer, replyBuffer.length, senderAddress, receivePacket.getPort());
                socket.send(replyPacket);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
