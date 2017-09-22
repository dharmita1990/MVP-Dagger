package com.dkb.mvpdagger;


import com.dkb.mvpdagger.dagger.AppComponent;
import com.dkb.mvpdagger.dagger.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Created by User on 21-09-2017.
 */

public class MVPBasicApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }


}
