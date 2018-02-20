package al.dmullaraj.di.daggerimageexplorer.application.network;

/**
 * Created by denis.mullaraj on 19/02/2018.
 */

public interface ClientApi {

    void retrievePhotoList(int index, ClientListener listener);

    void cancelRequestQueue();

}
