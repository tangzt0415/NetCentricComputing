import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Rfc865UdpServer {
    public Rfc865UdpServer() {
    }

    public static void main(String[] argv) {
        //
        // 1. Open UDP socket at well-known port
        //
        DatagramSocket socket = null;

        try {
            socket = new DatagramSocket(68);
            System.out.println("Socket created.");
        } catch (SocketException var9) {
        }

        while (true) {
            try {
                //
                // 2. Listen for UDP request from client
                //
                System.out.println("waiting for client...");
                byte[] received = new byte[1024];
                DatagramPacket request = new DatagramPacket(received, received.length);
                socket.receive(request);
                String message_incoming = new String(request.getData(), 0, request.getLength());
                System.out.println(message_incoming);
                //
                // 3. Send UDP reply to client
                //
                String quote = "NCC is fun!";
                InetAddress request_address = request.getAddress();
                int request_port = request.getPort();
                DatagramPacket reply = new DatagramPacket(quote.getBytes(), quote.length(), request_address, request_port);
                socket.send(reply);
            } catch (IOException var10) {
            }
        }
    }
}