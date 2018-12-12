package algorithm.leetcode.problem.P147;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static algorithm.util.CommonUtils.listNodeToString;
import static algorithm.util.CommonUtils.stringToListNode;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Using @TestInstance, a new test instance will be created once per test class.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SolutionTest {
    private Solution solution;

    @BeforeAll
    void init() {
        solution = new Solution();
    }

    @ParameterizedTest
    @MethodSource("parametersProvider")
    void shouldSortLinkedListSuccessful(String expected, String input) {
        assertEquals(expected, listNodeToString(solution.insertionSortList(stringToListNode(input))));
    }

    private static Stream<Arguments> parametersProvider() {
        return Stream.of(
                Arguments.of("[1, 2, 3, 4]", "[4, 2, 1, 3]"),
                Arguments.of("[-1, 0, 3, 4, 5]", "[-1, 5, 3, 4, 0]")
        );
    }

    /**
     * stupid style.
     * For junit 5, refer to above
     */
//    @Ignore
    @Test
    void insertionSortList() {
        assertEquals("[1, 2, 3, 4]", listNodeToString(solution.insertionSortList(stringToListNode("[4, 2, 1, 3]"))));
        assertEquals("[-1, 0, 3, 4, 5]", listNodeToString(solution.insertionSortList(stringToListNode("[-1, 5, 3, 4, 0]"))));
    }
}