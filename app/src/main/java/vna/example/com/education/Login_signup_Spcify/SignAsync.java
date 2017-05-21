package vna.example.com.education.Login_signup_Spcify;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Google       Company on 13/03/2017.
 */

public class SignAsync extends AsyncTask<String,Void,String> {
    Context c;
    AlertDialog alertDialog;
    String ip;

    public SignAsync(Context c,String ip) {
        this.c = c;
        this.ip=ip;
    }
    @Override
    protected void onPostExecute (String s){
        super.onPostExecute(s);
        System.out.print(s+"fddddddddmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmdddddddddddddddddddddddddddddddddd");
        Log.i("kkk",s);
        alertDialog.setMessage(s);
        // alertDialog.show();
    }

    @Override
    protected void onPreExecute () {
        alertDialog = new AlertDialog.Builder(c).create();
        alertDialog.setTitle("status login");
    }

    @Override
    protected String doInBackground (String...params){
        Log.v("kkk","kmnnnnnnnnnnnnnnnnnnnnnkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");

        String type = params[0];
        String firstname=params[1];
        Log.i("kkk",firstname+"kkkknnnnnnnnnnnnnnnnnnnnnnnnngggggggggkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");

        String seconname=params[2];
        Log.i("kkk",seconname+"kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");

        String phone=params[3];
        Log.i("kkk",phone+"kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");

        String user=params[4];
        Log.i("kkk",user+"kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");

        String user_pass = params[5];
        Log.i("kkk",user_pass+"kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");

        if (type.equals("logIn")) {
            try {

                URL url = new URL("http://192.168.1.4:8080/educationelctronic/newuser.php?");
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