package com.justeat.architectures.mvc;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.justeat.architectures.R;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MvcFragment extends Fragment implements UserRepo.UserRepoObserver {
    @BindView(R.id.user_textview) TextView userTextView;
    @BindString(R.string.user_format) String userFormat;

    private Controller controller;
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_view, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    // Generate Ui Events
    @OnClick(R.id.get_user_button)
    void getUserButtonClicked() {
        controller.onGetUserButtonClicked();
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void userUpdated(User user) {
        // Transform Result into UiModel
        String userName = user.getName();
        String userSurname = user.getSurname();

        // Consume UiModel
        userTextView.setText(String.format(userFormat, userName, userSurname));
    }
}
