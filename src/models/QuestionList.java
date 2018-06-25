package models;

public class QuestionList {
	
	private TypeQuestion typeQuestion;
	private String message;
	private String optionA;
	private String optionB;
	private String optionC;
	private String optionD;
	private String answer;
	
	public QuestionList(TypeQuestion typeQuestion, String message, String optionA, String optionB, String optionC,
			String optionD, String answer) {
		this.typeQuestion = typeQuestion;
		this.message = message;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.optionD = optionD;
		this.answer = answer;
	}
	
	public String[] getQuestion() {
		return new String[] {message,optionA,optionB,optionC,optionD};
	}
	
	public int[] removeWrongAnswers() {
		int[] values = new int[2];
		if(answer.equals(optionA)) {
			values[0] = 1;
		}else if(answer.equals(optionB)) {
			values[0] = 2;
		}else if(answer.equals(optionC)) {
			values[0] = 3;
		} else {
			values[0] = 4;
		}
		values[1] = randomvalue(values[0]);
		return values;
	}
	
	public int randomvalue(int number) {
		int value = (int)(Math.random() * 4);
		if(value == number) {
			return randomvalue(number);
		}else {
			return value;
		}
	}
	
	public String[] getOptions() {
		return new String[] {optionA,optionB,optionC,optionD};
	}
	
	public String print() {
		return optionA + " " + optionB + " " +optionC + " " + optionD;
	}

	public TypeQuestion getTypeQuestion() {
		return typeQuestion;
	}

	public String getMessage() {
		return message;
	}

	public String getOptionA() {
		return optionA;
	}

	public String getOptionB() {
		return optionB;
	}

	public String getOptionC() {
		return optionC;
	}

	public String getOptionD() {
		return optionD;
	}

	public String getAnswer() {
		return answer;
	}

	@Override
	public String toString() {
		return "QuestionList [typeQuestion=" + typeQuestion + ", message=" + message + ", optionA=" + optionA
				+ ", optionB=" + optionB + ", optionC=" + optionC + ", optionD=" + optionD + ", answer=" + answer + "]";
	}
}
