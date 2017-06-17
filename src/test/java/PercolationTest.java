import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import uf.percolation.Percolation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import static org.testng.Assert.assertEquals;


public class PercolationTest {
    @Test(dataProvider = "percolatesProvider")
    public void testPercolates(String fileName, boolean expectedResult) {
        Assert.assertEquals(readPercolation(fileName).percolates(), expectedResult);
    }

    @Test(dataProvider = "numberOfOpenSitesProvider")
    public void testPercolates(String fileName, int expectedResult) {
        Assert.assertEquals(readPercolation(fileName).numberOfOpenSites(), expectedResult);
    }


    @Test(dataProvider = "isFullDataProvider")
    public void testIsFull(String fileName, int row, int col, boolean expectedResult) {
        Percolation percolation = readPercolation(fileName);
        assertEquals(percolation.isFull(row, col), expectedResult);
    }

    @DataProvider(name = "percolatesProvider")
    private Object[][] providePercolatesData() {
        return new Object[][]{
                {"input1.txt", true},
                {"input1-no.txt", false},
                {"input10.txt", true},
                {"input8-no.txt", false},
                {"input10-no.txt", false},
                {"input50.txt", true}
        };
    }

    @DataProvider(name = "numberOfOpenSitesProvider")
    private Object[][] provideNumberOfOpenSitesData() {
        return new Object[][]{
                {"input50.txt", 1412}
        };
    }

    @DataProvider(name = "isFullDataProvider")
    private Object[][] provideIsFullData() {
        return new Object[][]{
                {"input10.txt", 9, 1, false}
        };
    }

    private Percolation readPercolation(String fileName) {
        InputStream is = this.getClass().getResourceAsStream(fileName);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            Scanner scanner = new Scanner(reader);
            int size = scanner.nextInt();
            Percolation percolation = new Percolation(size);
            while (scanner.hasNext()) {
                percolation.open(scanner.nextInt(), scanner.nextInt());
            }
            return percolation;
        } catch (IOException e) {
            throw new IllegalStateException(String.format("Failed to open test file %s", fileName), e);
        }
    }
}