package al.dmullaraj.di.daggerimageexplorer.application.dagger.ui.photogrid;

import al.dmullaraj.di.daggerimageexplorer.application.dagger.ApplicationComponent;
import al.dmullaraj.di.daggerimageexplorer.ui.activity.photogrid.PhotoGridActivity;
import dagger.Component;

/**
 * Created by denis.mullaraj on 19/02/2018.
 */
@PhotoGridActivityScope
@Component(modules = PhotoGridActivityModule.class, dependencies = ApplicationComponent.class)
public interface PhotoGridActivityComponent {

    void injectPhotoGridActivity(PhotoGridActivity photoGridActivity);

}