package com.justeat.architectures.react;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

class ViewModel {
    private UserRepo userRepo;
    private UserUiModel userUiModel;

    ViewModel(UserRepo userRepo, UserUiModel userUiModel) {
        this.userRepo = userRepo;
        this.userUiModel = userUiModel;
    }

    void onGetUserButtonClicked(GetUserUiEvent getUserUiEvent) {
        // Transform Ui Event into Action
        GetUserAction getUserAction = new GetUserAction(getUserUiEvent.getName());
        userRepo.getUser(getUserAction)
                .onErrorReturn(throwable -> new UserResult("Unknown", "", UserRole.NONE, "", 0.0))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userResult -> {
                    // Transform Result into UiModel (Presentation Logic in View Model)
                    String userFullName = userResult.getName() + userResult.getSurname();

                    int userColor;
                    if (userResult.getRole() == UserRole.ADMIN) {
                        userColor = 0xFFFF0000;
                    } else if(userResult.getRole() == UserRole.GUEST) {
                        userColor = 0xFF0000FF;
                    } else {
                        userColor = 0xFF000000;
                    }

                    // Consume UiModel
                    userUiModel.fullname.set(userFullName);
                    userUiModel.color.set(userColor);
            });
    }
}
