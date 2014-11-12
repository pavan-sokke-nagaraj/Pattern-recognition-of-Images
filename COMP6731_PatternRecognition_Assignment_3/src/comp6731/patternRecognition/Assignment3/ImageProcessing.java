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
		System.out.println("Input image height:\t" + inputImage.getHeight());
		System.out.println("Input image width:\t" + inputImage.getWidth());
		// convert the image to 2 dimensional binarry array
//		byte[][] binaryMatrix = otsuImageBinarization();
		OtsuImageBinarization otsuImageBinarization = new OtsuImageBinarization();
		byte[][] binaryMatrix = otsuImageBinarization.binarizeImage(inputImage) ;
		// print the binary values of image
		printBinaryImage(binaryMatrix);
		// normalize size to height 30 width 25
		byte[][] normalizedMatrix = sizeNormalization(binaryMatrix);
		printBinaryImage(normalizedMatrix);
		// Slkeleton extraction
		// Function call for Zhang-Suen Skeletonization
		ZhangSuenThinning zhangSuenThinning = new ZhangSuenThinning();
		byte[][] resultMatrix = zhangSuenThinning.applyZhangSuenThinning(normalizedMatrix);
		printBinaryImage(resultMatrix);
		byte[][] resultMatrix2 = thinningBinaryImage(normalizedMatrix);
		printBinaryImage(resultMatrix);
		
		CharacterFeatureExtraction featureExtraction = new CharacterFeatureExtraction();
		featureExtraction.extractFeatures(resultMatrix);
		
	}

	private static void printBinaryImage(byte[][] binaryMatrix) {
		System.out.println("Binary Image length:\t" + binaryMatrix.length);
		System.out.println("Binary Image width:\t" + binaryMatrix[0].length);
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
		String image4 = directory + "41.png"; // best 4T, 4TH, 4H
		String image = directory + "81.png"; // best 8, 8H
		String imageA = directory + "A.png"; // best A, AH
		String imageB = directory + "BTH.png"; // best B, BH, BTH
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

	public static byte[][] imageBinarization() {
		final int THRESHOLD = 160;
		int height = inputImage.getHeight();
		int width = inputImage.getWidth();
		byte[][] binaryImage = new byte[width][(height)];

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < (height); j++) {
				Color c = new Color(inputImage.getRGB(i, j));
				int red = c.getRed();
				int green = c.getGreen();
				int blue = c.getBlue();
				if (red < THRESHOLD && green < THRESHOLD && blue < THRESHOLD) {
					binaryImage[i][j] = 1;
				} else {
					binaryImage[i][j] = 0;
				}
			}
		}
		return binaryImage;
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
		System.out.println("Size Normalization:: Alpha :\t" + alpha
				+ "\tBeta :\t" + beta);
		for (int x = 0; x < binaryMatrix.length; x++) {
			for (int y = 0; y < binaryMatrix[0].length; y++) {
				normalizedRow = (int) (x * alpha);
				normalizedColumn = (int) (y * beta);
				resultMatrix[normalizedRow][normalizedColumn] = binaryMatrix[x][y];
				// System.out.println();
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
				
				boolean leftBit  = (binaryMatrix[i][j - 1] == 1) ? true : false;
				boolean rightBit = (binaryMatrix[i][j + 1] == 1) ? true : false;
				boolean upperBit = (binaryMatrix[i - 1][j] == 1) ? true : false;
				boolean lowerBit = (binaryMatrix[i + 1][j] == 1) ? true : false;
				
				boolean leftUpperBit = (binaryMatrix[i - 1][j - 1] == 1) ? true : false;
				boolean leftLowerBit = (binaryMatrix[i + 1][j - 1] == 1) ? true : false;
				boolean rightUpperBit = (binaryMatrix[i - 1][j + 1] == 1) ? true : false;
				boolean rightLowerBit = (binaryMatrix[i + 1][j + 1] == 1) ? true : false;

				boolean resultBit = targetBit | ( ( leftUpperBit  & leftBit  & upperBit ) 
												& ( rightUpperBit & rightBit & upperBit ) 
												& ( leftLowerBit  & leftBit  & lowerBit ) 
												& ( rightLowerBit & rightBit & lowerBit ) );
				binaryMatrix[i][j] = (byte) ((resultBit) ? 1 : 0);
			}
		}
		return binaryMatrix;
	}

}
