package al.dmullaraj.di.daggerimageexplorer.application.dagger.network;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import al.dmullaraj.di.daggerimageexplorer.application.dagger.ApplicationContext;
import al.dmullaraj.di.daggerimageexplorer.application.dagger.ApplicationScope;
import al.dmullaraj.di.daggerimageexplorer.application.dagger.ContextModule;
import al.dmullaraj.di.daggerimageexplorer.application.network.ClientApi;
import al.dmullaraj.di.daggerimageexplorer.application.network.volley.ImageExplorerVolleyApi;
import dagger.Module;
import dagger.Provides;

/**
 * Created by denis.mullaraj on 19/02/2018.
 */

@Module(includes = ContextModule.class)
public class NetworkModule {

    @ApplicationScope
    @Provides
    public RequestQueue requestQueue(@ApplicationContext Context context){
        return Volley.newRequestQueue(context);
    }

    @ApplicationScope
    @Provides
    public ClientApi clientApi(@ApplicationContext Context context){
        return new ImageExplorerVolleyApi(context);
    }


}
