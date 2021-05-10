package main;

import java.util.Scanner;

import contract.AccidentList;
import contract.AccidentListImpl;
import contract.ContractList;
import contract.ContractListImpl;
import customer.CustomerList;
import customer.CustomerListImpl;
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
	private AccidentList accidentList;
	private ContractList contractList;
	private CustomerList customerList;
	
	public Home() {
		scn = new Scanner(System.in);
		insuranceList = new InsuranceListImpl();
		accidentList = new AccidentListImpl();
		contractList = new ContractListImpl();
		customerList = new CustomerListImpl();
	}
	
	public void start() {
		while (true) {
			System.out.println("*******보험사 메뉴*******");
			System.out.println("1.고객 가입하기");
			System.out.println("2.보험 상품 기획하기");
			
			switch (scn.nextInt()) {
			case 1:
				break;
			case 2:
				createInsurance();
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 0:
				System.out.println("시스템을 종료합니다");
				System.exit(0);
				break;
			}
		}
	}
	
	private void createInsurance() {
		Insurance insurance;
		while (true) {
			System.out.println("1.운전자 보험\n2.치아 보험\n3.실비 보험\n4.화재 보험\n5.암 보험\n6.여행 보험\n7돌아가기");
			System.out.printf("어떤 보험을 기획하시겠습니까? : ");
			switch (scn.nextInt()) {
			case 1:
				insurance = new DriverInsurance();
				break;
			case 2:
				insurance = new DentalInsurance();
				break;
			case 3:
				insurance = new ActualCostInsurance();
				break;
			case 4:
				insurance = new FireInsurance();
				break;
			case 5:
				insurance = new CancerInsurance();
				break;
			case 6:
				insurance = new TripInsurance();
				break;
			case 7:
				System.out.println("메인 메뉴로 돌아갑니다");
				return;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
			}
		}
	}
	
	private Insurance planInsurance(Insurance insurance) {
		System.out.println("가입 대상자의 최소, 최대 나이를 선택해주세요");
		System.out.println();
	
		return insurance;
	}
	
}
