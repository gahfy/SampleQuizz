package net.gahfy.samplequizz;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class QuizzApplicationAutomaticTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void check_answer_select() throws Exception {
        onView(withId(R.id.lbl_result)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));


        String questionText = getText(withId(R.id.lbl_question));
        String answer = getGoodAnswer(questionText);
        checkAnswer(R.id.radio_answer_1, answer);
        checkAnswer(R.id.radio_answer_2, answer);
        checkAnswer(R.id.radio_answer_3, answer);
        checkAnswer(R.id.radio_answer_4, answer);
    }

    private void checkAnswer(int answerId, String goodAnswer){
        String answerToSelect = getText(withId(answerId));
        onView(withId(answerId)).perform(click());
        if(answerToSelect.equals(goodAnswer)){
            onView(withId(R.id.lbl_result)).check(matches(withText("Félicitations\u202F!")));
        }
        else{
            onView(withId(R.id.lbl_result)).check(matches(withText("Incorrect\u202F! Essaye encore\u202F!")));
        }
    }

    private String getText(final Matcher<View> matcher) {
        final String[] stringHolder = { null };
        onView(matcher).perform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(TextView.class);
            }

            @Override
            public String getDescription() {
                return "getting text from a TextView";
            }

            @Override
            public void perform(UiController uiController, View view) {
                TextView tv = (TextView)view; //Save, because of check in getConstraints()
                stringHolder[0] = tv.getText().toString();
            }
        });
        return stringHolder[0];
    }

    /**
     * Returns the correct answer to a question.
     * @param questionString the text of the question
     * @return the correct answer to a question
     */
    private String getGoodAnswer(String questionString){
        for(Question question:QuizzPresenter.QUESTIONS){
            if(question.getQuestion().equals(questionString)){
                for(String answer: question.getAnswers()){
                    if(question.goodAnswer(answer))
                        return answer;
                }
            }
        }
        return null;
    }
}
