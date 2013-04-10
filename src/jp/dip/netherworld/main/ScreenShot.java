package jp.dip.netherworld.main;

import java.awt.AWTException;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ScreenShot {
	private int height;
	private int width;
	private String outDir;

	public ScreenShot() {
		super();
		Rectangle rect = getScreenSize();
		this.height = rect.height;
		this.width = rect.width;
		this.outDir = "storage";
	}

	public void shot() throws AWTException, IOException {
		if (!new File(this.outDir).exists()) {
			throw new FileNotFoundException();
		}

		Robot robot = new Robot();
		BufferedImage image = robot.createScreenCapture(new Rectangle(0, 0,
				this.width, this.height));
		long time = System.currentTimeMillis();
		ImageIO.write(image, "PNG", new File(this.outDir + File.separator
				+ "screenshot-" + time + ".png"));
	}

	/**
	 * スクリーンショットを取る際の撮影サイズを計算<br>
	 * 縦：ディスプレイの最大の高さ<br>
	 * 横：各ディスプレイの長さの合計<br>
	 */
	private Rectangle getScreenSize() {
		int height = 0;
		int width = 0;

		Rectangle virtualBounds = new Rectangle();
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();
		for (int j = 0; j < gs.length; j++) {
			GraphicsDevice gd = gs[j];
			GraphicsConfiguration[] gc = gd.getConfigurations();
			for (int i = 0; i < gc.length; i++) {
				virtualBounds = virtualBounds.union(gc[i].getBounds());
				if (height < virtualBounds.height) {
					height = virtualBounds.height;
				}
				width = virtualBounds.width;
			}
		}
		return new Rectangle(width, height);
	}

	public void setOutputDir(String dirName) {
		this.outDir = dirName;
	}

	public String getOutputDir() {
		return this.outDir;
	}
}
