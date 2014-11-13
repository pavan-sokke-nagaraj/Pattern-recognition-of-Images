/**
 * 
 */
package comp6731.patternRecognition.Assignment3;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Pavan Sokke Nagaraj <pavansn8@gmail.com>
 *
 */
public class ImageProcessing {

	public static BufferedImage inputImage;
	private static float normalizeHeight = 30;
	private static float normalizeWidth = 25;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// read the input image
		readImage();
		System.out.println("_______________IMAGE INPUT DETAILS _______________");
		System.out.println("Input image height:\t" + inputImage.getHeight());
		System.out.println("Input image width:\t" + inputImage.getWidth());
		System.out.println("___________________________________________________");
		System.out.println();
		// convert the image to 2 dimensional binarry array
		// byte[][] binaryMatrix = otsuImageBinarization();
		OtsuImageBinarization otsuImageBinarization = new OtsuImageBinarization();
		byte[][] binaryMatrix = otsuImageBinarization.binarizeImage(inputImage);
		// print the binary values of image
		// printBinaryImage(binaryMatrix);
		// normalize size to height 30 width 25
		System.out.println("________BINARY IMAGE AFTER SIZE NORMALIZATION ( 30H x 25W ) __________");
		byte[][] normalizedMatrix = sizeNormalization(binaryMatrix);
		System.out.println("Length and width after size normalization");
		printBinaryImage(normalizedMatrix);
		System.out.println("______________________________________________________________________");
		// Slkeleton extraction
		// Function call for Zhang-Suen Skeletonization
		ZhangSuenThinning zhangSuenThinning = new ZhangSuenThinning();
		byte[][] resultMatrix = zhangSuenThinning
				.applyZhangSuenThinning(normalizedMatrix);
		System.out.println("______________BINARY IMAGE AFTER ZHANG-SUEN THINNING__________________");
		printBinaryImage(resultMatrix);
		System.out.println("______________________________________________________________________");
//		byte[][] resultMatrix2 = thinningBinaryImage(normalizedMatrix);
//		printBinaryImage(resultMatrix);
		

		CharacterFeatureExtraction featureExtraction = new CharacterFeatureExtraction();
		featureExtraction.extractFeatures(resultMatrix);

	}

	private static void printBinaryImage(byte[][] binaryMatrix) {
		System.out.println("Binary Image length:\t" + binaryMatrix.length);
		System.out.println("Binary Image width :\t" + binaryMatrix[0].length);
		for (int i = 0; i < binaryMatrix.length; i++) {
			for (int j = 0; j < binaryMatrix[0].length; j++) {
				if (binaryMatrix[i][j] == 1)
					System.out.print(binaryMatrix[i][j]);
				else
					System.out.print(" ");
			}
			System.out.println();
		}
	}

	private static void readImage() {
		String directory = "resource/";
		String image = directory + "4H.png"; // 4T, 4TH, 4H
		String image8 = directory + "8H.png"; // 8, 8H
		String imageA = directory + "AH.png"; // A, AH
		String imageB = directory + "BTH.png"; // B, BH, BTH
		try {
			inputImage = ImageIO.read(new File(image));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			File outputfile = new File("saved.png");
			ImageIO.write(inputImage, "png", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Function for Size Normalization
	 */
	private static byte[][] sizeNormalization(byte[][] binaryMatrix) {
		byte[][] resultMatrix = new byte[(int) normalizeHeight][(int) normalizeWidth];
		int normalizedRow = 0;
		int normalizedColumn = 0;
		double alpha = (float) (normalizeHeight / binaryMatrix.length);
		double beta = (float) (normalizeWidth / binaryMatrix[0].length);
		System.out.println("\n\t Alpha :\t" + alpha
				+ "\n\t Beta  :\t" + beta);
		System.out.println();
		for (int x = 0; x < binaryMatrix.length; x++) {
			for (int y = 0; y < binaryMatrix[0].length; y++) {
				normalizedRow = (int) (x * alpha);
				normalizedColumn = (int) (y * beta);
				resultMatrix[normalizedRow][normalizedColumn] = binaryMatrix[x][y];
			}
		}
		return resultMatrix;
	}

	/*
	 * Function for Thinning Binary Image
	 */
	private static byte[][] thinningBinaryImage(byte[][] binaryMatrix) {
		for (int i = 1; i < binaryMatrix.length - 1; i++) {
			for (int j = 1; j < binaryMatrix[0].length - 1; j++) {
				boolean targetBit = (binaryMatrix[i][j] == 1) ? true : false;

				boolean leftBit = (binaryMatrix[i][j - 1] == 1) ? true : false;
				boolean rightBit = (binaryMatrix[i][j + 1] == 1) ? true : false;
				boolean upperBit = (binaryMatrix[i - 1][j] == 1) ? true : false;
				boolean lowerBit = (binaryMatrix[i + 1][j] == 1) ? true : false;

				boolean leftUpperBit = (binaryMatrix[i - 1][j - 1] == 1) ? true
						: false;
				boolean leftLowerBit = (binaryMatrix[i + 1][j - 1] == 1) ? true
						: false;
				boolean rightUpperBit = (binaryMatrix[i - 1][j + 1] == 1) ? true
						: false;
				boolean rightLowerBit = (binaryMatrix[i + 1][j + 1] == 1) ? true
						: false;

				boolean resultBit = targetBit
						| ((leftUpperBit & leftBit & upperBit)
								& (rightUpperBit & rightBit & upperBit)
								& (leftLowerBit & leftBit & lowerBit) & (rightLowerBit
								& rightBit & lowerBit));
				binaryMatrix[i][j] = (byte) ((resultBit) ? 1 : 0);
			}
		}
		return binaryMatrix;
	}

}
