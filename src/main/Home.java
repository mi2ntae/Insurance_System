package main;

import java.util.Scanner;

import contract.AccidentList;
import contract.AccidentListImpl;
import contract.ContractList;
import contract.ContractListImpl;
import customer.Customer;
import customer.CustomerList;
import customer.CustomerListImpl;
import employee.Employee;
import global.Constants.eGender;
import global.Constants.eInsuranceType;
import insurance.ActualCostInsurance;
import insurance.CancerInsurance;
import insurance.DentalInsurance;
import insurance.DriverInsurance;
import insurance.FireInsurance;
import insurance.Insurance;
import insurance.InsuranceList;
import insurance.InsuranceListImpl;
import insurance.TripInsurance;

public class Home {
	private Scanner scn;

	private InsuranceList insuranceList;
	private ContractList contractList;
	private CustomerList customerList;
	
	public Home() {
		this.scn = new Scanner(System.in);
		this.insuranceList = new InsuranceListImpl();
		this.contractList = new ContractListImpl();
		this.customerList = new CustomerListImpl();
	}
	
	public void start() {
		while (true) {
			System.out.println("*******보험사 메뉴*******");
			System.out.println("1.고객");
			System.out.println("2.직원");
			System.out.println("0.시스템 종료");
			
			switch (scn.nextInt()) {
			case 1:
				System.out.println("*******고객 메뉴*******");
				System.out.println("1.로그인");
				System.out.println("2.회원가입");
				System.out.println("0.뒤로가기");
				switch(scn.nextInt()) {
				case 1:
					if(true) { // 여기에 로그인 함수가 들어가야함
						login : while(true) {
							System.out.println("1.전체 보험리스트 확인하기");
							System.out.println("2.고객 만족 설문조사 작성하기");
							System.out.println("3.가입한 보험 리스트 확인하기");
							System.out.println("4.면담 신청하기");
							System.out.println("0.로그아웃");
							switch (scn.nextInt()) {
							case 1:
								break;
							case 2:
								break;
							case 3:
								break;
							case 4:
								break;
							case 0:
								break login;
							default:
								break;
							}
						}
					}
					break;
				case 2:
					createCustomer();
					break;
				}
				break;
			case 2:
				System.out.println("*******직원 메뉴*******");
				System.out.println("1.로그인");
				System.out.println("2.회원가입");
				System.out.println("0.뒤로가기");
				switch(scn.nextInt()) {
				case 1:
					Employee employee = new Employee();
					if(true) { // 여기에 로그인 함수가 들어가야함
						switch(employee.getEmployeeRole()) {
						
						}
					}
					break;
				case 2:
					// createEmployee();
					break;
				}
				break;
			case 0:
				System.out.println("시스템을 종료합니다");
				System.exit(0);
				break;
			}
		}
	}
	// 고객 가입하기
	private void createCustomer() {
		Customer customer = new Customer();
		System.out.println("이름을 입력해주세요.");
		customer.setName(scn.next());
		
		System.out.println("주소를 입력해주세요.");
		customer.setAddress(scn.next());
		
		System.out.println("전화번호를 입력해주세요.(ooo-oooo-oooo)");
		customer.setPhoneNumber(scn.next());
		
		String input = null;
		do {
			System.out.println("사용하실 ID를 입력해주세요.(중복확인)");
			input = scn.next();
		} while (!customerList.checkDuplication(input));
		customer.setCustomerId(input);

		System.out.println("비밀번호를 입력해주세요.");
		customer.setPassword(scn.next());
		
		if(customerList.insert(customer)) {
			System.out.println("!!!회원가입이 완료되었습니다!!!!");
		}
	}
	
	// 보험 만들기
	private void createInsurance() {
		Insurance insurance;
		while (true) {
			System.out.println("1.운전자 보험\n2.치아 보험\n3.실비 보험\n4.화재 보험\n5.암 보험\n6.여행 보험\n7돌아가기");
			System.out.printf("어떤 보험을 기획하시겠습니까? : ");
			switch (scn.nextInt()) {
			case 1:
				insurance = new DriverInsurance();
				insurance.setType(eInsuranceType.driverInsurance);
				break;
			case 2:
				insurance = new DentalInsurance();
				insurance.setType(eInsuranceType.dentalInsurance);
				break;
			case 3:
				insurance = new ActualCostInsurance();
				insurance.setType(eInsuranceType.actualCostInsurance);
				break;
			case 4:
				insurance = new FireInsurance();
				insurance.setType(eInsuranceType.fireInsurance);
				break;
			case 5:
				insurance = new CancerInsurance();
				insurance.setType(eInsuranceType.cancerInsurance);
				break;
			case 6:
				insurance = new TripInsurance();
				insurance.setType(eInsuranceType.tripInsurance);
				break;
			case 7:
				System.out.println("메인 메뉴로 돌아갑니다");
				return;
			default:
				insurance = new DriverInsurance();
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
			}
			
			insurance = createPlanInsurance(insurance);
			if (insurance == null) {
				createInsurance();
			}
			insurance = createDetailInsurance(insurance);
			
			this.insuranceList.insert(insurance);
			System.out.println("!!!보험 설계가 완료되었습니다!!!!");
		}
	}
	
