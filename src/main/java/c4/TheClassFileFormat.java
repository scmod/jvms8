package c4;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.text.translate.CharSequenceTranslator;
import org.apache.commons.lang3.text.translate.EntityArrays;
import org.apache.commons.lang3.text.translate.JavaUnicodeEscaper;
import org.apache.commons.lang3.text.translate.LookupTranslator;

public class TheClassFileFormat implements Serializable, Cloneable {

	private static final int CONSTANT_Class = 7;
	private static final int CONSTANT_Fieldref = 9;
	private static final int CONSTANT_Methodref = 10;
	private static final int CONSTANT_InterfaceMethodref = 11;
	private static final int CONSTANT_String = 8;
	private static final int CONSTANT_Integer = 3;
	private static final int CONSTANT_Float = 4;
	private static final int CONSTANT_Long = 5;
	private static final int CONSTANT_Double = 6;
	private static final int CONSTANT_NameAndType = 12;
	private static final int CONSTANT_Utf8 = 1;
	private static final int CONSTANT_MethodHandle = 15;
	private static final int CONSTANT_MethodType = 16;
	private static final int CONSTANT_InvokeDynamic = 18;

	private static final int Ref = 22;

	private static final String TYPE = "type";
	private static final String VALUE = "value";

	public static void main(String[] args) throws IOException,
			URISyntaxException {
		// invoke lambda to bring in
		// CONSTANT_MethodHandle,CONSTANT_MethodType,CONSTANT_InvokeDynamic
		// ignore it temporary
		// new Thread(() -> {}).start();
		// RandomAccessFile di = new RandomAccessFile(TheClassFileFormat.class
		// .getResource("TheClassFileFormat.class").toURI().getPath(), "r");
		DataInput di = new DataInputStream(
				TheClassFileFormat.class
						.getResourceAsStream("TheClassFileFormat.class"));
		System.out.println("magic : " + Integer.toHexString(di.readInt()));
		System.out.println("minor_version : " + di.readUnsignedShort());
		System.out.println("major_version : " + di.readUnsignedShort());

		int constant_pool_count = di.readUnsignedShort();
		System.out.println("CONSTANT_pool_count : " + constant_pool_count);
		System.out.println("Constant pool:");
		List<Map<String, String>> context = analyseConstantPool(di,
				constant_pool_count);
		System.out.println(simpleFormatOutput(context));

		System.out.println("access_flags : "
				+ classModifiers(di.readUnsignedShort()));
		System.out.println("this_class : "
				+ dig(context, di.readUnsignedShort()));
		System.out.println("super_class : "
				+ dig(context, di.readUnsignedShort()));

		int interfaces_count = di.readUnsignedShort();
		System.out.println("interfaces_count : " + interfaces_count);
		System.out.println(digInterfaces(di, context, interfaces_count));

		int fields_count = di.readUnsignedShort();
		System.out.println("fields_count : " + fields_count);
	}

	static String digInterfaces(DataInput di,
			List<Map<String, String>> context, int interfaces_count)
			throws IOException {
		StringBuilder sb = new StringBuilder();
		if (interfaces_count > 0) {
			for (int i = 0; i < interfaces_count; i++) {
				sb.append("interface[").append(i).append("] : ")
						.append(dig(context, di.readUnsignedShort()));
			}
		}
		return sb.toString();
	}

	
	private static final int ACC_PUBLIC = 0x0001;
	private static final int ACC_FINAL = 0x0010;
	private static final int ACC_SUPER = 0x0020;
	private static final int ACC_INTERFACE = 0x0200;
	private static final int ACC_ABSTRACT = 0x0400;
	private static final int ACC_SYNTHETIC = 0x1000;
	private static final int ACC_ANNOTATION = 0x2000;
	private static final int ACC_ENUM = 0x4000;
	
	//class's modifiers is different from method's&field's
	static String classModifiers(int mod) {
		StringBuilder sb = new StringBuilder();
		if ((mod & ACC_PUBLIC) != 0)
			sb.append("ACC_PUBLIC, ");
		if ((mod & ACC_FINAL) != 0)
			sb.append("ACC_FINAL, ");
		if ((mod & ACC_SUPER) != 0)
			sb.append("ACC_SUPER, ");
		if ((mod & ACC_INTERFACE) != 0)
			sb.append("ACC_INTERFACE, ");
		if ((mod & ACC_ABSTRACT) != 0)
			sb.append("ACC_ABSTRACT, ");
		if ((mod & ACC_SYNTHETIC) != 0)
			sb.append("ACC_SYNTHETIC, ");
		if ((mod & ACC_ANNOTATION) != 0)
			sb.append("ACC_ANNOTATION, ");
		if ((mod & ACC_ENUM) != 0)
			sb.append("ACC_ENUM, ");
		return sb.subSequence(0, sb.length() - 2).toString();
	}

