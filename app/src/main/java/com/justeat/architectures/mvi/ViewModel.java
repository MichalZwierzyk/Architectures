package com.justeat.architectures.mvi;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;

class ViewModel {
    private UserRepo userRepo;
    private UserUiModel userUiModel;
    private Observable<? extends UiEvent> uiEvents;

    ViewModel(UserRepo userRepo, UserUiModel userUiModel, Observable<? extends UiEvent> uiEvents) {
        this.userRepo = userRepo;
        this.userUiModel = userUiModel;
        this.uiEvents = uiEvents;

        setUpDataFlow();
    }
    private void setUpDataFlow() {
        uiEvents.compose(uiEventsToResultTransformer)
            .compose(resultToUiModelTransformer)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(uiModel -> {
                // Consume UiModel
                userUiModel.fullname.set(uiModel.fullname.get());
                userUiModel.color.set(uiModel.color.get());
            });
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
