package com.example.newsfeed;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {
    Context context;

    public FragmentAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new AllNews();
            case 1:
                return new Policy();
            case 2:
                return new Sport();
            case 3:
                return new Technology();
            case 4:
                return new Health();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return context.getString(R.string.all_news);
            case 1: return context.getString(R.string.policy);
            case 2: return context.getString(R.string.sport);
            case 3: return context.getString(R.string.technology);
            case 4: return context.getString(R.string.health);
        }
        return null;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }
}
