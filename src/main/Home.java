package main;

import java.util.InputMismatchException;
import java.util.Scanner;

import contract.Contract;
import contract.ContractList;
import contract.ContractListImpl;
import customer.Customer;
import customer.CustomerList;
import customer.CustomerListImpl;
import customer.Insurant;
import employee.Employee;
import employee.EmployeeList;
import employee.EmployeeListImpl;
import employee.UnderWriter;
import global.Constants;
import global.Constants.eAge;
import global.Constants.eEmployeeRole;
import global.Constants.eFamilyMedicalDisease;
import global.Constants.eGender;
import global.Constants.eInsuranceType;
import global.Constants.eJob;
import global.Constants.eRankOfCar;
import global.Constants.eTypeOfCar;
import global.Constants.eRiskOfTripCountry;
import global.Constants.eUsageOfStructure;
import insurance.ActualCostInsurance;
import insurance.CancerInsurance;
import insurance.DentalInsurance;
import insurance.DriverInsurance;
import insurance.FireInsurance;
import insurance.Insurance;
import insurance.InsuranceList;
import insurance.InsuranceListImpl;
import insurance.TripInsurance;

/* 	
 * 공통 : 요율 refactoring enum으로
 * 		 employee extend하기, home에서 employee함수를 통해서 작업(프린트문x, 데이터만 옮겨가기)
		 Guarantee Plan 생각하기
		 
 * 소철 : 보험 일부 정보보기, insurant 가입할 때 case나눠서 입력받기, 예외처리 추가하고 에러메시지 통일
 * 
 * 민태 : insurance id, name 입력받는 것 추가, 예외처리 추가하고 에러메시지 통일
 * 
 * 영석 : 메뉴 추가하고 함수 연결하기, DBA(멋져) serialization으로 각 어레이리스트들 저장, 불러오기(가능하면)
 * 
 */

public class Home {
	private Scanner scn;

	private InsuranceList insuranceList;
	private ContractList contractList;
	private CustomerList customerList;
	private EmployeeList employeeList;
	
	public Home(){
		try {
		this.scn = new Scanner(System.in);
		this.insuranceList = new InsuranceListImpl();
		this.contractList = new ContractListImpl();
		this.customerList = new CustomerListImpl();
		this.employeeList = new EmployeeListImpl();
		}catch(Exception e) {
			System.out.println("error : 파일을 불러오는 오류가 발생했습니다.");
			e.printStackTrace();
		}
	}
	
