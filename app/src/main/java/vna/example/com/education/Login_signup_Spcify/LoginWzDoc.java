package vna.example.com.education.Login_signup_Spcify;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import vna.example.com.education.StudentNdDoctorMain.DoctorMainActivity;
import vna.example.com.education.R;

public class LoginWzDoc extends AppCompatActivity {
    Button login;
    TextView create_account;
    EditText usernameEdit,passEdt;
    String usernameedit;
    String passwardedit;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    CheckBox checkBox;
    String res;
    AlertDialog alertDialog;
    TextView welcome;
    Typeface typefacewelcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_wz_doc);
        login          =(Button)findViewById(R.id.logindoctor);
        checkBox       = (CheckBox) findViewById(R.id.remember_me);
        usernameEdit   =(EditText)findViewById(R.id.usernamedoctor);
        passEdt        =(EditText)findViewById(R.id.passwaddoctor);
        create_account =(TextView) findViewById(R.id.signipdoctor);
        welcome=(TextView)findViewById(R.id.welcome);
        typefacewelcome= Typeface.createFromAsset(getAssets(),"fonts/Oswald-Stencbab.ttf");
        welcome.setTypeface(typefacewelcome);


        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(LoginWzDoc.this, SignUpDoc.class);
                startActivity(in);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!registration()) {
                    Toast.makeText(LoginWzDoc.this, "plz ensure username and paswward", Toast.LENGTH_SHORT);

                } else {

                    new LoginWzDoc.AsyncDoc().execute("logIn", usernameEdit.getText().toString(), passEdt.getText().toString());


                }
            }
        });



        restorepreferance();
    }

    @Override
    protected void onPause() {
        super.onPause();
        checkbox();
    }

    public class AsyncDoc extends AsyncTask<String, Void, String> {


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s==null||s.equals("username or passward invalid")){
                alertDialog.setMessage(s);
                alertDialog.show();

            }
            else {
                alertDialog.setMessage(s);
                Intent in = new Intent(LoginWzDoc.this, DoctorMainActivity.class);
                in.putExtra("id_doctor", s);
                Log.i("iduser",s);
                startActivity(in);
                alertDialog.show();

            }

        }

        @Override
        public void onPreExecute() {
            alertDialog=new AlertDialog.Builder(LoginWzDoc.this).create();
            alertDialog.setTitle("status login");



        }


        @Override
        public String doInBackground(String... params) {
            String type = params[0];
            String user_name = params[1];
            String user_pass = params[2];
            if (type.equals("logIn")) {
                try {
                    String ipaddress=getResources().getString(R.string.ip);

                    URL url = new URL("http://"+ipaddress+":8080/educationelctronic/doctor.php?");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.connect();
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8") +
                            "&" +URLEncoder.encode("user_pass", "UTF-8") + "=" + URLEncoder.encode(user_pass, "UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    res = "";
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        res += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    Log.v("mmmmmmmm5",res+"jkjjhjjjjjjjjj");








                } catch (Exception e) {

                }
                return res;
            }


            return res;
        }

    }
    private boolean registration(){
        boolean result=true;
        usernameedit=this.usernameEdit.getText().toString();
        passwardedit=this.passEdt.getText().toString();
        if(TextUtils.isEmpty(usernameedit)){
            usernameEdit.setError("plz enter you usernaame");
            usernameEdit.requestFocus();
            return false;
        }
        if(TextUtils.isEmpty(passwardedit)){
            this.passEdt.setError("plz enter you passward");
            passEdt.requestFocus();

            return false;
        }

        return result;

    }
    public void restorepreferance() {
        SharedPreferences sharedPreferences = getSharedPreferences("Data2", MODE_PRIVATE);
        usernameEdit.setText(sharedPreferences.getString("username", ""));
        passEdt.setText(sharedPreferences.getString("passward", ""));
        checkBox.setChecked(true);


    }

    public void checkbox() {
        if (checkBox.isChecked()) {


            sharedPreferences = getSharedPreferences("Data2", MODE_PRIVATE);
            editor = sharedPreferences.edit();
            editor.putString("username", usernameEdit.getText().toString());
            editor.putString("passward", passEdt.getText().toString());
            editor.commit();
        }


    }
}


