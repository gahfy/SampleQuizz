package net.gahfy.samplequizz;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.TextView;

public class QuizzActivity extends AppCompatActivity implements QuizzView{
    TextView mLblQuestion;
    TextView mLblResult;
    RadioButton mRadioAnswer1;
    RadioButton mRadioAnswer2;
    RadioButton mRadioAnswer3;
    RadioButton mRadioAnswer4;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quizz);

        mLblQuestion = (TextView) findViewById(R.id.lbl_question);
        mLblResult = (TextView) findViewById(R.id.lbl_result);
        mRadioAnswer1 = (RadioButton) findViewById(R.id.radio_answer_1);
        mRadioAnswer2 = (RadioButton) findViewById(R.id.radio_answer_2);
        mRadioAnswer3 = (RadioButton) findViewById(R.id.radio_answer_3);
        mRadioAnswer4 = (RadioButton) findViewById(R.id.radio_answer_4);

        final QuizzPresenter quizzPresenter = new QuizzPresenter(this);

        OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    quizzPresenter.onAnswerSelected((String) buttonView.getTag());
            }
        };
        mRadioAnswer1.setOnCheckedChangeListener(onCheckedChangeListener);
        mRadioAnswer2.setOnCheckedChangeListener(onCheckedChangeListener);
        mRadioAnswer3.setOnCheckedChangeListener(onCheckedChangeListener);
        mRadioAnswer4.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    @Override
    public void showQuestion(String question, String answer1, String answer2, String answer3, String answer4) {
        mLblQuestion.setText(question);
        mRadioAnswer1.setText(answer1);
        mRadioAnswer1.setTag(answer1);

        mRadioAnswer2.setText(answer2);
        mRadioAnswer2.setTag(answer2);

        mRadioAnswer3.setText(answer3);
        mRadioAnswer3.setTag(answer3);

        mRadioAnswer4.setText(answer4);
        mRadioAnswer4.setTag(answer4);
    }

    @Override
    public void showMessage(boolean goodAnswer){
        mLblResult.setText(goodAnswer ? R.string.message_good_answer : R.string.message_wrong_answer);
        mLblResult.setTextColor(ContextCompat.getColor(this, goodAnswer ? R.color.color_good_answer : R.color.color_wrong_answer));
        mLblResult.setVisibility(View.VISIBLE);
    }
}
