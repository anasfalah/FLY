/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flyteam.mastermind;

import com.flyteam.mastermind.client.ApiClient;
import com.flyteam.mastermind.client.ProposalResult;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jtapiat
 */
public class SimpleElementsFinder {

    public static class ElementChecker implements Runnable {

        char element;
        int size;
        int found;
        ApiClient client;

        public ElementChecker(char elementToCheck, ApiClient client, int size) {
            this.element = elementToCheck;
            this.client = client;
            this.size = size;
        }

        @Override
        public void run() {
            try {
                String test = "";
                for (int j = 0; j < size; j++) {
                    test += element;
                }
                ProposalResult apiResult = client.test(test);
                this.found = apiResult.getGood();
            } catch (Exception e) {
                throw new Error("Problem while checking element " + this.element, e);
            }
        }

    }

    public String find(ApiClient client, int size) throws Exception {
        List<ElementChecker> runnables = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ElementChecker r = new ElementChecker(Character.forDigit(i, 10), client, size);
            runnables.add(r);
            Thread t = new Thread(r);
            threads.add(t);
            t.start();
        }
        Boolean isThreadRunning;
        do {
            isThreadRunning = false;
            for (Thread t : threads) {
                isThreadRunning = t.isAlive();
                if (isThreadRunning) {
                    break;
                }
            }
        } while (isThreadRunning);

        String res = "";
        for (ElementChecker runnable : runnables) {
            for (int k = 0; k < runnable.found; k++) {
                res += runnable.element;
            }
        }
        return res;
    }
    
    public static void main(String[] args) throws Exception {
        ApiClient client = new ApiClient();
        int size = client.start();
//      int size = 5;
        System.out.println("size : " + size);
        SimpleElementsFinder sef = new SimpleElementsFinder();
        Date ref = new Date();
        String s = sef.find(client, size);
        System.out.println(new Date().getTime() - ref.getTime());
        System.out.println(s);
    }
}
