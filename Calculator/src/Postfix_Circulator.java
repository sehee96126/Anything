import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class Postfix_Circulator {

	public static double simpleCalculator(double x, double y, char oper) {
		double result = 0;

		if (oper == '+')
			result = x + y;
		if (oper == '-')
			result = x - y;
		if (oper == '*')
			result = x * y;
		if (oper == '/')
			result = x / y;

		return result;
	}

	public static ArrayList<OperatorOrOprand> StringtoArray(String str) {
		ArrayList<OperatorOrOprand> result = new ArrayList<>();
		String sbuf = "";
		str.replace(" ", "");
		for (int i = 0; i < str.length(); i++) {
			char cbuf = str.charAt(i);
			if (cbuf >= '0' && cbuf <= '9') {
				sbuf += cbuf;
			} else if (cbuf == '+' || cbuf == '-' || cbuf == '*' || cbuf == '/' || cbuf == '(' || cbuf == ')') {
				try {
					if (sbuf != "") {
						result.add(new OperatorOrOprand(Double.parseDouble(sbuf.replace(" ", "")), false));
						sbuf = "";
					}
					result.add(new OperatorOrOprand((int)cbuf,true));
				} catch (NumberFormatException e) {
					System.out.println("숫자에러");
				}
			}
		}
		result.add(new OperatorOrOprand(Integer.parseInt(sbuf.replace(" ", "")), false));

		return result;
	}

	public static ArrayList<OperatorOrOprand> InfixtoPostfix(String exp) {

		ArrayList<OperatorOrOprand> expBuffer = StringtoArray(exp);

		Stack<ArrayList<OperatorOrOprand>> arrayBuf = new Stack<>();
		Stack<Stack<OperatorOrOprand>> stackBuf = new Stack<>();

		Stack<OperatorOrOprand> stack = new Stack<>();
		ArrayList<OperatorOrOprand> array = new ArrayList<>();

		Iterator<OperatorOrOprand> itBuffer = expBuffer.iterator();
		while (itBuffer.hasNext()) {
			OperatorOrOprand obuf = itBuffer.next();
			if (obuf.isOperator() == false) {
				array.add(obuf);
			} else if (obuf.getData() == '+' || obuf.getData() == '-') {
				while (stack.empty() == false) {
					array.add(stack.pop());
				}
				stack.push(obuf);
			} else if (obuf.getData() == '*' || obuf.getData() == '/') {
				stack.push(obuf);
			} else if (obuf.getData() == '(') {
				arrayBuf.push(array);
				stackBuf.push(stack);
				array = new ArrayList<>();
				stack = new Stack<>();
			} else if (obuf.getData() == ')') {
				while (stack.empty() == false) {
					array.add(stack.pop());
				}
				ArrayList<OperatorOrOprand> buf = array;
				array = arrayBuf.pop();
				stack = stackBuf.pop();

				Iterator<OperatorOrOprand> itBuf = buf.iterator();
				while (itBuf.hasNext()) {
					array.add(itBuf.next());
				}
			}
		}

		while (stack.empty() == false) {
			array.add(stack.pop());
		}

		return array;
	}

	public static double PostfixCalculator(String exp) {
		double result = 0;
		OperatorOrOprand []x = new OperatorOrOprand[2];
		int oper;

		ArrayList<OperatorOrOprand> expBuffer = InfixtoPostfix(exp);

		for (int i = 0; i < expBuffer.size(); i++) {
			OperatorOrOprand obuf = expBuffer.get(i);
			if (obuf.isOperator()) {
				oper = (int)obuf.getData();
				if (i - 2 < 0) {
					System.out.println("err");
					break;
				}
				else {
					x[0] = expBuffer.get(i - 2);
					x[1] = expBuffer.get(i - 1);
				}

				result = simpleCalculator(x[0].getData(), x[1].getData(), (char)oper);

				expBuffer.remove(i);
				expBuffer.remove(i - 1);
				expBuffer.remove(i - 2);
				expBuffer.add(i - 2, new OperatorOrOprand(result, false));
				i = 0;
			}
		}

		return result;
	}
}