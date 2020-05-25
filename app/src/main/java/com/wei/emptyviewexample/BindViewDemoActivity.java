package com.wei.emptyviewexample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.wei.emptyview.WEmptyView;

public class BindViewDemoActivity extends AppCompatActivity {

    public static void startActivity(Context context){
    	Intent intent = new Intent(context, BindViewDemoActivity.class);
    	context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WEmptyView emptyView = findViewById(R.id.emptyView);
        Button reloadBtn = findViewById(R.id.btn_reload);
        View mainView = findViewById(R.id.rl_main);
        emptyView.bindView(mainView);
        emptyView.success();

        reloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emptyView.loading();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        emptyView.empty();
                    }
                },3000);
            }
        });


    }
}
