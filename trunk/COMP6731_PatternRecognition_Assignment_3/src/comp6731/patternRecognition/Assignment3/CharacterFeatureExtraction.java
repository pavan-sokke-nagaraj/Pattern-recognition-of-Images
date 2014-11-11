package comp6731.patternRecognition.Assignment3;

import java.util.LinkedList;
import java.util.List;

public class CharacterFeatureExtraction {

	public void extractFeatures(byte[][] inputMatrix) {
		int horizontalLines = extractHorizontalLines(inputMatrix);
		int verticalLines = extractVerticalLines(inputMatrix);
		contourSquareTracing(inputMatrix);
	}

	private void contourSquareTracing(byte[][] inputMatrix) {
		List<Pixel> setPixel = new LinkedList<Pixel>();

		
	}

	private int extractVerticalLines(byte[][] inputMatrix) {
		int verticalLineCount = 0;
		boolean lineStart = false;
		char check = 'C';
		int pixelCount = 0;
		for (int i = 1; i < inputMatrix[0].length - 1; i++) {
			for (int j = 0; j < inputMatrix.length - 1; j++) {
				/*if (inputMatrix[j][i - 1] == 1 && (check == 'C' || check == 'L')) {
					lineStart = true;
					check = 'L';
					pixelCount++;

				} else if (inputMatrix[j][i + 1] == 1 && ( check == 'C' || check == 'R') ) {
					lineStart = true;
					check = 'R';
					pixelCount++;
				} else if (inputMatrix[j][i] == 1
						&& (check == 'L' || check == 'R' || check == 'C')) {
					lineStart = true;
					check = 'C';
					pixelCount++;
				} else {
					if (lineStart && pixelCount > 10) {
						verticalLineCount++;
					}
					lineStart = false;
					pixelCount = 0;
				}*/
				
				if (inputMatrix[j][i] == 1) {
					lineStart=true;
					pixelCount++;
				}else{
					if( lineStart && pixelCount > 10 ){
						verticalLineCount++;
					}
					lineStart = false;
					pixelCount = 0;
				}

			}
		}

		System.out.println("Vertical Line Count :\t " + verticalLineCount);
		return verticalLineCount;
	}

	private int extractHorizontalLines(byte[][] inputMatrix) {
		int HorizontalLineCount = 0;
		boolean lineStart = false;
		char check = 'C';
		int pixelCount = 0;
		for (int i = 2; i < inputMatrix.length - 1; i++) {
			for (int j = 2; j < inputMatrix[0].length - 1; j++) {
				if (inputMatrix[i][j] == 1) {
					lineStart = true;
					pixelCount++;
				} else {
					if (lineStart && pixelCount > 5/*10*/) {
						HorizontalLineCount++;
					}
					lineStart = false;
					pixelCount = 0;

				}
			}
		}
		System.out.println("Horizontal Line Count :\t " + HorizontalLineCount);
		return HorizontalLineCount;

	}

}
