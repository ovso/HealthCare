package io.github.ovso.healthcare.ui.result.di;

import dagger.Binds;
import dagger.Module;
import io.github.ovso.healthcare.ui.result.ResultActivity;
import io.github.ovso.healthcare.ui.result.ResultPresenter;

@Module public abstract class ResultActivityViewModule {

  @Binds abstract ResultPresenter.View bindResultView(ResultActivity activity);
}