package al.dmullaraj.di.daggerimageexplorer.ui.activity.photogrid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.android.volley.VolleyError;

import java.util.ArrayList;

import javax.inject.Inject;

import al.dmullaraj.di.daggerimageexplorer.R;
import al.dmullaraj.di.daggerimageexplorer.application.ImageExplorerApplication;
import al.dmullaraj.di.daggerimageexplorer.application.dagger.ui.photogrid.DaggerPhotoGridActivityComponent;
import al.dmullaraj.di.daggerimageexplorer.application.dagger.ui.photogrid.PhotoGridActivityComponent;
import al.dmullaraj.di.daggerimageexplorer.application.network.ClientApi;
import al.dmullaraj.di.daggerimageexplorer.application.network.ClientListener;
import al.dmullaraj.di.daggerimageexplorer.domain.data.model.PopularTvShowResponse;
import al.dmullaraj.di.daggerimageexplorer.domain.data.model.TvShow;
import al.dmullaraj.di.daggerimageexplorer.domain.listener.photogrid.EndlessRecyclerViewScrollListener;
import al.dmullaraj.di.daggerimageexplorer.domain.listener.photogrid.OnGridImageViewClickListener;
import al.dmullaraj.di.daggerimageexplorer.ui.adapter.photogrid.PhotoGridAdapter;
import al.dmullaraj.di.daggerimageexplorer.ui.fragment.fullscreenphoto.FullScreenPhotoSliderDialogFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by denis.mullaraj on 19/01/2018.
 */

public class PhotoGridActivity extends AppCompatActivity implements OnGridImageViewClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.photo_grid_recycler_view)
    RecyclerView photoGridRecyclerView;

    @Inject
    ClientApi restApi;

    private final static int STARTING_PAGE = 1;
    private PhotoGridAdapter mPhotoGridAdapter;
    private ArrayList<TvShow> mTvShowList;
    private int maxNrOfPages = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_grid);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);


        PhotoGridActivityComponent component = DaggerPhotoGridActivityComponent.builder()
                .applicationComponent(ImageExplorerApplication.get(this).getAppComponent())
                .build();

        component.injectPhotoGridActivity(this);

        mTvShowList = new ArrayList<>();
        mPhotoGridAdapter = new PhotoGridAdapter(this, mTvShowList, this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        photoGridRecyclerView.setLayoutManager(gridLayoutManager);
        photoGridRecyclerView.setItemAnimator(new DefaultItemAnimator());
        photoGridRecyclerView.setAdapter(mPhotoGridAdapter);
        photoGridRecyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                // totalItemsCount here only for debugging and as a help for interviewer to verify the integrity of the logic
                Log.d("Endless", "Page: " + page + " Total: " + totalItemsCount);
                if (page > 0 && page <= maxNrOfPages) {
                    getLatestImages(page);
                }
            }
        });

        getLatestImages(STARTING_PAGE);
    }

    private void getLatestImages(int index) {

        restApi.retrievePhotoList(
                        index,
                        new ClientListener<PopularTvShowResponse, VolleyError>() {
                            @Override
                            public void onSuccess(PopularTvShowResponse response) {
                                Log.d("Endless", "Page loaded from api: " + response.getPage());
                                Log.d("Endless", "Max nr of pages: " + response.getTotal_pages());
                                maxNrOfPages = response.getTotal_pages();
                                mTvShowList.addAll(response.getTvShowList());
                                mPhotoGridAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(VolleyError error) {
                                Log.d("ErrorLog", error.getMessage());
                            }
                        });
    }

    @Override
    public void onImageClicked(int position) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        FullScreenPhotoSliderDialogFragment fragment = FullScreenPhotoSliderDialogFragment.newInstance(
                mTvShowList,
                position
        );
        fragment.show(ft, FullScreenPhotoSliderDialogFragment.TAG);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        restApi.cancelRequestQueue();
    }
}