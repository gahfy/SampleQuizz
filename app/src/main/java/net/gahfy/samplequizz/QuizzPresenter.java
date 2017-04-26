package net.gahfy.samplequizz;

import java.util.List;

public class QuizzPresenter {
    private QuizzView mView;
    private Question mQuestion = new Question(
            "En quelle année est sortie le premier téléphone sous Android ?",
            "2008",
            new String[]{
                    "2006",
                    "2007",
                    "2009"
            }
            );

    QuizzPresenter(QuizzView view){
        mView = view;
        presentQuestion();
    }

    public QuizzView getView(){
        return mView;
    }

    public Question getQuestion(){
        return mQuestion;
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
        getView().showMessage(getQuestion().goodAnswer(answer));
    }
}
