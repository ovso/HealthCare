package io.github.ovso.healthcare.ui.model.di;

import dagger.Binds;
import dagger.Module;
import io.github.ovso.healthcare.ui.model.ModelActivity;
import io.github.ovso.healthcare.ui.model.ModelPresenter;

@Module public abstract class ModelActivityViewModule {

  @Binds abstract ModelPresenter.View bindModelView(ModelActivity activity);
}