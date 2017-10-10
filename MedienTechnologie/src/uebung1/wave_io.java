package uebung1;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class wave_io 
{
	public static void waveConvert(String[] args, int reduced_bits, String writeValueTxt, boolean downsampling, boolean bitReduction, boolean bitNoise) 
	{
		int samples=0;
		int validBits=0;
		long sampleRate=0;
		long numFrames=0; 
		int numChannels=0;

		String inFilename = null;
		String outFilename = null;
		
		if (args.length < 1) {
			try { throw new WavFileException("At least one filename specified  (" + args.length + ")"); }
			catch (WavFileException e1) { e1.printStackTrace(); }
		}
	
		inFilename=args[0];
		
		
		
		//read wave data, sample contained in array readWavFile.sound
		WavFile readWavFile = null;
		try {
			readWavFile = WavFile.read_wav(inFilename);
			
			//local copy of header data
			numFrames = readWavFile.getNumFrames(); 
			numChannels = readWavFile.getNumChannels();
			samples = (int)numFrames*numChannels;
			validBits = readWavFile.getValidBits();
			sampleRate = readWavFile.getSampleRate();
		
			
			// samples schreiben 2.1.	
			
		    if (writeValueTxt != null){
		    	try (FileWriter fw = new FileWriter(new File(writeValueTxt))) {
					PrintWriter fileOut = new PrintWriter(fw, true);
					for (int i=0; i < samples;i++) {
						fileOut.println(readWavFile.sound[i]);
					}
				} catch (IOException e) {
					System.out.println("Problem beim �ffnen der Datei zum Schreiben");
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
			
		outFilename=args[1];
		try {

			// 2.4 Downsampling
			if (downsampling){
				for (int i=0; i < samples/2;i++) {
					readWavFile.sound[i*2] = 0;
				}
			}
		
		
 			// 3.2 Bitreduzierung
//			int reduced_bits = 1;
			
			if (bitReduction) {
				for (int i=0; i < samples;i++) {
					readWavFile.sound[i] = (short)((readWavFile.sound[i]/2*reduced_bits)*2*reduced_bits);	
				}
			}
			
			
 			// 3.4 Bitreduzierung
//			reduced_bits = 1;
			if (bitNoise){
				for (int i=0; i < samples;i++) {
					readWavFile.sound[i] = (short)(readWavFile.sound[i]%(2*reduced_bits));
				}
			}
			
			
			WavFile.write_wav(outFilename, numChannels, numFrames, validBits, sampleRate, readWavFile.sound);
		}			
		catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
}
