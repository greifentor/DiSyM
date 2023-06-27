package de.ollie.disym.service.model.rule;

public class MissingArgumentRuleEvaluationException extends RuleEvaluationException {

	public static final String MESSAGE_MISSING_ARGUMENT = "missing argument {0} for {1} evaluation";

	public MissingArgumentRuleEvaluationException(int argumentNumber, Command command) {
		super(MESSAGE_MISSING_ARGUMENT.replace("{0}", "" + argumentNumber).replace("{1}", command.getToken()));
	}

}
