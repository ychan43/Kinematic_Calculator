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
        String displacement = ((EditText)findViewById(R.id.Dispinp)).getText().toString();
        String variable = ((EditText)findViewById(R.id.Variableinp)).getText().toString();



        String calculateKinEquation = kinematics(initialVelocity, finalVelocity, acceleration, time, displacement, variable);
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
                double convDisplacement = Double.parseDouble(d);

                // add the algorithm here
                return "iVel = " + String.format("%.3f", ((convDisplacement - (0.5 * convAcceleration * convTime * convTime)) / convTime)) + " meters/second";

            } else if (var.equals("Disp")) {
                double convAcceleration = Double.parseDouble(a);
                double convTime = Double.parseDouble(t);
                double convInitialVel = Double.parseDouble(vi);

                // add the algorithm here
                return "Disp = " + String.format("%.3f", ((0.5 * convAcceleration * convTime * convTime) + (convInitialVel * convTime))) + " meters";

            } else if (var.equals("Accel")) {
                double convTime = Double.parseDouble(t);
                double convDisplacement = Double.parseDouble(d);
                double convInitialVel = Double.parseDouble(vi);

                // add the algorithm here
                return "Accel = " + String.format("%.3f", (2 * (convDisplacement - (convInitialVel * convTime)) / (convTime * convTime))) + " meters/second^2";

            } else if (var.equals("Time")) {
                double convDisplacement = Double.parseDouble(d);
                double convInitialVel = Double.parseDouble(vi);
                double convAcceleration = Double.parseDouble(a);

                // add the algorithm here
                return "Time = " + String.format("%.3f", ((((-1 * convInitialVel)) - Math.sqrt((convInitialVel * convInitialVel) + (2 * convAcceleration * convDisplacement))) / convAcceleration)) + " seconds";

            }
        }
        if (a.equals("")) {
            if (var.equals("fVel")) {
                double convTime = Double.parseDouble(t);
                double convDisplacement = Double.parseDouble(d);
                double convInitialVel = Double.parseDouble(vi);

                // add the algorithm here
                return "fVel = " + String.format("%,3f", (((2 * convDisplacement) / convTime) - convInitialVel)) + "meters/second";

            } else if (var.equals("iVel")) {
                double convTime = Double.parseDouble(t);
                double convDisplacement = Double.parseDouble(d);
                double convFinalVel = Double.parseDouble(vf);

                // add the algorithm
                return "iVel = " + String.format("%.3f", (((2 * convDisplacement) / convTime) - convFinalVel)) + " meters/second";

            } else if (var.equals("Disp")) {
                double convFinalVel = Double.parseDouble(vf);
                double convTime = Double.parseDouble(t);
                double convInitialVel = Double.parseDouble(vi);

                // add the algorithm
                return "Disp = " + String.format("%.3f", (((convInitialVel + convFinalVel) / 2) * convTime)) + " meters";

            } else if (var.equals("Time")) {
                double convDisplacement = Double.parseDouble(d);
                double convFinalVel = Double.parseDouble(vf);
                double convInitialVel = Double.parseDouble(vi);

                // add the algorithm
                return "Time = " + String.format("%.3f", ((2 * convDisplacement) / (convInitialVel + convFinalVel))) + " seconds";

            }
        }
        if (t.equals("")) {
            if (var.equals("fVel")) {
                double convDisplacement = Double.parseDouble(d);
                double convInitialVel = Double.parseDouble(vi);
                double convAcceleration = Double.parseDouble(a);

                // add the algorithm
                return "fVel = " + String.format("%.3f", Math.sqrt((convInitialVel * convInitialVel) + (2 * convAcceleration * convDisplacement))) + " meters/second";

            } else if (var.equals("iVel")) {
                double convDisplacement = Double.parseDouble(d);
                double convFinalVel = Double.parseDouble(vf);
                double convAcceleration = Double.parseDouble(a);

                // add the algorithm
                return "iVel = " + String.format("%.3f", Math.sqrt((2 * convAcceleration * convDisplacement) - (convFinalVel * convFinalVel))) + " meters/second";

            } else if (var.equals("Disp")) {
                double convFinalVel = Double.parseDouble(vf);
                double convInitialVel = Double.parseDouble(vi);
                double convAcceleration = Double.parseDouble(a);

                // add the algorithm
                return "Disp = " + String.format("%.3f", ((((convFinalVel * convFinalVel) - (convInitialVel * convInitialVel)) / (2 * convAcceleration)))) + " meters";

            } else if (var.equals("Accel")) {
                double convFinalVel = Double.parseDouble(vf);
                double convInitialVel = Double.parseDouble(vi);
                double convDisplacement = Double.parseDouble(d);

                // add the algorithm
                return "Accel = " + String.format("%.3f", ((((convFinalVel * convFinalVel) - (convInitialVel * convInitialVel)) / (2 * convDisplacement)))) + " meters/second^2";

            }

        }
        if (d.equals("")) {
            if (var.equals("fVel")) {
                double convInitialVel = Double.parseDouble(vi);
                double convAcceleration = Double.parseDouble(a);
                double convTime = Double.parseDouble(t);

                // add the algorithm
                return "fVel = " + String.format("%.3f", (convInitialVel + (convAcceleration * convTime))) + " meters/second";

            } else if (var.equals("iVel")) {
                double convFinalVel = Double.parseDouble(vf);
                double convAcceleration = Double.parseDouble(a);
                double convTime = Double.parseDouble(t);

                // add the algorithm
                return "iVel = " + String.format("%.3f", (convFinalVel - (convAcceleration * convTime))) + " meters/second";

            } else if (var.equals("Accel")) {
                double convFinalVel = Double.parseDouble(vf);
                double convInitialVel = Double.parseDouble(vi);
                double convTime = Double.parseDouble(t);

                // add the algorithm
                return "Accel = " + String.format("%.3f", ((convFinalVel - convInitialVel) / convTime)) + " seconds";

            } else if (var.equals("Time")) {
                double convFinalVel = Double.parseDouble(vf);
                double convInitialVel = Double.parseDouble(vi);
                double convAcceleration = Double.parseDouble(a);

                // add the algorithm
                return "Accel = " + String.format("%.3f", ((convFinalVel - convInitialVel) / convAcceleration)) + " seconds";

            }
        } else {
            return "Invalid Calculation";
        }
        return "";
    }
}
