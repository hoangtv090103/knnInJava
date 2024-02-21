package com.knn;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        KNearestNeighbors knn = new KNearestNeighbors(3);

        // Initialize variables
        ArrayList<ArrayList<Double>> irisTrainData = new ArrayList<>();
        ArrayList<Integer> irisTrainingLabels = new ArrayList<>();
        ArrayList<ArrayList<Double>> irisTestData = new ArrayList<>();
        ArrayList<Integer> irisTestingLabels = new ArrayList<>();

        // Split dataset
        Dataset dataset = new Dataset("src/main/resources/Iris.csv", true);
        int labelIndex = 0;
        for (int i = 0; i < dataset.data.get(0).size(); i++) {
            String header = dataset.data.get(0).get(i).trim().toLowerCase();
            if (header.equals("species") || header.equals("label") || header.equals("labels")) {
                labelIndex = i;
                break;
            }
        }
        dataset.removeRow(0);

        ArrayList<ArrayList<String>> X = new ArrayList<>();
        ArrayList<String> y = new ArrayList<>();
        for (ArrayList<String> row : dataset.data) {
            X.add(new ArrayList<>(row.subList(0, labelIndex)));
            y.add(row.get(labelIndex));
        }

        dataset.trainTestSplit(X, y, 0.2, true);

        // Convert data to double
        for (ArrayList<String> row : dataset.xTrain) {
            ArrayList<Double> doubleRow = new ArrayList<>();
            for (String value : row) {
                try {
                    doubleRow.add(Double.parseDouble(value));
                } catch (NumberFormatException e) {
                    continue;
                }
            }
            irisTrainData.add(doubleRow);
        }

        for (ArrayList<String> row : dataset.xTest) {
            ArrayList<Double> doubleRow = new ArrayList<>();
            for (String value : row) {
                try {
                    doubleRow.add(Double.parseDouble(value));
                } catch (NumberFormatException e) {
                    continue;
                }
            }
            irisTestData.add(doubleRow);
        }

        // Convert labels to one-hot encoding
        ArrayList<String> uniqueLabels = new ArrayList<>(new HashSet<>(dataset.yTrain));
        for (String label : dataset.yTrain) {
            irisTrainingLabels.add(uniqueLabels.indexOf(label));
        }

        for (String label : dataset.yTest) {
            irisTestingLabels.add(uniqueLabels.indexOf(label));
        }


        // Fit model
        knn.fit(irisTrainData, irisTrainingLabels);

        ArrayList<Integer> predictions = knn.predict(irisTestData);
        for (int i = 0; i < predictions.size(); i++) {
            System.out.println("Feautures: " + dataset.xTest.get(i) + "- Label: " + dataset.yTest.get(i) + " - Predicted: " + uniqueLabels.get(predictions.get(i)));
        }
        System.out.println("Predictions: " + knn.predict(irisTestData));
        System.out.println("Accuracy: " + knn.accuracy(predictions, irisTestingLabels) * 100 + "%");
        System.out.println("Precision: " + knn.precision(predictions, irisTestingLabels) * 100 + "%");


    }
}