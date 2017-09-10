package gekaixuan.bawei.com.shoppingcart;

import android.app.Application;

import org.xutils.x;

/**
 * Created by 葛凯旋 on 2017/9/9.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);

    }
}
