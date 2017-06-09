package com.justeat.architectures.vm;

class UserRepo {
    // Transform Action into Result
    User getUser() {
        return new User("Chuck", "Rhoades");
    }
}
