package io.github.ovso.healthcare.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import io.github.ovso.healthcare.ui.brand.BrandActivity;
import io.github.ovso.healthcare.ui.brand.di.BrandActivityModule;
import io.github.ovso.healthcare.ui.brand.di.BrandActivityViewModule;
import io.github.ovso.healthcare.ui.main.MainActivity;
import io.github.ovso.healthcare.ui.main.di.MainActivityModule;
import io.github.ovso.healthcare.ui.main.di.MainActivityViewModule;
import io.github.ovso.healthcare.ui.model.ModelActivity;
import io.github.ovso.healthcare.ui.model.di.ModelActivityModule;
import io.github.ovso.healthcare.ui.model.di.ModelActivityViewModule;
import io.github.ovso.healthcare.ui.model_group.ModelGroupActivity;
import io.github.ovso.healthcare.ui.model_group.di.ModelGroupActivityModule;
import io.github.ovso.healthcare.ui.model_group.di.ModelGroupActivityViewModule;
import io.github.ovso.healthcare.ui.result.ResultActivity;
import io.github.ovso.healthcare.ui.result.di.ResultActivityModule;
import io.github.ovso.healthcare.ui.result.di.ResultActivityViewModule;
import javax.inject.Singleton;

@Module(includes = { AndroidSupportInjectionModule.class })
public abstract class ActivityBuilder {
  @Singleton @ContributesAndroidInjector(modules = {
      MainActivityModule.class,
      MainActivityViewModule.class
  })
  abstract MainActivity bindMainActivity();

  @Singleton @ContributesAndroidInjector(modules = {
      BrandActivityModule.class,
      BrandActivityViewModule.class
  })
  abstract BrandActivity bindBrandActivity();

  @Singleton @ContributesAndroidInjector(modules = {
      ModelGroupActivityModule.class,
      ModelGroupActivityViewModule.class
  })
  abstract ModelGroupActivity bindModelGroupActivity();

  @Singleton @ContributesAndroidInjector(modules = {
      ModelActivityModule.class,
      ModelActivityViewModule.class
  })
  abstract ModelActivity bindModelActivity();

  @Singleton @ContributesAndroidInjector(modules = {
      ResultActivityModule.class, ResultActivityViewModule.class
  })
  abstract ResultActivity bindResultActivity();
}
