package io.github.ovso.healthcare.ui.like.di;

import dagger.Binds;
import dagger.Module;
import io.github.ovso.healthcare.data.db.model.DiseaseEntity;
import io.github.ovso.healthcare.ui.base.listener.DiseaseOnItemClickListener;
import io.github.ovso.healthcare.ui.like.LikeActivity;
import io.github.ovso.healthcare.ui.like.LikePresenter;

@Module public abstract class LikeActivityViewModule {
  @Binds abstract LikePresenter.View bindLikePresenter(LikeActivity act);

  @Binds
  abstract DiseaseOnItemClickListener<DiseaseEntity> bindLikeItemClickListener(LikeActivity act);
}
