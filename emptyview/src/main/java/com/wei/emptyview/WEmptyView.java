package com.wei.emptyview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
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
    private boolean mIsShowIcon;
    private boolean mIsShowText;
    private boolean mIsShowButton;

    private final Context mContext;
    public ImageView mIcon;
    public Button mButton;
    public float mTextSize;
    public int mTextColor;
    private OnClickListener mListener;
    private boolean mClickEnable = true;
    private TextView mTextView;
    private String mDefEmptyText;
    private int mDefEmptyIcon;
    private ProgressBar mProgressBar;
    private String mButtonText;

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
            mButtonText = "点击刷新";
        }else {
            mButtonText = ta.getString(R.styleable.WEmptyView_btnString);
        }
        mIsShowIcon = ta.getBoolean(R.styleable.WEmptyView_isShowIcon ,true);
        mIsShowText = ta.getBoolean(R.styleable.WEmptyView_isShowText ,true);
        mIsShowButton = ta.getBoolean(R.styleable.WEmptyView_isShowBtn ,false);
        mDefEmptyIcon = ta.getResourceId(R.styleable.WEmptyView_iconDrawable , R.mipmap.pagefailed_bg);
        Log.d("WEmptyView" , "mDefEmptyText" + mDefEmptyText);
        Log.d("WEmptyView" , "mButtonText" + mButtonText);
        Log.d("WEmptyView" , "mDefEmptyIcon" + mDefEmptyIcon);
        ta.recycle();
        init();
    }

    private void init() {
        View view = View.inflate(mContext, R.layout.view_empty_layout, null);
        mIcon = view.findViewById(R.id.img_error_layout);
        mTextView = view.findViewById(R.id.tv_empty_text);
        mButton = view.findViewById(R.id.btn_empty_btn);
        mRootView = view.findViewById(R.id.rl_root);
        mProgressBar = view.findViewById(R.id.progressbar);
        setBackgroundColor(-1);
        mIcon.setBackgroundResource(mDefEmptyIcon);
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

    public void setShowIcon(boolean mShowIcon) {
        this.mIsShowIcon = mShowIcon;
        mIcon.setVisibility(mShowIcon ? VISIBLE : GONE);
    }

    public void setShowText(boolean showText) {
        this.mIsShowText = showText;
        mTextView.setVisibility(showText ? VISIBLE : GONE);
    }

    public void setShowButton(boolean showButton) {
        this.mIsShowButton = showButton;
        mButton.setVisibility(showButton ? VISIBLE : GONE);
    }

    public void setTextSize(float mTextSize) {
        this.mTextSize = mTextSize;
        mTextView.setTextSize(mTextSize);
    }

    public void setTextColor(int mTextColor ) {
        this.mTextColor = mTextColor;
        mTextView.setTextColor(mTextColor);
    }

    public void bindView(View view) {
        mBindView = view;
    }

    public void loading() {
        if (mBindView != null) {
            mBindView.setVisibility(View.INVISIBLE);
        }
        mProgressBar.setVisibility(View.VISIBLE);
        mIcon.setVisibility(View.GONE);
        mButton.setVisibility(View.GONE);
        mTextView.setVisibility(View.GONE);
        mClickEnable = false;
    }

    public void setBtnBackgroundResource(int resid){
        mButton.setBackgroundResource(resid);
    }

    public void setBtnBackg(Drawable background){
        mButton.setBackground(background);
    }

    public void success() {
        if (mBindView != null) {
            mBindView.setVisibility(View.VISIBLE);
        }
        mProgressBar.setVisibility(View.GONE);
        mIcon.setVisibility(View.GONE);
        mButton.setVisibility(View.GONE);
        mTextView.setVisibility(View.GONE);
        mClickEnable = false;
    }

    public void empty() {
        if (mBindView != null) {
            mBindView.setVisibility(View.GONE);
        }
        mTextView.setVisibility(mIsShowText ? View.VISIBLE : View.GONE);
        mIcon.setVisibility(mIsShowIcon ? View.VISIBLE : View.GONE);
        mButton.setVisibility(mIsShowButton ? View.VISIBLE : View.GONE);
        mButton.setText(mButtonText);
        mTextView.setText(mDefEmptyText);
        mIcon.setBackgroundResource(mDefEmptyIcon);
        mProgressBar.setVisibility(View.GONE);
        mClickEnable = true;
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
    public void setOnLayoutClickListener(OnClickListener listener) {
        this.mListener = listener;
    }
}
