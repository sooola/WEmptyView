# WEmptyView
一个通用的错误布局，用于在数据显示前的loading界面，与没数据的错误界面。

# 导入  
```
compile 'com.wei:WEmptyView:1.0.0'
```

# 使用   
1.在布局外层包裹 WEmptyView
```
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <com.wei.emptyview.WEmptyView
        android:id="@+id/emptyView"
        app:btnString="hahahaha"
        app:textString="暂无数据"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <TextView
            android:id="@+id/tv_hello"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:textSize="36dp"
            android:text="Hello World!"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toTopOf="parent" />

    </com.wei.emptyview.WEmptyView>

</android.support.constraint.ConstraintLayout>
```

2.使用 bindView 绑定内层布局
```
        final WEmptyView emptyView = findViewById(R.id.emptyView);
        TextView helloTv = findViewById(R.id.tv_hello);
        emptyView.bindView(helloTv);
```
# 使用说明
```
emptyView.loading();    //加载数据前的loading状态
emptyView.success();    //数据加载成功
emptyView.empty();  //没有数据情况
```

# 自定义属性
| 一个普通标题 | 一个普通标题 | 一个普通标题 |
| ------ | ------ | ------ |
| 短文本 | 中等文本 | 稍微长一点的文本 |
| 稍微长一点的文本 | 短文本 | 中等文本 |


