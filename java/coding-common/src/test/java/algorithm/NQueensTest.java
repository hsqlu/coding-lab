package algorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NQueensTest {

    @Test
    void solveNQueens() {
        int res = new NQueens().solveNQueens(8).size();
        assertEquals(res, new NQueens().anotherApproach(8));
    }
}