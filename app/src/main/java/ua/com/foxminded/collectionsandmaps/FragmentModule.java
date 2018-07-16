package ua.com.foxminded.collectionsandmaps;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ua.com.foxminded.collectionsandmaps.factories.ListTasksFactory;
import ua.com.foxminded.collectionsandmaps.factories.MapTasksFactory;

@Module
public class FragmentModule {

    @Named("Map")
    @Provides
    TabContract.Presenter provideMapTabPresenter(MapTasksFactory tasksDataSource) {
        return new TabPresenter(tasksDataSource);
    }

    @Named("List")
    @Provides
    TabContract.Presenter provideListTabPresenter(ListTasksFactory tasksDataSource) {
        return new TabPresenter(tasksDataSource);
    }

    @Singleton
    @Provides
    MapTasksFactory provideMapTasksDataSource() {
        return new MapTasksFactory();
    }

    @Singleton
    @Provides
    ListTasksFactory provideListTasksDataSource() {
        return new ListTasksFactory();
    }
}
