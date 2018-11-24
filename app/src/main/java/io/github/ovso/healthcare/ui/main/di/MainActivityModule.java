package io.github.ovso.healthcare.ui.main.di;

import androidx.lifecycle.LifecycleOwner;
import dagger.Module;
import dagger.Provides;
import io.github.ovso.healthcare.data.db.AppDatabase;
import io.github.ovso.healthcare.data.db.model.DiseaseEntity;
import io.github.ovso.healthcare.data.network.VersionRequest;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterDataModel;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterView;
import io.github.ovso.healthcare.ui.base.listener.DiseaseOnItemClickListener;
import io.github.ovso.healthcare.ui.main.MainPresenter;
import io.github.ovso.healthcare.ui.main.MainPresenterImpl;
import io.github.ovso.healthcare.ui.main.adapter.MainAdapter;
import io.github.ovso.healthcare.utils.ResourceProvider;
import io.github.ovso.healthcare.utils.SchedulersFacade;
import javax.inject.Singleton;

@Module public class MainActivityModule {

  @Provides MainPresenter provideMainPresenter(MainPresenter.View view,
      ResourceProvider resourceProvider, SchedulersFacade schedulersFacade,
      BaseAdapterDataModel<DiseaseEntity> adapterDataModel,
      AppDatabase database, LifecycleOwner lifecycleOwner, VersionRequest versionRequest) {
    MainPresenter presenter = new MainPresenterImpl(
        view, resourceProvider, schedulersFacade, adapterDataModel, database, versionRequest);

    lifecycleOwner.getLifecycle().addObserver(presenter);

    return presenter;
  }

  @Singleton @Provides MainAdapter provideMainAdapter(DiseaseOnItemClickListener<DiseaseEntity> l) {
    return new MainAdapter.Builder().setItemClickListener(l).build();
  }

  @Provides BaseAdapterDataModel<DiseaseEntity> provideMainAdapterDataModel(MainAdapter adapter) {
    return adapter;
  }

  @Provides BaseAdapterView provideMainAdapterView(MainAdapter adapter) {
    return adapter;
  }
}