package comp6731.patternRecognition.Assignment3;

import java.util.LinkedList;
import java.util.List;

public class CharacterFeatureExtraction {

	private static final int pixelThresholdH = 5;
	private static final int pixelThresholdV = 5;
	private static final int pixelThresholdRightDiag = 5;
	private static final int pixelThresholdLeftDiag = 5;

	public void extractFeatures(byte[][] inputMatrix) {
		// Starters: pixels with one neighbour 1 in the character skeleton
		int starterPixelCount = extractStarterPixels(inputMatrix);
		System.out.println("Starter Pixel Count :\t " + starterPixelCount);
		int intersectionCount = extractIntersections(inputMatrix);
		System.out.println("Intersection Count :\t " + intersectionCount);
		int horizontalLineCount = extractHorizontalLines(inputMatrix);
		System.out.println("Horizontal Line Count :\t " + horizontalLineCount);
		int verticalLineCount = extractVerticalLines(inputMatrix);
		System.out.println("Vertical Line Count :\t " + verticalLineCount);
		int characterHoleCount = extractCharacterHoles(inputMatrix);
		System.out.println("Character's Hole Count :\t " + characterHoleCount);
		int leftDiagLineCount = extractLeftDiagonalLines(inputMatrix);
		System.out.println("Left Diagonal Line Count :\t " + leftDiagLineCount);
		int rightDiagLineCount = extractRightDiagonalLines(inputMatrix);
		System.out.println("Right Diagonal Line Count :\t "
				+ rightDiagLineCount);

		contourSquareTracing(inputMatrix);
	}

	private int extractIntersections(byte[][] inputMatrix) {
		int neighborCount = 0;
		int interSectionCount = 0;
		int adjacentPixelCount = 0;

		boolean isLinearPixel = false;
		boolean isAdjacentPixel = false;
		boolean isPerpendicular = false;

		for (int i = 1; i < inputMatrix.length - 1; i++) {
			for (int j = 1; j < inputMatrix[0].length - 1; j++) {

				boolean targetBit = (inputMatrix[i][j] == 1) ? true : false;

				boolean leftBit = (inputMatrix[i][j - 1] == 1) ? true : false;
				boolean rightBit = (inputMatrix[i][j + 1] == 1) ? true : false;
				boolean upperBit = (inputMatrix[i - 1][j] == 1) ? true : false;
				boolean lowerBit = (inputMatrix[i + 1][j] == 1) ? true : false;

				boolean leftUpperBit = (inputMatrix[i - 1][j - 1] == 1) ? true
						: false;
				boolean leftLowerBit = (inputMatrix[i + 1][j - 1] == 1) ? true
						: false;
				boolean rightUpperBit = (inputMatrix[i - 1][j + 1] == 1) ? true
						: false;
				boolean rightLowerBit = (inputMatrix[i + 1][j + 1] == 1) ? true
						: false;

				if (targetBit) {

					neighborCount = getNeighborCount(inputMatrix, i, j);

					if (leftBit & leftUpperBit) {
						adjacentPixelCount++;
						isAdjacentPixel = true;
					}
					if (leftBit & leftLowerBit) {
						adjacentPixelCount++;
						isAdjacentPixel = true;
					}
					if (rightBit & rightUpperBit) {
						adjacentPixelCount++;
						isAdjacentPixel = true;
					}
					if (rightBit & rightLowerBit) {
						adjacentPixelCount++;
						isAdjacentPixel = true;
					}
					if (upperBit & leftUpperBit) {
						adjacentPixelCount++;
						isAdjacentPixel = true;
					}
					if (upperBit & rightUpperBit) {
						adjacentPixelCount++;
						isAdjacentPixel = true;
					}
					if (lowerBit & leftLowerBit) {
						adjacentPixelCount++;
						isAdjacentPixel = true;
					}
					if (lowerBit & rightLowerBit) {
						adjacentPixelCount++;
						isAdjacentPixel = true;
					}
					if (rightLowerBit & leftUpperBit)
						isLinearPixel = true;
					if (rightUpperBit & leftLowerBit)
						isLinearPixel = true;
					if (rightBit & leftBit)
						isLinearPixel = true;
					if (lowerBit & upperBit)
						isLinearPixel = true;
					if (rightBit & (lowerBit | upperBit))
						isPerpendicular = true;
					if (leftBit & (lowerBit | upperBit))
						isPerpendicular = true;
					if (lowerBit & (rightBit | leftBit))
						isPerpendicular = true;
					if (upperBit & (rightBit | leftBit))
						isPerpendicular = true;
					if (neighborCount > 4) {
						j++;
						interSectionCount++;
					} else if (neighborCount == 4 && adjacentPixelCount <= 1) {
						j++;
						interSectionCount++;
					} else if (neighborCount == 3 && !isAdjacentPixel) {
						j++;
						interSectionCount++;
					} else if (neighborCount == 2 && isPerpendicular) {
						// interSectionCount++;
					}
				}
				isLinearPixel = false;
				isAdjacentPixel = false;
				isPerpendicular = false;
				adjacentPixelCount = 0;
				// System.out.print(inputMatrix[i][j]);
			}
		}
		return interSectionCount;
	}

