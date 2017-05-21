package vna.example.com.education.Models;

/**
 * Created by Google       Company on 12/05/2017.
 */

public class QuizModel
{
    String question, answera, answerb,answerc, answerd , correceasn;

    public QuizModel( String question,String answera, String answerb, String answerc, String answerd,String correceasn) {
        this.answera = answera;
        this.answerb = answerb;
        this.answerc = answerc;
        this.answerd = answerd;
        this.question = question;
        this.correceasn = correceasn;

    }

    public String getAnswera() {
        return answera;
    }

    public String getAnswerc() {
        return answerc;
    }

    public String getAnswerd() {
        return answerd;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswerb() {
        return answerb;
    }
    public String getcorrecr() {
        return correceasn;
    }

}
