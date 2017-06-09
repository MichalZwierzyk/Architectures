package com.justeat.architectures.components;

import dagger.Component;

@Component
interface DiComponent {
    void inject(ComponentsActivity componentsActivity);
}
