package net.gahfy.samplequizz;

import java.util.List;
import java.util.Random;

public class QuizzPresenter {
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
        Question[] questions = new Question[]{
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
        int randId = new Random().nextInt(questions.length);
        mQuestion = questions[randId];
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
