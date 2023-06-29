package de.ollie.disym.cli;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationArguments;

import de.ollie.disym.cli.model.Argument;
import de.ollie.disym.cli.model.CommandLineOption;
import de.ollie.disym.cli.model.CommandLineOption.Type;
import de.ollie.disym.reader.yaml.YAMLReader;
import de.ollie.disym.service.RuleEvaluator;
import de.ollie.disym.service.StringToRuleConverter;
import de.ollie.disym.service.model.rule.Rule;

@Named
public class CLIRunner {

	private static final Logger LOG = LogManager.getLogger(CLIRunner.class);

	@Inject
	private CommandLineOptionChecker commandLineOptionChecker;
	@Inject
	private RuleEvaluator ruleEvaluator;
	@Inject
	private StringToRuleConverter stringToRuleConverter;
	@Inject
	private YAMLReader yamlReader;

	public void run(ApplicationArguments args) {
		List<Argument> arguments =
				commandLineOptionChecker
						.check(
								args,
								CommandLineOption
										.of("rule", "A rule to check configuration settings for.", false, Type.STRING),
								CommandLineOption
										.of(
												"yamlFile",
												"Name of a YAML file with application properties.",
												false,
												Type.STRING));
		List<Rule> rules = new ArrayList<>();
		for (Argument argument : arguments) {
			if (argument.getName().equals("rule")) {
				try {
					for (Object o : argument.getValues()) {
						rules.add(stringToRuleConverter.convert(o.toString()));
					}
				} catch (Exception e) {
					LOG.warn("problems while converting rules: " + argument.getName() + " -> " + e.getMessage());
				}
			}
		}
		for (Argument argument : arguments) {
			for (Object value : argument.getValues()) {
				try {
					if (argument.getName().equals("yamlFile")) {
						yamlReader
								.readYAMLFile((String) value)
								.forEach(
										cs -> rules
												.forEach(
														rule -> System.out
																.println(
																		cs.getIdentifier() + " -> "
																				+ ruleEvaluator.evaluate(rule, cs))));
					}
				} catch (Exception e) {
					LOG.warn("problems while processing: " + argument.getName() + " -> " + e.getMessage());
				}
			}
		}
	}

}
