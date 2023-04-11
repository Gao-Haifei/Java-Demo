package Socket;

import com.google.gson.JsonObject;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Server {

    public static void main(String[] args) throws IOException {
        System.out.println("服务器开始");
        ServerSocket serverSocket = new ServerSocket(9999);
        while(true){
            Socket socket = serverSocket.accept();
            new Thread(new Server_listen(socket)).start();
        }

    }
}


class Server_listen implements Runnable{
    Socket socket;

    Server_listen(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try{
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            while(true){
                System.out.println(ois.readObject().toString());
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
