package vna.example.com.education.Login_signup_Spcify;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;

import vna.example.com.education.R;

public class SignUpStudent extends AppCompatActivity {
    EditText first_name;
    EditText last_name;
    EditText phone;
    EditText uusername;
    EditText ppasward;
    String getfirst_name;
    String getlast_name;
    String getemail;
    String getuusername;
    String getppasward;
    AppCompatButton create_account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        create_account=(AppCompatButton) findViewById(R.id.create);
        initElement();
        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check_element()){
                    String type="logIn";

                    new SignAsync(getApplicationContext(),getResources().getString(R.string.ip)).execute(type
                            ,first_name.getText().toString()
                            ,last_name.getText().toString()
                            ,phone.getText().toString()
                            ,uusername.getText().toString()
                            , ppasward.getText().toString());
                }

            }
        });

    }
    public void initElement(){

        first_name=(EditText) findViewById(R.id.firstname_student);
        last_name=(EditText) findViewById(R.id.secondnam_student);
        phone=(EditText) findViewById(R.id.phone_student);
        uusername=(EditText) findViewById(R.id.username_student);
        ppasward=(EditText) findViewById(R.id.passwad_student);
    }
    public  boolean check_element(){
        boolean result=true;
        getfirst_name=first_name.getText().toString();
        getlast_name=last_name.getText().toString();
        getemail=phone.getText().toString();
        getuusername=uusername.getText().toString();
        getppasward=ppasward.getText().toString();



        if(getfirst_name.isEmpty()){
            first_name.setError("plz enter first name");
            first_name.requestFocus();
            result= false;
        }
        if(getlast_name.isEmpty()){
            last_name.setError("plz enter last  name");
            last_name.requestFocus();
            result= false;
        }
        if(getemail.isEmpty() ){
            phone.setError("plz enter  Email");
            phone.requestFocus();
            result= false;
        }

        if(getuusername.isEmpty()){
            uusername.setError("plz enter user name");
            uusername.requestFocus();
            result= false;
        }
        if(getppasward.isEmpty() ||getppasward.length()<9){
            ppasward.setError("plz enter paswward");
            ppasward.requestFocus();
            result= false;
        }
        return result;
    }
}

