package al.dmullaraj.di.daggerimageexplorer.application.network.volley;

import android.content.Context;
import android.net.Uri;

import com.android.volley.RequestQueue;
import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import al.dmullaraj.di.daggerimageexplorer.BuildConfig;
import al.dmullaraj.di.daggerimageexplorer.application.ImageExplorerApplication;
import al.dmullaraj.di.daggerimageexplorer.application.network.ClientApi;
import al.dmullaraj.di.daggerimageexplorer.application.network.ClientListener;
import al.dmullaraj.di.daggerimageexplorer.application.network.volley.request.GsonRequest;
import al.dmullaraj.di.daggerimageexplorer.domain.data.model.PopularTvShowResponse;

/**
 * Created by denis.mullaraj on 19/02/2018.
 */

public class ImageExplorerVolleyApi implements ClientApi {

    @Inject
    RequestQueue mQueue;
    private final String BASE_AUTHORITY = "api.themoviedb.org";

    public ImageExplorerVolleyApi(Context context) {
        ((ImageExplorerApplication)context).getAppComponent().inject(this);
    }

    @Override
    public void retrievePhotoList(int index, final ClientListener listener) {
        final String popular_tv_show_path = "3/tv/popular";
        Response.Listener photosResponseListener = (Response.Listener<PopularTvShowResponse>) listener::onSuccess;

        Response.ErrorListener errorListener = listener::onFailure;

        Map<String, String> jsonParams = new HashMap<>();
        jsonParams.put("language", "en");
        jsonParams.put("page", String.valueOf(index));

        GsonRequest<PopularTvShowResponse> latestPhotoRequest = new GsonRequest<>(
                createGetWithParams(popular_tv_show_path, jsonParams), PopularTvShowResponse.class, null, photosResponseListener, errorListener
        );

        mQueue.add(latestPhotoRequest);
    }

    @Override
    public void cancelRequestQueue() {
        mQueue.cancelAll(request -> true);
    }


    private String createGetWithParams(String path, Map<String, String> params){
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority(BASE_AUTHORITY)
                .appendEncodedPath(path)
                .appendQueryParameter("api_key", BuildConfig.API_KEY);

        for(String key : params.keySet()){
            String value = params.get(key);
            if(value != null){
                builder.appendQueryParameter(key, value);
            }
        }

        return builder.build().toString();
    }

}
