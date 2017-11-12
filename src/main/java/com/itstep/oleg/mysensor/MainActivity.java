package com.itstep.oleg.mysensor;

import android.app.ActivityManager;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity  implements SensorEventListener
{

    private  SensorManager SM;
    private Sensor accel;

    @Override
    public void onResume()
    {
        super.onResume();
        this.SM.registerListener(this, this.accel, SensorManager.SENSOR_DELAY_GAME);
    }

    public void onPause()
    {
        super.onPause();
        this.SM.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent event)
    {
       float [] values = event.values;
        switch (event.sensor.getType())
        {
            case Sensor.TYPE_ACCELEROMETER:
            {
                TextView tvTextX = (TextView)this.findViewById(R.id.tvTextX);
                TextView tvTextY = (TextView)this.findViewById(R.id.tvTextY);
                TextView tvTextZ = (TextView)this.findViewById(R.id.tvTextZ);
                // Здесь можно обрабатывать данные от сенсора

                tvTextX.setText(String.valueOf(values[0]));
                tvTextY.setText(String.valueOf(values[1]));
                tvTextZ.setText(String.valueOf(values[2]));


            }
            break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {
        Log.d("##########", "onAccuracyChanged : " + sensor + ", accurasy : " + accuracy);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.SM = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
        this.accel = this.SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);



    }
}





















