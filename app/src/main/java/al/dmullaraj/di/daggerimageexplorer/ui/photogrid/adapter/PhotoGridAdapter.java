package al.dmullaraj.di.daggerimageexplorer.ui.photogrid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import al.dmullaraj.di.daggerimageexplorer.R;
import al.dmullaraj.di.daggerimageexplorer.domain.data.model.TvShow;
import al.dmullaraj.di.daggerimageexplorer.ui.photogrid.listener.OnGridImageViewClickListener;

/**
 * Created by denis.mullaraj on 19/01/2018.
 */

public class PhotoGridAdapter extends RecyclerView.Adapter<PhotoGridAdapter.PhotoViewHolder> {

    private Context mContext;
    private ArrayList<TvShow> mTvShowList;
    private OnGridImageViewClickListener mOnGridImageViewClickListener;

    public PhotoGridAdapter(Context context, ArrayList<TvShow> tvShowList, OnGridImageViewClickListener onGridImageViewClickListener) {
        mContext = context;
        mTvShowList = tvShowList;
        mOnGridImageViewClickListener = onGridImageViewClickListener;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo_gridview_item, parent, false);

        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        TvShow tvShow = mTvShowList.get(position);

        Glide.with(mContext).load(tvShow.getLowResImageUrl())
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mTvShowList.size();
    }

    class PhotoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;

        PhotoViewHolder(View view){
            super(view);

            imageView = view.findViewById(R.id.photo_griview_item);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnGridImageViewClickListener.onImageClicked(getLayoutPosition());
        }
    }

}