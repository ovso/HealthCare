package io.github.ovso.healthcare.ui.main.di;

import androidx.lifecycle.LifecycleOwner;
import dagger.Binds;
import dagger.Module;
import io.github.ovso.healthcare.data.db.model.DiseaseEntity;
import io.github.ovso.healthcare.ui.main.MainActivity;
import io.github.ovso.healthcare.ui.main.MainPresenter;
import io.github.ovso.healthcare.ui.base.listener.DiseaseOnItemClickListener;

@Module public abstract class MainActivityViewModule {

  @Binds abstract MainPresenter.View bindMainView(MainActivity act);

  @Binds
  abstract DiseaseOnItemClickListener<DiseaseEntity> bindOnItemClickListener(MainActivity act);

  @Binds abstract LifecycleOwner bindLifecycleOwner(MainActivity act);
}