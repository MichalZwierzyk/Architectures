package com.justeat.architectures.react;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

class UserRepo {
    private final List<UserResult> users = new ArrayList<>();

    public UserRepo() {
        users.add(new UserResult("Chuck", "Rhoades", UserRole.ADMIN, "A0C3", 1234.56));
        users.add(new UserResult("Billy", "Dollar", UserRole.ADMIN, "B0C1", 2134.59));
        users.add(new UserResult("Amanda", "Rhoades", UserRole.GUEST, "C3E2", 4331.14));
        users.add(new UserResult("Bobby", "Axelrod", UserRole.ADMIN, "A1A1", 9999.11));
    }

    // Transform Action into Result
    Observable<UserResult> getUser(GetUserAction getUserAction) {
        return Observable.fromCallable(() -> searchUser(getUserAction.getName()));
    }

    private UserResult searchUser(String userName) {
        for(UserResult user : users) {
            if (user.getName().equals(userName))
                return user;
        }
        return null;
    }
}