	public void start() {
		while (true) {
			System.out.println("*******보험사 메뉴*******");
			System.out.println("1.고객");
			System.out.println("2.직원");
			System.out.println("0.시스템 종료");
			
			switch (scn.nextInt()) {
			case 1:
				customer : while (true) {
					System.out.println("*******고객 메뉴*******");
					System.out.println("1.로그인");
					System.out.println("2.회원가입");
					System.out.println("0.뒤로가기");
					try {
						switch (scn.nextInt()) {
						case 1:
							System.out.println("ID를 입력하세요 : ");
							String id = scn.next();
							System.out.println("비밀번호를 입력하세요 : ");
							String pw = scn.next();
							Customer customer = loginCustomer(id, pw);
							if (customer != null) {
								System.out.println("안녕하세요 " + customer.getName() + "님!");
								login: while (true) {
									System.out.println("1.전체 보험리스트 확인하기");
									System.out.println("2.고객 만족 설문조사 작성하기");
									System.out.println("3.가입한 보험 리스트 확인하기");
									System.out.println("4.면담 신청하기");
									System.out.println("0.로그아웃");
									try {
										switch (scn.nextInt()) {
										case 1:
											this.showAllInsurance();
											while (true) {
												System.out.println("보험을 가입하시겠습니까?(y/n)");
												String input = scn.next();
												if (input.equals("y")) {
													this.contractInsurnace(customer); // customer를 받아가서 아이디를 받거나, customer.getCustomerId()로 아이디를 받아갈 것
													break;
												} else if (input.equals("n")) {
													break;
												} else {
													System.out.println("error : 정해진 문자를 사용해주세요");
													System.out.println("-----------------------");
													continue;
												}
											}
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
											System.out.println("error : 범위 내의 숫자를 입력해주세요");
											System.out.println("------------------------------");
											break;
										}
									} catch (InputMismatchException e) {
										System.out.println("error : 숫자를 입력해주세요");
										System.out.println("-----------------------");
										scn.nextLine();
									}
								}
							}
							break;
						case 2:
							createCustomer();
							break;
						case 0:
							break customer;
						default:
							System.out.println("error : 범위 내의 숫자를 입력해주세요");
							System.out.println("------------------------------");
							break;
						}
					} catch (InputMismatchException e) {
						System.out.println("error : 숫자를 입력해주세요");
						System.out.println("-----------------------");
						scn.nextLine();
					}
				}
				break;
			case 2:
				employee: while (true) {
					System.out.println("*******직원 메뉴*******");
					System.out.println("1.로그인");
					System.out.println("2.회원가입");
					System.out.println("0.뒤로가기");
					try {
						switch (scn.nextInt()) {
						case 1:
							System.out.println("ID를 입력하세요 : ");
							String id = scn.next();
							System.out.println("비밀번호를 입력하세요 : ");
							String pw = scn.next();
							Employee employee = this.loginEmployee(id, pw);
							if (employee != null) {
								System.out.println("안녕하세요 " + employee.getName() + "님!");
								switch (employee.getEmployeeRole()) {
								case insuranceDeveloper:
									employee2: while (true) {
										System.out.println("*******보험개발자 메뉴*******");
										System.out.println("1.고객 만족 설문조사 결과보기");
										System.out.println("2.보험상품 설계하기");
										System.out.println("3.보험상품 사후관리하기");
										System.out.println("0.로그아웃");
										try {
											switch (scn.nextInt()) {
											case 1:
												// 고객 만족 설문조사 결과보기
												break;
											case 2:
												this.createInsurance();
												break;
											case 3:
												// 보험상품 사후 관리하기
												break;
											case 0:
												break employee2;
											default:
												System.out.println("error : 범위 내의 숫자를 입력해주세요");
												System.out.println("------------------------------");
												break;
											}
										} catch (InputMismatchException e) {
											System.out.println("error : 숫자를 입력해주세요");
											System.out.println("-----------------------");
											scn.nextLine();
										}
									}
									break;
								case insuranceConfirmer:
									employee2: while (true) {
										System.out.println("*******보험상품 확정자 메뉴*******");
										System.out.println("1.보험상품 확정하기");
										System.out.println("0.로그아웃");
										try {
											switch (scn.nextInt()) {
											case 1:
												this.confirmInsurance();
												break;
											case 0:
												break employee2;
											default:
												System.out.println("error : 범위 내의 숫자를 입력해주세요");
												System.out.println("------------------------------");
												break;
											}
										} catch (InputMismatchException e) {
											System.out.println("error : 숫자를 입력해주세요");
											System.out.println("-----------------------");
											scn.nextLine();
										}
									}
									break;
								case salesperson:
									employee2: while (true) {
										System.out.println("*******영업사원 메뉴*******");
										System.out.println("1.실적 확인하기");
										System.out.println("2.면담신청 리스트 확인하기");
										System.out.println("3.전체보험리스트 확인하기");
										System.out.println("0.로그아웃");
										try {
											switch (scn.nextInt()) {
											case 1:
												// 실적 확인하기
												break;
											case 2:
												// 면담신청 리스트 확인하기
												break;
											case 3:
												showAllInsurance();// 전체보험리스트 확인하기
												break;
											case 0:
												break employee2;
											default:
												System.out.println("error : 범위 내의 숫자를 입력해주세요");
												System.out.println("------------------------------");
												break;
											}
										} catch (InputMismatchException e) {
											System.out.println("error : 숫자를 입력해주세요");
											System.out.println("-----------------------");
											scn.nextLine();
										}
									}
									break;
								case contractManager:
									employee2: while (true) {
										System.out.println("*******계약관리자 메뉴*******");
										System.out.println("1.우편물 발송하기");
										System.out.println("2.가입자 리스트 확인하기");
										System.out.println("0.로그아웃");
										try {
											switch (scn.nextInt()) {
											case 1:
												// 우편물 발송하기
												break;
											case 2:
												// 가입자 리스트 확인하기
												break;
											case 0:
												break employee2;
											default:
												System.out.println("error : 범위 내의 숫자를 입력해주세요");
												System.out.println("------------------------------");
												break;
											}
										} catch (InputMismatchException e) {
											System.out.println("error : 숫자를 입력해주세요");
											System.out.println("-----------------------");
											scn.nextLine();
										}
									}
									break;
								case compensationHandler:
									employee2: while (true) {
										System.out.println("*******보상처리사 메뉴*******");
										System.out.println("1.배상금 산출하기");
										System.out.println("2.협력업체 비용 지불하기");
										System.out.println("0.로그아웃");
										try {
											switch (scn.nextInt()) {
											case 1:
												// 배상금 산출하기
												break;
											case 2:
												// 협력업체 비용 지불하기
												break;
											case 0:
												break employee2;
											default:
												System.out.println("error : 범위 내의 숫자를 입력해주세요");
												System.out.println("------------------------------");
												break;
											}
										} catch (InputMismatchException e) {
											System.out.println("error : 숫자를 입력해주세요");
											System.out.println("-----------------------");
											scn.nextLine();
										}
									}
									break;
								case underWriter:
									employee2: while (true) {
										System.out.println("*******U/W 메뉴*******");
										System.out.println("1.보험계약 심사하기");
										System.out.println("0.로그아웃");
										try {
											switch (scn.nextInt()) {
											case 1:
												this.judgeContract();
												// 보험계약 심사하기
												break;
											case 0:
												break employee2;
											default:
												System.out.println("error : 범위 내의 숫자를 입력해주세요");
												System.out.println("------------------------------");
												break;
											}
										} catch (InputMismatchException e) {
											System.out.println("error : 숫자를 입력해주세요");
											System.out.println("-----------------------");
											scn.nextLine();
										}
									}
									break;
								default:
									System.out.println("asdasdasdasdasdasd");
									System.out.println(employee.getEmployeeRole());
									break;

								}
							}
							break;
						case 2:
							 createEmployee();
							break;
						case 0:
							break employee;
						default:
							System.out.println("error : 범위 내의 숫자를 입력해주세요");
							System.out.println("------------------------------");
							break;
						}
					} catch (InputMismatchException e) {
						System.out.println("error : 숫자를 입력해주세요");
						System.out.println("----------------------");
						scn.nextLine();
					}
				}
				break;
			case 0:
				System.out.println("시스템을 종료합니다");
				System.exit(0);
				break;
			}
		}
	}
	
	// 가입한 보험 리스트 보기
	private void showSubscribedInsurance(Customer customer) {
		for (Contract contract : this.contractList.getContractList()) {
			if(contract.getCustomer().getCustomerId() == customer.getCustomerId()) {
				this.showInsuranceData(contract.getInsurance());
			}
		}
	}
	
	// 보험 계약 심사하기
	private void judgeContract() {
		UnderWriter underwriter = new UnderWriter();
		underwriter.assoicate(this.contractList);
		for(Contract contract : this.contractList.getContractList()) {
			if(contract.isEffectiveness() == false) {
				this.showContractData(contract);
			}
		}
		Contract contract = null;
		while(contract == null) {
			System.out.println("'심사'할 계약 ID를 입력하세요");
			contract = this.contractList.search(scn.next());
			if (contract != null) {
				System.out.println("------보험료 산출정보------");
				System.out.println(contract.getInsurance().calculateFee(contract.getInsurant()) + "원");				
				System.out.println("1.승인\n2.거부");
				String input = scn.next();
				while(input != "1" || input != "2") {
					System.out.println("1.승인\n2.거부");
					switch (input) {
					default:
						System.out.println("잘못된 입력입니다");
						break;
					case "1":
						underwriter.approveContract(contract);
						break;
					case "2":
						underwriter.refuseContract(contract);
						break;
					}
				}
			} else {
				System.out.println("해당 계약이 존재하지 않습니다");
			}
		}
	}
	
