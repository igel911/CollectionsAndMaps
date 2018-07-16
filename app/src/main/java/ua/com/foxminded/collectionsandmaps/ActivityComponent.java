package ua.com.foxminded.collectionsandmaps;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {FragmentModule.class})
public interface ActivityComponent {

    void injectMapTabFragment(MapTabFragment fragment);

    void injectListTabFragment(ListTabFragment fragment);
}
