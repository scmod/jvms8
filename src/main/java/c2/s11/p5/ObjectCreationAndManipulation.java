package c2.s11.p5;

public class ObjectCreationAndManipulation {
	public static void main(String[] args) {
		/**
		 * <pre>
		 *  0: bipush        10
		 *  2: newarray       int
		 *  4: astore_1
		 *  5: iconst_3
		 * </pre>
		 */
		int[] i = new int[10];
		/**
		 * <pre>
		 *   6: newarray       int
		 *   8: dup
		 *   9: iconst_0
		 *  10: iconst_1
		 *  11: iastore
		 *  12: dup
		 *  13: iconst_1
		 *  14: iconst_2
		 *  15: iastore
		 *  16: dup
		 *  17: iconst_2
		 *  18: iconst_3
		 *  19: iastore
		 *  20: astore_2
		 * </pre>
		 */
		int[] j = { 1, 2, 3 };
		/**
		 * <pre>
		 * 21: iconst_1
		 * 22: iconst_m1
		 * 23: multianewarray #16,  2            // class "[[I"
		 * 27: astore_3
		 * 28: return
		 * </pre>
		 */
		int[][] k = new int[1][-1];
	}
}
