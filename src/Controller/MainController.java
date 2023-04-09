package Controller;

import java.awt.Point;
import java.awt.image.BufferedImage;

import View.ImageListener;
import View.MainPanel;

public class MainController implements ImageListener {

	ScopeScanController scopeScanController;
	RectangleScanController rectangleScanController;

	MainPanel mainPanel;

	public MainController() {
		scopeScanController = new ScopeScanController();
		rectangleScanController = new RectangleScanController();
		getMainPanel().getTabbedPane().addTab("Rectangle", rectangleScanController.getPanel());
		getMainPanel().getTabbedPane().addTab("Scope", scopeScanController.getPanel());

	}

	public MainPanel getMainPanel() {
		if (mainPanel == null) {
			mainPanel = new MainPanel();
		}
		return mainPanel;
	}

	@Override
	public void setImage(BufferedImage image) {
		scopeScanController.setImage(image);
		rectangleScanController.setImage(image);

	}

	@Override
	public void setDomain(Point point) {
		scopeScanController.setDomain(point);
		rectangleScanController.setDomain(point);

	}

	@Override
	public void setRange(Point point) {
		scopeScanController.setRange(point);
		rectangleScanController.setRange(point);

	}

	@Override
	public void markPoint(Point point) {
		scopeScanController.markPoint(point);
		rectangleScanController.markPoint(point);

	}

	@Override
	public void setOffset(double offset) {
		scopeScanController.setOffset(offset);
		rectangleScanController.setOffset(offset);

	}

}
