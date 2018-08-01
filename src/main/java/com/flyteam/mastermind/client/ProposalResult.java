package com.flyteam.mastermind.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jtapiat
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ProposalResult {

    @XmlElement(name = "wrong_place")
    private int wrongPlace;
    private int good;
    @XmlElement(name = "Error")
    private String error;

    public ProposalResult() {
    }

    public int getWrongPlace() {
        return wrongPlace;
    }

    public void setWrongPlace(int wrongPlace) {
        this.wrongPlace = wrongPlace;
    }

    public int getGood() {
        return good;
    }

    public void setGood(int good) {
        this.good = good;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
