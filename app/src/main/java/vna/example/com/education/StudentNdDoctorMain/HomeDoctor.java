package vna.example.com.education.StudentNdDoctorMain;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import vna.example.com.education.AssimentsAndcourceAndQuiz.DetailCourseForDoctor;
import vna.example.com.education.AssimentsAndcourceAndQuiz.InsertCourse;
import vna.example.com.education.CustomAdapter.CustomAdapterCourse;
import vna.example.com.education.Models.ModelCourse;
import vna.example.com.education.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeDoctor extends Fragment {

    ArrayList<ModelCourse> itemsCourse=new ArrayList<>();
    ListView itemsCourseList;
    String name_course;
    int []doctor__id;
    ImageButton imageButton;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home_doctor, container, false);
        itemsCourseList=(ListView)rootView.findViewById(R.id.item_course_list);
        imageButton=(ImageButton) rootView.findViewById(R.id.insert_btn_course) ;

        Log.i("idusers",id_doc());
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),InsertCourse.class);
                intent.putExtra("id_doc",id_doc());
                startActivity(intent);

            }
        });




        String url="http://"+getResources().getString(R.string.ip)+":8080/educationelctronic/selectCourcesForDoctor.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray=jsonObject.getJSONArray("message");
                            doctor__id=new int[jsonArray.length()];
                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                                name_course=jsonObject1.getString("name_course");
                                Log.i("naaaaaaaaaaaaaaaame",name_course);
                                itemsCourse.add(new ModelCourse(name_course));
                                doctor__id[i]=Integer.parseInt(jsonObject1.getString("id_course"));

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        CustomAdapterCourse customAdapterCourse=new CustomAdapterCourse(itemsCourse,getActivity());
                        itemsCourseList.setAdapter(customAdapterCourse);
                        customAdapterCourse.notifyDataSetChanged();





                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("onresponse error   *******************************");
                    }
                }) ;
        Singleton.getInstance(getActivity()).addRequestQue(stringRequest);
        itemsCourseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getActivity(),DetailCourseForDoctor.class);
                intent.putExtra("id_course",doctor__id[i]);
                startActivity(intent);
            }
        });

        return rootView;

    }
    public String id_doc(){
        Intent n=getActivity().getIntent();
        String id=n.getStringExtra("id_doctor");
        return id;
    }
}
