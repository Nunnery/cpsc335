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
package me.topplethenun.cps335;

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

    public static void main(String[] args) {
        List<Natural> naturals = new ArrayList<Natural>();
        naturals.add(new Natural(0));
        naturals.add(new Natural(25));
        naturals.add(new Natural(234));

        Natural numericalMinimum = Utility.findMinimum(naturals);
        Natural alphaMinimum = Utility.findMinimum(naturals, Natural.alphaSorter());
        Natural numericalMaximum = Utility.findMaximum(naturals);
        Natural alphaMaximum = Utility.findMaximum(naturals, Natural.alphaSorter());

        System.out.println("numericalMinimum is " + numericalMinimum.value() + " and should be 0");
        System.out.println("alphaMinimum is " + alphaMinimum.value() + " and should be 0");
        System.out.println("numericalMaximum is " + numericalMaximum.value() + " and should be 234");
        System.out.println("alphaMaximum is " + alphaMaximum.value() + " and should be 25");
    }

}
