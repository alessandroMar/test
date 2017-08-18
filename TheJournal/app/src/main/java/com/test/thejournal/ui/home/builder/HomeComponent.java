package com.test.thejournal.ui.home.builder;

import com.test.thejournal.application.builder.TheJournalComponent;
import com.test.thejournal.ui.home.HomeActivity;
import dagger.Component;

@HomeScope
@Component(modules = HomeModule.class, dependencies = TheJournalComponent.class)
public interface HomeComponent {

  void inject(HomeActivity activity);
}
