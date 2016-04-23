package com.shail.fragmentwithtopbar;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TopBar extends RelativeLayout implements View.OnClickListener {

    private static final String TAG = TopBar.class.getSimpleName();
    private MainActivity mActvityCallBack = null;
    private int mCurrentTitleType = 0;


    public TopBar(Context context) {
        super(context);
        mActvityCallBack = (MainActivity) context;
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mActvityCallBack = (MainActivity) context;
    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mActvityCallBack = (MainActivity) context;
    }

    private static class TopBarSavedState extends BaseSavedState {
        public static final Parcelable.Creator<TopBarSavedState> CREATOR = new Parcelable.Creator<TopBarSavedState>() {
            @Override
            public TopBarSavedState createFromParcel(final Parcel in) {
                return new TopBarSavedState(in);
            }

            @Override
            public TopBarSavedState[] newArray(final int size) {
                return new TopBarSavedState[size];
            }
        };
        private int mCurrentTitleType;

        TopBarSavedState(final Parcelable superState) {
            super(superState);
        }

        private TopBarSavedState(final Parcel in) {
            super(in);
            this.mCurrentTitleType = in.readInt();
        }

        @Override
        public void writeToParcel(@NonNull final Parcel out, final int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(this.mCurrentTitleType);
        }
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        final Parcelable superState = super.onSaveInstanceState();
        final TopBarSavedState state = new TopBarSavedState(superState);
        state.mCurrentTitleType = this.mCurrentTitleType;
        return state;
    }

    @Override
    protected void onRestoreInstanceState(final Parcelable state) {
        if (!(state instanceof TopBarSavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        final TopBarSavedState restoredState = (TopBarSavedState) state;
        super.onRestoreInstanceState(restoredState.getSuperState());
        // set previously selected state
        mCurrentTitleType = restoredState.mCurrentTitleType;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        initTopBar(mCurrentTitleType);
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        super.onTouchEvent(event);
        return true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mActvityCallBack = null;
    }

    private void initTopBar(int screenTitleType) {

        resetTitle(screenTitleType);

        Button accountSettingsButton = (Button) findViewById(R.id.account_settings);
        accountSettingsButton.setOnClickListener(this);

        Button homeScreenButton = (Button) findViewById(R.id.home_screen);
        homeScreenButton.setOnClickListener(this);

        Button communityButton = (Button) findViewById(R.id.community);
        communityButton.setOnClickListener(this);

    }

    private void resetTitle(int screenTitleType) {
        SCREEN screen = SCREEN.values()[screenTitleType];
        TextView screenTitleView = (TextView) findViewById(R.id.screen_title);
        screenTitleView.setText(screen.getScreenName());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.account_settings:
                Log.i(TAG, "launchAccountSettings");
                mCurrentTitleType = SCREEN.ACCOUNT_SETTINGS.ordinal();
                mActvityCallBack.launchAccountSettings();
                break;

            case R.id.home_screen:
                Log.i(TAG, "launchHomeScreen");
                mCurrentTitleType = SCREEN.HOME.ordinal();
                mActvityCallBack.launchHomeScreen();
                break;

            case R.id.community:
                Log.i(TAG, "launchCommunity");
                mCurrentTitleType = SCREEN.COMMUNITY.ordinal();
                mActvityCallBack.launchCommunity();
                break;
        }

        resetTitle(mCurrentTitleType);
    }
}
