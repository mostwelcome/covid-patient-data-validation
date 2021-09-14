package com.psl.validate.CovidValidate.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="validateddata")
public class ValidatedData {
	
	

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "serialno")
	private int serialNo;

	@Column(name = "district")
	private String district;

	@Column(name = "name")
	private String name;

	@Column(name = "age")
	private int age;

	@Column(name = "sex")
	private String sex;

	@Column(name = "address")
	private String address;

	@Column(name = "ward")
	private String ward;

	@Column(name = "zone")
	private String zone;

	@Column(name = "contactNo")
	private Long contactNo;

	@Column(name = "traceable")
	private String traceable;

	@Column(name = "dateOfReceiptOfInformation")
	private Date dateOfReceiptOfInformation;

	@Column(name = "observationStartedFrom")
	private Date observationStartedFrom;

	@Column(name = "nameOfHospital")
	private String nameOfHospital;

	@Column(name = "sampleCollected")
	private String sampleCollected;

	@Column(name = "dateOfSampleCollection")
	private Date dateOfSampleCollection;

	@Column(name = "resultOfSampel")
	private String resultOfSampel;

	@Column(name = "lab")
	private String lab;

	@Column(name = "currentStatus")
	private String currentStatus;

	@Column(name = "hospitalizationStatus")
	private String hospitalizationStatus;

	@Column(name = "surveillanceStatus")
	private String surveillanceStatus;

	@Column(name = "ifMigrated")
	private String ifMigrated;

	@Column(name = "otherStateName")
	private String otherStateName;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "firstSampleCollected")
	private Date firstSampleCollected;

	@Column(name = "firstSampleResult")
	private Date firstSampleResult;

	@Column(name = "resultFirst")
	private String resultFirst;

	@Column(name = "NIVnoFirst")
	private int NIVnoFirst;

	@Column(name = "secondSampleCollected")
	private Date secondSampleCollected;

	@Column(name = "secondSampleResult")
	private Date secondSampleResult;

	@Column(name = "resultSecond")
	private String resultSecond;

	@Column(name = "NIVnoSecond")
	private int NIVnoSecond;

	@Column(name = "remark")
	private String remark;

	@Column(name = "countryOfVisit")
	private String countryOfVisit;

	@Column(name = "leavingAffectedCountry")
	private Date leavingAffectedCountry;

	@Column(name = "arrivalFromAffectedCountry")
	private Date arrivalFromAffectedCountry;

	@Column(name = "sourceOFInformation")
	private String sourceOFInformation;
	
	
	
	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public Long getContactNo() {
		return contactNo;
	}

	public void setContactNo(Long contactNo) {
		this.contactNo = contactNo;
	}

	public String getTraceable() {
		return traceable;
	}

	public void setTraceable(String traceable) {
		this.traceable = traceable;
	}

	public Date getDateOfReceiptOfInformation() {
		return dateOfReceiptOfInformation;
	}

	public void setDateOfReceiptOfInformation(Date dateOfReceiptOfInformation) {
		this.dateOfReceiptOfInformation = dateOfReceiptOfInformation;
	}

	public Date getObservationStartedFrom() {
		return observationStartedFrom;
	}

	public void setObservationStartedFrom(Date observationStartedFrom) {
		this.observationStartedFrom = observationStartedFrom;
	}

	public String getNameOfHospital() {
		return nameOfHospital;
	}

	public void setNameOfHospital(String nameOfHospital) {
		this.nameOfHospital = nameOfHospital;
	}

	public String getSampleCollected() {
		return sampleCollected;
	}

	public void setSampleCollected(String sampleCollected) {
		this.sampleCollected = sampleCollected;
	}

	public Date getDateOfSampleCollection() {
		return dateOfSampleCollection;
	}

	public void setDateOfSampleCollection(Date dateOfSampleCollection) {
		this.dateOfSampleCollection = dateOfSampleCollection;
	}

	public String getResultOfSampel() {
		return resultOfSampel;
	}

	public void setResultOfSampel(String resultOfSampel) {
		this.resultOfSampel = resultOfSampel;
	}

	public String getLab() {
		return lab;
	}

	public void setLab(String lab) {
		this.lab = lab;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getHospitalizationStatus() {
		return hospitalizationStatus;
	}

	public void setHospitalizationStatus(String hospitalizationStatus) {
		this.hospitalizationStatus = hospitalizationStatus;
	}

	public String getSurveillanceStatus() {
		return surveillanceStatus;
	}

	public void setSurveillanceStatus(String surveillanceStatus) {
		this.surveillanceStatus = surveillanceStatus;
	}

	public String getIfMigrated() {
		return ifMigrated;
	}

	public void setIfMigrated(String ifMigrated) {
		this.ifMigrated = ifMigrated;
	}

	public String getOtherStateName() {
		return otherStateName;
	}

	public void setOtherStateName(String otherStateName) {
		this.otherStateName = otherStateName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getFirstSampleCollected() {
		return firstSampleCollected;
	}

	public void setFirstSampleCollected(Date firstSampleCollected) {
		this.firstSampleCollected = firstSampleCollected;
	}

	public Date getFirstSampleResult() {
		return firstSampleResult;
	}

	public void setFirstSampleResult(Date firstSampleResult) {
		this.firstSampleResult = firstSampleResult;
	}

	public String getResultFirst() {
		return resultFirst;
	}

	public void setResultFirst(String resultFirst) {
		this.resultFirst = resultFirst;
	}

	public int getNIVnoFirst() {
		return NIVnoFirst;
	}

	public void setNIVnoFirst(int nIVnoFirst) {
		NIVnoFirst = nIVnoFirst;
	}

	public Date getSecondSampleCollected() {
		return secondSampleCollected;
	}

	public void setSecondSampleCollected(Date secondSampleCollected) {
		this.secondSampleCollected = secondSampleCollected;
	}

	public Date getSecondSampleResult() {
		return secondSampleResult;
	}

	public void setSecondSampleResult(Date secondSampleResult) {
		this.secondSampleResult = secondSampleResult;
	}

	public String getResultSecond() {
		return resultSecond;
	}

	public void setResultSecond(String resultSecond) {
		this.resultSecond = resultSecond;
	}

	public int getNIVnoSecond() {
		return NIVnoSecond;
	}

	public void setNIVnoSecond(int nIVnoSecond) {
		NIVnoSecond = nIVnoSecond;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCountryOfVisit() {
		return countryOfVisit;
	}

	public void setCountryOfVisit(String countryOfVisit) {
		this.countryOfVisit = countryOfVisit;
	}

	public Date getLeavingAffectedCountry() {
		return leavingAffectedCountry;
	}

	public void setLeavingAffectedCountry(Date leavingAffectedCountry) {
		this.leavingAffectedCountry = leavingAffectedCountry;
	}

	public Date getArrivalFromAffectedCountry() {
		return arrivalFromAffectedCountry;
	}

	public void setArrivalFromAffectedCountry(Date arrivalFromAffectedCountry) {
		this.arrivalFromAffectedCountry = arrivalFromAffectedCountry;
	}

	public String getSourceOFInformation() {
		return sourceOFInformation;
	}

	public void setSourceOFInformation(String sourceOFInformation) {
		this.sourceOFInformation = sourceOFInformation;
	}


}
