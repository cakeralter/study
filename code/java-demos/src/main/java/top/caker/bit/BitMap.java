package top.caker.bit;

/**
 * BitMap可以用于快速统计大数据中一个数是否存在
 * <p>
 * 1000_000的数据均匀分散到int数据中只需要 1000_000 / (4 * 8) + 1的存储空间
 * 注: int为4byte 每个字节可以存储8bit
 * <p>
 * 对于一个数num，首先计算其在int数组中的下标 即 index = num / (4 * 8)
 * 再计算其在该下标整数中的位数 即 point = num % (4 * 8)
 * bits[index] | (1 << point) 将该位置1
 * bits[index] & (~(1 << point)) 将该位置0
 * bits[index] & (1 << point) 是否为0可判断该位是否已用
 *
 * @author cakeralter
 * @date 2020/5/14
 */
public class BitMap {
    private final static int UNSIGNED_INT_BIT_LENGTH = 32;
    private final int[] bits;
    private final int max;

    public BitMap(int max) {
        this.max = max;
        this.bits = new int[max / UNSIGNED_INT_BIT_LENGTH + 1];
    }

    /**
     * 放入一个数据
     *
     * @param num
     */
    public void put(int num) {
        if (num < 0 || num > max) {
            throw new IllegalArgumentException("非法值" + num);
        }
        int index = getIndex(num);
        int point = getPoint(num);
        bits[index] |= (1 << point);
    }

    /**
     * 删除一个数据
     *
     * @param num
     */
    public void del(int num) {
        if (num < 0 || num > max) {
            throw new IllegalArgumentException("非法值" + num);
        }
        int index = getIndex(num);
        int point = getPoint(num);
        bits[index] &= ~(1 << point);
    }

    /**
     * 判断数据是否存在
     *
     * @param num
     * @return
     */
    public boolean exists(int num) {
        if (num < 0 || num > max) {
            throw new IllegalArgumentException("非法值" + num);
        }
        int index = getIndex(num);
        int point = getPoint(num);
        return (bits[index] & (1 << point)) != 0;
    }

    /**
     * 获取下标位置
     *
     * @param num
     * @return
     */
    private int getIndex(int num) {
//        return num / UNSIGNED_INT_BIT_LENGTH;
        return num >> 5;
    }

    /**
     * 获取指针位置
     *
     * @param num
     * @return
     */
    private int getPoint(int num) {
        return num % UNSIGNED_INT_BIT_LENGTH;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(max + UNSIGNED_INT_BIT_LENGTH);
        builder.append("BitMap{\n");
        for (int bit : bits) {
            builder.append(Integer.toBinaryString(bit));
        }
        builder.append("\n}");
        return builder.toString();
    }
}
