public class Scorecard {

  private BaseballGame game;
  private ScorecardPanel scorecardPanel;

  public Scorecard() {
    game = new BaseballGame();
    scorecardPanel = new ScorecardPanel(this);
  }

  public PlayerPanel getBatterPanel(int i) {
    return game.getBatterPanel(i);
  }

  public DiamondPanel getDiamondPanel(int i, int j) {
    return game.getDiamondPanel(i, j);
  }

  public PlayerPanel getNextBatter(int i, String s) {
    if (s.equals("DOWN")) {
      i++; if (i > 9) i -= 9;
    } else {
      i--; if (i < 1) i += 9;
    }
    return game.getBatterPanel(i);
  }

  public ScorecardPanel getPanel() {
    return scorecardPanel;
  }

}
