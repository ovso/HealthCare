package io.github.ovso.healthcare.ui.detail;

import android.support.annotation.NonNull;
import io.github.ovso.healthcare.data.network.model.Car;
import java.util.List;

public interface DetailPresenter {
  void onCreate(@NonNull Car car);

  void onDestroy();

  interface View {

    void showBackButton();

    void showTitle(String title);

    void setupImages(List<String> image_urls);

    void setupPagenation(int size);

    void showPrice(String price);

    void showCarNumber(String number);

    void showMileage(String mileage);

    void showInitialRegistrationDate(String date);

    void showYear(String year);

    void showFuel(String fuel);

    void showStatusDisplay(String statusDisplay);

    void setupForSale();

    void setupOnSale();

    void setupSoldOut();
  }
}
