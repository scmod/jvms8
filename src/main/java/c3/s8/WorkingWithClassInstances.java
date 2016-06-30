package c3.s8;

public class WorkingWithClassInstances {
	static {
		System.out.println("static");
	}

	// 普通代码块会被类似内联的直接编译到构造方法里
	{
		System.out.println("ins");
	}

	private int i;

	/**
	 * <pre>
	 *  0: aload_0
	 *  1: invokespecial #27                 // Method java/lang/Object."<init>":()V
	 *  4: getstatic     #10                 // Field java/lang/System.out:Ljava/io/PrintStream;
	 *  7: ldc           #29                 // String ins
	 *  9: invokevirtual #18                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
	 * 12: getstatic     #10                 // Field java/lang/System.out:Ljava/io/PrintStream;
	 * 15: ldc           #31                 // String !!
	 * 17: invokevirtual #18                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
	 * 20: return
	 * </pre>
	 */
	public WorkingWithClassInstances() {
		System.out.println("!!");
	}

	/**
	 * <pre>
	 *  0: aload_0
	 *  1: new           #36                 // class java/lang/StringBuilder
	 *  4: dup
	 *  5: iload_1
	 *  6: invokestatic  #38                 // Method java/lang/String.valueOf:(I)Ljava/lang/String;
	 *  9: invokespecial #44                 // Method java/lang/StringBuilder."<init>":(Ljava/lang/String;)V
	 * 12: invokevirtual #46                 // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
	 * 15: invokespecial #50                 // Method "<init>":(Ljava/lang/String;)V
	 * 18: return
	 * </pre>
	 */
	public WorkingWithClassInstances(int i) {
		// 普通代码块不会被写到这个this调用的构造方法里
		this(i + "");
	}

	/**
	 * <pre>
	 *  0: aload_0
	 *  1: invokespecial #27                 // Method java/lang/Object."<init>":()V
	 *  4: getstatic     #10                 // Field java/lang/System.out:Ljava/io/PrintStream;
	 *  7: ldc           #29                 // String ins
	 *  9: invokevirtual #18                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
	 * 12: getstatic     #10                 // Field java/lang/System.out:Ljava/io/PrintStream;
	 * 15: ldc           #51                 // String str
	 * 17: invokevirtual #18                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
	 * 20: return
	 * </pre>
	 */
	public WorkingWithClassInstances(String str) {
		System.out.println("str");
	}

	/**
	 * <pre>
	 *  0: aload_0
	 *  1: iload_1
	 *  2: putfield      #55                 // Field i:I
	 *  5: return
	 * </pre>
	 */
	void setIt(int value) {
		this.i = value;
	}

	/**
	 * <pre>
	 *  0: aload_0
	 *  1: getfield      #55                 // Field i:I
	 *  4: ireturn
	 * </pre>
	 */
	int getIt() {
		return this.i;
	}
}
