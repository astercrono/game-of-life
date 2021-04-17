package net.astercrono.gameoflife.life;

import java.util.ArrayList;
import java.util.List;

public class SimpleLifeRunner extends AbstractLifeRunner {
	private List<LifeCycleListener> cycleListeners = new ArrayList<>();
	private Life life;

	protected int rateMs = 1;
	protected boolean active = true;
	protected boolean paused = true;

	public SimpleLifeRunner(Life life) {
		this.life = life;
	}

	@Override
	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public boolean isActive() {
		return active;
	}

	@Override
	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	@Override
	public boolean isPaused() {
		return paused;
	}

	@Override
	public Life getLife() {
		return life;
	}

	@Override
	public void setRateMs(int rateMs) {
		this.rateMs = rateMs;
	}

	@Override
	public int getRateMs() {
		return rateMs;
	}

	@Override
	public List<LifeCycleListener> getCycleListeners() {
		return cycleListeners;
	}
}
