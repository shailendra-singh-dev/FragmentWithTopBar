package com.shail.fragmentwithtopbar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

public class MainActivity extends FragmentActivity {

    private static final String  TAG = MainActivity.class.getName();
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragmentManager = getSupportFragmentManager();
    }

    @Override
    protected void onStart() {
        super.onStart();
        launchHomeScreen();
    }

    private boolean isScreenExists(SCREEN screen) {
        return mFragmentManager.findFragmentByTag(screen.getScreenName()) != null;
    }

    public void launchAccountSettings() {
        AccountCreation accountCreation = new AccountCreation();
        Log.i(TAG,"launchAccountSettings");
        handleMainScreen(accountCreation, R.id.main_panel, SCREEN.ACCOUNT_SETTINGS);
    }

    public void launchHomeScreen() {
        HomeScreen homeScreen = new HomeScreen();
        Log.i(TAG,"launchHomeScreen");
        handleMainScreen(homeScreen, R.id.main_panel, SCREEN.HOME);
    }

    public void launchCommunity() {
        Community community = new Community();
        Log.i(TAG,"launchCommunity");
        handleMainScreen(community, R.id.main_panel, SCREEN.COMMUNITY);
    }

    public void handleMainScreen(Fragment fragment, int viewID, SCREEN screen) {
        boolean isExists = isScreenExists(screen);
        if (!isFinishing()) {
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.replace(viewID, fragment, screen.getScreenName()).addToBackStack(screen.getScreenName()).commit();
            mFragmentManager.executePendingTransactions();
        }
    }
}
