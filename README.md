# WEmptyView
快速集成Android空白页面占位图

![image](https://github.com/sooola/WEmptyView/blob/master/screenshots/GIF.gif)

### EmptyView 提供的元素  

每项可单独配置，是否显示  
![image](https://github.com/sooola/WEmptyView/blob/master/screenshots/25.png)
![image](https://github.com/sooola/WEmptyView/blob/master/screenshots/24.png)

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
compile 'com.github.sooola:WEmptyView:1.2'
```

# 使用   
可以使用以下任意一种方式，bindView方式适合单一元素布局，如只有RecycleView情况
1.在布局外层加入 WEmptyView
```
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <RelativeLayout
        android:id="@+id/rl_main"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <TextView
            android:id="@+id/tv_hello"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:text="Hello World!"
            android:textSize="36dp" />

        <Button
            android:id="@+id/btn_reload"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_centerInParent="true"
            android:text="重新加载" />
    </RelativeLayout>
    
        <com.wei.emptyview.WEmptyView
        android:id="@+id/emptyView"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

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
| 属性名称         | 作用         |
| ---------------- | ------------ |
| app:btnString    | 按钮文字内容 |
| app:textString   | 文本文字内容 |
| app:iconDrawable | 默认图片配置     |
| app:isShowBtn    | 是否显示按钮 |
| app:isShowIcon   | 是否显示图片 |
| app:isShowText   | 是否显示文本 |
| app:defNoNetworkIcon   | 无网络状态默认图片 |
| app:defNoNetworkTextString   | 无网络状态默认文字 |
| app:defNoNetworkBtnTextString   | 无网络状态默认按钮文字 |

