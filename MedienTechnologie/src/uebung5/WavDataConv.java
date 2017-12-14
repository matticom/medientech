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
			
			// 1a
			{"./backupAudio/Musik_Carbon.wav", "./samples_uebung5/Musik_Carbon_Gain_3db.wav", 3, 0, 0, false, false, null},
			{"./backupAudio/Musik_Carbon.wav", "./samples_uebung5/Musik_Carbon_Gain_6db.wav", 6, 0, 0, false, false, null},
			{"./backupAudio/Musik_Carbon.wav", "./samples_uebung5/Musik_Carbon_Gain_9db.wav", 9, 0, 0, false, false, null},
			
			// 1b
			
			// 1c
			{"./backupAudio/sine_1000_ue5.wav", "./samples_uebung5/sine_1000_ue5_Gain_6db.wav", 6, 0, 0, false, false, null},
			{"./backupAudio/sine_1000_ue5.wav", "./samples_uebung5/sine_1000_ue5_Gain_9db.wav", 9, 0, 0, false, false, null},
			{"./backupAudio/sine_1000_ue5.wav", "./samples_uebung5/sine_1000_ue5_Gain_12db.wav", 12, 0, 0, false, false, null},
			
			// 2a
			{"./backupAudio/Sprache_Carbon.wav", "./samples_uebung5/Sprache_Carbon_Delay_10ms.wav", 0, 10, 0, false, false, null},
			{"./backupAudio/Sprache_Carbon.wav", "./samples_uebung5/Sprache_Carbon_Delay_100ms.wav", 0, 100, 0, false, false, null},
			{"./backupAudio/Sprache_Carbon.wav", "./samples_uebung5/Sprache_Carbon_Delay_200ms.wav", 0, 200, 0, false, false, null},
			{"./backupAudio/Musik_Carbon.wav", "./samples_uebung5/Musik_Carbon_Delay_10ms.wav", 0, 10, 0, false, false, null},
			{"./backupAudio/Musik_Carbon.wav", "./samples_uebung5/Musik_Carbon_Delay_100ms.wav", 0, 100, 0, false, false, null},
			{"./backupAudio/Musik_Carbon.wav", "./samples_uebung5/Musik_Carbon_Delay_200ms.wav", 0, 200, 0, false, false, null},
			
			// 3a
			{"./backupAudio/Musik_Carbon.wav", "./samples_uebung5/Musik_Carbon_positiv.wav", 0, 0, 0, true, true, null},
			{"./backupAudio/Musik_Carbon.wav", "./samples_uebung5/Musik_Carbon_negativ.wav", 0, 0, 0, true, false, null},
			
			// 3b
			{"./backupAudio/white_noise_ue5.wav", "./samples_uebung5/white_noise_ue5_positiv.wav", 0, 0, 0, true, true, null},
			{"./backupAudio/white_noise_ue5.wav", "./samples_uebung5/white_noise_ue5_negativ.wav", 0, 0, 0, true, false, null},

		});
	}
	
	@Test
	public void convertWavs() {
		wave_io.waveConvert(new String[]{input,  output}, gain_factor, echoSpeechDelay, echoMusicDelay, filter, positivFilter, writeValueTxt);
	}	
}
