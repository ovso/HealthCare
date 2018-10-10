package io.github.ovso.healthcare.ui.brand;

import android.content.Intent;
import android.support.annotation.NonNull;
import io.github.ovso.healthcare.R;
import io.github.ovso.healthcare.data.network.BrandRequest;
import io.github.ovso.healthcare.data.network.model.SearchItem;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterDataModel;
import io.github.ovso.healthcare.utils.ResourceProvider;
import io.github.ovso.healthcare.utils.SchedulersFacade;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.List;

public class BrandPresenterImpl implements BrandPresenter {

  private BrandPresenter.View view;
  private BrandRequest brandRequest;
  private ResourceProvider resourceProvider;
  private SchedulersFacade schedulersFacade;
  private CompositeDisposable compositeDisposable = new CompositeDisposable();
  private BaseAdapterDataModel<SearchItem> adapterDataModel;

  public BrandPresenterImpl(BrandPresenter.View $view, ResourceProvider $resourceProvider,
      BrandRequest $brandRequest, SchedulersFacade $schedulersFacade,
      BaseAdapterDataModel<SearchItem> $adapterDataModel) {
    view = $view;
    brandRequest = $brandRequest;
    resourceProvider = $resourceProvider;
    schedulersFacade = $schedulersFacade;
    adapterDataModel = $adapterDataModel;
  }

  @Override public void onCreate(@NonNull Intent intent) {
    view.setTitle(resourceProvider.getString(R.string.brand_select));
    view.showBackButton();
    view.setupRecyclerView();
    Disposable disposable = brandRequest.getBrands()
        .subscribeOn(schedulersFacade.io())
        .observeOn(schedulersFacade.ui())
        .subscribe(
            new Consumer<List<SearchItem>>() {
              @Override public void accept(List<SearchItem> items) throws Exception {
                adapterDataModel.addAll(items);
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
    if(data instanceof SearchItem) {
      view.navigateToModelGroup(((SearchItem) data).getId());
    }

  }
}
