package net.gahfy.samplequizz;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class QuizzPresenterUnitTest {
    private int mShowMessageCount;
    private boolean mLastShowMessageArgument;

    @Before
    public void setUp(){
        mShowMessageCount = 0;
        mLastShowMessageArgument = false;
    }

    @Test
    public void presenter_callView() throws Exception {
        QuizzView quizzView = new QuizzView() {
            @Override
            public void showQuestion(String question, String answer1, String answer2, String answer3, String answer4) {

            }

            @Override
            public void showMessage(boolean goodAnswer) {
                mShowMessageCount++;
                mLastShowMessageArgument = goodAnswer;
            }
        };

        // Mock of quizzPresenter
        QuizzPresenter quizzPresenter = mock(QuizzPresenter.class);
        // We set that getView() method returns the implemented QuizzView
        when(quizzPresenter.getView()).thenReturn(quizzView);
        // We set that getQuestion() method returns a specific question
        when(quizzPresenter.getQuestion()).thenReturn(new Question("Test question", "1", new String[]{"2", "3", "4"}));
        // We set that the behaviour of onAnswerSelected() remains as the original
        doCallRealMethod().when(quizzPresenter).onAnswerSelected(anyString());

        quizzPresenter.onAnswerSelected("1");
        Assert.assertEquals("Checking that showMessage has been called", mShowMessageCount, 1);
        Assert.assertTrue("Checking that showMessage has been called with true", mLastShowMessageArgument);
        quizzPresenter.onAnswerSelected("2");
        Assert.assertEquals("Checking that showMessage has been called", mShowMessageCount, 2);
        Assert.assertFalse("Checking that showMessage has been called with false", mLastShowMessageArgument);
    }
}