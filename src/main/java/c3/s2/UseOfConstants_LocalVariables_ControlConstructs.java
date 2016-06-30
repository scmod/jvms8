package c3.s2;

public class UseOfConstants_LocalVariables_ControlConstructs {
	/**
	 * <pre>
	 *  0: iconst_0
	 *  1: istore_1
	 *  2: goto          8
	 *  5: iinc          1, 1
	 *  8: iload_1
	 *  9: bipush        100
	 * 11: if_icmplt     5
	 * 14: return
	 * </pre>
	 */
	void spin() {
		for (int i = 0; i < 100; i++) {
		}
	}

	/**
	 * <pre>
	 *  0: dload_1
	 *  1: dload_3
	 *  2: dadd
	 *  3: dreturn
	 * </pre>
	 * double和long类型一次占两格
	 */
	double doubleLocals(double d1, double d2) {
		return d1 + d2;
	}
}
