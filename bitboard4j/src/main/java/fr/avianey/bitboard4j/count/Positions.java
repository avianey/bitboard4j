package fr.avianey.bitboard4j.count;

import java.util.Collection;
import java.util.LinkedList;



/*
 * This file is part of bitboard4j.
 * <https://github.com/avianey/bitboard4j>
 *  
 * Copyright (C) 2014 Antoine Vianey
 * 
 * bitboard4j is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * bitboard4j is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with bitboard4j. If not, see <http://www.gnu.org/licenses/lgpl.html>
 */

/**
 * Retrieve positions of bits inside a word or a long word.
 * <ul>
 * <li>{@link #ofSetBits(long)} : indexes of set bits </li>
 * <li>{@link #ofUnsetBits(long)} : indexes of unset bits</li>
 * </ul>
 * 
 * @author antoine vianey
 */
public class Positions {

    /**
     * Get the {@link Collection} of set (1) bits inside a word or long word
     * @param i
     * @return
     * 		The not null {@link Collection} of the indexes of set bits.
     * 		The {@link Collection} implementation is an {@link LinkedList}.
     */
    public static Collection<Integer> ofSetBits(long i) {
    	final Collection<Integer> c = new LinkedList<Integer>();
        int k = 0;
        while (i != 0) {
            if ((i & 1L) != 0) {
                c.add(k);
            }
            k++;
            i >>>= 1;
        }
        return c;
    }

    /**
     * Get the {@link Collection} of unset (0) bits inside a word or long word
     * @param i
     * @return
     * 		The not null {@link Collection} of the indexes of set bits.
     * 		The {@link Collection} implementation is an {@link LinkedList}.
     */
    public static Collection<Integer> ofUnsetBits(long i) {
    	final Collection<Integer> c = new LinkedList<Integer>();
        int k = 0;
        while (i != 0) {
            if ((i & 1L) == 0) {
                c.add(k);
            }
            k++;
            i >>>= 1;
        }
        return c;
    }
    
}
