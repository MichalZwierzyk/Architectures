package com.justeat.architectures.components;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.subjects.PublishSubject;

class ComponentsViewModel extends ViewModel {
    private UserRepo userRepo;
    private PublishSubject<UiEvent> uiEvents = PublishSubject.create();
    private MutableLiveData<UserUiModel> userUiModel = new MutableLiveData<>();

    ComponentsViewModel(UserRepo userRepo) {
        this.userRepo = userRepo;
        setUpDataFlow();
    }

    PublishSubject<UiEvent> getUiEvents() {
        return uiEvents;
    }

    LiveData<UserUiModel> getUserUiModel() {
        return userUiModel;
    }

    private void setUpDataFlow() {
        uiEvents.compose(uiEventsToResultTransformer)
            .compose(resultToUiModelTransformer)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(uiModel -> userUiModel.setValue(uiModel));
    }

    private String getUserFullName(UserResult userResult) {
        return userResult.getName() + userResult.getSurname();
    }

    private int getUserColor(UserResult userResult) {
        switch (userResult.getRole()) {
            case ADMIN:
                return 0xFFFF0000;
            case GUEST:
                return 0xFF0000FF;
            default:
                return 0xFF000000;
        }
    }

    private ObservableTransformer<GetUserAction, UserResult> actionToResultTransformer =
            getUserActionObservable -> getUserActionObservable.flatMap(getUserAction -> userRepo.getUser(getUserAction));

    private ObservableTransformer<UiEvent, Result> uiEventsToResultTransformer =
            // Transform Ui Event into Action
            uiEvents -> uiEvents.publish(shared ->
                    shared.ofType(GetUserUiEvent.class).map(getUserUiEvent ->
                            new GetUserAction(getUserUiEvent.getName())).compose(actionToResultTransformer));

    private ObservableTransformer<Result, UserUiModel> resultToUiModelTransformer =
            results -> results.scan(new UserUiModel("", 0), (userUiModel, result) -> {
                if (result instanceof UserResult) {
                    // Transform Result into UiModel (Presentation Logic in View Model)
                    UserResult userResult = (UserResult) result;
                    return new UserUiModel(getUserFullName(userResult), getUserColor(userResult));
                }
                throw new IllegalArgumentException("Unknown result: " + result);
            });
}
