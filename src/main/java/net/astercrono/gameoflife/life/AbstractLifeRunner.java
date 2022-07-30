package net.astercrono.gameoflife.life;

import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class AbstractLifeRunner implements LifeRunner {
	@Override
	public void start(int rateMs) {
		setRateMs(rateMs);
		runCycles();
	}

	@Override
	public void stop() {
		setActive(false);
	}

	@Override
	public void togglePause() {
		if (isPaused()) {
			setPaused(false);
		} else {
			setPaused(true);
		}
	}

	@Override
	public void addLifeCycleListener(LifeCycleListener listener) {
		getCycleListeners().add(listener);
	}

	@Override
	public void clearLifeCycleListeners() {
		getCycleListeners().clear();
	}

	@Override
	public void runCycles() {
		Life thisLife = getLife();

		setActive(true);
		setPaused(false);

		while (isActive()) {
			if (!isPaused()) {
				thisLife.nextCycle();
				triggerCycleEvent(thisLife);
				delayCycle();
			}
		}
	}

	@Override
	public void triggerCycleEvent(Life life) {
		List<LifeCycleListener> cycleListeners = getCycleListeners();
		if (cycleListeners != null) {
			for (LifeCycleListener l : cycleListeners) {
				l.lifeCycleUpdated(life);
			}
		}
	}

	@Override
	public void delayCycle() {
		if (getRateMs() < 0) {
			return;
		}

		try {
			TimeUnit.MILLISECONDS.sleep(getRateMs());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
