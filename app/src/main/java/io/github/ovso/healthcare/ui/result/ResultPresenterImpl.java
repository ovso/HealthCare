package io.github.ovso.healthcare.ui.result;

import android.content.Intent;
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
    if (!ObjectUtils.isEmpty(diseaseName)) {
      Disposable subscribe = request.getResult(diseaseName, pageToken)
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

  @Override public boolean onOptionsItemSelected(int itemId) {
    if (itemId != android.R.id.home) {
      view.navigateToWeb(itemId, diseaseName);
    } else {
      view.finish();
    }
    return true;
  }

  @Override public void onItemClick(SearchItem item) {
    view.navigateToVideo(item);
  }
}