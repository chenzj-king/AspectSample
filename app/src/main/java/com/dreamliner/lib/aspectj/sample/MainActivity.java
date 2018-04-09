package com.dreamliner.lib.aspectj.sample;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.dreamliner.lib.aspectj.sample.annotation.SingleClick;
import com.dreamliner.lib.aspectj.sample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private long lastTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setAct(this);
    }

    @SingleClick
    public void onClick(View v) {
        Log.e("TAG", "click:\t" + (System.currentTimeMillis() - lastTime));
        lastTime = System.currentTimeMillis();
    }
}
