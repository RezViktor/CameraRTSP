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

    public static String getIpAddress() {
        return ipAddress;
    }

    public static void setIpAddress(String ipAddress) {
        GlobalClass.ipAddress = ipAddress;
    }

    public static String ipAddress;

    public static String getFullIp() {
        return fullIp;
    }

    public static void setFullIp(String fullIp) {
        GlobalClass.fullIp = fullIp;
    }

    public static String fullIp;

    public static String getMacCount() {
        return macCount;
    }

    public static void setMacCount(String macCount) {
        GlobalClass.macCount = macCount;
    }

    public static String macCount;

    public static String getLine() {
        return line;
    }

    public static void setLine(String line) {
        GlobalClass.line = line;
    }

    public static String line;

    public static boolean isIsRecordingActive() {
        return isRecordingActive;
    }

    public static void setIsRecordingActive(boolean isRecordingActive) {
        GlobalClass.isRecordingActive = isRecordingActive;
    }

    public static boolean isRecordingActive = false;
}
