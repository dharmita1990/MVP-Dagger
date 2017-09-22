package com.dkb.mvpdagger.dagger;


import com.dkb.mvpdagger.addedittask.AddEditTaskActivity;
import com.dkb.mvpdagger.addedittask.AddEditTaskModule;
import com.dkb.mvpdagger.task.TaskActivity;
import com.dkb.mvpdagger.task.TasksModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = TasksModule.class)
    abstract TaskActivity tasksActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = AddEditTaskModule.class)
    abstract AddEditTaskActivity addEditTaskActivity();


}
