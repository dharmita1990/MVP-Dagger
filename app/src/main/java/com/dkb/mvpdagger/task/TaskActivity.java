package com.dkb.mvpdagger.task;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.dkb.mvpdagger.ActivityUtils;
import com.dkb.mvpdagger.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;

public class TaskActivity extends DaggerAppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @Inject
    Lazy<TasksFragment> taskFragmentProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        ButterKnife.bind(this);
        // Set up the toolbar.
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Task");

        TasksFragment tasksFragment =
                (TasksFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (tasksFragment == null) {
            // Get the fragment from dagger
            tasksFragment = taskFragmentProvider.get();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), tasksFragment, R.id.contentFrame);
        }


    }

}
