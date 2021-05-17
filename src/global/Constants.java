package global;

import insurance.*;

public class Constants {
	public static enum eAge {
		kids(1, "영유아"),
		teen(2, "10대"),
		twenties(3, "20대"),
		thirties(4, "30대"),
		fourties(5, "40대"),
		fifties(6, "50대"),
		older(7, "노년층");

		final private int num;
		final private String name;
		
		private eAge(int num, String name) {
			this.num = num;
			this.name = name;
		}

		public int getNum() {return num;}
		public String getName() {return this.name;}
	}
	
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
		male(1, "남성"),
		female(2, "여성"),
		both(3, "남성/여성");

		final private int num;
		final private String name;
		
		private eGender(int num, String name) {
			this.num = num;
			this.name = name;
		}

		public int getNum() {return num;}
		public String getName() {return this.name;}
	}

	public static enum eJob {
		officeWorker(1, "사무직"),
		driver(2, "운송업"),
		factoryWorker(3, "현장직"),
		student(4, "학생"),
		teacher(5, "교육직"),
		soldier(6, "군인"),
		etc(7, "기타");

		final private int num;
		final private String name;
		
		private eJob(int num, String name) {
			this.num = num;
			this.name = name;
		}

		public int getNum() {return num;}
		public String getName() {return this.name;}
	}

	public static enum eRankOfCar {
		high(1), middle(2), low(3);

		final private int num;
		
		private eRankOfCar(int num) {
			this.num = num;
		}

		public int getNum() {return num;}
	}

	public static enum eUsageOfStructure {
		house(1,"주택"), 
		study(2, "교육"), 
		factory(3, "공장"), 
		warehouse(4, "창고"), 
		office(5, "사무"), 
		publicFacility(6, "공공시설");
		
		final private int num;
		final private String name;
		
		private eUsageOfStructure(int num, String name) {
			this.num = num;
			this.name = name;
		}

		public int getNum() {return num;}
		public String getName() {return name;}
	}

	public static enum eRiskOfTripCountry {
		safe(1, "안전"), 
		first(2, "1단계"), 
		second(3, "2단계"), 
		third(4, "3단계");

		final private int num;
		final private String name;
		
		private eRiskOfTripCountry(int num, String name) {
			this.num = num;
			this.name = name;
		}

		public int getNum() {return num;}
		public String getName() {return name;}
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
		thyroid("갑상선암"), 
		testicular("고환암"), 
		ovarian("난소암"), 
		esophageal("식도암"), 
		lung("폐암");
		
		final private String name;
		
		private eFamilyMedicalDisease(String name) {
			this.name = name;
		}

		public String getName() {return this.name;}
	}
	
	public static String[] eFamilyMedicalRelationship = {"1촌","2촌","3촌","4촌"};
	
	public static String[] ePostedPrice = {"(공시가)<=5천만원", "5천만원<(공시가)<=5억", "5억<(공시가)<=10억", "10억<(공시가)<=20억", "20억<(공시가)"};
	
	public static enum eCancer {
		thyroid(1,"갑상선암"), 
		testicular(2, "고환암"), 
		ovarian(3, "난소암"), 
		esophageal(4, "식도암"), 
		lung(5, "폐암"),
		liver(6, "간암"),
		colorectal(7, "대장암");
		
		final private int num;
		final private String name;
		
		private eCancer(int num, String name) {
			this.num = num;
			this.name = name;
		}

		public int getNum() {return num;}
		public String getName() {return this.name;}
	}

	public static enum eEmployeeRole {
		insuranceDeveloper(1), insuranceConfirmer(2), salesperson(3), contractManager(4), compensationHandler(5), underWriter(6);
		
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
