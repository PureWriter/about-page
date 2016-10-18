package me.drakeet.support.about;

import android.support.annotation.NonNull;
import me.drakeet.multitype.Item;

/**
 * @author drakeet
 */
public class Category implements Item {

    @NonNull public final String value;


    public Category(@NonNull String value) {this.value = value;}
}
