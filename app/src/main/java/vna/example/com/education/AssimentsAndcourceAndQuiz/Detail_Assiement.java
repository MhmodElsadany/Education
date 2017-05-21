package vna.example.com.education.AssimentsAndcourceAndQuiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;

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
import vna.example.com.education.StudentNdDoctorMain.Singleton;

public class Detail_Assiement extends AppCompatActivity {
    AppCompatTextView instruction;
    AppCompatTextView description;
    AppCompatTextView starttime;
    AppCompatTextView endtime;
    AppCompatButton start;
    String instructiontxt, descriptiontxt, starttimetxt, endtimetxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__assiement);
        instruction = (AppCompatTextView) findViewById(R.id.instruction);
        description = (AppCompatTextView) findViewById(R.id.description);
        starttime = (AppCompatTextView) findViewById(R.id.starttime);
        endtime = (AppCompatTextView) findViewById(R.id.endtime);
        start = (AppCompatButton) findViewById(R.id.startquz);
        Log.i("lllllllll", assienmnt() + "");
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Detail_Assiement.this,Quizzer.class);
                intent.putExtra("assiment_id",assienmnt());
                startActivity(intent);
            }
        });

        String url = "http://" + getResources().getString(R.string.ip) + ":8080/educationelctronic/select_assiemnt_detail.php?";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            JSONArray jsonArray = jsonObject.getJSONArray("message");

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                                instructiontxt = jsonObject1.getString("instruction");
                                Log.i("ooooooooooooo", instructiontxt + "");

                                descriptiontxt = jsonObject1.getString("description");

                                starttimetxt = jsonObject1.getString("starttime");
                                endtimetxt = jsonObject1.getString("endtime");

                            }
                            instruction.setText(instructiontxt);
                            description.setText(descriptiontxt);
                            starttime.setText(starttimetxt);
                            endtime.setText(endtimetxt);

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
                map.put("id_assiment", assienmnt());


                return map;
            }
        };
        Singleton.getInstance(this).addRequestQue(stringRequest);


    }

    public String assienmnt() {
        String assienment = getIntent().getExtras().getString("id_assiment");
        return assienment;

    }

}
