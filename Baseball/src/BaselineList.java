import java.awt.*;

public class BaselineList {

  private Baseline[] list;

  public BaselineList(Diamond d) {
    list = new Baseline[4];
    for (int i=0; i<4; i++) list[i] = new Baseline(i, d);
  }

  public void show(Graphics g) {
    for (int i=0; i<4; i++) list[i].show(g);
  }

  public boolean allSelected() {
    boolean all = true;
    for (int i=0; i<4; i++) {
      if (!list[i].isSelected()) all = false;
    }
    return all;
  }

  public void reset() {
    for (int i=0; i<4; i++) list[i].reset();
  }

  public void fill(Graphics g) {
    int[] xpnts = new int[4];
    int[] ypnts = new int[4];
    for (int i=0; i<4; i++) {
      xpnts[i] = list[i].getB0X();
      ypnts[i] = list[i].getB0Y();
    }
    g.setColor(new Color(255, 252, 206));
    g.fillPolygon(xpnts, ypnts, 4);
  }

  public void checkHover(int x, int y) {
    for (int i=0; i<4; i++) list[i].checkHover(x, y);
  }

  public void clicked() {
    for (int i=0; i<4; i++) list[i].clicked();
  }

  public void doubleClicked() {
    for (int i=0; i<4; i++) list[i].doubleClicked();
  }

  public void keyPressed(char c) {
    for (int i=0; i<4; i++) list[i].keyPressed(c);
  }

}
