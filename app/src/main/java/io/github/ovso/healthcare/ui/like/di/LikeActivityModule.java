package io.github.ovso.healthcare.ui.like.di;

import dagger.Module;
import dagger.Provides;
import io.github.ovso.healthcare.data.db.AppDatabase;
import io.github.ovso.healthcare.data.db.model.DiseaseEntity;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterDataModel;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterView;
import io.github.ovso.healthcare.ui.base.listener.DiseaseOnItemClickListener;
import io.github.ovso.healthcare.ui.like.LikeActivity;
import io.github.ovso.healthcare.ui.like.LikePresenter;
import io.github.ovso.healthcare.ui.like.LikePresenterImpl;
import io.github.ovso.healthcare.ui.like.adapter.LikeAdapter;
import io.github.ovso.healthcare.utils.ResourceProvider;
import javax.inject.Singleton;

@Module public class LikeActivityModule {

  @Provides LikePresenter provideLikePresenter(LikePresenter.View view, LikeActivity act,
      AppDatabase database, ResourceProvider resProvider,
      BaseAdapterDataModel<DiseaseEntity> adapterDataModel) {
    LikePresenter presenter = new LikePresenterImpl(view, database, resProvider, adapterDataModel);
    act.getLifecycle().addObserver(presenter);
    return presenter;
  }

  @Singleton @Provides LikeAdapter provideLikeAdapter(DiseaseOnItemClickListener<DiseaseEntity> l) {
    return new LikeAdapter.Builder().setItemClickListener(l).build();
  }

  @Provides BaseAdapterDataModel<DiseaseEntity> provideAdapterDataModel(LikeAdapter adapter) {
    return adapter;
  }

  @Provides BaseAdapterView provideAdapterView(LikeAdapter adapter) {
    return adapter;
  }
}