package com.codex_iter.www.awol;
import android.content.Context;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.codex_iter.www.awol.ListData;
import com.codex_iter.www.awol.R;

import java.util.Scanner;

public class Bunk extends AppCompatActivity {

    EditText atndedt,bnkedt,taredt;
    TextView result,left;
    Spinner sub;
    TextView T1,T2,T3,T4,T5;
    Button target,bunk,attend;
    double absent,total,percent,present;
    ListData[] ld;
    LinearLayout line;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bunk);
        LinearLayout ll=findViewById(R.id.ll);
        line = findViewById(R.id.line);
        ll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (result.getVisibility() == View.VISIBLE && left.getVisibility() == View.VISIBLE) {
                    Intent intent = new Intent(Bunk.this, Bunk.class);
                    startActivity(intent);

                }
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                return false;
            }

        });
        this.ld=ListData.ld;
        T1=findViewById(R.id.T1);
        T2=findViewById(R.id.T2);
        T3=findViewById(R.id.T3);
        T4=findViewById(R.id.T4);
        T5=findViewById(R.id.T5);
        String subn[]=new String[ld.length];
        for(int i=0;i<ld.length;i++)
            subn[i]=ld[i].getSub();
        sub=findViewById(R.id.sub);
        ArrayAdapter a=new ArrayAdapter(this,R.layout.drop_down,subn);
        sub.setAdapter(a);
        sub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                total=Double.parseDouble(ld[position].getClasses());
                absent=Double.parseDouble(ld[position].getAbsent());
                percent=Double.parseDouble(ld[position].getPercent());
                present=total-absent;
                if (75 < percent) {
                    int i;
                    for (i = 0; i != -99; i++) {
                        double p = (present / (total + i)) * 100;
                        if (p < 75) break;
                    }
                    result.setText("Bunk " + (i-1) + " classes for 75% ");

                } else if (75 > percent) {
                    int i;
                    for (i = 0; i != -99; i++) {
                        double p = ((present + i) / (total + i) * 100);
                        if (p > 75) break;
                    }
                    result.setVisibility(View.VISIBLE);



                }
                left.setText("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                total=Double.parseDouble(ld[0].getClasses());
                absent=Double.parseDouble(ld[0].getAbsent());
                percent=Double.parseDouble(ld[0].getPercent());
                present=total-absent;
                if (75 < percent) {
                    int i;
                    for (i = 0; i != -99; i++) {
                        double p = (present / (total + i)) * 100;
                        if (p < 75) break;
                    }
                    result.setText("Bunk " + (i-1) + " classes for 75% ");
                    atndedt.setVisibility(View.INVISIBLE);
                    taredt.setVisibility(View.INVISIBLE);
                    bnkedt.setVisibility(View.INVISIBLE);
                    sub.setVisibility(View.INVISIBLE);
                    target.setVisibility(View.INVISIBLE);
                    bunk.setVisibility(View.INVISIBLE);
                    attend.setVisibility(View.INVISIBLE);
                    line.setVisibility(View.INVISIBLE);
                    result.setVisibility(View.VISIBLE);
                    left.setVisibility(View.VISIBLE);
                    T1.setVisibility(View.INVISIBLE);
                    T2.setVisibility(View.INVISIBLE);
                    T3.setVisibility(View.INVISIBLE);
                    T4.setVisibility(View.INVISIBLE);
                    T5.setVisibility(View.INVISIBLE);


                } else if (75 > percent) {
                    int i;
                    for (i = 0; i != -99; i++) {
                        double p = ((present + i) / (total + i) * 100);
                        if (p > 75) break;
                    }
                    result.setText("Attend " + (i-1) + " classes for 75%");
                    atndedt.setVisibility(View.INVISIBLE);
                    taredt.setVisibility(View.INVISIBLE);
                    bnkedt.setVisibility(View.INVISIBLE);
                    sub.setVisibility(View.INVISIBLE);
                    target.setVisibility(View.INVISIBLE);
                    bunk.setVisibility(View.INVISIBLE);
                    attend.setVisibility(View.INVISIBLE);
                    line.setVisibility(View.INVISIBLE);
                    T5.setVisibility(View.INVISIBLE);
                    result.setVisibility(View.VISIBLE);
                    left.setVisibility(View.VISIBLE);
                    T1.setVisibility(View.INVISIBLE);
                    T2.setVisibility(View.INVISIBLE);
                    T3.setVisibility(View.INVISIBLE);
                    T4.setVisibility(View.INVISIBLE);
                    T5.setVisibility(View.INVISIBLE);


                }
                left.setText("");
            }
        });
        present = total-absent;
        atndedt=findViewById(R.id.atndedt);
        bnkedt=findViewById(R.id.bnkedt);
        taredt=findViewById(R.id.taredt);
        attend=findViewById(R.id.attend);
        bunk=findViewById(R.id.bunk);
        target=findViewById(R.id.target);
        result=findViewById(R.id.result);
        left=findViewById(R.id.left);
        target.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = taredt.getText().toString().trim();
                if (s.equals(""))
                    Toast.makeText(getApplicationContext(), "enter some value", Toast.LENGTH_SHORT).show();
                else if(s.equals("100")&&absent>0)
                    Toast.makeText(getApplicationContext(), "Not Possible!!", Toast.LENGTH_SHORT).show();
                else {
                    double tp = new Scanner(s).nextDouble();
                    InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (getCurrentFocus() != null)
                        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    if (tp < percent) {
                        int i;
                        double p;
                        for (i = 0; i != -99; i++) {
                            p = (present / (total + i)) * 100;
                            if (p<tp) break;
                        }
                        result.setText("Bunk " + (i-1) + " classes for req attendance");
                        atndedt.setVisibility(View.INVISIBLE);
                        taredt.setVisibility(View.INVISIBLE);
                        bnkedt.setVisibility(View.INVISIBLE);
                        sub.setVisibility(View.INVISIBLE);
                        target.setVisibility(View.INVISIBLE);
                        bunk.setVisibility(View.INVISIBLE);
                        attend.setVisibility(View.INVISIBLE);
                        line.setVisibility(View.INVISIBLE);
                        T1.setVisibility(View.INVISIBLE);
                        T2.setVisibility(View.INVISIBLE);
                        T3.setVisibility(View.INVISIBLE);
                        T4.setVisibility(View.INVISIBLE);
                        T5.setVisibility(View.INVISIBLE);


                        result.setVisibility(View.VISIBLE);
                        left.setVisibility(View.VISIBLE);
                        if((int)tp!=75)
                        {
                            int b=i-1;
                            if (75 < tp) {

                                for (i = 0; i!=99; i++) {
                                    p = (present / (total+b + i)) * 100;
                                    if (p < 75) break;
                                }
                                left.setText("Bunk " + (i-1) + " more classes for 75% ");
                                atndedt.setVisibility(View.INVISIBLE);
                                taredt.setVisibility(View.INVISIBLE);
                                bnkedt.setVisibility(View.INVISIBLE);
                                sub.setVisibility(View.INVISIBLE);
                                target.setVisibility(View.INVISIBLE);
                                bunk.setVisibility(View.INVISIBLE);
                                attend.setVisibility(View.INVISIBLE);
                                line.setVisibility(View.INVISIBLE);
                                T1.setVisibility(View.INVISIBLE);
                                T2.setVisibility(View.INVISIBLE);
                                T3.setVisibility(View.INVISIBLE);
                                T4.setVisibility(View.INVISIBLE);
                                T5.setVisibility(View.INVISIBLE);
                                result.setVisibility(View.VISIBLE);
                                left.setVisibility(View.VISIBLE);

                            } else if (75 > tp) {
                                for (i = 0; i != -99; i++) {
                                    p = ((present + i) / (total+b + i)) * 100;
                                    if (p > 75) break;
                                }
                                left.setText("Attend " + (i-1) + " classes after bunk for 75%");
                                atndedt.setVisibility(View.INVISIBLE);
                                taredt.setVisibility(View.INVISIBLE);
                                bnkedt.setVisibility(View.INVISIBLE);
                                sub.setVisibility(View.INVISIBLE);
                                target.setVisibility(View.INVISIBLE);
                                bunk.setVisibility(View.INVISIBLE);
                                attend.setVisibility(View.INVISIBLE);
                                line.setVisibility(View.INVISIBLE);
                                T1.setVisibility(View.INVISIBLE);
                                T2.setVisibility(View.INVISIBLE);
                                T3.setVisibility(View.INVISIBLE);
                                T4.setVisibility(View.INVISIBLE);
                                T5.setVisibility(View.INVISIBLE);
                                result.setVisibility(View.VISIBLE);
                                left.setVisibility(View.VISIBLE);

                            }

                        }
                        else
                            left.setText("");
                    } else if (tp > percent) {
                        int i;
                        for (i = 0; i != -99; i++) {
                            double p = ((present + i) / (total + i) * 100);
                            if (p>tp) break;
                        }
                        result.setText("Attend " + i + " classes for req attendance");
                        atndedt.setVisibility(View.INVISIBLE);
                        taredt.setVisibility(View.INVISIBLE);
                        bnkedt.setVisibility(View.INVISIBLE);
                        sub.setVisibility(View.INVISIBLE);
                        target.setVisibility(View.INVISIBLE);
                        bunk.setVisibility(View.INVISIBLE);
                        attend.setVisibility(View.INVISIBLE);
                        line.setVisibility(View.INVISIBLE);
                        T1.setVisibility(View.INVISIBLE);
                        T2.setVisibility(View.INVISIBLE);
                        T3.setVisibility(View.INVISIBLE);
                        T4.setVisibility(View.INVISIBLE);
                        T5.setVisibility(View.INVISIBLE);
                        result.setVisibility(View.VISIBLE);
                        left.setVisibility(View.VISIBLE);

                        if((int)tp!=75) {
                            double att = i;
                            double  p;
                            if (75 < tp) {
                                for (i = 0; i != -99; i++) {
                                    p = ((present+att) / (total+att+ i)) * 100;
                                    if (p < 75) break;
                                }
                                left.setText("Bunk " + (i-1) + " classes afterwards for 75% ");
                                atndedt.setVisibility(View.INVISIBLE);
                                taredt.setVisibility(View.INVISIBLE);
                                bnkedt.setVisibility(View.INVISIBLE);
                                sub.setVisibility(View.INVISIBLE);
                                target.setVisibility(View.INVISIBLE);
                                bunk.setVisibility(View.INVISIBLE);
                                attend.setVisibility(View.INVISIBLE);
                                line.setVisibility(View.INVISIBLE);
                                T1.setVisibility(View.INVISIBLE);
                                T2.setVisibility(View.INVISIBLE);
                                T3.setVisibility(View.INVISIBLE);
                                T4.setVisibility(View.INVISIBLE);
                                T5.setVisibility(View.INVISIBLE);
                                result.setVisibility(View.VISIBLE);
                                left.setVisibility(View.VISIBLE);

                            } else if (75 > tp) {
                                for (i = 0; i != -99; i++) {
                                    p = ((present+att + i) / (total+att + i) * 100);
                                    if (p > 75) break;
                                }
                                left.setText("Attend " + (i-1) + " more classes for 75%");
                                atndedt.setVisibility(View.INVISIBLE);
                                taredt.setVisibility(View.INVISIBLE);
                                bnkedt.setVisibility(View.INVISIBLE);
                                sub.setVisibility(View.INVISIBLE);
                                target.setVisibility(View.INVISIBLE);
                                bunk.setVisibility(View.INVISIBLE);
                                line.setVisibility(View.INVISIBLE);
                                attend.setVisibility(View.INVISIBLE);
                                T1.setVisibility(View.INVISIBLE);
                                T2.setVisibility(View.INVISIBLE);
                                T3.setVisibility(View.INVISIBLE);
                                T4.setVisibility(View.INVISIBLE);
                                T5.setVisibility(View.INVISIBLE);
                                result.setVisibility(View.VISIBLE);
                                left.setVisibility(View.VISIBLE);

                            }

                        }
                    }
                }

            }}
        );

        attend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String s= atndedt.getText().toString().trim();

                if(s.equals(""))
                    Toast.makeText(getApplicationContext(),"enter some value",Toast.LENGTH_SHORT).show();
                else {
                    int c = new Scanner(s).nextInt();
                    InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (getCurrentFocus() != null)
                        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    double p = ((present + c) / (total + c)) * 100;
                    result.setText("You attendance will be " + String.format("%.2f", p));
                    atndedt.setVisibility(View.INVISIBLE);
                    taredt.setVisibility(View.INVISIBLE);
                    bnkedt.setVisibility(View.INVISIBLE);
                    sub.setVisibility(View.INVISIBLE);
                    target.setVisibility(View.INVISIBLE);
                    bunk.setVisibility(View.INVISIBLE);
                    attend.setVisibility(View.INVISIBLE);
                    T1.setVisibility(View.INVISIBLE);
                    T2.setVisibility(View.INVISIBLE);
                    T3.setVisibility(View.INVISIBLE);
                    T4.setVisibility(View.INVISIBLE);
                    T5.setVisibility(View.INVISIBLE);
                    result.setVisibility(View.VISIBLE);
                    left.setVisibility(View.VISIBLE);

                    int att = c,i;
                    double pr;
                    if (75 < p) {

                        for (i = 0; i != -99; i++) {
                            pr = ((present+att )/ (total+att + i)) * 100;
                            if (pr < 75) break;
                        }
                        left.setText("Bunk " + (i-1) + " classes afterwards for 75% ");
                        atndedt.setVisibility(View.INVISIBLE);
                        taredt.setVisibility(View.INVISIBLE);
                        bnkedt.setVisibility(View.INVISIBLE);
                        sub.setVisibility(View.INVISIBLE);
                        target.setVisibility(View.INVISIBLE);
                        bunk.setVisibility(View.INVISIBLE);
                        attend.setVisibility(View.INVISIBLE);
                        T1.setVisibility(View.INVISIBLE);
                        T2.setVisibility(View.INVISIBLE);
                        T3.setVisibility(View.INVISIBLE);
                        T4.setVisibility(View.INVISIBLE);
                        T5.setVisibility(View.INVISIBLE);
                        result.setVisibility(View.VISIBLE);
                        left.setVisibility(View.VISIBLE);

                    } else if (75 > p) {
                        for (i = 0; i != -99; i++) {
                            pr = ((present+att + i) / (total+att+ i) * 100);
                            if (pr > 75) break;
                        }
                        left.setText("Attend " + (i-1) + " more classes for 75%");
                        atndedt.setVisibility(View.INVISIBLE);
                        taredt.setVisibility(View.INVISIBLE);
                        bnkedt.setVisibility(View.INVISIBLE);
                        sub.setVisibility(View.INVISIBLE);
                        target.setVisibility(View.INVISIBLE);
                        bunk.setVisibility(View.INVISIBLE);
                        attend.setVisibility(View.INVISIBLE);
                        T1.setVisibility(View.INVISIBLE);
                        T2.setVisibility(View.INVISIBLE);
                        T3.setVisibility(View.INVISIBLE);
                        T4.setVisibility(View.INVISIBLE);
                        T5.setVisibility(View.INVISIBLE);
                        result.setVisibility(View.VISIBLE);
                        left.setVisibility(View.VISIBLE);

                    }

                }

            }
        });

        bunk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String s= bnkedt.getText().toString().trim();

                if(s.equals(""))
                    Toast.makeText(getApplicationContext(),"enter some value",Toast.LENGTH_SHORT).show();
                else {
                    InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (getCurrentFocus() != null)
                        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    int c = new Scanner(bnkedt.getText().toString().trim()).nextInt();
                    double p = ((present) / (total + c)) * 100;
                    result.setText("You attendance will be " + String.format("%.2f", p));
                    atndedt.setVisibility(View.INVISIBLE);
                    taredt.setVisibility(View.INVISIBLE);
                    bnkedt.setVisibility(View.INVISIBLE);
                    sub.setVisibility(View.INVISIBLE);
                    target.setVisibility(View.INVISIBLE);
                    bunk.setVisibility(View.INVISIBLE);
                    T1.setVisibility(View.INVISIBLE);
                    T2.setVisibility(View.INVISIBLE);
                    T3.setVisibility(View.INVISIBLE);
                    T4.setVisibility(View.INVISIBLE);
                    T5.setVisibility(View.INVISIBLE);
                    result.setVisibility(View.VISIBLE);
                    left.setVisibility(View.VISIBLE);
                    attend.setVisibility(View.INVISIBLE);

                    int b=c,i;
                    double pr;

                    if (75 < p) {

                        for (i = 0; i != -99; i++) {
                            pr = (present / (total+b + i)) * 100;
                            if (pr < 75) break;
                        }
                        left.setText("Bunk " + (i-1) + " more classes for 75% ");atndedt.setVisibility(View.INVISIBLE);
                        taredt.setVisibility(View.INVISIBLE);
                        bnkedt.setVisibility(View.INVISIBLE);
                        sub.setVisibility(View.INVISIBLE);
                        target.setVisibility(View.INVISIBLE);
                        bunk.setVisibility(View.INVISIBLE);
                        attend.setVisibility(View.INVISIBLE);
                        T1.setVisibility(View.INVISIBLE);
                        T2.setVisibility(View.INVISIBLE);
                        T3.setVisibility(View.INVISIBLE);
                        T4.setVisibility(View.INVISIBLE);
                        T5.setVisibility(View.INVISIBLE);
                        result.setVisibility(View.VISIBLE);
                        left.setVisibility(View.VISIBLE);


                    } else if (75 > p) {
                        for (i = 0; i != -99; i++) {
                            pr = ((present + i) / (total+b + i) * 100);
                            if (pr > 75) break;
                        }
                        left.setText("Attend " + (i-1) + " classes after bunk for 75%");
                        atndedt.setVisibility(View.INVISIBLE);
                        taredt.setVisibility(View.INVISIBLE);
                        bnkedt.setVisibility(View.INVISIBLE);
                        sub.setVisibility(View.INVISIBLE);
                        target.setVisibility(View.INVISIBLE);
                        bunk.setVisibility(View.INVISIBLE);
                        attend.setVisibility(View.INVISIBLE);
                        T1.setVisibility(View.INVISIBLE);
                        T2.setVisibility(View.INVISIBLE);
                        T3.setVisibility(View.INVISIBLE);
                        T4.setVisibility(View.INVISIBLE);
                        T5.setVisibility(View.INVISIBLE);
                        result.setVisibility(View.VISIBLE);
                        left.setVisibility(View.VISIBLE);

                    }



                }
            }
        });

        bnkedt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                taredt.setText("");
                atndedt.setText("");
                return false;
            }
        });

        atndedt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bnkedt.setText("");
                taredt.setText("");
                return false;
            }
        });

        taredt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bnkedt.setText("");
                atndedt.setText("");
                return false;
            }
        });





    }

}
