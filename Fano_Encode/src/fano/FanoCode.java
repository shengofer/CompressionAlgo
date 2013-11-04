package fano;

public  class FanoCode implements Comparable<FanoCode> {
    private int symbol;
    private String encode;
    private int prob;
    public FanoCode(int prob,int sym,String enc)
    {
        this.prob = prob;
        this.symbol = sym;
        this.encode = enc;
    }

    public int getSymbol() {
        return symbol;
    }

    public String getEncode() {
        return encode;
    }

    public int getProb() {
        return prob;
    }

    @Override
    public int compareTo(FanoCode o) {
        if (this.prob < o.prob) {
            return -1;
        } else if (this.prob > o.prob) {
            return 1;
        } else {
            return 0;
        }
    }

}