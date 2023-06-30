package de.ollie.disym.cli;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import de.ollie.disym.cli.model.EvaluationResult;

@Named
public class EvaluationResultProcessor {

	private List<EvaluationResult> evaluationResults = new ArrayList<>();

	public void add(EvaluationResult evaluationResult) {
		evaluationResults.add(evaluationResult);
	}

	public void process() {
		System.out.println("\n");
		evaluationResults.stream().filter(EvaluationResult::isResult).forEach(System.out::println);
		System.out.println("\n");
	}

}
