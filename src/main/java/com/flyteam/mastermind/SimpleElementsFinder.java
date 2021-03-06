package com.flyteam.mastermind;

import com.flyteam.mastermind.client.ApiClient;
import com.flyteam.mastermind.client.ApiFailureException;
import com.flyteam.mastermind.client.ProposalResult;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author jtapiat
 */
public class SimpleElementsFinder {

    protected static final List<Character> ELEMENTS = getCharList();

    private static List<Character> getCharList() {
        List<Character> chars = new ArrayList<>();
        for (int i = 48; i <= 57; i++) {
            chars.add((char) i);
        }
        for (int i = 65; i <= 90; i++) {
            chars.add((char) i);
        }
        for (int i = 97; i <= 122; i++) {
            chars.add((char) i);
        }
        return chars;
    }

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

    private void launchThread(List<ElementChecker> checkers, List<Thread> threads, char character, ApiClient client, int size) {
        ElementChecker runnable = new ElementChecker(character, client, size);
        checkers.add(runnable);
        Thread t = new Thread(runnable);
        threads.add(t);
        t.start();
    }

    private void waitForThreads(List<Thread> threads) {
        do {
            for (Iterator<Thread> it = threads.iterator(); it.hasNext();) {
                Thread t = it.next();
                if (!t.isAlive()) {
                    it.remove();
                }
            }
        } while (!threads.isEmpty());
    }

    private String generateString(List<ElementChecker> runnables) {
        String res = "";
        for (ElementChecker runnable : runnables) {
            for (int k = 0; k < runnable.found; k++) {
                res += runnable.element;
            }
        }
        return res;
    }

    /**
     *
     * @param client
     * @param size
     * @return A string with containing all elements in the right number but not
     * the right position
     * @throws com.flyteam.mastermind.client.ApiFailureException
     */
    public String find(ApiClient client, int size) throws ApiFailureException {
        List<ElementChecker> runnables = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        for (char character : ELEMENTS) {
            launchThread(runnables, threads, character, client, size);
        }

        waitForThreads(threads);
        return generateString(runnables);
    }
}
