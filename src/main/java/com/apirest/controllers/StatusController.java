package com.apirest.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.models.status.StatusDto;
import com.apirest.models.status.StatusRequest;
import com.apirest.services.status.StatusService;

@RestController
@RequestMapping("status")
public class StatusController {

	private final StatusService service;

	public StatusController(StatusService _service) {
		this.service = _service;
	}

	@PostMapping
	public StatusDto create(@RequestBody StatusRequest request) {
		return service.create(request);
	}

	@PatchMapping("/{id}")
	public StatusDto update(@PathVariable String id, @RequestBody StatusRequest request) {
		return service.update(id, request);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		service.delete(id);
	}

	@GetMapping
	public List<StatusDto> index() {
		return service.index();
	}

	@GetMapping("/{id}")
	public StatusDto show(@PathVariable String id) {
		return service.show(id);
	}
	
	
}


