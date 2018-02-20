package al.dmullaraj.di.daggerimageexplorer.application;

import android.app.Activity;
import android.app.Application;

import al.dmullaraj.di.daggerimageexplorer.application.dagger.ApplicationComponent;
import al.dmullaraj.di.daggerimageexplorer.application.dagger.ContextModule;
import al.dmullaraj.di.daggerimageexplorer.application.dagger.DaggerApplicationComponent;

/**
 * Created by denis.mullaraj on 19/02/2018.
 */

public class ImageExplorerApplication extends Application {

    protected ApplicationComponent appComponent;

    public ApplicationComponent getAppComponent() {
        return appComponent;
    }

    public static ImageExplorerApplication get(Activity activity) {
        return (ImageExplorerApplication) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }
}

