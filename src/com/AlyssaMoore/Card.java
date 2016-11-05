package com.AlyssaMoore;

public class Card {

    int value;
    String suit;

    public Card(int v, String s) {
        this.value = v;
        this.suit = s;
    }

    public int getRank() {
        return value;
    }

    public String getSuit() {
        return suit;
    }
}