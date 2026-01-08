package com.service;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.exeption.InvalidTrafficstateException;
import com.model.Direction;
import com.model.LightColor;
import com.model.TrafficLight;

@Service
public class IntersectionService {
	private final Map<Direction, TrafficLight> lights = new EnumMap<>(Direction.class);
	private final List<String> history = new ArrayList<>();
	private boolean paused = false;

	public IntersectionService() {
		for (Direction d : Direction.values()) {
			lights.put(d, new TrafficLight());
		}
		history.add("SYSTEM_INITIALIZED");
	}

	public synchronized void setGreen(Direction direction) {
		if (paused) {
			throw new InvalidTrafficstateException("System is paused");
		}
		validateConflicts(direction);
		lights.values().forEach(l -> l.changeTo(LightColor.RED));
		lights.get(direction).changeTo(LightColor.GREEN);
		history.add(direction + "set to GREEN");
	}

	private void validateConflicts(Direction direction) {
		if (direction == Direction.NORTH || direction == Direction.SOUTH) {
			if (isGreen(Direction.EAST) || isGreen(Direction.WEST)) {
				throw new InvalidTrafficstateException("conflict with EAST/WEST");

			}
		}
		if (direction == Direction.EAST || direction == Direction.WEST) {
			if (isGreen(Direction.NORTH) || isGreen(Direction.SOUTH)) {
				throw new InvalidTrafficstateException("conflict with NORTH/SOUTH");

			}
		}
	}

	private boolean isGreen(Direction d) {
		return lights.get(d).getColor() == LightColor.GREEN;
	}

	public synchronized void pause() {
		paused = true;
		history.add("SYSTEM_PAUSED");
	}

	public synchronized void resume() {
		paused = false;
		history.add("SYSTEM_RESUMED");
	}

	public Map<Direction, LightColor> getCurrentState() {
		return lights.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getColor()));
	}

	public List<String> getHistory() {
		return history;
	}

}
