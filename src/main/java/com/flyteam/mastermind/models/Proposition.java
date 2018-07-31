/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flyteam.mastermind.models;

/**
 *
 * @author jtapiat
 */
public class Proposition {

    int wellPositionned = 0;
    int notWellPositionned = 0;
    int invalid = 0;
    Element[] elements;

    public Proposition() {
    }

    public int getWellPositionned() {
        return wellPositionned;
    }

    public void setWellPositionned(int wellPositionned) {
        this.wellPositionned = wellPositionned;
    }

    public int getNotWellPositionned() {
        return notWellPositionned;
    }

    public void setNotWellPositionned(int notWellPositionned) {
        this.notWellPositionned = notWellPositionned;
    }

    public int getInvalid() {
        return invalid;
    }

    public void setInvalid(int invalid) {
        this.invalid = invalid;
    }

    public Element[] getElements() {
        return elements;
    }

    public void setElements(Element[] elements) {
        this.elements = elements;
    }
    
}