	private int extractStarterPixels(byte[][] inputMatrix) {
		int neighborCount = 0;
		int starterPixelCount = 0;
		for (int i = 1; i < inputMatrix.length - 1; i++) {
			for (int j = 1; j < inputMatrix[0].length - 1; j++) {
				if (inputMatrix[i][j] == 1) {
					neighborCount = getNeighborCount(inputMatrix, i, j);
					if (neighborCount == 1) {
						starterPixelCount++;
						neighborCount = 0;
					}
				}
			}
		}
		return starterPixelCount;

	}

	private void contourSquareTracing(byte[][] inputMatrix) {
		List<Pixel> setPixel = new LinkedList<Pixel>();

	}

	private int extractVerticalLines(byte[][] inputMatrix) {
		int verticalLineCount = 0;
		boolean lineStart = false;
		// char check = 'C';
		int pixelCount = 0;
		for (int i = 1; i < inputMatrix[0].length - 1; i++) {
			for (int j = 0; j < inputMatrix.length - 1; j++) {
				/*
				 * if (inputMatrix[j][i - 1] == 1 && (check == 'C' || check ==
				 * 'L')) { lineStart = true; check = 'L'; pixelCount++;
				 * 
				 * } else if (inputMatrix[j][i + 1] == 1 && ( check == 'C' ||
				 * check == 'R') ) { lineStart = true; check = 'R';
				 * pixelCount++; } else if (inputMatrix[j][i] == 1 && (check ==
				 * 'L' || check == 'R' || check == 'C')) { lineStart = true;
				 * check = 'C'; pixelCount++; } else { if (lineStart &&
				 * pixelCount > 10) { verticalLineCount++; } lineStart = false;
				 * pixelCount = 0; }
				 */

				if (inputMatrix[j][i] == 1) {
					lineStart = true;
					pixelCount++;
				} else {
					if (lineStart && pixelCount >= pixelThresholdV) {
						verticalLineCount++;
					}
					lineStart = false;
					pixelCount = 0;
				}

			}
		}
		return verticalLineCount;
	}

	private int extractHorizontalLines(byte[][] inputMatrix) {
		int horizontalLineCount = 0;
		boolean lineStart = false;
		char check = 'C';
		int pixelCount = 0;
		for (int i = 2; i < inputMatrix.length - 1; i++) {
			for (int j = 2; j < inputMatrix[0].length - 1; j++) {
				if (inputMatrix[i][j] == 1) {
					lineStart = true;
					pixelCount++;
				} else {
					if (lineStart && pixelCount >= pixelThresholdH)
						horizontalLineCount++;
					lineStart = false;
					pixelCount = 0;

				}
			}
		}
		return horizontalLineCount;
	}

	private int getNeighborCount(byte[][] inputMatrix, int x, int y) {
		return inputMatrix[x - 1][y] + inputMatrix[x - 1][y + 1]
				+ inputMatrix[x][y + 1] + inputMatrix[x][y - 1]
				+ inputMatrix[x + 1][y + 1] + inputMatrix[x + 1][y]
				+ inputMatrix[x + 1][y - 1] + inputMatrix[x - 1][y - 1];
	}

	private int extractCharacterHoles(byte[][] inputMatrix) {
		int characterHoleCount = 0;
		byte[][] resultMatrix = fillUpZeroPixel(inputMatrix);
		for (int i = 1; i < resultMatrix.length - 1; i++) {
			for (int j = 1; j < resultMatrix[i].length - 1; j++) {
				if (resultMatrix[i][j] == 0) {
					++characterHoleCount;
					resultMatrix = extractHoleCount(resultMatrix, i, j);
				}
			}
		}
		return characterHoleCount;
	}

