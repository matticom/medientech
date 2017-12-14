package uebung5;

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
	protected int gain_factor;
	protected int echoSpeechDelay;
	protected int echoMusicDelay;
	protected boolean filter;
	protected boolean positivFilter;
	protected String writeValueTxt;
	

	public WavDataConv(String input, String output, int gain_factor, int echoSpeechDelay, int echoMusicDelay, boolean filter, boolean positivFilter, String writeValueTxt) {
		this.input = input;
		this.output = output;
		this.gain_factor = gain_factor;
		this.echoSpeechDelay = echoSpeechDelay;
		this.echoMusicDelay = echoMusicDelay;
		this.filter = filter;
		this.positivFilter = positivFilter;
		this.writeValueTxt = writeValueTxt;
	}

	@Parameters
	public static List<Object[]> data() {
		return generateTestArrayList();
	}
	
	private static List<Object[]> generateTestArrayList() {
		return Arrays.asList(new Object[][] {
			//(String[] inOut, 	int gain_factor, int echoSpeechDelay, int echoMusicDelay, String writeValueTxt) 
			
			{"./backupAudio/Sprache_Carbon.wav", "./samples_uebung5/Sprache_Carbon_Gain_3db.wav", 3, 0, 0, false, false, "./samples_uebung5/Sprache_Carbon_Gain_3db.txt"},
			{"./backupAudio/Sprache_Carbon.wav", "./samples_uebung5/Sprache_Carbon_Gain_6db.wav", 6, 0, 0, false, false, "./samples_uebung5/Sprache_Carbon_Gain_6db.txt"},
			{"./backupAudio/Sprache_Carbon.wav", "./samples_uebung5/Sprache_Carbon_Gain_9db.wav", 9, 0, 0, false, false, "./samples_uebung5/Sprache_Carbon_Gain_9db.txt"},
			
//			{"./backupAudio/Sprache_Carbon.wav", "./samples_uebung5/Sprache_Carbon_Delay1s.wav", 0, 100, 0, false, false, "./samples_uebung5/Sprache_Carbon_Delay100ms.txt"},
//			{"./backupAudio/Musik_Carbon.wav", "./samples_uebung5/Musik_Carbon_Delay1s.wav", 0, 0, 10, false, false, "./samples_uebung5/Musik_Carbon_Delay1s.txt"},
			{"./backupAudio/white_noise_ue5.wav", "./samples_uebung5/white_noise_ue5_positiv.wav", 0, 0, 0, true, true, "./samples_uebung5/white_noise_ue5_positiv.txt"},
			{"./backupAudio/white_noise_ue5.wav", "./samples_uebung5/white_noise_ue5_negativ.wav", 0, 0, 0, true, false, "./samples_uebung5/white_noise_ue5_negativ.txt"},

		});
	}
	
	@Test
	public void convertWavs() {
		wave_io.waveConvert(new String[]{input,  output}, gain_factor, echoSpeechDelay, echoMusicDelay, filter, positivFilter, writeValueTxt);
	}	
}
