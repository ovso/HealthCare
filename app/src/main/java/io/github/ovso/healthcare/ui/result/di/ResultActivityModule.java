package io.github.ovso.healthcare.ui.result.di;

import dagger.Module;
import dagger.Provides;
import io.github.ovso.healthcare.data.network.ResultRequest;
import io.github.ovso.healthcare.data.network.model.youtube.SearchItem;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterDataModel;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterView;
import io.github.ovso.healthcare.ui.result.ResultActivity;
import io.github.ovso.healthcare.ui.result.ResultPresenter;
import io.github.ovso.healthcare.ui.result.ResultPresenterImpl;
import io.github.ovso.healthcare.ui.result.adapter.ResultAdapter;
import io.github.ovso.healthcare.utils.ResourceProvider;
import io.github.ovso.healthcare.utils.SchedulersFacade;
import javax.inject.Singleton;

@Module public class ResultActivityModule {

  @Provides ResultPresenter provideResultPresenter(ResultPresenter.View view,
      ResourceProvider resourceProvider, ResultRequest ResultRequest,
      SchedulersFacade schedulersFacade, BaseAdapterDataModel<SearchItem> adapterDataModel,
      ResultActivity activity) {
    ResultPresenter presenter =
        new ResultPresenterImpl(view, resourceProvider, ResultRequest, schedulersFacade,
            adapterDataModel, activity.getIntent());
    activity.getLifecycle().addObserver(presenter);
    return presenter;
  }

  @Singleton @Provides ResultAdapter provideResultAdapter() {
    return new ResultAdapter();
  }

  @Provides BaseAdapterDataModel<SearchItem> provideAdapterDataModel(ResultAdapter adapter) {
    return adapter;
  }

  @Provides BaseAdapterView provideResultAdapterView(ResultAdapter adapter) {
    return adapter;
  }
}