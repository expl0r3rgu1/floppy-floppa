package main.state_changers;

import main.utilities.CommonMethods;
import main.utilities.Constants;
import main.utilities.Position;
import main.utilities.Skin;

public class StateChangerFactoryImpl implements StateChangerFactory {

	@Override
	public Malus malusFactory(Position position) {
		int randomMalusIndex = CommonMethods.getRandomNumber(0, Constants.MALUS.size());
		Malus randomMalus = Constants.MALUS.get(randomMalusIndex);
		randomMalus.setPosition(position);
		return randomMalus;
	}

	@Override
	public Booster boosterFactory(Position position) {
		int randomBoosterIndex = CommonMethods.getRandomNumber(0, Constants.BOOSTERS.size());
		Booster randomBooster = Constants.BOOSTERS.get(randomBoosterIndex);
		randomBooster.setPosition(position);
		return randomBooster;
	}

}
