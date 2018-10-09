package io.github.ovso.healthcare.ui.model_group.di;

import dagger.Binds;
import dagger.Module;
import io.github.ovso.healthcare.ui.model_group.ModelGroupActivity;
import io.github.ovso.healthcare.ui.model_group.ModelGroupPresenter;

@Module public abstract class ModelGroupActivityViewModule {

  @Binds abstract ModelGroupPresenter.View bindModelGroupView(ModelGroupActivity activity);
}