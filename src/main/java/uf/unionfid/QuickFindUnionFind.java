package uf.unionfid;


public class QuickFindUnionFind implements UnionFind {

  private int[] id;

  /**
   * Initiate union-find solver for n points.
   *
   * @param n - number of points.
   */
  public QuickFindUnionFind(int n) {
    id = new int[n];
    for (int i = 0; i < n; i++) {
      id[i] = i;
    }
  }

  /**
   * Are points connected?
   *
   * @param p - first point.
   * @param q - second poit.
   * @return true if connected.
   */
  public boolean connected(int p, int q) {
    return id[p] == id[q];
  }


  /**
   * Connect two points.
   *
   * @param p - first point.
   * @param q - second point.
   */
  public void union(int p, int q) {
    int pid = id[p];
    int qid = id[q];

    for (int i = 0; i < id.length; i++) {
      if (id[i] == pid) {
        id[i] = qid;
      }
    }
  }
}
