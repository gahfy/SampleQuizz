package net.gahfy.samplequizz;

import android.util.Log;

import java.util.List;
import java.util.Random;

public class QuizzPresenter {
    public static final Question[] QUESTIONS = new Question[]{
            new Question(
                    "En quelle année est sortie le premier téléphone sous Android ?",
                    "2008",
                    new String[]{
                            "2006",
                            "2007",
                            "2009"
                    }
            ),
            new Question(
                    "Quelle société développe le système d'exploitation Android ?",
                    "Google",
                    new String[]{
                            "Microsoft",
                            "IBM",
                            "Apple"
                    }
            )
    };

    private QuizzView mView;
    private Question mQuestion;

    QuizzPresenter(QuizzView view){
        mView = view;

        initQuestion();
        presentQuestion();
    }

    public QuizzView getView(){
        return mView;
    }

    public Question getQuestion(){
        return mQuestion;
    }

    private void initQuestion(){
        int randId = new Random().nextInt(QUESTIONS.length);
        mQuestion = QUESTIONS[randId];
    }

    private void presentQuestion(){
        List<String> answers = getQuestion().getAnswers();
        getView().showQuestion(
                getQuestion().getQuestion(),
                answers.get(0),
                answers.get(1),
                answers.get(2),
                answers.get(3)
        );
    }

    void onAnswerSelected(String answer){
        Log.d(QuizzPresenter.class.getSimpleName(), "User selected answer: ".concat(answer));
        getView().showMessage(getQuestion().goodAnswer(answer));
    }
}
