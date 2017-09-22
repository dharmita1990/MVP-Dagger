package com.dkb.mvpdagger.task;


import com.dkb.mvpdagger.dagger.ActivityScoped;
import com.dkb.mvpdagger.dagger.FragmentScoped;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by User on 18-09-2017.
 */
@Module
public abstract class TasksModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract TasksFragment tasksFragment();

    @ActivityScoped
    @Binds
    abstract TasksContract.Presenter taskPresenter(TasksPresenter presenter);
}
