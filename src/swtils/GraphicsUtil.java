package swtils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class GraphicsUtil {
	/**
	 * TODO draw oval with center
	 * @param g
	 * @param center center of oval
	 * @param height 
	 * @param width
	 */
	public static void drawOvalCenter(Graphics g, Point center, 
			int height, int width, boolean semi) {
		int x, y;
		if(semi) {
			x = center.x - width;
			y = center.y - height;
			g.drawOval(x, y, width * 2, height * 2);
		}else {
			x = center.x - width / 2;
			y = center.y - height / 2;
			g.drawOval(x, y, width, height);
		}
		
	}
	
	public static void drawOvalCenter(Graphics g, Point center, 
			int height, int width) {
		drawOvalCenter(g, center, height, width, false);
	}
	
	public static void drawOvalCenter(Graphics g, int centerX,
			int centerY, int height, int width, boolean semi) {
		drawOvalCenter(g, new Point(centerX, centerY), height, width, semi);
	}
	
	
	
	public static void drawOvalCenter(Graphics g, int centerX, int centerY,
			int height, int width) {
		drawOvalCenter(g, new Point(centerX, centerY), height, width);
	}
	
	public static void drawCircle(Graphics g, Point origin, int size) {
		g.drawOval(origin.x, origin.y, size, size);
	}
	
	public static void drawCircle(Graphics g, int originX, int originY, int size) {
		drawCircle(g, new Point(originX, originY), size);
	}
	
	public static void drawCircleCenter(Graphics g, Point center, int size, boolean semi) {
		drawOvalCenter(g, center, size, size, semi);
	}
	
	public static void drawCircleCenter(Graphics g, int centerX, int centerY, int size, boolean semi) {
		drawCircleCenter(g, new Point(centerX, centerY), size, semi);
	}
	
	public static void drawCircleCenter(Graphics g, Point center, int size) {
		drawCircleCenter(g, center, size, false);
	}
	
	public static void drawCircleCenter(Graphics g, int centerX, int centerY, int size) {
		drawCircleCenter(g, centerX, centerY, size, false);
	}
	
	/**
	 * TODO fill oval
	 * @param g
	 * @param center
	 * @param height
	 * @param width
	 * @param semi
	 */
	public static void fillOvalCenter(Graphics g, Point center, 
			int height, int width, boolean semi) {
		int x, y;
		if(semi) {
			x = center.x - width;
			y = center.y - height;
			g.fillOval(x, y, width * 2, height * 2);
		}else {
			x = center.x - width / 2;
			y = center.y - height / 2;
			g.fillOval(x, y, width, height);
		}
		
	}
	
	public static void fillOvalCenter(Graphics g, Point center, 
			int height, int width) {
		fillOvalCenter(g, center, height, width, false);
	}
	
	public static void fillOvalCenter(Graphics g, int centerX,
			int centerY, int height, int width, boolean semi) {
		fillOvalCenter(g, new Point(centerX, centerY), height, width, semi);
	}
	
	
	public static void fillOvalCenter(Graphics g, int centerX, int centerY,
			int height, int width) {
		fillOvalCenter(g, new Point(centerX, centerY), height, width);
	}
	
	public static void fillCircle(Graphics g, Point origin, int size) {
		g.fillOval(origin.x, origin.y, size, size);
	}
	
	public static void fillCircle(Graphics g, int originX, int originY, int size) {
		fillCircle(g, new Point(originX, originY), size);
	}
	
	public static void fillCircleCenter(Graphics g, Point center, int size, boolean semi) {
		fillOvalCenter(g, center, size, size, semi);
	}
	
	public static void fillCircleCenter(Graphics g, int centerX, int centerY, int size, boolean semi) {
		fillCircleCenter(g, new Point(centerX, centerY), size, semi);
	}
	
	public static void fillCircleCenter(Graphics g, Point center, int size) {
		fillCircleCenter(g, center, size, false);
	}
	
	public static void fillCircleCenter(Graphics g, int centerX, int centerY, int size) {
		fillCircleCenter(g, centerX, centerY, size, false);
	}
	
	public static void drawHover(Graphics g, Point center, int bias, int len, Color color) {
		int ox = center.x;
		int oy = center.y;
		int x1 = ox - bias;
		int x2 = ox + bias;
		int y1 = oy - bias;
		int y2 = oy + bias;
		Color c = g.getColor();
		g.setColor(color);
		g.drawLine(x1, y1 - len, x1, y1);
		g.drawLine(x2, y1 - len, x2, y1);
		g.drawLine(x1 - len, y1, x1, y1);
		g.drawLine(x1 - len, y2, x1, y2);
		g.drawLine(x1, y2, x1, y2 + len);
		g.drawLine(x2, y2, x2, y2 + len);
		g.drawLine(x2, y1, x2 + len, y1);
		g.drawLine(x2, y2, x2 + len, y2);
		g.setColor(c);
	}
	
	public static void drawHover(Graphics g, int centerX, int centerY, int bias, int len) {
		drawHover(g, new Point(centerX, centerY), bias, len, Color.RED);
	}
	
	public static void drawCross(Graphics g, Point center, int len, Color color) {
		Color c = g.getColor();
		g.setColor(color);
		int half = len / 2;
		int x = center.x;
		int y = center.y;
		g.drawLine(x, y - half, x, y + half);
		g.drawLine(x - half, y, x + half, y);
		g.setColor(c);
	}
	
	public static void drawCross(Graphics g, Point center, int len) {
		drawCross(g, center, len, Color.RED);
	}
	
}
