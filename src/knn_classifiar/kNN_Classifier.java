package knn_classifiar;

import knn_classifiar.DigitImage;

import java.util.ArrayList;
import java.util.Collections;

public class kNN_Classifier {
	int labelCount;
	ArrayList<DigitImage> images;
	int k;

	public kNN_Classifier(int labelCount, ArrayList<DigitImage> images, int k) {
		super();

		this.labelCount = labelCount;
		this.images = images;
		this.k = k;
	}

	int distance(DigitImage image1, DigitImage image2) {
		int dist = 0;
		int featureCount = image1.pixels.length;

		// Complete here
        for(int i=0; i<featureCount; i++)
            if(image1.pixels[i] != image2.pixels[i])
                dist++;

		return dist;
	}

	int classify(DigitImage newImage) {
		ArrayList<Neighbor> N = new ArrayList<Neighbor>();
		int[] digitCount = new int[labelCount];

		int maxCount = 0;
		int prediction = -1;

		for (DigitImage image : images) {
			Neighbor neighbor = new Neighbor(image.digit, distance(image, newImage));
			N.add(neighbor);
		}	

		Collections.sort(N);

		// Complete here
        for(int i=0; i<k; i++)
            digitCount[N.get(i).label]++;

        int mx = 0;
        for(int i=0; i<10; i++)
            if(digitCount[i] > mx) {
                mx = digitCount[i];
                prediction = i;
            }

		return prediction;
	}

	class Neighbor implements Comparable<Neighbor> {
		int label;
		int distance;

		public Neighbor(int label, int distance) {
			super();
			this.label = label;
			this.distance = distance;
		}

		@Override
		public int compareTo(Neighbor n) {
			return this.distance - n.distance;
		}
	}
}
