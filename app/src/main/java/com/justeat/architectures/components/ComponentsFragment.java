package com.justeat.architectures.components;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.LiveData;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.justeat.architectures.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

public class ComponentsFragment extends LifecycleFragment {
    @BindView(R.id.get_user_button) Button getUserButton;
    @BindView(R.id.user_name_edittext) TextInputEditText userNameEditText;
    @BindView(R.id.user_textview) TextView userTextView;

    private Unbinder unbinder;
    private Disposable uiEventsDisposable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_view_components, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        unbinder.unbind();
        uiEventsDisposable.dispose();
    }

    // Generate Ui Events
    void setupUiEventsStream(PublishSubject<UiEvent> uiEvents) {
        uiEventsDisposable = RxView.clicks(getUserButton)
            .map(click -> new GetUserUiEvent(userNameEditText.getText().toString()))
            .subscribe(uiEvents::onNext);
    }

    // Consume Ui Model
    void render(LiveData<UserUiModel> userUiModel) {
        userUiModel.observe(this, user -> {
            if (user != null) {
                userTextView.setText(user.fullname);
                userTextView.setTextColor(user.color);
            }
        });
    }
}
