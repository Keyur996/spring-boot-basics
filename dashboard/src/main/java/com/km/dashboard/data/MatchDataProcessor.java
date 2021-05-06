package com.km.dashboard.data;

import org.springframework.batch.item.ItemProcessor;

import com.km.dashboard.model.Match;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

	@Override
	public Match process(MatchInput item) throws Exception {
		return null;
	}

}
