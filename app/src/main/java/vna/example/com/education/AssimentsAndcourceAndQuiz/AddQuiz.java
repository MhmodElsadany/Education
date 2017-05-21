package vna.example.com.education.AssimentsAndcourceAndQuiz;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
import vna.example.com.education.StudentNdDoctorMain.Singleton;

public class AddQuiz extends AppCompatActivity {
    EditText question, answera, answerb, answerec, answerd, correctanswer;
    AlertDialog alertDialog;
    Button submit;
    StringRequest stringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quiz);

        question = (EditText) findViewById(R.id.question);
        answera = (EditText) findViewById(R.id.answera);
        answerb = (EditText) findViewById(R.id.answerb);
        answerec = (EditText) findViewById(R.id.answerc);
        answerd = (EditText) findViewById(R.id.answerd);
        correctanswer = (EditText) findViewById(R.id.correctanswer);
        submit = (Button) findViewById(R.id.addquestion);


        String url = "http://" + getResources().getString(R.string.ip) + ":8080/educationelctronic/insertquizquetion.php";

        stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {

                        alertDialog = new AlertDialog.Builder(AddQuiz.this).create();
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

                map.put("id_assiment", id_assiment());
                map.put("question", question.getText().toString());
                map.put("answera", answera.getText().toString());
                map.put("answerb", answerb.getText().toString());
                map.put("answerec", answerec.getText().toString());
                map.put("answerd", answerd.getText().toString());
                map.put("correctanswer", correctanswer.getText().toString());


                return map;
            }
        };
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Singleton.getInstance(AddQuiz.this).addRequestQue(stringRequest);

            }
        });
    }

    private String id_assiment() {
        String idassiment = getIntent().getStringExtra("assiment_id");
        return idassiment;


    }

}
