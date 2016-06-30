package c4.s3.p3;

public class MethodDescriptors {
	public static void main(String[] args) {
		System.out.println(MethodDescriptors.class.getDeclaredMethods()[0]
				.getParameterTypes()[0]);
		System.out.print("void method(");
		//参数数量不能超过255,对于实例方法this是隐含的也算在里面
		for (int i = 0; i < 255; i++) {
			System.out.print(", int i" + i);
		}
		System.out.print(") { }");
	}
}
