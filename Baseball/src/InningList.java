public class InningList {

  private Inning[] list;

  public InningList() {
    list = new Inning[9];
    for (int i=0; i<9; i++) list[i] = new Inning(i+1);
  }

  public DiamondPanel getDiamondPanel(int i, int j) {
    return list[i-1].getDiamondPanel(j);
  }

}
