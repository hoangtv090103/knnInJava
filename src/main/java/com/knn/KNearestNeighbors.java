package com.knn;

import java.util.*;

public class KNearestNeighbors {
    ArrayList<ArrayList<Double>> trainingData;
    ArrayList<Integer> trainingLabels;
    int k;

    public KNearestNeighbors() {
        this.k = 3;
    }

    public KNearestNeighbors(int k) {
        this.k = k;

    }

    public void fit(ArrayList<ArrayList<Double>> trainingData, ArrayList<Integer> trainingLabels) {
        this.trainingData = trainingData;
        this.trainingLabels = trainingLabels;
    }

    public ArrayList<Integer> predict(ArrayList<ArrayList<Double>> testData) {
        ArrayList<Integer> predictions = new ArrayList<>();
        for (ArrayList<Double> point : testData) {
            int label = predictPoint(point);
            predictions.add(label);
        }
        return predictions;
    }

    public int predictPoint(ArrayList<Double> point) {
        ArrayList<Double> distances = new ArrayList<>();
        for (ArrayList<Double> trainingPoint : this.trainingData) {
            distances.add(calculateDistance(point, trainingPoint));
        }

        ArrayList<Double> sortedDistances = new ArrayList<>(distances);
        Collections.sort(sortedDistances);

        ArrayList<Integer> kNearestLabels = new ArrayList<>();
        for (int i = 0; i < this.k; i++) {
            int index = distances.indexOf(sortedDistances.get(i));
            kNearestLabels.add(this.trainingLabels.get(index));
        }
        return mostCommon(kNearestLabels);
    }

    public int mostCommon(ArrayList<Integer> list) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : list) {
            Integer val = map.get(i);
            map.put(i, val == null ? 1 : val + 1);
        }
        Map.Entry<Integer, Integer> max = null;
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            if (max == null || e.getValue() > max.getValue()) {
                max = e;
            }
        }
        return max.getKey();
    }

    public double calculateDistance(ArrayList<Double> point1, ArrayList<Double> point2) {
        double distance = 0;
        for (int i = 0; i < point1.size(); i++) {
            distance += Math.pow(point1.get(i) - point2.get(i), 2);
        }
        return Math.sqrt(distance);
    }

    // Accuracy
    public double accuracy(ArrayList<Integer> predictions, ArrayList<Integer> actual) {
        int correct = 0;
        for (int i = 0; i < predictions.size(); i++) {
            if (Objects.equals(predictions.get(i), actual.get(i))) {
                correct++;
            }
        }
        return (double) correct / predictions.size();
    }

    // Precision
    public double precision(ArrayList<Integer> predictions, ArrayList<Integer> actual) {
        int truePositive = 0;
        int falsePositive = 0;
        for (int i = 0; i < predictions.size(); i++) {
            if (Objects.equals(predictions.get(i), 1) && Objects.equals(actual.get(i), 1)) {
                truePositive++;
            } else if (Objects.equals(predictions.get(i), 1) && Objects.equals(actual.get(i), 0)) {
                falsePositive++;
            }
        }
        return (double) truePositive / (truePositive + falsePositive);
    }
}