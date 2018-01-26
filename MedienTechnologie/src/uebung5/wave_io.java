package uebung5;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class wave_io {
	public static void waveConvert(String[] args, int gain_factor, int echoSpeechDelay, int echoMusicDelay,
			boolean filter, boolean positivFilter, String writeValueTxt) {
		int samples = 0;
		int validBits = 0;
		long sampleRate = 0;
		long numFrames = 0;
		int numChannels = 0;

		String inFilename = null;
		String outFilename = null;

		if (args.length < 1) {
			try {
				throw new WavFileException("At least one filename specified  (" + args.length + ")");
			} catch (WavFileException e1) {
				e1.printStackTrace();
			}
		}

		inFilename = args[0];

		// read wave data, sample contained in array readWavFile.sound
		WavFile readWavFile = null;
		try {
			readWavFile = WavFile.read_wav(inFilename);

			// local copy of header data
			numFrames = readWavFile.getNumFrames();
			numChannels = readWavFile.getNumChannels();
			samples = (int) numFrames * numChannels;
			validBits = readWavFile.getValidBits();
			sampleRate = readWavFile.getSampleRate();

			// samples schreiben 2.1.

			if (writeValueTxt != null) {
				try (FileWriter fw = new FileWriter(new File(writeValueTxt))) {
					PrintWriter fileOut = new PrintWriter(fw, true);
					for (int i = 0; i < samples; i++) {
						fileOut.println(readWavFile.sound[i]);
					}
				} catch (IOException e) {
					System.out.println("Problem beim öffnen der Datei zum Schreiben");
				}
			}

			if (args.length == 1)
				System.exit(0);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (WavFileException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		outFilename = args[1];
		try {
			if (gain_factor > 0) {
				for (int i = 0; i < samples; i++) {
					int sample = readWavFile.sound[i];
					sample *= Math.pow(10, gain_factor / 20.0);
					if (sample > 32767) {
						sample = 32767;
					}
					if (sample < -32768) {
						sample = -32768;
					}
					readWavFile.sound[i] = (short) sample;
				}
			}

			// in ms
			if (echoSpeechDelay > 0) {
				short[] newSound = new short[samples];
				int numbersOfDelaySamples = (int) (echoSpeechDelay / 1000.0 * sampleRate);
				for (int i = 0; i < samples; i++) {
					int sample = readWavFile.sound[i];
					if (i < numbersOfDelaySamples) {
						sample = sample / 2;
					} else {
						int echoIndex = i - numbersOfDelaySamples;
						sample = (int) (sample / 2 + 0.6 * readWavFile.sound[echoIndex]);
						// if (sample > 32767) {
						// sample = 32767;
						// }
						// if (sample < -32768) {
						// sample = -32768;
						// }
					}
					newSound[i] = (short) sample;
				}
				readWavFile.sound = newSound;
			}

			// in ms
			if (echoMusicDelay > 0) {
				short[] newSound = new short[samples];
				int numbersOfDelaySamples = (int) (echoMusicDelay / 1000.0 * sampleRate);
				for (int i = 0; i < numFrames; i++) {
					int sampleCH1 = readWavFile.sound[i * 2];
					int sampleCH2 = readWavFile.sound[i * 2 + 1];
					if (i < numbersOfDelaySamples) {
						sampleCH1 = sampleCH1 / 2;
						sampleCH2 = sampleCH2 / 2;
					} else {
						int echoIndex = i - numbersOfDelaySamples;
						sampleCH1 = (int) (sampleCH1 / 2 + 0.6 * readWavFile.sound[echoIndex * 2]);
						sampleCH2 = (int) (sampleCH2 / 2 + 0.6 * readWavFile.sound[echoIndex * 2 + 1]);
						 if (sampleCH1 > 32767) {
						 sampleCH1 = 32767;
						 System.out.println("war größer");
						 }
						 if (sampleCH1 < -32768) {
						 sampleCH1 = -32768;
						 }
						 if (sampleCH2 > 32767) {
						 sampleCH2 = 32767;
						 System.out.println("war größer");
						 }
						 if (sampleCH2 < -32768) {
						 sampleCH2 = -32768;
						 }
					}
					newSound[i * 2] = (short) sampleCH1;
					newSound[i * 2 + 1] = (short) sampleCH2;
				}
				readWavFile.sound = newSound;
			}

			if (filter) {

				if (numChannels == 1) { // WhiteNoise
					short[] newSound = new short[samples];
					for (int i = 0; i < samples; i++) {
						int sample = readWavFile.sound[i];
						if (i < 1) {
							sample = (int) (0.5 * sample);
						} else {
							if (positivFilter) {
								sample = (int) (0.5 * sample + 0.45 * readWavFile.sound[i - 1]);
							} else {
								sample = (int) (0.5 * sample - 0.45 * readWavFile.sound[i - 1]);
							}
							if (sample > 32767) {
								sample = 32767;
							}
							if (sample < -32768) {
								sample = -32768;
							}
						}
						newSound[i] = (short) sample;
					}
					readWavFile.sound = newSound;
				} else { // 2 Channel --> Musik
					short[] newSound = new short[samples];
					for (int i = 0; i < numFrames; i++) {
						int sampleCH1 = readWavFile.sound[i * 2];
						int sampleCH2 = readWavFile.sound[i * 2 + 1];
						if (i < 1) {
							sampleCH1 = (int) (0.5 * sampleCH1);
							sampleCH2 = (int) (0.5 * sampleCH2);
						} else {
							if (positivFilter) {
								sampleCH1 = (int) (0.5 * sampleCH1 + 0.45 * readWavFile.sound[(i - 1) * 2]);
								sampleCH2 = (int) (0.5 * sampleCH2 + 0.45 * readWavFile.sound[(i - 1) * 2 + 1]);
							} else {
								sampleCH1 = (int) (0.5 * sampleCH1 - 0.45 * readWavFile.sound[(i - 1) * 2]);
								sampleCH2 = (int) (0.5 * sampleCH2 - 0.45 * readWavFile.sound[(i - 1) * 2 + 1]);
							}
							if (sampleCH1 > 32767) {
								sampleCH1 = 32767;
							}
							if (sampleCH1 < -32768) {
								sampleCH1 = -32768;
							}
							if (sampleCH2 > 32767) {
								sampleCH2 = 32767;
							}
							if (sampleCH2 < -32768) {
								sampleCH2 = -32768;
							}
						}
						newSound[i * 2] = (short) sampleCH1;
						newSound[i * 2 + 1] = (short) sampleCH2;
					}
					readWavFile.sound = newSound;
				}
			}

			WavFile.write_wav(outFilename, numChannels, numFrames, validBits, sampleRate, readWavFile.sound);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}

}
