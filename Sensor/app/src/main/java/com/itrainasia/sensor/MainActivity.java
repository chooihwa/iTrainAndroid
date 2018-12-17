package com.itrainasia.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class VibrateActivity extends AppCompatActivity



    Vibrate vibrator;
    long partner[] = {2000, 3000, 1000, 2000}



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vibrator);

        vibrator=(Vibrator)getSystemService(VIBRATOR_SERVICE);

        }

        public void vibratePattern(View v){
        vibrator.vibrate(pattern,-1);
        }


    public void vibrateTime(View v) {
        vibrator.vibrate(500);}


    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        txtX.setText(String.valueOf(event.values[0]));
        txtY.setText(String.valueOf(event.values[1]));
        txtZ.setText(String.valueOf(event.values[2]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

