public class Inning {

  private DiamondList diamondList;
  private int num;

  public Inning(int i) {
    diamondList = new DiamondList();
    num = i;
  }

  public DiamondPanel getDiamondPanel(int i) {
    return diamondList.getDiamondPanel(i);
  }

}
