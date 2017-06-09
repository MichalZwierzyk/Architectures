package com.justeat.architectures.mvp;

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

public class MvpFragment extends Fragment implements UserView {
    @BindView(R.id.user_textview) TextView userTextView;
    @BindString(R.string.user_format) String userFormat;

    private Presenter presenter;
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_view, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    // Generate Ui Events
    @OnClick(R.id.get_user_button)
    void getUserButtonClicked() {
        presenter.onGetUserButtonClicked();
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void renderUser(UserUiModel userUiModel) {
        // Consume UiModel (Presentation Logic inside View)
        String userFullName = String.format(userFormat, userUiModel.getName(), userUiModel.getSurname());
        userTextView.setText(userFullName);
        if (userUiModel.getRole() == UserRole.ADMIN) {
            userTextView.setTextColor(0xFFFF0000);
        } else {
            userTextView.setTextColor(0xFF0000FF);
        }
    }
}
