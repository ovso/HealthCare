package io.github.ovso.healthcare.ui.brand.di;

import dagger.Module;
import dagger.Provides;
import io.github.ovso.healthcare.data.network.BrandRequest;
import io.github.ovso.healthcare.data.network.model.Brand;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterDataModel;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterView;
import io.github.ovso.healthcare.ui.brand.BrandPresenter;
import io.github.ovso.healthcare.ui.brand.BrandPresenterImpl;
import io.github.ovso.healthcare.ui.brand.adapter.BrandAdapter;
import io.github.ovso.healthcare.utils.ResourceProvider;
import io.github.ovso.healthcare.utils.SchedulersFacade;
import javax.inject.Singleton;

@Module public class BrandActivityModule {

  @Provides BrandPresenter provideBrandPresenter(BrandPresenter.View view,
      ResourceProvider resourceProvider, BrandRequest brandRequest,
      SchedulersFacade schedulersFacade, BaseAdapterDataModel<Brand> adapterDataModel) {
    return new BrandPresenterImpl(view, resourceProvider, brandRequest, schedulersFacade,
        adapterDataModel);
  }

  @Singleton @Provides BrandAdapter provideBrandAdapter() {
    return new BrandAdapter();
  }

  @Provides BaseAdapterDataModel<Brand> provideMainAdapterDataModel(BrandAdapter adapter) {
    return adapter;
  }

  @Provides BaseAdapterView provideBrandAdapterView(BrandAdapter adapter) {
    return adapter;
  }
}