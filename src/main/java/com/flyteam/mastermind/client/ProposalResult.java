package com.flyteam.mastermind.client;

/**
 *
 * @author jtapiat
 */
public class ProposalResult {

    private int wrong_place;
    private int good;

    public ProposalResult() {
    }

    public int getWrong_place() {
        return wrong_place;
    }

    public void setWrong_place(int wrong_place) {
        this.wrong_place = wrong_place;
    }

    public int getGood() {
        return good;
    }

    public void setGood(int good) {
        this.good = good;
    }
}
