package message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SenderReceiver {
	
	
    public static void main(String[] args) {
    	
    	ChatWindow window = new ChatWindow();
    	
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(12345)) {
                while (true) {
                    try (Socket socket = serverSocket.accept()) {
                        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                        while (true) {
                            String message = inputStream.readUTF();
                            window.listModel.addElement("recieved: " + message);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        try (Socket socket = new Socket("localhost", 12345)) {
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            window.oStream = outputStream;
            while (true) {
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}