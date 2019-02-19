package com.example.aluno.tccappschoolll;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ForumAdapter extends FragmentPagerAdapter
{

    private String[] mTabPub;

    public ForumAdapter(FragmentManager fm, String[] mTabPub) {

        super(fm);
        this.mTabPub = mTabPub;
    }

    @Override
    public Fragment getItem(int position)
    {

        switch (position){
            case 0:
                return new ForumPosts();

            case 1:
                return new PublicarFragment();

            default:
                return new ForumPosts();

        }

    }

    @Override
    public int getCount()
    {

        return this.mTabPub.length;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return this.mTabPub[position];
    }
}
