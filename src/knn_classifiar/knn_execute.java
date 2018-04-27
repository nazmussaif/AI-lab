package knn_classifiar;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class knn_execute {
    final static int EXAMPLES = 5000;
    final static int FEATURES = 400;
    final static int CLASSES = 10;
    final static int K = 50;
    final static double trainingSetFrac = 0.99;

    static ArrayList<DigitImage> images, trainingSet, testSet;

    public static void read_data() throws Exception {
        BufferedReader featureReader = new BufferedReader(new FileReader("data//X.csv"));
        BufferedReader outputReader = new BufferedReader(new FileReader("data//Y.csv"));

        images = new ArrayList<>();
        int[] pixels = new int[FEATURES];
        int digit;

        String featureStr = null;
        String outputStr = null;

        while (true) {
            featureStr = featureReader.readLine();
            outputStr = outputReader.readLine();
            if (featureStr == null)
                break;

            String[] features = featureStr.split(",");

            int featureCount = 0;
            for (String f : features) {
                pixels[featureCount++] = Integer.parseInt(f);
            }

            digit = Integer.parseInt(outputStr);

            images.add(new DigitImage(pixels, digit));
        }

        featureReader.close();
        outputReader.close();
    }

    public static void partition_data() {
        int trainingSetSize = (int) (EXAMPLES * trainingSetFrac);

        trainingSet = new ArrayList<>();
        testSet = new ArrayList<>();

        Collections.shuffle(images);

        int i;
        for (i = 0; i < trainingSetSize; ++i)
            trainingSet.add(images.get(i));

        for (; i < EXAMPLES; ++i)
            testSet.add(images.get(i));
    }

    static double measure_accuracy(kNN_Classifier kNN) {
        double correctPrediction = 0;

        // Complete here
        for(DigitImage test: testSet) {
           if(kNN.classify(test) == test.digit)
               correctPrediction++;
        }
        return correctPrediction / testSet.size();
    }

    public static void execute() throws Exception {
        read_data();

        partition_data();

        Random random = new Random();
        int randomNum = random.nextInt(testSet.size());

        new DigitDisplay(testSet.get(randomNum).pixels);

        kNN_Classifier kNN = new kNN_Classifier(CLASSES, trainingSet, K);

        System.out.println("Predicted digit (k-NN): " + kNN.classify(testSet.get(randomNum)));

        System.out.println("\nActual digit: " + testSet.get(randomNum).digit);

        System.out.println("\nAccuracy of the model = " + measure_accuracy(kNN));
    }
}

@SuppressWarnings("serial")
class DigitDisplay extends JFrame {
    int[][] data = new int[20][20];

    public DigitDisplay(int[] d) {
        super("Digit Display");
        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 20; j++)
                data[i][j] = d[i * 20 + j];
        setSize(400, 400);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void paint(Graphics g) {
        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 20; j++) {
                if (data[i][j] == 1)
                    g.fillRect(i * 20, j * 20, 20, 20);
            }
    }
}
