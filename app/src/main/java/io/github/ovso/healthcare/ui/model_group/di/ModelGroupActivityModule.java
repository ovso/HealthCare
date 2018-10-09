package io.github.ovso.healthcare.ui.model_group.di;

import dagger.Module;
import dagger.Provides;
import io.github.ovso.healthcare.data.network.ModelGroupRequest;
import io.github.ovso.healthcare.data.network.model.ModelGroup;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterDataModel;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterView;
import io.github.ovso.healthcare.ui.model_group.ModelGroupPresenter;
import io.github.ovso.healthcare.ui.model_group.ModelGroupPresenterImpl;
import io.github.ovso.healthcare.ui.model_group.adapter.ModelGroupAdapter;
import io.github.ovso.healthcare.utils.ResourceProvider;
import io.github.ovso.healthcare.utils.SchedulersFacade;
import javax.inject.Singleton;

@Module public class ModelGroupActivityModule {

  @Provides ModelGroupPresenter provideModelGroupPresenter(ModelGroupPresenter.View view,
      ResourceProvider resourceProvider, ModelGroupRequest modelGroupRequest,
      SchedulersFacade schedulersFacade, BaseAdapterDataModel<ModelGroup> adapterDataModel) {
    return new ModelGroupPresenterImpl(view, resourceProvider, modelGroupRequest, schedulersFacade,
        adapterDataModel);
  }

  @Singleton @Provides ModelGroupAdapter provideModelGroupAdapter() {
    return new ModelGroupAdapter();
  }

  @Provides BaseAdapterDataModel<ModelGroup> provideModelGroupAdapterDataModel(ModelGroupAdapter adapter) {
    return adapter;
  }

  @Provides BaseAdapterView provideModelGroupAdapterView(ModelGroupAdapter adapter) {
    return adapter;
  }
}