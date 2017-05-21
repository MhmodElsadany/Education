package vna.example.com.education.AssimentsAndcourceAndQuiz;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

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

import vna.example.com.education.Models.QuizModel;
import vna.example.com.education.R;
import vna.example.com.education.StudentNdDoctorMain.Singleton;

public class Quizzer extends AppCompatActivity {
    TextView ques;
    RadioButton a;
    RadioButton b;
    RadioButton c;
    RadioButton d;
    String qq, aa, bb, cc, dd;
    String answercorrect = null;
    int score = 0;
    Button next;
    int i = 1;

    String checked = null;
    ArrayList<QuizModel> quizitem = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizzer);
        ques = (TextView) findViewById(R.id.quest);
        a = (RadioButton) findViewById(R.id.a);
        b = (RadioButton) findViewById(R.id.b);
        c = (RadioButton) findViewById(R.id.c);
        d = (RadioButton) findViewById(R.id.d);
        next = (Button) findViewById(R.id.next);
        Log.i("llllnnnnnnnnnnnnnnnnnl", score + "");
        Log.i("uiuiuiuiiuu", a.getText().toString() + "");





        String url = "http://" + getResources().getString(R.string.ip) + ":8080/educationelctronic/select_question.php?";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            JSONArray jsonArray = jsonObject.getJSONArray("message");

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                                qq = jsonObject1.getString("question");
                                aa = jsonObject1.getString("a");
                                bb = jsonObject1.getString("b");
                                cc = jsonObject1.getString("c");
                                dd = jsonObject1.getString("d");
                                answercorrect = jsonObject1.getString("correct");
                                Log.i("answercorrect", answercorrect);
                                quizitem.add(new QuizModel(qq, aa, bb, cc, dd, answercorrect));

                            }
                            ques.setText(quizitem.get(0).getQuestion());
                            a.setText(quizitem.get(0).getAnswera());
                            b.setText(quizitem.get(0).getAnswerb());
                            c.setText(quizitem.get(0).getAnswerc());
                            d.setText(quizitem.get(0).getAnswerd());

                            next.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                        if (a.isChecked()) {
                                            Log.i("nos;;pp", answercorrect);
                                            checked = a.getText().toString();
                                            if (quizitem.get(i-1).getcorrecr().equals(checked)) {
                                                score++;
                                                Toast.makeText(Quizzer.this, "score zaad" + score, Toast.LENGTH_SHORT).show();
                                            }


                                        } else if (b.isChecked()) {
                                            checked = b.getText().toString();
                                            if (quizitem.get(i-1).getcorrecr().equals(checked)) {
                                                score++;
                                                Toast.makeText(Quizzer.this, "score zaad" + score, Toast.LENGTH_SHORT).show();

                                            }

                                        } else if (c.isChecked()) {
                                            checked = c.getText().toString();
                                            if (quizitem.get(i-1).getcorrecr().equals(checked)) {
                                                score++;
                                                Log.i("lllll", score + "");
                                                Toast.makeText(Quizzer.this, "score zaad" + score, Toast.LENGTH_SHORT).show();

                                            }


                                        } else if (d.isChecked()) {
                                            checked = a.getText().toString();
                                            if (quizitem.get(i-1).getcorrecr().equals(checked)) {
                                                score++;
                                                Log.i("lllll", score + "");
                                                Toast.makeText(Quizzer.this, "score zaad" + score, Toast.LENGTH_SHORT).show();

                                            }


                                        }

                                    if (i < quizitem.size() ) {
                                        ques.setText(quizitem.get(i).getQuestion());
                                        a.setText(quizitem.get(i).getAnswera());
                                        b.setText(quizitem.get(i).getAnswerb());
                                        c.setText(quizitem.get(i).getAnswerc());
                                        d.setText(quizitem.get(i).getAnswerd());
                                        i++;
                                        if (i == quizitem.size()) {
                                            next.setText("Finish");
                                        }
                                    } else {
                                        AlertDialog alertDialog;
                                        alertDialog = new AlertDialog.Builder(Quizzer.this).create();
                                        alertDialog.setTitle("your grade in Quiz is ");
                                        alertDialog.setMessage(score + "");
                                        alertDialog.show();
                                    }
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
                map.put("id_assiment", assienmnt());


                return map;
            }
        };
        Singleton.getInstance(this).addRequestQue(stringRequest);


    }

    public String assienmnt() {
        String assienment = getIntent().getExtras().getString("assiment_id");
        return assienment;

    }

}