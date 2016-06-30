package c2.s11.p1;

public class TypesAndTheJavaVirtualMachine {

	public static void main(String[] args) {
		sign_extend();
	}

	/**
	 * <pre>
	 *  0: dconst_1
	 *  1: dstore_1
	 *  2: iconst_1
	 *  3: istore_3
	 *  4: dload_1
	 *  5: iload_3
	 *  6: i2d
	 *  7: dmul
	 *  8: dstore        4
	 * 10: return
	 * </pre>
	 * 
	 * i2d转成double后用dmul,将操作分解而不是将他们进行正交组合出来idmul之类的操作符 这样可以减少操作符数量
	 */
	void orthogonal() {
		double a = 1.0D;
		int b = 1;
		double i = a * b;
	}

	/**
	 * 带符号扩展和零扩展,当用更多的内存存储某一个有符号数时,符号位扩展好依旧要保持在第一位,零扩展就是全补零
	 */
	static void sign_extend() {
		short s = 1;
		System.out.printf("%x%n", new Object[] { Short.valueOf(s) });
		int i = s;

		System.out.printf("%x%n", new Object[] { Short.valueOf(s) });
		s = -1;

		System.out.printf("%x%n", new Object[] { Short.valueOf(s) });
		i = s;
		System.out.printf("%x%n", new Object[] { Integer.valueOf(i) });
	}

	/**
	 * <pre>
	 *  0: iconst_1
	 *  1: istore_0
	 *  2: bipush        50
	 *  4: istore_1
	 *  5: iload_1
	 *  6: istore_2
	 *  7: return
	 * </pre>
	 */
	static void zero_extend() {
		boolean b = true;
		char c = 50;
		int ic = c;
	}

	/**
	 * <pre>
	 *  0: iconst_1
	 *  1: istore_0
	 *  2: iconst_2
	 *  3: istore_1
	 *  4: iload_0
	 *  5: iload_1
	 *  6: if_icmpne     11
	 *  9: iconst_1
	 * 10: ireturn
	 * 11: iconst_0
	 * 12: ireturn
	 * </pre>
	 * 
	 * int类型有现成的操作符if_icmpxx
	 */
	static boolean icmp() {
		int i = 1;
		int j = 2;
		return i == j;
	}

	/**
	 * <pre>
	 *  0: lconst_1
	 *  1: lstore_0
	 *  2: ldc2_w        #62                 // long 2l
	 *  5: lstore_2
	 *  6: lload_0
	 *  7: lload_2
	 *  8: lcmp
	 *  9: iflt          14
	 * 12: iconst_1
	 * 13: ireturn
	 * 14: iconst_0
	 * 15: ireturn
	 * </pre>
	 * 
	 * 其他类型比如long要通过lcmp返回一个类似-1,0,1这样的int值 然后通过ifxx来判断
	 */
	static boolean lcmp() {
		long i = 1L;
		long j = 2L;
		return i >= j;
	}

	/**
	 * <pre>
	 *  0: new           #3                  // class java/lang/Object
	 *  3: dup
	 *  4: invokespecial #8                  // Method java/lang/Object."<init>":()V
	 *  7: astore_0
	 *  8: new           #3                  // class java/lang/Object
	 * 11: dup
	 * 12: invokespecial #8                  // Method java/lang/Object."<init>":()V
	 * 15: astore_1
	 * 16: aload_0
	 * 17: aload_1
	 * 18: if_acmpeq     23
	 * 21: iconst_1
	 * 22: ireturn
	 * 23: iconst_0
	 * 24: ireturn
	 * </pre>
	 */
	static boolean acmp() {
		Object o1 = new Object();
		Object o2 = new Object();
		return o1 != o2;
	}
}
