package io.github.ovso.healthcare.data.network.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import io.github.ovso.healthcare.R;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString public class Car implements Parcelable {
  private int id;
  private String main_image_url;
  private String status;
  private String status_display;
  private String model_part_name;
  private String grade_part_name;
  private int year;
  private int mileage;
  private int price;
  private int discounted_price;
  private String absolute_url;

  protected Car(Parcel in) {
    id = in.readInt();
    main_image_url = in.readString();
    status = in.readString();
    status_display = in.readString();
    model_part_name = in.readString();
    grade_part_name = in.readString();
    year = in.readInt();
    mileage = in.readInt();
    price = in.readInt();
    discounted_price = in.readInt();
    absolute_url = in.readString();
  }

  public static final Creator<Car> CREATOR = new Creator<Car>() {
    @Override
    public Car createFromParcel(Parcel in) {
      return new Car(in);
    }

    @Override
    public Car[] newArray(int size) {
      return new Car[size];
    }
  };

  public static String toDistance(Context context, int mileage) {
    return ((double) (mileage / 10000)) + context.getString(R.string.distance) + context.getString(
        R.string.km);
  }

  public static String toYear(Context context, int year) {
    return year + context.getString(R.string.year);
  }

  public static String toPrice(Context context, int price) {
    return String.format("%,d", price) + context.getString(R.string.price);
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(id);
    dest.writeString(main_image_url);
    dest.writeString(status);
    dest.writeString(status_display);
    dest.writeString(model_part_name);
    dest.writeString(grade_part_name);
    dest.writeInt(year);
    dest.writeInt(mileage);
    dest.writeInt(price);
    dest.writeInt(discounted_price);
    dest.writeString(absolute_url);
  }
}
