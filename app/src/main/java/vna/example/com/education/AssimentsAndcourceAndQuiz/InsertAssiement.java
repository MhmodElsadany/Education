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

public class InsertAssiement extends AppCompatActivity {
    EditText instruction, description, starttime, endtime;
    Button submit;
    StringRequest stringRequest;
    AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_assiement);

        instruction = (EditText) findViewById(R.id.instruction_insert);
        description = (EditText) findViewById(R.id.description_insert);
        starttime = (EditText) findViewById(R.id.starttime_insert);
        endtime = (EditText) findViewById(R.id.endtime_insert);
        submit = (Button) findViewById(R.id.assim_sub_insert);

        String url = "http://" + getResources().getString(R.string.ip) + ":8080/educationelctronic/insertassiment.php?";
        stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
                        Log.i("rrrrrr", response);
                        alertDialog = new AlertDialog.Builder(InsertAssiement.this).create();
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

                map.put("coursenumber", id_course());
                map.put("instruction", instruction.getText().toString());
                map.put("description", description.getText().toString());
                map.put("starttime", starttime.getText().toString());
                map.put("endtime", endtime.getText().toString());
                return map;
            }
        };
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Singleton.getInstance(InsertAssiement.this).addRequestQue(stringRequest);

            }
        });

    }
    public String id_course() {
        String idcourse = getIntent().getExtras().getString("id_course");
        return idcourse;
    }


}
