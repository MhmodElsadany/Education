package vna.example.com.education.StudentNdDoctorMain;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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

public class EditProfileStudent extends AppCompatActivity {
EditText firstname,secondname,phone,username,passward;
    AlertDialog alertDialog;
    Button submit;
    StringRequest stringRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        firstname=(EditText)findViewById(R.id.firstname_student_edit);
        secondname=(EditText)findViewById(R.id.secondnam_student_edit);
        phone=(EditText)findViewById(R.id.phone_student_edit);
        username=(EditText)findViewById(R.id.username_student_edit);
        passward=(EditText)findViewById(R.id.passwad_student_edit);
        submit=(Button)findViewById(R.id.student_sub_edit);

        String url = "http://" + getResources().getString(R.string.ip) + ":8080/educationelctronic/update_user_info.php";

         stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {

                        alertDialog=new AlertDialog.Builder(EditProfileStudent.this).create();
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
                map.put("id_user",id_user());
                map.put("firstname",firstname.getText().toString());
                map.put("secondname",secondname.getText().toString());
                map.put("phone",phone.getText().toString());
                map.put("username",username.getText().toString());
                map.put("passward",passward.getText().toString());



                return map;
            }
        };
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Singleton.getInstance(EditProfileStudent.this).addRequestQue(stringRequest);

            }
        });
    }
    public String id_user(){
        Intent n=getIntent();
        String id=n.getStringExtra("id_user");
        return id;
    }

}
