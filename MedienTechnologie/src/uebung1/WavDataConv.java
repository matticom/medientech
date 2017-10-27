package uebung1;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class WavDataConv {

	protected String input;
	protected String output;
	protected int reduced_bits;
	protected String writeValueTxt;
	protected boolean downsampling;
	protected boolean bitReduction;
	protected boolean bitNoise;

	
	public WavDataConv(String input, String output, int reduced_bits, String writeValueTxt, boolean downsampling, boolean bitReduction, boolean bitNoise){
		this.input = input;
		this.output = output;
		this.reduced_bits = reduced_bits;
		this.writeValueTxt = writeValueTxt;
		this.downsampling = downsampling;
		this.bitReduction = bitReduction;
		this.bitNoise = bitNoise;
	}
	
	@Parameters
	public static List<Object[]> data() {
		return generateTestArrayList();
	}
	
	private static List<Object[]> generateTestArrayList() {
		return Arrays.asList(new Object[][] {
			//(String[] inOut, 	int reduced_bits, 	String writeValueTxt, 	boolean downsampling, 	boolean bitReduction, 	boolean bitNoise) 
			
			{"./samples/sine_lo01.wav", null, 0, "./samples/sine_lo01.txt", false, false, false},
			{"./samples/sine_hi01.wav", null, 0, "./samples/sine_hi01.txt", false, false, false},
//			
//			
//			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-1Bit.wav", 1, "./samples/Sprache_Carbon_-1Bit.txt", false, true, false},
//			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-2Bit.wav", 2, "./samples/Sprache_Carbon_-2Bit.txt", false, true, false},
//			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-3Bit.wav", 3, "./samples/Sprache_Carbon_-3Bit.txt", false, true, false},
//			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-4Bit.wav", 4, "./samples/Sprache_Carbon_-4Bit.txt", false, true, false},
//			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-5Bit.wav", 5, "./samples/Sprache_Carbon_-5Bit.txt", false, true, false},
//			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-6Bit.wav", 6, "./samples/Sprache_Carbon_-6Bit.txt", false, true, false},
//			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-7Bit.wav", 7, "./samples/Sprache_Carbon_-7Bit.txt", false, true, false},
//			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-8Bit.wav", 8, "./samples/Sprache_Carbon_-8Bit.txt", false, true, false},
//			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-9Bit.wav", 9, "./samples/Sprache_Carbon_-9Bit.txt", false, true, false},
//			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-10Bit.wav", 10, "./samples/Sprache_Carbon_-10Bit.txt", false, true, false},
//			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-11Bit.wav", 11, "./samples/Sprache_Carbon_-11Bit.txt", false, true, false},
//			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-12Bit.wav", 12, "./samples/Sprache_Carbon_-12Bit.txt", false, true, false},
//			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-13Bit.wav", 13, "./samples/Sprache_Carbon_-13Bit.txt", false, true, false},
//			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-14Bit.wav", 14, "./samples/Sprache_Carbon_-14Bit.txt", false, true, false},
//			
//
//			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-1Bit.wav", 1, "./samples/Musik_Carbon_-1Bit.txt", false, true, false}, 
//			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-2Bit.wav", 2, "./samples/Musik_Carbon_-2Bit.txt", false, true, false},
//			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-3Bit.wav", 3, "./samples/Musik_Carbon_-3Bit.txt", false, true, false},
//			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-4Bit.wav", 4, "./samples/Musik_Carbon_-4Bit.txt", false, true, false},
//			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-5Bit.wav", 5, "./samples/Musik_Carbon_-5Bit.txt", false, true, false},
//			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-6Bit.wav", 6, "./samples/Musik_Carbon_-6Bit.txt", false, true, false},
//			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-7Bit.wav", 7, "./samples/Musik_Carbon_-7Bit.txt", false, true, false},
//			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-8Bit.wav", 8, "./samples/Musik_Carbon_-8Bit.txt", false, true, false},
//			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-9Bit.wav", 9, "./samples/Musik_Carbon_-9Bit.txt", false, true, false},
//			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-10Bit.wav", 10, "./samples/Musik_Carbon_-10Bit.txt", false, true, false},
//			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-11Bit.wav", 11, "./samples/Musik_Carbon_-11Bit.txt", false, true, false}, 
//			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-12Bit.wav", 12, "./samples/Musik_Carbon_-12Bit.txt", false, true, false},
//			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-13Bit.wav", 13, "./samples/Musik_Carbon_-13Bit.txt", false, true, false}, 
//			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-14Bit.wav", 14, "./samples/Musik_Carbon_-14Bit.txt", false, true, false},
//			
//			
//			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-1Bit_Noise.wav", 1, "./samples/Sprache_Carbon_-1Bit_Noise.wav", false, false, true}, 
//			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-2Bit_Noise.wav", 2, "./samples/Sprache_Carbon_-2Bit_Noise.wav", false, false, true},
//			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-3Bit_Noise.wav", 3, "./samples/Sprache_Carbon_-3Bit_Noise.wav", false, false, true},
//			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-4Bit_Noise.wav", 4, "./samples/Sprache_Carbon_-4Bit_Noise.wav", false, false, true},
//			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-5Bit_Noise.wav", 5, "./samples/Sprache_Carbon_-5Bit_Noise.wav", false, false, true},
//			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-6Bit_Noise.wav", 6, "./samples/Sprache_Carbon_-6Bit_Noise.wav", false, false, true},
//			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-7Bit_Noise.wav", 7, "./samples/Sprache_Carbon_-7Bit_Noise.wav", false, false, true},
//			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-8Bit_Noise.wav", 8, "./samples/Sprache_Carbon_-8Bit_Noise.wav", false, false, true},
//			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-9Bit_Noise.wav", 9, "./samples/Sprache_Carbon_-9Bit_Noise.wav", false, false, true},
//			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-10Bit_Noise.wav", 10, "./samples/Sprache_Carbon_-10Bit_Noise.wav", false, false, true},
//			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-11Bit_Noise.wav", 11, "./samples/Sprache_Carbon_-11Bit_Noise.wav", false, false, true}, 
//			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-12Bit_Noise.wav", 12, "./samples/Sprache_Carbon_-12Bit_Noise.wav", false, false, true},
//			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-13Bit_Noise.wav", 13, "./samples/Sprache_Carbon_-13Bit_Noise.wav", false, false, true}, 
//			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-14Bit_Noise.wav", 14, "./samples/Sprache_Carbon_-14Bit_Noise.wav", false, false, true},
//			
//			
//			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-1BitNoise.wav", 1, "./samples/Musik_Carbon_-1BitNoise.txt", false, false, true}, 
//			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-2BitNoise.wav", 2, "./samples/Musik_Carbon_-2BitNoise.txt", false, false, true},
//			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-3BitNoise.wav", 3, "./samples/Musik_Carbon_-3BitNoise.txt", false, false, true},
//			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-4BitNoise.wav", 4, "./samples/Musik_Carbon_-4BitNoise.txt", false, false, true},
//			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-5BitNoise.wav", 5, "./samples/Musik_Carbon_-5BitNoise.txt", false, false, true},
//			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-6BitNoise.wav", 6, "./samples/Musik_Carbon_-6BitNoise.txt", false, false, true}, 
//			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-7BitNoise.wav", 7, "./samples/Musik_Carbon_-7BitNoise.txt", false, false, true},
//			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-8BitNoise.wav", 8, "./samples/Musik_Carbon_-8BitNoise.txt", false, false, true},
//			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-9BitNoise.wav", 9, "./samples/Musik_Carbon_-9BitNoise.txt", false, false, true},
//			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-10BitNoise.wav", 10, "./samples/Musik_Carbon_-10BitNoise.txt", false, false, true},
//			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-11BitNoise.wav", 11, "./samples/Musik_Carbon_-11BitNoise.txt", false, false, true}, 
//			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-12BitNoise.wav", 12, "./samples/Musik_Carbon_-12BitNoise.txt", false, false, true},
//			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-13BitNoise.wav", 13, "./samples/Musik_Carbon_-13BitNoise.txt", false, false, true}, 
//			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-14BitNoise.wav", 14, "./samples/Musik_Carbon_-14BitNoise.txt", false, false, true},
//			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-16BitNoise.wav", 16, "./samples/Musik_Carbon_-16BitNoise.txt", false, false, true},
			
		});
	}
	
	@Test
	public void convertWavs() {
		wave_io.waveConvert(new String[]{input,  output}, reduced_bits, writeValueTxt, downsampling, bitReduction, bitNoise);
	}	
}
