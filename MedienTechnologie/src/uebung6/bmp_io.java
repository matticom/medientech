package uebung6;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public final class bmp_io {
	
	public static void bmpConvert(String[] args, String detail_filter, String detail_diff, 
			String flaeche_filter, String flaeche_diff,
			boolean mean, boolean gradient, boolean median, boolean sobel) throws IOException {
		
		String inFilename = null;
		String inFilename2 = null;
		
		BmpImage bmp = null;
		BmpImage bmp_filter = null;
		BmpImage bmp_diff = null;
		BmpImage bmp2 = null;
		BmpImage bmp_filter2 = null;
		BmpImage bmp_diff2 = null;
		
		OutputStream outFilter = null;
		OutputStream outDiff = null;
		OutputStream outFilter2 = null;
		OutputStream outDiff2 = null;
		
		if(args.length < 2) {
			System.out.println("At least two filename specified  (" + args.length + ")");
			System.exit(0);
		}
				
		// Detail
		inFilename = args[0];
		InputStream in = new FileInputStream(inFilename);
		bmp = BmpReader.read_bmp(in);
		
		InputStream in2 = new FileInputStream(inFilename);
		bmp_filter = BmpReader.read_bmp(in2);
		
		InputStream in3 = new FileInputStream(inFilename);
		bmp_diff = BmpReader.read_bmp(in3);
		
		//Flaeche
		inFilename2 = args[1];
		InputStream in4 = new FileInputStream(inFilename2);
		bmp2 = BmpReader.read_bmp(in4);
		
		InputStream in5 = new FileInputStream(inFilename2);
		bmp_filter2 = BmpReader.read_bmp(in5);
		
		InputStream in6 = new FileInputStream(inFilename2);
		bmp_diff2 = BmpReader.read_bmp(in6);
		
	
		outFilter = new FileOutputStream(detail_filter);
		outDiff = new FileOutputStream(detail_diff);
		outFilter2 = new FileOutputStream(flaeche_filter);
		outDiff2 = new FileOutputStream(flaeche_diff);
		
		if (mean) {
			
			int[] meanFilter = new int[]{1, 1, 1,   1, 1, 1,   1, 1, 1}; //3x Zeilen
			for(int y = 0; y < bmp.image.getHeight(); y++) {
				for(int x = 0;x < bmp.image.getWidth(); x++) {
					bmp_filter.image.setRgbPixel(x, y, applyStdFilterToPixel(x, y, meanFilter, bmp));
					bmp_diff.image.setRgbPixel(x, y, createDiffImg(x, y, bmp, bmp_filter, 4));
				}
			}
			
			for(int y = 0; y < bmp2.image.getHeight(); y++) {
				for(int x = 0;x < bmp2.image.getWidth(); x++) {
					bmp_filter2.image.setRgbPixel(x, y, applyStdFilterToPixel(x, y, meanFilter, bmp2));
					bmp_diff2.image.setRgbPixel(x, y, createDiffImg(x, y, bmp2, bmp_filter2, 20));
				}
			}
		}
		
		if (gradient) {
			int[] gradientFilter = new int[]{0, -2, 0,   -2, 12, -2,   0, -2, 0}; //Zeilen
			for(int y = 0; y < bmp.image.getHeight(); y++) {
				for(int x = 0;x < bmp.image.getWidth(); x++) {
					bmp_filter.image.setRgbPixel(x, y, applyStdFilterToPixel(x, y, gradientFilter, bmp));
					bmp_diff.image.setRgbPixel(x, y, createDiffImg(x, y, bmp, bmp_filter, 4));
				}
			}
			
			for(int y = 0; y < bmp2.image.getHeight(); y++) {
				for(int x = 0;x < bmp2.image.getWidth(); x++) {
					bmp_filter2.image.setRgbPixel(x, y, applyStdFilterToPixel(x, y, gradientFilter, bmp2));
					bmp_diff2.image.setRgbPixel(x, y, createDiffImg(x, y, bmp2, bmp_filter2, 20));
				}
			}
		}
		
		if (median) {
			for(int y = 0; y < bmp.image.getHeight(); y++) {
				for(int x = 0;x < bmp.image.getWidth(); x++) {
					bmp_filter.image.setRgbPixel(x, y, medianFilterToPixel(x, y, bmp));
					bmp_diff.image.setRgbPixel(x, y, createDiffImg(x, y, bmp, bmp_filter, 4));
				}
			}
			
			for(int y = 0; y < bmp2.image.getHeight(); y++) {
				for(int x = 0;x < bmp2.image.getWidth(); x++) {
					bmp_filter2.image.setRgbPixel(x, y, medianFilterToPixel(x, y, bmp2));
					bmp_diff2.image.setRgbPixel(x, y, createDiffImg(x, y, bmp2, bmp_filter2, 20));
				}
			}
		}
		
		if(sobel) {
			int[] sobelVerticalFilter = new int[]{1, 0, -1,   2, 0, -2,   1, 0, -1};
			int[] sobelHorizontalFilter = new int[]{1, 2, 1,   0, 0, 0,   -1, -2, -1};
			for(int y = 0; y < bmp.image.getHeight(); y++) {
				for(int x = 0;x < bmp.image.getWidth(); x++) {
					bmp_filter.image.setRgbPixel(x, y, sobelFilterToPixel(x, y, sobelVerticalFilter, bmp));
					bmp_diff.image.setRgbPixel(x, y, sobelFilterToPixel(x, y, sobelHorizontalFilter, bmp));
				}
			}
			
			for(int y = 0; y < bmp2.image.getHeight(); y++) {
				for(int x = 0;x < bmp2.image.getWidth(); x++) {
					bmp_filter2.image.setRgbPixel(x, y, sobelFilterToPixel(x, y, sobelVerticalFilter, bmp2));
					bmp_diff2.image.setRgbPixel(x, y, sobelFilterToPixel(x, y, sobelHorizontalFilter, bmp2));
				}
			}
		}
		
		try {
			BmpWriter.write_bmp(outFilter,bmp_filter);
			BmpWriter.write_bmp(outDiff,bmp_diff);
			BmpWriter.write_bmp(outFilter2,bmp_filter2);
			BmpWriter.write_bmp(outDiff2,bmp_diff2);
		} finally {
			in.close();
			in2.close();
			in3.close();
			in4.close();
			in5.close();
			in6.close();
			outFilter.close();
			outDiff.close();
			outFilter2.close();
			outDiff2.close();
		}
	}
	
	private static PixelColor applyStdFilterToPixel(int x, int y, int[] filter, BmpImage input) {
		int imgWidth = input.image.getWidth()-1;
		int imgHeight = input.image.getHeight()-1;
		
		double koeffSum = 0;  
		for (int koeff : filter) {
			koeffSum += koeff;
		}
		
		int h;
		int w;
		
		int red = 0;
		int green = 0;
		int blue = 0;
//		System.out.println("\n\nX: " + x + "; Y: " + y );
		for(int m = 0; m < 9; m++) {
			h = (m / 3) - 1 + y;
			w = (m % 3) - 1 + x;
//			System.out.println("Position: " + m);
//			System.out.println("Height: " + h);
//			System.out.println("Width: " + w);
			if (h < 0 || h > imgHeight || w < 0 || w > imgWidth) {
				red += (128 * filter[m]);
				green += (128 * filter[m]);
				blue += (128 * filter[m]);
			} else {
				PixelColor pc = input.image.getRgbPixel(w, h);
				red += (pc.r * filter[m]);
				green += (pc.g * filter[m]);
				blue += (pc.b * filter[m]);
			}
		}		
		
		red = round(red/koeffSum);
		green = round(green/koeffSum);
		blue = round(blue/koeffSum);
		if (red < 0) {
			red = 0;
		}
		if (red > 255) {
			red = 255;
		}
		if (green < 0) {
			green = 0;
		}
		if (green > 255) {
			green = 255;
		}
		if (blue < 0) {
			blue = 0;
		}
		if (blue > 255) {
			blue = 255;
		}
		return new PixelColor(red, green, blue);
	}
	
	private static PixelColor medianFilterToPixel(int x, int y, BmpImage input) {
		int imgWidth = input.image.getWidth()-1;
		int imgHeight = input.image.getHeight()-1;
						
		int h;
		int w;
		int[] valueArray = new int[9];
		
		int red = 0;
		int green = 0;
		int blue = 0;

		for(int m = 0; m < 9; m++) {
			h = (m / 3) - 1 + y;
			w = (m % 3) - 1 + x;
			
			if (h < 0 || h > imgHeight || w < 0 || w > imgWidth) {
				valueArray[m] = 128;
			} else {
				PixelColor pc = input.image.getRgbPixel(w, h);
				valueArray[m] = pc.r;
			}
		}		
		if (x == 244 && y == 238){
			System.out.println("hier");
		}
		Arrays.sort(valueArray);
		return new PixelColor(valueArray[4], valueArray[4], valueArray[4]);
	}
	
	private static PixelColor sobelFilterToPixel(int x, int y, int[] filter, BmpImage input) {
		int imgWidth = input.image.getWidth()-1;
		int imgHeight = input.image.getHeight()-1;
				
		int h;
		int w;
		
		int red = 0;
		int green = 0;
		int blue = 0;
		
		for(int m = 0; m < 9; m++) {
			h = (m / 3) - 1 + y;
			w = (m % 3) - 1 + x;
			if (h < 0 || h > imgHeight || w < 0 || w > imgWidth) {
				red += (128 * filter[m]);
				green += (128 * filter[m]);
				blue += (128 * filter[m]);
			} else {
				PixelColor pc = input.image.getRgbPixel(w, h);
				red += (pc.r * filter[m]);
				green += (pc.g * filter[m]);
				blue += (pc.b * filter[m]);
			}
		}	
		red = red / 2 + 127;
		green = green / 2 + 127;
		blue = blue / 2 + 127;
		return new PixelColor(red, green, blue);
	}
	
	private static PixelColor createDiffImg(int x, int y, BmpImage original, BmpImage filter, double contrastFactor) {
			
		int diffRed = filter.image.getRgbPixel(x, y).r - original.image.getRgbPixel(x, y).r;
		int diffGreen = filter.image.getRgbPixel(x, y).g - original.image.getRgbPixel(x, y).g;
		int diffBlue = filter.image.getRgbPixel(x, y).b - original.image.getRgbPixel(x, y).b;
		diffRed = 128 + diffRed;
		diffGreen = 128 + diffGreen;
		diffBlue = 128 + diffBlue;
		// Kontrastverstärkunng
		diffRed = (int)((diffRed - 128) * contrastFactor + 128);
		diffGreen = (int)((diffGreen - 128) * contrastFactor + 128);
		diffBlue = (int)((diffBlue - 128) * contrastFactor + 128);
		if (diffRed < 0) {
			diffRed = 0;
		}
		if (diffRed > 255) {
			diffRed = 255;
		}
		if (diffGreen < 0) {
			diffGreen = 0;
		}
		if (diffGreen > 255) {
			diffGreen = 255;
		}
		if (diffBlue < 0) {
			diffBlue = 0;
		}
		if (diffBlue > 255) {
			diffBlue = 255;
		}
		return new PixelColor(diffRed, diffGreen, diffBlue);
	}
	
	private static int round(double d){
	    double dAbs = Math.abs(d);
	    int i = (int) dAbs;
	    double result = dAbs - (double) i;
	    if(result<0.5){
	        return d<0 ? -i : i;            
	    }else{
	        return d<0 ? -(i+1) : i+1;          
	    }
	}
	
}
