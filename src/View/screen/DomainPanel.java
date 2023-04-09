package View.screen;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

public class DomainPanel extends JPanel {

	private final double xInset = 10;

	private int domainMin;
	private int domainMax;

	private int strWidth;

	private int gridDomains;

	private int gridCount;

	private double gridWidth;

	private int domainValue;

	private StringBuilder domainSTRBuilder;

	private FontMetrics fontMetrics;

	private String domainSTRValue;

	private int x;

	public DomainPanel() {

		this.setBackground(Color.black);
		setRange(0, 360);
		setGridCount(4);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (fontMetrics == null) {
			fontMetrics = g.getFontMetrics();
		}

		if (gridCount != 0 && getWidth() > 0) {
			gridWidth = (getWidth() - xInset * 2) / gridCount;
			gridDomains = (domainMax - domainMin) / gridCount;

			drawLine(g, domainMin, domainMax, gridWidth / gridDomains, 0);

			if (domainSTRBuilder == null) {
				domainSTRBuilder = new StringBuilder();
			}

			for (int i = 0; i <= gridCount; i++) {
				x = (int) (xInset + (i * gridWidth));

				// her çizgiye sayı yaz
				domainValue = domainMin + (i * gridDomains);

				g.setColor(Color.white);
				g.drawLine(x, 0, x, 5);

				domainSTRBuilder.setLength(0);
				domainSTRBuilder.append(domainValue);
				domainSTRValue = domainSTRBuilder.toString();

				strWidth = fontMetrics.stringWidth(domainSTRValue);
				g.drawString(domainSTRValue, Math.max(x - strWidth / 2, 0), 20);
			}
		}
	}

	private void drawLine(Graphics g, int x1, int x2, double offset, int y) {

		int lineWidth = 4;
		Color color;

		for (int i = x1; i < x2; i++) {
			int x = (i + 360) % 360;

			if (x >= 0 && x < 145) {
				color = Color.GREEN;
			} else if (x >= 145 && x < 155) {
				color = Color.YELLOW;
			} else if (x >= 155 && x < 205) {
				color = Color.RED;
			} else if (x >= 205 && x < 215) {
				color = Color.YELLOW;
			} else {
				color = Color.GREEN;
			}

			g.setColor(color);
			g.fillRect((int) ((i - x1) * offset + xInset), y, (int) Math.ceil(offset), lineWidth);
		}
	}

	public void setRange(int min, int max) {
		this.domainMin = min;
		this.domainMax = max;
		repaint();
	}

	public void setGridCount(int gridCount) {
		this.gridCount = gridCount;
	}

}
