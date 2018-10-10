package io.github.ovso.healthcare.data.network.model;

import java.util.List;
import lombok.Getter;

@Getter public class BrandGroup extends SearchItem {
  private List<ModelGroup> model_groups;
}
