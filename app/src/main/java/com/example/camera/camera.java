package com.example.camera;

import android.media.MediaRecorder;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;

import java.io.File;
import java.net.Socket;

import static android.os.Environment.DIRECTORY_MOVIES;

public class camera {
//    public static MediaRecorder getCameraInstance(Socket socket){
        public static MediaRecorder getCameraInstance(){
//        ParcelFileDescriptor pfd = ParcelFileDescriptor.fromSocket(socket);
            MediaRecorder c = null;
        try {
            c.setAudioSource(MediaRecorder.AudioSource.MIC);
            c.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            c.setVideoSource(MediaRecorder.VideoSource.CAMERA);
            c.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
            c.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
//            c.setOutputFile(pfd.getFileDescriptor());
            c.setOutputFile("DIRECTORY_MOVIES/firs.mp4");
//            c.prepare();
            //c.start();   // Recording is now started
        } catch (Exception e){
        }
        return c;
    }
}

