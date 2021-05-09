package customer;

import global.Constants.eFamilyMedicalDisease;

public class FamilyMedicalHistory {

	// Attributes
	private eFamilyMedicalDisease disease;
	private int relationship;

	// Constructor
	public FamilyMedicalHistory(){

	}

	// getters % setters
	public eFamilyMedicalDisease getDisease() {return disease;}
	public void setDisease(eFamilyMedicalDisease disease) {this.disease = disease;}

	public int getRelationship() {return relationship;}
	public void setRelationship(int relationship) {this.relationship = relationship;}
	
}