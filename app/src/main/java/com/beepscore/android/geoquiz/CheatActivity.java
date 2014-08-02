package com.beepscore.android.geoquiz;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by stevebaker on 7/30/14.
 */
public class CheatActivity extends Activity {

    public static final String EXTRA_ANSWER_IS_TRUE = "com.beepscore.android.geoquiz.answer_is_true";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
    }

}
