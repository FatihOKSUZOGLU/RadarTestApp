package View;

import java.awt.Point;
import java.awt.image.BufferedImage;

public interface ImageListener {
	public void setImage(BufferedImage image);

	public void setDomain(Point point);

	public void setRange(Point point);

	public void markPoint(Point point);

	public void setOffset(double offset);
}
