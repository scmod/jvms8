package c3.s4;

public class AccessingTheRunTimeConstantPool {
	//
	public static void main(String[] args) {
		
		/*
		 * 大于Short.MAX_VALUE的值不会通过bipush或者sipush来直接入栈,而是会存在常量池里
		 * 通过ldc来获取,常量池里的个数超过Byte.MAX_VALUE的会通过ldc_w和store_w来操作
		 */
		for (int i = 1; i < 256; i++) {
			System.out.print("int i" + i + " = " + (Short.MAX_VALUE + i) + ";");
		}
	}

	void useManyNumeric() {
		int i = 100;
		int j = 100000;
		long l1 = 1L;
		long l2 = -1L;
		double d = 2.2D;
		float f1 = 1.0F;
		float f2 = 1.0F;
	}
}
