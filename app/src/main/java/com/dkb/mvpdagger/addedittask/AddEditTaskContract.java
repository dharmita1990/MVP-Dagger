package com.dkb.mvpdagger.addedittask;


import com.dkb.mvpdagger.base.BasePresenter;
import com.dkb.mvpdagger.base.BaseView;

/**
 * Created by User on 06-09-2017.
 */

public interface AddEditTaskContract {
    interface View extends BaseView<Presenter> {
        void setTitle(String title);

        void setDescription(String description);

        void showTasksList();

        boolean isActive();


    }

    interface Presenter extends BasePresenter<View> {
        void save(String title, String description);

        void getTask();
        void takeView(AddEditTaskContract.View view);

        void dropView();
    }
}
