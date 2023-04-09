package View.screen;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class RangePanel extends JPanel {

	private final int yInset = 10;

	private int rangeMin;
	private int rangeMax;

	private int gridRanges;

	private int gridCount;

	private double gridWidth;

	/* FOR ALL REFRESH VARIABLES */

	private int strWidth;

	private String rangeSTRValue;

	private int rangeValue;

	private int y;

	public RangePanel() {
		this.setBackground(Color.black);
		setRange(0, 2300);
		setGridCount(6);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.white);
		g.fillRect(getWidth() - 2, yInset, 2, (getHeight() - yInset * 2));

		gridWidth = (getHeight() - yInset * 2) / gridCount;
		gridRanges = (rangeMax - rangeMin) / gridCount;

		for (int i = 0; i <= gridCount; i++) {

			y = (int) (getHeight() - (yInset + (i * gridWidth)));

			rangeValue = rangeMin + (i * (rangeMax - rangeMin) / gridCount);
			rangeSTRValue = Integer.toString(rangeValue);
			strWidth = g.getFontMetrics().stringWidth(rangeSTRValue);
			g.fillRect(getWidth() - 5, y - 3, 3, 2);
			g.drawString(rangeSTRValue, 0, y + 2);
		}
	}

	public void setRange(int min, int max) {
		rangeMin = min;
		rangeMax = max;
		repaint();
	}

	public void setGridCount(int gridCount) {
		this.gridCount = gridCount;
	}

}