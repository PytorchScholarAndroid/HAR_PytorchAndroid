package har_android_pytorch.har_android;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    Toolbar mToolbar;

    SensorManager sensorManager;
    Sensor gyroscopeSensor;
    Sensor accelerometerSensor;
    Sensor stepCounterSensor;
    Sensor linearAccelerationSensor;

    /** FOR TESTING PURPOSE **/
    TextView accX_textview;
    TextView accY_textview;
    TextView accZ_textview;
    TextView gyroX_textview;
    TextView gyroY_textview;
    TextView gyroZ_textview;
    TextView linX_textview;
    TextView linY_textview;
    TextView linZ_textview;


    /** Variables for linear acceleration (without gravity) Vector m/s²**/
    float linearacceleration_x_value = 0;
    float linearacceleration_y_value = 0;
    float linearacceleration_z_value = 0;


    /** Variables for step Counter**/
    float steps_taken_since_boot = 0;

    /** Variables for accelerometer Vector m/s²**/
    float accelerometer_x_value = 0;
    float accelerometer_y_value = 0;
    float accelerometer_z_value = 0;

    /** Variables for gyroscope Vector in Degree**/
    float gyroscope_x_value = 0;
    float gyroscope_y_value = 0;
    float gyroscope_z_value = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = findViewById(R.id.main_toolbar);
        mToolbar.setTitle(R.string.app_name);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));

        MainFragment mainFragment = new MainFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, mainFragment);
        fragmentTransaction.commit();


        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        linearAccelerationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);



        sensorManager.registerListener(gyroscopeSensorListener,
                gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(accelerometerListener,
                accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(stepCounterListener,
                stepCounterSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(linearAccelerationListener,
                linearAccelerationSensor, SensorManager.SENSOR_DELAY_NORMAL);


    }

    SensorEventListener linearAccelerationListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            linearacceleration_x_value = sensorEvent.values[0];
            linearacceleration_y_value = sensorEvent.values[1];
            linearacceleration_z_value = sensorEvent.values[2];

            linX_textview = findViewById(R.id.tv_linaccX);
            linY_textview = findViewById(R.id.tv_linaccY);
            linZ_textview = findViewById(R.id.tv_linaccZ);

            linX_textview.setText( Float.toString(linearacceleration_x_value));
            linY_textview.setText( Float.toString(linearacceleration_y_value));
            linZ_textview.setText( Float.toString(linearacceleration_z_value));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    };

    SensorEventListener stepCounterListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            steps_taken_since_boot = sensorEvent.values[0];
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    };



    SensorEventListener accelerometerListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            accelerometer_x_value = sensorEvent.values[0];
            accelerometer_y_value = sensorEvent.values[1];
            accelerometer_z_value = sensorEvent.values[2];

            accX_textview = findViewById(R.id.tv_accelerationX);
            accY_textview = findViewById(R.id.tv_accelerationY);
            accZ_textview = findViewById(R.id.tv_accelerationZ);

            accX_textview.setText( Float.toString(accelerometer_x_value));
            accY_textview.setText( Float.toString(accelerometer_y_value));
            accZ_textview.setText( Float.toString(accelerometer_z_value));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    };

    SensorEventListener gyroscopeSensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            gyroscope_x_value = sensorEvent.values[0];
            gyroscope_y_value = sensorEvent.values[1];
            gyroscope_z_value = sensorEvent.values[2];
         //   Log.v("GyroscopeX", Integer.toString((int)gyroscope_x_value));

            gyroX_textview = findViewById(R.id.tv_gyroscopeX);
            gyroY_textview = findViewById(R.id.tv_gyroscopeY);
            gyroZ_textview = findViewById(R.id.tv_gyroscopeZ);

            gyroX_textview.setText( Float.toString(gyroscope_x_value));
            gyroY_textview.setText( Float.toString(gyroscope_y_value));
            gyroZ_textview.setText( Float.toString(gyroscope_z_value));

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    };



   @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(gyroscopeSensorListener,
                gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(accelerometerListener,
                accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(stepCounterListener,
                stepCounterSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(linearAccelerationListener,
                linearAccelerationSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(gyroscopeSensorListener);
        sensorManager.unregisterListener(accelerometerListener);
        sensorManager.unregisterListener(stepCounterListener);
        sensorManager.unregisterListener(linearAccelerationListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;
        if (id == R.id.action_settings) {
            fragment = new Settings_activity();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

        return super.onOptionsItemSelected(item);
    }
}
