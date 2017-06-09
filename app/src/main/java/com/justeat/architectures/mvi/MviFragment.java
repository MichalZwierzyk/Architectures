package com.justeat.architectures.mvi;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jakewharton.rxbinding2.view.RxView;
import com.justeat.architectures.R;
import com.justeat.architectures.databinding.FragmentViewDbMviBinding;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;

public class MviFragment extends Fragment {
    @BindView(R.id.get_user_button) Button getUserButton;
    @BindView(R.id.user_name_edittext) TextInputEditText userNameEditText;

    private UserUiModel userUiModel;
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        FragmentViewDbMviBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_view_db_mvi, container, false);

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
    public Observable<GetUserUiEvent> getUiEventsStream() {
        return RxView.clicks(getUserButton).map(click -> new GetUserUiEvent(userNameEditText.getText().toString()));
    }

    public UserUiModel getUserUiModel() {
        return userUiModel;
    }
}
