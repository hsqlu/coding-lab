package basic.bit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BasicBitTest {
    /**
     * 判断奇偶
     * <p>
     * (num & 1) == 0就是偶数
     */
    @Test
    public void testIsOdd() {
        assertTrue((2 & 1) == 0);
        assertTrue((2 % 2) == 0);
        assertFalse((13 & 1) == 0);
        assertFalse((13 % 2) == 0);
    }

    /**
     * 交换
     * <p>
     * 几个特性：
     * 1）^异或操作符：两个位相同为0，相异为1
     * 2）一个数异或本身恒等于0，如5^5恒等于0
     * 3）一个数异或0恒等于本身，如5^0恒等于5
     * <p>
     * 满足交换律：
     * a = a^b
     * b = b^a = b^(a^b) = a^(b^b)=a^0=a
     * a = a^b = (a^b)^b^(a^b) = b^(a^a)^(b^b) = b^0^0 = b
     */
    @Test
    public void testSwap() {
        int a = 1;
        int b = 2;
        a ^= b;
        b ^= a;
        a ^= b;
        assertEquals(2, a);
        assertEquals(1, b);
    }

    /**
     * 取反
     * <p>
     * 十进制：-11
     * 二进制：11110101
     * 第一步：取反00001010
     * 第二步：加1 => 00001011
     * 结果就是11
     * <p>
     * 原因是补码=反码+1，因此才有所谓的+1.
     * 使用补码, 一是为了防止0有2个编码，其次就是为了把减法运算用加法运算表示出来，
     * 以达到简化电路的作用（因为有了负数的概念，减法可以换算为加法） 而且还能够多表示一个最低数.
     * 这就是为什么8位二进制, 使用原码或反码表示的范围为[-127, +127], 而使用补码表示的范围为[-128, 127].
     */
    @Test
    public void testReverse() {
        assertEquals(-15, ~15 + 1);
        System.out.println(Integer.toBinaryString(10));
        System.out.println(Integer.toBinaryString(-10));
        System.out.println(Integer.toBinaryString(11));
        System.out.println(Integer.toBinaryString(-11));
    }

    @Test
    public void testFindRightMost1Bit() {
        int x = 10;
        System.out.println(Integer.toBinaryString(x));
        int res = x & -x;
        assertEquals(2, res);

        x = 768;
        System.out.println(Integer.toBinaryString(x));
        res = x & -x;
        assertEquals(256, res);

    }
    /**
     * 求绝对值
     * <p>
     * 方法1：按照{@link #testReverse()}如果是负数就这么处理
     * 方法2：任何数，与0异或都会保持不变，即-1（0xFFFFFFFF）异或相当于取反，然后+1或者+0就是绝对值了
     */
    @Test
    public void testAbs() {
        int a = -5;
        // 右移31位取符号位判断正负
        //>> 算术右移：低位溢出，符号位不变，并用符号位补溢出的高位
        //>>>逻辑右移：低位溢出，高位补0
        assertEquals(5, (a >> 31) == 0 ? a : ~a + 1);

        // 优化版本
        assertEquals(5, (a ^ (a >> 31)) - (a >> 31));
    }

    /**
     * 太tricky了
     */
    @Test
    public void add() {
        assertEquals(9, getSum(4, 5));
    }

    int getSum(int a, int b) {
        return b == 0 ? a : getSum(a ^ b, (a & b) << 1); //be careful about the terminating condition;
    }

    @Test
    public void findNearestPowerOf2() {
        assertEquals(32L, largestPower(62L));
        assertEquals(128, largestPower(129));
        assertEquals(128, Integer.highestOneBit(129));
        assertEquals(128, Integer.highestOneBit(255));
        assertEquals(256, Integer.highestOneBit(256));
        assertEquals(256, Integer.highestOneBit(257));
    }

    /**
     * {@link Integer#highestOneBit(int)}思想一样
     */
    long largestPower(long N) {
        //changing all right side bits to 1.
        N = N | (N >> 1);
        N = N | (N >> 2);
        N = N | (N >> 4);
        N = N | (N >> 8);
        N = N | (N >> 16);
        return N - (N >>> 1);
    }

    @Test
    public void misc() {
        // Integer.MAX_VALUE
        System.out.println((1 << 31) - 1);
        System.out.println(Integer.toBinaryString((1 << 31) - 1));
        System.out.println(Integer.toBinaryString(1 << 31));
        System.out.println(~(1 << 31));

        // Integer.MIN_VALUE
        System.out.println(1 << 31);
        System.out.println(1 << -1);

        // Long.MAX_VALUE
        System.out.println(((long) 1 << 63) - 1);
        System.out.println(Long.MAX_VALUE);

        // 乘以2
        System.out.println(10 << 1);

        // 除以2
        System.out.println(10 >> 1);

        // 判断是否相等
        System.out.println((10 ^ 12) > 0);

        // 计算2的10次方
        System.out.println(2 << (10 - 1));

        // 判断是否是2的幂，如果是n一定是1000... n-1就是111...，与之后就是0
        int n = 7;
        System.out.println((n & (n - 1)) == 0);

        // 求平均数
        System.out.println((10 + 20) >> 1);

        // 从低位到高位，将n的第m个位置置为1
        n = 234345;
        int m = 4;
        n |= (1 << m);
        System.out.println(n);

        // 从低位到高位，将n的第m个位置置为0
        n &= ~(1 << m);
        System.out.println(n);

        n = 3;
        System.out.println(Integer.toBinaryString(n));
        System.out.println(Integer.toBinaryString(-n));

        int start = Integer.MAX_VALUE / 2;
        int end = Integer.MAX_VALUE;
        System.out.println("start = " + start);
        System.out.println("end = " + end);
        System.out.println("mid=" + (start + end) / 2);
        System.out.println("mid=" + ((start + end) >> 1));
        System.out.println("mid=" + ((start + end) >>> 1));

        System.out.println("========");
        start = 100;
        end = 300;
        System.out.println("start = " + start);
        System.out.println("end = " + end);
        System.out.println("mid=" + (start + end) / 2);
        System.out.println("mid=" + ((start + end) >> 1));
        System.out.println("mid=" + ((start + end) >>> 1));
    }
}
