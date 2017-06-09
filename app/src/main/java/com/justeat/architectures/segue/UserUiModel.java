package com.justeat.architectures.segue;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;

public class UserUiModel {
    public final ObservableField<String> fullname = new ObservableField<>();
    public final ObservableInt color = new ObservableInt();

    UserUiModel(String fullname, int color) {
        this.fullname.set(fullname);
        this.color.set(color);
    }
}
