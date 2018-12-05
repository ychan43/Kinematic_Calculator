package com.example.alexc.kinematic_calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

        TextView results = (TextView) findViewById(R.id.Results);
        results.setText(calculateKinEquation);
    }
//    double convFinalVel = Double.parseDouble(vf);
//    double convAcceleration = Double.parseDouble(a);
//    double convTime = Double.parseDouble(t);
//    double convDistance = Double.parseDouble(d);
//    double convInitialVel = Double.parseDouble(vi);

    public String kinematics(String vi, String vf, String a, String t, String d, String var) {

        if (vf == null) {
            if (var.equals("iVel")) {
                double convAcceleration = Double.parseDouble(a);
                double convTime = Double.parseDouble(t);
                double convDistance = Double.parseDouble(d);
                // add the algorithm here

            } else if (var.equals("Dist")) {
                double convAcceleration = Double.parseDouble(a);
                double convTime = Double.parseDouble(t);
                double convInitialVel = Double.parseDouble(vi);
                // add the algorithm here

            } else if (var.equals("Acccel")) {
                double convTime = Double.parseDouble(t);
                double convDistance = Double.parseDouble(d);
                double convInitialVel = Double.parseDouble(vi);
                // add the algorithm here

            } else if (var.equals("Time")) {
                double convDistance = Double.parseDouble(d);
                double convInitialVel = Double.parseDouble(vi);
                double convAcceleration = Double.parseDouble(a);
                // add the algorithm here

            }
        } else if (a == null) {
            if (var.equals("fVel")) {
                
            } else if (var.equals("iVel")) {

            } else if (var.equals("Dist")) {

            }

        } else if (t == null) {

        } else if (d == null) {

        }
    }
}
