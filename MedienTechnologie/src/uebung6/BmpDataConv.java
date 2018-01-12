package uebung6;

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
	protected String input2;
	protected String output1;
	protected String output2;
	protected String output3;
	protected String output4;
	protected boolean mean;
	protected boolean gradient;
	protected boolean median;
	protected boolean sobel;

	public BmpDataConv(String input, String input2, String output1, String output2, String output3, String output4, 
			boolean mean, boolean gradient, boolean median, boolean sobel){
		this.input = input;
		this.input2 = input2;
		this.output1 = output1;
		this.output2 = output2;
		this.output3 = output3;
		this.output4 = output4;
		this.mean = mean;
		this.gradient = gradient;
		this.median = median;
		this.sobel = sobel;
	}
	
	@Parameters
	public static List<Object[]> data() {
		return generateTestArrayList();
	}
	
	private static List<Object[]> generateTestArrayList() {
		String in1 = "./backupImage/Details_gray6.bmp";
		String in2 = "./backupImage/flaechen_gray6.bmp";
		String inFehler1 = "./backupImage/Details_gray_FehlerMin.bmp";
		String inFehler2 = "./backupImage/flaechen_gray_FehlerMin.bmp";
		return Arrays.asList(new Object[][] {
			
			// Aufgabe 1a und c
			{	in1, in2, 
				"./samples_uebung6/1a_Details_gray_Mean.bmp", "./samples_uebung6/1c_Details_gray_Mean_Diff.bmp", 
				"./samples_uebung6/1a_Flaeche_gray_Mean.bmp", "./samples_uebung6/1c_Flaeche_gray_Mean_Diff.bmp", 
				true, false, false, false},
			// Aufgabe 2a und b
			{	in1, in2, 
				"./samples_uebung6/2a_Details_gray_Gradient.bmp", "./samples_uebung6/2b_Details_gray_Gradient_Diff.bmp", 
				"./samples_uebung6/2a_Flaeche_gray_Gradient.bmp", "./samples_uebung6/2b_Flaeche_gray_Gradient_Diff.bmp", 
				false, true, false, false},
			// Aufgabe 3
			{	inFehler1, inFehler2, 
				"./samples_uebung6/3_Details_gray_Median.bmp", "./samples_uebung6/3_Details_gray_Median_Diff.bmp", 
				"./samples_uebung6/3_Flaeche_gray_Median.bmp", "./samples_uebung6/3_Flaeche_gray_Median_Diff.bmp", 
				false, false, true, false},
			// Aufgabe 4
			{	in1, in2, 
				"./samples_uebung6/4_Details_gray_Sobel_vertical.bmp", "./samples_uebung6/4_Details_gray_Sobel_horizontal.bmp", 
				"./samples_uebung6/4_Flaeche_gray_Sobel_vertical.bmp", "./samples_uebung6/4_Flaeche_gray_Sobel_horizontal.bmp", 
				false, false, false, true},
		});
	}
	
	@Test
	public void convertWavs() throws IOException {
		bmp_io.bmpConvert(new String[]{input, input2}, output1, output2, output3, output4, mean, gradient, median, sobel);
	}	
}
