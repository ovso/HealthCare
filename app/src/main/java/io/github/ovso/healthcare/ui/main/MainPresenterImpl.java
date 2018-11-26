package io.github.ovso.healthcare.ui.main;

import android.text.TextUtils;
import io.github.ovso.healthcare.BuildConfig;
import io.github.ovso.healthcare.R;
import io.github.ovso.healthcare.data.db.AppDatabase;
import io.github.ovso.healthcare.data.db.model.DiseaseEntity;
import io.github.ovso.healthcare.data.network.VersionRequest;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterDataModel;
import io.github.ovso.healthcare.utils.ResourceProvider;
import io.github.ovso.healthcare.utils.SchedulersFacade;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import java.util.List;
import java.util.Optional;
import timber.log.Timber;

public class MainPresenterImpl implements MainPresenter {

  private final VersionRequest versionRequest;
  private MainPresenter.View view;
  private ResourceProvider resourceProvider;
  private CompositeDisposable compositeDisposable = new CompositeDisposable();
  private SchedulersFacade schedulers;
  private BaseAdapterDataModel<DiseaseEntity> adapterDataModel;
  private AppDatabase database;

  public MainPresenterImpl(MainPresenter.View $view, ResourceProvider $ResourceProvider,
      SchedulersFacade $schedulers, BaseAdapterDataModel<DiseaseEntity> $adapterDataModel,
      AppDatabase $database, VersionRequest $versionRequest) {
    view = $view;
    resourceProvider = $ResourceProvider;
    schedulers = $schedulers;
    adapterDataModel = $adapterDataModel;
    database = $database;
    versionRequest = $versionRequest;
  }

  @Override public void onCreate() {
    view.setupToolbar();
    view.setupNavigationView();
    view.setTitle(resourceProvider.getString(R.string.app_name));
    view.setupSearchLiveo();
    view.setupRecyclerView();

    reqDatabase();
    reqVersion();
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

  @Override public void onItemClick(DiseaseEntity disease) {
    view.navigateToResult(disease);
  }

  @Override public void onItemLikeClick(DiseaseEntity item, boolean checked) {
    item.like = checked;
    database.diseaseDao().update(item);
  }

  @Override public boolean onNavigationItemSelected(int itemId) {
    switch (itemId) {
      case R.id.nav_like:
        view.navigateToLike();
        break;
      case R.id.nav_help:
        view.showMessage("도움말");
        break;
      case R.id.nav_share:
        String title = resourceProvider.getString(R.string.app_name);
        view.share(title, getShareText());
        break;
      case R.id.nav_license:
        view.showLicenses();
        break;
    }
    view.closeDrawer();
    return false;
  }

  private String getShareText() {
    String link = "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID;
    String recommend = resourceProvider.getString(R.string.all_recommendapp);
    StringBuffer buffer = new StringBuffer();
    buffer.append(recommend).append(link);
    return buffer.toString();
  }

  private void reqVersion() {
    versionRequest.version()
        .subscribeOn(schedulers.io())
        .observeOn(schedulers.ui())
        .subscribe(new Observer<String>() {
          @Override public void onSubscribe(Disposable d) {
            compositeDisposable.add(d);
          }

          @Override public void onNext(String version) {
            Timber.d("version = " + version);

            String appVersion = BuildConfig.VERSION_NAME;
            String newVersion = Optional.ofNullable(version).orElse(BuildConfig.VERSION_NAME);

            view.showAppVersion(toVersions(appVersion, newVersion));
          }

          @Override public void onError(Throwable e) {
            Timber.d(e);
            String appVersion = BuildConfig.VERSION_NAME;
            view.showAppVersion(toVersions(appVersion, appVersion));
          }

          @Override public void onComplete() {
          }
        });
  }

  private String toVersions(String appVersion, String newVersion) {
    String appVerPrefix = resourceProvider.getString(R.string.nav_appversion);
    String newVerPrefix = resourceProvider.getString(R.string.nav_newversion);
    String nextline = resourceProvider.getString(R.string.all_nextline);
    String appName = resourceProvider.getString(R.string.app_name);
    String hyphen = resourceProvider.getString(R.string.all_hyphen);
    StringBuffer builder = new StringBuffer();
    builder.append(hyphen).append(appName).append(hyphen).append(nextline);
    builder.append(appVerPrefix).append(appVersion);
    builder.append(nextline);
    builder.append(newVerPrefix).append(newVersion);
    return builder.toString();
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
            showEmpty();
          }, Timber::d);
      compositeDisposable.add(subscribe);
    } else {
      Disposable subscribe = Observable.fromCallable(() -> database.diseaseDao().getItems())
          .subscribeOn(schedulers.io()).observeOn(schedulers.ui()).subscribe(items -> {
            adapterDataModel.clear();
            adapterDataModel.addAll(items);
            view.refresh();
            showEmpty();
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