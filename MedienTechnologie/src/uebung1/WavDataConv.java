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
//			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-1Bit.wav", 1, "./samples/Sprache_Carbon_-1Bit.txt", false, true, false},
//			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-2Bit.wav", 2, "./samples/Sprache_Carbon_-2Bit.txt", false, true, false},
//			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-3Bit.wav", 3, "./samples/Sprache_Carbon_-3Bit.txt", false, true, false},
//			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-4Bit.wav", 4, "./samples/Sprache_Carbon_-4Bit.txt", false, true, false},
//			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-5Bit.wav", 5, "./samples/Sprache_Carbon_-5Bit.txt", false, true, false},
//			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-6Bit.wav", 6, "./samples/Sprache_Carbon_-6Bit.txt", false, true, false},
//			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-7Bit.wav", 7, "./samples/Sprache_Carbon_-7Bit.txt", false, true, false},
//			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-8Bit.wav", 8, "./samples/Sprache_Carbon_-8Bit.txt", false, true, false},
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
			
			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-1BitNoise.wav", 1, "./samples/Musik_Carbon_-1BitNoise.txt", false, false, true}, 
			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-2BitNoise.wav", 2, "./samples/Musik_Carbon_-2BitNoise.txt", false, false, true},
			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-3BitNoise.wav", 3, "./samples/Musik_Carbon_-3BitNoise.txt", false, false, true},
			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-4BitNoise.wav", 4, "./samples/Musik_Carbon_-4BitNoise.txt", false, false, true},
			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-5BitNoise.wav", 5, "./samples/Musik_Carbon_-5BitNoise.txt", false, false, true},
		});
	}
	
	@Test
	public void convertWavs() {
		wave_io.waveConvert(new String[]{input,  output}, reduced_bits, writeValueTxt, downsampling, bitReduction, bitNoise);
	}	
}
