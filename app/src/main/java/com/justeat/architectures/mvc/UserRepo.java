package com.justeat.architectures.mvc;

import java.util.ArrayList;
import java.util.List;

class UserRepo {
    private List<UserRepoObserver> observers;

    UserRepo() {
        this.observers = new ArrayList<>();
    }

    // Transform Action into Result
    void getUser() {
        User newUser = generateUser();
        notifyObservers(newUser);
    }

    void addObserver(UserRepoObserver observer) {
        observers.add(observer);
    }

    @SuppressWarnings("unused")
    void clear() {
        observers.clear();
    }

    private User generateUser() {
        return new User("Chuck", "Rhoades");
    }

    private void notifyObservers(User newUser) {
        for(UserRepoObserver observer : observers) {
            observer.userUpdated(newUser);
        }
    }

    interface UserRepoObserver {
        void userUpdated(User user);
    }
}
