package c3.s9;

public class Arrays {

	/**
	 * <pre>
	 *  0: bipush        10
	 *  2: newarray       int
	 *  4: astore_1
	 *  5: iconst_3
	 *  6: newarray       int
	 *  8: dup
	 *  9: iconst_0
	 * 10: iconst_1
	 * 11: iastore
	 * 12: dup
	 * 13: iconst_1
	 * 14: iconst_2
	 * 15: iastore
	 * 16: dup
	 * 17: iconst_2
	 * 18: iconst_3
	 * 19: iastore
	 * 20: astore_2
	 * 21: return
	 * </pre>
	 */
	void newarray() {
		int[] i = new int[10];
		int[] j = { 1, 2, 3 };
	}

	/**
	 * <pre>
	 *  0: bipush        10
	 *  2: anewarray     #3                  // class java/lang/Object
	 *  5: astore_1
	 *  6: return
	 * </pre>
	 */
	void anewarray() {
		Object[] o = new Object[10];
	}

	/**
	 * <pre>
	 *  0: iconst_5
	 *  1: iconst_5
	 *  2: multianewarray #22,  2            // class "[[Ljava/lang/Object;"
	 *  6: astore_1
	 *  7: bipush        10
	 *  9: bipush        10
	 * 11: multianewarray #24,  2            // class "[[I"
	 * 15: astore_2
	 * 16: return
	 * </pre>
	 */
	void multianewarray() {
		Object[][] o = new Object[5][5];
		int[][] i = new int[10][10];
	}
}
