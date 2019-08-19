# WEmptyView
一个通用的错误布局，用于在数据显示前的loading界面，与没数据的错误界面，免除每次重复写布局文件。

# 导入  
1.在根目录的build.gradle 
```
allprojects {
		repositories {
			...
			maven { url 'https://www.jitpack.io' }
		}
	}
```
2.在项目build.gradle 
```
compile 'com.github.sooola:WEmptyView:1.1'
```

# 使用   
1.在布局外层包裹 WEmptyView
```
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
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

</RelativeLayout>
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
emptyView.empty();      //没有数据情况

例子
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
```

# 自定义属性
| 属性名称 | 作用 | 
| ------ | ------ |
| app:btnString | 按钮文字内容 | 
| app:textString | 文本文字内容 | 
| app:iconDrawable | 图片配置 | 
| app:isShowBtn | 是否显示按钮 | 
| app:isShowIcon | 是否显示图片 | 
| app:isShowText | 是否显示文本 | 

# 截图  
![image](https://github.com/sooola/WEmptyView/blob/master/screenshots/loading.png)

![image](https://github.com/sooola/WEmptyView/blob/master/screenshots/empty.png)




