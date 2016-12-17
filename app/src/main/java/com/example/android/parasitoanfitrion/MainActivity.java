package com.example.android.parasitoanfitrion;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.echo.holographlibrary.Line;
import com.echo.holographlibrary.LineGraph;
import com.echo.holographlibrary.LinePoint;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText cAnf, cPar, tasaCont, tasaNacAnf, tasaMuerAnf, tasaMuerPar;
        Button simular;

        cAnf = (EditText)findViewById(R.id.anf_inicial);
        cPar = (EditText)findViewById(R.id.par_inicial);
        tasaCont = (EditText)findViewById(R.id.TCont);
        tasaNacAnf = (EditText)findViewById(R.id.TNAnf);
        tasaMuerAnf = (EditText)findViewById(R.id.TMAnf);
        tasaMuerPar = (EditText)findViewById(R.id.TMPar);

        simular = (Button) findViewById(R.id.simular);
        simular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simulate(cAnf,cPar,tasaCont,tasaNacAnf,tasaMuerAnf,tasaMuerPar);
            }
        });
    }

    public void simulate(EditText cAnf,EditText cPar, EditText tasaCont, EditText tasaNacAnf, EditText tasaMuerAnf, EditText tasaMuerPar) {

        float cantAnfi, cantPara, tNAnf, tCont, tMPar, tMAnf;

        cantAnfi = Float.parseFloat(cAnf.getText().toString());
        cantPara = Float.parseFloat(cPar.getText().toString());
        tCont = Float.parseFloat(tasaCont.getText().toString());
        tNAnf = Float.parseFloat(tasaNacAnf.getText().toString());
        tMAnf = Float.parseFloat(tasaMuerAnf.getText().toString());
        tMPar = Float.parseFloat(tasaMuerPar.getText().toString());

        Line l = new Line();
        LinePoint p;
        Line l2 = new Line();
        LinePoint p2;

        for (int xVal=0; xVal<501 ; xVal++) {
            p = new LinePoint();
            p2 = new LinePoint();
            cantAnfi += (cantAnfi * tNAnf) - ((tCont * cantAnfi * cantPara) + (cantAnfi * tMAnf));
            cantPara += (tCont * cantAnfi * cantPara) - (cantPara * tMPar);
            p.setX(xVal);
            p.setY(cantAnfi);
            p2.setX(xVal);
            p2.setY(cantPara);
            if (xVal % 25  == 0) {
                l.setShowingPoints(false);
                l2.setShowingPoints(false);
                l.addPoint(p);
                l2.addPoint(p2);
            }
        }
        l.setColor(Color.parseColor("#FFBB33"));
        l2.setColor(Color.parseColor("#FF4444"));

        LineGraph li = (LineGraph)findViewById(R.id.graph);
        li.setRangeY(0, 12000);
        li.setRangeX(0, 500);
        li.removeAllLines();
        li.addLine(l);
        li.addLine(l2);
        li.setLineToFill(0);
    }
}
