package com.wei.emptyviewexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by wei on 2019/12/24.
 */
public class IndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        findViewById(R.id.tv_list_demo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListDemoActivity.startActivity(view.getContext());
            }
        });

        findViewById(R.id.tv_diff_type).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiffTypeDemoActivity.startActivity(view.getContext());
            }
        });

        findViewById(R.id.tv_bindview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BindViewDemoActivity.startActivity(view.getContext());
            }
        });
    }
}
