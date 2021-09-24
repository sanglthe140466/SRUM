package com.srum.util.comparator;

import com.srum.entity.Issue;

import java.util.Comparator;

/**
 * @author ThoNN1
 */
public class IssueDateDescComparator implements Comparator<Issue> {
    @Override
    public int compare(Issue o1, Issue o2) {
        return o2.getDate().compareTo(o1.getDate());
    }
}
