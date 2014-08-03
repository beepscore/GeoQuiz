package com.beepscore.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by stevebaker on 7/30/14.
 */
public class CheatActivity extends Activity {

    public static final String EXTRA_ANSWER_IS_TRUE = "com.beepscore.android.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "com.beepscore.android.geoquiz.answer_shown";
    private boolean mAnswerIsTrue;

    private TextView mAnswerTextView;
    private Button mShowAnswer;
    private boolean mAnswerShown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        restoreFromSavedState(savedInstanceState);
        setAnswerShownResult(mAnswerShown);

        // use an Intent and extra to pass information into CheatActivity
        // getIntent() returns intent passed in from Android OS ActivityManager
        // intent may contain an "extra" with key-value pairs
        // getBooleanExtra() returns extra value for key, else second argument as default value.
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextView = (TextView) findViewById(R.id.answerTextView);

        mShowAnswer = (Button)findViewById(R.id.showAnswerButton);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                mAnswerShown = true;
                setAnswerShownResult(mAnswerShown);
            }
        });
    }

    private void restoreFromSavedState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mAnswerShown = savedInstanceState.getBoolean(EXTRA_ANSWER_SHOWN, false);
        }
    }

    // onSaveInstanceState() is called before onPause(), onStop(), onDestroy()
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(EXTRA_ANSWER_SHOWN, mAnswerShown);
    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        // use an Intent and extra to pass information out of CheatActivity
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        // call setResult() on Android OS ActivityManager
        setResult(RESULT_OK, data);
    }

}
