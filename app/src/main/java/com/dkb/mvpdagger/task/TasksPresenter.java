package com.dkb.mvpdagger.task;

import android.app.Activity;

import com.dkb.mvpdagger.addedittask.AddEditTaskActivity;
import com.dkb.mvpdagger.data.Task;
import com.dkb.mvpdagger.data.TasksRepository;
import com.dkb.mvpdagger.data.source.TasksDataSource;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Inject;

/**
 * Created by User on 06-09-2017.
 */

public class TasksPresenter implements TasksContract.Presenter {
    @Nullable
    private TasksContract.View mTasksView;

    private final TasksRepository mTasksRepository;

    @Inject
    TasksPresenter(TasksRepository tasksRepository) {
        mTasksRepository = tasksRepository;
    }


    @Override
    public void loadTask() {
        mTasksView.showProgress(true);
        mTasksRepository.getTasks(new TasksDataSource.LoadTasksCallback() {
            @Override
            public void onTasksLoaded(List<Task> tasks) {
                List<Task> tasksToShow = new ArrayList<Task>();

                // This callback may be called twice, once for the cache and once for loading
                // the data from the server API, so we check before decrementing, otherwise
                // it throws "Counter has been corrupted!" exception.

                // We filter the tasks based on the requestType
                for (Task task : tasks) {
                    tasksToShow.add(task);
                }
                // The view may not be able to handle UI updates anymore
                if (mTasksView == null) {
                    return;
                }

                if (!mTasksView.isActive()) {
                    return;
                }

                processTasks(tasksToShow);
            }

            @Override
            public void onDataNotAvailable() {
                // The view may not be able to handle UI updates anymore
                if (mTasksView == null) {
                    return;
                }

                if (!mTasksView.isActive()) {
                    return;
                }
                mTasksView.showLoadingTasksError();
            }
        });
    }

    private void processTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            // Show a message indicating there are no tasks for that filter type.
            if (mTasksView.isActive()) {
                mTasksView.showProgress(false);
                mTasksView.showNoTasks();
            }
        } else {
            // Show the list of tasks
            if (mTasksView.isActive()) {
                mTasksView.showProgress(false);
                mTasksView.showTasks(tasks);
            }

        }
        mTasksView.showProgress(false);
    }


    @Override
    public void addNewTask() {
        if (mTasksView != null) {
            mTasksView.showAddTask();
        }
    }

    @Override
    public void result(int requestCode, int resultCode) {
        if (AddEditTaskActivity.REQUEST_ADD_TASK == requestCode && Activity.RESULT_OK == resultCode) {
            if (mTasksView != null) {
                mTasksView.showSuccessfullySavedMessage();
            }
        }
    }


    @Override
    public void takeView(TasksContract.View view) {
        this.mTasksView = view;
        loadTask();
    }

    @Override
    public void dropView() {
        mTasksView = null;
    }
}
