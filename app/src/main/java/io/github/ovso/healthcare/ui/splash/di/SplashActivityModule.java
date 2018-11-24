package io.github.ovso.healthcare.ui.splash.di;

import dagger.Module;
import dagger.Provides;
import io.github.ovso.healthcare.data.db.AppDatabase;
import io.github.ovso.healthcare.data.network.VersionRequest;
import io.github.ovso.healthcare.ui.splash.SplashActivity;
import io.github.ovso.healthcare.ui.splash.SplashPresenter;
import io.github.ovso.healthcare.ui.splash.SplashPresenterImpl;
import io.github.ovso.healthcare.utils.ResourceProvider;
import io.github.ovso.healthcare.utils.SchedulersFacade;

@Module public class SplashActivityModule {
  @Provides SplashPresenter provideSplashPresenter(SplashPresenter.View view,
      SplashActivity activity,
      ResourceProvider resourceProvider, AppDatabase database, SchedulersFacade schedulers) {

    SplashPresenter presenter =
        new SplashPresenterImpl(view, resourceProvider, database, schedulers);
    activity.getLifecycle().addObserver(presenter);
    return presenter;
  }
}
