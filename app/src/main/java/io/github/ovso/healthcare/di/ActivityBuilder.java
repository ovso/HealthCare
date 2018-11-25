package io.github.ovso.healthcare.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import io.github.ovso.healthcare.ui.like.LikeActivity;
import io.github.ovso.healthcare.ui.like.di.LikeActivityModule;
import io.github.ovso.healthcare.ui.like.di.LikeActivityViewModule;
import io.github.ovso.healthcare.ui.main.MainActivity;
import io.github.ovso.healthcare.ui.main.di.MainActivityModule;
import io.github.ovso.healthcare.ui.main.di.MainActivityViewModule;
import io.github.ovso.healthcare.ui.result.ResultActivity;
import io.github.ovso.healthcare.ui.result.di.ResultActivityModule;
import io.github.ovso.healthcare.ui.result.di.ResultActivityViewModule;
import io.github.ovso.healthcare.ui.splash.SplashActivity;
import io.github.ovso.healthcare.ui.splash.di.SplashActivityModule;
import io.github.ovso.healthcare.ui.splash.di.SplashActivityViewModule;
import io.github.ovso.healthcare.ui.web.WebActivity;
import javax.inject.Singleton;

@Module(includes = { AndroidSupportInjectionModule.class })
public abstract class ActivityBuilder {
  @Singleton @ContributesAndroidInjector(modules = {
      MainActivityModule.class,
      MainActivityViewModule.class
  })
  abstract MainActivity bindMainActivity();

  @Singleton @ContributesAndroidInjector(modules = {
      ResultActivityModule.class, ResultActivityViewModule.class
  })
  abstract ResultActivity bindResultActivity();

  @Singleton @ContributesAndroidInjector(modules = {
      SplashActivityModule.class, SplashActivityViewModule.class
  })
  abstract SplashActivity bindSplashActivity();

  @Singleton @ContributesAndroidInjector(modules = {
      LikeActivityModule.class, LikeActivityViewModule.class
  })
  abstract LikeActivity bindLikeActivity();

  @Singleton @ContributesAndroidInjector(modules = {
  })
  abstract WebActivity bindWebActivity();
}