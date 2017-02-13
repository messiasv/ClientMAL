package com.example.mooncat.clientmal;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class ViewPagerFragment extends Fragment {
    private static final String TAG = "ViewPagerFragment";
    MyPagerAdapter adapterViewPager;

    FragmentActivity faActivity;
    LinearLayout linearLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        faActivity = super.getActivity();
        linearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_view_pager, container, false);
        String mUsername = faActivity.getIntent().getStringExtra("username");
        ViewPager viewPager = (ViewPager) linearLayout.findViewById(R.id.viewPager);
        adapterViewPager = new MyPagerAdapter(faActivity.getSupportFragmentManager(), mUsername, faActivity.getApplicationContext());
        viewPager.setAdapter(adapterViewPager);
        Log.i(TAG, mUsername);
        return linearLayout;
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static final String TAG = "MyPagerAdapter";
        private static int NUM_ITEMS = 2;
        private final String mUsername;
        private final Context mContext;

        MyPagerAdapter(FragmentManager fragmentManager, String username, Context context) {
            super(fragmentManager);
            this.mUsername = username;
            this.mContext = context;
            Log.wtf(TAG, (username != null) ? username : "username NULL !!!!!");
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
//            Log.e(TAG, mUsername);
            switch (position) {
                case 0: // Fragment # 0 - This will show AnimeListFragment
                    return AnimeListFragment.newInstance(mUsername);

                case 1: // Fragment # 1 - This will show MangaListFragment
                    return MangaListFragment.newInstance(mUsername);
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return mContext.getString(R.string.anime);
                case 1:
                    return mContext.getString(R.string.manga);
                default:
                    return "";
            }
        }

        /*// Attach the page change listener inside the activity
        viewPager.addOnPageChangeListener(new

        OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected ( int position){
                Toast.makeText(HomeActivity.this,
                        "Selected page position: " + position, Toast.LENGTH_SHORT).show();
            }

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled ( int position, float positionOffset,
            int positionOffsetPixels){
                // Code goes here
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged ( int state){
                // Code goes here
            }
        }

        );*/
    }
}
