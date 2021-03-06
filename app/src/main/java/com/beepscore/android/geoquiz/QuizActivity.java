package com.beepscore.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends Activity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final String IS_CHEATER = "is_cheater";

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mCheatButton;
    private ImageButton mPreviousButton;
    private ImageButton mNextButton;
    private TextView mQuestionTextView;
    private TextView mVersionTextView;

    // MVC model
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_oceans, true),
            new TrueFalse(R.string.question_mideast, false),
            new TrueFalse(R.string.question_africa, false),
            new TrueFalse(R.string.question_americas, true),
            new TrueFalse(R.string.question_asia, true),
    };

    private int mCurrentIndex = 0;
    private boolean mIsCheater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
        setContentView(R.layout.activity_quiz);

        restoreFromSavedState(savedInstanceState);

        mVersionTextView = (TextView) findViewById(R.id.version_text_view);
        mVersionTextView.setText("API level " + android.os.Build.VERSION.SDK_INT);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(nextListener);

        mTrueButton = (Button) findViewById(R.id.true_button);
        // implement button listener as an anonymous inner class
        // the class will be used here only, so don't need to name it
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuizActivity.this, CheatActivity.class);
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
                // attach extra to intent
                // in extra add key-value pair
                intent.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, answerIsTrue);
                // startActivity sends intent outside of app, to Android OS ActivityManager.
                //startActivity(intent);
                // Use startActivityForResult() to get information back from child activity.
                int requestCode = 0;
                startActivityForResult(intent, requestCode);
            }
        });

        mPreviousButton = (ImageButton) findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(previousListener);

        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(nextListener);

        updateQuestion();
    }

    private void restoreFromSavedState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            // if savedInstanceState doesn't have a value for KEY_INDEX, getInt returns second argument as default value
            // https://developer.android.com/training/basics/activity-lifecycle/recreating.html
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            mIsCheater = savedInstanceState.getBoolean(IS_CHEATER, false);
        }
    }

    // onSaveInstanceState() is called before onPause(), onStop(), onDestroy
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
        savedInstanceState.putBoolean(IS_CHEATER, mIsCheater);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    private View.OnClickListener previousListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // keep mCurrentIndex >= 0 and within array bounds
            // to move backwards, advance current index almost all the way through the length of the array
            // Probably there's a more elegant way to decrement this index!
            mCurrentIndex = (mCurrentIndex + (mQuestionBank.length - 1)) % mQuestionBank.length;
            mIsCheater = false;
            updateQuestion();
        }
    };

    // http://martin.cubeactive.com/android-onclicklitener-tutorial/
    private View.OnClickListener nextListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // can't just increment mCurrentIndex++
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
            mIsCheater = false;
            updateQuestion();
        }
    };

    private void updateQuestion() {
        // Log with exception argument shows a stack trace.
        // Usually this is used after catching a thrown exception.
        // For debugging purposes, create a new exception in order to see stack trace.
        // Log.d(TAG, "Updating question text for question #" + mCurrentIndex, new Exception());
        // question is a resource id, not a string
        int question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
        int messageResId = 0;

        if (mIsCheater) {
            messageResId = R.string.judgment_toast;
        } else {
            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.correct_toast;
            } else {
                messageResId = R.string.incorrect_toast;
            }
        }
        // 'this' refers to View.OnClickListener
        Toast toast = Toast.makeText(this, messageResId, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 100);
        toast.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    // Android OS ActivityManager calls this to pass result
    // from child activity back to parent activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        mIsCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
    }

}
