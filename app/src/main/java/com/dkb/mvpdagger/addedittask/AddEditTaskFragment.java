package com.dkb.mvpdagger.addedittask;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.dkb.mvpdagger.R;
import com.dkb.mvpdagger.dagger.ActivityScoped;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by User on 13-09-2017.
 */
@ActivityScoped
public class AddEditTaskFragment extends Fragment implements AddEditTaskContract.View {

    public static final String ARGUMENT_EDIT_TASK_ID = "EDIT_TASK_ID";
    private Unbinder unbinder;

    @Inject
    AddEditTaskContract.Presenter mPresenter;
    @BindView(R.id.edt_des)
    EditText edt_des;

    @BindView(R.id.edt_title)
    EditText edt_title;

    @Inject
    public AddEditTaskFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_addedit, container, false);
        unbinder = ButterKnife.bind(this, root);

        FloatingActionButton fab =
                (FloatingActionButton) getActivity().findViewById(R.id.fab_add_task);
        fab.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_done));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.save(edt_title.getText().toString().trim(), edt_des.getText().toString().trim());
            }
        });
        setHasOptionsMenu(true);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.takeView(this);
    }

    @Override
    public void onPause() {
        mPresenter.dropView();
        super.onPause();
    }

    @Override
    public void setTitle(String title) {
        edt_title.setText(title);
    }

    @Override
    public void setDescription(String des) {
        edt_des.setText(des);
    }

    @Override
    public void showTasksList() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        mPresenter.dropView();
    }
}
