package ua.com.foxminded.collectionsandmaps;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.com.foxminded.collectionsandmaps.adapters.CustomFragmentPagerAdapter;
import ua.com.foxminded.collectionsandmaps.tasks.TasksFactory;

public class TabActivity extends AppCompatActivity {

    private ListTabFragment mListTabFragment;
    private MapTabFragment mMapTabFragment;

    @BindView(R.id.viewPagerMain) ViewPager viewPager;
    @BindView(R.id.tabsLayoutMain) TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mListTabFragment = new ListTabFragment();
        mMapTabFragment = new MapTabFragment();
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        new TabPresenter(mMapTabFragment, TasksFactory.getInstance());
        new TabPresenter(mListTabFragment, TasksFactory.getInstance());
    }

    private void setupViewPager(ViewPager viewPager) {
        CustomFragmentPagerAdapter adapter = new CustomFragmentPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(mListTabFragment, getString(R.string.tab_collections));
        adapter.addFragment(mMapTabFragment, getString(R.string.tab_maps));
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mListTabFragment = null;
        mMapTabFragment = null;
    }
}
