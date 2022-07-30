package net.astercrono.gameoflife.life;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadedLifeRunner extends AbstractLifeRunner {
	private static long THREAD_COUNT = 1;

	private volatile Life life;

	private List<LifeCycleListener> cycleListeners = Collections.synchronizedList(new ArrayList<LifeCycleListener>());
	protected AtomicInteger rateMs = new AtomicInteger(1);
	protected AtomicBoolean active = new AtomicBoolean(false);
	protected AtomicBoolean paused = new AtomicBoolean(true);

	private Thread thread = new Thread(new Runnable() {
		@Override
		public void run() {
			runCycles();
			life = null;
		}
	}, "LifeThread-" + THREAD_COUNT++);

	public ThreadedLifeRunner(Life life) {
		this.life = life;
	}
	
	@Override
	public void start(int rateMs) {
		setRateMs(rateMs);
		thread.start();
	}

	@Override
	public void stop() {
		setActive(false);
	}
	
	@Override
	public synchronized void setActive(boolean active) {
		this.active.set(active);
	}

	@Override
	public boolean isActive() {
		return active.get();
	}

	@Override
	public void setPaused(boolean paused) {
		this.paused.set(paused);
	}

	@Override
	public boolean isPaused() {
		return paused.get();
	}

	@Override
	public synchronized Life getLife() {
		return life;
	}

	@Override
	public void setRateMs(int rateMs) {
		this.rateMs.set(rateMs);;
	}

	@Override
	public int getRateMs() {
		return rateMs.get();
	}

	@Override
	public List<LifeCycleListener> getCycleListeners() {
		return cycleListeners;
	}
}
