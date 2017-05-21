package vna.example.com.education.Login_signup_Spcify;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import vna.example.com.education.R;

public class DocOrStudent extends AppCompatActivity {
    Button student,doctor;
    TextView welcome;
    Typeface typefacewelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_doc_or_student);


        student        =(Button)findViewById(R.id.student_to_login);
        doctor         =(Button)findViewById(R.id.doc_to_login);
        welcome=(TextView)findViewById(R.id.welcome);
        typefacewelcome= Typeface.createFromAsset(getAssets(),"fonts/Oswald-Stencbab.ttf");
        welcome.setTypeface(typefacewelcome);

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DocOrStudent.this,LoginWzStudent.class));
            }
        });

        doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DocOrStudent.this,LoginWzDoc.class));

            }
        });


    }

}
