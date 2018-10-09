package io.github.ovso.healthcare.ui.model;

import android.content.Intent;
import android.support.annotation.NonNull;
import io.github.ovso.healthcare.R;
import io.github.ovso.healthcare.data.network.ModelRequest;
import io.github.ovso.healthcare.data.network.model.Model;
import io.github.ovso.healthcare.data.network.model.ModelGroupDetail;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterDataModel;
import io.github.ovso.healthcare.utils.ResourceProvider;
import io.github.ovso.healthcare.utils.SchedulersFacade;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class ModelPresenterImpl implements ModelPresenter {

  private View view;
  private ModelRequest modelRequest;
  private ResourceProvider resourceProvider;
  private SchedulersFacade schedulersFacade;
  private CompositeDisposable compositeDisposable = new CompositeDisposable();
  private BaseAdapterDataModel<Model> adapterDataModel;

  public ModelPresenterImpl(View $view, ResourceProvider $resourceProvider,
      ModelRequest $modelRequest, SchedulersFacade $schedulersFacade,
      BaseAdapterDataModel<Model> $adapterDataModel) {
    view = $view;
    modelRequest = $modelRequest;
    resourceProvider = $resourceProvider;
    schedulersFacade = $schedulersFacade;
    adapterDataModel = $adapterDataModel;
  }

  @Override public void onCreate(@NonNull Intent intent) {
    view.setTitle(resourceProvider.getString(R.string.model_select));
    view.showBackButton();
    view.setupRecyclerView();
    Disposable disposable = modelRequest.getModel(intent.getIntExtra("id", 0))
        .subscribeOn(schedulersFacade.io())
        .observeOn(schedulersFacade.ui())
        .subscribe(
            new Consumer<ModelGroupDetail>() {
              @Override public void accept(ModelGroupDetail detail) throws Exception {
                adapterDataModel.addAll(detail.getModels());
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
    if (data instanceof Model) {
      int id = ((Model) data).getId();
      String name = ((Model) data).getName();
      view.navigateToMain(id, name);
    }
  }
}
