package com.example.camera;

import android.app.Application;

public class GlobalClass extends Application {

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
}
