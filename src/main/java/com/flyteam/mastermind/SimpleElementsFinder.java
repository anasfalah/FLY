package com.flyteam.mastermind;

import com.flyteam.mastermind.client.ApiClient;
import com.flyteam.mastermind.client.ProposalResult;
import java.util.ArrayList;
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
                if (!isThreadRunning) {
                    isThreadRunning = t.isAlive();
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
}
