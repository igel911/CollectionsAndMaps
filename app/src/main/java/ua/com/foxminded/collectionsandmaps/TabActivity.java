package ua.com.foxminded.collectionsandmaps;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.com.foxminded.collectionsandmaps.adapters.CustomFragmentPagerAdapter;

public class TabActivity extends AppCompatActivity {

    @BindView(R.id.viewPagerMain) ViewPager viewPager;
    @BindView(R.id.tabsLayoutMain) TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        CustomFragmentPagerAdapter adapter = new CustomFragmentPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ListTabFragment(), getString(R.string.tab_collections));
        adapter.addFragment(new MapTabFragment(), getString(R.string.tab_maps));
        viewPager.setAdapter(adapter);
    }
}
