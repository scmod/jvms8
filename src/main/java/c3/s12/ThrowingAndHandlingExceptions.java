package c3.s12;

public class ThrowingAndHandlingExceptions {

	/**
	 * <pre>
	 *  0: iload_1
	 * 	1: ifne          12
	 * 	4: new           #17                 // class java/lang/Exception
	 * 	7: dup
	 * 	8: invokespecial #19                 // Method java/lang/Exception."<init>":()V
	 * 11: athrow
	 * 12: return
	 * </pre>
	 */
	void cantBeZero(int i) throws Exception {
		if (i == 0) {
			throw new Exception();
		}
	}

	/**
	 * <pre>
	 *     0: aload_0
	 *     1: iconst_0
	 *     2: invokevirtual #24                 // Method cantBeZero:(I)V
	 *     5: goto          13
	 *     8: astore_1
	 *     9: aload_1
	 *    10: invokevirtual #26                 // Method java/lang/Exception.printStackTrace:()V
	 *    13: return
	 *  Exception table:
	 *     from    to  target type
	 *        0     5     8   Class java/lang/Exception
	 * </pre>
	 */
	void catchOne() {
		try {
			cantBeZero(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		return;
	}
}
