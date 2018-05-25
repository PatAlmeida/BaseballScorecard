import java.awt.*;

public class Baseline {

  private static Color UNSELECTED_COLOR = new Color(210, 210, 210);
  private static Color SELECTED_COLOR = new Color(255, 220, 0);
  private static Color TEXT_COLOR = Color.black;
  private static Color CURSOR_COLOR = Color.blue;
  private static int CURSOR_SIZE = 8;
  private static Font FONT = new Font("Times", Font.BOLD, 20);

  // c: center    b: base    t: text    cu: cursor
  private int id, x, y, s, cx, cy, b0x, b0y, b1x, b1y, tx, ty, cux, cuy;
  private String text;
  private Rectangle rect;
  private Color color;
  private boolean hoveredOver, showCursor, clicked, typing;

  public Baseline(int i, Diamond d) {
    id = i; x = d.getX(); y = d.getY(); s = d.getSize();
    text = "";
    rect = new Rectangle();
    color = UNSELECTED_COLOR;
    hoveredOver = false; showCursor = false; typing = false; clicked = false;
    switch (id) {
      case 0: b0x = x+s/2; b0y = y+s; b1x = x+s; b1y = y+s/2; break;
      case 1: b0x = x+s; b0y = y+s/2; b1x = x+s/2; b1y = y; break;
      case 2: b0x = x+s/2; b0y = y; b1x = x; b1y = y+s/2; break;
      case 3: b0x = x; b0y = y+s/2; b1x = x+s/2; b1y = y+s; break;
    }
    cx = (b0x + b1x) / 2; cy = (b0y + b1y) / 2;
    switch (id) {
      case 0: cux = cx+12; cuy = cy; rect = new Rectangle(b0x, b1y, s/2, s/2); break;
      case 1: cux = cx+12; cuy = cy-8; rect = new Rectangle(b1x, b1y, s/2, s/2); break;
      case 2: cux = cx-20; cuy = cy-8; rect = new Rectangle(b1x, b0y, s/2, s/2); break;
      case 3: cux = cx-20; cuy = cy; rect = new Rectangle(b0x, b0y, s/2, s/2); break;
    }
  }

  public void show(Graphics g) {
    g.setColor(color);
    if (clicked) g.setColor(SELECTED_COLOR);
    Graphics2D g2 = (Graphics2D) g;
    g2.setStroke(new BasicStroke(5));
    g.drawLine(b0x, b0y, b1x, b1y);
    g.setColor(TEXT_COLOR);
    g.setFont(FONT);
    FontMetrics metrics = g.getFontMetrics(g.getFont());
    tx = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
    ty = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
    g.drawString(text, tx, ty);
    if (showCursor) {
      g.setColor(CURSOR_COLOR);
      g.fillOval(cux, cuy, CURSOR_SIZE, CURSOR_SIZE);
    }
  }

  public void reset() {
    text = "";
    clicked = false;
  }

  public void checkHover(int mx, int my) {
    double dist = Math.sqrt(Math.pow(cx-mx, 2) + Math.pow(cy-my, 2));
    if (dist < s/5) {
      if (!hoveredOver) {
        hoveredOver = true;
        color = SELECTED_COLOR;
      }
    } else if (hoveredOver) {
      hoveredOver = false;
      color = UNSELECTED_COLOR;
    }
  }

  public void clicked() {
    if (hoveredOver) clicked = !clicked;
  }

  public void doubleClicked() {
    if (hoveredOver) {
      showCursor = true;
      text = "";
    }
  }

  public void keyPressed(char c) {
    if (showCursor) {
      typing = true;
      showCursor = false;
    }
    if (typing) {
      if (c == 10) typing = false;
      else {
        text += c;
      }
    }
  }

  public int getB0X() { return b0x; }
  public int getB0Y() { return b0y; }
  public boolean isSelected() { return clicked; }

}
