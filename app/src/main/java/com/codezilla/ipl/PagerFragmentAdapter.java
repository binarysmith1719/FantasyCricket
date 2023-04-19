package com.codezilla.ipl;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerFragmentAdapter extends FragmentPagerAdapter {
    public PagerFragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position==0)
            return new pointsFragment();
        else if(position==1)
            return new RankingFragment();
        else
            return new userjoinedFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0)
            return "POINTS";
        else if(position==1)
            return "RANKS";
        else
            return "USERS";
    }
}
