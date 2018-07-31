/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flyteam.mastermind.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jtapiat
 */
public class Element {
    
    public static enum State {
        WELL_POSITIONNED, NOT_WELL_POSITIONNED, NOT_PRESENT
    }

    int position;
    int number;
    List<State> possibleStates = new ArrayList<>();

    public Element() {
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<State> getPossibleStates() {
        return possibleStates;
    }

    public void setPossibleStates(List<State> possibleStates) {
        this.possibleStates = possibleStates;
    }
}
