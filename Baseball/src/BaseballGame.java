public class BaseballGame {

  private Lineup lineup;
  private InningList inningList;

  public BaseballGame() {
    lineup = new Lineup();
    inningList = new InningList();
  }

  public PlayerPanel getBatterPanel(int i) {
    return lineup.getBatterPanel(i);
  }

  public DiamondPanel getDiamondPanel(int i, int j) {
    return inningList.getDiamondPanel(i, j);
  }

}
