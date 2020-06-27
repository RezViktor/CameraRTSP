package com.example.camera;

import android.media.MediaRecorder;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private MediaRecorder camerra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //camerra = camera.getCameraInstance();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Clientstuff receive = new Clientstuff();
        GlobalClass globalClass = new GlobalClass();
        serverstuff send = new serverstuff();
        Button btnn = findViewById(R.id.button);
        EditText editText;
        editText = (EditText) findViewById(R.id.editText);
        btnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalClass.setTextToSend(editText.getText().toString());

                send.sendData();
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
               Snackbar.make(findViewById(R.id.editText), globalClass.getTextReceived() , Snackbar.LENGTH_LONG)
                       .setAction("Action", null).show();
           }
       });
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
