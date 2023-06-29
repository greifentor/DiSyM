package de.ollie.disym.cli;

import static de.ollie.disym.util.Check.ensure;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.springframework.boot.ApplicationArguments;

import de.ollie.disym.cli.model.Argument;
import de.ollie.disym.cli.model.CommandLineOption;
import de.ollie.disym.cli.model.CommandLineOption.Type;

@Named
public class CommandLineOptionChecker {

	public List<Argument> check(ApplicationArguments args, CommandLineOption... options) {
		ensure(args != null, "application arguments cannot be null!");
		List<Argument> arguments = new ArrayList<>();
		for (CommandLineOption option : options) {
			if (option.isRequired()) {
				ensure(args.containsOption(option.getName()), "required option missed: " + option.getName());
			}
			if (args.containsOption(option.getName())) {
				List<String> values = args.getOptionValues(option.getName());
				arguments.add(Argument.of(option.getName(), convertValues(option.getName(), option.getType(), values)));
			}
		}
		return arguments;
	}

	private List<?> convertValues(String name, Type type, List<String> values) {
		List<Object> l = new ArrayList<>(values.size());
		for (String s : values) {
			try {
				if (type == Type.BOOLEAN) {
					l.add(Boolean.valueOf(s));
				} else if (type == Type.LONG) {
					l.add(Long.valueOf(s));
				} else {
					l.add(s);
				}
			} catch (Exception e) {
				throw new IllegalArgumentException(
						"argument value of '" + name + "' does not match with type: " + type);
			}
		}
		return l;
	}

}
