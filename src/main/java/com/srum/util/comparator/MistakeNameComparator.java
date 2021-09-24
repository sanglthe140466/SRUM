package com.srum.util.comparator;


import com.srum.entity.Mistake;

import java.util.Comparator;

public class MistakeNameComparator implements Comparator<Mistake> {

    @Override
    public int compare(Mistake o1, Mistake o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