	// 선택된 계약 보기
	private void showContractData(Contract contract) {
		System.out.println("------계약 상세정보------");
		System.out.println("계약 ID : " + contract.getContractId());
		System.out.println("계약 기간 : " + contract.getLifespanOfContract());
		System.out.println("지불된 요금 : " + contract.getPaidFee());
		System.out.println("영업사원 ID" + contract.getSalespersonId());
		System.out.println("미납 기간 : " + contract.getUnpaidPeriod());
		this.showInsurantData(contract.getInsurant());
		this.showInsuranceData(contract.getInsurance());
	}
	
	private void showInsurantData(Insurant insurant) {
		System.out.println("------보험 가입자 상세정보------");
		System.out.println("사고 기록 : " + insurant.getAccidentHistory());
		System.out.println("주소 : " + insurant.getAddress());
		System.out.println("나이 : " + insurant.getAge());
		System.out.println("보험 가입자 ID : " + insurant.getInsurantId());
		System.out.println("이름 : " + insurant.getName());
		System.out.println("전화번호 : " + insurant.getPhoneNumber());
		System.out.println("공시지사 :" + insurant.getPostedPriceOfStructure());
		System.out.println("구조물 용도 : " + insurant.getUsageOfStructure());
		System.out.println("성별 : " + insurant.getGender());
		System.out.println("직업 : " + insurant.getJob());
		System.out.println("자동차 등급 : " + insurant.getRankOfCar());
		System.out.println("여행 국가 위험 등급 : " + insurant.getRiskOfTripCountry());
	}

	private void showCustomerData(Customer customer) {
		System.out.println("------고객 상세정보------");
		System.out.println("주소 : " + customer.getAddress());
		System.out.println("고객 ID : " + customer.getCustomerId());
		System.out.println("이름 : " + customer.getName());
		System.out.println("전화번호 : " + customer.getPhoneNumber());
		System.out.println("고객 PW : " + customer.getPassword());
		System.out.print("보험 가입자 ID :");
		if(customer.getInsurantList().isEmpty()) {
			System.out.print("보험 가입자 ID : null");
		} else {
			for(Insurant insurant : customer.getInsurantList().getInsurantList()) {
				System.out.print(" " + insurant.getInsurantId());
			}
		}
		System.out.println();
	}

	// 전체 보험 리스트 확인하기
	private void showAllInsurance() {
		eInsuranceType type = null;
		while (true) {
			System.out.println("0.이전\n1.운전자 보험\n2.치아 보험\n3.실비 보험\n4.화재 보험\n5.암 보험\n6.여행 보험\n7.전체보기");
			int input = 0;
			while(!(input > 0) &&!(input < 8)) {
				try {
					input = scn.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("error : 숫자를 입력해주세요");
					System.out.println("----------------------");
				}
				switch(input) {
				case 0 :
					return;
				case 1 :
					type = eInsuranceType.driverInsurance;
					break;
				case 2 :
					type = eInsuranceType.dentalInsurance;
					break;
				case 3 :
					type = eInsuranceType.actualCostInsurance;
					break;
				case 4 :
					type = eInsuranceType.fireInsurance;
					break;
				case 5 :
					type = eInsuranceType.cancerInsurance;
					break;
				case 6 :
					type = eInsuranceType.tripInsurance;
					break;
				case 7 :
					break;
				default:
					System.out.println("error : 범위 내의 숫자를 입력해주세요");
					System.out.println("------------------------------");
					continue;
				}
			}
			break;
		}
		System.out.println("-----보험리스트-----");
		if(type == null) { //null이면 전체보험
			for (Insurance insurance: this.insuranceList.getInsuranceList()) {
				this.showInsuranceData(insurance);
			}
		} else {
			for (Insurance insurance: this.insuranceList.getInsuranceList()) {
				if(insurance.getType() == type) {
					this.showInsuranceData(insurance);
				}
			}
		}
		System.out.println("-----------------\n");
	}
	

	// 보험 가입하기
	private void contractInsurnace(Customer customer) {
		Insurance insurance = null;
		while(insurance == null) {
			System.out.print("가입할 보험 ID를 입력해주세요 : ");
			insurance = this.insuranceList.select(scn.next());
			if (insurance != null) {
				Insurant insurant = this.selectInsurant(customer, insurance);
				if (customer != null) {
					Contract contract = new Contract();
					if(this.contractList.getContractList().isEmpty()) {
						contract.setContractId(Integer.toString(1));
					} else {
						contract.setContractId(Integer.toString(Integer.parseInt(this.contractList.getContractList().get(this.contractList.getContractList().size() - 1).getContractId()) + 1));
						
					}
					contract.joinInsurance(customer, insurance, insurant);
					this.contractList.insert(contract);
					System.out.println("!!!!보험가입이 완료되었습니다!!!!");
				}
			} else {
				System.out.println("해당 보험이 존재하지 않습니다");
			}
		}
	}
	// 보험 가입자 선택하기
	private Insurant selectInsurant(Customer customer, Insurance insurance) {
		System.out.println("1.보험가입자 선택\n2.보험가입자 생성");
		int input = scn.nextInt();
		while(input != 1 && input != 2) {
			System.out.println("잘못된 입력입니다");
			System.out.print("1.보험가입자 선택\n2.보험가입자 생성 : ");
			input = scn.nextInt();
		}
		if(input == 1 && !customer.getInsurantList().isEmpty()) {
			boolean flag = false;
			while(!flag) {
				System.out.print("보험가입자 ID를 입력하세요 : ");
				String InsurantId = scn.next();
				if(customer.getInsurantList().select(InsurantId) != null) {
					flag = true;
					this.contractInsurnace(customer);
				} else {
					System.out.println("해당 보험가입자가 존재하지 않습니다");
				}
			}
		} else {
			this.createInsurant(customer, insurance);
		}
		return customer.getInsurantList().getSelectedInsurant();
	}

