package com.justeat.architectures.mvvm;

class UserRepo {
    // Transform Action into Result
    User getUser() {
        return new User("Chuck", "Rhoades", UserRole.ADMIN, "A0C3", 1234.56);
    }
}
