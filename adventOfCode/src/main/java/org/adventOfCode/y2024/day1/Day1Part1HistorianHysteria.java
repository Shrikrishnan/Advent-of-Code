package org.adventOfCode.y2024.day1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Day1Part1HistorianHysteria {

    final static String inputFile = "y2024/day1/Day1Part1HistorianHysteria.txt";
    private static final Logger log = LoggerFactory.getLogger(Day1Part1HistorianHysteria.class);
    public static void main(String... args) throws IOException, URISyntaxException {
        new Day1Part1HistorianHysteria().run();
    }

    List<Integer> leftList = new ArrayList<>();
    List<Integer> rightList = new ArrayList<>();

    void run() throws IOException, URISyntaxException {
        var resource = ClassLoader.getSystemResource(inputFile);
        if (resource == null) {
            throw new IOException("Resource not found: " + inputFile);
        }
        var lines = Files.readAllLines(Paths.get(resource.toURI()), StandardCharsets.UTF_8);
        parseInput(lines);
        var result = calculateTotalDistance(leftList, rightList);
        log.warn("What is the total distance between your lists? {}", String.valueOf(result));
    }

    private void parseInput(List<String> lines) {
        for (String line : lines) {
            String[] parts = line.trim().split("\\s+");
            leftList.add(Integer.parseInt(parts[0]));
            rightList.add(Integer.parseInt(parts[1]));
        }
    }

    public static int calculateTotalDistance(List<Integer> leftList, List<Integer> rightList) {
        Collections.sort(leftList);
        Collections.sort(rightList);
        int totalDistance = 0;
        for (int i = 0; i < leftList.size(); i++) {
            totalDistance += Math.abs(leftList.get(i) - rightList.get(i));
        }
        return totalDistance;
    }
}