package comp6731.patternRecognition.Assignment3;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * @author Pavan Sokke Nagaraj <pavansn8@gmail.com>
 *
 */
public class OtsuImageBinarization {

	public static byte[][] binarizeImage(BufferedImage inputImage){		
		
		inputImage = rotateImage(inputImage);
		final int THRESHOLD = 160;
		int height = inputImage.getHeight();
		int width = inputImage.getWidth();
		byte[][] binaryImage = new byte[width][(height/*-21*/)];
		for(int i=0; i<width; i++){
			for(int j=/*5*/0; j<(height/*-50*/); j++){
				Color c = new Color(inputImage.getRGB(i,j));
				int red = c.getRed();
				int green = c.getGreen();
				int blue = c.getBlue();
				if(red<THRESHOLD && green<THRESHOLD && blue<THRESHOLD){
					binaryImage[i][j] = 1;
				}else{
					binaryImage[i][j] = 0;
				}
//				System.out.print(c.getRGB()+" ");
			}
//			System.out.println();
		}
		return binaryImage;
	}

	private static BufferedImage rotateImage(BufferedImage inputImage) {
		
		int height = inputImage.getHeight();
		int width = inputImage.getWidth();

		BufferedImage mirror= new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);


		for(int i=0; i<height; i++)
		{
			for(int j=0,x=width-1; j<width; j++,x--)
			{
				int p=inputImage.getRGB(j, i);
				mirror.setRGB(x, i, p);
			}
		}
		height = mirror.getHeight();
		width = mirror.getWidth();
		BufferedImage newImage = new BufferedImage(width, height, mirror.getType());
		Graphics2D g2 = newImage.createGraphics();
		g2.rotate(Math.toRadians(-90), width/2, height/2);  
		g2.drawImage(mirror,null,0,0);
		return newImage;
	}

}
