package com.example.sensorsapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionsActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensor = null;
    private Sensor sensor2 = null;
    private TextView tvItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);


        tvItems = findViewById(R.id.tv_Items);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        tvItems.setText(MainActivity.questionsList.get(1));

        if ( sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) != null && sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            sensor2 = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }else {
            Toast.makeText(this , "Sensor not found" , Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY){
            int val =(int)sensorEvent.values[0];
//            if(val > 0){
//
//            }

        }
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            int val =(int)sensorEvent.values[2];
            if(val < -5){
                Intent intent = new Intent(getApplicationContext() , MainActivity.class);
                startActivity(intent);
            }
        }

    }
    @Override
    protected void onResume(){
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensor2, SensorManager.SENSOR_DELAY_NORMAL);

    }
    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this);
    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {


    }

}
