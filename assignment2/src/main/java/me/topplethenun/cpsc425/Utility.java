/*
 * This file is part of cspc335, licensed under the Fair License.
 *
 * (C) 2015 Richard Harrah <richard.harrah.13@cnu.edu>
 *
 * Usage of the works is permitted provided that this instrument is retained with the works, so that any entity that uses
 * the works is notified of this instrument.
 *
 * DISCLAIMER: THE WORKS ARE WITHOUT WARRANTY.
 */
package me.topplethenun.cpsc425;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class Utility {

    private Utility() {
        // do nothing
    }

    public static Natural findMinimum(List<Natural> list) {
        if (list == null) {
            throw new IllegalArgumentException("list cannot be null");
        }
        if (list.isEmpty()) {
            throw new IllegalArgumentException("list cannot be empty");
        }
        List<Natural> copy = new ArrayList<Natural>(list);
        Collections.sort(copy);
        return copy.get(0);
    }

    public static Natural findMaximum(List<Natural> list) {
        if (list == null) {
            throw new IllegalArgumentException("list cannot be null");
        }
        if (list.isEmpty()) {
            throw new IllegalArgumentException("list cannot be empty");
        }
        List<Natural> copy = new ArrayList<Natural>(list);
        Collections.sort(copy);
        return copy.get(copy.size() - 1);
    }

    public static Natural findMinimum(List<Natural> list, Comparator<Natural> comparator) {
        if (list == null) {
            throw new IllegalArgumentException("list cannot be null");
        }
        if (list.isEmpty()) {
            throw new IllegalArgumentException("list cannot be empty");
        }
        List<Natural> copy = new ArrayList<Natural>(list);
        Collections.sort(copy, comparator);
        return copy.get(0);
    }

    public static Natural findMaximum(List<Natural> list, Comparator<Natural> comparator) {
        if (list == null) {
            throw new IllegalArgumentException("list cannot be null");
        }
        if (list.isEmpty()) {
            throw new IllegalArgumentException("list cannot be empty");
        }
        List<Natural> copy = new ArrayList<Natural>(list);
        Collections.sort(copy, comparator);
        return copy.get(copy.size() - 1);
    }

}
