package util;

import java.util.HashMap;
import java.util.Map;

public class Timer {
	private static Timer instance;
	private static Map<String, Long> times = new HashMap<>();
	private long lastTime;

	private Timer() {
		lastTime = System.currentTimeMillis();
	}

	public static Timer getInstance() {
		if (instance == null) {
			instance = new Timer();
		}
		return instance;
	}

	public void getTime(String key) {
		long currentTime = System.currentTimeMillis();
		if (times.containsKey(key)) {
			long elapsedTime = currentTime - times.get(key);
			System.out.println("Elapsed time since last call for " + key + ": " + elapsedTime + " ms");
			times.remove(key);
		} else {

			times.put(key, currentTime);
		}
	}
}
