package io.github.ovso.healthcare.ui.detail.di;

import dagger.Module;
import dagger.Provides;
import io.github.ovso.healthcare.data.network.DetailRequest;
import io.github.ovso.healthcare.ui.detail.DetailPresenter;
import io.github.ovso.healthcare.ui.detail.DetailPresenterImpl;
import io.github.ovso.healthcare.utils.ResourceProvider;
import io.github.ovso.healthcare.utils.SchedulersFacade;

@Module public class DetailActivityModule {

  @Provides DetailPresenter provideDetailPresenter(DetailPresenter.View view,
      ResourceProvider resourceProvider, DetailRequest request, SchedulersFacade schedulersFacade) {
    return new DetailPresenterImpl(view, resourceProvider, request, schedulersFacade);
  }
}