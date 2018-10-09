package io.github.ovso.healthcare.data.network.model;

import java.util.List;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString public class CarDetail {
  private int id;
  private List<String> image_urls;
  private String full_name;
  private int price;
  private int discounted_price;
  private String status;
  private String status_display;
  private String car_number;
  private int mileage;
  private String initial_registration_date;
  private int year;
  private String fuel;
}
