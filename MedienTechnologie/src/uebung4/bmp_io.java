package uebung4;

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
	public static void bmpConvert(String[] args, boolean RGBpics, boolean YCbCr, boolean YCbCrToRGB, boolean histogram, String histogramOutput, boolean meanBrightness, 
			boolean contrast, double contrastFactor, String contrastOutput, boolean changeBrightness, int de_increaseBrightness, String brightnessOutput) throws IOException {
		String inFilename = null;
		String outFilename = null;
		PixelColor pc = null;
		BmpImage bmp = null;

		if (args.length < 1)
			System.out.println("At least one filename specified  (" + args.length + ")");

		inFilename = args[0];
		InputStream in = new FileInputStream(inFilename);
		bmp = BmpReader.read_bmp(in);
		in.close();

		if (RGBpics) {
			try (	InputStream inR = new FileInputStream(inFilename);
					InputStream inB = new FileInputStream(inFilename);
					InputStream inG = new FileInputStream(inFilename);
					OutputStream outR = new FileOutputStream("./samples_uebung4/Details_Rot_Anteil.bmp");
					OutputStream outB = new FileOutputStream("./samples_uebung4/Details_Blau_Anteil.bmp");
					OutputStream outG = new FileOutputStream("./samples_uebung4/Details_Gruen_Anteil.bmp");	) {

				BmpImage rPic = BmpReader.read_bmp(inR);
				BmpImage bPic = BmpReader.read_bmp(inB);
				BmpImage gPic = BmpReader.read_bmp(inG);
				PixelColor rPicPixel = null;
				PixelColor bPicPixel = null;
				PixelColor gPicPixel = null;

				for (int x = 0; x < bmp.image.getWidth(); x++) {
					for (int y = 0; y < bmp.image.getHeight(); y++) {
						rPicPixel = rPic.image.getRgbPixel(x, y);
						rPicPixel.g = 0;
						rPicPixel.b = 0;
//						System.out.println("Vorher\n" + bPic.image.getRgbPixel(x, y));
						bPicPixel = bPic.image.getRgbPixel(x, y);
						bPicPixel.g = 0;
						bPicPixel.r = 0;
//						System.out.println("Nachher\n" + bPic.image.getRgbPixel(x, y));
						gPicPixel = gPic.image.getRgbPixel(x, y);
						gPicPixel.b = 0;
						gPicPixel.r = 0;
						
					}
				}

				BmpWriter.write_bmp(outR, rPic);
				BmpWriter.write_bmp(outG, gPic);
				BmpWriter.write_bmp(outB, bPic);
			}
		}

		if (YCbCr) {
			// Y = R Kanal * 0.299, G Kanal * 0.587, B Kanal * 0.114
			// Cb = R Kanal * -0.169, G Kanal * -0.331, B Kanal * 0.5,
			// Cr = 0.5 * R -0.419 * G – 0.081 * B

			try (	InputStream inRGB = new FileInputStream(inFilename);
					InputStream inY = new FileInputStream(inFilename);
					InputStream inCb = new FileInputStream(inFilename);
					InputStream inCr = new FileInputStream(inFilename);
					OutputStream outY = new FileOutputStream("./samples_uebung4/Details_Y_Anteil.bmp");
					OutputStream outCb = new FileOutputStream("./samples_uebung4/Details_Cb_Anteil.bmp");
					OutputStream outCr = new FileOutputStream("./samples_uebung4/Details_Cr_Anteil.bmp");	) {

				BmpImage rgbPic = BmpReader.read_bmp(inRGB);
				BmpImage yPic = BmpReader.read_bmp(inY);
				BmpImage cbPic = BmpReader.read_bmp(inCb);
				BmpImage crPic = BmpReader.read_bmp(inCr);
				
				PixelColor rgbPicPixel = null;
				PixelColor yPicPixel = null;
				PixelColor cbPicPixel = null;
				PixelColor crPicPixel = null;

				for (int x = 0; x < bmp.image.getWidth(); x++) {
					for (int y = 0; y < bmp.image.getHeight(); y++) {
						rgbPicPixel = rgbPic.image.getRgbPixel(x, y);
						
						yPicPixel = yPic.image.getRgbPixel(x, y);
						int grey = (int) (0.299 * rgbPicPixel.r + 0.587 * rgbPicPixel.g + 0.114 * rgbPicPixel.b);
						yPicPixel.r = grey;
						yPicPixel.g = grey;
						yPicPixel.b = grey;
						cbPicPixel = cbPic.image.getRgbPixel(x, y);
						cbPicPixel.r = (int) (rgbPicPixel.r * -0.169 + 128);
						cbPicPixel.g = (int) (rgbPicPixel.g * -0.331 + 128);
						cbPicPixel.b = (int) (rgbPicPixel.b * 0.5 + 128);
						crPicPixel = crPic.image.getRgbPixel(x, y);
						crPicPixel.r = (int) (rgbPicPixel.r * 0.5 + 128);
						crPicPixel.g = (int) (rgbPicPixel.g * -0.419 + 128);
						crPicPixel.b = (int) (rgbPicPixel.b * -0.081 + 128);
					}
				}
				BmpWriter.write_bmp(outY, yPic);
				BmpWriter.write_bmp(outCr, cbPic);
				BmpWriter.write_bmp(outCb, crPic);
			}
		}

		if (YCbCrToRGB) {
			// R = Y + 1.403 (Cr-128)
			// G = Y – 0.344 (Cb-128) – 0.714 (Cr -128)
			// B = Y + 1.773 (Cb-128)
			
			try (	InputStream inRGB = new FileInputStream(inFilename);
					OutputStream outRGB = new FileOutputStream("./samples_uebung4/Details_YCbCr2RGB.bmp");) {
				
				BmpImage yPic = BmpReader.read_bmp(inRGB);
				PixelColor yPicPixel = null;

				for(int n = 0; n < yPic.image.getHeight(); n++) {
		            for (int m = 0; m < yPic.image.getWidth(); m++) {
		                yPicPixel = yPic.image.getRgbPixel(m,n);

		                // RGB to YCbCr
		                double lum = 0.299 * yPicPixel.r + 0.587 * yPicPixel.g + 0.114 * yPicPixel.b;
		                double cb = 128 + (-0.169) * yPicPixel.r + (- 0.331 * yPicPixel.g) + 0.5 * yPicPixel.b;
		                double cr = 128 + 0.5 * yPicPixel.r + (-0.419 * yPicPixel.g) + (-0.081 * yPicPixel.b);

		                // YCbCr to RGB
		                yPicPixel.r = (int) (lum + 1.402 * (cr - 128));
		                yPicPixel.g = (int) (lum - 0.344 * (cb - 128) - 0.714 * (cr - 128));
		                yPicPixel.b = (int) (lum + 1.772 * (cb - 128));

		                yPic.image.setRgbPixel(m,n,yPicPixel);
		            }
		        }
				BmpWriter.write_bmp(outRGB, yPic);				
			}
		}
		
		if (contrastFactor > Double.NEGATIVE_INFINITY) {
			try (OutputStream out = new FileOutputStream(contrastOutput);) {

				for (int x = 0; x < bmp.image.getWidth(); x++) {
					for (int y = 0; y < bmp.image.getHeight(); y++) {
						int red = (int)((bmp.image.getRgbPixel(x, y).r - 128) * contrastFactor + 128);
						int green = (int)((bmp.image.getRgbPixel(x, y).g - 128) * contrastFactor + 128);
						int blue = (int)((bmp.image.getRgbPixel(x, y).b - 128) * contrastFactor + 128);
				
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
						bmp.image.setRgbPixel(x, y, new PixelColor(blue, green, red));
					}
				}
				BmpWriter.write_bmp(out, bmp);
			}
		}
		
		if (contrast) {
//			Kontrast = WU[  1/Pixel_Anzahl   *  für_alle_Pixel_aufsum( (Helligkeit_des_Pixel – H)² )  ]
			int calcMeanBrightness = meanBrightnessCalc(bmp);
			int pixelQuantity = bmp.image.getWidth() * bmp.image.getHeight();
			int greyValue = 0;
			int pixelVarianceSum = 0;
			for (int x = 0; x < bmp.image.getWidth(); x++) {
				for (int y = 0; y < bmp.image.getHeight(); y++) {
					greyValue = bmp.image.getRgbPixel(x, y).r;
					pixelVarianceSum = pixelVarianceSum + (int)Math.pow(greyValue - calcMeanBrightness, 2);
				}
			}
			double contrastValue = Math.sqrt(pixelVarianceSum / pixelQuantity);
			System.out.println("Der Kontrast von " + inFilename + " beträgt: " + contrastValue);
		}
		
		if (changeBrightness) {
			try (OutputStream out = new FileOutputStream(brightnessOutput);) {

				for (int x = 0; x < bmp.image.getWidth(); x++) {
					for (int y = 0; y < bmp.image.getHeight(); y++) {
						bmp.image.getRgbPixel(x, y).r += de_increaseBrightness;
						bmp.image.getRgbPixel(x, y).g += de_increaseBrightness;
						bmp.image.getRgbPixel(x, y).b += de_increaseBrightness;
						if (bmp.image.getRgbPixel(x, y).r < 0) {
							bmp.image.getRgbPixel(x, y).r = 0;
						}
						if (bmp.image.getRgbPixel(x, y).r > 255) {
							bmp.image.getRgbPixel(x, y).r = 255;
						}
						if (bmp.image.getRgbPixel(x, y).g < 0) {
							bmp.image.getRgbPixel(x, y).g = 0;
						}
						if (bmp.image.getRgbPixel(x, y).g > 255) {
							bmp.image.getRgbPixel(x, y).g = 255;
						}
						if (bmp.image.getRgbPixel(x, y).b < 0) {
							bmp.image.getRgbPixel(x, y).b = 0;
						}
						if (bmp.image.getRgbPixel(x, y).b > 255) {
							bmp.image.getRgbPixel(x, y).b = 255;
						}
					}
				}
				BmpWriter.write_bmp(out, bmp);
			}
		}
		
		if (meanBrightness) {
			System.out.println("Die mittlere Helligkeit von " + inFilename + " beträgt: " + meanBrightnessCalc(bmp));
		}
		
		if (histogram) {
			try (	FileWriter fwHistogram = new FileWriter(new File(histogramOutput));
					PrintWriter pwHistogram = new PrintWriter(fwHistogram, true);	) {

				int[] histoArray = new int[256];
				int greyValue = 0;
				
				for (int x = 0; x < bmp.image.getWidth(); x++) {
					for (int y = 0; y < bmp.image.getHeight(); y++) {
						greyValue = bmp.image.getRgbPixel(x, y).r;
						histoArray[greyValue]++;
					}
				}

				for (int i = 0; i < 256; i++){
					pwHistogram.println(histoArray[i]);
				}
			}
		}
	}
	
	private static int meanBrightnessCalc(BmpImage bmp) {
		int allPixelBrightness = 0;
		int greyValue = 0;
		
		for (int x = 0; x < bmp.image.getWidth(); x++) {
			for (int y = 0; y < bmp.image.getHeight(); y++) {
				greyValue = bmp.image.getRgbPixel(x, y).r;
				allPixelBrightness = allPixelBrightness + greyValue;
			}
		}
		return allPixelBrightness / (bmp.image.getWidth() * bmp.image.getHeight());
	}
}
