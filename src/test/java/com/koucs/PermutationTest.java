package com.koucs;

import com.koucs.util.Pair;
import org.junit.Test;

import java.util.*;

import static com.koucs.util.Utils.getCombinationsGivenCities;
import static com.koucs.util.Utils.listPermutations;

public class PermutationTest {

    @Test
    public void testListToPairs() {
        List<Integer> list = new ArrayList<>();
        list.add(41);
        list.add(34);
        list.add(45);
        list.add(67);
        list.add(11);
        list.add(77);
        list.add(55);

        List<Pair<Integer, Integer>> pairs = new ArrayList<>();

        for (int i = 0; i<list.size()-1; i++) {
            pairs.add(new Pair<>(list.get(i), list.get(i+1)));
        }
        System.out.println(pairs);
    }

    @Test
    public void testCombination() {
        List<Integer> list = new ArrayList<>();
        list.add(41);
        list.add(34);
        list.add(45);
        list.add(67);
        list.add(11);
        list.add(77);
        list.add(55);
        List<Pair<Integer, Integer>> pairList = getCombinationsGivenCities(list);
        System.out.println(pairList);
    }


    @Test
    public void testPermutation() {
        List<Integer> list = new ArrayList<>();
        list.add(41);
        list.add(34);
        list.add(45);
        list.add(67);

        List<List<Integer>> myLists = listPermutations(list);
        System.out.println("size : " +  myLists.size());

        myLists.sort(Comparator
                .comparing((List<Integer> o) -> o.get(0))
                .thenComparing(o -> o.get(1))
                .thenComparing(o -> o.get(2))
                .thenComparing(o -> o.get(4)));

        for (List<Integer> al : myLists) {
            String appender = "";
            for (Integer i : al) {
                System.out.print(appender + i);
                appender = " ";
            }
            System.out.println();
        }
    }
}
