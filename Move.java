public class Move {
    private int xCoor;
    private int yCoor;
    private int score;
    public Move(int x, int y, int s) {
      xCoor = x;
      yCoor = y;
      score = s;
    }
    public int getXCoor() {
      return this.xCoor;
    }
    public int getYCoor() {
      return this.yCoor;
    }
    public int getScore() {
      return this.score;
    }
    public void setXCoor(int x) {
      this.xCoor = x;
    }
    public void setYCoor(int y) {
      this.yCoor = y;
    }
    public void setScore(int s) {
      this.score = s;
    }
}
