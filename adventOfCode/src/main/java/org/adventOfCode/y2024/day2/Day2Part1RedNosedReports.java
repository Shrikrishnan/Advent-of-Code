package org.adventOfCode.y2024.day2;

import org.slf4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day2Part1RedNosedReports {

    final static String inputFile = "y2024/day2/Day2Part1RedNosedReports.txt";
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Day2Part1RedNosedReports.class);

    public static void main(String[] args) throws URISyntaxException, IOException {
        new Day2Part1RedNosedReports().run();
    }


    public void run() throws URISyntaxException, IOException {
        System.out.println("Day 2 - Part 1: Red-Nosed Reports");
        var inputData = ClassLoader.getSystemResource(inputFile);
        if (inputData == null) {
            throw new RuntimeException("Resource not found: " + inputFile);
        }
        var lines = Files.readAllLines(Paths.get(inputData.toURI()), StandardCharsets.UTF_8);
        List<List<Integer>> actualIntegerListLisr = parseInput(lines);
        var result = calculateSafeRequest(actualIntegerListLisr);
        log.info("How many of the listed requests are safe? {}", result);
    }

    private List<List<Integer>> parseInput(List<String> lines) {
        List<List<Integer>> inListList = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.trim().split("\\s+");
            List<Integer> inList = new ArrayList<>();
            for (String part : parts) {
                inList.add(Integer.parseInt(part));
            }
            inListList.add(inList);
        }
        return inListList;
    }

    public int calculateSafeRequest(List<List<Integer>> inListList) {
        int safeRequest = 0;
        for (List<Integer> inList : inListList) {
            if (calculateSafeRequestRowLevel(inList)) {
                safeRequest++;
            }
        }
        return safeRequest;
    }

    public Boolean calculateSafeRequestRowLevel(List<Integer> inList) {
        boolean isIncreasing = true;
        boolean isDecreasing = true;
        for (int i = 0; i < inList.size() - 1; i++) {
            int diff = inList.get(i + 1) - inList.get(i);
            if (Math.abs(diff) < 1 || Math.abs(diff) > 3) {
                return false;
            }
            if (diff > 0) {
                isDecreasing = false;
            } else if (diff < 0) {
                isIncreasing = false;
            }
        }
        return isIncreasing || isDecreasing;
    }


     public Boolean calculateSafeRequestRowLevelOwn(List<Integer> inList) {
        Set<Integer> setInterger = new HashSet<>();
        setInterger.add(1);
        setInterger.add(2);
        setInterger.add(3);
        for (int i = 0; i < inList.size() - 1; i++) {
            if (!setInterger.contains(Math.abs(inList.get(i) - inList.get(i + 1)))) {
                {
                    return false;
                }
            }
        }
        return true;
    }
}
