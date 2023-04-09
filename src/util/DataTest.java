package util;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;

import View.ImageListener;

public class DataTest extends Thread {

	private final int WIDTH = 360;
	private final int HEIGHT = 360;

	private final int polarR = 500;

	private double patchNumber = 0.02;
	private final int NUMBER_OF_GENERATIONS_PER_SECOND = 33;
	private BufferedImage image;

	private ImageListener listener;
	private Color color = Color.green;

	private int counter = 0;
	private double alpha;
	private Color colorWithAlpha;
	private BufferedImage polarImage;

	public DataTest(ImageListener listener) {
		this.listener = listener;
		generateImage();
		generatePolarImage();
	}

	@Override
	public void run() {
		while (true) {
			generateImage();
			listener.setImage(getImage());
			if (patchNumber > 1) {
				patchNumber = 0.02;
				listener.setDomain(new Point(0, 360));
				listener.setRange(new Point(0, (int) (Math.random() * 50000)));
			} else {
				patchNumber += 0.02;
			}

			try {
				Thread.sleep(1000 / NUMBER_OF_GENERATIONS_PER_SECOND);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}

	public BufferedImage getImage() {
		if (image == null) {
			// Image boyutunu ayarla ve BufferedImage.TYPE_INT_RGB tipinde oluştur
			image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		}
		return image;
	}

	public void generateImage() {
		for (int x = 0; x < WIDTH; x++) {
			if (x > WIDTH / 2)
				for (int y = 0; y < HEIGHT; y++) {
					if (y < patchNumber * HEIGHT) {
						alpha = Math.random();
						colorWithAlpha = new Color((int) (color.getRed() * alpha), (int) (color.getGreen() * alpha),
								(int) (color.getBlue() * alpha), (int) (alpha * 255));
						getImage().setRGB(x, HEIGHT - y - 1, colorWithAlpha.getRGB());
					}
				}
		}
	}

	public void generatePolarImage() {

		for (int x = 0; x < polarR * 2; x++) {
			for (int y = 0; y < polarR * 2; y++) {
				double r = (double) y / polarR * 2 * polarR;
				double theta = (double) x / polarR * 2 * Math.PI;
				int newX = (int) (polarR + r * Math.cos(theta));
				int newY = (int) (polarR + r * Math.sin(theta));
				// Piksel değerini kopyala
				if (newX >= 0 && newX < WIDTH && newY >= 0 && newY < HEIGHT) {
					getPolarImage().setRGB(x, y, getImage().getRGB(newX, newY));
				}
			}
		}
	}

	private BufferedImage getPolarImage() {
		if (polarImage == null) {
			polarImage = new BufferedImage(polarR * 2, polarR * 2, BufferedImage.TYPE_INT_ARGB);
		}
		return polarImage;
	}

}
