package ua.com.foxminded.collectionsandmaps.enums;


import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@StringDef({
        ListTasks.ADD_BEGIN,
        ListTasks.ADD_MIDDLE,
        ListTasks.ADD_END,
        ListTasks.SEARCH_VALUE,
        ListTasks.REMOVE_BEGIN,
        ListTasks.REMOVE_MIDDLE,
        ListTasks.REMOVE_END})
public @interface ListTasks {

    String ADD_BEGIN = "ADD_BEGIN";
    String ADD_MIDDLE = "ADD_MIDDLE";
    String ADD_END = "ADD_END";
    String SEARCH_VALUE = "SEARCH_VALUE";
    String REMOVE_BEGIN = "REMOVE_BEGIN";
    String REMOVE_MIDDLE = "REMOVE_MIDDLE";
    String REMOVE_END = "REMOVE_END";
    String[] tasks ={
            ADD_BEGIN,
            ADD_MIDDLE,
            ADD_END,
            SEARCH_VALUE,
            REMOVE_BEGIN,
            REMOVE_MIDDLE,
            REMOVE_END
    };
}
