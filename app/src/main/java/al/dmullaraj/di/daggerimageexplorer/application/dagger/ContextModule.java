package al.dmullaraj.di.daggerimageexplorer.application.dagger;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by denis.mullaraj on 19/02/2018.
 */

@Module
public class ContextModule {

    private final Context context;

    public ContextModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @Provides
    @ApplicationScope
    @ApplicationContext
    public Context context() {
        return context;
    }
}
