/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class StartReturn {

    private String name;
    private int size;
    @XmlElement(name = "quizz_id")
    private int quizzId;
    @XmlElement(name = "Error")
    private String error;

    public StartReturn() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getQuizzId() {
        return quizzId;
    }

    public void setQuizzId(int quizzId) {
        this.quizzId = quizzId;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