	private byte[][] extractHoleCount(byte[][] resultMatrix, int i, int j) {
		if (resultMatrix[i][j] == 0) {
			resultMatrix[i][j] = '$';
			if (i > 0 && j > 0)
				extractHoleCount(resultMatrix, i - 1, j - 1);
			if (i > 0)
				extractHoleCount(resultMatrix, i - 1, j);
			if (i > 0 && j < resultMatrix[0].length - 1)
				extractHoleCount(resultMatrix, i - 1, j + 1);
			if (j > 0)
				extractHoleCount(resultMatrix, i, j - 1);
			if (j < resultMatrix[0].length - 1)
				extractHoleCount(resultMatrix, i, j + 1);
			if (i < resultMatrix.length - 1 && j > 0)
				extractHoleCount(resultMatrix, i + 1, j - 1);
			if (i < resultMatrix.length - 1)
				extractHoleCount(resultMatrix, i + 1, j);
			if (i < resultMatrix.length - 1 && j < resultMatrix[0].length - 1)
				extractHoleCount(resultMatrix, i + 1, j + 1);
		}
		return resultMatrix;
	}

	private byte[][] fillUpZeroPixel(byte[][] inputMatrix) {
		for (int i = 1; i < inputMatrix.length - 1; i++) {
			for (int j = 1; j < inputMatrix[0].length - 1; j++) {
				if (inputMatrix[i][j] != 1)
					inputMatrix[i][j] = '*';
				else
					break;
			}
		}
		for (int i = 1; i < inputMatrix.length - 1; i++) {
			for (int j = inputMatrix[0].length - 1; j > 1; j--) {
				if (inputMatrix[i][j] != 1)
					inputMatrix[i][j] = '*';
				else
					break;
			}
		}
		for (int i = 1; i < inputMatrix[0].length - 1; i++) {
			for (int j = 1; j < inputMatrix.length - 1; j++) {
				if (inputMatrix[j][i] != 1)
					inputMatrix[j][i] = '*';
				else
					break;
			}
		}
		for (int i = inputMatrix[0].length - 1; i > 1; i--) {
			for (int j = inputMatrix.length - 1; j > 1; j--) {
				if (inputMatrix[j][i] != 1)
					inputMatrix[j][i] = '*';
				else
					break;
			}
		}
		return inputMatrix;
	}

	private int extractLeftDiagonalLines(byte[][] inputMatrix) {
		int leftDiagLineCount = 0;
		int rowCount = inputMatrix.length;
		int columnCount = inputMatrix[0].length;
		int maximumCount = rowCount + columnCount - 2;
		int pixelCount = 0;
		boolean isLinearPixel = false;
		boolean lineIntercepted = false;
		for (int k = 0; k <= maximumCount; k++) {
			for (int i = 0; i < rowCount; i++) {
				for (int j = 0; j < columnCount; j++) {
					if (i + j - k == 0) {
						if (inputMatrix[i][j] == 1) {
							isLinearPixel = true;
							pixelCount++;
						} else {
							if (lineIntercepted) {
								i++;
								j++;
								lineIntercepted = false;
								isLinearPixel = false;
							}
							if (isLinearPixel
									&& pixelCount >= pixelThresholdLeftDiag) {
								leftDiagLineCount++;
								lineIntercepted = true;
							}
							isLinearPixel = false;
							pixelCount = 0;
						}
					}
				}
			}
		}
		return leftDiagLineCount;
	}

	private int extractRightDiagonalLines(byte[][] inputMatrix) {
		int rightDiagLineCount = 0;
		int rowCount = inputMatrix.length;
		int columnCount = inputMatrix[0].length;
		boolean isLinearPixel = false;
		int maximumCount = rowCount + columnCount - 2;
		int pixelCount = 0;
		boolean lineIntercepted = false;
		for (int sum = maximumCount; sum > 0; sum--) {
			for (int i = 0; i < rowCount; i++) {
				for (int j = 0; j < columnCount; j++) {
					if (i + j - sum == 0) {
						if (inputMatrix[i][inputMatrix[i].length - j - 1] == 1) {
							isLinearPixel = true;
							pixelCount++;
						} else {
							if (lineIntercepted) {
								i++;
								j++;
								lineIntercepted = false;
								isLinearPixel = false;
							}
							if (isLinearPixel && pixelCount >= 5) {
								rightDiagLineCount++;
								lineIntercepted = true;
							}
							isLinearPixel = false;
							pixelCount = 0;
						}
					}
				}
			}
		}
		return rightDiagLineCount;
	}

}