	private void createInsurant(Customer customer, Insurance insurance) {
		Insurant insurant = new Insurant();
		System.out.print("사고횟수 : ");
		int accidentHistory = scn.nextInt();
		insurant.setAccidentHistory(accidentHistory);
		
		System.out.print("주소 : ");
		String address = scn.next();
		insurant.setAddress(address);
		
		System.out.print("나이 : ");
		int age = scn.nextInt();
		insurant.setAge(age);
		
		if(customer.getInsurantList().getInsurantList().isEmpty()) {
			insurant.setInsurantId("1");
		} else {
			insurant.setInsurantId(Integer.toString(Integer.parseInt(customer.getInsurantList().getInsurantList().get(customer.getInsurantList().getInsurantList().size() - 1).getInsurantId()) - 1));
		}
		
		
		System.out.print("이름 : ");
		String name = scn.next();
		insurant.setName(name);
		
		System.out.print("전화번호 : ");
		String phoneNum = scn.next();
		insurant.setPhoneNumber(phoneNum);
		
		if(insurance.getType() == eInsuranceType.fireInsurance) {
			System.out.print("건물가격 : ");
			long postedPriceOfStructure = scn.nextLong();
			insurant.setPostedPriceOfStructure(postedPriceOfStructure);
			
			System.out.println("건물용도\n1.집\n2.학원\n3.공장\n4.창고\n5.사무실\n6.공공시설");
			eUsageOfStructure usageOfStructure = null;
			while (usageOfStructure == null) {
				switch (scn.nextInt()) {
				default:
					System.out.print("입력값이 잘못되었습니다");
					break;
				case 1:
					usageOfStructure = eUsageOfStructure.house;
					break;
				case 2:
					usageOfStructure = eUsageOfStructure.study;
					break;
				case 3:
					usageOfStructure = eUsageOfStructure.factory;
					break;
				case 4:
					usageOfStructure = eUsageOfStructure.warehouse;
					break;
				case 5:
					usageOfStructure = eUsageOfStructure.office;
					break;
				case 6:
					usageOfStructure = eUsageOfStructure.publicFacility;
					break;
				}
			}
			insurant.setUsageOfStructure(usageOfStructure);
		}
		
		System.out.println("성별\n1.남자\n2.여");
		eGender gender = null;
		while(gender == null) {
			switch(scn.nextInt()) {
				default :
					System.out.print("입력값이 잘못되었습니다");
					break;
				case 1 :
					gender = eGender.male;
					break;
				case 2 :
					gender = eGender.female;
					break;
			}
		}
		insurant.setGender(gender);
		
		if(insurance.getType() != eInsuranceType.fireInsurance) {
			System.out.println("직업\n1.사무직\n2.운전자\n3.현장직\n4.학생\n5.교사\n6.군인\n7.기타");
			eJob job = null;
			while(job == null) {
				switch(scn.nextInt()) {
				default :
					System.out.print("입력값이 잘못되었습니다");
					break;
				case 1 :
					job = eJob.officeWorker;
					break;
				case 2 :
					job = eJob.driver;
					break;
				case 3 :
					job = eJob.factoryWorker;
					break;
				case 4 :
					job = eJob.student;
					break;
				case 5 :
					job = eJob.teacher;
					break;
				case 6 :
					job = eJob.soldier;
					break;
				case 7 :
					job = eJob.etc;
					break;
				}
			}
			insurant.setJob(job);
		}
		
		if(insurance.getType() == eInsuranceType.driverInsurance) {
			System.out.println("자동차등급\n1.최고급\n2.고급\n3.보급형\n4.저가");
			eRankOfCar rankOfCar = null;
			while(rankOfCar == null) {
				switch(scn.nextInt()) {
				default :
					System.out.print("입력값이 잘못되었습니다");
					break;
				case 1 :
					rankOfCar = eRankOfCar.Luxury;
					break;
				case 2 :
					rankOfCar = eRankOfCar.high;
					break;
				case 3 :
					rankOfCar = eRankOfCar.middle;
					break;
				case 4 :
					rankOfCar = eRankOfCar.low;
					break;
				}
			}
			insurant.setRankOfCar(rankOfCar);
			
			System.out.println("자동차종류\n1.버스\n2.승합차\n3.SUV\n4.외제차\n5.기타");
			eTypeOfCar typeOfCar = null;
			while(typeOfCar == null) {
				switch(scn.nextInt()) {
				default :
					System.out.print("입력값이 잘못되었습니다");
					break;
				case 1 :
					typeOfCar = eTypeOfCar.bus;
					break;
				case 2 :
					typeOfCar = eTypeOfCar.van;
					break;
				case 3 :
					typeOfCar = eTypeOfCar.suv;
					break;
				case 4 :
					typeOfCar = eTypeOfCar.foreign;
					break;
				case 5 :
					typeOfCar = eTypeOfCar.etc;
					break;
				}
			}
			insurant.setTypeOfCar(typeOfCar);
		}
		
		if(insurance.getType() == eInsuranceType.tripInsurance) {
			System.out.println("여행국가 위험등급\n1.안전\n2.1등급\n3.2등급\n4.3등급");
			eRiskOfTripCountry riskOfTripCountry = null;
			while(riskOfTripCountry == null) {
				switch(scn.nextInt()) {
				default :
					System.out.print("입력값이 잘못되었습니다");
					break;
				case 1 :
					riskOfTripCountry = eRiskOfTripCountry.safe;
					break;
				case 2 :
					riskOfTripCountry = eRiskOfTripCountry.first;
					break;
				case 3 :
					riskOfTripCountry = eRiskOfTripCountry.second;
					break;
				case 4 :
					riskOfTripCountry = eRiskOfTripCountry.third;
					break;
				}
			}
			insurant.setRiskOfTripCountry(riskOfTripCountry);
		}
		customer.createInsurant(insurant);
		customer.getInsurantList().select(insurant.getInsurantId());
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
		
		check : while(true) {
			System.out.println("사용하실 ID를 입력해주세요.(중복확인)");
			String input = scn.next();
			for (Customer customer2 : this.customerList.getCustomerList()) {
				if (customer2.getCustomerId().equals(input)) {
					System.out.println("이미 존재하는 ID입니다!(사용불가)");
					System.out.println("--------------------------");
					continue check;
				}
			}
			customer.setCustomerId(input);
			break;
		}

		System.out.println("비밀번호를 입력해주세요.");
		customer.setPassword(scn.next());
		
		if(customerList.insert(customer)) {
			System.out.println("!!!회원가입이 완료되었습니다!!!!");
		}
	}
	// 고객 로그인하기
	private Customer loginCustomer(String id, String pw) {
		for(Customer customer : this.customerList.getCustomerList()) {
			if(customer.getCustomerId().equals(id)) {
				if(customer.getPassword().equals(pw)) {
					System.out.println("!!!로그인에 성공하였습니다!!!!");
					return customer;
				}else {
					System.out.println("error : 비밀번호가 틀립니다!");
					return null;
				}
			}
		}
		System.out.println("error : 존재하지 않는 ID입니다!");
		return null;
	}
	
