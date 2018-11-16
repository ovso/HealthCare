package io.github.ovso.healthcare.di;

import android.app.Application;
import android.content.Context;
import dagger.Module;
import dagger.Provides;
import io.github.ovso.healthcare.data.db.AppDatabase;
import io.github.ovso.healthcare.utils.ResourceProvider;
import io.github.ovso.healthcare.utils.SchedulersFacade;
import javax.inject.Singleton;

@Module public class AppModule {
  @Provides Context provideContext(Application application) {
    return application;
  }

  @Provides
  public AppDatabase provideAppDatabase(Application application) {
    return AppDatabase.getInstance(application);
  }
}
