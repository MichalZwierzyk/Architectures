package com.justeat.architectures.segue;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

class UserRepo {
    private final List<UserResult> users = new ArrayList<>();

    public UserRepo() {
        users.add(new UserResult("Chuck", "Rhoades", UserRole.ADMIN, "A0C3", 1234.56));
        users.add(new UserResult("Billy", "Dollar", UserRole.ADMIN, "B0C1", 2134.59));
        users.add(new UserResult("Amanda", "Rhoades", UserRole.GUEST, "C3E2", 4331.14));
        users.add(new UserResult("Bobby", "Axelrod", UserRole.ADMIN, "A1A1", 9999.11));
    }

    // Transform Action into Result
    @Nullable
    UserResult getUser(GetUserAction getUserAction) {
        for(UserResult user : users) {
            if (user.getName().equals(getUserAction.getName()))
                return user;
        }
        return null;
    }
}
