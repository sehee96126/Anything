import java.util.ArrayList;
import java.util.Iterator;

public class Main {
	public static void main(String[] args) {
		// post.InfixtoPostfix("2+3*(5+4*2)–3+6");
		// ArrayList<Integer> result = post.InfixtoPostfix("2+3*(5+4*2)-3+6");
		ArrayList<OperatorOrOprand> result = Postfix_Circulator.StringtoArray("12+34+56/(78+90*15+(23+34))+12+34*12-1200");

		// ArrayList<Integer> array = post.StringtoArray("25+37*(55+45*2)–3+6");
		// 2 3 5 4 2 * + * + 3 – 6 +
		Iterator<OperatorOrOprand> it = result.iterator();
		while (it.hasNext()) {
			OperatorOrOprand obuf = it.next();
			if (obuf.isOperator())
				System.out.print(" " + (char) obuf.getData());
			else
				System.out.print(" " + obuf.getData());

		}
		System.out.println();

		result = Postfix_Circulator.InfixtoPostfix("12+34+56/(78+90*15+(23+34))+12+34*12-1200");

		it = result.iterator();
		while (it.hasNext()) {
			OperatorOrOprand obuf = it.next();
			if (obuf.isOperator())
				System.out.print(" " + (char) obuf.getData());
			else
				System.out.print(" " + obuf.getData());

		}

		System.out.println();

		double res = Postfix_Circulator.PostfixCalculator("12+34+56/(78+90*15+(23+34))+12+34*12-1200");
		System.out.println("결과 : " + res);
	}

}