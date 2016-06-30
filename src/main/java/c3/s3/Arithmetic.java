package c3.s3;

public class Arithmetic {

	public static void main(String[] args) {
		int result = 0;
		for (int g = 1; g <= 1000; g++)
			result ^= g;
		System.out.println(result ^ 1002);
	}

	/**
	 * <pre>
	 *  0: iload_1
	 *  1: iload_2
	 *  2: iadd
	 *  3: iconst_1
	 *  4: isub
	 *  5: iload_2
	 *  6: iconst_1
	 *  7: isub
	 *  8: iconst_m1
	 *  9: ixor
	 * 10: iand
	 * 11: ireturn
	 * </pre>
	 * 
	 * 这里有个公式 ~x = -1^x
	 */
	int align2grain(int i, int grain) {
		return (i + grain - 1) & ~(grain - 1);
	}
}
