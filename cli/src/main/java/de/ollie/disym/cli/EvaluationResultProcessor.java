package de.ollie.disym.cli;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.inject.Named;

import de.ollie.disym.cli.model.EvaluationResult;

@Named
public class EvaluationResultProcessor {

	private List<EvaluationResult> evaluationResults = new ArrayList<>();

	public void add(EvaluationResult evaluationResult) {
		evaluationResults.add(evaluationResult);
	}

	public void process(Consumer<EvaluationResult> evaluationResultProvider) {
		evaluationResults.stream().filter(EvaluationResult::isResult).forEach(evaluationResultProvider::accept);
	}

}
