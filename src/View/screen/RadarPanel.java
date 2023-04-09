package View.screen;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

public class RadarPanel extends JPanel {
	private RangePanel rangePanel;
	private RectanglePanel imagePanel;
	private DomainPanel domainPanel;
	private JSlider sliderBRT;

	public RadarPanel() {
		initialize();
	}

	private void initialize() {

		this.setBackground(Color.black);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 52, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 25 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 0.0 };
		setLayout(gridBagLayout);
		GridBagConstraints gbc_sliderBRT = new GridBagConstraints();
		gbc_sliderBRT.fill = GridBagConstraints.BOTH;
		gbc_sliderBRT.insets = new Insets(0, 0, 5, 5);
		gbc_sliderBRT.gridx = 0;
		gbc_sliderBRT.gridy = 0;
		add(getSliderBRT(), gbc_sliderBRT);
		GridBagConstraints gbc_rangePanel = new GridBagConstraints();
		gbc_rangePanel.gridheight = 2;
		gbc_rangePanel.insets = new Insets(0, 0, 5, 5);
		gbc_rangePanel.fill = GridBagConstraints.BOTH;
		gbc_rangePanel.gridx = 1;
		gbc_rangePanel.gridy = 0;
		add(getRangePanel(), gbc_rangePanel);
		GridBagConstraints gbc_imagePanel = new GridBagConstraints();
		gbc_imagePanel.gridheight = 2;
		gbc_imagePanel.insets = new Insets(10, 10, 10, 10);
		gbc_imagePanel.fill = GridBagConstraints.BOTH;
		gbc_imagePanel.gridx = 2;
		gbc_imagePanel.gridy = 0;
		add(getImagePanel(), gbc_imagePanel);
		GridBagConstraints gbc_domainPanel = new GridBagConstraints();
		gbc_domainPanel.fill = GridBagConstraints.BOTH;
		gbc_domainPanel.gridx = 2;
		gbc_domainPanel.gridy = 2;
		add(getDomainPanel(), gbc_domainPanel);
	}

	public RangePanel getRangePanel() {
		if (rangePanel == null) {
			rangePanel = new RangePanel();
		}
		return rangePanel;
	}

	public RectanglePanel getImagePanel() {
		if (imagePanel == null) {
			imagePanel = new RectanglePanel();
		}
		return imagePanel;
	}

	public DomainPanel getDomainPanel() {
		if (domainPanel == null) {
			domainPanel = new DomainPanel();
		}
		return domainPanel;
	}

	public JSlider getSliderBRT() {
		if (sliderBRT == null) {
			sliderBRT = new JSlider();
			sliderBRT.setOrientation(SwingConstants.VERTICAL);
		}
		return sliderBRT;
	}
}
