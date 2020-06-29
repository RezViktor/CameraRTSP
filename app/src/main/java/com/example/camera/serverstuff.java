package com.example.camera;

import android.content.Context;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.net.Socket;

import androidx.core.content.ContextCompat;

import com.example.camera.GlobalClass;

public class serverstuff {
    GlobalClass globalClass = new GlobalClass();
    private static Socket socket = null;
    private static DataOutputStream stream = null;
    void sendData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                getClientList();
                try {
                    if (socket == null) {
                        socket = new Socket("192.168.0.19", 50001);
                    }
                    if (stream == null) {
                        stream = new DataOutputStream(socket.getOutputStream());
                    }
                    stream.writeUTF(globalClass.getTextToSend());
                    stream.flush();
//                    stream.close();
//                    socket.close();
                    globalClass.setSendDone(true);
                } catch (Exception e) {
                    globalClass.setSendDone(false);
                }
            }
        }).start();
    }
    public void displaySnackbar (Context context, View view, String s)
    {
        Snackbar snack = Snackbar.make(view, s, Snackbar.LENGTH_LONG);
        View sbview = snack.getView();
        sbview.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
        TextView textView = (TextView) sbview.findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        snack.show();
    }
    public void getClientList() {   //not used. maybe in android <=9
        int macCount = 0;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("/proc/net/arp"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] splitted = line.split(" +");
                if (splitted != null ) {
                    // Basic sanity check
                    String mac = splitted[3];
                    System.out.println("Mac : Outside If "+ mac );
                    if (mac.matches("..:..:..:..:..:..")) {
                        macCount++;
                   /* ClientList.add("Client(" + macCount + ")");
                    IpAddr.add(splitted[0]);
                    HWAddr.add(splitted[3]);
                    Device.add(splitted[5]);*/
                        System.out.println("Mac : "+ mac + " IP Address : "+splitted[0] );
                        System.out.println("Mac_Count  " + macCount + " MAC_ADDRESS  "+ mac);
                        GlobalClass.setIpAddress(splitted[0]);
                        GlobalClass.setFullIp(splitted.toString());
                        GlobalClass.setMacCount(Integer.toString(macCount));
                        GlobalClass.setLine(line);
                    }
               /* for (int i = 0; i < splitted.length; i++)
                    System.out.println("Addressssssss     "+ splitted[i]);*/

                }
            }
        } catch(Exception e) {

        }
    }
}
