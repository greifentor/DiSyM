package de.ollie.disym.service;

import de.ollie.disym.service.model.rule.Rule;

public interface StringToRuleConverter {

	Rule convert(String ruleContent);

}
