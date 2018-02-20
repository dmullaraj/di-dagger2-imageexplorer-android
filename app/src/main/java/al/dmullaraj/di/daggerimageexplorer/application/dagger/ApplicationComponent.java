package al.dmullaraj.di.daggerimageexplorer.application.dagger;

import al.dmullaraj.di.daggerimageexplorer.application.dagger.network.NetworkModule;
import al.dmullaraj.di.daggerimageexplorer.application.network.ClientApi;
import al.dmullaraj.di.daggerimageexplorer.application.network.volley.ImageExplorerVolleyApi;
import dagger.Component;

/**
 * Created by denis.mullaraj on 19/02/2018.
 */

@ApplicationScope
@Component(modules = {NetworkModule.class, ContextModule.class})
public interface ApplicationComponent {

    ClientApi getImageExplorerVolleyApi();

    void inject(ImageExplorerVolleyApi target);

}
