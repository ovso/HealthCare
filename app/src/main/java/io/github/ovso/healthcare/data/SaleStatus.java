package io.github.ovso.healthcare.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor public enum SaleStatus {
  FOR_SALE("for_sale"), ON_SALE("on_sale"), SOLD_OUT("sold_out");
  private String value;

  public static SaleStatus toStatus(String statusString) {
    for (SaleStatus status : SaleStatus.values()) {
      if (status.getValue().equals(statusString)) {
        return status;
      }
    }
    return SaleStatus.FOR_SALE;
  }
}
