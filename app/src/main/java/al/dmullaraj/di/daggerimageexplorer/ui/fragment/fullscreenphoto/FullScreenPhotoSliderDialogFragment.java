package al.dmullaraj.di.daggerimageexplorer.ui.fragment.fullscreenphoto;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import al.dmullaraj.di.daggerimageexplorer.R;
import al.dmullaraj.di.daggerimageexplorer.domain.data.model.TvShow;
import al.dmullaraj.di.daggerimageexplorer.ui.adapter.fullscreenphoto.FullScreenPhotoSliderPagerAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by denis.mullaraj on 19/01/2018.
 */

public class FullScreenPhotoSliderDialogFragment extends AppCompatDialogFragment {

    public static final String TAG = FullScreenPhotoSliderDialogFragment.class.getSimpleName();
    public static final String ARG_TV_SHOW_LIST = "tv_show_list";
    public static final String ARG_TV_SHOW_LIST_CURRENT_POSITION = "tv_show_list_current_position";

    private FullScreenPhotoSliderPagerAdapter mFullScreenPhotoSliderPagerAdapter;
    private ArrayList<TvShow> mTvShowList;
    private int mCurrentPosition;

    @BindView(R.id.fullscreen_slider_viewpager)
    ViewPager viewPager;

    public static FullScreenPhotoSliderDialogFragment newInstance(ArrayList<TvShow> imagesList, int position){
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(ARG_TV_SHOW_LIST, imagesList);
        bundle.putInt(ARG_TV_SHOW_LIST_CURRENT_POSITION, position);
        FullScreenPhotoSliderDialogFragment fragment = new FullScreenPhotoSliderDialogFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_fullscreen_slider, container, false);
        ButterKnife.bind(this, view);

        mTvShowList = getArguments().getParcelableArrayList(ARG_TV_SHOW_LIST);
        mCurrentPosition = getArguments().getInt(ARG_TV_SHOW_LIST_CURRENT_POSITION);

        mFullScreenPhotoSliderPagerAdapter = new FullScreenPhotoSliderPagerAdapter(getActivity(), mTvShowList);
        viewPager.setAdapter(mFullScreenPhotoSliderPagerAdapter);
        viewPager.setCurrentItem(mCurrentPosition, false);

        return view;
    }
}