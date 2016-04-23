package com.shail.fragmentwithtopbar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeScreen extends Fragment {

    public HomeScreen() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_screen, container, false);
        RelativeLayout homeScreenView = (RelativeLayout)view;
        return view;
    }
}
