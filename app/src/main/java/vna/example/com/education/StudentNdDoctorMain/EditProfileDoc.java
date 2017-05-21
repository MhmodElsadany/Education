package vna.example.com.education.StudentNdDoctorMain;

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

public class EditProfileDoc extends AppCompatActivity {
    EditText firstname,secondname,phone,username,passward,address;
    Button submit;
    StringRequest stringRequest;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_doc);
        firstname=(EditText)findViewById(R.id.firstname_doc_edit);
        secondname=(EditText)findViewById(R.id.secondnam_doc_edit);
        phone=(EditText)findViewById(R.id.phone_doc_edit);
        address=(EditText)findViewById(R.id.phone_doc_edit);
        username=(EditText)findViewById(R.id.username_doc_edit);
        passward=(EditText)findViewById(R.id.passwad_doc_edit);
        submit=(Button) findViewById(R.id.submitdactra_edit);



        String url = "http://"+getResources().getString(R.string.ip)+ ":8080/educationelctronic/update_doc_info.php";
                 stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
                        Log.i("rrrrrr",response);
                        Log.i("iddd",id_doc());
                        Log.i("firstname",firstname.getText().toString());
                        Log.i("secondname",secondname.getText().toString());
                        Log.i("phone",phone.getText().toString());
                        Log.i("username",username.getText().toString());
                        Log.i("passward",passward.getText().toString());
                        alertDialog=new AlertDialog.Builder(EditProfileDoc.this).create();
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

                map.put("id_user",id_doc());
                map.put("firstname",firstname.getText().toString());
                map.put("secondname",secondname.getText().toString());
                map.put("phone",phone.getText().toString());
                map.put("address",address.getText().toString());
                map.put("user",username.getText().toString());
                map.put("user_pass",passward.getText().toString());

                return map;
            }
        };
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        Singleton.getInstance(EditProfileDoc.this).addRequestQue(stringRequest);

            }
        });
    }
    public String id_doc(){
        Intent n=getIntent();
        String id=n.getStringExtra("id_doctor");
        return id;
    }

}
