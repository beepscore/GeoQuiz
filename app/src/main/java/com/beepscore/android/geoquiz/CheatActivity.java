package com.beepscore.android.geoquiz;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by stevebaker on 7/30/14.
 */
public class CheatActivity extends Activity {

    public static final String EXTRA_ANSWER_IS_TRUE = "com.beepscore.android.geoquiz.answer_is_true";
    private boolean mAnswerIsTrue;

    private TextView mAnswerTextView;
    private Button mShowAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

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
            }
        });
    }

}
