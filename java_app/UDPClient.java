import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {

    public static void main(String[] args) throws Exception {
    DatagramSocket s = new DatagramSocket(8765); //client_port
    byte[] buf = new byte[1000];
    DatagramPacket dp = new DatagramPacket(buf, buf.length);
    InetAddress hostAddress = InetAddress.getByName("fd00::202:2:2:2");

    while (true) {

      BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
      String outMessage = stdin.readLine();
      if (outMessage.equals("bye"))
        break;
      String outString = "Client say: " + outMessage;
      buf = outString.getBytes();
      System.out.println("Invio "+outMessage);
      DatagramPacket out = new DatagramPacket(buf, buf.length, hostAddress, 5678); //server_port
      s.send(out);
      s.receive(dp);
      String rcvd = "rcvd from " + dp.getAddress() + ", " + dp.getPort() + ": " + new String(dp.getData(), 0, dp.getLength());
      System.out.println(rcvd);

    }

  }

}