	// 직원 가입하기
	private void createEmployee() {
		Employee employee = new Employee();
		System.out.println("이름을 입력해주세요.");
		employee.setName(scn.next());
		
		System.out.println("전화번호를 입력해주세요.(ooo-oooo-oooo)");
		employee.setPhoneNumber(scn.next());

		role : while (true) {
			System.out.println("직종을 선택해주세요.");
			System.out.println("1.보험개발자");
			System.out.println("2.보험상품 확정자");
			System.out.println("3.영업사원");
			System.out.println("4.계약관리인");
			System.out.println("5.보상처리사");
			System.out.println("6.U/W");
			try {
				switch (scn.nextInt()) {
				case 1:
					employee.setEmployeeRole(eEmployeeRole.insuranceDeveloper);
					break role;
				case 2:
					employee.setEmployeeRole(eEmployeeRole.insuranceConfirmer);
					break role;
				case 3:
					employee.setEmployeeRole(eEmployeeRole.salesperson);
					break role;
				case 4:
					employee.setEmployeeRole(eEmployeeRole.contractManager);
					break role;
				case 5:
					employee.setEmployeeRole(eEmployeeRole.compensationHandler);
					break role;
				case 6:
					employee.setEmployeeRole(eEmployeeRole.underWriter);
					break role;
				default:
					System.out.println("error : 범위 내의 숫자를 입력해주세요");
					System.out.println("------------------------------");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("error : 숫자를 입력해주세요");
				System.out.println("-----------------------");
				scn.nextLine();
			}
		}
		
		check : while(true) {
			System.out.println("사용하실 ID를 입력해주세요.(중복확인)");
			String input = scn.next();
			for (Employee employee2 : this.employeeList.getEmployeeList()) {
				if (employee2.getEmployeeId().equals(input)) {
					System.out.println("이미 존재하는 ID입니다!(사용불가)");
					System.out.println("--------------------------");
					continue check;
				}
			}
			employee.setEmployeeId(input);
			break;
		}

		System.out.println("비밀번호를 입력해주세요.");
		employee.setPassword(scn.next());

		if (employeeList.insert(employee)) {
			System.out.println("!!!회원가입이 완료되었습니다!!!!");
		}
	}

	// 직원 로그인하기
	private Employee loginEmployee(String id, String pw) {
		for (Employee employee : this.employeeList.getEmployeeList()) {
			if (employee.getEmployeeId().equals(id)) {
				if (employee.getPassword().equals(pw)) {
					System.out.println("!!!로그인에 성공하였습니다!!!!");
					return employee;
				} else {
					System.out.println("error : 비밀번호가 틀립니다!");
					return null;
				}
			}
		}
		System.out.println("error : 존재하지 않는 ID입니다!");
		return null;
	}

	// 보험 만들기
	private void createInsurance() {
		Insurance insurance = null;
		while (true) {
			System.out.println("1.운전자 보험\n2.치아 보험\n3.실비 보험\n4.화재 보험\n5.암 보험\n6.여행 보험\n0.돌아가기");
			System.out.printf("어떤 보험을 기획하시겠습니까? : ");
			try {
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
				case 0:
					System.out.println("메인 메뉴로 돌아갑니다");
					return;
				default:
					System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
					continue;
				}
			} catch (InputMismatchException e) {
				System.out.println("error : 숫자를 입력해주세요");
				System.out.println("-----------------------");
				scn.nextLine();
				continue;
			}
			
			insurance = this.createPlanInsurance(insurance);	// 보험 설계하기
			if (insurance == null) {
				continue;
			}
			insurance = this.createDetailInsurance(insurance);	// 보험 세부설정하기
			
			if (this.insuranceList.insert(insurance)) {
				System.out.println("!!!보험 설계가 완료되었습니다!!!!");
			} else {
				System.out.println("보험 설계에 실패하였습니다. 다시 시도해주세요.");
				continue;
			}
		}
	}

