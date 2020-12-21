package group43.exceptions;

public class InvalidQuestionnaireException extends Exception{
	private static final long serialVersionUID = 1L;

	public InvalidQuestionnaireException(String message) {
		super(message);
	}
}
