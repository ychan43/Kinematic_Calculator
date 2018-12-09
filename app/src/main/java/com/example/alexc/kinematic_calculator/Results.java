package com.example.alexc.kinematic_calculator;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Results extends AppCompatActivity {
    LineGraphSeries<DataPoint> series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent intent = getIntent();
        double t, vi, a;

        t = Double.valueOf(intent.getStringExtra("t"));
        vi = Double.valueOf(intent.getStringExtra("vi"));
        a = Double.valueOf(intent.getStringExtra("a"));
        double max = t;

        double d;
        double ti = 0;

        GraphView plot = (GraphView) findViewById(R.id.plot);
        series = new LineGraphSeries<DataPoint>();
        plot.getViewport().setXAxisBoundsManual(true);
        plot.getViewport().setMinX(0);
        plot.getViewport().setMaxX(max);
        for (int i = 0; i < t * 10; i++) {
            ti = ti + 0.1;
            d = vi*ti + 0.5*a*ti*ti;
            series.appendData(new DataPoint(ti, d), true, (int)t * 10);
        }
        plot.addSeries(series);

        Button mainBtn = (Button) findViewById(R.id.ReturnButton);
        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(startIntent);
            }
        });

    }
}

