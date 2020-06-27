package com.example.camera;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Clientstuff {
    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream stream;
    private String gotdata;

    public String getGotdata() {
        return gotdata;
    }

    void startServer() throws IOException {

        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(50001);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //socket = null;
            //gotdata = null;
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //stream = null;
            try {
                //if (socket != null) {
                    stream = new DataInputStream(socket.getInputStream());
                //}
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                //if (stream != null) {
                    //gotdata = stream.readUTF();
                String inputLine;
                while (true) {
                    if (stream.available()>0)
                    gotdata = stream.readUTF();
                }
                //}
            } catch (IOException e) {
                e.printStackTrace();
            }
/*            try {
                //assert socket != null;
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }*/

            System.out.println("THE DATA WE HAVE GOT :" + gotdata);

        }).start();
    }
}
