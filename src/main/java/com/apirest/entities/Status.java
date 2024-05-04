package com.apirest.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ESTADOS")
public class Status {

	@Id
	@Column(name = "ESTADO_ID", unique = true, nullable = false, length = 5)
	private String statusID;

	@Column(name = "ESTADO", unique = true, nullable = false, length = 50)
	private String statusName;
}
