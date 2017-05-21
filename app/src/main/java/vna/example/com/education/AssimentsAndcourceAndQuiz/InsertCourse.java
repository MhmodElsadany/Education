package vna.example.com.education.AssimentsAndcourceAndQuiz;

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

public class InsertCourse extends AppCompatActivity {
    EditText name, number, point, semster;
    Button submit;
    StringRequest stringRequest;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_course);
        name = (EditText) findViewById(R.id.name_course_insert);
        number = (EditText) findViewById(R.id.number_course_insert);
        point = (EditText) findViewById(R.id.point_course_insert);
        semster = (EditText) findViewById(R.id.smster_course_insert);
        submit = (Button) findViewById(R.id.course_sub_insert);
         Log.i("mahmooooooooood", id_doc());

        String url = "http://" + getResources().getString(R.string.ip) + ":8080/educationelctronic/inserCources.php?";
        stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
                        Log.i("rrrrrr", response);
                        alertDialog = new AlertDialog.Builder(InsertCourse.this).create();
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

                map.put("id_doc", id_doc());
                map.put("name", name.getText().toString());
                map.put("point", point.getText().toString());
                map.put("number", number.getText().toString());
                map.put("semster", semster.getText().toString());
                return map;
            }
        };
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Singleton.getInstance(InsertCourse.this).addRequestQue(stringRequest);

            }
        });

    }

    public String id_doc() {
        String id_doc = getIntent().getStringExtra("id_doc");
        return id_doc;
    }

}
