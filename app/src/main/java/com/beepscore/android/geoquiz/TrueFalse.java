package com.beepscore.android.geoquiz;

/**
 * Created by stevebaker on 7/27/14.
 */
public class TrueFalse {
    /**
     * mQuestion is a resource id, so type is int not String
     * Returning id may reduce memory usage, especially if the resource is large.
     * Here it seems like it would be clearer to change the variable name (e.g. mQuestionID) or return a string!
     */
    private int mQuestion;

    private boolean mTrueQuestion;

    public TrueFalse(int question, boolean trueQuestion) {

        // Book follows Android naming convention for m and s prefix
        // http://source.android.com/source/code-style.html#follow-field-naming-conventions
        // Can set in Android Studio / Preferences / Code Style / Java / Code Generation
        // alternatively, could use "question" and this.question = question;
        mQuestion = question;
        mTrueQuestion = trueQuestion;
    }

    public int getQuestion() {
        return mQuestion;
    }

    public void setQuestion(int question) {
        mQuestion = question;
    }

    public boolean isTrueQuestion() {
        return mTrueQuestion;
    }

    public void setTrueQuestion(boolean trueQuestion) {
        mTrueQuestion = trueQuestion;
    }

}
