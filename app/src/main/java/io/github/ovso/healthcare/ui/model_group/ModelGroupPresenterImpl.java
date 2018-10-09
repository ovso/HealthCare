package io.github.ovso.healthcare.ui.model_group;

import android.content.Intent;
import android.support.annotation.NonNull;
import io.github.ovso.healthcare.R;
import io.github.ovso.healthcare.data.network.ModelGroupRequest;
import io.github.ovso.healthcare.data.network.model.BrandGroup;
import io.github.ovso.healthcare.data.network.model.ModelGroup;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterDataModel;
import io.github.ovso.healthcare.utils.ResourceProvider;
import io.github.ovso.healthcare.utils.SchedulersFacade;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class ModelGroupPresenterImpl implements ModelGroupPresenter {

  private View view;
  private ModelGroupRequest modelGroupRequest;
  private ResourceProvider resourceProvider;
  private SchedulersFacade schedulersFacade;
  private CompositeDisposable compositeDisposable = new CompositeDisposable();
  private BaseAdapterDataModel<ModelGroup> adapterDataModel;

  public ModelGroupPresenterImpl(View $view, ResourceProvider $resourceProvider,
      ModelGroupRequest $modelGroupRequest, SchedulersFacade $schedulersFacade,
      BaseAdapterDataModel<ModelGroup> $adapterDataModel) {
    view = $view;
    modelGroupRequest = $modelGroupRequest;
    resourceProvider = $resourceProvider;
    schedulersFacade = $schedulersFacade;
    adapterDataModel = $adapterDataModel;
  }

  @Override public void onCreate(@NonNull Intent intent) {
    view.setTitle(resourceProvider.getString(R.string.model_group_select));
    view.showBackButton();
    view.setupRecyclerView();
    Disposable disposable = modelGroupRequest.getBrandDetail(intent.getIntExtra("id", 0))
        .subscribeOn(schedulersFacade.io())
        .observeOn(schedulersFacade.ui())
        .subscribe(
            new Consumer<BrandGroup>() {
              @Override public void accept(BrandGroup brandDetail) throws Exception {
                adapterDataModel.addAll(brandDetail.getModel_groups());
                view.refresh();
              }
            }, new Consumer<Throwable>() {
              @Override public void accept(Throwable throwable) throws Exception {

              }
            });
    compositeDisposable.add(disposable);
  }

  @Override public void onDestroy() {
    compositeDisposable.clear();
  }

  @Override public void onListItemClick(Object data) {
    if(data instanceof ModelGroup) {
      view.navigateToModel(((ModelGroup) data).getId());
    }

  }
}
