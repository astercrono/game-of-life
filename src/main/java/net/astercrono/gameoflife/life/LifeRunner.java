package net.astercrono.gameoflife.life;

import java.util.List;

public interface LifeRunner {
	void start(int rateMs);
	void stop();
	void togglePause();
	void addLifeCycleListener(LifeCycleListener listener);
	void clearLifeCycleListeners();
	void runCycles();
	void triggerCycleEvent(Life life);    
	void setActive(boolean active);
	boolean isActive();
	void setPaused(boolean paused);
	boolean isPaused();
	void delayCycle();
	Life getLife();
	void setRateMs(int rateMs);
	int getRateMs();
	List<LifeCycleListener> getCycleListeners();
}
