package com.bignerdranch.android.geoquiz;

/**
 * Created by stevebaker on 7/27/14.
 */
public class TrueFalse {

    // mQuestion is a resource ID, so type is int not String
    private int mQuestion;

    private boolean mTrueQuestion;

    public TrueFalse(int question, boolean trueQuestion) {

        // m prefix is book's naming convention
        // alternatively, could use "question" and this.question = question;
        mQuestion = question;
        mTrueQuestion = trueQuestion;
    }
}
