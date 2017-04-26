package net.gahfy.samplequizz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Question {
    private String mQuestion;
    private String mGoodAnswer;
    private String[] mWrongAnswers;

    Question(String question, String goodAnswer, String[] wrongAnswers){
        mQuestion = question;
        mGoodAnswer = goodAnswer;
        mWrongAnswers = wrongAnswers;
    }

    String getQuestion() {
        return mQuestion;
    }

    boolean goodAnswer(String answer){
        return mGoodAnswer.equals(answer);
    }

    List<String> getAnswers(){
        List<String> allAnswers = new ArrayList<>();
        allAnswers.addAll(Arrays.asList(mWrongAnswers));
        allAnswers.add(mGoodAnswer);
        Collections.shuffle(allAnswers);
        return allAnswers;
    }
}
