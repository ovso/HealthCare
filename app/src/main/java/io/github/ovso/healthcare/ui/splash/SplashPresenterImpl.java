package io.github.ovso.healthcare.ui.splash;

import android.os.Handler;
import android.os.Looper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pixplicity.easyprefs.library.Prefs;
import io.github.ovso.healthcare.data.db.AppDatabase;
import io.github.ovso.healthcare.data.db.DiseaseEntity;
import io.github.ovso.healthcare.utils.ResourceProvider;
import io.github.ovso.healthcare.utils.SchedulersFacade;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import java.util.List;
import java.util.concurrent.TimeUnit;
import timber.log.Timber;

public class SplashPresenterImpl implements SplashPresenter {
  private final static String PREFS_KEY_FIRST_RUN = "first_run";
  private SplashPresenter.View view;
  private ResourceProvider resourceProvider;
  private AppDatabase database;
  private SchedulersFacade schedulers;
  private CompositeDisposable compositeDisposable = new CompositeDisposable();

  public SplashPresenterImpl(SplashPresenter.View $view, ResourceProvider $resourceProvider,
      AppDatabase $database, SchedulersFacade $scheduler) {
    view = $view;
    resourceProvider = $resourceProvider;
    database = $database;
    schedulers = $scheduler;
  }

  @Override public void onCreate() {
    if (isFirstRun()) {
      assetsToDb();
    } else {
      compositeDisposable.add(
          Single.just("delay").delay(1, TimeUnit.SECONDS).subscribe(s -> goMain()));
    }
  }

  private void goMain() {
    view.finish();
    view.navigateToMain();
  }

  private void assetsToDb() {
    Observable.fromCallable(() -> resourceProvider.assetsToJson("disease.json"))
        .map(this::toList)
        .subscribeOn(schedulers.io())
        .subscribe(new Observer<List<DiseaseEntity>>() {
          @Override public void onSubscribe(Disposable d) {
            compositeDisposable.add(d);
          }

          @Override public void onNext(List<DiseaseEntity> entities) {
            database.diseaseDao().insert(entities);
          }

          @Override public void onError(Throwable e) {
            Timber.d(e);
          }

          @Override public void onComplete() {
            Prefs.putBoolean(PREFS_KEY_FIRST_RUN, false);
            new Handler(Looper.getMainLooper()).post(() -> goMain());
          }
        });
  }

  private boolean isFirstRun() {
    return Prefs.getBoolean(PREFS_KEY_FIRST_RUN, true);
  }

  private List<DiseaseEntity> toList(String json) {
    return new Gson().fromJson(json, new TypeToken<List<DiseaseEntity>>() {
    }.getType());
  }

  @Override public void onDestroy() {
    clear();
  }

  private void clear() {
    compositeDisposable.clear();
  }

  @Override public void onBackPressed() {
    clear();
    view.finish();
  }
}
