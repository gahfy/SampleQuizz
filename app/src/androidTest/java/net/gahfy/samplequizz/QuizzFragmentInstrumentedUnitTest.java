package net.gahfy.samplequizz;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.widget.RadioButton;
import android.widget.TextView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class QuizzFragmentInstrumentedUnitTest {
    private QuizzFragment mQuizzFragment;

    @Before
    public void setup(){
        Context context = InstrumentationRegistry.getTargetContext();

        mQuizzFragment = new QuizzFragment();
        mQuizzFragment.mLblQuestion = new TextView(context);
        mQuizzFragment.mLblResult = new TextView(context);

        mQuizzFragment.mRadioAnswer1 = new RadioButton(context);
        mQuizzFragment.mRadioAnswer2 = new RadioButton(context);
        mQuizzFragment.mRadioAnswer3 = new RadioButton(context);
        mQuizzFragment.mRadioAnswer4 = new RadioButton(context);
    }

    @Test
    public void fragment_showQuestion() throws Exception {
        mQuizzFragment.showQuestion("Test Question", "Answer1", "Answer2", "Answer3", "Answer4");

        Assert.assertEquals("Check content of question TextView", mQuizzFragment.mLblQuestion.getText(), "Test Question");

        Assert.assertEquals("Check content of first answer TextView", mQuizzFragment.mRadioAnswer1.getText(), "Answer1");
        Assert.assertEquals("Check content of second answer TextView", mQuizzFragment.mRadioAnswer2.getText(), "Answer2");
        Assert.assertEquals("Check content of third answer TextView", mQuizzFragment.mRadioAnswer3.getText(), "Answer3");
        Assert.assertEquals("Check content of fourth answer TextView", mQuizzFragment.mRadioAnswer4.getText(), "Answer4");

        Assert.assertEquals("Check tag of first answer TextView", mQuizzFragment.mRadioAnswer1.getTag(), "Answer1");
        Assert.assertEquals("Check tag of second answer TextView", mQuizzFragment.mRadioAnswer2.getTag(), "Answer2");
        Assert.assertEquals("Check tag of third answer TextView", mQuizzFragment.mRadioAnswer3.getTag(), "Answer3");
        Assert.assertEquals("Check tag of fourth answer TextView", mQuizzFragment.mRadioAnswer4.getTag(), "Answer4");
    }
}
