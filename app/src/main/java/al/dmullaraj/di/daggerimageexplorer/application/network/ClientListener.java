package al.dmullaraj.di.daggerimageexplorer.application.network;

/**
 * Created by denis.mullaraj on 21/01/2018.
 */

public interface ClientListener<T, E extends Exception> {

    void onSuccess(T r);
    void onFailure(E t);

}