package vna.example.com.education.AssimentsAndcourceAndQuiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import vna.example.com.education.R;
import vna.example.com.education.StudentNdDoctorMain.Singleton;

public class EditCourse extends AppCompatActivity {

    EditText name,number,point,semster;
    Button submit;
    StringRequest stringRequest;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);
        name=(EditText)findViewById(R.id.name_course_edit);
        number=(EditText)findViewById(R.id.number_course_edit);
        point=(EditText)findViewById(R.id.point_course_edit);
        semster=(EditText)findViewById(R.id.smster_course_edit);
        submit=(Button) findViewById(R.id.course_sub_edit);
        Log.i("iddd",id_course());



        String url = "http://"+getResources().getString(R.string.ip)+ ":8080/educationelctronic/update_course.php?";
        stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
                        Log.i("rrrrrr",response);
                        Log.i("iddd",id_course());
                        alertDialog=new AlertDialog.Builder(EditCourse.this).create();
                        alertDialog.setTitle("update your profile  ");
                        alertDialog.setMessage(response);
                        alertDialog.show();


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("onresponse error   *******************************");
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();

                map.put("id_course",id_course());
                map.put("name",name.getText().toString());
                map.put("point",point.getText().toString());
                map.put("number",number.getText().toString());
                map.put("semster",semster.getText().toString());
                return map;
            }
        };
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Singleton.getInstance(EditCourse.this).addRequestQue(stringRequest);

            }
        });
    }
    public String id_course(){
        Intent n=getIntent();
        String id=n.getStringExtra("id_coursee");
        return id;
    }

}
