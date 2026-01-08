package com.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Direction;
import com.model.LightColor;
import com.service.IntersectionService;

@RestController
@RequestMapping("/api/traffic")
public class TrafficLightController {

	private final IntersectionService service;

	public TrafficLightController(IntersectionService service) {
		this.service = service;
	}

	@PostMapping("/green/{direction}")
	public void setGreen(@PathVariable Direction direction) {
		service.setGreen(direction);
	}

	@PostMapping("/pause")
	public void pause() {
		service.pause();
	}

	@PostMapping("/resume")
	public void resume() {
		service.resume();
	}

	public Map<Direction, LightColor> state() {
		return service.getCurrentState();
	}

	public List<String> history() {
		return service.getHistory();
	}
}
