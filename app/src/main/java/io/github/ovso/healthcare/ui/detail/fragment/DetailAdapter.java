package io.github.ovso.healthcare.ui.detail.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;
import java.util.List;

public class DetailAdapter extends FragmentPagerAdapter {
  private List<Fragment> items;

  public DetailAdapter(FragmentManager fm, List<Fragment> $items) {
    super(fm);
    items = $items;
  }

  @Override public Fragment getItem(int position) {
    return items.get(position);
  }

  @Override public int getCount() {
    return items.size();
  }

  @Override public void destroyItem(ViewGroup container, int position, Object object) {
    //super.destroyItem(container, position, object);
  }
}