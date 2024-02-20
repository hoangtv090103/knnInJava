package com.knn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static java.util.Arrays.asList;

public class Dataset {
    ArrayList<ArrayList<String>> data;
    ArrayList<ArrayList<String>> xTrain;
    ArrayList<ArrayList<String>> xTest;
    ArrayList<String> yTrain;
    ArrayList<String> yTest;

    public Dataset() {
        this.data = new ArrayList<>();
    }

    public Dataset(String path, boolean shuffle) {
        this.data = readDataset(path);
        if (shuffle) {
            Collections.shuffle(this.data);
        }
    }

    public void trainTestSplit(ArrayList<ArrayList<String>> X, ArrayList<String> y, double testSize, boolean shuffle) {
        int datasetSize = X.size();
        int splitIndex = (int) (datasetSize * (1 - testSize));
        this.xTrain = new ArrayList<>(X.subList(0, splitIndex));
        this.xTest = new ArrayList<>(X.subList(splitIndex, datasetSize));
        this.yTrain = new ArrayList<>(y.subList(0, splitIndex));
        this.yTest = new ArrayList<>(y.subList(splitIndex, datasetSize));
    }

    public static ArrayList<ArrayList<String>> readDataset(String path) {
        // Read csv file
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                for (int i = 0; i < values.length; i++) {
                    values[i] = values[i].trim();
                    try {
                        Double.parseDouble(values[i]);
                    } catch (NumberFormatException e) {
                        values[i] = values[i];
                    }
                }
                data.add(new ArrayList<>(asList(values)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

}
