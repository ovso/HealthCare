package io.github.ovso.healthcare.ui.detail;

import android.content.Context;
import android.support.annotation.NonNull;
import io.github.ovso.healthcare.App;
import io.github.ovso.healthcare.data.SaleStatus;
import io.github.ovso.healthcare.data.network.DetailRequest;
import io.github.ovso.healthcare.data.network.model.Car;
import io.github.ovso.healthcare.data.network.model.CarDetail;
import io.github.ovso.healthcare.utils.ResourceProvider;
import io.github.ovso.healthcare.utils.SchedulersFacade;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class DetailPresenterImpl implements DetailPresenter {
  private DetailPresenter.View view;
  private ResourceProvider resourceProvider;
  private CompositeDisposable compositeDisposable = new CompositeDisposable();
  private DetailRequest request;
  private SchedulersFacade schedulersFacade;

  public DetailPresenterImpl(DetailPresenter.View $view, ResourceProvider $resourceProvider,
      DetailRequest $request, SchedulersFacade $scSchedulersFacade) {
    view = $view;
    resourceProvider = $resourceProvider;
    request = $request;
    schedulersFacade = $scSchedulersFacade;
  }

  @Override public void onCreate(@NonNull Car car) {
    Context context = App.getInstance();
    view.showBackButton();
    view.showTitle(car.getModel_part_name());
    Disposable disposable = request.getCarDetail(car.getId())
        .subscribeOn(schedulersFacade.io())
        .observeOn(schedulersFacade.ui())
        .subscribe(new Consumer<CarDetail>() {
          @Override public void accept(CarDetail detail) throws Exception {
            view.setupImages(detail.getImage_urls());
            view.setupPagenation(detail.getImage_urls().size());
            view.showPrice(Car.toPrice(context, detail.getPrice()));
            view.showCarNumber(detail.getCar_number());
            view.showMileage(Car.toDistance(context, detail.getMileage()));
            view.showInitialRegistrationDate(detail.getInitial_registration_date());
            view.showYear(Car.toYear(context, detail.getYear()));
            view.showFuel(detail.getFuel());
            view.showStatusDisplay(detail.getStatus_display());

            switch (SaleStatus.toStatus(detail.getStatus())) {
              case FOR_SALE:
                view.setupForSale();
                break;
              case ON_SALE:
                view.setupOnSale();
                break;
              case SOLD_OUT:
                view.setupSoldOut();
                break;
            }
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
}
