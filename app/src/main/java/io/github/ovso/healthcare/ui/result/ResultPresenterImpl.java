package io.github.ovso.healthcare.ui.result;

import android.content.Intent;
import android.support.annotation.NonNull;
import io.github.ovso.healthcare.data.KeyName;
import io.github.ovso.healthcare.data.network.ResultRequest;
import io.github.ovso.healthcare.data.network.model.youtube.SearchItem;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterDataModel;
import io.github.ovso.healthcare.utils.ObjectUtils;
import io.github.ovso.healthcare.utils.ResourceProvider;
import io.github.ovso.healthcare.utils.SchedulersFacade;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class ResultPresenterImpl implements ResultPresenter {

  private ResultPresenter.View view;
  private ResourceProvider resourceProvider;
  private ResultRequest request;
  private SchedulersFacade schedulers;
  private BaseAdapterDataModel<SearchItem> adapterDataModel;
  private CompositeDisposable compositeDisposable = new CompositeDisposable();
  private String pageToken;

  public ResultPresenterImpl(
      ResultPresenter.View $view,
      ResourceProvider $resourceProvider,
      ResultRequest $request, SchedulersFacade $scheduler,
      BaseAdapterDataModel<SearchItem> $adapterDataModel) {
    view = $view;
    resourceProvider = $resourceProvider;
    request = $request;
    schedulers = $scheduler;
    adapterDataModel = $adapterDataModel;
  }

  @Override public void onCreate(@NonNull Intent intent) {
    view.setupRecyclerView();
    String name = intent.getStringExtra(KeyName.DISEASE_NAME.getValue());
    if (!ObjectUtils.isEmpty(name)) {
      Disposable subscribe = request.getResult(name, pageToken)
          .subscribeOn(schedulers.io())
          .observeOn(schedulers.ui())
          .subscribe(search -> {
            Timber.d("search= " + search.getItems().size());
            adapterDataModel.addAll(search.getItems());
            view.refresh();
          }, throwable -> {
          });
      compositeDisposable.add(subscribe);
    }
  }
}