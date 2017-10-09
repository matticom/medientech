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

	
	public WavDataConv(String input, String output, int reduced_bits){
		this.input = input;
		this.output = output;
		this.reduced_bits = reduced_bits;
	}
	
	@Parameters
	public static List<Object[]> data() {
		return generateTestArrayList();
	}
	
	private static List<Object[]> generateTestArrayList() {
		return Arrays.asList(new Object[][] {
			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-1Bit.wav", 1},
			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-2Bit.wav", 2},
			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-3Bit.wav", 3},
			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-4Bit.wav", 4},
			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-5Bit.wav", 5},
			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-6Bit.wav", 6},
			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-7Bit.wav", 7},
			{"./samples/Sprache_Carbon.wav", "./samples/Sprache_Carbon_-8Bit.wav", 8},
			
			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-1Bit.wav", 1}, 
			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-2Bit.wav", 2},
			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-3Bit.wav", 3},
			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-4Bit.wav", 4},
			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-5Bit.wav", 5},
			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-6Bit.wav", 6},
			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-7Bit.wav", 7},
			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-8Bit.wav", 8},
			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-9Bit.wav", 9},
			{"./samples/Musik_Carbon.wav", "./samples/Musik_Carbon_-10Bit.wav", 10},
		});
	}
	
	@Test
	public void convertWavs() {
		wave_io.waveConvert(new String[]{input,  output}, reduced_bits);
	}	
}
