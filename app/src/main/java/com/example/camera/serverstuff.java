package com.example.camera;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.io.DataOutputStream;
import java.net.Socket;

import androidx.core.content.ContextCompat;

import com.example.camera.GlobalClass;

public class serverstuff {
    GlobalClass globalClass = new GlobalClass();
    void sendData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket("192.168.0.14", 50001);
                    DataOutputStream stream = new DataOutputStream(socket.getOutputStream());
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
}
