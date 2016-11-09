package freelancer.androidbuider;

import android.app.Application;

/**
 * Created by nqlong on 22-Sep-16.
 */
public class AndroidBuilderApplication extends Application {
    private static final String TAG = AndroidBuilderApplication.class.getSimpleName();
    public static AndroidBuilderApplication instance;

    public AndroidBuilderApplication() {
        super();
        if (instance == null) {
            instance = this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    /**
     * get instance singleton
     * @return
     */
    public AndroidBuilderApplication getInstance() {
        if (instance == null) {
            instance = new AndroidBuilderApplication();
        }
        return instance;
    }
}
