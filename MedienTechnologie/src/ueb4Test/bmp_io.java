package ueb4Test;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class bmp_io {
	
	// Run with zero command-line arguments. This program reads Demo.bmp and writes Demo2.bmp in the current directory.
	public static void main(String[] args) throws IOException {
		String inFilename = null;
		String outFilename = null;
		PixelColor pc = null;
		BmpImage bmp = null;
		
		if (args.length < 1) 
			System.out.println("At least one filename specified  (" + args.length + ")"); 
		
		inFilename = args[0];
		InputStream in = new FileInputStream(inFilename);
		bmp = BmpReader.read_bmp(in);
		
		// BGR schreiben horizontal 2.1.	
    	for(int x = 0; x < bmp.image.getWidth(); x++) {

    		// ********* ToDo ***************
		}

		// BGR schreiben vertikal 2.1.	
    	for(int y = 0; y < bmp.image.getHeight(); y++) {

    		// ********* ToDo ***************
    		
    	}

	    if (args.length == 1) 
			System.exit(0);

		outFilename = args[1];
		OutputStream out = new FileOutputStream(outFilename);
		
		// erzeuge graustufenbild
		for(int y = 0; y < bmp.image.getHeight(); y++) {
			for(int x = 0;x < bmp.image.getWidth(); x++) {
			
				// ********* ToDo ***************
				
			}
		}
		
		// downsampling
		for(int y = 0; y < bmp.image.getHeight(); y++) {
			for(int x = 0; x < bmp.image.getWidth(); x++) {
		
				// ********* ToDo ***************
				
			}
		}
		
		// bitreduzierung
		int reduced_bits = 1;
		for(int y = 0; y < bmp.image.getHeight(); y++) {
			for (int x = 0; x < bmp.image.getWidth(); x++) {
		
				// ********* ToDo ***************
			
			}
		}
		
		// bitreduzierung differenz
		reduced_bits = 1;
		int bitsPerColor = 8;
		for(int y = 0; y < bmp.image.getHeight(); y++) {
			for (int x = 0; x < bmp.image.getWidth(); x++) {

				// ********* ToDo ***************
				
			}
		}
		PixelColor yPicPixel = null;
		
		for (int x = 0; x < bmp.image.getWidth(); x++) {
            for (int y = 0; y < bmp.image.getHeight(); y++) {
                yPicPixel = bmp.image.getRgbPixel(x, y);

                int hell = (int)(0.299 * yPicPixel.r + 0.587 * yPicPixel.g + 0.114 * yPicPixel.b);
                int cr = (int)((yPicPixel.r * -0.169) + (yPicPixel.g * -0.331) + (yPicPixel.b * 0.5) + 128);
                int cb = (int)((yPicPixel.r * 0.5) + (yPicPixel.g * -0.419) + (yPicPixel.b * -0.081) + 128);

                yPicPixel.r = (int) (hell + 1.403 * (cr - 128));
                yPicPixel.g = (int) (hell - 0.344 * (cb - 128) - 0.714 * (cr - 128));
                yPicPixel.b = (int) (hell + 1.773 * (cb - 128));
            }
        }
		
		try {
			BmpWriter.write_bmp(out, bmp);
		} finally {
			out.close();
		}
	}
}
