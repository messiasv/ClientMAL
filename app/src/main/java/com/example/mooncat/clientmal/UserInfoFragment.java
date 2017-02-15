package com.example.mooncat.clientmal;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UserInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UserInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserInfoFragment extends Fragment {
    FragmentActivity faActivity;
    ViewGroup vG;

    private OnFragmentInteractionListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        faActivity = super.getActivity();
        String username = faActivity.getIntent().getStringExtra("username");

        // Inflate the layout for this fragment
        vG = (ViewGroup) inflater.inflate(R.layout.fragment_user_info, container, false);
        ((TextView)vG.findViewById(R.id.info_username)).setText(username);
        return vG;
    }

    public void setAnimeValues(String xmlAnime) {
        User animeUser;
        try {
            animeUser = ParserXML.parseUserAnimeInfo(xmlAnime);
            ((TextView)vG.findViewById(R.id.info_anime_days)).setText(animeUser.getDaysSpentWatchingAnime());
            ((TextView)vG.findViewById(R.id.info_anime_watching_value)).setText(animeUser.getWatchingAnime());
            ((TextView)vG.findViewById(R.id.info_anime_completed_value)).setText(animeUser.getCompletedAnime());
            ((TextView)vG.findViewById(R.id.info_anime_onhold_value)).setText(animeUser.getOnHoldAnime());
            ((TextView)vG.findViewById(R.id.info_anime_dropped_value)).setText(animeUser.getDroppedAnime());
            ((TextView)vG.findViewById(R.id.info_anime_plantowatch_value)).setText(animeUser.getPlanToWatchAnime());
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }
    }

    public void setMangaValues(String xmlManga) {
        User mangaUser;
        try {
            mangaUser = ParserXML.parseUserMangaInfo(xmlManga);
            ((TextView)vG.findViewById(R.id.info_manga_days)).setText(mangaUser.getDaysSpentWatchingManga());
            ((TextView)vG.findViewById(R.id.info_manga_reading_value)).setText(mangaUser.getReadingManga());
            ((TextView)vG.findViewById(R.id.info_manga_completed_value)).setText(mangaUser.getCompletedManga());
            ((TextView)vG.findViewById(R.id.info_manga_onhold_value)).setText(mangaUser.getOnHoldManga());
            ((TextView)vG.findViewById(R.id.info_manga_dropped_value)).setText(mangaUser.getDroppedManga());
            ((TextView)vG.findViewById(R.id.info_manga_plantoread_value)).setText(mangaUser.getPlanToReadManga());
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }
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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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
