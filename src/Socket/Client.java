package Socket;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static Socket socket;
    public static boolean Connet_ = false;

    public static void main(String[] args) {
        connet();
        if (Connet_){
            new Thread(new Client_Send(socket)).start();
        }

    }

    public static void connet(){
        try {
            socket = new Socket("127.0.0.1",9999);
            Connet_ = true;
        }catch (Exception e){
            e.printStackTrace();
            Connet_ = false;
        }
    }

}

class Client_Listen implements Runnable{
    Socket socket;

    Client_Listen(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            while (true){
                System.out.println(ois.read());
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

class Client_Send implements Runnable{
    Socket socket;

    Client_Send(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());


            Person person = new Person();

            while (true){
                System.out.print("请输入：");
                Scanner scanner = new Scanner(System.in);
                String str = scanner.nextLine();
                person.setId("127.0.0.1");
                person.setValue(str);


                Object obj = person.toString();

                oos.writeObject(obj);
                oos.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
