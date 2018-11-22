package com.example.edu.pedometer_1122;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener, View.OnClickListener {

    float currentY=0.0f, previousY=0.0f, acceleration=0.0f;
    int threshold = 3,steps=0;
    TextView textViewSteps, textViewGx, textViewGy, textViewGz;
    SeekBar seekBarSensitive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        seekBarSensitive = findViewById(R.id.seekBarSensitive);
//        threshold = seekBarSensitive.getProgress();

        SensorManager sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(
                Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);

        textViewSteps = findViewById(R.id.textViewSteps);
        textViewGx = findViewById(R.id.textViewGx);
        textViewGy = findViewById(R.id.textViewGy);
        textViewGz = findViewById(R.id.textViewGz);

        ((Button)findViewById(R.id.buttonReset)).setOnClickListener(this);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        currentY = y;
        if(Math.abs(currentY-previousY)>threshold){
            steps++;
            textViewSteps.setText(String.valueOf(steps));

        }textViewGx.setText(String.valueOf(x));
        textViewGy.setText(String.valueOf(y));
        textViewGz.setText(String.valueOf(z));
        previousY = y;

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onClick(View v) {
        textViewSteps.setText("");
        steps = 0;
    }
}
