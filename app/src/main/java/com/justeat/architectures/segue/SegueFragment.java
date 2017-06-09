package com.justeat.architectures.segue;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.justeat.architectures.R;
import com.justeat.architectures.databinding.FragmentViewDbSegueBinding;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SegueFragment extends Fragment {
    @BindView(R.id.user_name_edittext) TextInputEditText userNameEditText;

    private UserUiModel userUiModel;
    private Unbinder unbinder;
    private ViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        FragmentViewDbSegueBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_view_db_segue, container, false);

        userUiModel = new UserUiModel("", 0xFF000000);
        binding.setUser(userUiModel);

        unbinder = ButterKnife.bind(this, binding.getRoot());

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    // Generate Ui Events
    @OnClick(R.id.get_user_button)
    void onGetUserButtonClicked() {
        String userName = userNameEditText.getText().toString();
        GetUserUiEvent getUserUiEvent = new GetUserUiEvent(userName);
        viewModel.onGetUserButtonClicked(getUserUiEvent);
    }

    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public UserUiModel getUserUiModel() {
        return userUiModel;
    }
}
