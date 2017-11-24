package puzzles;

import org.testng.annotations.DataProvider;

public class BoardTestProvider {

  @DataProvider
  public static Object[][] provideHamming() {
    return new Object[][]{
        {
            new int[][]
                {
                    {8, 1, 3},
                    {4, 0, 2},
                    {7, 6, 5}
                },
            5
        }
    };
  }

  @DataProvider
  public static Object[][] provideManhattan() {
    return new Object[][]{
        {
            new int[][]
                {
                    {8, 1, 3},
                    {4, 0, 2},
                    {7, 6, 5}
                },
            10
        }
    };
  }

}
