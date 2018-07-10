package ua.com.foxminded.collectionsandmaps.enums;


import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@StringDef({MapTasks.ADD_NEW, MapTasks.SEARCH_KEY, MapTasks.REMOVE})
public @interface MapTasks {

    String ADD_NEW = "ADD_NEW";
    String SEARCH_KEY = "SEARCH_KEY";
    String REMOVE = "REMOVE";
    String[] tasks = {ADD_NEW, SEARCH_KEY, REMOVE};
}
