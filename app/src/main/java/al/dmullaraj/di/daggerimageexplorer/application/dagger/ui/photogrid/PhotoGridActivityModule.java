package al.dmullaraj.di.daggerimageexplorer.application.dagger.ui.photogrid;

import al.dmullaraj.di.daggerimageexplorer.ui.activity.photogrid.PhotoGridActivity;
import dagger.Module;
import dagger.Provides;

/**
 * Created by denis.mullaraj on 19/02/2018.
 */

@Module
public class PhotoGridActivityModule {

    private final PhotoGridActivity photoGridActivity;

    public PhotoGridActivityModule(PhotoGridActivity photoGridActivity) {
        this.photoGridActivity = photoGridActivity;
    }

    @Provides
    @PhotoGridActivityScope
    public PhotoGridActivity photoGridActivity(){
        return photoGridActivity;
    }

}
