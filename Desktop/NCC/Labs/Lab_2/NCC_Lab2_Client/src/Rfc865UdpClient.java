import java.io.IOException;
import java.net.*;

public class Rfc865UdpClient { //class to get hostname,address and set destination server
    private String hostAddress;
    private String hostName;
    private String serverAddress;

    public String getHostAddress() {
        return hostAddress;
    }

    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public Rfc865UdpClient() throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        this.hostAddress = inetAddress.getHostAddress();
        this.hostName = inetAddress.getHostName();
        //this.serverAddress = "172.21.150.53"; //set to QOTD server by default
    }


    public static void main(String[] argv) {
        //
        // 1. Open UDP socket
        //
        DatagramSocket socket = null;
        String message = "Tang Zhengtian, TS4, ";
        try {
            socket = new DatagramSocket();
            System.out.println("Socket Created");
        } catch (SocketException var11) {
        }

        try {
            //
            // 2. Send UDP request to server
            //
            Rfc865UdpClient client = new Rfc865UdpClient(); //initiate client class to easily obtaining ip and host name
            message += client.getHostAddress(); //adds client IP address into message
            //client.setServerAddress(client.getHostAddress()); //setting to own server
            client.setServerAddress("172.21.150.53"); //setting to QoTD server -- HWLab1
            InetAddress ip_address = InetAddress.getByName(client.getServerAddress());
            DatagramPacket request = new DatagramPacket(message.getBytes(), message.length(), ip_address, 17); //sends default msg
            socket.send(request);
            //
            // 3. Receive UDP reply from server
            //
            byte[] buffer = new byte[1024];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            socket.receive(reply);
            String reply_message = new String(reply.getData(), 0, reply.getLength());
            System.out.println(reply_message);

        } catch (IOException var10) {

        }
    }
}
//Name: Tang Zhengtian
//Group: TS4
//IP Address: 10.27.7.185
//Message Received: The beginning is the most important part of the work - Plato.