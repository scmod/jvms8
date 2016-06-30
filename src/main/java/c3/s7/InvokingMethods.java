package c3.s7;

public class InvokingMethods {
	/**
	 * <pre>
	 *  0: aload_0
	 *  1: invokevirtual #15                 // Method invokevirtual:()V
	 *  4: invokestatic  #18                 // Method invokestatic:()V
	 *  7: aload_0
	 *  8: invokespecial #21                 // Method invokespecial:()V
	 * 11: return
	 * </pre>
	 */
	void total() {
		invokevirtual();
		invokestatic();
		invokespecial();
	}

	void invokevirtual() {
	}

	static void invokestatic() {
	}

	private void invokespecial() {
	}
}
