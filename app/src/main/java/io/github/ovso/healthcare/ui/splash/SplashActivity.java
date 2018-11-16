package io.github.ovso.healthcare.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.Nullable;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.github.ovso.healthcare.R;
import io.github.ovso.healthcare.data.db.AppDatabase;
import io.github.ovso.healthcare.data.db.DiseaseEntity;
import io.github.ovso.healthcare.ui.base.BaseActivity;
import io.github.ovso.healthcare.ui.main.MainActivity;
import io.github.ovso.healthcare.utils.ResourceProvider;
import io.github.ovso.healthcare.utils.SchedulersFacade;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import timber.log.Timber;

public class SplashActivity extends BaseActivity {

  private CompositeDisposable compositeDisposable = new CompositeDisposable();

  @Inject AppDatabase database;
  @Inject ResourceProvider resourceProvider;
  @Inject SchedulersFacade schedulers;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Observable.fromCallable(() -> resourceProvider.assetsToJson("disease.json"))
        .map(json -> {
          List<DiseaseEntity> items = new Gson().fromJson(json,
              new TypeToken<ArrayList<DiseaseEntity>>() {
              }.getType());
          return items;
        })
        .delay(1, TimeUnit.SECONDS)
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
            int size = database.diseaseDao().getItems().size();
            Timber.d("size = " + size);
            for (DiseaseEntity diseaseEntity : database.diseaseDao().getItems()) {
              database.diseaseDao().delete(diseaseEntity);
            }

            int size1 = database.diseaseDao().getItems().size();
            Timber.d("size1 = " + size1);
          }
        });
  }

  private void navigateToMain() {
    finish();
    startActivity(new Intent(getApplicationContext(), MainActivity.class));
  }

  @Override public void onBackPressed() {
    compositeDisposable.clear();
    super.onBackPressed();
  }

  @Override protected int getLayoutResID() {
    return R.layout.activity_splash;
  }

  @Override protected void onDestroy() {
    compositeDisposable.clear();
    super.onDestroy();
  }
}