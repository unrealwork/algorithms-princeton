package uf.unionfid;


public class QuickUnionUnionFind implements UnionFind {

  private int[] id;

  public QuickUnionUnionFind(int n) {
    id = new int[n];
    for (int i = 0; i < n; i++) {
      id[i] = i;
    }
  }

  public boolean connected(int p, int q) {
    return root(p) == root(q);
  }

  public void union(int p, int q) {
    int rootP = root(p);
    int rootQ = root(q);
    id[rootP] = rootQ;
  }

  private int root(int q) {
    int index = q;
    while (id[index] != index) {
      index = id[index];
    }
    return index;
  }
}
