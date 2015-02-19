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

import java.util.Comparator;

public final class Natural implements Comparable<Natural> {

    private final int value;

    public Natural(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("value must be 0 or greater");
        }
        this.value = value;
    }

    public int value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Natural && value() == ((Natural) o).value();
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }

    @Override
    public int compareTo(Natural o) {
        if (o == null) {
            return 1;
        }
        return Integer.compare(value, o.value);
    }

    public static Comparator<Natural> comparatorByNumber() {
        return new Comparator<Natural>() {
            @Override
            public int compare(Natural o1, Natural o2) {
                if (o1 == null) {
                    throw new IllegalArgumentException("o1 cannot be null");
                }
                if (o2 == null) {
                    throw new IllegalArgumentException("o2 cannot be null");
                }
                return o1.compareTo(o2);
            }
        };
    }

    public static Comparator<Natural> comparatorByAlpha() {
        return new Comparator<Natural>() {
            @Override
            public int compare(Natural o1, Natural o2) {
                if (o1 == null) {
                    throw new IllegalArgumentException("o1 cannot be null");
                }
                if (o2 == null) {
                    throw new IllegalArgumentException("o2 cannot be null");
                }
                return (""+o1.value()).compareTo("" + o2.value());
            }
        };
    }

}
