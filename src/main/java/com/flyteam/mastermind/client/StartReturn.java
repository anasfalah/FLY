/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flyteam.mastermind.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
    private int quizz_id;

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

    public int getQuizz_id() {
        return quizz_id;
    }

    public void setQuizz_id(int quizz_id) {
        this.quizz_id = quizz_id;
    }

}
