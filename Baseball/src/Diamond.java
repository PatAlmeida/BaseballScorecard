import java.awt.*;

public class Diamond {

  private static int DIAMOND_PADDING = 10;
  private static int DIAMOND_SIZE = 100;
  private static Font FONT = new Font("Times", Font.BOLD, 20);

  private BaselineList baselineList;
  private Position pos;
  private String centerText;
  private int size, cx, cy, outNum, rbiCount;
  private DiamondPanel diamondPanel;
  private Rectangle rect;
  private boolean hoveredOver, showCursor, typing, isOut, inningEnd, hasRBI;

  public Diamond() {
    pos = new Position(DIAMOND_PADDING, DIAMOND_PADDING);
    size = DIAMOND_SIZE;
    centerText = "";
    hoveredOver = false;
    showCursor = false;
    typing = false;
    isOut = false;
    inningEnd = false;
    hasRBI = false;
    outNum = 1;
    rbiCount = 1;
    cx = pos.getX() + DIAMOND_SIZE/2;
    cy = pos.getY() + DIAMOND_SIZE/2;
    rect = new Rectangle(pos.getX(), pos.getY(), DIAMOND_SIZE, DIAMOND_SIZE);
    baselineList = new BaselineList(this);
    diamondPanel = new DiamondPanel(this);
  }

  public void show(Graphics g) {
    if (baselineList.allSelected()) baselineList.fill(g);
    baselineList.show(g);
    g.setFont(FONT);
    g.setColor(Color.black);
    FontMetrics metrics = g.getFontMetrics(g.getFont());
    int tx = rect.x + (rect.width - metrics.stringWidth(centerText)) / 2;
    int ty = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
    g.drawString(centerText, tx, ty);
    if (showCursor) {
      g.setColor(Color.blue);
      g.fillOval(cx-4, cy-4, 8, 8);
    }
    if (isOut) {
      g.setFont(new Font("Times", Font.PLAIN, 15));
      g.setColor(Color.black);
      g.drawString(Integer.toString(outNum), pos.getX(), pos.getY()+size+2);
      Graphics2D g2 = (Graphics2D) g;
      g2.setStroke(new BasicStroke(1));
      g.drawOval(pos.getX()-6, pos.getY()+size-13, 19, 19);
    }
    if (hasRBI) {
      g.setColor(Color.red);
      for (int i=0; i<rbiCount; i++)
        g.fillOval(pos.getX()-4+10*i, pos.getY()-4, 8, 8);
    }
    if (inningEnd) {
      g.setColor(Color.black);
      Graphics2D g2 = (Graphics2D) g;
      g2.setStroke(new BasicStroke(8));
      g.drawLine(154, 50, 50, 154);
    }
    drawBorder(g);
  }

  private static Color GRASS = new Color(0, 157, 0);
  private static Color BROWN = new Color(142, 88, 60);
  private static Color WHITE = new Color(255, 255, 255);
  private static Color BLACK = new Color(0, 0, 0);
  private static int BASE_SIZE = 11;
  private static int BS = BASE_SIZE;

  public void show2(Graphics g) {
    // draw background
    g.setColor(GRASS);
    g.fillRect(0, 0, 120, 120);
    // draw border
    Graphics2D g2 = (Graphics2D) g;
    g2.setStroke(new BasicStroke(4));
    g.setColor(BLACK);
    g.drawRect(2, 2, 116, 116);
    // draw baseline
    g2.setStroke(new BasicStroke(8));
    g.setColor(BROWN);
    g.drawLine(60, 101+5, 101+5, 60);
    g.drawLine(101+5, 60, 60, 19-5);
    g.drawLine(60, 19-5, 19-5, 60);
    g.drawLine(19-5, 60, 60, 101+5);
    // draw bases
    drawBase(g, 55, 96);
    drawBase(g, 96, 55);
    drawBase(g, 55, 14);
    drawBase(g, 14, 55);
  }

