package com.wei.emptyviewexample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.wei.emptyview.WEmptyView;

/**
 * Created by wei on 2019/12/26.
 */
public class DiffTypeDemoActivity extends AppCompatActivity {

    WEmptyView mEmptyView;

    public static void startActivity(Context context){
    	Intent intent = new Intent(context, DiffTypeDemoActivity.class);
    	context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diff_type);
        mEmptyView = findViewById(R.id.emptyView);
        mEmptyView.empty();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        //导入菜单布局
        getMenuInflater().inflate(R.menu.difftypemenu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        //创建菜单项的点击事件
        switch (item.getItemId()) {
            case R.id.mune_1:
                //只显示图片
                mEmptyView.setEmptyViewVisibility(true,false,false);
                break;
            case R.id.mune_2:
                //只显示文字
                mEmptyView.setEmptyViewVisibility(false,true,false);
                break;
            case R.id.mune_3:
                //只显示按钮
                mEmptyView.setEmptyViewVisibility(false,false,true);
                break;
            case R.id.mune_4:
                //显示图片文字
                mEmptyView.setEmptyViewVisibility(true,true,false);
                break;
            case R.id.mune_5:
                //显示图片文字按钮
                mEmptyView.setEmptyViewVisibility(true,true,true);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
