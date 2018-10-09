package io.github.ovso.healthcare.ui.detail.di;

import dagger.Binds;
import dagger.Module;
import io.github.ovso.healthcare.ui.detail.DetailActivity;
import io.github.ovso.healthcare.ui.detail.DetailPresenter;

@Module public abstract class DetailActivityViewModule {

  @Binds abstract DetailPresenter.View bindDetailView(DetailActivity activity);
}