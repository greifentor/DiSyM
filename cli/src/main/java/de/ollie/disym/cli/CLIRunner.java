package de.ollie.disym.cli;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationArguments;

import de.ollie.disym.cli.exception.UnknownCommandException;
import de.ollie.disym.cli.model.Argument;
import de.ollie.disym.cli.model.CommandLineCommand;
import de.ollie.disym.cli.model.CommandLineOption;
import de.ollie.disym.cli.model.CommandLineOption.Type;
import de.ollie.disym.cli.model.EvaluationResult;
import de.ollie.disym.reader.yaml.YAMLReader;
import de.ollie.disym.service.RuleEvaluator;
import de.ollie.disym.service.StringToRuleConverter;
import de.ollie.disym.service.model.rule.Rule;

@Named
public class CLIRunner {

	private static final Logger LOG = LogManager.getLogger(CLIRunner.class);

	@Inject
	private CommandLineCommandChecker commandLineCommandChecker;
	@Inject
	private CommandLineOptionChecker commandLineOptionChecker;
	@Inject
	private EvaluationResultProcessor evaluationResultProcessor;
	@Inject
	private HelpPrinter helpPrinter;
	@Inject
	private RuleEvaluator ruleEvaluator;
	@Inject
	private StringToRuleConverter stringToRuleConverter;
	@Inject
	private YAMLReader yamlReader;

	static final CommandLineOption[] OPTIONS = {
			CommandLineOption.of("rule", "A rule to check configuration settings for.", false, Type.STRING),
			CommandLineOption.of("yamlFile", "Name of a YAML file with application properties.", false, Type.STRING) };

	public void run(ApplicationArguments args) {
		PrintStream out = System.out;
		List<CommandLineCommand> commands = getCommandLineCommands(args, out);
		if (commands.contains(CommandLineCommand.HELP) || commands.isEmpty()) {
			helpPrinter.print(out, OPTIONS);
		} else {
			List<Argument> arguments = commandLineOptionChecker.check(args, OPTIONS);
			List<Rule> rules = new ArrayList<>();
			for (Argument argument : arguments) {
				if (argument.getName().equals("rule")) {
					for (Object o : argument.getValues()) {
						rules.add(stringToRuleConverter.convert(o.toString()));
					}
				}
			}
			for (Argument argument : arguments) {
				for (Object value : argument.getValues()) {
					try {
						if (argument.getName().equals("yamlFile")) {
							yamlReader.readYAMLFile((String) value)
									.forEach(cs -> rules.forEach(rule -> evaluationResultProcessor
											.add(EvaluationResult.of(cs.getIdentifier(),
													(Boolean) ruleEvaluator.evaluate(rule, cs).pop()))));
						}
					} catch (Exception e) {
						LOG.warn("problems while processing: " + argument.getName() + " -> " + e.getMessage());
					}
				}
			}
			if (commands.contains(CommandLineCommand.SHOW)) {
				evaluationResultProcessor.process();
			}
		}
	}

	private List<CommandLineCommand> getCommandLineCommands(ApplicationArguments args, PrintStream out) {
		try {
			return commandLineCommandChecker.check(args);
		} catch (UnknownCommandException uce) {
			out.println("\n'" + uce.getCommand() + "' is not a valid command!\n");
			return List.of();
		}
	}

}
