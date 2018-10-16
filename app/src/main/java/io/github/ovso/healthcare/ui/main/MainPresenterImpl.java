package io.github.ovso.healthcare.ui.main;

import android.text.TextUtils;
import io.github.ovso.healthcare.data.network.model.Disease;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterDataModel;
import io.github.ovso.healthcare.utils.ResourceProvider;
import io.github.ovso.healthcare.utils.SchedulersFacade;
import io.reactivex.Observable;
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
  private String fileName;

  public MainPresenterImpl(MainPresenter.View $view, ResourceProvider $ResourceProvider,
      SchedulersFacade $schedulers, BaseAdapterDataModel<Disease> $adapterDataModel) {
    view = $view;
    resourceProvider = $ResourceProvider;
    schedulers = $schedulers;
    adapterDataModel = $adapterDataModel;
    fileName = "disease.json";
  }

  @Override public void onCreated() {
    view.setupToolbar();
    view.setupSearchLiveo();
    view.setupRecyclerView();

    reqDiseases();
  }

  private void reqDiseases() {

    Disposable subscribe = Single.fromCallable(() -> {
      String json = resourceProvider.assetsToJson(fileName);
      return Disease.fromJson(json);
    })
        .subscribeOn(schedulers.io())
        .observeOn(schedulers.ui())
        .subscribe(items -> {
          adapterDataModel.addAll(items);
          view.refresh();
        }, throwable -> Timber.d(throwable));
    compositeDisposable.add(subscribe);
  }

  @Override public void onItemClick(Disease disease) {
    view.navigateToDetail(disease);
  }

  @Override public void onItemLikeClick(Disease item) {
    Timber.d("onItemLikeClick = " + item);
  }

  @Override public void changedSearch(CharSequence charSequence) {
    String search = charSequence.toString().replaceAll("\\p{Z}", "");
    Timber.d("search = " + search);
    if (!TextUtils.isEmpty(search)) {
      Disposable subscribe = Observable.fromCallable(() -> {
        String json = resourceProvider.assetsToJson(fileName);
        return Disease.fromJson(json);
      })
          .flatMapIterable(diseases -> diseases)
          .filter(disease -> {
            String inpu = charSequence.toString();
            String name = disease.getName();
            name = name.replaceAll("\\p{Z}", "");
            inpu = inpu.replaceAll("\\p{Z}", "");
            return name.contains(inpu);
          })
          .toList().subscribeOn(schedulers.io()).observeOn(schedulers.ui()).subscribe(diseases -> {
            adapterDataModel.clear();
            adapterDataModel.addAll(diseases);
            view.refresh();
          }, throwable -> Timber.d(throwable));
      compositeDisposable.add(subscribe);
    } else {
      Disposable subscribe = Observable.fromCallable(() -> {
        String json = resourceProvider.assetsToJson(fileName);
        return Disease.fromJson(json);
      }).subscribeOn(schedulers.io()).observeOn(schedulers.ui()).subscribe(items -> {
        adapterDataModel.clear();
        adapterDataModel.addAll(items);
        view.refresh();
      });
      compositeDisposable.add(subscribe);
    }
  }

  @Override public void onBackPressed(boolean isDrawerOpen) {
    if (isDrawerOpen) {
      view.closeDrawer();
    } else {
      compositeDisposable.clear();
      view.finish();
    }
  }
}