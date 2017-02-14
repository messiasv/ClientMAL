package com.example.mooncat.clientmal;

import android.content.Context;
import android.net.Uri;
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

public class ViewPagerFragment extends Fragment {
    private static final String TAG = "ViewPagerFragment";
    MyPagerAdapter adapterViewPager;

    FragmentActivity faActivity;
    ViewGroup viewGroup;

    private UserInfoFragment.OnFragmentInteractionListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        faActivity = super.getActivity();
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_view_pager, container, false);
        String username = faActivity.getIntent().getStringExtra("username");
        ViewPager viewPager = (ViewPager) viewGroup.findViewById(R.id.viewPager);
        adapterViewPager = new MyPagerAdapter(faActivity.getSupportFragmentManager(), username, faActivity.getApplicationContext());
        viewPager.setAdapter(adapterViewPager);
        Log.i(TAG, username);
        return viewGroup;
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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof UserInfoFragment.OnFragmentInteractionListener) {
            mListener = (UserInfoFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}