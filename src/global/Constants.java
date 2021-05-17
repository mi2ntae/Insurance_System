package global;

import insurance.*;

public class Constants {
//	public static enum eAge {
//		teen, twenties, thirties, fourties, fifties, sixties, seventies;
//	}
	public static enum eAccidentType {
		driver(1),
		dental(2),
		actual(3),
		fire(4),
		cancer(5),
		trip(6);
		
		final private int num;
		
		private eAccidentType(int num) {
			this.num = num;
		}

		public int getNum() {return num;}
	}

	public static enum eGender {
		male(1),
		female(2),
		both(3);

		final private int num;
		
		private eGender(int num) {
			this.num = num;
		}

		public int getNum() {return num;}
	}

	public static enum eJob {
		officeWorker, driver, factoryWorker, student, teacher, soldier, etc
	}

	public static enum eRankOfCar {
		high, middle, low;
	}

	public static enum eUsageOfStructure {
		house, study, factory, warehouse, office, publicFacility
	}

	public static enum eRiskOfTripCountry {
		safe, first, second, third
	}

	public static enum eGuaranteePlanGuaranteeItem {

	}

	public enum eInsuranceType {
		driverInsurance(1, new DriverInsurance(), "driverInsurance"),
		dentalInsurance(2, new DentalInsurance(), "dentalInsurance"),
		actualCostInsurance(3, new ActualCostInsurance(), "actualCostInsurance"),
		fireInsurance(4, new FireInsurance(), "fireInsurance"),
		cancerInsurance(5, new CancerInsurance(), "cancerInsurance"),
		tripInsurance(6, new TripInsurance(), "tripInsurance");

		private int num;
		private Insurance selectedInsurance;
		private String name;
		
		private eInsuranceType(int num, Insurance selectedInsurance, String name) {
			this.num = num;
			this.selectedInsurance = selectedInsurance;
			this.name = name;
		}

		public int getNum() {return this.num;}
		public Insurance getSelectedInsurance() {return this.selectedInsurance;}
		public String getName() {return this.name;}
	}

	public static enum eFamilyMedicalDisease {
		thyroid, testicular, ovarian, esophageal, lung;
	}

	public static enum eEmployeeRole {
		insuranceDeveloper(1),
		insuranceConfirmer(2),
		salesperson(3),
		contractManager(4),
		compensationHandler(5),
		underWriter(6);
		
		final private int num;
		
		private eEmployeeRole(int num) {
			this.num = num;
		}

		public int getNum() {return num;}
	}
	
//	public static enum eRateOfAge{
//		baby("영유아 : "),
//		teen("10대 : "),
//		twenties("20대 : "),
//		thirties("30대 : "),
//		fourties("40대 : "),
//		fifties("50대 : "),
//		Upfifties("노년층 : ");
//		
//		private String print;
//		
//		private eRateOfAge(String print) {
//			this.print = print;
//		}
//
//		public String getPrint() {
//			return this.print;
//		}
//	}

}
