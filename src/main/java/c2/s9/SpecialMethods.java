package c2.s9;

import java.lang.invoke.MethodType;

public class SpecialMethods {
	public static void main(String[] args) throws Throwable {
		SpecialMethods sm = new SpecialMethods();
		System.out.println(java.lang.invoke.MethodHandles
				.lookup()
				.findSpecial(SpecialMethods.class, "hashCode",
						MethodType.methodType(Integer.TYPE),
						SpecialMethods.class).invoke(sm));
		System.out.println(SpecialMethods.class.getMethod("hashCode",
				new Class[0]).invoke(sm, new Object[0]));
		System.out.println(sm.hashCode());

		System.out.println(java.lang.invoke.MethodHandles
				.lookup()
				.findStatic(
						SpecialMethods.class,
						"method",
						MethodType.methodType(String.class, Integer.TYPE,
								new Class[] { Integer.class })).invoke(1, 2));
	}

	static String method(int i1, Integer i2) {
		return i1 + " " + i2;
	}
}
