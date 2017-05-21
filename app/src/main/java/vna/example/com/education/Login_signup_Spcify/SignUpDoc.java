package vna.example.com.education.Login_signup_Spcify;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import vna.example.com.education.R;

public class SignUpDoc extends AppCompatActivity {
    EditText first_name;
    EditText last_name;
    EditText phone;
    EditText uusername;
    EditText ppasward;
    EditText address;
    String getfirst_name;
    String getlast_name;
    String getaddress;
    String getemail;
    String getuusername;
    String getppasward;
    AppCompatButton create_account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);
        create_account=(AppCompatButton) findViewById(R.id.create);
        initElement();
        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check_element()){
                    String type="logIn";

                    new SignDoc().execute(type
                            ,first_name.getText().toString()
                            ,last_name.getText().toString()
                            ,phone.getText().toString()
                            ,address.getText().toString()
                            ,uusername.getText().toString()
                            , ppasward.getText().toString());
                }

            }
        });

    }
    public void initElement(){

        first_name=(EditText) findViewById(R.id.firstname_doc);
        last_name =(EditText) findViewById(R.id.secondnamdoc);
        address   =(EditText) findViewById(R.id.address_doc);
        phone     =(EditText) findViewById(R.id.docphone);
        uusername =(EditText) findViewById(R.id.usernamedoc);
        ppasward  =(EditText) findViewById(R.id.passwad_doc);
    }
    public  boolean check_element(){
        boolean result=true;
        getfirst_name  =first_name.getText().toString();
        getlast_name   =last_name.getText().toString();
        getuusername   =uusername.getText().toString();
        getppasward    =ppasward.getText().toString();
        getaddress     =address.getText().toString();
        getemail       =phone.getText().toString();



        if(getfirst_name.isEmpty()){
            first_name.setError("plz enter first name");
            first_name.requestFocus();
            result= false;
        }
        if (getaddress.isEmpty()) {
            address.setError("plz enter your adress");
            address.requestFocus();
            result=false;
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
    public class SignDoc extends AsyncTask<String,Void,String> {
        Context c;
        AlertDialog alertDialog;

        @Override
        protected void onPostExecute (String s){
            super.onPostExecute(s);
            System.out.print(s+"fddddddddmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmdddddddddddddddddddddddddddddddddd");
            Log.i("kkk",s);
//            alertDialog.setMessage(s);
            // alertDialog.show();
        }

        @Override
        protected void onPreExecute () {
//            alertDialog = new AlertDialog.Builder(c).create();
  //          alertDialog.setTitle("status login");
        }

        @Override
        protected String doInBackground (String...params){
            Log.v("kkk","kmnnnnnnnnnnnnnnnnnnnnnkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");

            String type = params[0];
            String firstname=params[1];
            Log.v("kkk",firstname+"");

            String seconname=params[2];
            Log.v("kkk",seconname+"");

            String phone=params[3];
            Log.v("kkk",seconname+"");

            String address=params[4];
            Log.v("kkk",seconname+"");

            String user=params[5];
            Log.v("kkk",seconname+"");

            String user_pass = params[6];
            Log.v("kkk",seconname+"");

            if (type.equals("logIn")) {
                try {
                    String ip=getResources().getString(R.string.ip);
                    URL url = new URL("http://"+ip+":8080/educationelctronic/newdoctor.php?");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);

                    httpURLConnection.connect();
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("firstname", "UTF-8") + "=" + URLEncoder.encode(firstname, "UTF-8") + "&" +
                            URLEncoder.encode("seconname", "UTF-8") + "=" + URLEncoder.encode(seconname, "UTF-8")
                            + "&" +
                            URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(phone, "UTF-8")
                            + "&" +
                            URLEncoder.encode("address", "UTF-8") + "=" + URLEncoder.encode(address, "UTF-8")
                            + "&" +
                            URLEncoder.encode("user", "UTF-8") + "=" + URLEncoder.encode(user, "UTF-8")
                            + "&" +
                            URLEncoder.encode("user_pass", "UTF-8") + "=" + URLEncoder.encode(user_pass, "UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String res = "";
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        res += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    ;
                    return res;
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
            return null;
        }
    }
}



