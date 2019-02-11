/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkgrabber;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Simon
 */
public class SimonMath {

    public static void main(String[] args) {
        String tools = "RRRR" + "WWW" + "SSSSS";
        Random rand = new Random();
        int correct = 0;
        for(int loop = 1; true; loop++) {
            List<Character> list = asList(tools);
            int black = 0;
            for (int i = 0; i < 4; i++) {
                Character ch = list.remove(rand.nextInt(list.size()));
                if(ch == 'S') black++;
            }
            if(black <= 2)
                correct ++;
            System.out.println((double)correct/loop);
        }
    }

    private static List<Character> asList(String tools) {
        List<Character> list = new ArrayList<>();
        for (char c : tools.toCharArray()) {
            list.add(c);
        }
        return list;
    }
}
