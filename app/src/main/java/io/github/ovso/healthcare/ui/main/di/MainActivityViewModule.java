package io.github.ovso.healthcare.ui.main.di;

import dagger.Binds;
import dagger.Module;
import io.github.ovso.healthcare.data.network.model.Disease;
import io.github.ovso.healthcare.ui.main.MainActivity;
import io.github.ovso.healthcare.ui.main.MainPresenter;
import io.github.ovso.healthcare.ui.main.adapter.MainOnItemClickListener;

@Module public abstract class MainActivityViewModule {

  @Binds abstract MainPresenter.View bindMainView(MainActivity act);

  @Binds abstract MainOnItemClickListener<Disease> bindOnItemClickListener(MainActivity act);
}