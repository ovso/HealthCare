package io.github.ovso.healthcare.ui.main;

import io.github.ovso.healthcare.data.network.MainRequest;
import io.github.ovso.healthcare.data.network.model.Disease;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterDataModel;
import io.github.ovso.healthcare.utils.ResourceProvider;
import io.github.ovso.healthcare.utils.SchedulersFacade;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class MainPresenterImpl implements MainPresenter {

  private MainPresenter.View view;
  private ResourceProvider resourceProvider;
  private CompositeDisposable compositeDisposable = new CompositeDisposable();
  private SchedulersFacade schedulers;
  private BaseAdapterDataModel<Disease> adapterDataModel;

  public MainPresenterImpl(MainPresenter.View $view, ResourceProvider $ResourceProvider,
      SchedulersFacade $schedulers, BaseAdapterDataModel<Disease> $adapterDataModel) {
    view = $view;
    resourceProvider = $ResourceProvider;
    schedulers = $schedulers;
    adapterDataModel = $adapterDataModel;
  }

  @Override public void onCreated() {
    view.setupToolbar();
    view.setupSearchLiveo();
    view.setupRecyclerView();

    reqDiseases();
  }

  private void reqDiseases() {

    Disposable subscribe = Single.fromCallable(() -> {
      String json = resourceProvider.assetsToJson("disease.json");
      return Disease.fromJson(json);
    })
        .subscribeOn(schedulers.io())
        .observeOn(schedulers.ui())
        .subscribe(o -> {
          Timber.d("size = " + o.size());
          adapterDataModel.addAll(o);
          view.refresh();
        }, throwable -> {
          Timber.d(throwable);
        });
    compositeDisposable.add(subscribe);
  }

  @Override public void onListItemClick(Object data, int itemPosition) {
    Timber.d("data = " + data);
    view.navigateToDetail(data);
  }
}