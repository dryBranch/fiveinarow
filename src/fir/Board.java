package fir;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import swtils.GraphicsUtil;

import static swtils.GraphicsUtil.*;

public class Board extends JPanel{
	private static final long serialVersionUID = 1L;
	/* default number of grid 15 * 15 */
	private static int COMMON_LENGTH = 15;
	/* default size of grids*/ 
	private static int DEFAULT_SIZE = 60;
	
	/* status of pieces */
	private static Integer FREE = 2;
	private static Integer BLACK = 0;
	private static Integer WHITE = 1;
	/* images of pieces */
	private static Map<Integer, Image> images;
	static {
		images = new HashMap<Integer, Image>();
		images.put(BLACK, new ImageIcon("./resource/black.png").getImage());
		images.put(WHITE, new ImageIcon("./resource/white.png").getImage());
	}
	
	private int height, width;
	private int gridSize;
	private int[][] data;
	private int currentPlayer;
	private Point hover;
	private Point last;
	private int step;
	
	private void init(){
		height = COMMON_LENGTH;
		width = COMMON_LENGTH;
		gridSize = DEFAULT_SIZE;
		currentPlayer = BLACK;
		data = new int[height][width];
		for(int row = 0; row < height; row++) {
			for(int col = 0; col < width; col++) {
				data[row][col] = FREE;
			}
		}
		step = 0;
	}
	
	public Board() {
		init();
		this.addMouseListener(new MouseCmd());
		this.addMouseMotionListener(new MouseMove());
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame("Five in a row");
		Board b = new Board();
		f.add(b);
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setVisible(true);

	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		drawBoard(g);
		drawPieces(g);
		drawHover(g);
		drawLast(g);
	}

	/* draw main board */
	private void drawBoard(Graphics g) {
		int ox = gridSize / 2;
		int oy = gridSize / 2;
		for(int row = 0; row < height - 1; row++) {
			for(int col = 0; col < width - 1; col++) {
				g.drawRect(ox + col * gridSize, oy + row * gridSize, gridSize, gridSize);
			}
		}
		int pointSize = (int)(gridSize * 0.2); 
		int bias = (int)(3.5 * gridSize);
		int len = width * gridSize;
		fillCircleCenter(g, bias, bias, pointSize);
		fillCircleCenter(g, bias, len - bias, pointSize);
		fillCircleCenter(g, len - bias, bias, pointSize);
		fillCircleCenter(g, len - bias, len - bias, pointSize);
		int c = (int)(1.0 * width / 2 * gridSize);
		fillCircleCenter(g, c, c, pointSize);
	}
	
	private void drawPieces(Graphics g) {
		for(int row = 0; row < height; row++) {
			for(int col = 0; col < width; col++) {
				int status = data[row][col];
				if(status != FREE) {
					double rate = 0.8;
					int ox = col * gridSize;
					int oy = row * gridSize;
					int size = (int)(gridSize * rate);
					int x = (int)((1 - rate) / 2 * gridSize + ox);
					int y = (int)((1 - rate) / 2 * gridSize + oy);
					g.drawImage(images.get(status), x, y, size, size, this);
				}
			}
		}
	}
	
	private void drawHover(Graphics g) {
		if(hover != null) {
			int bias = 5;
			int len = gridSize / 5;
			GraphicsUtil.drawHover(g, hover.x, hover.y, bias, len);
		}
	}
	
	private void drawLast(Graphics g) {
		if(last != null) {
			drawCross(g, last, 5);
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		int h = height * gridSize;
		int w = width * gridSize;
		return new Dimension(w, h);
	}

	
	private int getCurruntRow(MouseEvent e) {
		return e.getY() / gridSize;
	}
	
	private int getCurruntCol(MouseEvent e) {
		return e.getX() / gridSize;
	}
	
	public boolean isInRow(int row, int col) {
		int[][][] dirs = new int[][][] {
			{{-1, -1}, {1, 1}},
			{{-1, 1}, {1, -1}},
			{{-1, 0}, {1, 0}},
			{{0, -1}, {0, 1}}
		};
		int n = 5;
		int max = 0;
		int status = data[row][col];
		for(int[][] rows : dirs) {
			int cnt = 1;
			for(int[] d : rows) {
				int dx = d[0];
				int dy = d[1];
				for(int i = 1; i < n; i++) {
					int r = row + dy * i;
					int c = col + dx * i;
					if(isInner(r, c) && data[r][c] == status) {
						cnt++;
					}else {
						break;
					}
				}
			}
			max = max > cnt? max : cnt;
		}
		return max >= n;
	}
	
	private boolean isInner(int row, int col) {
		return row > -1 && row < height && col > -1 && col < width;
	}
	
	class MouseMove implements MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			int row = getCurruntRow(e);
			int col = getCurruntCol(e);
			if(data[row][col] == FREE) {
				int x = (int)((0.5 + col) * gridSize);
				int y = (int)((0.5 + row) * gridSize);
				hover = new Point(x, y);
			}else {
				hover = null;
			}
			repaint();
		}
		
	}
	
	class MouseCmd implements MouseListener{
		private void nextPlayer() {
			currentPlayer = (currentPlayer == BLACK)? WHITE : BLACK;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
//			System.out.println("click");
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
//			System.out.println("press");
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			System.out.println("release");
			int row = getCurruntRow(e);
			int col = getCurruntCol(e);
			if(data[row][col] == FREE) {
				System.out.println(++step);
				data[row][col] = currentPlayer;
				if(step % 2 == currentPlayer) System.out.println("err");
				hover = null;
				int x = (int)((0.5 + col) * gridSize);
				int y = (int)((0.5 + row) * gridSize);
				last = new Point(x , y);
				repaint();
				if(isInRow(row, col)) {
					String player = (currentPlayer == BLACK)? "BLACK" : "WHITE";
					String msg = String.format("%s win\nplay again?", player);
					int code = JOptionPane.showConfirmDialog(Board.this, msg);
					if(code == JOptionPane.YES_OPTION) {
						init();
					}else {
						System.exit(0);
					}
				}else {
					nextPlayer();
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
		}
		

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
