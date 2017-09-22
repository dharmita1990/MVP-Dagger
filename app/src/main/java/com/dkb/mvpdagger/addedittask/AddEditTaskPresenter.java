package com.dkb.mvpdagger.addedittask;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.dkb.mvpdagger.data.Task;
import com.dkb.mvpdagger.data.TasksRepository;
import com.dkb.mvpdagger.data.source.TasksDataSource;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by User on 13-09-2017.
 */

public class AddEditTaskPresenter implements AddEditTaskContract.Presenter {

    @NonNull
    private final TasksRepository mTasksRepository;
    @Nullable
    private AddEditTaskContract.View mTasksView;
    @NonNull
    private String mTaskID;

    @Inject
    public AddEditTaskPresenter(@Nullable String taskID, @NonNull TasksRepository tasksRepository) {
        mTasksRepository = checkNotNull(tasksRepository, "tasksRepository cannot be null");
        mTaskID = taskID;
    }

    @Override
    public void save(String title, String description) {
        Task task = new Task(title, description);
        mTasksRepository.saveTask(task);
        mTasksView.showTasksList();
    }

    @Override
    public void getTask() {
        mTasksRepository.getTask(mTaskID, new TasksDataSource.GetTaskCallback() {
            @Override
            public void onTaskLoaded(Task task) {
                if (mTasksView.isActive()) {
                    mTasksView.setTitle(task.getTitle());
                    mTasksView.setDescription(task.getDescription());
                }
            }

            @Override
            public void onDataNotAvailable() {
            }
        });
    }

    @Override
    public void takeView(AddEditTaskContract.View view) {
        mTasksView = view;
        if (mTaskID != null)
            getTask();
    }


    @Override
    public void dropView() {

    }

}
