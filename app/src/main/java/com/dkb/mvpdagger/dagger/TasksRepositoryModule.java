package com.dkb.mvpdagger.dagger;


import com.dkb.mvpdagger.data.Local;
import com.dkb.mvpdagger.data.Remote;
import com.dkb.mvpdagger.data.source.TasksDataSource;
import com.dkb.mvpdagger.data.source.local.TasksLocalDataSource;
import com.dkb.mvpdagger.data.source.remote.TasksRemoteDataSource;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * Created by User on 21-09-2017.
 */
@Module
abstract public class TasksRepositoryModule {
    @Singleton
    @Binds
    @Local
    abstract TasksDataSource provideTasksLocalDataSource(TasksLocalDataSource dataSource);

    @Singleton
    @Binds
    @Remote
    abstract TasksDataSource provideTasksRemoteDataSource(TasksRemoteDataSource dataSource);
}
