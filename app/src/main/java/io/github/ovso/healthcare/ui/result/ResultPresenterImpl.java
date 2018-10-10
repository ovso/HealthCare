package io.github.ovso.healthcare.ui.result;

import io.github.ovso.healthcare.data.network.ResultRequest;
import io.github.ovso.healthcare.data.network.model.youtube.SearchItem;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterDataModel;
import io.github.ovso.healthcare.utils.ResourceProvider;
import io.github.ovso.healthcare.utils.SchedulersFacade;

public class ResultPresenterImpl implements ResultPresenter {

  private ResultPresenter.View view;
  private ResourceProvider resourceProvider;
  private ResultRequest request;
  private SchedulersFacade schedulers;
  private BaseAdapterDataModel<SearchItem> adapterDataModel;

  public ResultPresenterImpl(ResultPresenter.View $view, ResourceProvider $resourceProvider,
      ResultRequest $request, SchedulersFacade $scheduler,
      BaseAdapterDataModel<SearchItem> $adapterDataModel) {
    view = $view;
    resourceProvider = $resourceProvider;
    request = $request;
    schedulers = $scheduler;
    adapterDataModel = $adapterDataModel;
  }

  @Override public void onCreate() {

  }
}