package com.wei.emptyview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by wei on 2019/2/12.
 */
public class WEmptyView extends FrameLayout {

    private View mBindView;
    private View mRootView;
    private boolean mIsShowIcon;    //是否显示图片
    private boolean mIsShowText;    //是否显示文字
    private boolean mIsShowButton;  //是否显示按钮
    private final Context mContext;
    private OnClickListener mListener;
    private boolean mClickEnable = true;

    private ProgressBar mProgressBar;
    private TextView mTextView;
    private int mDefEmptyIcon;
    public ImageView mImg;
    public Button mButton;

    private String mDefEmptyText;   //默认显示文字
    public float mTextSize;
    public int mTextColor;
    private String mButtonText;     //默认显示按钮文字
    private String mDefNoNetworkText;
    private String mDefNoNetworkBtnText;
    private int mDefNoNetworkImg;

    public WEmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.WEmptyView, 0, 0);
        if (null == ta.getString(R.styleable.WEmptyView_textString)){
            mDefEmptyText = "暂无数据";
        }else {
            mDefEmptyText = ta.getString(R.styleable.WEmptyView_textString);
        }
        if (null == ta.getString(R.styleable.WEmptyView_btnString)){
            mButtonText = "重新加载";
        }else {
            mButtonText = ta.getString(R.styleable.WEmptyView_btnString);
        }
        if (null == ta.getString(R.styleable.WEmptyView_defNoNetworkTextString)){
            mDefNoNetworkText = "网络出错了，请点击按钮重新加载";
        }else {
            mDefNoNetworkText = ta.getString(R.styleable.WEmptyView_defNoNetworkTextString);
        }
        if (null == ta.getString(R.styleable.WEmptyView_defNoNetworkBtnTextString)){
            mDefNoNetworkBtnText = "重新加载";
        }else {
            mDefNoNetworkBtnText = ta.getString(R.styleable.WEmptyView_defNoNetworkBtnTextString);
        }

        mIsShowIcon = ta.getBoolean(R.styleable.WEmptyView_isShowIcon ,true);
        mIsShowText = ta.getBoolean(R.styleable.WEmptyView_isShowText ,true);
        mIsShowButton = ta.getBoolean(R.styleable.WEmptyView_isShowBtn ,false);
        mDefEmptyIcon = ta.getResourceId(R.styleable.WEmptyView_iconDrawable , R.mipmap.pop_failure);
        mDefNoNetworkImg = ta.getResourceId(R.styleable.WEmptyView_defNoNetworkIcon , R.mipmap.no_wifi);
        ta.recycle();
        init();
    }

    private void init() {
        View view = View.inflate(mContext, R.layout.view_empty_layout, null);
        mImg = view.findViewById(R.id.img_error_layout);
        mTextView = view.findViewById(R.id.tv_empty_text);
        mButton = view.findViewById(R.id.btn_empty_btn);
        mRootView = view.findViewById(R.id.rl_root);
        mProgressBar = view.findViewById(R.id.progressbar);
        setBackgroundColor(-1);
        mImg.setImageResource(mDefEmptyIcon);
        mButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mClickEnable) {
                    if (mListener != null)
                        mListener.onClick(v);
                }
            }
        });
        addView(view);
    }

    public void setRootViewBackgroundColor(String colorString) {
        mRootView.setBackgroundColor(Color.parseColor(colorString));
    }

    public void setImgBackgroundResource(int backgroundRes){
        mImg.setBackgroundResource(backgroundRes);
    }

    public void setImageResource(int imageRes){
        mImg.setImageResource(imageRes);
    }

    public ImageView getImg(){
        return mImg;
    }

    /**
     * 设置文字内容
     * @param showText
     */
    public void setShowText(boolean showText) {
        this.mIsShowText = showText;
        mTextView.setVisibility(showText ? VISIBLE : GONE);
    }

    public void setTextSize(float mTextSize) {
        this.mTextSize = mTextSize;
        mTextView.setTextSize(mTextSize);
    }

    public void setTextColor(int mTextColor ) {
        this.mTextColor = mTextColor;
        mTextView.setTextColor(mTextColor);
    }

    public TextView getText(){
        return mTextView;
    }

    public void bindView(View view) {
        mBindView = view;
    }

    public void loading() {
        if (mBindView != null) {
            mBindView.setVisibility(View.INVISIBLE);
        }
        setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
        mImg.setVisibility(View.GONE);
        mButton.setVisibility(View.GONE);
        mTextView.setVisibility(View.GONE);
        mClickEnable = false;
    }

    /**
     *
     * @param showButton 是否显示按钮
     */
    public void setShowButton(boolean showButton) {
        this.mIsShowButton = showButton;
        mButton.setVisibility(showButton ? VISIBLE : GONE);
    }

    /**
     * 设置按钮背景
     * @param resid
     */
    public void setBtnBackgroundResource(int resid){
        mButton.setBackgroundResource(resid);
    }
    /**
     * 设置按钮背景
     * @param background
     */
    public void setBtnBackg(Drawable background){
        mButton.setBackground(background);
    }

    public Button getButton(){
        return mButton;
    }

    /**
     * 获取数据成功调用
     */
    public void success() {
        if (mBindView != null) {
            mBindView.setVisibility(View.VISIBLE);
        }
        mProgressBar.setVisibility(View.GONE);
        mImg.setVisibility(View.GONE);
        mButton.setVisibility(View.GONE);
        mTextView.setVisibility(View.GONE);
        setVisibility(View.GONE);
        mClickEnable = false;
    }

    /**
     * 获取数据失败调用
     */
    public void empty() {
        if (mBindView != null) {
            mBindView.setVisibility(View.GONE);
        }
        setVisibility(View.VISIBLE);
        if (NetworkUtil.isNetworkConnected(mContext)){
            mTextView.setVisibility(mIsShowText ? View.VISIBLE : View.GONE);
            mImg.setVisibility(mIsShowIcon ? View.VISIBLE : View.GONE);
            mButton.setVisibility(mIsShowButton ? View.VISIBLE : View.GONE);
            mButton.setText(mButtonText);
            mTextView.setText(mDefEmptyText);
            mImg.setBackgroundResource(mDefEmptyIcon);
            mProgressBar.setVisibility(View.GONE);
            mClickEnable = true;
        }else {
            mTextView.setVisibility(View.VISIBLE);
            mImg.setVisibility(View.VISIBLE);
            mButton.setVisibility(View.VISIBLE );
            mButton.setText(mDefNoNetworkBtnText);
            mTextView.setText(mDefNoNetworkText);
            mImg.setBackgroundResource(mDefNoNetworkImg);
            mProgressBar.setVisibility(View.GONE);
            mClickEnable = true;
        }
    }

    public void setEmptyViewVisibility(boolean isShowIcon , boolean isShowText , boolean isShowButton){
        mTextView.setVisibility(isShowText ? View.VISIBLE : View.GONE);
        mImg.setVisibility(isShowIcon ? View.VISIBLE : View.GONE);
        mButton.setVisibility(isShowButton ? View.VISIBLE : View.GONE);
    }

    /**
     * 设置无网络情况默认显示文字
     * @param text
     */
    public void setDefNoNetworkText(String text){
        mDefNoNetworkText = text;
    }

    /**
     * 设置无网络情况默认显示按钮文字
     * @param btnText
     */
    public void setDefNoNetworkBtnText(String btnText){
        mDefNoNetworkBtnText = btnText;
    }

    public void setDefNoNetworkImg(int resId){
        mDefNoNetworkImg = resId;
    }

    /**
     * 设置无数据的图片
     */
    public void setEmptyIconAndContent(int resId, String text) {
        mDefEmptyIcon = resId;
        mDefEmptyText = text;
    }

    public void setDefEmptyContent(int resId) {
        mDefEmptyIcon = resId;
    }

    public void setDefEmptyContent(String text) {
        mDefEmptyText = text;
    }

    /**
     * 点击按钮的事件
     *
     * @param listener
     */
    public void setOnBtnClickListener(OnClickListener listener) {
        this.mListener = listener;
    }

    /**
     *
     * @param colorRes  color资源文件
     */
    public void setProgressBarColor(int colorRes){
        mProgressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(colorRes), PorterDuff.Mode.SRC_IN);
    }

    public ProgressBar getProgressBar(){
        return mProgressBar;
    }
}
