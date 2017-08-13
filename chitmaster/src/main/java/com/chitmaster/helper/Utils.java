package com.chitmaster.helper;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Utils {
	
	public static <T> List<T> toList(T... values) {
        List<T> list = new ArrayList<T>(values.length);
        Collections.addAll(list, values);
        return list;
    }
}
