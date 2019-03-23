
public class OperatorOrOprand {
	private double Data;
	private boolean IsOperator;

	public OperatorOrOprand() {
	}

	public OperatorOrOprand(double data, boolean isOperator) {
		// TODO Auto-generated constructor stub
		Data = data;
		IsOperator = isOperator;
	}

	public void setData(double data, boolean isOperator) {
		Data = data;
		IsOperator = isOperator;
	}
	
	public boolean isOperator() {
		return IsOperator;
	}
	
	public double getData() {
		return Data;
	}
}
