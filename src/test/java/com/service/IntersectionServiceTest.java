package com.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import com.exeption.InvalidTrafficstateException;
import com.model.Direction;
import com.model.LightColor;

class IntersectionServiceTest {

	private IntersectionService service;

	void setup() {
		service = new IntersectionService();
	}

	@Test
	void shouldAlllowNorthGreen() {
		service.setGreen(Direction.NORTH);
		assertEquals(LightColor.GREEN, service.getCurrentState().get(Direction.NORTH));

	}

	@Test
	void shouldNotAllowEastWhenNorthIsGreen() {
		service.setGreen(Direction.NORTH);
		assertThrows(InvalidTrafficstateException.class, () -> service.setGreen(Direction.EAST));
	}

	@Test
	void shouldPauseSystem() {
		service.pause();
		assertThrows(InvalidTrafficstateException.class, () -> service.setGreen(Direction.SOUTH));
	}

}
