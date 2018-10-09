package io.github.ovso.healthcare.ui.model.di;

import dagger.Module;
import dagger.Provides;
import io.github.ovso.healthcare.data.network.ModelRequest;
import io.github.ovso.healthcare.data.network.model.Model;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterDataModel;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterView;
import io.github.ovso.healthcare.ui.model.ModelPresenter;
import io.github.ovso.healthcare.ui.model.ModelPresenterImpl;
import io.github.ovso.healthcare.ui.model.adapter.ModelAdapter;
import io.github.ovso.healthcare.utils.ResourceProvider;
import io.github.ovso.healthcare.utils.SchedulersFacade;
import javax.inject.Singleton;

@Module public class ModelActivityModule {

  @Provides ModelPresenter provideModelPresenter(ModelPresenter.View view,
      ResourceProvider resourceProvider, ModelRequest modelRequest,
      SchedulersFacade schedulersFacade, BaseAdapterDataModel<Model> adapterDataModel) {
    return new ModelPresenterImpl(view, resourceProvider, modelRequest, schedulersFacade,
        adapterDataModel);
  }

  @Singleton @Provides ModelAdapter provideModelAdapter() {
    return new ModelAdapter();
  }

  @Provides BaseAdapterDataModel<Model> provideModelAdapterDataModel(ModelAdapter adapter) {
    return adapter;
  }

  @Provides BaseAdapterView provideModelAdapterView(ModelAdapter adapter) {
    return adapter;
  }
}