package com.yelinlan;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDateTime;

public class WindowFinder {

	public static final int DELAY = 1000;
	private static int num = 0;

	private static volatile boolean start = true;

	public static void main(String[] args) throws Exception {

		new Thread(() -> {
			while (true) {
				if (start) {
					Robot robot1 = null;
					try {
						robot1 = new Robot();
					} catch (AWTException e) {
						e.printStackTrace();
					}
					assert robot1 != null;
					press(robot1, 1451, 1210, DELAY);
					press(robot1, 1480, 1355, DELAY);
					try {
						Thread.sleep(DELAY);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();


		new Thread(() -> {
			while (true) {
				Robot robot = null;
				try {
					robot = new Robot();
				} catch (AWTException e) {
					e.printStackTrace();
				}
				try {
					assert robot != null;
					cook(robot);
				} catch (AWTException | InterruptedException | IOException e) {
					e.printStackTrace();
				}
			}
		}).start();

	}

	private static void cook(Robot robot) throws AWTException, InterruptedException, IOException {
		Color target = new Color(0xFF, 0xC0, 0x40);
		// 如果你想获取窗口的截图，可以使用以下代码
		int[] pos = null;
		robot.setAutoWaitForIdle(true);
		BufferedImage image;
		while (true) {
			image = robot.createScreenCapture(new Rectangle(700, 900, 1100, 300));
			if (pos == null) {
				pos = getTargetColor(image, target);
				continue;
			}
			int rgb = image.getRGB(pos[0], pos[1]);
			int red = (rgb >> 16) & 0xFF;
			int green = (rgb >> 8) & 0xFF;
			int blue = rgb & 0xFF;
			if (red != target.getRed() || green != target.getGreen() || blue != target.getBlue()) {
				press(robot, 1284, 1260, 30);
				//				ImageIO.write(image, "png", new File("C:\\Users\\Administrator\\Desktop\\my\\" + (++num) + "aaa.png"));
				//				press(robot, 1299, 1215, 100);
				//				press(robot, 1544, 1340, 0);
				System.out.println(LocalDateTime.now() + "         开始做菜。。。");
				beep();
//				start=true;
//				Thread.sleep(100);
//				start=false;
				break;
			}
		}
	}

	private static void beep() {
		try {
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Robot robot1 = new Robot();
			int duration = 1000;
			toolkit.beep();
			robot1.delay(duration);
			robot1.keyRelease(KeyEvent.VK_CONTEXT_MENU);
		} catch (AWTException ex) {
			ex.printStackTrace();
		}
	}

	private static void press(Robot robot, int x, int y, int delay) {
		robot.mouseMove(x, y);
		robot.delay(delay);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.delay(delay);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		robot.delay(delay);
		System.out.println("移动鼠标到点(x,y):" + x + "," + y);
	}

	private static int[] getTargetColor(BufferedImage image, Color targetColor) {
		int width = image.getWidth();
		int height = image.getHeight();
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Color pixelColor = new Color(image.getRGB(x, y));
				if (pixelColor.equals(targetColor)) {
					System.out.println("Found yellow pixel at (" + x + ", " + y + "): " + pixelColor);
					return new int[]{x, y};
				}
			}
		}
		return null;
	}
}