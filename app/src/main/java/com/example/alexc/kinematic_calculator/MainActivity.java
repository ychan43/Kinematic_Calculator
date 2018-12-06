package com.example.alexc.kinematic_calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void buttonOnClick(View v) {
        Button Calculate = (Button) v;
        String initialVelocity = ((EditText)findViewById(R.id.InitialVinp)).getText().toString();
        String finalVelocity = ((EditText)findViewById(R.id.FinalVinp)).getText().toString();
        String acceleration = ((EditText)findViewById(R.id.Accinp)).getText().toString();
        String time = ((EditText)findViewById(R.id.Timeinp)).getText().toString();
        String distance = ((EditText)findViewById(R.id.Distinp)).getText().toString();
        String variable = ((EditText)findViewById(R.id.Variableinp)).getText().toString();



        String calculateKinEquation = kinematics(initialVelocity, finalVelocity, acceleration, time, distance, variable);
        TextView output = (TextView) findViewById(R.id.Results);
        output.setText(calculateKinEquation);



        // Calculation Successful
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.TOP | Gravity.LEFT, 0 , 0);
        toast.makeText(MainActivity.this, "Calculation Successful", toast.LENGTH_SHORT).show();


    }
    public String kinematics(String vi, String vf, String a, String t, String d, String var) {

        if (vf.equals("")) {
            if (var.equals("iVel")) {
                double convAcceleration = Double.parseDouble(a);
                double convTime = Double.parseDouble(t);
                double convDistance = Double.parseDouble(d);

                // add the algorithm here
                return "iVel = " + ((convDistance - (0.5 * convAcceleration * convTime * convTime)) / convTime) + " meters/second";

            } else if (var.equals("Dist")) {
                double convAcceleration = Double.parseDouble(a);
                double convTime = Double.parseDouble(t);
                double convInitialVel = Double.parseDouble(vi);

                // add the algorithm here
                return "Dist = " + ((0.5 * convAcceleration * convTime * convTime) + (convInitialVel * convTime)) + " meters";

            } else if (var.equals("Accel")) {
                double convTime = Double.parseDouble(t);
                double convDistance = Double.parseDouble(d);
                double convInitialVel = Double.parseDouble(vi);

                // add the algorithm here
                return "Accel = " + (2 * (convDistance - (convInitialVel * convTime)) / (convTime * convTime)) + " meters/second^2";

            } else if (var.equals("Time")) {
                double convDistance = Double.parseDouble(d);
                double convInitialVel = Double.parseDouble(vi);
                double convAcceleration = Double.parseDouble(a);
                // add the algorithm here

            }
        }
        if (a.equals("")) {
            if (var.equals("fVel")) {
                double convTime = Double.parseDouble(t);
                double convDistance = Double.parseDouble(d);
                double convInitialVel = Double.parseDouble(vi);

                // add the algorithm here
                return "fVel = " + (((2 * convDistance) / convTime) - convInitialVel) + "meters/second";

            } else if (var.equals("iVel")) {
                double convTime = Double.parseDouble(t);
                double convDistance = Double.parseDouble(d);
                double convFinalVel = Double.parseDouble(vf);

                // add the algorithm
                return "iVel = " + (((2 * convDistance) / convTime) - convFinalVel) + " meters/second";

            } else if (var.equals("Dist")) {
                double convFinalVel = Double.parseDouble(vf);
                double convTime = Double.parseDouble(t);
                double convInitialVel = Double.parseDouble(vi);

                // add the algorithm
                return "Dist = " + (((convInitialVel + convFinalVel) / 2) * convTime) + " meters";

            } else if (var.equals("Time")) {
                double convDistance = Double.parseDouble(d);
                double convFinalVel = Double.parseDouble(vf);
                double convInitialVel = Double.parseDouble(vi);

                // add the algorithm
                return "Time = " + ((2 * convDistance) / (convInitialVel + convFinalVel)) + " seconds";

            }
        }
        if (t.equals("")) {
            if (var.equals("fVel")) {
                double convDistance = Double.parseDouble(d);
                double convInitialVel = Double.parseDouble(vi);
                double convAcceleration = Double.parseDouble(a);

                // add the algorithm
                return "fVel = " + String.format("%.3f", Math.sqrt((convInitialVel * convInitialVel) + (2 * convAcceleration * convDistance))) + " meters/second";

            } else if (var.equals("iVel")) {
                double convDistance = Double.parseDouble(d);
                double convFinalVel = Double.parseDouble(vf);
                double convAcceleration = Double.parseDouble(a);
                // add the algorithm

            } else if (var.equals("Dist")) {
                double convFinalVel = Double.parseDouble(vf);
                double convInitialVel = Double.parseDouble(vi);
                double convAcceleration = Double.parseDouble(a);
                // add the algorithm

            } else if (var.equals("Accel")) {
                double convFinalVel = Double.parseDouble(vf);
                double convInitialVel = Double.parseDouble(vi);
                double convDistance = Double.parseDouble(d);
                // add the algorithm

            }

        }
        if (d.equals("")) {
            if (var.equals("fVel")) {
                double convInitialVel = Double.parseDouble(vi);
                double convAcceleration = Double.parseDouble(a);
                double convTime = Double.parseDouble(t);
                // add the algorithm

            } else if (var.equals("iVel")) {
                double convFinalVel = Double.parseDouble(vf);
                double convAcceleration = Double.parseDouble(a);
                double convTime = Double.parseDouble(t);
                // add the algorithm

            } else if (var.equals("Accel")) {
                double convFinalVel = Double.parseDouble(vf);
                double convInitialVel = Double.parseDouble(vi);
                double convTime = Double.parseDouble(t);

            } else if (var.equals("Time")) {
                double convFinalVel = Double.parseDouble(vf);
                double convInitialVel = Double.parseDouble(vi);
                double convAcceleration = Double.parseDouble(a);
                // add the algorithm
            }
        } else {
            return "Invalid Calculation";
        }
        return "";
    }
}
