package com.example.alexc.kinematic_calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String iVel, tim, acc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button graphBtn = findViewById(R.id.results);
        graphBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!(Double.parseDouble(tim) < 0)) {
                        Intent startIntent = new Intent(MainActivity.this, Results.class);
                        startIntent.putExtra("vi", iVel);
                        startIntent.putExtra("t", tim);
                        startIntent.putExtra("a", acc);
                        startActivity(startIntent);
                    } else {
                        Toast toast = new Toast(getApplicationContext());
                        toast.setGravity(Gravity.TOP | Gravity.START, 0, 0);
                        toast.makeText(MainActivity.this, "Time can not be negative.", toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.TOP | Gravity.START, 0, 0);
                    toast.makeText(MainActivity.this, "Not Enough Information To Create A Graph", toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void transfer(String t, String vi, String a) {
        iVel = vi;
        tim = t;
        acc = a;
    }

    public void buttonOnClick(View v) {

        Button Calculate = (Button) v;
        String initialVelocity = ((EditText) findViewById(R.id.InitialVinp)).getText().toString();
        String finalVelocity = ((EditText) findViewById(R.id.FinalVinp)).getText().toString();
        String acceleration = ((EditText) findViewById(R.id.Accinp)).getText().toString();
        String time = ((EditText) findViewById(R.id.Timeinp)).getText().toString();
        String displacement = ((EditText) findViewById(R.id.Dispinp)).getText().toString();
        String variable = ((EditText) findViewById(R.id.Variableinp)).getText().toString();


        String calculateKinEquation = kinematics(initialVelocity, finalVelocity, acceleration, time, displacement, variable);
        TextView output = (TextView) findViewById(R.id.Results);
        output.setText(calculateKinEquation);

        String usedKinEquation = usedKinematics(initialVelocity, finalVelocity, acceleration, time, displacement, variable);
        TextView eqOutput = (TextView) findViewById(R.id.equationResult);
        eqOutput.setText(usedKinEquation);


        // Calculation Successful
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.TOP | Gravity.LEFT, 0, 0);
        toast.makeText(MainActivity.this, "Calculated", toast.LENGTH_SHORT).show();


    }

    public String kinematics(String vi, String vf, String a, String t, String d, String var) {

        // Solving for Initial Velocity (Generate Velocity vs. time graph)
        if (var.equals("iVel")) {
            if (vf.equals("") && vi.equals("")) {
                double convAcceleration = Double.parseDouble(a);
                double convTime = Double.parseDouble(t);
                double convDisplacement = Double.parseDouble(d);
                double convInitialVel = ((convDisplacement - (0.5 * convAcceleration * convTime * convTime)) / convTime);
                transfer(""+convTime, ""+convInitialVel,""+convAcceleration);

                // add the algorithm here
                return "iVel = " + String.format("%.3f", ((convDisplacement - (0.5 * convAcceleration * convTime * convTime)) / convTime)) + " meters/second";
            } else if (d.equals("") && vi.equals("")) {
                double convFinalVel = Double.parseDouble(vf);
                double convAcceleration = Double.parseDouble(a);
                double convTime = Double.parseDouble(t);
                double convInitialVel = (convFinalVel - (convAcceleration * convTime));
                transfer(""+convTime, ""+convInitialVel,""+convAcceleration);

                // add the algorithm
                return "iVel = " + String.format("%.3f", (convFinalVel - (convAcceleration * convTime))) + " meters/second";
            } else if (a.equals("") && vi.equals("")) {
                double convTime = Double.parseDouble(t);
                double convDisplacement = Double.parseDouble(d);
                double convFinalVel = Double.parseDouble(vf);
                double convInitialVel = (((2 * convDisplacement) / convTime) - convFinalVel);
                double convAcceleration = ((convFinalVel - convInitialVel) / convTime);
                transfer(""+convTime, ""+convInitialVel,""+convAcceleration);

                // add the algorithm
                return "iVel = " + String.format("%.3f", (((2 * convDisplacement) / convTime) - convFinalVel)) + " meters/second";
            } else if (t.equals("") && vi.equals("")) {
                double convDisplacement = Double.parseDouble(d);
                double convFinalVel = Double.parseDouble(vf);
                double convAcceleration = Double.parseDouble(a);
                double convInitialVel = Math.sqrt((convFinalVel * convFinalVel) - (2 * convAcceleration * convDisplacement));
                double convTime = ((convFinalVel - convInitialVel) / convAcceleration);
                transfer(""+convTime, ""+convInitialVel,""+convAcceleration);

                // add the algorithm
                return "iVel = " + String.format("%.3f", Math.sqrt((convFinalVel * convFinalVel) - (2 * convAcceleration * convDisplacement))) + " meters/second";
            } else if (vi.equals("")) {
                double convAcceleration = Double.parseDouble(a);
                double convTime = Double.parseDouble(t);
                double convDisplacement = Double.parseDouble(d);
                double convInitialVel = ((convDisplacement - (0.5 * convAcceleration * convTime * convTime)) / convTime);
                transfer(""+convTime, ""+convInitialVel,""+convAcceleration);

                // add the algorithm here
                return "iVel = " + String.format("%.3f", ((convDisplacement - (0.5 * convAcceleration * convTime * convTime)) / convTime)) + " meters/second";
            }
        } else if (var.equals("fVel")) {
            if (d.equals("") && vf.equals("")) {
                double convInitialVel = Double.parseDouble(vi);
                double convAcceleration = Double.parseDouble(a);
                double convTime = Double.parseDouble(t);
                transfer(""+convTime, ""+convInitialVel,""+convAcceleration);

                // add the algorithm
                return "fVel = " + String.format("%.3f", (convInitialVel + (convAcceleration * convTime))) + " meters/second";
            } else if (a.equals("") && vf.equals("")) {
                double convTime = Double.parseDouble(t);
                double convDisplacement = Double.parseDouble(d);
                double convInitialVel = Double.parseDouble(vi);
                double convFinalVel = (((2 * convDisplacement) / convTime) - convInitialVel);
                double convAcceleration = ((convFinalVel - convInitialVel) / convTime);
                transfer(""+convTime, ""+convInitialVel,""+convAcceleration);

                // add the algorithm here
                return "fVel = " + String.format("%,3f", (((2 * convDisplacement) / convTime) - convInitialVel)) + "meters/second";
            } else if (t.equals("") && vf.equals("")) {
                double convDisplacement = Double.parseDouble(d);
                double convInitialVel = Double.parseDouble(vi);
                double convAcceleration = Double.parseDouble(a);
                double convFinalVel = Math.sqrt((convInitialVel * convInitialVel) + (2 * convAcceleration * convDisplacement));
                double convTime = ((convFinalVel - convInitialVel) / convAcceleration);
                transfer(""+convTime, ""+convInitialVel,""+convAcceleration);

                // add the algorithm
                return "fVel = " + String.format("%.3f", Math.sqrt((convInitialVel * convInitialVel) + (2 * convAcceleration * convDisplacement))) + " meters/second";
            } else if (vf.equals("")) {
                double convInitialVel = Double.parseDouble(vi);
                double convAcceleration = Double.parseDouble(a);
                double convTime = Double.parseDouble(t);
                transfer(""+convTime, ""+convInitialVel,""+convAcceleration);

                // add the algorithm
                return "fVel = " + String.format("%.3f", (convInitialVel + (convAcceleration * convTime))) + " meters/second";
            }
        } else if (var.equals("Disp")) {
            if (vf.equals("") && d.equals("")) {
                double convAcceleration = Double.parseDouble(a);
                double convTime = Double.parseDouble(t);
                double convInitialVel = Double.parseDouble(vi);
                transfer(""+convTime, ""+convInitialVel,""+convAcceleration);

                // add the algorithm here
                return "Disp = " + String.format("%.3f", ((0.5 * convAcceleration * convTime * convTime) + (convInitialVel * convTime))) + " meters";
            } else if (a.equals("") && d.equals("")) {
                double convFinalVel = Double.parseDouble(vf);
                double convTime = Double.parseDouble(t);
                double convInitialVel = Double.parseDouble(vi);
                double convAcceleration = ((convFinalVel - convInitialVel) / convTime);
                transfer(""+convTime, ""+convInitialVel,""+convAcceleration);

                // add the algorithm
                return "Disp = " + String.format("%.3f", (((convInitialVel + convFinalVel) / 2) * convTime)) + " meters";
            } else if (t.equals("") && d.equals("")) {
                double convFinalVel = Double.parseDouble(vf);
                double convInitialVel = Double.parseDouble(vi);
                double convAcceleration = Double.parseDouble(a);
                double convTime = ((convFinalVel - convInitialVel) / convAcceleration);
                transfer(""+convTime, ""+convInitialVel,""+convAcceleration);

                // add the algorithm
                return "Disp = " + String.format("%.3f", ((((convFinalVel * convFinalVel) - (convInitialVel * convInitialVel)) / (2 * convAcceleration)))) + " meters";
            } else if (d.equals("")) {
                double convAcceleration = Double.parseDouble(a);
                double convTime = Double.parseDouble(t);
                double convInitialVel = Double.parseDouble(vi);
                transfer(""+convTime, ""+convInitialVel,""+convAcceleration);

                // add the algorithm here
                return "Disp = " + String.format("%.3f", ((0.5 * convAcceleration * convTime * convTime) + (convInitialVel * convTime))) + " meters";
            }
        } else if (var.equals("Time")) {
            if (d.equals("") && t.equals("")) {
                double convFinalVel = Double.parseDouble(vf);
                double convInitialVel = Double.parseDouble(vi);
                double convAcceleration = Double.parseDouble(a);
                double convTime = ((convFinalVel - convInitialVel) / convAcceleration);
                transfer(""+convTime, ""+convInitialVel,""+convAcceleration);

                // add the algorithm
                return "Time = " + String.format("%.3f", ((convFinalVel - convInitialVel) / convAcceleration)) + " seconds";
            } else if (a.equals("") && t.equals("")) {
                double convDisplacement = Double.parseDouble(d);
                double convFinalVel = Double.parseDouble(vf);
                double convInitialVel = Double.parseDouble(vi);
                double convTime = ((2 * convDisplacement) / (convInitialVel + convFinalVel));
                double convAcceleration = ((convFinalVel - convInitialVel) / convTime);
                transfer(""+convTime, ""+convInitialVel,""+convAcceleration);

                // add the algorithm
                return "Time = " + String.format("%.3f", ((2 * convDisplacement) / (convInitialVel + convFinalVel))) + " seconds";
            } else if (vf.equals("") && t.equals("")) {
                double convDisplacement = Double.parseDouble(d);
                double convInitialVel = Double.parseDouble(vi);
                double convAcceleration = Double.parseDouble(a);

                // add the algorithm here
                if (convAcceleration == 0) {
                    double rt1 = convDisplacement / convInitialVel;
                    transfer(""+rt1, ""+convInitialVel,""+convAcceleration);
                    return "Time = " + String.format("%.3f", rt1);
                } else if (convAcceleration != 0) {
                    double rt1 = (((((-1 * convInitialVel)) - Math.sqrt((convInitialVel * convInitialVel) - (2 * convAcceleration * convDisplacement)))) / convAcceleration);
                    double rt2 = ((((-1 * convInitialVel)) + Math.sqrt((convInitialVel * convInitialVel) - (2 * convAcceleration * convDisplacement))) / convAcceleration);

                    if (rt1 > 0) {
                        double convTime = rt1;
                        transfer(""+convTime, ""+convInitialVel,""+convAcceleration);
                        return "Time = " + String.format("%.3f", convTime) + " seconds";
                    } else {
                        double convTime = rt2;
                        transfer("" + convTime, "" + convInitialVel, "" + convAcceleration);
                        return "Time = " + String.format("%.3f", convTime) + " seconds";
                    }
                }
            } else if (t.equals("")) {
                double convFinalVel = Double.parseDouble(vf);
                double convInitialVel = Double.parseDouble(vi);
                double convAcceleration = Double.parseDouble(a);
                double convTime = ((convFinalVel - convInitialVel) / convAcceleration);
                transfer(""+convTime, ""+convInitialVel,""+convAcceleration);

                // add the algorithm
                return "Time = " + String.format("%.3f", ((convFinalVel - convInitialVel) / convAcceleration)) + " seconds";
            }
        } else if (var.equals("Accel")) {
            if (d.equals("") && a.equals("")) {
                double convFinalVel = Double.parseDouble(vf);
                double convInitialVel = Double.parseDouble(vi);
                double convTime = Double.parseDouble(t);
                double convAcceleration = ((convFinalVel - convInitialVel) / convTime);
                transfer(""+convTime, ""+convInitialVel,""+convAcceleration);

                // add the algorithm
                return "Accel = " + String.format("%.3f", ((convFinalVel - convInitialVel) / convTime)) + " meters/second^2";
            } else if (vf.equals("") && a.equals("")) {
                double convTime = Double.parseDouble(t);
                double convDisplacement = Double.parseDouble(d);
                double convInitialVel = Double.parseDouble(vi);
                double convAcceleration = (2 * (convDisplacement - (convInitialVel * convTime)) / (convTime * convTime));
                transfer(""+convTime, ""+convInitialVel,""+convAcceleration);

                // add the algorithm here
                return "Accel = " + String.format("%.3f", (2 * (convDisplacement - (convInitialVel * convTime)) / (convTime * convTime))) + " meters/second^2";
            } else if (t.equals("") && a.equals("")) {
                double convFinalVel = Double.parseDouble(vf);
                double convInitialVel = Double.parseDouble(vi);
                double convDisplacement = Double.parseDouble(d);
                double convTime = ((2 * convDisplacement) / (convInitialVel + convFinalVel));
                double convAcceleration = ((convFinalVel - convInitialVel) / convTime);
                transfer(""+convTime, ""+convInitialVel,""+convAcceleration);

                // add the algorithm
                return "Accel = " + String.format("%.3f", ((((convFinalVel * convFinalVel) - (convInitialVel * convInitialVel)) / (2 * convDisplacement)))) + " meters/second^2";
            } else if (a.equals("")) {
                double convFinalVel = Double.parseDouble(vf);
                double convInitialVel = Double.parseDouble(vi);
                double convTime = Double.parseDouble(t);
                double convAcceleration = ((convFinalVel - convInitialVel) / convTime);
                transfer(""+convTime, ""+convInitialVel,""+convAcceleration);

                // add the algorithm
                return "Accel = " + String.format("%.3f", ((convFinalVel - convInitialVel) / convTime)) + " meters/second^2";
            }
        } else {
            return "Not Enough Information";
        }
        return "Not Enough Information";
    }

    // Print out the equation used
    public String usedKinematics(String ivel, String fvel, String accel, String time, String disp, String varia) {

        // Solving for Initial Velocity (Generate Velocity vs. time graph)
        if (varia.equals("iVel")) {
            if (fvel.equals("") && ivel.equals("")) {
                // display the algorithm here
                return "Disp  =  (1/2 * Accel * Time^2)  +  (iVel * Time)";   // 1
            } else if (disp.equals("") && ivel.equals("")) {
                // display the algorithm
                return "fVel  =  iVel  +  (Accel * Time)";   // 3
            } else if (accel.equals("") && ivel.equals("")) {
                // display the algorithm
                return "Disp  =  ((iVel + fVel) / 2)  *  Time";   // 4
            } else if (time.equals("") && ivel.equals("")) {
                // display the algorithm
                return "fVel^2  =  iVel^2  +  (2 * Accel * Disp)";    // 2
            } else if (ivel.equals("")) {
                // display the algorithm here
                return "Disp  =  (1/2 * Accel * Time^2)  +  (iVel * Time)";
            }
        } else if (varia.equals("fVel")) {
            if (disp.equals("") && fvel.equals("")) {
                // add the algorithm
                return "fVel  =  iVel  +   (Accel * Time)";
            } else if (accel.equals("") && fvel.equals("")) {
                // add the algorithm here
                return "Disp  =  ((iVel + fVel) / 2)  *  Time";
            } else if (time.equals("") && fvel.equals("")) {
               // add the algorithm
                return "fVel^2  =  iVel^2  +  (2 * Accel * Disp)";
            } else if (fvel.equals("")) {
                // add the algorithm
                return "fVel  =  iVel  +  (Accel * Time)";
            }
        } else if (varia.equals("Disp")) {
            if (fvel.equals("") && disp.equals("")) {
                // add the algorithm here
                return "Disp  =  (1/2 * Accel * Time^2)  +  (iVel * Time)";
            } else if (accel.equals("") && disp.equals("")) {
                // add the algorithm
                return "Disp  =  ((iVel + fVel) / 2)  *  Time";
            } else if (time.equals("") && disp.equals("")) {
                // add the algorithm
                return "fVel^2  =  iVel^2  +  (2 * Accel * Disp)";
            } else if (disp.equals("")) {
                // add the algorithm here
                return "Disp  =  (1/2 * Accel * Time^2)  +  (iVel * Time)";
            }
        } else if (varia.equals("Time")) {
            if (disp.equals("") && time.equals("")) {
                // add the algorithm
                return "fVel  =  iVel  +  (Accel * Time)";
            } else if (accel.equals("") && time.equals("")) {
                // add the algorithm
                return "Disp  =  ((iVel + fVel) / 2)  *  Time";
            } else if (fvel.equals("") && time.equals("")) {
                // add the algorithm here
                return "Disp  =  (1/2 * Accel * Time^2)  +  (iVel * Time)";
            } else if (time.equals("")) {
                // add the algorithm
                return "Disp  =  (1/2 * Accel * Time^2)  +  (iVel * Time)";
            }
        } else if (varia.equals("Accel")) {
            if (disp.equals("") && accel.equals("")) {
                // add the algorithm
                return "fVel  =  iVel  +  (Accel * Time)";
            } else if (fvel.equals("") && accel.equals("")) {
                // add the algorithm here
                return "Disp  =  (1/2 * Accel * Time^2)  +  (iVel * Time)";
            } else if (time.equals("") && accel.equals("")) {
                // add the algorithm
                return "fVel^2  =  iVel^2  +  (2 * Accel * Disp)";
            } else if (accel.equals("")) {
                // add the algorithm
                return "Disp  =  (1/2 * Accel * Time^2)  +  (iVel * Time)";
            }
        } else {
            return "Not Enough Information";
        }
        return "Not Enough Information";
    }
}




