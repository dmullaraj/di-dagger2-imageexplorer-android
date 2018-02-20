package al.dmullaraj.di.daggerimageexplorer.domain.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by denis.mullaraj on 19/01/2018.
 */

public class PopularTvShowResponse implements Parcelable {

    private int page;

    private int total_results;

    private int total_pages;

    @SerializedName("results")
    private ArrayList<TvShow> tvShowList;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public ArrayList<TvShow> getTvShowList() {
        return tvShowList;
    }

    public void setTvShowList(ArrayList<TvShow> tvShowList) {
        this.tvShowList = tvShowList;
    }

    public PopularTvShowResponse() {
    }

    protected PopularTvShowResponse(Parcel in) {
        page = in.readInt();
        total_results = in.readInt();
        total_pages = in.readInt();
        if (in.readByte() == 0x01) {
            tvShowList = new ArrayList<>();
            in.readList(tvShowList, TvShow.class.getClassLoader());
        } else {
            tvShowList = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(page);
        dest.writeInt(total_results);
        dest.writeInt(total_pages);
        if (tvShowList == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(tvShowList);
        }
    }

    @SuppressWarnings("unused")
    public static final Creator<PopularTvShowResponse> CREATOR = new Creator<PopularTvShowResponse>() {
        @Override
        public PopularTvShowResponse createFromParcel(Parcel in) {
            return new PopularTvShowResponse(in);
        }

        @Override
        public PopularTvShowResponse[] newArray(int size) {
            return new PopularTvShowResponse[size];
        }
    };
}