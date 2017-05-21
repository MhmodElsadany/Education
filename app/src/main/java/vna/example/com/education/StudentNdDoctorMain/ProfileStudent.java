package vna.example.com.education.StudentNdDoctorMain;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import vna.example.com.education.R;

/**
 * Created by Google       Company on 11/05/2017.
 */

public class ProfileStudent extends android.support.v4.app.Fragment {
    TextView firstname, lastname, phone, username;
    String id_user;
    String first="null", second="null", third="null", four="null";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.profile, container, false);
        firstname = (TextView) rootView.findViewById(R.id.nameprofile);
        lastname = (TextView) rootView.findViewById(R.id.secondnameprofile);
        phone = (TextView) rootView.findViewById(R.id.phoneprofile);
        username = (TextView) rootView.findViewById(R.id.usernameprofile);
        String url = "http://" + getResources().getString(R.string.ip) + ":8080/educationelctronic/select_user_info.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("message");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                first = jsonObject1.getString("firstname");
                                Log.i("hhhhhhhhhhhhhh", jsonObject1.getString("firstname"));
                                second = jsonObject1.getString("secondname");
                                Log.i("hhhhhhhhhhhhhh", jsonObject1.getString("secondname"));

                                third = jsonObject1.getString("phone");
                                Log.i("hhhhhhhhhhhhhh", jsonObject1.getString("phone"));

                                four = jsonObject1.getString("username");

                                firstname.setText(first);
                                lastname.setText(second);
                                username.setText(four);
                                phone.setText(third);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


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

                return map;
            }
        };
        Singleton.getInstance(getActivity()).addRequestQue(stringRequest);


        return rootView;
    }
    public String id_user(){
        Intent n=getActivity().getIntent();
        String id=n.getStringExtra("id_user");
        return id;
    }
}