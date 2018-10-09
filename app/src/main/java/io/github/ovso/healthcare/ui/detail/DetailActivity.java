package io.github.ovso.healthcare.ui.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatRadioButton;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnPageChange;
import io.github.ovso.healthcare.R;
import io.github.ovso.healthcare.data.network.model.Car;
import io.github.ovso.healthcare.ui.base.BaseActivity;
import io.github.ovso.healthcare.ui.detail.fragment.DetailAdapter;
import io.github.ovso.healthcare.ui.detail.fragment.DetailFragment;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class DetailActivity extends BaseActivity implements DetailPresenter.View {
  @Inject DetailPresenter presenter;
  @BindView(R.id.view_pager) ViewPager viewPager;
  @BindView(R.id.pagination_radio_group) RadioGroup paginationRadioGroup;
  @BindView(R.id.price_text_view) TextView priceTextView;
  @BindView(R.id.status_display_text_view) TextView statusDisplayTextView;
  @BindView(R.id.car_number_text_view) TextView carNumberTextView;
  @BindView(R.id.mileage_text_view) TextView mileageTextView;
  @BindView(R.id.initial_registration_date_text_view) TextView initialRegistrationDateTextView;
  @BindView(R.id.year_text_view) TextView yearTextView;
  @BindView(R.id.fuel_text_view) TextView fuelTextView;
  @BindView(R.id.button) Button button;

  @Override protected int getLayoutResID() {
    return R.layout.activity_detail;
  }

  @Override protected void onCreated(@Nullable Bundle savedInstanceState) {
    if (getIntent().hasExtra("data")) {
      Car car = getIntent().getParcelableExtra("data");
      presenter.onCreate(car);
    }
  }

  @Override public void showBackButton() {
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  @Override public void showTitle(String title) {
    setTitle(title);
  }

  @Override public void setupImages(List<String> image_urls) {
    List<Fragment> items = new ArrayList<>();
    for (String image_url : image_urls) {
      Bundle bundle = new Bundle();
      bundle.putString("image_url", image_url);
      items.add(DetailFragment.newInstance(bundle));
    }
    viewPager.setAdapter(new DetailAdapter(getSupportFragmentManager(), items));
  }

  @Override public void setupPagenation(int size) {
    for (int i = 0; i < size; i++) {
      AppCompatRadioButton radioButton = new AppCompatRadioButton(this);
      radioButton.setClickable(false);
      paginationRadioGroup.addView(radioButton);
    }
    if (size > 0) {
      paginationRadioGroup.check(paginationRadioGroup.getChildAt(0).getId());
    }
  }

  @Override public void showPrice(String price) {
    priceTextView.setText(price);
  }

  @Override public void showCarNumber(String number) {
    carNumberTextView.setText(number);
  }

  @Override public void showMileage(String mileage) {
    mileageTextView.setText(mileage);
  }

  @Override public void showInitialRegistrationDate(String date) {
    initialRegistrationDateTextView.setText(date);
  }

  @Override public void showYear(String year) {
    yearTextView.setText(year);
  }

  @Override public void showFuel(String fuel) {
    fuelTextView.setText(fuel);
  }

  @Override public void showStatusDisplay(String statusDisplay) {
    statusDisplayTextView.setText(statusDisplay);
  }

  @Override public void setupForSale() {
    button.setBackgroundResource(R.drawable.selector_status_1);
    statusDisplayTextView.setTextColor(ContextCompat.getColor(this,android.R.color.holo_blue_dark));
    priceTextView.setTextColor(ContextCompat.getColor(this, android.R.color.black));
  }

  @Override public void setupOnSale() {
    button.setBackgroundResource(R.drawable.selector_status_2);
    statusDisplayTextView.setTextColor(ContextCompat.getColor(this,android.R.color.holo_green_dark));
    priceTextView.setTextColor(ContextCompat.getColor(this, android.R.color.black));
  }

  @Override public void setupSoldOut() {
    button.setBackgroundResource(R.drawable.selector_status_3);
    statusDisplayTextView.setTextColor(ContextCompat.getColor(this,android.R.color.darker_gray));
  }

  @OnPageChange(value = R.id.view_pager, callback = OnPageChange.Callback.PAGE_SELECTED)
  void onPageChange(int position) {
    paginationRadioGroup.check(paginationRadioGroup.getChildAt(position).getId());
  }

  @Override public boolean isTitle() {
    return true;
  }

  @Override protected void onDestroy() {
    presenter.onDestroy();
    super.onDestroy();
  }
}