	static List<Map<String, String>> analyseConstantPool(DataInput di,
			int constant_pool_count) throws IOException {
		List<Map<String, String>> context = new ArrayList<>(
				constant_pool_count + 1);
		// trick?
		context.add(null);
		// The constant_pool table is indexed from 1 to constant_pool_count - 1.
		for (int i = 1; i < constant_pool_count; i++) {
			int b = di.readUnsignedByte();// u1
			String tmp = null;
			switch (b) {
			case CONSTANT_Class:
				context.add(attr("Class", format(di.readUnsignedShort())));
				break;

			case CONSTANT_Fieldref:
				tmp = "Fieldref";
			case CONSTANT_Methodref:
				tmp = "Methodref";
			case CONSTANT_InterfaceMethodref:
				tmp = "InterfaceMethodref";
			case Ref:
				context.add(attr(tmp, format(di.readUnsignedShort()) + "."
						+ format(di.readUnsignedShort())));
				break;

			case CONSTANT_String:
				context.add(attr("String", format(di.readUnsignedShort())));
				break;

			case CONSTANT_Integer:
				context.add(attr("Integer", String.valueOf(di.readInt())));
				break;
			case CONSTANT_Float:
				context.add(attr("Float", String.valueOf(di.readFloat())));
				break;
			case CONSTANT_Long:
				context.add(attr("Long", String.valueOf(di.readLong())));
				break;
			case CONSTANT_Double:
				context.add(attr("Double", String.valueOf(di.readDouble())));
				break;

			case CONSTANT_NameAndType:
				context.add(attr("NameAndType", format(di.readUnsignedShort())
						+ ":" + format(di.readUnsignedShort())));
				break;
			case CONSTANT_Utf8:
				int length = di.readUnsignedShort();
				byte[] utf = new byte[length];
				di.readFully(utf);
				context.add(attr("Utf8", new String(utf, "utf-8")));
				break;

			// ignore...
			case CONSTANT_MethodHandle:
			case CONSTANT_MethodType:
			case CONSTANT_InvokeDynamic:
			default:
				System.out.println("i don't know...");
			}
		}
		return context;
	}

	// String.format? no...
	static String format(Object arg) {
		return "#" + arg;
	}

	static Map<String, String> attr(String type, String value) {
		Map<String, String> tmp = new HashMap<String, String>(4);
		tmp.put(TYPE, type);
		tmp.put(VALUE, value);
		return tmp;
	}

	static String simpleFormatOutput(List<Map<String, String>> context) {
		StringBuilder sb = new StringBuilder();
		int size = context.size();
		for (int i = 1; i < size; i++) {
			Map<String, String> map = context.get(i);
			String type = map.get(TYPE);
			// #1 = Class \t\t\t\t
			sb.append('#').append(i).append(" = ").append(type).append("\t\t");
			sb.append(dig(context, i));
		}
		return sb.toString();
	}

	static String dig(List<Map<String, String>> context, int index) {
		StringBuilder sb = new StringBuilder();
		Map<String, String> map = context.get(index);
		String type = map.get(TYPE);
		String value = map.get(VALUE);
		sb.append(escapeJava(value)).append("\t\t");
		// if symbolic
		if (!"Utf8".equals(type) && value.startsWith("#")) {
			sb.append("//");
			digDeep(sb, context, type, value);
		}
		sb.append("\n");
		return sb.toString();
	}

	static final Pattern INDEX = Pattern.compile("\\d+");

	static String digDeep(StringBuilder sb, List<Map<String, String>> context,
			String type, String value) {
		// eh...duplicated if, but i've no idea to solve it
		if (!"Utf8".equals(type) && value.startsWith("#")) {
			Matcher m = INDEX.matcher(value);
			if (m.find()) {
				String tmpStr = m.group();
				int tmpInt = Integer.parseInt(tmpStr);
				// randomAccess list, get may be not slower than create a temp
				// localvariable
				String innerType = context.get(tmpInt).get(TYPE);
				String innerValue = context.get(tmpInt).get(VALUE);
				digDeep(sb, context, innerType, innerValue);
				if (!m.hitEnd()) {
					sb.append(value.charAt(m.end()));
					m.find();
					tmpStr = m.group();
					tmpInt = Integer.parseInt(tmpStr);
					innerType = context.get(tmpInt).get(TYPE);
					innerValue = context.get(tmpInt).get(VALUE);
					digDeep(sb, context, innerType, innerValue);
				}
			}
		} else {
			sb.append(escapeJava(value));
		}
		return sb.toString();
	}

	public static final CharSequenceTranslator ESCAPE_JAVA = new LookupTranslator(
			new String[][] { { "\"", "\\\"" } }).with(
			new LookupTranslator(EntityArrays.JAVA_CTRL_CHARS_ESCAPE())).with(
			JavaUnicodeEscaper.outsideOf(32, 0x7f));

	public static final String escapeJava(String input) {
		return ESCAPE_JAVA.translate(input);
	}
}
