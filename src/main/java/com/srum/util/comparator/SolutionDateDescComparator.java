package com.srum.util.comparator;

import com.srum.entity.Solution;

import java.util.Comparator;

/**
 * @author ThoNN1
 */
public class SolutionDateDescComparator implements Comparator<Solution> {
    @Override
    public int compare(Solution o1, Solution o2) {
        return o2.getDate().compareTo(o1.getDate());
    }
}
