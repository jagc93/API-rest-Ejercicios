package com.apirest.services.status;

import static com.apirest.constants.MessagesConstant.ALREADY_EXIST;
import static com.apirest.exceptions.GenericException.getNotFound;
import static com.apirest.exceptions.GenericException.requireNotNull;
import static com.apirest.exceptions.GenericException.validateFieldLength;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apirest.entities.Status;
import com.apirest.models.status.StatusDto;
import com.apirest.models.status.StatusMapper;
import com.apirest.models.status.StatusRequest;
import com.apirest.repositories.StatusRepository;

@Service
public class StatusServiceImpl implements StatusService {

	private static final String TABLE_NAME = "status";
	private static final String FIELD_STATUS_ID = "statusID";
	private static final String FIELD_STATUS_NAME = "statusName";

	private final StatusRepository repository;
	private final StatusMapper mapper;

	public StatusServiceImpl(
			StatusRepository _repository,
			StatusMapper _mapper
	) {
		this.repository = _repository;
		this.mapper = _mapper;
	}

	@Transactional
	public StatusDto create(StatusRequest request) {
		validateRequiredFields(request);
		validateFieldsLength(request);
		validateFieldsUnique(request);
		return Optional.of(request)
				.map(mapper::toEntity)
				.map(repository::save)
				.map(mapper::toDto)
				.get();
	}

	@Transactional
	public StatusDto update(String statusId, StatusRequest request) {
		request.setStatusID(null);
		validateFieldsLength(request);
		validateFieldsUnique(request);
		Status status = repository.findById(statusId).orElseThrow(() -> getNotFound(statusId, "Status"));

		mapper.updateFromRequest(request, status);

		return Optional.of(status)
				.map(repository::save)
				.map(mapper::toDto)
				.get();
	}

	@Transactional
	public void delete(String statusId) {
		Status status = repository.findById(statusId).orElseThrow(() -> getNotFound(statusId, "Status"));
		repository.delete(status);
	}

	@Transactional(readOnly = true)
	public List<StatusDto> index() {
		return mapper.toListDto(repository.findAll());
	}

	@Transactional(readOnly = true)
	public StatusDto show(String statusId) {
		return repository.findById(statusId)
				.map(mapper::toDto)
				.orElseThrow(() -> getNotFound(statusId, "Status"));
	}

	private void validateRequiredFields(StatusRequest request) {
		requireNotNull(request.getStatusID(), FIELD_STATUS_ID);
		requireNotNull(request.getStatusName(), FIELD_STATUS_NAME);
	}

	private void validateFieldsLength(StatusRequest request) {
		validateFieldLength(request.getStatusID(), 5, FIELD_STATUS_ID);
		validateFieldLength(request.getStatusName(), 50, FIELD_STATUS_NAME);
	}

	private void validateFieldsUnique(StatusRequest request) {
		boolean existBy = repository.existsByStatusIDIgnoreCase(request.getStatusID());
		String fileName = FIELD_STATUS_ID;
		String value = request.getStatusID();

		if (!existBy && repository.existsByStatusNameIgnoreCase(request.getStatusName())) {
			existBy = true;
			fileName = FIELD_STATUS_NAME;
			value = request.getStatusName();
		}

		if (existBy) {
			throw new IllegalArgumentException(String.format(ALREADY_EXIST, TABLE_NAME, fileName, value));
		}
	}
}
