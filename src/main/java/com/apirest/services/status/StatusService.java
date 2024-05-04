package com.apirest.services.status;

import java.util.List;

import com.apirest.models.status.StatusDto;
import com.apirest.models.status.StatusRequest;

public interface StatusService {
	public StatusDto create(StatusRequest request);
	public StatusDto update(String statusId, StatusRequest request);
	public void delete(String statusId);
	public List<StatusDto> index();
	public StatusDto show(String statusId);
}
