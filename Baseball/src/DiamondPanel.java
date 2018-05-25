import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DiamondPanel extends JPanel implements MouseMotionListener, MouseListener, KeyListener {

  private static int DIAMOND_PANEL_SIZE = 120;
  private static Color PANEL_COLOR = new Color(238, 238, 238);
  private static Font BASELINE_FONT = new Font("Helvetica", Font.BOLD, 18);

  private Diamond diamond;

  public DiamondPanel(Diamond d) {
    setPreferredSize(new Dimension(DIAMOND_PANEL_SIZE+1, DIAMOND_PANEL_SIZE+1));
    addKeyListener(this);
    addMouseListener(this);
    addMouseMotionListener(this);
    setFocusable(true);
    diamond = d;
  }

  public void paint(Graphics g) {
    g.setFont(BASELINE_FONT);
    g.setColor(PANEL_COLOR);
    g.fillRect(0, 0, DIAMOND_PANEL_SIZE, DIAMOND_PANEL_SIZE);
    diamond.show(g);
  }

  public void mouseMoved(MouseEvent e) {
    diamond.checkHover(e.getX(), e.getY());
    requestFocusInWindow();
    repaint();
  }

  public void mouseClicked(MouseEvent e) {
    if (e.getClickCount() == 2) {
      diamond.doubleClicked();
      repaint();
    }
  }

  public void mouseReleased(MouseEvent e) {
     diamond.clicked();
     repaint();
  }

  public void keyTyped(KeyEvent e) {
    diamond.keyPressed(e.getKeyChar());
    repaint();
  }

  public void keyPressed(KeyEvent e) { }
  public void keyReleased(KeyEvent e) { }
  public void mouseDragged(MouseEvent e) { }
  public void mousePressed(MouseEvent e) { }
  public void mouseEntered(MouseEvent e) { }
  public void mouseExited(MouseEvent e) { }

}
