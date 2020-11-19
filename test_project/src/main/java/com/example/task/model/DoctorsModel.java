package com.example.task.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="DoctorsAppointment")
public class DoctorsModel {

	@Id
	@Column(name="appointmentsId")
	private Integer appointmentsId;
	
	@Column(name="appointmentsName")
	private String appointmentsName;
	
	@Column(name="appointmentsAge")
	private Integer appointmentsAge;
	
	@Column(name="appointmentsDate")
	private Date appointmentsDate;
	
	@Column(name="appointmentsBloodGroup")
	private String appointmentsBloodGroup;
	
	@Column(name="appointmentsAddress")
	private String appointmentsAddress;
	
	@Column(name="appointmentsMobile")
	private String appointmentsMobile;
	
	@Column(name="appointmentsEmail")
	private String appointmentsEmail;
	
	@Column(name="appointmentsDateOfAppointment")
	private String appointmentsDateOfAppointment;
	
	@Lob
	@Column(name="appointmentsPatientReports",columnDefinition="MEDIUMTEXT")
	private String appointmentsPatientReports;
	
	
	
	
}
