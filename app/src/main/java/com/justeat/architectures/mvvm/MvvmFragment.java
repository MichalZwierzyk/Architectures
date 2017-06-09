package com.justeat.architectures.mvvm;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.justeat.architectures.R;
import com.justeat.architectures.databinding.FragmentViewDbBinding;

public class MvvmFragment extends Fragment {
    private FragmentViewDbBinding binding;
    private UserUiModel userUiModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_view_db, container, false);

        userUiModel = new UserUiModel("", 0xFF000000);
        binding.setUser(userUiModel);

        return binding.getRoot();
    }

    public void setViewModel(ViewModel viewModel) {
        binding.setViewModel(viewModel);
    }

    public UserUiModel getUserUiModel() {
        return userUiModel;
    }
}
