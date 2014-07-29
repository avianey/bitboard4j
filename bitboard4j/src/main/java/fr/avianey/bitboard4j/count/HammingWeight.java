package fr.avianey.bitboard4j.count;


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
 * Compute the number of bit sets inside a word or a long word.<br/>
 * <a href="http://en.wikipedia.org/wiki/Hamming_weight">Hamming weight</a>
 * 
 * @author antoine vianey
 */
public class HammingWeight {

    /**
     * Count number of bits set to one in the given long (64-bit integers).
     * @param i
     * @return
     */
    public static long ofSetBits64_1(long i) {
        i = i - ((i >>> 1) & 0x5555555555555555L);
        i = (i & 0x3333333333333333L) + ((i >>> 2) & 0x3333333333333333L);
        i = ((i + (i >>> 4)) & 0x0F0F0F0F0F0F0F0FL);
        return (i * (0x0101010101010101L)) >>> 56;
    }
    
    /**
     * Count number of bits set to one in the given long (64-bit integers).<br/>
     * Faster when only few bits are set to one.
     * @param i
     * @return
     */
    public static long ofSetBits64_2(long i) {
        int count = 0;
        while (i != 0) {
        	i &= (i - 1L);
        	count++;
        }
        return count;
    }

    /**
     * Count number of bits set to one in the given int (32-bit integers).
     * @param i
     * @return
     */
    public static int ofSetBits32_1(int i) {
        i = i - ((i >>> 1) & 0x55555555);
        i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
        i = ((i + (i >>> 4)) & 0x0F0F0F0F);
        return (i * (0x01010101)) >>> 24;
    }

    /**
     * Count number of bits set to one in the given int (32-bit integers).<br/>
     * Faster when only few bits are set to one.
     * @param i
     * @return
     */
    public static long ofSetBits32_2(int i) {
        int count = 0;
        while (i != 0) {
        	i &= i-1;
        	count++;
        }
        return count;
    }

}