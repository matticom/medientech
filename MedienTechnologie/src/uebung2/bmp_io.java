package uebung2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

public final class bmp_io {

	// Run with zero command-line arguments. This program reads Demo.bmp and
	// writes Demo2.bmp in the current directory.
	public static void bmpConvert(String[] args, String writeValueTxt, boolean greyScale, int reductionFactor,
			boolean downSampling, boolean isHorizontalDS, int reduced_bits, boolean bitReduction, boolean bitNoise)
			throws IOException {
		String inFilename = null;
		String outFilename = null;
		PixelColor pc = null;
		BmpImage bmp = null;

		if (args.length < 1)
			System.out.println("At least one filename specified  (" + args.length + ")");

		inFilename = args[0];
		InputStream in = new FileInputStream(inFilename);
		bmp = BmpReader.read_bmp(in);

		if (writeValueTxt != null) {
			try (FileWriter fwRed = new FileWriter(new File(writeValueTxt + "_Red.txt"));
					FileWriter fwGreen = new FileWriter(new File(writeValueTxt + "_Green.txt"));
					FileWriter fwBlue = new FileWriter(new File(writeValueTxt + "_Blue.txt"));
					PrintWriter pwRed = new PrintWriter(fwRed, true);
					PrintWriter pwGreen = new PrintWriter(fwGreen, true);
					PrintWriter pwBlue = new PrintWriter(fwBlue, true);) {

				// BGR schreiben horizontal 2.1.
				for (int x = 0; x < bmp.image.getWidth(); x++) {
					PixelColor actualPixel = bmp.image.getRgbPixel(x, bmp.image.getHeight() / 2);
					pwRed.println(actualPixel.r);
					pwGreen.println(actualPixel.g);
					pwBlue.println(actualPixel.b);
				}

				pwRed.println("\n------------->   Spalte   <--------------\n");
				pwGreen.println("\n------------->   Spalte   <--------------\n");
				pwBlue.println("\n------------->   Spalte   <--------------\n");

				// BGR schreiben vertikal 2.1.
				for (int y = 0; y < bmp.image.getHeight(); y++) {
					PixelColor actualPixel = bmp.image.getRgbPixel(bmp.image.getWidth() / 2, y);
					pwRed.println(actualPixel.r);
					pwGreen.println(actualPixel.g);
					pwBlue.println(actualPixel.b);
				}
			} catch (IOException e) {
				System.out.println("Problem beim öffnen der Datei zum Schreiben");
			}
		}

		if (args.length == 1)
			System.exit(0);

		outFilename = args[1];
		OutputStream out = new FileOutputStream(outFilename);

		// http://www.kurztutorial.info/programme/helligkeit-farbe-berechnen/rgb-bright-rechner.htm

		if (greyScale) {
			// erzeuge graustufenbild
			for (int y = 0; y < bmp.image.getHeight(); y++) {
				for (int x = 0; x < bmp.image.getWidth(); x++) {
					PixelColor actualPixel = bmp.image.getRgbPixel(x, y);
//					int grey = (int) ((0.2126 * Math.pow(actualPixel.r / 255.0, 2.2)
//							+ 0.7152 * Math.pow(actualPixel.g / 255.0, 2.2)
//							+ 0.0722 * Math.pow(actualPixel.b / 255.0, 2.2)) * 255);
					int grey = (int) (0.333 * actualPixel.r + 0.333 * actualPixel.g + 0.333 * actualPixel.b);
					actualPixel.r = grey;
					actualPixel.g = grey;
					actualPixel.b = grey;
				}
			}
		}

		// downsampling
		if (downSampling) {
			PixelColor[] currentY = new PixelColor[bmp.image.getWidth()];
			PixelColor currentX = null;
			for (int y = 0; y < bmp.image.getHeight(); y++) {
				for (int x = 0; x < bmp.image.getWidth(); x++) {
					if (isHorizontalDS) {  // zB jede Zweite Spalte wird mit der vorherigen überschrieben
						if (x % reductionFactor == 0) {
							currentX = bmp.image.getRgbPixel(x, y);
						} else {
							bmp.image.setRgbPixel(x, y, currentX);
						}
					}
					if (!isHorizontalDS) { // zB jede Zweite Zeile wird mit der vorherigen überschrieben
						if (y % reductionFactor == 0) {
							currentY[x] = bmp.image.getRgbPixel(x, y);
						} else {
							bmp.image.setRgbPixel(x, y, currentY[x]);
						}
					}
				}
			}
		}

		// bitreduzierung
		if (bitReduction) {
			// int reduced_bits = 1;
			for (int y = 0; y < bmp.image.getHeight(); y++) {
				for (int x = 0; x < bmp.image.getWidth(); x++) {
					PixelColor actualPixel = bmp.image.getRgbPixel(x, y);
					int red = (int) (actualPixel.r / Math.pow(2, reduced_bits));
					int green = (int) (actualPixel.g / Math.pow(2, reduced_bits));
					int blue = (int) (actualPixel.b / Math.pow(2, reduced_bits));
					actualPixel.r = (int) (red * Math.pow(2, reduced_bits));
					actualPixel.g = (int) (green * Math.pow(2, reduced_bits));
					actualPixel.b = (int) (blue * Math.pow(2, reduced_bits));
				}
			}
		}

		// bitreduzierung differenz
		if (bitNoise) {
			int bitsPerColor = 8;
			for (int y = 0; y < bmp.image.getHeight(); y++) {
				for (int x = 0; x < bmp.image.getWidth(); x++) {
					PixelColor actualPixel = bmp.image.getRgbPixel(x, y);
				
					int originalRed = actualPixel.r;
					int originalGreen = actualPixel.g;
					int originalBlue = actualPixel.b;
					
					int reducedRed = (int) (actualPixel.r / Math.pow(2, reduced_bits));
					int reducedGreen = (int) (actualPixel.g / Math.pow(2, reduced_bits));
					int reducedBlue = (int) (actualPixel.b / Math.pow(2, reduced_bits));
					reducedRed = (int) (reducedRed * Math.pow(2, reduced_bits));
					reducedGreen = (int) (reducedGreen * Math.pow(2, reduced_bits));
					reducedBlue = (int) (reducedBlue * Math.pow(2, reduced_bits));
					
					// Original minus Bitreduziertes Bild: das Original kann bei -8bit wiederhergestellt werden 
//					int diffRed = originalRed - reducedRed;
//					int diffGreen = originalGreen - reducedGreen;
//					int diffBlue = originalBlue - reducedBlue;
					
					// Bitreduziertes Bild minus Original: das Original kann bei -8bit invertiert wiederhergestellt werden
					int diffRed = reducedRed - originalRed;
					int diffGreen = reducedGreen - originalGreen;
					int diffBlue = reducedBlue - originalBlue;
					
					diffRed = 127 + diffRed/2;
					diffGreen = 127 + diffGreen/2;
					diffBlue = 127 + diffBlue/2;
					
//					System.out.println("red: " + diffRed);
//					System.out.println("green: " + diffGreen);
//					System.out.println("blue: " + diffBlue+ "\n\n");
					
					actualPixel.r = (int)(diffRed * Math.pow(2, bitsPerColor-reduced_bits-1));
					actualPixel.g = (int)(diffGreen * Math.pow(2, bitsPerColor-reduced_bits-1));
					actualPixel.b = (int)(diffBlue * Math.pow(2, bitsPerColor-reduced_bits-1));
				}
			}
		}

		try {
			BmpWriter.write_bmp(out, bmp);
		} finally {
			out.close();
		}
	}
}
