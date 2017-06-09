package com.justeat.architectures.vm;

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

public class ViewModelFragment extends Fragment {
    @BindView(R.id.user_textview) TextView userTextView;
    @BindString(R.string.user_format) String userFormat;

    private UserRepo userRepo;
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
        // Transform Ui Event into Action
        User user = userRepo.getUser();

        // Transform Result into UiModel
        String userName = user.getName();
        String userSurname = user.getSurname();

        // Consume UiModel
        userTextView.setText(String.format(userFormat, userName, userSurname));
    }

    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
}
