package com.itrainasia.media;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;

import static android.media.MediaRecorder.*;
import static android.media.MediaRecorder.OutputFormat.THREE_GPP;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    MediaRecorder mediaRecorder;
    String OUTPUT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OUTPUT = Environment.getExternalStorageDirectory()
                + "/mediaplayer.3gp";
    }

    void startPlay() throws IOException {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }

        File outFile = new File(OUTPUT);
        if (outFile.exists()) {
            outFile.delete();
        }

        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(OutputFormat, THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mediaRecorder.setOutputFile(OUTPUT);

        mediaRecorder.prepare();
        mediaRecorder.start();

    }



    public void onClick(View v) throws IOException, IllegalAccessException {
        switch (v.getId()) {
            case R.id.play_stop;
                stopPlay();
                break;
            case R.id.record_start;
                startRecord();
                break;
            case R.id.record_stop;
                stopRecord();
                break;
        }
        }

    void stopPlay(){
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

        }
        private void startRecord()throws IllegalAccessException,IOException {
        if (mediaRecorder !=null) {
            mediaRecorder.release();
        }

            }

                void stopRecord(){
                    if (mediaRecorder != null){
                        mediaRecorder.stop();

                    }

    }
}
