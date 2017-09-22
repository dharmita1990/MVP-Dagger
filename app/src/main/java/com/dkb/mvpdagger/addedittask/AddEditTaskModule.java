package com.dkb.mvpdagger.addedittask;

import android.support.annotation.Nullable;

import com.dkb.mvpdagger.dagger.ActivityScoped;
import com.dkb.mvpdagger.dagger.FragmentScoped;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by User on 19-09-2017.
 */
@Module
public abstract class AddEditTaskModule {
    @Provides
    @ActivityScoped
    @Nullable
    static String provideTaskId(AddEditTaskActivity activity) {
        return activity.getIntent().getStringExtra(AddEditTaskFragment.ARGUMENT_EDIT_TASK_ID);
    }

    @FragmentScoped
    @ContributesAndroidInjector
    abstract AddEditTaskFragment addEditTaskFragment();

    @ActivityScoped
    @Binds
    abstract AddEditTaskContract.Presenter taskPresenter(AddEditTaskPresenter presenter);

}
