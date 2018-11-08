package io.github.ovso.healthcare.ui.result.di;

import dagger.Binds;
import dagger.Module;
import io.github.ovso.healthcare.data.network.model.youtube.SearchItem;
import io.github.ovso.healthcare.ui.base.listener.BaseOnItemClickListener;
import io.github.ovso.healthcare.ui.result.ResultActivity;
import io.github.ovso.healthcare.ui.result.ResultPresenter;

@Module public abstract class ResultActivityViewModule {

  @Binds abstract ResultPresenter.View bindResultView(ResultActivity activity);

  @Binds abstract BaseOnItemClickListener<SearchItem> bindOnItemClickListener(ResultActivity act);
}