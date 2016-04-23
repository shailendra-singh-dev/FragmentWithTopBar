package com.shail.fragmentwithtopbar;


public enum SCREEN {
    HOME("Home Screen"), ACCOUNT_SETTINGS("Account Creation"),COMMUNITY("Community");


    private String mScreenName;

    SCREEN(String name) {
        mScreenName = name;
    }

    public String getScreenName() {
        return mScreenName;
    }
}
