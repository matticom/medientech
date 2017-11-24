package uebung2;

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
	protected String output;
	protected String writeValueTxt;
	protected boolean greyScale;
	protected int reductionFactor;
	protected boolean downsampling;
	protected boolean isHorizontalDS;
	protected int reduced_bits;
	protected boolean bitReduction;
	protected boolean bitNoise;

	public BmpDataConv(String input, String output, String writeValueTxt, boolean greyScale, int reductionFactor, boolean downsampling, boolean isHorizontalDS, int reduced_bits, boolean bitReduction, boolean bitNoise){
		this.input = input;
		this.output = output;
		this.writeValueTxt = writeValueTxt;
		this.greyScale = greyScale;
		this.reductionFactor = reductionFactor;
		this.downsampling = downsampling;
		this.isHorizontalDS = isHorizontalDS;
		this.reduced_bits = reduced_bits;
		this.bitReduction = bitReduction;
		this.bitNoise = bitNoise;
	}
	
	@Parameters
	public static List<Object[]> data() {
		return generateTestArrayList();
	}
	
	private static List<Object[]> generateTestArrayList() {
		return Arrays.asList(new Object[][] {
			// String input, String output, String writeValueTxt, boolean greyScale, int reductionFactor, boolean downsampling, 
			// boolean isHorizontalDS, int reduced_bits, boolean bitReduction, boolean bitNoise
			
//			{"./backupImage/Details_Carbon.bmp", "./samples/test.bmp", "./samples/test", false, 2, false, false, 1, false, false},
//			{"./backupImage/Flaechen_Carbon.bmp", "./samples/test.bmp", "./samples/test", false, 2, false, false, 1, false, false},
//			{"./backupImage/grating_V2.bmp", "./samples/test.bmp", "./samples/test", false, 2, false, false, 1, false, false},
//			{"./backupImage/grating_H2.bmp", "./samples/test.bmp", "./samples/test", false, 2, false, false, 1, false, false},
			
//			{"./backupImage/grating_V2.bmp", "./samples/horizontalDS2.bmp", "./samples/test", false, 2, true, false, 1, false, false},
//			{"./backupImage/grating_V2.bmp", "./samples/horizontalDS3.bmp", "./samples/test", false, 3, true, false, 1, false, false},
//			{"./backupImage/grating_V2.bmp", "./samples/horizontalDS4.bmp", "./samples/test", false, 4, true, false, 1, false, false},
//			{"./backupImage/grating_V2.bmp", "./samples/horizontalDS5.bmp", "./samples/test", false, 5, true, false, 1, false, false},
//			{"./backupImage/grating_V2.bmp", "./samples/horizontalDS6.bmp", "./samples/test", false, 6, true, false, 1, false, false},
//			{"./backupImage/grating_V2.bmp", "./samples/horizontalDS7.bmp", "./samples/test", false, 7, true, false, 1, false, false},
//			{"./backupImage/grating_V2.bmp", "./samples/horizontalDS8.bmp", "./samples/test", false, 8, true, false, 1, false, false},
//			{"./backupImage/grating_V2.bmp", "./samples/horizontalDS9.bmp", "./samples/test", false, 9, true, false, 1, false, false},
//			{"./backupImage/grating_V2.bmp", "./samples/horizontalDS10.bmp", "./samples/test", false, 10, true, false, 1, false, false},
//			{"./backupImage/grating_V2.bmp", "./samples/horizontalDS11.bmp", "./samples/test", false, 11, true, false, 1, false, false},
			
//			{"./backupImage/grating_H2.bmp", "./samples/verticalDS2.bmp", "./samples/test", false, 2, true, true, 1, false, false},
//			{"./backupImage/grating_H2.bmp", "./samples/verticalDS3.bmp", "./samples/test", false, 3, true, true, 1, false, false},
//			{"./backupImage/grating_H2.bmp", "./samples/verticalDS4.bmp", "./samples/test", false, 4, true, true, 1, false, false},
//			{"./backupImage/grating_H2.bmp", "./samples/verticalDS5.bmp", "./samples/test", false, 5, true, true, 1, false, false},
//			{"./backupImage/grating_H2.bmp", "./samples/verticalDS6.bmp", "./samples/test", false, 6, true, true, 1, false, false},
//			{"./backupImage/grating_H2.bmp", "./samples/verticalDS7.bmp", "./samples/test", false, 7, true, true, 1, false, false},
//			{"./backupImage/grating_H2.bmp", "./samples/verticalDS8.bmp", "./samples/test", false, 8, true, true, 1, false, false},
//			{"./backupImage/grating_H2.bmp", "./samples/verticalDS9.bmp", "./samples/test", false, 9, true, true, 1, false, false},
//			{"./backupImage/grating_H2.bmp", "./samples/verticalDS10.bmp", "./samples/test", false, 10, true, true, 1, false, false},
//			{"./backupImage/grating_H2.bmp", "./samples/verticalDS11.bmp", "./samples/test", false, 11, true, true, 1, false, false},

			
			{"./backupImage/Details_Carbon.bmp", "./samples/detail_gray.bmp", "./samples/test", true, 5, false, false, 1, false, false},
//			{"./backupImage/Flaechen_Carbon.bmp", "./samples/flaechen_gray.bmp", "./samples/test", true, 5, false, false, 1, false, false},
			
			
			// BitReduktion der beiden Bilder in verschiedenen Stufen
//			{"./backupImage/Details_Carbon.bmp", "./samples/Details_bitRed-1.bmp", "./samples/test", false, 2, false, false, 1, true, false},
//			{"./backupImage/Details_Carbon.bmp", "./samples/Details_bitRed-2.bmp", null, false, 2, false, false, 2, true, false},
//			{"./backupImage/Details_Carbon.bmp", "./samples/Details_bitRed-3.bmp", null, false, 2, false, false, 3, true, false},
//			{"./backupImage/Details_Carbon.bmp", "./samples/Details_bitRed-4.bmp", null, false, 2, false, false, 4, true, false},
//			{"./backupImage/Details_Carbon.bmp", "./samples/Details_bitRed-5.bmp", null, false, 2, false, false, 5, true, false},
//			{"./backupImage/Details_Carbon.bmp", "./samples/Details_bitRed-6.bmp", null, false, 2, false, false, 6, true, false},
//			{"./backupImage/Details_Carbon.bmp", "./samples/Details_bitRed-7.bmp", null, false, 2, false, false, 7, true, false},
//			{"./backupImage/Details_Carbon.bmp", "./samples/Details_bitRed-8.bmp", null, false, 2, false, false, 8, true, false},
//			
//			{"./backupImage/Flaechen_Carbon.bmp", "./samples/Flaechen_bitRed-1.bmp", "./samples/test", false, 2, false, false, 1, true, false},
//			{"./backupImage/Flaechen_Carbon.bmp", "./samples/Flaechen_bitRed-2.bmp", null, false, 2, false, false, 2, true, false},
//			{"./backupImage/Flaechen_Carbon.bmp", "./samples/Flaechen_bitRed-3.bmp", null, false, 2, false, false, 3, true, false},
//			{"./backupImage/Flaechen_Carbon.bmp", "./samples/Flaechen_bitRed-4.bmp", null, false, 2, false, false, 4, true, false},
//			{"./backupImage/Flaechen_Carbon.bmp", "./samples/Flaechen_bitRed-5.bmp", null, false, 2, false, false, 5, true, false},
//			{"./backupImage/Flaechen_Carbon.bmp", "./samples/Flaechen_bitRed-6.bmp", null, false, 2, false, false, 6, true, false},
//			{"./backupImage/Flaechen_Carbon.bmp", "./samples/Flaechen_bitRed-7.bmp", null, false, 2, false, false, 7, true, false},
//			{"./backupImage/Flaechen_Carbon.bmp", "./samples/Flaechen_bitRed-8.bmp", null, false, 2, false, false, 8, true, false},
			
			// BitNoise der beiden Bilder in verschiedenen Stufen
//			{"./backupImage/Details_Carbon.bmp", "./samples/Details_bitNoise-1.bmp", null, false, 2, false, false, 1, false, true},
//			{"./backupImage/Details_Carbon.bmp", "./samples/Details_bitNoise-2.bmp", null, false, 2, false, false, 2, false, true},
//			{"./backupImage/Details_Carbon.bmp", "./samples/Details_bitNoise-3.bmp", null, false, 2, false, false, 3, false, true},
//			{"./backupImage/Details_Carbon.bmp", "./samples/Details_bitNoise-4.bmp", null, false, 2, false, false, 4, false, true},
//			{"./backupImage/Details_Carbon.bmp", "./samples/Details_bitNoise-5.bmp", null, false, 2, false, false, 5, false, true},
//			{"./backupImage/Details_Carbon.bmp", "./samples/Details_bitNoise-6.bmp", null, false, 2, false, false, 6, false, true},
//			{"./backupImage/Details_Carbon.bmp", "./samples/Details_bitNoise-7.bmp", null, false, 2, false, false, 7, false, true},
//			{"./backupImage/Details_Carbon.bmp", "./samples/Details_bitNoise-8.bmp", null, false, 2, false, false, 8, false, true},
//			
//			{"./backupImage/Flaechen_Carbon.bmp", "./samples/Flaechen_bitNoise-1.bmp", null, false, 2, false, false, 1, false, true},
//			{"./backupImage/Flaechen_Carbon.bmp", "./samples/Flaechen_bitNoise-2.bmp", null, false, 2, false, false, 2, false, true},
//			{"./backupImage/Flaechen_Carbon.bmp", "./samples/Flaechen_bitNoise-3.bmp", null, false, 2, false, false, 3, false, true},
//			{"./backupImage/Flaechen_Carbon.bmp", "./samples/Flaechen_bitNoise-4.bmp", null, false, 2, false, false, 4, false, true},
//			{"./backupImage/Flaechen_Carbon.bmp", "./samples/Flaechen_bitNoise-5.bmp", null, false, 2, false, false, 5, false, true},
//			{"./backupImage/Flaechen_Carbon.bmp", "./samples/Flaechen_bitNoise-6.bmp", null, false, 2, false, false, 6, false, true},
//			{"./backupImage/Flaechen_Carbon.bmp", "./samples/Flaechen_bitNoise-7.bmp", null, false, 2, false, false, 7, false, true},
//			{"./backupImage/Flaechen_Carbon.bmp", "./samples/Flaechen_bitNoise-8.bmp", null, false, 2, false, false, 8, false, true},
		});
	}
	
	@Test
	public void convertWavs() throws IOException {
		bmp_io.bmpConvert(new String[]{input,  output}, writeValueTxt, greyScale, reductionFactor, downsampling, isHorizontalDS, reduced_bits, bitReduction, bitNoise);
	}	
}
