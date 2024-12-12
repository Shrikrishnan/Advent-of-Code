package org.adventOfCode.y2024.day1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day1Part2HistorianHysteria {
    private static  final String inputFile = "y2024/day1/Day1Part2HistorianHysteria.txt";
    public static void main(String[] args) throws IOException, URISyntaxException {
        new Day1Part2HistorianHysteria().run();
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
      var   result = calculateTheSimilarityScore(leftList, rightList);
        System.out.println("What is the similarity score  your lists ? " + result);
    }

    private void parseInput(List<String> lines) {
        for (String line : lines) {
            String[] parts = line.trim().split("\\s+");
            leftList.add(Integer.parseInt(parts[0]));
            rightList.add(Integer.parseInt(parts[1]));
        }
    }

    public static Integer calculateTheSimilarityScore(List<Integer> leftList, List<Integer> rightList) {

        HashMap<Integer, Integer> similarityScoreMap = new HashMap<>();
        for (int i = 0; i < leftList.size(); i++) {
            if (similarityScoreMap.containsKey(rightList.get(i))) {
                similarityScoreMap.put(leftList.get(i), similarityScoreMap.get(rightList.get(i)) + 1);
            }
            similarityScoreMap.put(leftList.get(i), 1);
        }

        return calculateTheSimilaritySCoreUsingMapAndRightList(similarityScoreMap, rightList);
    }


    public static Integer calculateTheSimilaritySCoreUsingMapAndRightList(HashMap<Integer, Integer> similarityScoreMap, List<Integer> rightList) {
        int similarityScore = 0;
        for (int i = 0; i < rightList.size(); i++) {
            if (similarityScoreMap.containsKey(rightList.get(i))) {
                similarityScore = (similarityScoreMap.get(rightList.get(i)) * rightList.get(i)) + similarityScore;
            }
        }
        return similarityScore;
    }
}
