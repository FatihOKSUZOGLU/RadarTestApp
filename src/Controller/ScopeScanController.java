package Controller;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.SwingUtilities;

import View.ImageListener;
import View.screen.Scope.RadarScopePanel;

public class ScopeScanController implements ImageListener {

	private MouseAdapter mouseHandler;

	private RadarScopePanel radarPanel;

	public ScopeScanController() {

		initPanelOverlay();

	}

	private void initPanelOverlay() {

		getPanel().getScopePanel().addMouseListener(getMouseListener());
		getPanel().getScopePanel().addMouseMotionListener(getMouseListener());
	}

	private MouseAdapter getMouseListener() {
		if (mouseHandler == null) {

			mouseHandler = new MouseAdapter() {

				@Override
				public void mouseMoved(MouseEvent e) {
					// Fare konumunu crosshairPosition'a güncelle
					getPanel().getScopePanel().setCrossHair(e.getPoint());
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

	public RadarScopePanel getPanel() {
		if (radarPanel == null) {
			radarPanel = new RadarScopePanel();
		}
		return radarPanel;
	}

	@Override
	public void setImage(BufferedImage image) {
		getPanel().getScopePanel().setImage(image);

	}

	@Override
	public void setDomain(Point point) {
		// getPanel().getScopePanel().setRange(point.x, point.y);
	}

	@Override
	public void setRange(Point point) {
		// getPanel().getRangePanel().setRange(point.x, point.y);

	}

	@Override
	public void markPoint(Point point) {
		getPanel().getScopePanel().setMarkedPoint(point);

	}

	@Override
	public void setOffset(double offset) {

	}

}
