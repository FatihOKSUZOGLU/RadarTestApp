package View.screen.Scope;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

public class RadarScopePanel extends JPanel {
	private ScopePanel imagePanel;
	private JSlider sliderBRT;

	public RadarScopePanel() {
		initialize();
	}

	private void initialize() {

		this.setBackground(Color.black);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0 };
		setLayout(gridBagLayout);
		GridBagConstraints gbc_sliderBRT = new GridBagConstraints();
		gbc_sliderBRT.fill = GridBagConstraints.BOTH;
		gbc_sliderBRT.insets = new Insets(0, 0, 5, 5);
		gbc_sliderBRT.gridx = 0;
		gbc_sliderBRT.gridy = 0;
		add(getSliderBRT(), gbc_sliderBRT);
		GridBagConstraints gbc_imagePanel = new GridBagConstraints();
		gbc_imagePanel.gridheight = 2;
		gbc_imagePanel.insets = new Insets(10, 10, 10, 10);
		gbc_imagePanel.fill = GridBagConstraints.BOTH;
		gbc_imagePanel.gridx = 1;
		gbc_imagePanel.gridy = 0;
		add(getScopePanel(), gbc_imagePanel);
	}

	public ScopePanel getScopePanel() {
		if (imagePanel == null) {
			imagePanel = new ScopePanel();
		}
		return imagePanel;
	}

	public JSlider getSliderBRT() {
		if (sliderBRT == null) {
			sliderBRT = new JSlider();
			sliderBRT.setOrientation(SwingConstants.VERTICAL);
		}
		return sliderBRT;
	}
}
