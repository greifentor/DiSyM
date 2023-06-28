package de.ollie.disym.service;

import java.util.Stack;

import de.ollie.disym.service.model.ConfigurationSetting;
import de.ollie.disym.service.model.rule.Rule;

public interface RuleEvaluator {

	Stack<Object> evaluate(Rule rule, ConfigurationSetting settingToEvaluate);

}
