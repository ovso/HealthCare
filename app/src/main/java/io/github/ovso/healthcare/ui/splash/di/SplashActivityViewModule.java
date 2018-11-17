package io.github.ovso.healthcare.ui.splash.di;

import dagger.Binds;
import dagger.Module;
import io.github.ovso.healthcare.ui.splash.SplashActivity;
import io.github.ovso.healthcare.ui.splash.SplashPresenter;

@Module public abstract class SplashActivityViewModule {

  @Binds abstract SplashPresenter.View bindSplashView(SplashActivity act);
}
