package Controller;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.SwingUtilities;

import View.ImageListener;
import View.screen.RadarPanel;

public class RectangleScanController implements ImageListener {

	private MouseAdapter mouseHandler;

	private RadarPanel radarPanel;

	public RectangleScanController() {

		initPanelOverlay();

	}

	private void initPanelOverlay() {

		getPanel().getImagePanel().addMouseListener(getMouseListener());
		getPanel().getImagePanel().addMouseMotionListener(getMouseListener());
	}

	private MouseAdapter getMouseListener() {
		if (mouseHandler == null) {

			mouseHandler = new MouseAdapter() {

				@Override
				public void mouseMoved(MouseEvent e) {
					// Fare konumunu crosshairPosition'a güncelle
					getPanel().getImagePanel().setCrossHair(e.getPoint());
				}

				@Override
				public void mousePressed(MouseEvent e) {

					if (SwingUtilities.isLeftMouseButton(e)) {
						markPoint(e.getPoint());
					} else if (SwingUtilities.isMiddleMouseButton(e)) {
					} else if (SwingUtilities.isRightMouseButton(e)) {
					}

				}

				@Override
				public void mouseReleased(MouseEvent e) {
//					if (SwingUtilities.isLeftMouseButton(e)) {
//						System.out.println("solR");
//					} else if (SwingUtilities.isMiddleMouseButton(e)) {
//						System.out.println("ortaR");
//					} else if (SwingUtilities.isRightMouseButton(e)) {
//						System.out.println("sağR");
//					}
				}

			};
		}
		return mouseHandler;
	}

	public RadarPanel getPanel() {
		if (radarPanel == null) {
			radarPanel = new RadarPanel();
		}
		return radarPanel;
	}

	@Override
	public void setImage(BufferedImage image) {
		getPanel().getImagePanel().setImage(image);

	}

	@Override
	public void setDomain(Point point) {
		getPanel().getDomainPanel().setRange(point.x, point.y);
	}

	@Override
	public void setRange(Point point) {
		getPanel().getRangePanel().setRange(point.x, point.y);

	}

	@Override
	public void markPoint(Point point) {
		getPanel().getImagePanel().setMarkedPoint(point);

	}

	@Override
	public void setOffset(double offset) {
		getPanel().getImagePanel().setOffset(offset);

	}

}
