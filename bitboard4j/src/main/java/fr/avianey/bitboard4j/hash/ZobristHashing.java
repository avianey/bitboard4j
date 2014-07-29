package fr.avianey.bitboard4j.hash;

import java.util.HashMap;

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
 * A simple implementation of the <a href="http://en.wikipedia.org/wiki/Zobrist_hashing">Zobrist Hash</a>.
 * As {@link Object#hashCode()} is limited to 32-bits int precision, be aware of the highest risk of collision
 * compared to a 64-bits long based Zobrist hash implementation. This ensure compatibility with java {@link HashMap}. 
 * 
 * @author antoine vianey
 */
public class ZobristHashing {

    private int hash;
    private final int[][] bitStrings;
    
    /**
     * Initialize values for the given number of pieces and the given number of positions
     * @param pieces
     * @param positions
     */
    public ZobristHashing(int pieces, int positions) {
        bitStrings = new int[pieces][positions];
        for (int i = 0; i < pieces; i++) {
            for (int j = 0; j < positions; j++) {
                bitStrings[i][j] = (int) (((long) (Math.random() * Long.MAX_VALUE)) & 0xFFFFFFFF);
            }
        }
        hash = 0;
    }
    
    /**
     * Initialize values from an existing instance of {@link ZobristHashing}
     * @param from
     */
    public ZobristHashing(ZobristHashing from) {
    	this.bitStrings = from.bitStrings;
    	this.hash = from.hash;
    }

    public void reset() {
        hash = 0;
    }
    
    /**
     * Compute the resulting hash after the given move.<br/>
     * Same as {@link #remove(int, int)} and {@link #xor(int, int)}.
     * @param piece
     * @param position
     * @return
     *      the Zobrist hash value
     */
    public int add(int piece, int position) {
        return xor(piece, position);
    }
    
    /**
     * Compute the resulting hash after the given move.<br/>
     * Same as {@link #add(int, int)} and {@link #xor(int, int)}.
     * @param piece
     * @param position
     * @return
     *      the Zobrist hash value
     */
    public int remove(int piece, int position) {
        return xor(piece, position);
    }

    /**
     * Compute the resulting hash after the given move.<br/>
     * XOR the bit string of the piece at the given position with the current hash value.
     * @param piece
     * @param position
     * @return
     *      the Zobrist hash value
     */
    public int xor(int piece, int position) {
        hash = hash ^ bitStrings[piece][position];
        return hash;
    }
    
    /**
     * Set the value of the Zobrist hash
     * @param hash
     * 		the hash value to set
     */
    public void set(int hash) {
    	this.hash = hash;
    }
    
    @Override
    public int hashCode() {
        return hash;
    }
    
}
