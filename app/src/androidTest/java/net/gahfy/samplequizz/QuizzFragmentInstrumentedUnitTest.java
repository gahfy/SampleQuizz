package net.gahfy.samplequizz;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class QuizzFragmentInstrumentedUnitTest {
    private QuizzFragment mQuizzFragment;
    private Context mContext;

    @Before
    public void setup(){
        mContext = InstrumentationRegistry.getTargetContext();

        mQuizzFragment = mock(QuizzFragment.class);

        mQuizzFragment.mLblQuestion = new TextView(mContext);
        mQuizzFragment.mLblResult = new TextView(mContext);

        mQuizzFragment.mRadioAnswer1 = new RadioButton(mContext);
        mQuizzFragment.mRadioAnswer2 = new RadioButton(mContext);
        mQuizzFragment.mRadioAnswer3 = new RadioButton(mContext);
        mQuizzFragment.mRadioAnswer4 = new RadioButton(mContext);

        doCallRealMethod().when(mQuizzFragment).showQuestion(anyString(), anyString(), anyString(), anyString(), anyString());
        doCallRealMethod().when(mQuizzFragment).showMessage(anyBoolean());
        doCallRealMethod().when(mQuizzFragment).init();
        when(mQuizzFragment.getContext()).thenReturn(mContext);
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

    @Test
    public void fragment_showMessage() throws Exception {
        String goodMessage = mContext.getString(R.string.message_good_answer);
        String wrongMessage = mContext.getString(R.string.message_wrong_answer);

        int goodColor = ContextCompat.getColor(mContext, R.color.color_good_answer);
        int wrongColor = ContextCompat.getColor(mContext, R.color.color_wrong_answer);

        // Test showMessage(true)
        mQuizzFragment.showMessage(true);
        Assert.assertEquals("Test visibility after showMessage(true)", mQuizzFragment.mLblResult.getVisibility(), View.VISIBLE);
        Assert.assertEquals("Test content after showMessage(true)", mQuizzFragment.mLblResult.getText(), goodMessage);
        Assert.assertEquals("Test color after showMessage(true)", mQuizzFragment.mLblResult.getCurrentTextColor(), goodColor);

        // Test showMessage(false)
        mQuizzFragment.showMessage(false);
        Assert.assertEquals("Test visibility after showMessage(false)", mQuizzFragment.mLblResult.getVisibility(), View.VISIBLE);
        Assert.assertEquals("Test content after showMessage(false)", mQuizzFragment.mLblResult.getText(), wrongMessage);
        Assert.assertEquals("Test color after showMessage(false)", mQuizzFragment.mLblResult.getCurrentTextColor(), wrongColor);
    }
}
