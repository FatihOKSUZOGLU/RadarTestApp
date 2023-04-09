package View.screen;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

import javax.swing.JPanel;

public class RectanglePanel extends JPanel {
	private static final BasicStroke crossStroke = new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,
			1.0f, new float[] { 3f, 3f }, 0.0f);
	private static final BasicStroke markStroke = new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,
			1.0f, null, 0.0f);

	private final Line2D.Double line = new Line2D.Double();

	private BufferedImage image;

	private BufferedImage processedImage;
	private Point crossHair;
	private Point markedPoint;
	private Color markColor = Color.yellow;
	private Color crossColor = Color.white;
	private boolean crossVisible = true;
	private boolean markVisible = true;

	// it must be between 0.0-1.0
	private double offset = 0;

	private RescaleOp rescaleOp;
	private float BRTValue;

	public RectanglePanel() {
		this.setBackground(Color.BLACK);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (offset > 0) {
			g.translate((int) (offset * getWidth()), 0);
			// Draw the image onto the panel
			g.drawImage(getImage(), 0, 0, getWidth(), getHeight(), null);

			g.translate(-getWidth(), 0);
		}

		// Draw the image onto the panel
		// g.drawImage(getImage(), 0, 0, getWidth(), getHeight(), null);

		Graphics2D g2d = (Graphics2D) g;

		renderBRT_THR(g2d);

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// Draw the crosshair if set
		if (crossHair != null) {
			drawCross(g2d);
		}

		// Draw the marked point if set
		if (markedPoint != null) {
			drawMark(g2d);
		}
	}

	private void renderBRT_THR(Graphics2D g) {

		/*
		 * BRTValue: Bu parametre, görüntünün parlaklığını artırmak veya azaltmak için
		 * kullanılır. Değerler 1.0'dan küçük olursa, görüntü daha karanlık hale gelir.
		 * Değerler 1.0'den büyük olursa, görüntü daha parlak hale gelir. Değerler 1.0
		 * ise, görüntü değişmez. Örneğin, 0.5 değeri, görüntüyü yarı parlaklığa
		 * indirirken, 2.0 değeri, görüntüyü iki katına çıkarır.
		 * 
		 * offset: Bu parametre, görüntünün parlaklık değerlerine bir sabit ekler veya
		 * çıkarır. Bu, görüntünün genel parlaklık seviyesini artırmak veya azaltmak
		 * için kullanılabilir. Örneğin, -50 değeri, görüntünün parlaklık değerlerine 50
		 * puanlık bir azaltma uygularken, 50 değeri, görüntünün parlaklık değerlerine
		 * 50 puanlık bir artış uygular.
		 * 
		 * hints: Bu parametre, işlemin yapılacağı görüntünün türü hakkında bilgi verir.
		 * 
		 */

		// Resim için RescaleOp nesnesi oluşturun

		float minPixelValue = 0.0f / 255.0f;
		float maxPixelValue = 255.0f / 255.0f;
		float scaleFactor = 1.0f / (maxPixelValue - minPixelValue);
		float offset = -minPixelValue * scaleFactor;

		rescaleOp = new RescaleOp(scaleFactor, offset, null);

		// Resmi işleyin ve yeni bir BufferedImage nesnesine kaydedin
		processedImage = rescaleOp.filter(getImage(), null);

		// Resmi panelde çizin
		g.drawImage(processedImage, 0, 0, getWidth(), getHeight(), null);
	}

	public BufferedImage getImage() {
		if (image == null) {
			image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		}
		return image;
	}

	/* DRAW FUNCTIONS */

	private void drawCross(Graphics2D g) {
		if (crossVisible) {
			g.setStroke(crossStroke);
			g.setColor(crossColor);
			g.drawLine(0, crossHair.y, getWidth(), crossHair.y);
			g.drawLine(crossHair.x, 0, crossHair.x, getHeight());
		}

	}

	private void drawMark(Graphics2D g) {
		if (markVisible) {
			g.setStroke(markStroke);
			g.setColor(markColor);
			g.drawLine(0, markedPoint.y, getWidth(), markedPoint.y);
			g.drawLine(markedPoint.x, 0, markedPoint.x, getHeight());
		}
	}

	/* ALL SETTER FUNCTIONS */

	public void setCrossHair(Point crossHair) {
		this.crossHair = crossHair;
		repaint();
	}

	public void setOffset(double offset) {
		this.offset = offset;
		repaint();
	}

	public void setImage(BufferedImage image) {
		this.image = image;
		repaint();
	}

	public void setMarkedPoint(Point markedPoint) {
		if (this.markedPoint != null) {
			this.markedPoint = null;
		} else {

			this.markedPoint = markedPoint;
		}
		repaint();
	}

	public void setMinMaxThreshold(float min, float max) {
		float scaleFactor = 1.0f / (max - min);
		float offset = -min * scaleFactor;
	}
}