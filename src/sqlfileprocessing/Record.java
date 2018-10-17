package sqlfileprocessing;

import java.io.*;

public class Record {
	private String voyageNumber;
	private String masterBl;
	private String landingBill;
	private String vesselName;
	private String arrivalDate;
	private int weight;
	private int weightlb;
	private String foreignPort;
	private String usPort;
	private String shipperName;
	private String shipperAddress;
	private String originCountry;
	private String consignee;
	private String notifyParty;
	private String notifyAddress;
	private String containerNumber;
	private String containerType;
	private String productDescription;
	private int quantity;
	private String quantityUnit;
	private int measurement;
	private String measureUnit;
	private String marks;
	private String seal;
	private String carrierCode;
	private String carrierName;
	private String placeOfReceipt;
	private String distributionPort;
	
	
	public String getVoyageNumber() {
		return voyageNumber;
		
	}
	
	public void setVoyageNumber(String voyageNumber) {
		this.voyageNumber = voyageNumber;
	}
	
	public String getMasterBl() {
		return masterBl;
		
	}
	
	public void setMasterBl(String masterBl) {
		this.masterBl = masterBl;
	}
	
	public String getLandingBill() {
		return landingBill;
		
	}
	
	public void setLandingBill(String landingBill) {
		this.landingBill = landingBill;
	}
	
	public String getVesselName() {
		return vesselName;
		
	}
	
	public void setVesselName(String vesselName) {
		this.vesselName = vesselName;
	}
	
	public String getArrivalDate() {
		return arrivalDate;
		
	}
	
	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	
	public int getWeight() {
		return weight;
		
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
		Double d = weight*2.204;
		this.weightlb = d.intValue();
	}
	
	public int getWeightlb() {
		
		return weightlb;
	}
	
	public String getForeignPort() {
		return foreignPort;
		
	}
	
	public void setForeignPort(String foreignPort) {
		this.foreignPort = foreignPort;
	}
	
	
	public String getUsPort() {
		return usPort;
		
	}
	
	public void setUsPort(String usPort) {
		this.usPort = usPort;
	}
	
	public String getShipperName() {
		return shipperName;
		
	}
	
	public void setShipperName(String shipperName) {
		this.shipperName = shipperName;
	}
	
	
	public String getShipperAddress() {
		return shipperAddress;
		
	}
	
	public void setShipperAddress(String shipperAddress) {
		this.shipperAddress = shipperAddress;
	}
	
	public String getOriginCountry() {
		return originCountry;
		
	}
	
	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}
	
	public String getConsignee() {
		return consignee;
		
	}
	
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	
	public String getNotifyParty() {
		return notifyParty;
		
	}
	
	public void setNotifyParty(String notifyParty) {
		this.notifyParty = notifyParty;
	}
	
	
	public String getNotifyAddress() {
		return notifyAddress;
		
	}
	
	public void setNotifyAddress(String notifyAddress) {
		this.notifyAddress = notifyAddress;
	}
	
	public String getContainerNumber() {
		return containerNumber;
		
	}
	
	public void setContainerNumber(String containerNumber) {
		this.containerNumber = containerNumber;
	}
	
	
	public String getContainerType() {
		return containerType;
		
	}
	
	public void setContainerType(String containerType) {
		this.containerType = containerType;
	}
	
	public String getProductDescription() {
		return productDescription;
		
	}
	
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	
	public int getQuantity() {
		return quantity;
		
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	public String getQuantityUnit() {
		return quantityUnit;
		
	}
	
	public void setQuantityUnit(String quantityUnit) {
		this.quantityUnit = quantityUnit;
	}
	
	
	public int getMeasurement() {
		return measurement;
		
	}
	
	public void setMeasurement(int measurement) {
		this.measurement = measurement;
	}
	
	
	public String getMeasureUnit() {
		return measureUnit;
		
	}
	
	public void setMeasureUnit(String measureUnit) {
		this.measureUnit = measureUnit;
	}
	
	public String getMarks() {
		return marks;
		
	}
	
	public void setMarks(String marks) {
		this.marks = marks;
	}
	
	public String getSeal() {
		
		return seal;
	}
	
	public void setSeal(String seal) {
		
		this.seal = seal;
	}
	
	
	public String getCarrierCode() {
		
		return carrierCode;
	}
	
	public void setCarrierCode(String carrierCode) {
		
		this.carrierCode = carrierCode;
	}
	
	
	public String getCarrierName() {
		
		return carrierName;
	}
	
	public void setCarrierName(String carrierName) {
		
		this.carrierName = carrierName;
	}
	
	
	public String getPlaceOfReceipt() {
		
		return placeOfReceipt;
	}
	
	public void setPlaceOfReceipt(String placeOfReceipt) {
		
		this.placeOfReceipt = placeOfReceipt;
	}
	
	public String getDistributionPort() {
		
		return distributionPort;
	}
	
	public void setDistributionPort(String distributionPort) {
		
		this.distributionPort = distributionPort;
	}
	

}