  public void drawBase(Graphics g, int x, int y) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setStroke(new BasicStroke(1));
    // white rectangle
    g.setColor(WHITE);
    g.fillRect(x, y, BS, BS);
    // extra lines (white)
    g.drawLine(x+1, y+BS, x+9, y+BS);
    g.drawLine(x+BS, y+1, x+BS, y+9);
    g.drawLine(x+1, y-1, x+9, y-1);
    g.drawLine(x-1, y+1, x-1, y+9);
    // inner outline
    g.setColor(BLACK);
    g.drawRect(x-2, y-2, BS+3, BS+3);
    // outer outline
    g.drawLine(x-1, y+BS+2, x+BS, y+BS+2);
    g.drawLine(x+BS+2, y+BS, x+BS+2, y-1);
    g.drawLine(x-1, y-3, x+BS, y-3);
    g.drawLine(x-3, y-1, x-3, y+BS);
    // extra outline points
    drawExtraPoints(g, x-1, y+BS-1, 1);
    drawExtraPoints(g, x+BS, y+BS-1, 2);
    drawExtraPoints(g, x+BS, y-1, 3);
    drawExtraPoints(g, x-1, y-1, 4);
  }

  public void drawExtraPoints(Graphics g, int x, int y, int id) {
    drawPoint(g, x, y);
    drawPoint(g, x, y+1);
    if (id == 1) drawPoint(g, x+1, y+1);
    if (id == 2) drawPoint(g, x-1, y+1);
    if (id == 3) drawPoint(g, x-1, y);
    if (id == 4) drawPoint(g, x+1, y);
  }

  public void drawPoint(Graphics g, int x, int y) {
    g.drawLine(x, y, x, y);
  }

  public void drawBorder(Graphics g) {
    int x = pos.getX(); int y = pos.getY(); int s = size;
    Graphics2D g2 = (Graphics2D) g;
    g2.setStroke(new BasicStroke(1));
    g.setColor(Color.black);
    g.drawLine(x-10, y-10, x+s+10, y-10);
    g.drawLine(x+s+10, y-10, x+s+10, y+s+10);
    g.drawLine(x+s+10, y+s+10, x-10, y+s+10);
    g.drawLine(x-10, y+s+10, x-10, y-10);
  }

  public void reset() {
    centerText = "";
    outNum = 1;
    rbiCount = 1;
    isOut = false;
    inningEnd = false;
    hasRBI = false;
    baselineList.reset();
  }

  public void checkHover(int x, int y) {
    baselineList.checkHover(x, y);
    double dist = Math.sqrt(Math.pow(cx-x, 2) + Math.pow(cy-y, 2));
    hoveredOver = dist < 20;
  }

  public void clicked() {
    baselineList.clicked();
  }

  public void doubleClicked() {
    baselineList.doubleClicked();
    if (hoveredOver) {
      showCursor = true;
      centerText = "";
    }
  }

  public void keyPressed(char c) {
    baselineList.keyPressed(c);
    if (!showCursor && !typing && c == 'R') reset();
    if (isOut) {
      if (!showCursor && !typing && c == 'o') {
        outNum++;
        if (outNum > 3) {
          isOut = false;
          outNum = 1;
        }
      }
    } else {
      isOut = !showCursor && !typing && c == 'o';
      outNum = 1;
    }
    if (hasRBI) {
      if (!showCursor && !typing && c == 'r') {
        rbiCount++;
        if (rbiCount > 4) {
          hasRBI = false;
          rbiCount = 1;
        }
      }
    } else {
      hasRBI = !showCursor && !typing && c == 'r';
      rbiCount = 1;
    }
    if (!showCursor && !typing && c == 'e') {
      inningEnd = !inningEnd;
    }
    if (showCursor) {
      typing = true;
      showCursor = false;
    }
    if (typing) {
      if (c == 10) typing = false;
      else {
        centerText += c;
      }
    }
  }

  public int getX() { return pos.getX(); }
  public int getY() { return pos.getY(); }
  public int getSize() { return size; }
  public DiamondPanel getPanel() { return diamondPanel; }
}
