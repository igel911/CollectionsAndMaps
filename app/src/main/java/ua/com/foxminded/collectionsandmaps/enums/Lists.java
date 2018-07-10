package ua.com.foxminded.collectionsandmaps.enums;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.SOURCE)
@StringDef({Lists.ARRAY_LIST, Lists.LINKED_LIST, Lists.COPY_ON_WRITE_ARRAY_LIST})
public @interface Lists {

    String ARRAY_LIST = "ARRAY_LIST";
    String LINKED_LIST = "LINKED_LIST";
    String COPY_ON_WRITE_ARRAY_LIST = "COPY_ON_WRITE_ARRAY_LIST";
    String[] types = {ARRAY_LIST, LINKED_LIST, COPY_ON_WRITE_ARRAY_LIST};
    int size = types.length;

}
