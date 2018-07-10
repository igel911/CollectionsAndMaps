package ua.com.foxminded.collectionsandmaps.enums;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@StringDef({Maps.TREE_MAP, Maps.HASH_MAP})
public @interface Maps {

    String TREE_MAP = "TREE_MAP";
    String HASH_MAP = "HASH_MAP";
    String[] types = {TREE_MAP, HASH_MAP};
    int size = types.length;
}