	// 보험 설계하기
	private Insurance createPlanInsurance(Insurance newInsurance) {
		System.out.println("1.남자\n2.여\n0.돌아가기");
		System.out.printf("가입 제한 성별을 입력하세요 : ");
		int inputGender = scn.nextInt();
		if (inputGender == 1) {
			newInsurance.setDeniedGender(eGender.male);
		} else if (inputGender == 2) {
			newInsurance.setDeniedGender(eGender.female);
		} else if (inputGender == 0) {
			System.out.println("이전 화면으로 돌아갑니다.");
			return null;
		}
		
		System.out.printf("동일한 조건의 보험들을 확인하시겠습니까?(Y/N) : ");
		String inputCondition = scn.next();
		if (inputCondition.equals("Y")) {
			for (Insurance insurance: this.insuranceList.getInsuranceList()) {
				if ((insurance.getType() != newInsurance.getType()) && (insurance.getDeniedGender() == newInsurance.getDeniedGender())) {
					continue;
				}
				System.out.println(insurance.getInsuranceId()+". "+insurance.getName());
				System.out.println("  기본보험료 : "+insurance.getBasicFee());
				System.out.println("  <나이 요율표>");
				System.out.println("영유아 : "+insurance.getRateOfAge()[0]);
				System.out.println("10대 : "+insurance.getRateOfAge()[1]);
				System.out.println("20대 : "+insurance.getRateOfAge()[2]);
				System.out.println("30대 : "+insurance.getRateOfAge()[3]);
				System.out.println("40대 : "+insurance.getRateOfAge()[4]);
				System.out.println("50대 : "+insurance.getRateOfAge()[5]);
				System.out.println("노년층 : "+insurance.getRateOfAge()[6]);
				System.out.println("\n  <성별 요율표>");
				System.out.println("남자 : "+insurance.getRateOfGender()[0]);
				System.out.println("여자 : "+insurance.getRateOfGender()[1]);
				System.out.println("\n  <직업 요율표>");
				System.out.println("직장인 : "+insurance.getRateOfJob()[0]);
				System.out.println("운전기사 : "+insurance.getRateOfJob()[1]);
				System.out.println("공장노동직 : "+insurance.getRateOfJob()[2]);
				System.out.println("교사(수) : "+insurance.getRateOfJob()[3]);
				System.out.println("군인 : "+insurance.getRateOfJob()[4]);
				System.out.println("기타직업 : "+insurance.getRateOfJob()[5]);
				System.out.println("----------------------------\n");
			}
			
			// 보장 내역 가져와서 보여주는 거 코딩해야함
			//
			
			System.out.printf("적용을 원하는 보험의 ID를 입력하세요 : ");
			String inputIndex = scn.next();
			boolean isExist = false;
			for (Insurance insurance: this.insuranceList.getInsuranceList()) {
				if (inputIndex.equals(insurance.getInsuranceId())) {
					isExist = true;
				}
			}
			if (isExist) {
				newInsurance.setRateOfAge(this.insuranceList.getInsuranceList().get(Integer.parseInt(inputIndex)).getRateOfAge());
				newInsurance.setRateOfGender(this.insuranceList.getInsuranceList().get(Integer.parseInt(inputIndex)).getRateOfGender());
				newInsurance.setRateOfJob(this.insuranceList.getInsuranceList().get(Integer.parseInt(inputIndex)).getRateOfJob());
				System.out.println("선택한 보험의 요율정보로 설정했습니다. 세부설정 단계로 넘어갑니다.");
				return newInsurance;
			} else {
				System.out.println("존재하지 않는 ID입니다. 기획 화면으로 넘어갑니다.");
				createPlanInsurance(newInsurance);
			}
		} else if (inputCondition.equals("N")) {
			System.out.println("보험 설계가 완료되었습니다. 세부설정 단계로 넘어갑니다.");
			return newInsurance;
		} else {
			System.out.println("잘못 입력하셨습니다. 기획 화면으로 돌아갑니다.");
			createPlanInsurance(newInsurance);
		}
		return newInsurance;
	}
	
	// 보험 세부설정하기
	private Insurance createDetailInsurance(Insurance newInsurance) {
		
		return newInsurance;
	}
}
