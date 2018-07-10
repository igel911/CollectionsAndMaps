package ua.com.foxminded.collectionsandmaps.enums;


import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@StringDef({Tabs.MAP_TAB, Tabs.LIST_TAB})
public @interface Tabs {

    String MAP_TAB = "MAP_TAB";
    String LIST_TAB = "LIST_TAB";
}
