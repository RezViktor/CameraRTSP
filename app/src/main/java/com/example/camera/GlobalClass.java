package com.example.camera;

import android.app.Application;

interface OnIntegerChangeListener
{
    public void onIntegerChanged(int newValue);
}

public class GlobalClass extends Application implements OnIntegerChangeListener{

    private static OnIntegerChangeListener listener;
    public void onIntegerChanged(int newValue)
    {

    }
    public void setOnIntegerChangeListener(OnIntegerChangeListener listener)
    {
        this.listener = listener;
    }

    private static boolean sendDone = true;
    public boolean isSendDone() {
        return sendDone;
    }

    public void setSendDone(boolean sendDone) {
        this.sendDone = sendDone;
    }

    public String getTextToSend() {
        return textToSend;
    }

    public void setTextToSend(String textToSend) {
        this.textToSend = textToSend;
    }

    public static String textToSend;

    public static String getTextReceived() {
        return textReceived;
    }


    private static int value;
    public static void setTextReceived(String textReceived) {
        GlobalClass.textReceived = textReceived;
        value++;
        if(listener != null)
        {
            listener.onIntegerChanged(value);
        }
    }

    public static String textReceived;
}
