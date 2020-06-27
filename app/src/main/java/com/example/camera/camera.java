package com.example.camera;

import android.media.MediaRecorder;
import android.os.ParcelFileDescriptor;

import java.net.Socket;

public class camera {
    public static MediaRecorder getCameraInstance(Socket socket){
        ParcelFileDescriptor pfd = ParcelFileDescriptor.fromSocket(socket);
        MediaRecorder c = null;
        try {
            c.setAudioSource(MediaRecorder.AudioSource.MIC);
            c.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            c.setVideoSource(MediaRecorder.VideoSource.CAMERA);
            c.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
            c.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            c.setOutputFile(pfd.getFileDescriptor());
            c.prepare();
            c.start();   // Recording is now started
        } catch (Exception e){
        }
        return c;
    }
}

