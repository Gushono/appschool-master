package com.example.aluno.tccappschoolll;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class ForumFragment extends Fragment {

    TabLayout mTabLayout;
    ViewPager mViewPager;
    View view;


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_forum, container, false);



        mTabLayout = (TabLayout) view.findViewById(R.id.tabLayoutForum);
        mViewPager = (ViewPager) view.findViewById(R.id.viewPagerForum);




        mViewPager.setAdapter(new ForumAdapter(getFragmentManager(), getResources().getStringArray(R.array.titulos)));
        mTabLayout.setupWithViewPager(mViewPager);







        return view;


    }





}

