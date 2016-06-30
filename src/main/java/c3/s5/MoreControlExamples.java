package c3.s5;

public class MoreControlExamples {
	/**
	 * <pre>
	 *  0: ldc2_w        #19                 // double 0.1d
	 *  3: dstore_0
	 *  4: goto          18
	 *  7: getstatic     #21                 // Field java/lang/System.out:Ljava/io/PrintStream;
	 * 10: dload_0
	 * 11: dup2
	 * 12: dconst_1
	 * 13: dadd
	 * 14: dstore_0
	 * 15: invokevirtual #27                 // Method java/io/PrintStream.println:(D)V
	 * 18: dload_0
	 * 19: ldc2_w        #33                 // double 100.1d
	 * 22: dcmpg
	 * 23: iflt          7
	 * 26: return
	 * </pre>
	 */
	static void whileDouble() {
		double i = 0.1D;
		while (i < 100.1D) {
			System.out.println(i++);
		}
	}
}
