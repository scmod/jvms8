package c3.s10;

public class CompilingSwitches {

	/**
	 * <pre>
	 *  0: iload_1
	 *  1: tableswitch   { // 0 to 2
	 *                0: 28
	 *                1: 30
	 *                2: 32
	 *          default: 34
	 *     }
	 * </pre>
	 */
	int tableswitch1(int i) {
		switch (i) {
		case 0:
			return 0;
		case 1:
			return 1;
		case 2:
			return 2;
		}
		return -1;
	}

	/**
	 * <pre>
	 *  1: tableswitch   { // 0 to 6
	 *                0: 44
	 *                1: 46
	 *                2: 50
	 *                3: 50
	 *                4: 50
	 *                5: 50
	 *                6: 48
	 *          default: 50
	 *     }
	 * </pre>
	 * 
	 * 如果值间隔比较小会自动补齐当中缺少的部分
	 */
	int tableswitch2(int i) {
		switch (i) {
		case 0:
			return 0;
		case 1:
			return 1;
		case 6:
			return 3;
		}
		return -1;
	}

	/**
	 * <pre>
	 *  1: lookupswitch  { // 3
	 *                0: 36
	 *                1: 38
	 *                7: 40
	 *          default: 42
	 *     }
	 * </pre>
	 * 
	 * 间隔较大会编译成lookupswitch
	 */
	int lookupswitch1(int i) {
		switch (i) {
		case 0:
			return 0;
		case 1:
			return 1;
		case 7:
			return 3;
		}
		return -1;
	}

	/**
	 * <pre>
	 * 1: lookupswitch  { // 3
	 *              -7: 40
	 *               0: 36
	 *               1: 38
	 *         default: 42
	 *    }
	 * </pre>
	 * 
	 * 并且规范规定lookupswitch的值必须按key排序,这样能提高性能
	 * tableswitch可以直接检查边界,性能上可能会好一丢丢..
	 */
	int lookupswitch2(int i) {
		switch (i) {
		case 0:
			return 0;
		case 1:
			return 1;
		case -7:
			return 3;
		}
		return -1;
	}
}
