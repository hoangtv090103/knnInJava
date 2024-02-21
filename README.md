# K-Nearest Neighbors (KNN) Classifier

This project is a simple implementation of the K-Nearest Neighbors (KNN) algorithm in Java. The KNN algorithm is a type of instance-based learning, or lazy learning, where the function is only approximated locally and all computation is deferred until function evaluation.

## How it works

The KNN algorithm works by finding the distances between a query and all the examples in the data, selecting the specified number examples (K) closest to the query, then votes for the most frequent label (in the case of classification) or averages the labels (in the case of regression).

## Classes

### `Dataset.java`

This class is responsible for handling the dataset used in the KNN algorithm. It includes methods for reading the dataset from a CSV file, splitting the dataset into training and testing sets, and removing a row from the dataset.

Attributes:
- `data`: An ArrayList of ArrayLists of Strings representing the dataset.
- `xTrain`: An ArrayList of ArrayLists of Strings representing the training data.
- `xTest`: An ArrayList of ArrayLists of Strings representing the testing data.
- `yTrain`: An ArrayList of Strings representing the training labels.
- `yTest`: An ArrayList of Strings representing the testing labels.

Methods:
- `Dataset()`: Constructor for creating an empty Dataset object.
- `Dataset(String path, boolean shuffle)`: Constructor for creating a Dataset object from a CSV file.
- `trainTestSplit(ArrayList<ArrayList<String>> X, ArrayList<String> y, double testSize, boolean shuffle)`: Splits the dataset into training and testing sets.
- `readDataset(String path)`: Reads the dataset from a CSV file.
- `removeRow(int index)`: Removes a row from the dataset.

### `KNearestNeighbors.java`

This class is responsible for the implementation of the KNN algorithm.

Attributes:
- `trainingData`: An ArrayList of ArrayLists of Doubles representing the training data.
- `trainingLabels`: An ArrayList of Integers representing the training labels.
- `k`: An integer representing the number of neighbors to consider in the KNN algorithm.

Methods:
- `KNearestNeighbors()`: Constructor for creating a KNearestNeighbors object with a default k value of 3.
- `KNearestNeighbors(int k)`: Constructor for creating a KNearestNeighbors object with a specified k value.
- `fit(ArrayList<ArrayList<Double>> trainingData, ArrayList<Integer> trainingLabels)`: Fits the model to the training data.
- `predict(ArrayList<ArrayList<Double>> testData)`: Predicts the labels for the testing data.
- `predictPoint(ArrayList<Double> point)`: Predicts the label for a single data point.
- `mostCommon(ArrayList<Integer> list)`: Finds the most common label in a list.
- `calculateDistance(ArrayList<Double> point1, ArrayList<Double> point2)`: Calculates the Euclidean distance between two data points.
- `accuracy(ArrayList<Integer> predictions, ArrayList<Integer> actual)`: Calculates the accuracy of the model's predictions.

## Usage

To use this project, you need to have a dataset in CSV format. The dataset should be split into training and testing sets using the `trainTestSplit` method in the `Dataset` class. Then, you can create a `KNearestNeighbors` object and fit the model to the training data using the `fit` method. Finally, you can make predictions on the testing data using the `predict` method.