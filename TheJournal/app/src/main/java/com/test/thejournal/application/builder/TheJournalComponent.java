package com.test.thejournal.application.builder;

import com.test.thejournal.application.TheJournalApplication;
import com.test.thejournal.network.NetworkModule;
import dagger.Component;
import retrofit2.Retrofit;

@TheJournalAppScope
@Component(modules = { TheJournalModule.class, NetworkModule.class })
public interface TheJournalComponent {

  void inject(TheJournalApplication application);

  Retrofit retrofitClient();
}
