package com.wei.emptyviewexample;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.wei.emptyview.WEmptyView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WEmptyView emptyView = findViewById(R.id.emptyView);
        TextView helloTv = findViewById(R.id.tv_hello);
        emptyView.bindView(helloTv);
        emptyView.loading();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                emptyView.empty();
            }
        },3000);
    }
}
