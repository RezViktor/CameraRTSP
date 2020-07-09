package com.example.camera;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;

import com.example.camera.R;
import com.example.camera.camera;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.media.MediaRecorder;
import android.widget.VideoView;

import java.io.IOException;

import static android.os.Environment.DIRECTORY_DCIM;

public class MainActivity extends AppCompatActivity {

    private MediaRecorder camerra;
    ProgressDialog mDialog;
    VideoView videoView;
    ImageButton play_button;

//    String videoURL = "https://www.radiantmediaplayer.com/media/big-buck-bunny-360p.mp4";
    String videoURL = "rtsp://192.168.55.152:8554/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        MediaRecorder c = new MediaRecorder();
        Clientstuff receive = new Clientstuff();
        GlobalClass globalClass = new GlobalClass();
        serverstuff send = new serverstuff();
        EditText editText;
        editText = (EditText) findViewById(R.id.editText);
        WebView webView;

        Button btnn = findViewById(R.id.button);
        btnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalClass.setTextToSend(editText.getText().toString());

                send.sendData();
                Snackbar.make(v, GlobalClass.getLine(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Button btnn2 = findViewById(R.id.button2);
        btnn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    receive.startServer();
                    Snackbar.make(v, "Socket created!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Snackbar.make(v, "Creating socket failed!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
        Button btnn3 = findViewById(R.id.button3);
        btnn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, GlobalClass.getTextReceived(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Button btnn4 = findViewById(R.id.button4);
        btnn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(globalClass.isRecordingActive){
                    try {
                        c.stop();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    }
                    globalClass.setIsRecordingActive(false);
                    Snackbar.make(v, "Record stopped", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else{
                    try {
                        //c.setAudioSource(MediaRecorder.AudioSource.MIC);
//                        c.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
//                        c.setVideoSource(MediaRecorder.VideoSource.CAMERA);
//                        c.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
                        c.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
//            c.setOutputFile(pfd.getFileDescriptor());
                        c.setOutputFile(DIRECTORY_DCIM+"/firs.mp4");
                        c.prepare();
                        c.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    globalClass.setIsRecordingActive(true);
                    Snackbar.make(v, "Record started", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        globalClass.setOnIntegerChangeListener(new OnIntegerChangeListener() {
           @Override
           public void onIntegerChanged(int newValue) {
               Snackbar.make(findViewById(R.id.editText), globalClass.getTextReceived(), Snackbar.LENGTH_LONG)
                       .setAction("Action", null).show();
           }
       });
        videoView = (VideoView) findViewById(R.id.videoView);
        play_button = (ImageButton) findViewById(R.id. play_button);
        play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog = new ProgressDialog(MainActivity.this);
                mDialog.setMessage("Please wait");
                mDialog.setCanceledOnTouchOutside(false);
                mDialog.show();

                try {
                    if (!videoView.isPlaying()) {
                        Uri uri = Uri.parse(videoURL);
                        videoView.setVideoURI(uri);
                        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                play_button.setImageResource(R.drawable.ic_action_name);
                            }
                        });
                    }
                    else
                    {
                        videoView.pause();
                        play_button.setImageResource(R.drawable.ic_action_name);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                videoView.requestFocus();
                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mDialog.dismiss();
                        mp.setLooping(true);
                        videoView.start();
                        play_button.setImageResource(R.drawable.ic_action_pause);
                    }
                });
            }
        });

//        webView = (WebView) findViewById(R.id.webView);
//        webView.getSettings().setJavaScriptEnabled(true);
//
//        webView.loadUrl("file:///android_asset/index.html");
    }
    public void displaySnackbar (View view,String s)
    {
        Snackbar snack = Snackbar.make(view, s, Snackbar.LENGTH_LONG);
        View sbview = snack.getView();
        sbview.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
        TextView textView = (TextView) sbview.findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setTextColor(getResources().getColor(R.color.colorPrimary));
        snack.show();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
