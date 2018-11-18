package io.github.ovso.healthcare.ui.main;

import android.text.TextUtils;
import androidx.lifecycle.LifecycleOwner;
import io.github.ovso.healthcare.R;
import io.github.ovso.healthcare.data.db.AppDatabase;
import io.github.ovso.healthcare.data.db.model.DiseaseEntity;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterDataModel;
import io.github.ovso.healthcare.utils.ResourceProvider;
import io.github.ovso.healthcare.utils.SchedulersFacade;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import java.util.List;
import timber.log.Timber;

public class MainPresenterImpl implements MainPresenter {

  private final LifecycleOwner lifeCycleOwner;
  private MainPresenter.View view;
  private ResourceProvider resourceProvider;
  private CompositeDisposable compositeDisposable = new CompositeDisposable();
  private SchedulersFacade schedulers;
  private BaseAdapterDataModel<DiseaseEntity> adapterDataModel;
  private AppDatabase database;

  public MainPresenterImpl(MainPresenter.View $view, ResourceProvider $ResourceProvider,
      SchedulersFacade $schedulers, BaseAdapterDataModel<DiseaseEntity> $adapterDataModel,
      AppDatabase $database, LifecycleOwner $lifeCycleOwner) {
    view = $view;
    resourceProvider = $ResourceProvider;
    schedulers = $schedulers;
    adapterDataModel = $adapterDataModel;
    database = $database;
    lifeCycleOwner = $lifeCycleOwner;
  }

  @Override public void onCreate() {
    view.setupToolbar();
    view.setupNavigationView();
    view.setTitle(resourceProvider.getString(R.string.app_name));
    view.setupSearchLiveo();
    view.setupRecyclerView();

    //reqDiseases();
    reqDatabase();
  }

  @Override
  public void reqDatabase() {
    Single.fromCallable(() -> database.diseaseDao().getItems())
        .subscribeOn(schedulers.io())
        .observeOn(schedulers.ui())
        .subscribe(new SingleObserver<List<DiseaseEntity>>() {
          @Override public void onSubscribe(Disposable d) {
            compositeDisposable.add(d);
          }

          @Override public void onSuccess(List<DiseaseEntity> entities) {
            adapterDataModel.clear();
            adapterDataModel.addAll(entities);
            view.refresh();
          }

          @Override public void onError(Throwable e) {
            Timber.d(e);
          }
        });

    database.diseaseDao()
        .getLiveItem()
        .observe(lifeCycleOwner, entity -> Timber.d("entity = " + entity.toString()));
    //database.diseaseDao().getLiveItems().observe(lifeCycleOwner, entities -> {
    //  adapterDataModel.clear();
    //  adapterDataModel.addAll(entities);
    //  view.refresh();
    //});
  }

  @Override public void onItemClick(DiseaseEntity disease) {
    view.navigateToResult(disease);
  }

  @Override public void onItemLikeClick(DiseaseEntity item, boolean checked) {
    item.like = checked;
    database.diseaseDao().update(item);
  }

  @Override public void changedSearch(CharSequence charSequence) {
    String search = charSequence.toString().replaceAll("\\p{Z}", "");
    Timber.d("search = " + search);
    if (!TextUtils.isEmpty(search)) {
      Disposable subscribe = Observable.fromCallable(() -> database.diseaseDao().getItems())
          .flatMapIterable(diseases -> diseases)
          .filter(disease -> {
            String inpu = charSequence.toString().replaceAll("\\p{Z}", "");
            String name = disease.name.replaceAll("\\p{Z}", "");
            return name.contains(inpu);
          })
          .toList().subscribeOn(schedulers.io()).observeOn(schedulers.ui()).subscribe(diseases -> {
            adapterDataModel.clear();
            adapterDataModel.addAll(diseases);
            view.refresh();
          }, throwable -> Timber.d(throwable));
      compositeDisposable.add(subscribe);
    } else {
      Disposable subscribe = Observable.fromCallable(() -> database.diseaseDao().getItems())
          .subscribeOn(schedulers.io()).observeOn(schedulers.ui()).subscribe(items -> {
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