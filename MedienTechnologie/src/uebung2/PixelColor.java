package uebung2;

public class PixelColor {
	int r;
	int b;
	int g;
	
	public PixelColor(int b, int g, int r) {
		this.r = r;
		this.b = b;
		this.g = g;
	}

	@Override
	public String toString() {
		return r + "\n" + g + "\n" + b;
	}
	
	
}
