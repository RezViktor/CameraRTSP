package com.example.camera;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Clientstuff {
    private static ServerSocket serverSocket = null;
    private static Socket socket = null;
    private static DataInputStream stream = null;
    private static String gotdata = null;

    public String getGotdata() {
        return gotdata;
    }

    void startServer() throws IOException {

        new Thread(() -> {
            try {
                if (serverSocket == null) {
                    serverSocket = new ServerSocket(50001);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            //socket = null;
            //gotdata = null;
            try {
                if (socket == null) {
                    socket = serverSocket.accept();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            //stream = null;
            try {
                if (stream == null) {
                    stream = new DataInputStream(socket.getInputStream());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                while (true) {
                    if (stream.available()>0) {
                        gotdata = stream.readUTF();
                        GlobalClass.setTextReceived(gotdata);
                    }



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
