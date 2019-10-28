package org.improving;

public class Card {
    private String rank;
    private String suite;

    public Card() {
    }

    public Card(String rank, String suite) {
        this.rank = rank;
        this.suite = suite;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    @Override
    public String toString() {
        return "" + rank.toString() + " of " + suite.toString();
    }
}
