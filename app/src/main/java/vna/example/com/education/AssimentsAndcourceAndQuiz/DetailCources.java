package vna.example.com.education.AssimentsAndcourceAndQuiz;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import vna.example.com.education.R;
import vna.example.com.education.StudentNdDoctorMain.Singleton;

public class DetailCources extends AppCompatActivity {
    AppCompatTextView name;
    AppCompatTextView instractor;
    AppCompatTextView number;
    AppCompatTextView point;
    AppCompatTextView semster;
    String nametxt = null, instractortxt = null, numbertxt = null, pointtxt = null, semstertxt = null;
    String id_ssiements;
    ArrayList<String> ssiements = new ArrayList<>();
    ListView assinmentlist;
    AppCompatTextView ass;
    Typeface typefacewelcome;
    StringRequest stringRequest;
ArrayList<String>assimentid=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cources);
        name = (AppCompatTextView) findViewById(R.id.course_name_detail);
        instractor = (AppCompatTextView) findViewById(R.id.instractot_detail);
        number = (AppCompatTextView) findViewById(R.id.num_detail);
        point = (AppCompatTextView) findViewById(R.id.point_detail);
        semster = (AppCompatTextView) findViewById(R.id.semster_detail);
        assinmentlist = (ListView) findViewById(R.id.listassinments);
        ass= (AppCompatTextView) findViewById(R.id.kk);
        typefacewelcome= Typeface.createFromAsset(getAssets(),"fonts/Oswald-Stencbab.ttf");
        ass.setTypeface(typefacewelcome);


        Log.i("lllllllll",id_course()+"");
        Log.i("idcourse", id_course() + "");
        String url = "http://" + getResources().getString(R.string.ip) + ":8080/educationelctronic/cources_detail.php";
        stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("message");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                nametxt = jsonObject1.getString("name_course");
                                Log.i("lllllllll",nametxt+"");

                                instractortxt = jsonObject1.getString("doc_name");
                                numbertxt = jsonObject1.getString("number");
                                pointtxt = jsonObject1.getString("point");
                                semstertxt = jsonObject1.getString("semster");

                            }
                            name.setText(nametxt);
                            instractor.setText(instractortxt);
                            number.setText(numbertxt);
                            point.setText(pointtxt);
                            semster.setText(semstertxt);

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
                map.put("id_course", id_course() + "");


                return map;
            }
        };
        Singleton.getInstance(this).addRequestQue(stringRequest);


        String urli = "http://" + getResources().getString(R.string.ip) + ":8080/educationelctronic/select_assiemnt.php";
        stringRequest = new StringRequest(Request.Method.POST, urli,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("message");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                id_ssiements = jsonObject1.getString("id_assiment");
                                ssiements.add("assienments" + id_ssiements);
                                assimentid.add(id_ssiements);
                                Log.i("iiiiiiiiiiiiiiiiiii",id_ssiements);
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(DetailCources.this, R.layout.assiemnt_row, R.id.assimenttxt, ssiements);
                            assinmentlist.setAdapter(adapter);
                            assinmentlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    Intent intent = new Intent(DetailCources.this, Detail_Assiement.class);
                                    intent.putExtra("id_assiment", assimentid.get(i));
                                    startActivity(intent);
                                }
                            });
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
                map.put("id_course", id_course() + "");


                return map;
            }
        };
        Singleton.getInstance(this).addRequestQue(stringRequest);
    }

    public int id_course() {
        int idcourse = getIntent().getExtras().getInt("id_course");
        return idcourse;
    }

}