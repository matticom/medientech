package uebung4;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class BmpDataConv {

	protected String input;
	protected boolean RGBpics;
	protected boolean YCbCr;
	protected boolean YCbCrToRGB;
	protected boolean histogram;
	protected String histogramOutput;
	protected boolean meanBrightness;
	protected boolean contrast;
	protected double contrastFactor;
	protected String contrastOutput;
	protected boolean changeBrightness;
	protected int de_increaseBrightness;
	protected String brightnessOutput;

	public BmpDataConv(String input, boolean RGBpics, boolean YCbCr, boolean YCbCrToRGB, boolean histogram, String histogramOutput, boolean meanBrightness, 
			boolean contrast, double contrastFactor, String contrastOutput, boolean changeBrightness, int de_increaseBrightness, String brightnessOutput){
		this.input = input;
		this.RGBpics = RGBpics;
		this.YCbCr = YCbCr;
		this.YCbCrToRGB = YCbCrToRGB;
		this.histogram = histogram;
		this.histogramOutput = histogramOutput;
		this.meanBrightness = meanBrightness;
		this.contrast = contrast;
		this.contrastFactor = contrastFactor;
		this.contrastOutput = contrastOutput;
		this.changeBrightness = changeBrightness;
		this.de_increaseBrightness = de_increaseBrightness;
		this.brightnessOutput = brightnessOutput;
	}
	
	@Parameters
	public static List<Object[]> data() {
		return generateTestArrayList();
	}
	
	private static List<Object[]> generateTestArrayList() {
		return Arrays.asList(new Object[][] {
//			String input, boolean RGBpics, boolean YCbCr, boolean YCbCrToRGB, boolean histogram, String histogramOutput, boolean meanBrightness, 
//			boolean contrast, double contrastFactor, String contrastOutput, boolean changeBrightness, int de_increaseBrightness, String brightnessOutput
			
			// Aufgabe 1 b
			{"./backupImage/Details_Carbon.bmp", true, false, false, false, null, false, false, Double.NEGATIVE_INFINITY, null, false, 0, null},
			// Aufgabe 1 c
			{"./backupImage/Details_Carbon.bmp", false, true, false, false, null, false, false, Double.NEGATIVE_INFINITY, null, false, 0, null},
			// Aufgabe 1 d
			{"./backupImage/Details_Carbon.bmp", false, false, true, false, null, false, false, Double.NEGATIVE_INFINITY, null, false, 0, null},
			// Aufgabe 2 und 3 a
			{"./backupImage/Detail_Grayscale_Carbon.bmp", false, false, false, true, "./samples_uebung4/Histogramm_Detail_Grayscale.txt", true, true, Double.NEGATIVE_INFINITY, null, false, 0, null},
//			{"./samples_uebung4/Details_Y_Anteil.bmp", false, false, false, true, "./samples_uebung4/Histogramm_Detail_Grayscale.txt", true, false, Double.NEGATIVE_INFINITY, null, false, 0, null},
			
			// Aufgabe 3 b (Input noch auf "./samples_uebung4/Details_Y_Anteil.bmp" umstellen!!!)
			{"./backupImage/Detail_Grayscale_Carbon.bmp", false, false, false, false, null, false, false, Double.NEGATIVE_INFINITY, null, true, 20, "./samples_uebung4/Brightness+20.bmp"},
			{"./backupImage/Detail_Grayscale_Carbon.bmp", false, false, false, false, null, false, false, Double.NEGATIVE_INFINITY, null, true, 40, "./samples_uebung4/Brightness+40.bmp"},
			{"./backupImage/Detail_Grayscale_Carbon.bmp", false, false, false, false, null, false, false, Double.NEGATIVE_INFINITY, null, true, 60, "./samples_uebung4/Brightness+60.bmp"},
			{"./backupImage/Detail_Grayscale_Carbon.bmp", false, false, false, false, null, false, false, Double.NEGATIVE_INFINITY, null, true, 80, "./samples_uebung4/Brightness+80.bmp"},
			{"./backupImage/Detail_Grayscale_Carbon.bmp", false, false, false, false, null, false, false, Double.NEGATIVE_INFINITY, null, true, 100, "./samples_uebung4/Brightness+100.bmp"},
			{"./backupImage/Detail_Grayscale_Carbon.bmp", false, false, false, false, null, false, false, Double.NEGATIVE_INFINITY, null, true, 120, "./samples_uebung4/Brightness+120.bmp"},
			{"./backupImage/Detail_Grayscale_Carbon.bmp", false, false, false, false, null, false, false, Double.NEGATIVE_INFINITY, null, true, 140, "./samples_uebung4/Brightness+140.bmp"},
			{"./backupImage/Detail_Grayscale_Carbon.bmp", false, false, false, false, null, false, false, Double.NEGATIVE_INFINITY, null, true, 160, "./samples_uebung4/Brightness+160.bmp"},
			{"./backupImage/Detail_Grayscale_Carbon.bmp", false, false, false, false, null, false, false, Double.NEGATIVE_INFINITY, null, true, 180, "./samples_uebung4/Brightness+180.bmp"},
			{"./backupImage/Detail_Grayscale_Carbon.bmp", false, false, false, false, null, false, false, Double.NEGATIVE_INFINITY, null, true, 200, "./samples_uebung4/Brightness+200.bmp"},
			{"./backupImage/Detail_Grayscale_Carbon.bmp", false, false, false, false, null, false, false, Double.NEGATIVE_INFINITY, null, true, 220, "./samples_uebung4/Brightness+220.bmp"},
			{"./backupImage/Detail_Grayscale_Carbon.bmp", false, false, false, false, null, false, false, Double.NEGATIVE_INFINITY, null, true, 240, "./samples_uebung4/Brightness+240.bmp"},
			{"./backupImage/Detail_Grayscale_Carbon.bmp", false, false, false, false, null, false, false, Double.NEGATIVE_INFINITY, null, true, -20, "./samples_uebung4/Brightness-20.bmp"},
			{"./backupImage/Detail_Grayscale_Carbon.bmp", false, false, false, false, null, false, false, Double.NEGATIVE_INFINITY, null, true, -40, "./samples_uebung4/Brightness-40.bmp"},
			{"./backupImage/Detail_Grayscale_Carbon.bmp", false, false, false, false, null, false, false, Double.NEGATIVE_INFINITY, null, true, -60, "./samples_uebung4/Brightness-60.bmp"},
			{"./backupImage/Detail_Grayscale_Carbon.bmp", false, false, false, false, null, false, false, Double.NEGATIVE_INFINITY, null, true, -80, "./samples_uebung4/Brightness-80.bmp"},
			{"./backupImage/Detail_Grayscale_Carbon.bmp", false, false, false, false, null, false, false, Double.NEGATIVE_INFINITY, null, true, -100, "./samples_uebung4/Brightness-100.bmp"},
			{"./backupImage/Detail_Grayscale_Carbon.bmp", false, false, false, false, null, false, false, Double.NEGATIVE_INFINITY, null, true, -120, "./samples_uebung4/Brightness-120.bmp"},
			{"./backupImage/Detail_Grayscale_Carbon.bmp", false, false, false, false, null, false, false, Double.NEGATIVE_INFINITY, null, true, -140, "./samples_uebung4/Brightness-140.bmp"},
			{"./backupImage/Detail_Grayscale_Carbon.bmp", false, false, false, false, null, false, false, Double.NEGATIVE_INFINITY, null, true, -160, "./samples_uebung4/Brightness-160.bmp"},
			{"./backupImage/Detail_Grayscale_Carbon.bmp", false, false, false, false, null, false, false, Double.NEGATIVE_INFINITY, null, true, -180, "./samples_uebung4/Brightness-180.bmp"},
			{"./backupImage/Detail_Grayscale_Carbon.bmp", false, false, false, false, null, false, false, Double.NEGATIVE_INFINITY, null, true, -200, "./samples_uebung4/Brightness-200.bmp"},
			{"./backupImage/Detail_Grayscale_Carbon.bmp", false, false, false, false, null, false, false, Double.NEGATIVE_INFINITY, null, true, -220, "./samples_uebung4/Brightness-220.bmp"},
			{"./backupImage/Detail_Grayscale_Carbon.bmp", false, false, false, false, null, false, false, Double.NEGATIVE_INFINITY, null, true, -240, "./samples_uebung4/Brightness-240.bmp"},
		
			{"./samples_uebung4/Brightness+XXX.bmp", false, false, false, true, "./samples_uebung4/Histogramm_Brightest.txt", true, true, Double.NEGATIVE_INFINITY, null, false, 0, null},
			{"./samples_uebung4/Brightness-XXX.bmp", false, false, false, true, "./samples_uebung4/Histogramm_Darkest.txt", true, true, Double.NEGATIVE_INFINITY, null, false, 0, null},
			
			// Aufgabe 3 c
//			{"./samples_uebung4/Details_Y_Anteil.bmp", false, false, false, false, null, false, false, 0.2, "./samples_uebung4/Contrast_0.2.bmp", false, 0, null},
//			{"./samples_uebung4/Details_Y_Anteil.bmp", false, false, false, false, null, false, false, 0.4, "./samples_uebung4/Contrast_0.4.bmp", false, 0, null},
//			{"./samples_uebung4/Details_Y_Anteil.bmp", false, false, false, false, null, false, false, 0.6, "./samples_uebung4/Contrast_0.6.bmp", false, 0, null},
//			{"./samples_uebung4/Details_Y_Anteil.bmp", false, false, false, false, null, false, false, 0.8, "./samples_uebung4/Contrast_0.8.bmp", false, 0, null},
//			{"./samples_uebung4/Details_Y_Anteil.bmp", false, false, false, false, null, false, false, 1.0, "./samples_uebung4/Contrast_1.0.bmp", false, 0, null},
//			{"./samples_uebung4/Details_Y_Anteil.bmp", false, false, false, false, null, false, false, 1.5, "./samples_uebung4/Contrast_1.5.bmp", false, 0, null},
//			{"./samples_uebung4/Details_Y_Anteil.bmp", false, false, false, false, null, false, false, 2.5, "./samples_uebung4/Contrast_2.5.bmp", false, 0, null},
//			{"./samples_uebung4/Details_Y_Anteil.bmp", false, false, false, false, null, false, false, 5.0, "./samples_uebung4/Contrast_5.0.bmp", false, 0, null},
//			{"./samples_uebung4/Details_Y_Anteil.bmp", false, false, false, false, null, false, false, 10.0, "./samples_uebung4/Contrast_10.0.bmp", false, 0, null},
//			
//			{"./samples_uebung4/Details_Y_Anteil.bmp", false, false, false, false, null, false, false, -0.6, "./samples_uebung4/Contrast_-0.6.bmp", false, 0, null},
//			
//			{"./samples_uebung4/Contrast_0.X.bmp", false, false, false, true, "./samples_uebung4/Histogramm_Least_Contrast.txt", true, true, Double.NEGATIVE_INFINITY, null, false, 0, null},
//			{"./samples_uebung4/Contrast_X.X.bmp", false, false, false, true, "./samples_uebung4/Histogramm_Most_Contrast.txt", true, true, Double.NEGATIVE_INFINITY, null, false, 0, null},
		
		});
	}
	
	@Test
	public void convertWavs() throws IOException {
		bmp_io.bmpConvert(new String[]{input}, RGBpics, YCbCr, YCbCrToRGB, histogram, histogramOutput, meanBrightness, 
				contrast, contrastFactor, contrastOutput, changeBrightness, de_increaseBrightness, brightnessOutput);
	}	
}
