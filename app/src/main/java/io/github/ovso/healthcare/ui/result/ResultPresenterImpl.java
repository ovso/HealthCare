package io.github.ovso.healthcare.ui.result;

import android.content.Intent;
import android.text.TextUtils;
import io.github.ovso.healthcare.R;
import io.github.ovso.healthcare.data.KeyName;
import io.github.ovso.healthcare.data.Portal;
import io.github.ovso.healthcare.data.network.ResultRequest;
import io.github.ovso.healthcare.data.network.model.youtube.Search;
import io.github.ovso.healthcare.data.network.model.youtube.SearchItem;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterDataModel;
import io.github.ovso.healthcare.utils.ObjectUtils;
import io.github.ovso.healthcare.utils.ResourceProvider;
import io.github.ovso.healthcare.utils.SchedulersFacade;
import io.reactivex.SingleObserver;
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
  private String nextPageToken;
  private String diseaseName;

  public ResultPresenterImpl(
      ResultPresenter.View $view,
      ResourceProvider $resourceProvider,
      ResultRequest $request, SchedulersFacade $scheduler,
      BaseAdapterDataModel<SearchItem> $adapterDataModel, Intent $intent) {
    view = $view;
    resourceProvider = $resourceProvider;
    request = $request;
    schedulers = $scheduler;
    adapterDataModel = $adapterDataModel;
    diseaseName = $intent.getStringExtra(KeyName.DISEASE_NAME.getValue());
  }

  @Override public void onCreate() {
    view.setupActionBar();
    view.setTitle(diseaseName);
    view.setupRecyclerView();
    view.setupSwipeRefresh();

    reqVideo();
  }

  private void reqVideo() {
    view.showLoading();
    if (!ObjectUtils.isEmpty(diseaseName)) {
      request.search(diseaseName, nextPageToken)
          .subscribeOn(schedulers.io())
          .observeOn(schedulers.ui())
          .subscribe(new SingleObserver<Search>() {
            @Override public void onSubscribe(Disposable d) {
              compositeDisposable.add(d);
            }

            @Override public void onSuccess(Search search) {
              nextPageToken = search.getNextPageToken();
              adapterDataModel.addAll(search.getItems());
              view.refresh();
              view.setLoaded();
              view.hideLoading();
              showEmpty();
            }

            @Override public void onError(Throwable e) {
              Timber.d(e);
              view.hideLoading();
            }
          });
    } else {
      view.hideLoading();
    }
  }

  private void showEmpty() {
    if (adapterDataModel.getSize() == 0) {
      view.showEmptyAni();
      view.hideRecyclerView();
    } else {
      view.hideEmpthAni();
      view.showRecyclerView();
    }
  }

  @Override public void onOptionsItemSelected(int itemId) {
    switch (itemId) {
      case R.id.action_google:
      case R.id.action_naver:
      case R.id.action_daum:
        view.navigateToWeb(itemId, diseaseName);
        break;
    }
  }

  @Override public void onItemClick(SearchItem item) {
    view.navigateToVideo(item);
  }

  private void clearObject() {
    nextPageToken = null;
    adapterDataModel.clear();
  }

  @Override public void onSwipeRefresh() {
    clearObject();
    reqVideo();
  }

  @Override public void onLoadMore() {
    if (!TextUtils.isEmpty(nextPageToken)) {
      reqVideo();
    }
  }
}