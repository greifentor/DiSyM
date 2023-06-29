package de.ollie.disym.service.impl;

import static de.ollie.disym.util.Check.ensure;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.text.StringTokenizer;
import org.apache.commons.text.matcher.StringMatcher;
import org.apache.commons.text.matcher.StringMatcherFactory;

import de.ollie.disym.service.StringToRuleConverter;
import de.ollie.disym.service.WordFactory;
import de.ollie.disym.service.model.rule.Rule;
import de.ollie.disym.service.model.rule.Value;
import de.ollie.disym.service.model.rule.Word;

@Named
public class StringToRuleConverterImpl implements StringToRuleConverter {

	@Inject
	private WordFactory wordFactory;

	@Override
	public Rule convert(String ruleContent) {
		ensure(ruleContent != null, "rule content to convert cannot be null!");
		List<Word> words = new ArrayList<Word>();
		StringTokenizer tokenizer = new StringTokenizer(ruleContent, " ");
		StringMatcher sm = StringMatcherFactory.INSTANCE.quoteMatcher();
		tokenizer.setQuoteMatcher(sm);
		while (tokenizer.hasNext()) {
			String token = tokenizer.nextToken();
			if (wordFactory.isCommand(token)) {
				words.add(wordFactory.createCommand(token));
			} else {
				words.add(Value.of(getValue(token)));
			}
		}
		return Rule.of(words);
	}

	Object getValue(String token) {
		if (token.toUpperCase().equals("FALSE") || token.toUpperCase().equals("TRUE")) {
			return Boolean.valueOf(token.toUpperCase());
		}
		return token;
	}

}
