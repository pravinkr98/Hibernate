package com.ps.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CARMODEL_LIST")
@Setter
@Getter
@RequiredArgsConstructor
public class CarModel implements Serializable {

	@Id
	@Type(type = "int")
	@GeneratedValue(generator = "gen1", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "gen1",sequenceName = "car_model_seq", initialValue = 101, allocationSize = 1)
	private Integer modelId;
	@NonNull
	@Type(type = "string")
	@Column(length = 15)
	private String modelName;
	@NonNull
	@Type(type = "string")
	@Column(length = 15)
	private String type;
	
	public CarModel() {
		System.out.println("CarModel: 0-param constructor");
	}

	@Override
	public String toString() {
		return "CarModel [modelId=" + modelId + ", modelName=" + modelName + ", type=" + type + "]";
	}
	
}
