package knn_classifiar;

public class DigitImage {
	int[] pixels;
	int digit;

	public DigitImage(int[] pixels, int digit) {
		super();

		this.pixels = (int[]) pixels.clone();
		this.digit = digit;
	}
}