//        if (vf.equals("")) {
//            if (var.equals("iVel")) {
//                double convAcceleration = Double.parseDouble(a);
//                double convTime = Double.parseDouble(t);
//                double convDisplacement = Double.parseDouble(d);
//
//                // add the algorithm here
//                return "iVel = " + String.format("%.3f", ((convDisplacement - (0.5 * convAcceleration * convTime * convTime)) / convTime)) + " meters/second";
//
//            } else if (var.equals("Disp")) {
//                double convAcceleration = Double.parseDouble(a);
//                double convTime = Double.parseDouble(t);
//                double convInitialVel = Double.parseDouble(vi);
//
//                // add the algorithm here
//                return "Disp = " + String.format("%.3f", ((0.5 * convAcceleration * convTime * convTime) + (convInitialVel * convTime))) + " meters";
//
//            } else if (var.equals("Accel")) {
//                double convTime = Double.parseDouble(t);
//                double convDisplacement = Double.parseDouble(d);
//                double convInitialVel = Double.parseDouble(vi);
//
//                // add the algorithm here
//                return "Accel = " + String.format("%.3f", (2 * (convDisplacement - (convInitialVel * convTime)) / (convTime * convTime))) + " meters/second^2";
//
//            } else if (var.equals("Time")) {
//                double convDisplacement = Double.parseDouble(d);
//                double convInitialVel = Double.parseDouble(vi);
//                double convAcceleration = Double.parseDouble(a);
//
//                // add the algorithm here
//                return "Time = " + String.format("%.3f", ((((-1 * convInitialVel)) - Math.sqrt((convInitialVel * convInitialVel) + (2 * convAcceleration * convDisplacement))) / convAcceleration)) + " seconds";
//
//            }
//        }
//        if (a.equals("")) {
//            if (var.equals("fVel")) {
//                double convTime = Double.parseDouble(t);
//                double convDisplacement = Double.parseDouble(d);
//                double convInitialVel = Double.parseDouble(vi);
//
//                // add the algorithm here
//                return "fVel = " + String.format("%.3f", (((2 * convDisplacement) / convTime) - convInitialVel)) + "meters/second";
//
//            } else if (var.equals("iVel")) {
//                double convTime = Double.parseDouble(t);
//                double convDisplacement = Double.parseDouble(d);
//                double convFinalVel = Double.parseDouble(vf);
//
//                // add the algorithm
//                return "iVel = " + String.format("%.3f", (((2 * convDisplacement) / convTime) - convFinalVel)) + " meters/second";
//
//            } else if (var.equals("Disp")) {
//                double convFinalVel = Double.parseDouble(vf);
//                double convTime = Double.parseDouble(t);
//                double convInitialVel = Double.parseDouble(vi);
//
//                // add the algorithm
//                return "Disp = " + String.format("%.3f", (((convInitialVel + convFinalVel) / 2) * convTime)) + " meters";
//
//            } else if (var.equals("Time")) {
//                double convDisplacement = Double.parseDouble(d);
//                double convFinalVel = Double.parseDouble(vf);
//                double convInitialVel = Double.parseDouble(vi);
//
//                // add the algorithm
//                return "Time = " + String.format("%.3f", ((2 * convDisplacement) / (convInitialVel + convFinalVel))) + " seconds";
//
//            }
//        }
//        if (t.equals("")) {
//            if (var.equals("fVel")) {
//                double convDisplacement = Double.parseDouble(d);
//                double convInitialVel = Double.parseDouble(vi);
//                double convAcceleration = Double.parseDouble(a);
//
//                // add the algorithm
//                return "fVel = " + String.format("%.3f", Math.sqrt((convInitialVel * convInitialVel) + (2 * convAcceleration * convDisplacement))) + " meters/second";
//
//            } else if (var.equals("iVel")) {
//                double convDisplacement = Double.parseDouble(d);
//                double convFinalVel = Double.parseDouble(vf);
//                double convAcceleration = Double.parseDouble(a);
//
//                // add the algorithm
//                return "iVel = " + String.format("%.3f", Math.sqrt((2 * convAcceleration * convDisplacement) - (convFinalVel * convFinalVel))) + " meters/second";
//
//            } else if (var.equals("Disp")) {
//                double convFinalVel = Double.parseDouble(vf);
//                double convInitialVel = Double.parseDouble(vi);
//                double convAcceleration = Double.parseDouble(a);
//
//                // add the algorithm
//                return "Disp = " + String.format("%.3f", ((((convFinalVel * convFinalVel) - (convInitialVel * convInitialVel)) / (2 * convAcceleration)))) + " meters";
//
//            } else if (var.equals("Accel")) {
//                double convFinalVel = Double.parseDouble(vf);
//                double convInitialVel = Double.parseDouble(vi);
//                double convDisplacement = Double.parseDouble(d);
//
//                // add the algorithm
//                return "Accel = " + String.format("%.3f", ((((convFinalVel * convFinalVel) - (convInitialVel * convInitialVel)) / (2 * convDisplacement)))) + " meters/second^2";
//
//            }
//
//        }
//        if (d.equals("")) {
//            if (var.equals("fVel")) {
//                double convInitialVel = Double.parseDouble(vi);
//                double convAcceleration = Double.parseDouble(a);
//                double convTime = Double.parseDouble(t);
//
//                // add the algorithm
//                return "fVel = " + String.format("%.3f", (convInitialVel + (convAcceleration * convTime))) + " meters/second";
//
//            } else if (var.equals("iVel")) {
//                double convFinalVel = Double.parseDouble(vf);
//                double convAcceleration = Double.parseDouble(a);
//                double convTime = Double.parseDouble(t);
//
//                // add the algorithm
//                return "iVel = " + String.format("%.3f", (convFinalVel - (convAcceleration * convTime))) + " meters/second";
//
//            } else if (var.equals("Accel")) {
//                double convFinalVel = Double.parseDouble(vf);
//                double convInitialVel = Double.parseDouble(vi);
//                double convTime = Double.parseDouble(t);
//
//                // add the algorithm
//                return "Accel = " + String.format("%.3f", ((convFinalVel - convInitialVel) / convTime)) + " seconds";
//
//            } else if (var.equals("Time")) {
//                double convFinalVel = Double.parseDouble(vf);
//                double convInitialVel = Double.parseDouble(vi);
//                double convAcceleration = Double.parseDouble(a);
//
//                // add the algorithm
//                return "Accel = " + String.format("%.3f", ((convFinalVel - convInitialVel) / convAcceleration)) + " seconds";
//
//            }
//        }