	// 보험 설계하기
	private Insurance createPlanInsurance(Insurance newInsurance) {
		while (true) {
			System.out.println("1.남자\n2.여자\n3.남자,여자\n0.돌아가기");
			System.out.printf("가입 가능 성별을 입력하세요 : ");
			try {
				switch(scn.nextInt()) {
				case 1:
					newInsurance.setGender(eGender.female);
					break;
				case 2:
					newInsurance.setGender(eGender.male);
					break;
				case 3:
					newInsurance.setGender(eGender.both);
					break;
				case 0:
					System.out.println("이전 화면으로 돌아갑니다.");
					return null;
				default:
					System.out.println("error : 범위 내의 숫자를 입력해주세요");
					System.out.println("-----------------------");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("error : 숫자를 입력해주세요");
				System.out.println("-----------------------");
				scn.nextLine();
				continue;
			}
			
			System.out.printf("동일한 조건의 보험들을 확인하시겠습니까?(y/n) : ");
			String inputCondition = scn.next();
			if (inputCondition.equals("y")) {
				boolean isEmpty = true;
				for (Insurance insurance: this.insuranceList.getInsuranceList()) {
					if ((insurance.getType() != newInsurance.getType()) && (insurance.getGender() == newInsurance.getGender())) {
						continue;
					}
					this.showInsuranceData(insurance);
					isEmpty = false;
				}
				if (isEmpty) {
					System.out.println("확인할 수 있는 보험이 존재하지 않습니다. 세부설정 단계로 넘어갑니다.");
					return newInsurance;
				} else{
					System.out.println("(적용을 원하는 보험이 없으면 0을 입력하세요)");
					System.out.printf("적용을 원하는 보험의 ID를 입력하세요 : ");
					String inputIndex = scn.next();
					if (inputIndex.equals("0")) {
						System.out.println("보험 설계가 완료되었습니다. 세부설정 단계로 넘어갑니다.");
						return newInsurance;
					}
					boolean isExist = false;
					for (Insurance insurance: this.insuranceList.getInsuranceList()) {
						if(insurance == null) break;
						if (inputIndex.equals(insurance.getInsuranceId())) {
							isExist = true;
						}
					}
					if (isExist) {
						newInsurance.setRateOfAge(this.insuranceList.getInsuranceList().get(Integer.parseInt(inputIndex)).getRateOfAge());
						newInsurance.setRateOfGender(this.insuranceList.getInsuranceList().get(Integer.parseInt(inputIndex)).getRateOfGender());
						newInsurance.setRateOfJob(this.insuranceList.getInsuranceList().get(Integer.parseInt(inputIndex)).getRateOfJob());
						newInsurance.setClone(true);
						System.out.println("선택한 보험의 요율정보로 설정했습니다. 세부설정 단계로 넘어갑니다.");
						return newInsurance;
					} else {
						System.out.println("존재하지 않는 ID입니다. 기획 화면으로 돌아갑니다.");
						continue;
					}
				}
			} else if (inputCondition.equals("n")) {
				System.out.println("보험 설계가 완료되었습니다. 세부설정 단계로 넘어갑니다.");
				return newInsurance;
			} else {
				System.out.println("잘못 입력하셨습니다. 기획 화면으로 돌아갑니다.");
				continue;
			}
			
		}
	}
	
	// 보험 세부설정하기
	private Insurance createDetailInsurance(Insurance newInsurance) {
		String newId = Integer.toString(Integer.parseInt(this.insuranceList.getInsuranceList().get(this.insuranceList.getInsuranceList().size()-1).getInsuranceId())+1);
		newInsurance.setInsuranceId(newId);
		
		while (true) {
			System.out.printf("만드실 보험의 이름을 입력해주세요 : ");
			newInsurance.setName(scn.next());
			System.out.printf("기본 보험료를 입력해주세요 : ");
			try {
				newInsurance.setBasicFee(scn.nextInt());
				System.out.printf("특약가입이 가능한 보험입니까?(y/n) : ");
				String inputSpecial = scn.next();
				if (inputSpecial.equals("y")) {
					newInsurance.setSpecialContract(true);
					System.out.printf("특약 보험료를 입력해주세요 : ");
					newInsurance.setSpecialContractFee(scn.nextInt());
				} else if (inputSpecial.equals("n")) {
					newInsurance.setSpecialContract(false);
					newInsurance.setSpecialContractFee(0);
				} else {
					System.out.println("error : 정해진 문자를 사용해주세요");
					System.out.println("-----------------------");
					continue;
				}
			} catch (InputMismatchException e) {
				System.out.println("error : 숫자를 입력해주세요");
				System.out.println("-----------------------");
				scn.nextLine();
				continue;
			}
	
			while (true) {
				System.out.printf("보장 기간을 입력해주세요(연단위) : ");
				try {
					int inputPeriod = scn.nextInt();
					if (inputPeriod >= 80 || inputPeriod <= 0) {
						System.out.println("보장기간은 1~79년 사이에서 설정해주세요");
						continue;
					}
					newInsurance.setWarrantyPeriod(inputPeriod);
					break;
				} catch (InputMismatchException e) {
					System.out.println("error : 숫자를 입력해주세요");
					System.out.println("-----------------------");
					scn.nextLine();
					continue;
				}
			}
			break;
		}
		if (!newInsurance.isClone()) {
			double[] tmpRateOfAge = new double[eAge.values().length];
			System.out.println("나이에 대한 요율을 설정합니다. ex) 20대 : 1.0)");
			
			for (int i = 0; i < eAge.values().length; i++) {
				try {
					System.out.printf(eAge.values()[i].getName() + " : ");
					tmpRateOfAge[i] = scn.nextDouble();
				} catch (InputMismatchException e) {
					System.out.println("error : 숫자를 입력해주세요");
					System.out.println("-----------------------");
					scn.nextLine();
					i--;
				}
			}
			newInsurance.setRateOfAge(tmpRateOfAge);
			
			double[] tmpRateOfGender = new double[eGender.values().length - 1];
			System.out.println("성별에 대한 요율을 설정합니다. ex) 남자 : 1.0)");
			for (int i = 0; i < eGender.values().length - 1; i++) {
				try {
					System.out.printf(eGender.values()[i].getName() + " : ");
					tmpRateOfAge[i] = scn.nextDouble();
				} catch (InputMismatchException e) {
					System.out.println("error : 숫자를 입력해주세요");
					System.out.println("-----------------------");
					scn.nextLine();
					i--;
				}
			}
			newInsurance.setRateOfGender(tmpRateOfGender);
			
			double[] tmpRateOfJob = new double[eJob.values().length];
			System.out.println("직업에 대한 요율을 설정합니다. ex) 직장 : 1.0)");
			
			for (int i = 0; i < eJob.values().length; i++) {
				try {
					System.out.printf(eJob.values()[i].getName() + " : ");
					tmpRateOfJob[i] = scn.nextDouble();
				} catch (InputMismatchException e) {
					System.out.println("error : 숫자를 입력해주세요");
					System.out.println("-----------------------");
					scn.nextLine();
					i--;
				}
			}
			
			
			newInsurance.setRateOfJob(tmpRateOfJob);
		} 
		
		// 특화 요율 설정하기 코딩해야함
		while (true) {
			switch (newInsurance.getType()) {
			case cancerInsurance:
				System.out.println("가족 병력에 따른 요율을 설정합니다.");
				double[] tmpRateOfFamilyDisease = new double[5];
				for (int i = 0; i < eFamilyMedicalDisease.values().length; i++) {
					System.out.printf(eFamilyMedicalDisease.values()[i].getName()+" : ");
					try {
						tmpRateOfFamilyDisease[i] = scn.nextDouble();
					} catch (InputMismatchException e) {
						System.out.println("error : 숫자를 입력해주세요");
						System.out.println("-----------------------");
						scn.nextLine();
						i--;
					}
				}
				System.out.println("병력이 있는 가족과의 관계에 따른 요율을 설정합니다.");
				double[] tmpRateOfFamilyRelationship = new double[4];
				for (int i = 0; i < Constants.eFamilyMedicalRelationship.length; i++) {
					System.out.printf(Constants.eFamilyMedicalRelationship[i]+" : ");
					try {
						tmpRateOfFamilyRelationship[i] = scn.nextDouble();
					} catch (InputMismatchException e) {
						System.out.println("error : 숫자를 입력해주세요");
						System.out.println("-----------------------");
						scn.nextLine();
						i--;
					}
				}
				((CancerInsurance)newInsurance).setRateOfFamilyMedicalDisease(tmpRateOfFamilyDisease);
				((CancerInsurance)newInsurance).setRateOfFamilyMedicalRelationship(tmpRateOfFamilyRelationship);
				break;
			case dentalInsurance:
				int annuallimit = 0;
				while (true) {
					System.out.printf("연간 한도 횟수를 입력해주세요 : ");
					try {
						annuallimit = scn.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("error : 정수를 입력해주세요");
						System.out.println("-----------------------");
						scn.nextLine();
						continue;
					}
					break;
				}
				((DentalInsurance)newInsurance).setAnnualLimitCount(annuallimit);
				break;
			case driverInsurance:
				// 코딩해야
				break;
			case fireInsurance:
				System.out.println("공시가격에 따른 요율을 설정합니다.");
				double[] tmpRateOfPostedPrice = new double[5];
				for (int i = 0; i < Constants.ePostedPrice.length; i++) {
					System.out.printf(Constants.ePostedPrice[i]+" : ");
					try {
						tmpRateOfPostedPrice[i] = scn.nextDouble();
					} catch (InputMismatchException e) {
						System.out.println("error : 숫자를 입력해주세요");
						System.out.println("-----------------------");
						scn.nextLine();
						i--;
					}
				}
				System.out.println("건물용도에 따른 요율을 설정합니다.");
				double[] tmpRateOfUsageOfStructure = new double[6];
				for (int i = 0; i < eUsageOfStructure.values().length; i++) {
					System.out.printf(eUsageOfStructure.values()[i].getName()+" : ");
					try {
						tmpRateOfUsageOfStructure[i] = scn.nextDouble();
					} catch (InputMismatchException e) {
						System.out.println("error : 숫자를 입력해주세요");
						System.out.println("-----------------------");
						scn.nextLine();
						i--;
					}
				}
				((FireInsurance)newInsurance).setRateOfPostedPrice(tmpRateOfPostedPrice);
				((FireInsurance)newInsurance).setRateOfStructureUsage(tmpRateOfUsageOfStructure);
				break;
			case tripInsurance:
				System.out.println("여행국가의 위험정도에 따른 요율을 설정합니다.");
				double[] tmpRateOfRiskOfTripCountry = new double[4];
				for (int i = 0; i < eRiskOfTripCountry.values().length; i++) {
					System.out.printf(eRiskOfTripCountry.values()[i].getName()+" : ");
					try {
						tmpRateOfRiskOfTripCountry[i] = scn.nextDouble();
					} catch (InputMismatchException e) {
						System.out.println("error : 숫자를 입력해주세요");
						System.out.println("-----------------------");
						scn.nextLine();
						i--;
					}
				}
				((TripInsurance)newInsurance).setRateOfCountryRank(tmpRateOfRiskOfTripCountry);
				break;
			case actualCostInsurance:
				double selfBurden = 0;
				while (true) {
					System.out.printf("자기부담비율을 입력해주세요 ex) 0.3 : ");
					try {
						selfBurden = scn.nextDouble();
					} catch (InputMismatchException e) {
						System.out.println("error : 숫자를 입력해주세요");
						System.out.println("-----------------------");
						scn.nextLine();
						continue;
					}
					break;
				}
				((ActualCostInsurance)newInsurance).setSelfBurdenRate(selfBurden);
				break;
			default:
				break;
			}
			break;
		}
	
		// 보장내역 설정
		System.out.println("보장 내역을 설정합니다.");
		makeGuaranteePlan(newInsurance, false);
		
		// 특약 보장내역 설정
		if(newInsurance.isSpecialContract()) {
			System.out.println("특약 보장 내역을 설정합니다.");
			makeGuaranteePlan(newInsurance, true);
		}
		return newInsurance;
	}

	// 보장내역 설정하기
	private void makeGuaranteePlan(Insurance newInsurance, boolean special) {
		if (newInsurance.getType().equals(eInsuranceType.dentalInsurance)
				|| newInsurance.getType().equals(eInsuranceType.cancerInsurance)) {
			while (true) {
				try {
					System.out.println("보장을 원하시는 항목을 선택해주세요.");
					for (int i = 1; i <= newInsurance.getType().getGuaranteePlan().length; i++) {
						System.out.println(i + "." + newInsurance.getType().getGuaranteePlan()[i - 1]);
					}
					System.out.println("0.그만하기");
					int input = scn.nextInt();
					if (input > 0 && input <= newInsurance.getType().getGuaranteePlan().length) {
						int compensation;
						while (true) {
							try {
								System.out.println("해당 항목의 보상금액을 입력해주세요.");
								System.out.println(newInsurance.getType().getGuaranteePlan()[input - 1] + "의 보상금액 : ");
								compensation = scn.nextInt();
								break;
							} catch (InputMismatchException e) {
								System.out.println("error : 숫자를 입력해주세요");
								System.out.println("-----------------------");
							}
						}
						newInsurance.addGuaranteePlan(newInsurance.getType().getGuaranteePlan()[input - 1],
								compensation, special, 1);
					} else if (input == 0) {
						break;
					} else {
						System.out.println("error : 범위내의 숫자를 입력해주세요");
						System.out.println("-----------------------------");
					}
				}catch(InputMismatchException e) {
					System.out.println("error : 숫자를 입력해주세요");
					System.out.println("-----------------------");
					scn.nextLine();
				}
			}
		} else if (newInsurance.getType().equals(eInsuranceType.fireInsurance)
					|| newInsurance.getType().equals(eInsuranceType.tripInsurance)
					|| newInsurance.getType().equals(eInsuranceType.driverInsurance)) {
			while (true) {
				try {
					System.out.println("보장을 원하시는 항목을 선택해주세요.");
					for (int i = 1; i <= newInsurance.getType().getGuaranteePlan().length; i++) {
						System.out.println(i + "." + newInsurance.getType().getGuaranteePlan()[i - 1]);
					}
					System.out.println("0.그만하기");
					int input = scn.nextInt();
					if (input > 0 && input <= newInsurance.getType().getGuaranteePlan().length) {
						int compensation;
						while (true) {
							try {
								System.out.println("해당 항목의 최대 보상 금액을 입력해주세요.");
								System.out.println(newInsurance.getType().getGuaranteePlan()[input - 1] + "의 최대 보상금액 : ");
								compensation = scn.nextInt();
								break;
							} catch (InputMismatchException e) {
								System.out.println("error : 숫자를 입력해주세요");
								System.out.println("-----------------------");
							}
						}
						int rate = 0;
						while(true) {
							try {
									System.out.println("해당 항목의 자기부담율을 입력해주세요(1% ~ 99% 중 숫자만 입력 / ex) 자기부담 30% -> 30)");
									rate = scn.nextInt();
									if(rate > 0 && rate < 100) {
										break;
									}else {
										System.out.println("error : 범위내의 숫자를 입력해주세요");
										System.out.println("-----------------------------");
									}
							} catch(InputMismatchException e){
								System.out.println("error : 숫자를 입력해주세요");
								System.out.println("-----------------------");
							}
						}
						newInsurance.addGuaranteePlan(newInsurance.getType().getGuaranteePlan()[input - 1], compensation, special, rate);
					} else if (input == 0) {
						break;
					} else {
						System.out.println("error : 범위내의 숫자를 입력해주세요");
						System.out.println("-----------------------------");
					}
				}catch(InputMismatchException e) {
					System.out.println("error : 숫자를 입력해주세요");
					System.out.println("-----------------------");
					scn.nextLine();
				}
			}
		}
	}

	// 보험 확정하기
	private void confirmInsurance() {
		while (true) {
			System.out.printf("보험 확정하기를 진행하시겠습니까?(y/n) : ");
			String inputProgress = scn.next();
			if (inputProgress.equals("y")) {
				for (Insurance insurance: this.insuranceList.getInsuranceList()) {
					if (insurance.isConfirmedStatus()) {
						continue;
					}
					this.showInsuranceData(insurance);
					
				}
				System.out.printf("확정을 원하는 보험의 ID를 입력하세요 : ");
				String inputIndex = scn.next();
				boolean isExist = false;
				for (Insurance insurance: this.insuranceList.getInsuranceList()) {
					if (inputIndex.equals(insurance.getInsuranceId())) {
						isExist = true;
					}
				}
				if (isExist && this.insuranceList.getInsuranceList().get(Integer.parseInt(inputIndex)).isConfirmedStatus()) {
					System.out.println("이미 확정된 보험입니다. 다시 선택해주세요.");
					continue;
				} else if(isExist && !this.insuranceList.getInsuranceList().get(Integer.parseInt(inputIndex)).isConfirmedStatus()) {
					this.insuranceList.getInsuranceList().get(Integer.parseInt(inputIndex)).setConfirmedStatus(true);
					System.out.println("선택한 보험이 확정되었습니다!!!");
					continue;
				}
				else {
					System.out.println("존재하지 않는 ID입니다. 다시 선택해주세요.");
					continue;
				}
			} else if (inputProgress.equals("n")) {
				System.out.println("이전 화면으로 돌아갑니다.");
				break;
			} else {
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				continue;
			}
		}
	}
	
	
	// 보험 정보 출력
	private void showInsurance(Insurance insurance) {
		System.out.println(insurance.getInsuranceId()+". "+insurance.getName());
		System.out.println("  기본보험료 : "+insurance.getBasicFee());
	}
	// 보험 리스트 출력
	
	// 보험 상세정보 출력하기
	private void showInsuranceData(Insurance insurance) {
		System.out.println("------보험 상세정보------");
		System.out.println(insurance.getInsuranceId()+". "+insurance.getName());
		System.out.println("  기본보험료 : "+insurance.getBasicFee());
		System.out.println("  <나이 요율표>");
		for (int i = 0; i < eAge.values().length; i++) {
			System.out.println(eAge.values()[i] + " : " + insurance.getRateOfAge()[i]);
		}
		System.out.println("\n  <성별 요율표>");
		for (int i = 0; i < eGender.values().length-1; i++) {
			System.out.println(eAge.values()[i] + " : " + insurance.getRateOfGender()[i]);
		}
		System.out.println("\n  <직업 요율표>");
		for (int i = 0; i < eJob.values().length-1; i++) {
			System.out.println(eJob.values()[i] + " : " + insurance.getRateOfJob()[i]);
		}
		System.out.println("-------------------");
		
		// 보장내역 조회하는 것 코딩해야함
	}
}
