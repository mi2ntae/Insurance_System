package global;

public class Constants {
//	public static enum eAge {
//		teen, twenties, thirties, fourties, fifties, sixties, seventies;
//	}
	public static enum eAccidentType {
		actual, cancer, dental, driver, fire, trip;
	}

	public static enum eGender {
		male, female;
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

	public static enum eInsuranceType {
		actualCostInsurance, cancerInsurance, dentalInsurance, driverInsurance, fireInsurance, tripInsurance;
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
