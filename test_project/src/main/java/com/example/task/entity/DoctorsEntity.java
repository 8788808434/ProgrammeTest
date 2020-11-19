package com.example.task.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DoctorsEntity {

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
	private byte[] appointmentsPatientReports;
}
