package io.github.ovso.healthcare.ui.detail.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import io.github.ovso.healthcare.R;

public class DetailFragment extends Fragment {

  @BindView(R.id.image_view) ImageView imageView;

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_defail, container, false);
  }

  @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    Glide.with(this).load(getArguments().getString("image_url")).into(imageView);
  }

  @Override public void onDetach() {
    super.onDetach();
  }

  public static DetailFragment newInstance(Bundle args) {
    DetailFragment f = new DetailFragment();
    f.setArguments(args);
    return f;
  }
}
