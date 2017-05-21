package vna.example.com.education.AssimentsAndcourceAndQuiz;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
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

import vna.example.com.education.StudentNdDoctorMain.EditProfileDoc;
import vna.example.com.education.R;
import vna.example.com.education.StudentNdDoctorMain.Singleton;

public class DetailCourseForDoctor extends AppCompatActivity {

    AppCompatTextView name;
    AppCompatTextView instractor;
    AppCompatTextView number;
    AppCompatTextView point;
    AppCompatTextView semster;
    String nametxt = null, instractortxt = null, numbertxt = null, pointtxt = null, semstertxt = null;
    String id_ssiements;
    ArrayList<String> ssiements = new ArrayList<>();
    ListView assinmentlist;
    StringRequest stringRequest;
    ArrayList<String>assimentid=new ArrayList<>();
    ImageButton insert_btn_assiment;
    AppCompatTextView ass;
    Typeface typefacewelcome;

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.forcourse, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {

            int id = item.getItemId();

            if (id == R.id.editcourse) {
                Intent intent=new Intent(DetailCourseForDoctor.this,EditCourse.class);
                intent.putExtra("id_coursee",id_course()+"");
                startActivity(intent);

            }
            else if (id==R.id.insetassiment){

                Intent in=new Intent(this,EditProfileDoc.class);
                in.putExtra("id_doctor",getIntent().getStringExtra("id_doctor"));
                startActivity(in);
            }

            return super.onOptionsItemSelected(item);
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_course_for_doctor);
        name = (AppCompatTextView) findViewById(R.id.course_name_detail);
        instractor = (AppCompatTextView) findViewById(R.id.instractot_detail);
        number = (AppCompatTextView) findViewById(R.id.num_detail);
        point = (AppCompatTextView) findViewById(R.id.point_detail);
        semster = (AppCompatTextView) findViewById(R.id.semster_detail);
        assinmentlist = (ListView) findViewById(R.id.listassinments);
        insert_btn_assiment=(ImageButton)findViewById(R.id.insert_btn_assiment);


        ass= (AppCompatTextView) findViewById(R.id.kk);
        typefacewelcome= Typeface.createFromAsset(getAssets(),"fonts/Oswald-Stencbab.ttf");
        ass.setTypeface(typefacewelcome);

        insert_btn_assiment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DetailCourseForDoctor.this,InsertAssiement.class);
                intent.putExtra("id_course",id_course()+"");
                startActivity(intent);
            }
        });

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
                            int x=0;
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                id_ssiements = jsonObject1.getString("id_assiment");
                                x++;
                                ssiements.add("assienments" + x);
                                assimentid.add(id_ssiements);
                                Log.i("iiiiiiiiiiiiiiiiiii",id_ssiements);
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(DetailCourseForDoctor.this, R.layout.assiemnt_row, R.id.assimenttxt, ssiements);
                            assinmentlist.setAdapter(adapter);
                            assinmentlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    Intent intent = new Intent(DetailCourseForDoctor.this, AssimentDetailForDoctor.class);
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