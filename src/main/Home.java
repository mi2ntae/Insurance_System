package main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import business.contract.Accident;
import business.contract.Contract;
import business.customer.Customer;
import business.customer.Insurant;
import business.employee.CompensationHandler;
import business.employee.ContractManager;
import business.employee.Employee;
import business.employee.InsuranceConfirmer;
import business.employee.InsuranceDeveloper;
import business.employee.Salesperson;
import business.employee.UnderWriter;
import business.insurance.ActualCostInsurance;
import business.insurance.CancerInsurance;
import business.insurance.DentalInsurance;
import business.insurance.DriverInsurance;
import business.insurance.FireInsurance;
import business.insurance.GuaranteePlan;
import business.insurance.Insurance;
import business.insurance.TripInsurance;
import business.interview.Interview;
import db.DBConnector;
import db.dao.AccidentDAO;
import db.dao.AccidentDAOImpl;
import db.dao.ContractDAO;
import db.dao.ContractDAOImpl;
import db.dao.CustomerDAO;
import db.dao.CustomerDAOImpl;
import db.dao.EmployeeDAO;
import db.dao.EmployeeDAOImpl;
import db.dao.InsuranceDAO;
import db.dao.InsuranceDAOImpl;
import db.dao.InsurantDAO;
import db.dao.InsurantDAOImpl;
import db.dao.InterviewDAO;
import db.dao.InterviewDAOImpl;
import global.Constants;
import global.Constants.eAge;
import global.Constants.eEmployeeRole;
import global.Constants.eFamilyMedicalDisease;
import global.Constants.eFamilyMedicalRelationship;
import global.Constants.eGender;
import global.Constants.eInsuranceType;
import global.Constants.eJob;
import global.Constants.eRankOfCar;
import global.Constants.eRiskOfTripCountry;
import global.Constants.eTypeOfCar;
import global.Constants.eUsageOfStructure;

public class Home {
	private Scanner scn;
		
	private DBConnector dbConnector;
	private CustomerDAO customerDAO;
	private ContractDAO contractDAO;
	private InsuranceDAO insuranceDAO;
	private EmployeeDAO employeeDAO;
	private InterviewDAO interviewDAO;
	private InsurantDAO insurantDAO;
	private AccidentDAO accidentDAO;
	
	private InsuranceDeveloper insuranceDeveloper;
	private InsuranceConfirmer insuranceConfirmer;
	private Salesperson salesperson;
	private UnderWriter underWriter;
	private ContractManager contractManager;
	private CompensationHandler compensationHandler;

	int time = Constants.thisYear * 100 + Constants.thisMonth;
	
	public Home(){
		this.scn = new Scanner(System.in);
		this.dbConnector = new DBConnector();
		this.customerDAO = new CustomerDAOImpl();
		this.contractDAO = new ContractDAOImpl();
		this.insuranceDAO = new InsuranceDAOImpl();
		this.employeeDAO = new EmployeeDAOImpl();
		this.interviewDAO = new InterviewDAOImpl();
		this.insurantDAO = new InsurantDAOImpl();
		this.accidentDAO = new AccidentDAOImpl();
	}
	
	public void initialize() {
		this.dbConnector.startDB();
		this.dbConnector.connect();
	}

	public void start() {
		while (true) {
			System.out.println("*******보험사 메뉴*******");
			System.out.println("1.고객");
			System.out.println("2.직원");
			System.out.println("3.시간++");
			System.out.println("0.시스템 종료");
			try {
				switch (scn.nextInt()) {
				case 1:
					customer: while (true) {
						System.out.println("*******고객 메뉴*******");
						System.out.println("1.로그인");
						System.out.println("2.회원가입");
						System.out.println("0.돌아가기");
						try {
							switch (scn.nextInt()) {
							case 1:
								System.out.println("ID를 입력하세요.");
								System.out.print("ID : ");
								String id = scn.next();
								System.out.println("비밀번호를 입력하세요.");
								System.out.print("비밀번호 : ");
								String pw = scn.next();
								Customer customer = this.loginCustomer(id, pw);
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
												Insurance insurance = null;
												while (insurance == null) {
													insurance = this.showAllInsurance();
													if(insurance != null) {
														String input = "";
														while(!input.equals("y") && !input.equals("n")) {
															System.out.printf("보험을 가입하시겠습니까?(y/n) : ");
															input = scn.next();
															if (input.equals("y")) {
																this.contractInsurance(customer, insurance);
																break;
															} else if (input.equals("n")) {
																break;
															} else {
																System.out.println("error : 정해진 문자를 사용해주세요");
																System.out.println("-----------------------");
																continue;
															}
														}
													}
												}
												break;
											case 2:
												break;
											case 3:
												Contract contract = this.showSubscribedInsurance(customer);
												if(contract != null) {
													System.out.println("원하시는 메뉴를 선택해주세요.");
													checkInsuranceList: while (true) {
														try {
															System.out.println("1.사고접수하기");
															System.out.println("2.보험료 납부내역 확인하기");
															System.out.println("3.보험 부활 신청하기");
															System.out.println("4.보험 재계약 신청하기");
															System.out.println("0.돌아가기");
															int input = scn.nextInt();
															switch (input) {
															case 1:
																this.submitAccident(contract);
																break;
															case 2:
																this.payFee(contract.getContractId());
																break;
															case 3:
																this.reviveContract(customer);
																break;
															case 4:
																this.requestReConract(contract);
																break;
															case 0:
																break checkInsuranceList;
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
											case 4:
												// 면담신청
												this.requestInterview(customer);
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
						System.out.println("0.돌아가기");
						try {
							switch (scn.nextInt()) {
							case 1:
								System.out.println("ID를 입력하세요.");
								System.out.print("ID : ");
								String id = scn.next();
								System.out.println("비밀번호를 입력하세요.");
								System.out.print("비밀번호 : ");
								String pw = scn.next();
								Employee employee = this.loginEmployee(id, pw);
								if (employee != null) {
									System.out.println("안녕하세요 " + employee.getName() + "님!");
									switch (employee.getEmployeeRole()) {
									case insuranceDeveloper:
										this.insuranceDeveloper = new InsuranceDeveloper(this.insuranceDAO);
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
													this.postManageInsurance();
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
										this.insuranceConfirmer = new InsuranceConfirmer(this.insuranceDAO);
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
													this.showPerformance(employee);
													break;
												case 2:
													// 면담신청 리스트 확인하기
													this.checkInterviewList(employee);
													break;
												case 3:
													this.showAllInsurance();
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
													this.sendPost();
													break;
												case 2:
													// 가입자 리스트 확인하기
													this.showSubscriberList();
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
													this.handleCompensation();
													break;
												case 2:
													this.payForCooperative();
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
				case 3 :
					if (Constants.thisMonth+1 > 12) {
						Constants.thisYear += 1;
						Constants.thisMonth = 1;
						time = Constants.thisYear * 100 + Constants.thisMonth;
						
						contractDAO.updateAnnualPayHistory();
					} else {
						time++;
						Constants.thisMonth += 1;
					}
					for(Contract contract : this.contractDAO.selectForTime()) {
						if(contract.getLifespan() - time < 0) {
							this.contractDAO.updateEffectiveness(contract.getContractId(), false);
						} else {
							if(contract.isEffectiveness()) {
								this.contractDAO.updateUnpaidPeriod(contract.getContractId(), contract.getUnpaidPeriod() + 1);
							}
							if (contract.getUnpaidPeriod() >= 3) {
								this.contractDAO.updateEffectiveness(contract.getContractId(), false);
							}
						}
					}
					if (!insuranceDAO.deleteInsuranceByTime()) {
						System.out.println("삭제 요청된 보험을 지울 수 없음. DB오류");
					}
					break;
				case 0:
					System.out.println("시스템을 종료합니다.");
					this.scn.close();
					this.dbConnector.close();
					System.exit(0);
					break;
				default:
					System.out.println("error : 범위 내의 숫자를 입력해주세요");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("error : 숫자를 입력해주세요");
				System.out.println("----------------------");
				scn.nextLine();
			}
		}
	}

	//우편물발송하기
	private void sendPost() {
		for(Customer customer : this.customerDAO.select()) {
			System.out.println("고객 ID : " + customer.getCustomerId());
			System.out.println("고객 이름 : " + customer.getName());
			System.out.println("고객 주소 : " + customer.getAddress());
			System.out.println("고객 전화번호 : " + customer.getPhoneNumber());
			System.out.println("--------------가입자 명단--------------");
			for(Insurant insurant : customer.getInsurantList()) {
				System.out.println("가입자 이름 : " + insurant.getName());
				System.out.println("가입자 성별 : " + insurant.getGender().getName());
				System.out.println("가입자 나이 : " + insurant.getAge());
				System.out.println("가입자 직업 : " + insurant.getJob().getName());
				System.out.println();
			}
			System.out.println("------------------------------------");
		}
		Customer customer = null;
		while(customer==null) {
			System.out.println("(이전으로 돌아가려면 0을 입력하세요)");
			System.out.printf("우편물을 보낼 고객의 ID를 입력하세요 : ");
			String input = this.scn.next();
			if(input.equals("0")) {
				return;
			}
			customer = this.customerDAO.selectCustomer(input);
			if(customer == null) {
				System.out.println("해당 ID의 고객이 존재하지 않습니다");
			}
		}
		String input = this.scn.nextLine();
		System.out.println("(이전으로 돌아가려면 0을 입력하세요)");
		System.out.printf("발신할 내용을 입력해 주세요 : ");
		input = this.scn.nextLine();
		if(input.equals("0")) {
			return;
		}
		System.out.println(customer.getAddress() + "(으)로 '" + input + "'의 내용의 우편이 발송되었습니다");
	}
	
	// 면담 신청하기
	private void requestInterview(Customer customer) {
		for(Interview interview : this.interviewDAO.select()) {
			if(interview.getCustomerId().equals(customer.getCustomerId()) && !interview.isConfirmedStatus()) {
				System.out.println("------이미 신청된 면담이 있습니다------");
				return;
			}
		}
		Interview interview = new Interview();
		interview.setCustomerId(customer.getCustomerId());
	
			int date = Integer.MIN_VALUE;
			while(date < time) {
				System.out.println("현재 날짜 : " + this.time);
				System.out.printf("원하는 달을 입력하세요 : ");
				try {
					date = scn.nextInt();
					if(date < time || (date % 100) > 12) {
						date = Integer.MIN_VALUE;
						System.out.println("범위 내의 숫자를 입력해주세요.");
						System.out.println("----------------------");
					}
				} catch (InputMismatchException e) {
					System.out.println("error : 숫자를 입력해주세요");
					System.out.println("----------------------");
					scn.nextLine();
				}
			}
			interview.setDate(Integer.toString(date));
			
			int temp = 0;
			System.out.printf("날짜를 입력해 주세요(1 ~ 30) : ");
			while(temp < 1 || temp > 30) {
				try {
					temp = this.scn.nextInt();
					if(temp < 1 || temp > 30) {
						System.out.println("error : 범위 내의 숫자를 입력해주세요");
						System.out.println("----------------------");
					}
				} catch (InputMismatchException e) {
					System.out.println("error : 숫자를 입력해주세요");
					System.out.println("----------------------");
					scn.nextLine();
				}
			}
			
			int interviewTime = Integer.MIN_VALUE;
			while(interviewTime < 0 || interviewTime > 4) {
				System.out.println("1.09-11시\n2.11시-13시\n3.13시-15시\n4.15시-17시\n0.돌아가기");
				try {
					interviewTime = scn.nextInt();
					if(interviewTime < 0 || interviewTime > 4) {
						System.out.println("error : 범위 내의 숫자를 입력해주세요");
					}
				}  catch (InputMismatchException e) {
					System.out.println("error : 숫자를 입력해주세요");
					System.out.println("----------------------");
					scn.nextLine();
				}
			}
			if(interviewTime == 0) {
				return;
			} else {
				switch(interviewTime) {
				case 1:
					interview.setTime(temp + "일 09시-11시");
					break;
				case 2:
					interview.setTime(temp + "일 11시-13시");
					break;
				case 3:
					interview.setTime(temp + "일 13시-15시");
					break;
				case 4:
					interview.setTime(temp + "일 15시-17시");
					break;
				}
			}
			if(this.interviewDAO.select().isEmpty()) {
				interview.setInterviewId("1");
			}else {
				interview.setInterviewId(Integer.toString(Integer.parseInt(this.interviewDAO.select().get(this.interviewDAO.select().size() - 1 ).getInterviewId()) + 1));
			}
			customer.requestInterview(this.interviewDAO,interview);	
			System.out.println("------면담 신청이 완료되었습니다------");
	}
	
	// 면담 신청 리스트 확인하기
	private void checkInterviewList(Employee employee) {
		this.salesperson = new Salesperson(this.interviewDAO);
		
		int count = 0;
		for(Interview interview : this.interviewDAO.select()) {
			if(!interview.isConfirmedStatus()) {
				count++;
				Customer customer = this.customerDAO.selectCustomer(interview.getCustomerId());
				System.out.println("주소 : " + customer.getAddress());
				System.out.println("고객 ID : " + customer.getCustomerId());
				System.out.println("이름 : " + customer.getName());
				System.out.println("전화번호 : " + customer.getPhoneNumber());
			}
		}
		if(count == 0) {
			System.out.println("------신청된 면담이 없습니다.------");
			return;
		}
		Customer customer = null;
		while(customer == null) {
			System.out.println("(이전으로 돌아가려면 0을 입력하세요)");
			System.out.printf("세부정보를 확인할 고객의 ID를 입력하세요 : ");
			String input = scn.next();
			if(input.equals("0")) {
				return;
			}
			customer = this.customerDAO.selectCustomer(input);
			if(customer == null) {
				System.out.println("--------존재하지 않은 고객입니다--------");
			} else {
				boolean flag = false;
				for(Interview interview : this.interviewDAO.select()) {
					if(interview.getCustomerId().equals(input)&& !interview.isConfirmedStatus()) {
						flag = true;
					}
				}
				if(flag) {
					System.out.println("주소 : " + customer.getAddress());
					System.out.println("고객 ID : " + customer.getCustomerId());
					System.out.println("이름 : " + customer.getName());
					System.out.println("전화번호 : " + customer.getPhoneNumber());
					System.out.println("------이전 면담 기록------");
					for(Interview interview : this.interviewDAO.select()) {
						if(interview.getCustomerId().equals(input) && interview.isConfirmedStatus()) {
							this.showInterviewData(interview);
						}
					}
					input = "";
					while(!input.equals("y") && !input.equals("n")) {
						System.out.print("면담결과 보고서를 작성하시겠습니까?(y/n) : ");
						input = scn.next();
						if(!input.equals("y") && !input.equals("n")) {
							System.out.println("error : 정해진 문자를 사용해주세요");
							System.out.println("------------------------------");	
						}
					}
					if(input.equals("y")) {
						Interview temp = null;
						for(Interview interview : this.interviewDAO.select()) {
							if(interview.getCustomerId().equals(customer.getCustomerId()) && !interview.isConfirmedStatus()) {
								temp = interview;
								
							}
						}
						this.writeReport(temp, employee);
						this.employeeDAO.updateSaleHistory(employee.getEmployeeId());
					} else {
						return;
					}
				} else {
					System.out.println("------면담을 신청하지 않은 고객입니다------");
					customer = null;
				}
			}
		}	
	}
	
	private void writeReport(Interview interview, Employee salesperson) {
		System.out.println("면담 내용을 입력하세요");
		String input = scn.nextLine() + "\n";
		System.out.print("내용 : ");
		input = scn.nextLine() + "\n";
		this.interviewDAO.updateSalespersonId(interview.getInterviewId(), salesperson.getEmployeeId());
		this.salesperson.writeReport(interview, input);
		System.out.println("------완료되었습니다------");
	}

	private void showInterviewData(Interview interview) {
		System.out.println("인터뷰 ID : " + interview.getInterviewId());
		System.out.println("영업사원 ID : " + interview.getSalespersonId());
		System.out.println("고객 ID : " + interview.getCustomerId());
		System.out.println("날짜 : " + interview.getDate());
		System.out.println("시간 : " + interview.getTime());
		System.out.println("내용 : " + interview.getContent());
	}
	
	// 가입자 리스트 보기
	private void showSubscriberList() {
		this.contractManager = new ContractManager(this.contractDAO);
		int unpaidCount = 0;
		int lifeCount = 0;
		for(Contract contract : this.contractDAO.select()) {
			if(contract.isEffectiveness() == true && !this.insuranceDAO.selectInsurance(contract.getInsuranceId()).isDel()) {
				if(contract.getUnpaidPeriod() > 0) {
					unpaidCount++;
				}
			} else {
				if(contract.getLifespan() - time < 0) {
					lifeCount++;
				}
			}
		}
		System.out.println("미납계약 수 : " + unpaidCount + " 만기계약 수 : " + lifeCount);
		boolean flag = true;
		while (flag) {
			try {
				System.out.println("1.미납계약 관리\n2.만기계약 관리\n0.돌아가기");
				int input = scn.nextInt();
				switch (input) {
				default :
					System.out.println("error : 범위 내의 숫자를 입력해주세요");
					break;
				case 0:
					return;
				case 1:
					flag = false;
					if(unpaidCount > 0) {
						this.manageUnpaidContract();
					} else {
						System.out.println("미납된 계약이 없습니다");
					}
					break;
				case 2:
					flag = false;
					if(lifeCount > 0) {
						this.ManageExpiredContract();
					} else {
						System.out.println("만기된 계약이 없습니다");
					}
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("error : 숫자를 입력해주세요");
				System.out.println("----------------------");
				scn.nextLine();
			}
		}
	}
	
	// 미납고객 관리
	private void manageUnpaidContract() {
		for(Contract contract : this.contractDAO.select()) {
			if(contract.isEffectiveness() == true && !this.insuranceDAO.selectInsurance(contract.getInsuranceId()).isDel()) {
				if(contract.getUnpaidPeriod() > 0) {
					Insurance insurance = insuranceDAO.selectInsurance(contract.getInsuranceId());
					Insurant insurant = insurantDAO.selectInsurant(contract.getInsurantId());
					Customer customer = customerDAO.selectCustomer(insurant.getCustomerId());
					System.out.println("계약 ID : " + contract.getContractId());
					System.out.println("고객 ID : " + customer.getCustomerId());
					System.out.println("고객 이름 : " + customer.getName());
					System.out.println("보험 이름 : " + insurance.getName());
					System.out.println("미납 금액 : " + contract.getFee() * contract.getUnpaidPeriod());
					System.out.println("미납된 월 수 : " + contract.getUnpaidPeriod());
					System.out.println();
				}
			}
		}
		ArrayList<Contract> tempList = new ArrayList<Contract>();
		boolean flag = false;
		
		while(true) {
			System.out.println("(이전으로 돌아가려면 0을 입력하세요)");
			System.out.printf("상세정보를 볼 계약 ID를 입력하세요 : ");
			String input = scn.next();
			if(input.equals("0")) return;
			Contract contract = this.contractDAO.selectContract(input);
			if(contract == null || contract.isEffectiveness() != true || contract.getUnpaidPeriod() == 0 || this.insuranceDAO.selectInsurance(contract.getInsuranceId()).isDel()) {
				System.out.println("해당 조건의 계약이 없습니다.");
				continue;
			}
			tempList.add(contract);
			Insurant insurant = this.insurantDAO.selectInsurant(contract.getInsurantId());
			Insurance insurance = this.insuranceDAO.selectInsurance(contract.getInsuranceId());
			this.showContractData(contract);
			this.showInsurantData(insurant, insurance.getType());
			break;
		}
		Contract contract = null;
		while(contract == null) {
			System.out.println("(이전으로 돌아가려면 0을 입력하세요)");
			System.out.printf("종료할 계약을 ID를 입력하세요 : ");
			String input = scn.next();
			if(input.equals("0")) return;
			contract = this.contractDAO.selectContract(input);
			if(contract == null) {
				System.out.println("해당 ID의 계약이 없습니다");
			} else {
				for(Contract temp : tempList) {
					if(contract.getContractId().equals(temp.getContractId())) {
						flag = true;
					}
				}
				if(!flag) {
					System.out.println("미납 계약이 아닙니다");
					contract = null;
					continue;
				}
			}
		}
		this.contractManager.manageUnpaidContract(contract);
		System.out.println("------보험계약을 종료시켰습니다------");
	}

	// 만기 계약 관리
	private void ManageExpiredContract() {
		for(Contract contract : this.contractDAO.select()) {
			if(contract.getLifespan() - time < 0 && !this.insuranceDAO.selectInsurance(contract.getInsuranceId()).isDel()) {
				this.showContractData(contract);
				System.out.println();
			}
		}
		Contract contract = null;
		while (contract == null) {
			System.out.println("(이전으로 돌아가려면 0을 입력하세요)");
			System.out.printf("관리할 계약 ID를 입력하세요 : ");
			String input = scn.next();
			if (input.equals("0")) {
				return;
			}
			contract = this.contractDAO.selectContract(input);
			if (contract == null) {
				System.out.println("해당 계약이 존재하지 않습니다");
			} else if(this.insuranceDAO.selectInsurance(contract.getInsuranceId()).isDel()){
				System.out.println("------삭제될 계약 입니다------");
				contract = null;
			}
		}
		this.showContractData(contract);
		Insurant insurant = this.insurantDAO.selectInsurant(contract.getInsurantId());
		Insurance insurance = this.insuranceDAO.selectInsurance(contract.getInsuranceId());
		this.showInsurantData(insurant, insurance.getType());
		
		// 우편보내기
		this.sendPost();
	}

	// 가입한 보험 리스트 보기
	private Contract showSubscribedInsurance(Customer customer) {
		int count = 0;
		
		for(Contract contract : contractDAO.select()) {
			if (contract.isEffectiveness()) {
				Insurant insurant = this.insurantDAO.selectInsurant(contract.getInsurantId());
				if(customer.getCustomerId().equals(insurant.getCustomerId())) {
					count++;
					this.showSimpleContract(contract, contract.isEffectiveness());
				}
			}
		}
		if(count == 0) {
			System.out.println("----------가입한 보험이 없습니다----------");
			return null;
		}
		
		Contract contract = null;
		while(true) {
			System.out.println("(이전으로 돌아가려면 0을 입력하세요)");
			System.out.printf("관리할 계약 ID를 입력하세요 : ");
			String input = scn.next();
			if(input.equals("0")) break;
			contract = this.contractDAO.selectContract(input);
			if(contract == null || !contract.isEffectiveness()) {
				System.out.println("찾으시는 ID를 갖는 계약이 없습니다.");
				continue;
			} else if(this.insuranceDAO.selectInsurance(contract.getInsuranceId()).isDel()) {
				System.out.println("------삭제될 계약 입니다-------");
				contract = null;
				continue;
			}
			if(!this.insurantDAO.selectInsurant(contract.getInsurantId()).getCustomerId().equals(customer.getCustomerId())) {
				System.out.println("찾으시는 ID를 갖는 계약이 없습니다.");
				contract = null;
				continue;
			}
			Insurance insurance = this.insuranceDAO.selectInsurance(contract.getInsuranceId());
			this.showInsuranceData(insurance);
			this.showContractData(contract);
			break;
		}
		return contract;
	}

	// 계약 간단한 정보 보기
	private void showSimpleContract(Contract contract, boolean judged) {
		Insurance insurance = insuranceDAO.selectInsurance(contract.getInsuranceId());
		Insurant insurant = insurantDAO.selectInsurant(contract.getInsurantId());
		System.out.println("계약ID : " + contract.getContractId());
		System.out.println("보험이름 : " + insurance.getName());
		System.out.println("가입자 이름 : " + insurant.getName());
		System.out.println("가입자 나이 : " + insurant.getAge());
		System.out.println("가입자 성별 : " + insurant.getGender().getName());
		if (judged) {
			System.out.println("보험료 : " +contract.getFee());
		} else {
			System.out.println("기본 보험료 : " + insurance.getBasicFee());
		}
		System.out.println("--------------------------");
	}
	
	// 보험 계약 심사하기
	private void judgeContract() {
		this.underWriter = new UnderWriter(contractDAO);
		int count = 0;
		ArrayList<Contract> tempList = new ArrayList<Contract>();
		boolean flag = false;
		for(Contract contract : this.contractDAO.select()) {
			if(contract.isEffectiveness() == false && contract.getLifespan() - time > 0 && !this.insuranceDAO.selectInsurance(contract.getInsuranceId()).isDel() && contract.getUnpaidPeriod() == 0) {
				this.showSimpleContract(contract, false);
				count++;
				tempList.add(contract);
			}
		}
		if(count == 0) {
			System.out.println("심사할 계약이 없습니다");
			return;
		}
		Contract contract = null;
		while(contract == null) {
			System.out.printf("상세정보를 볼 계약 ID를 입력하세요 : ");
			contract = this.contractDAO.selectContract(scn.next());
			
			if (contract != null) {
				for(Contract temp : tempList) {
					if(contract.getContractId().equals(temp.getContractId())) {
						flag = true;
					}
				}
				if(!flag) {
					System.out.println("심사할 계약이 아닙니다");
					contract = null;
					continue;
				}
				this.showContractData(contract);
				Insurant insurant = this.insurantDAO.selectInsurant(contract.getInsurantId());
				Insurance insurance = this.insuranceDAO.selectInsurance(contract.getInsuranceId());
				this.showInsurantData(insurant, insurance.getType());
				this.showInsuranceData(insurance);
				System.out.println("------보험료 산출정보------");
				System.out.println("산출 보험료 : "+insurance.calculateFee(insurant) + "원");
				int input = 0;
				while (input != 1 && input != 2) {
					try {
						System.out.println("1.승인\n2.거부");
						input = scn.nextInt();
						if (input != 1 && input != 2 && input != 0) {
							System.out.println("범위 내의 숫자를 입력해주세요");
						}
					} catch (InputMismatchException e) {
						System.out.println("error : 숫자를 입력해주세요");
						System.out.println("----------------------");
						scn.nextLine();
					}
				}
				switch (input) {
				case 1:
					underWriter.approveContract(contract);
					contract.connectContractDAO(this.contractDAO);
					contractDAO.updateFee(contract.getContractId(),insurance.calculateFee(insurant));
					for (int i = 0; i < Constants.thisMonth; i++) {
						contract.payFee(contract, i);
					}
					System.out.println("------승인 되었습니다------");
					break;
				case 2:
					underWriter.refuseContract(contract);
					System.out.println("------거부 되었습니다------");
					break;
				default:
					System.out.println("범위 내의 숫자를 입력해주세요.");
					break;
				}
					
			} else {
				System.out.println("심사 대상 계약이 아닙니다");
			}
		}
	}
	
	// 선택된 계약 보기
	private void showContractData(Contract contract) {
		System.out.println("------계약 상세정보------");
		System.out.println("계약 ID : " + contract.getContractId());
		System.out.println("보험 이름: " + this.insuranceDAO.selectInsurance(contract.getInsuranceId()).getName());
		System.out.println("가입자 ID : " + contract.getInsurantId());
		System.out.println("가입자 이름 : "+this.insurantDAO.selectInsurant(contract.getInsurantId()).getName());
		System.out.println("특약 여부 : " + contract.isSpecial());
		System.out.println("계약 기간 : " + contract.getLifespan());
		System.out.println("보험료 : " + contract.getFee());
		System.out.println("지불된 요금 : " + contract.getPaidFee());
		System.out.println("미납 기간 : " + contract.getUnpaidPeriod());
		System.out.println("----------------------");
	}
	
	private void showInsurantData(Insurant insurant, eInsuranceType insuranceType) {
		System.out.println("------보험 가입자 상세정보------");
		if(insuranceType == eInsuranceType.driverInsurance || insuranceType == eInsuranceType.dentalInsurance) {
			System.out.println("사고 기록 : " + insurant.getAccidentHistory());
		}
		System.out.println("주소 : " + insurant.getAddress());
		System.out.println("나이 : " + insurant.getAge());
		System.out.println("보험 가입자 ID : " + insurant.getInsurantId());
		System.out.println("이름 : " + insurant.getName());
		System.out.println("전화번호 : " + insurant.getPhoneNumber());
		if(insuranceType == eInsuranceType.fireInsurance) {
			System.out.println("공시지가 :" + insurant.getPostedPriceOfStructure());
			System.out.println("구조물 용도 : " + insurant.getUsageOfStructure().getName());
		}
		System.out.println("성별 : " + insurant.getGender().getName());
		System.out.println("직업 : " + insurant.getJob().getName());
		if(insuranceType == eInsuranceType.driverInsurance) {
			System.out.println("자동차 등급 : " + insurant.getRankOfCar().getName());
			System.out.println("자동차 종류 : " + insurant.getTypeOfCar().getName());
		}
		if(insuranceType == eInsuranceType.tripInsurance) {
			System.out.println("여행 국가 위험 등급 : " + insurant.getRiskOfTripCountry().getName());
		}
		if(insuranceType == eInsuranceType.cancerInsurance) {
			System.out.println("암 종류 : " + insurant.getFamilyMedicalDisease().getName());
			System.out.println("가족 촌수 : " + insurant.getFamilyMedicalRelationship().getName());
		}
		System.out.println("----------------------");
	}

	

	// 전체 보험 리스트 확인하기
	private Insurance showAllInsurance() {
		eInsuranceType type = null;
		while (type == null) {
			System.out.println("1.운전자 보험\n2.치아 보험\n3.실비 보험\n4.화재 보험\n5.암 보험\n6.여행 보험\n7.전체보기\n0.돌아가기");
			int input = 0;
			try {
				input = scn.nextInt();
				switch (input) {
				case 0:
					return null;
				case 1:
					type = eInsuranceType.driverInsurance;
					break;
				case 2:
					type = eInsuranceType.dentalInsurance;
					break;
				case 3:
					type = eInsuranceType.actualCostInsurance;
					break;
				case 4:
					type = eInsuranceType.fireInsurance;
					break;
				case 5:
					type = eInsuranceType.cancerInsurance;
					break;
				case 6:
					type = eInsuranceType.tripInsurance;
					break;
				case 7:
					break;
				default:
					System.out.println("error : 범위 내의 숫자를 입력해주세요");
					System.out.println("------------------------------");
					continue;
				}
				break;
			} catch (InputMismatchException e) {
				System.out.println("error : 숫자를 입력해주세요");
				System.out.println("----------------------");
				scn.nextLine();
			}
		}
		boolean flag = false;
		for (Insurance insurance : this.insuranceDAO.selectForConfirm()) {
			if (!insurance.isDel() && insurance.isConfirmedStatus()) {
				if (insurance.getType() == type) {
					flag = true;
				} else if (type == null) {
					flag = true;
				}
			}
		}
		if(flag) {
			System.out.println("-----------보험리스트-----------");
			if(type == null) {
				for (Insurance insurance: this.insuranceDAO.selectSimpleData()) {
					if(!insurance.isDel() && insurance.isConfirmedStatus()) {
						this.showSimpleInsuranceData(insurance);
					}
				}
			} else {
				for (Insurance insurance: this.insuranceDAO.selectSimpleData()) {
					if(insurance.getType() == type) {
						if(!insurance.isDel() && insurance.isConfirmedStatus()) {
							this.showSimpleInsuranceData(insurance);
						}
					}
				}
			}
			Insurance insurance = null;
			while(insurance == null) {
				System.out.printf("상세정보를 확인할 보험 ID를 입력하세요 : ");
				insurance = this.insuranceDAO.selectInsurance(scn.next());
				if(insurance != null) {
					if(!insurance.isDel() && insurance.isConfirmedStatus()) {
						this.showInsuranceData(insurance);
						break;
					}
				}
				System.out.println("------보험이 존재하지 않습니다------");
				insurance = null;
			}
			return insurance;
		} else {
			System.out.println("------보험이 존재하지 않습니다------");
			return null;
		}
	}
	
	// 간단한 보험 정보 보기 
	private void showSimpleInsuranceData(Insurance insurance) {
		System.out.println(insurance.getInsuranceId()+". "+insurance.getName());
		System.out.println("  기본보험료 : "+insurance.getBasicFee());
		System.out.println("-------------------------");
	}
	

	// 보험 가입하기
	private void contractInsurance(Customer customer, Insurance insurance) {
		if (insurance != null) {
			if (insurance.isConfirmedStatus() && !insurance.isDel()) {
				Insurant insurant = this.selectInsurant(customer, insurance);
				if (customer != null) {
					for (Contract temp : this.contractDAO.select()) {
						if (temp.getInsuranceId().equals(insurance.getInsuranceId())
								&& this.insurantDAO.selectInsurant(temp.getInsurantId()).getInsurantId()
										.equals(insurant.getInsurantId())) {
							if (temp.isEffectiveness()) {
								System.out.println("-----------이미 가입된 보험입니다-----------");
							} else {
								System.out.println("-----------이미 신청된 보험입니다-----------");
							}
							return;
						}
					}
					Contract contract = new Contract();
					int warrantyPeriod = insurance.getWarrantyPeriod();

					contract.setLifespan((warrantyPeriod / 12) * 100 + warrantyPeriod % 12 + time);
					// time
					ArrayList<String> ids = this.contractDAO.selectContractId();
					if (ids.isEmpty()) {
						contract.setContractId(Integer.toString(1));
					} else {
						int max = 0;
						for (String id : ids) {
							if (max < Integer.parseInt(id)) {
								max = Integer.parseInt(id);
							}
						}
						contract.setContractId(Integer.toString(max + 1));
					}
					// time
					String input = "";
					if (insurance.isSpecialContract()) {
						while (!input.equals("y") && !input.equals("n")) {
							System.out.printf("특약을 가입하시겠습니까?(y/n) : ");
							input = scn.next();
							if (!input.equals("y") && !input.equals("n")) {
								System.out.println("error : 정해진 문자를 사용해주세요");
								System.out.println("------------------------------");
							}
						}
						if (input.equals("y")) {
							contract.setSpecial(true);
						} else {
							contract.setSpecial(false);
						}
					} else {
						contract.setSpecial(false);
					}
					contract.joinInsurance(insurance, insurant);
					this.contractDAO.insert(contract);
					System.out.println("!!!!보험가입 신청이 완료되었습니다!!!!");
				}
			} else {
				System.out.println("해당 보험이 존재하지 않습니다");
			}
		} else {
			System.out.println("해당 보험이 존재하지 않습니다");
		}
	}

	// 보험 가입자 선택하기
		private Insurant selectInsurant(Customer customer, Insurance insurance) {
			customer = this.customerDAO.selectCustomer(customer.getCustomerId());
			Insurant selectedinsurant = null;
			System.out.println("1.보험가입자 선택\n2.보험가입자 생성");
			String input = scn.next();
			while (!input.equals("1") && !input.equals("2") ) {
				System.out.println("범위 내의 숫자를 입력해주세요.");
				System.out.println("1.보험가입자 선택\n2.보험가입자 생성");
				input = scn.next();
			}
			if (input.equals("1") && !customer.getInsurantList().isEmpty()) {
				for (Insurant insurant : customer.getInsurantList()) {
					if(insurant.getCustomerId().equals(customer.getCustomerId())) {
						this.showInsurantData(insurant, null);
					}
				}
				boolean flag = false;
				while (!flag) {
					System.out.printf("보험에 가입할 가입자 ID를 입력해주세요 : ");
					String InsurantId = scn.next();
					selectedinsurant = this.insurantDAO.selectInsurant(InsurantId);
					if (selectedinsurant != null) {
						if(selectedinsurant.getCustomerId().equals(customer.getCustomerId())) {
							flag = true;
						} else {
							System.out.println("해당 보험가입자가 존재하지 않습니다");
						}
					} else {
						System.out.println("해당 보험가입자가 존재하지 않습니다");
					}
				}
				switch (insurance.getType()) {
				case driverInsurance:
					if(selectedinsurant.getRankOfCar() == eRankOfCar.none && selectedinsurant.getTypeOfCar() == eTypeOfCar.none) {
						if (selectedinsurant.getRankOfCar() == eRankOfCar.none) {
							this.setRankOfCar(selectedinsurant);
							this.insurantDAO.updateRankOfCar(selectedinsurant.getInsurantId(), selectedinsurant.getRankOfCar().getNum());
						}
						if(selectedinsurant.getTypeOfCar() == eTypeOfCar.none) {
							this.setTypeOfCar(selectedinsurant);
							this.insurantDAO.updateTypeOfCar(selectedinsurant.getInsurantId(), selectedinsurant.getTypeOfCar().getNum());
						}
						this.setAccidentHistory(selectedinsurant);
						this.insurantDAO.updateAccidentHistory(selectedinsurant.getInsurantId(), selectedinsurant.getAccidentHistory());
					} else {
						System.out.println("----------기존의 가입자 정보로 가입합니다----------");
					}
					break;
				case dentalInsurance:
					break;
				case actualCostInsurance:
					if(selectedinsurant.getJob() == eJob.none) {
						this.setJob(selectedinsurant);
						this.insurantDAO.updateJob(selectedinsurant.getInsurantId(), selectedinsurant.getJob().getNum());
					} else {
						System.out.println("----------기존의 가입자 정보로 가입합니다----------");
					}
					break;
				case fireInsurance:
					if(selectedinsurant.getUsageOfStructure() == eUsageOfStructure.none) {
						this.setPostedPriceAndUseage(selectedinsurant);
						this.insurantDAO.updatePostedPriceOfStructure(selectedinsurant.getInsurantId(), selectedinsurant.getPostedPriceOfStructure());
						this.insurantDAO.updateUsageOfStructure(selectedinsurant.getInsurantId(), selectedinsurant.getUsageOfStructure().getNum());
					} else {
						System.out.println("----------기존의 가입자 정보로 가입합니다----------");
					}
					break;
				case cancerInsurance:
					if (selectedinsurant.getFamilyMedicalDisease() == eFamilyMedicalDisease.none
							&& selectedinsurant.getFamilyMedicalRelationship() == eFamilyMedicalRelationship.none) {
						if (selectedinsurant.getJob() == eJob.none) {
							this.setJob(selectedinsurant);
							this.insurantDAO.updateJob(selectedinsurant.getInsurantId(), selectedinsurant.getJob().getNum());
						}
						if (selectedinsurant.getFamilyMedicalDisease() == eFamilyMedicalDisease.none
								&& selectedinsurant.getFamilyMedicalRelationship() == eFamilyMedicalRelationship.none) {
							this.setDiseaseAndRelationship(selectedinsurant);
							this.insurantDAO.updateFamilyMedicalDisease(selectedinsurant.getInsurantId(), selectedinsurant.getFamilyMedicalDisease().getNum());
							this.insurantDAO.updateFamilyMedicalRelationship(selectedinsurant.getInsurantId(), selectedinsurant.getFamilyMedicalRelationship().getNum());
						}
					} else {
						System.out.println("----------기존의 가입자 정보로 가입합니다----------");
					}
					break;
				case tripInsurance:
					if (selectedinsurant.getRiskOfTripCountry() == eRiskOfTripCountry.none) {
						this.setRiskOfTripCountry(selectedinsurant);
						this.insurantDAO.updateRiskOfTripCountry(selectedinsurant.getInsurantId(), selectedinsurant.getRiskOfTripCountry().getNum());
						
					} else {
						System.out.println("----------기존의 가입자 정보로 가입합니다----------");
					}
					break;
				}
			} else {
				if (input.equals("1") && customer.getInsurantList().isEmpty()) {
					System.out.println("가입자가 존재하지 않아 가입자를 생성합니다");
				}
				selectedinsurant = this.createInsurant(customer, insurance);
			}
			return selectedinsurant;
		}

		private void setRankOfCar(Insurant insurant) {
			System.out.println("자동차 등급\n1.최고급(1억원 이상)\n2.고급(1억원 미만 ~ 7천만원 이상)\n3.보급형(7천만원 미만 ~ 4천만원 이상)\n4.저가(4천만원 이하)");
			System.out.printf("자동차 등급을 입력해 주세요 : ");
			eRankOfCar rankOfCar = null;
			while (rankOfCar == null) {
				try {
					switch (scn.nextInt()) {
					default:
						System.out.println("error : 범위 내의 숫자를 입력해주세요");
						System.out.println("------------------------------");
						break;
					case 1:
						rankOfCar = eRankOfCar.Luxury;
						break;
					case 2:
						rankOfCar = eRankOfCar.high;
						break;
					case 3:
						rankOfCar = eRankOfCar.middle;
						break;
					case 4:
						rankOfCar = eRankOfCar.low;
						break;
					}
				} catch (InputMismatchException e) {
					System.out.println("error : 숫자를 입력해주세요");
					System.out.println("-----------------------");
					scn.nextLine();
				}
			}
			insurant.setRankOfCar(rankOfCar);
		}
		
		private void setTypeOfCar(Insurant insurant) {
			System.out.println("자동차 종류\n1.버스\n2.승합차\n3.SUV\n4.세단\n5.기타");
			System.out.printf("자동차 종류를 입력해 주세요 : ");
			eTypeOfCar typeOfCar = null;
			while(typeOfCar == null) {
				try {
					switch(scn.nextInt()) {
					default :
						System.out.println("error : 범위 내의 숫자를 입력해주세요");
						System.out.println("------------------------------");
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
				} catch (InputMismatchException e) {
					System.out.println("error : 숫자를 입력해주세요");
					System.out.println("-----------------------");
					scn.nextLine();
				}
			}
			insurant.setTypeOfCar(typeOfCar);
		}
		
		private void setAccidentHistory(Insurant insurant) {
			int accidentHistory = Integer.MIN_VALUE;
			while (accidentHistory < 0) {
				try {
					System.out.printf("사고횟수 : ");
					accidentHistory = scn.nextInt();
					insurant.setAccidentHistory(accidentHistory);
					if (accidentHistory < 0) {
						System.out.print("error : 양의 정수를 입력해주세요");
						System.out.println("-----------------------");
					}
				} catch (InputMismatchException e) {
					System.out.println("error : 숫자를 입력해주세요");
					System.out.println("-----------------------");
					scn.nextLine();
				}
			}
			insurant.setAccidentHistory(accidentHistory);
		}
		
		private void setRiskOfTripCountry(Insurant insurant) {
			System.out.println("여행국가 위험등급\n1.안전\n2.1등급\n3.2등급\n4.3등급");
			System.out.printf("위험등급을 입력해 주세요 : ");
			eRiskOfTripCountry riskOfTripCountry = null;
			while(riskOfTripCountry == null) {
				try {
					switch(scn.nextInt()) {
					default :
						System.out.println("error : 범위 내의 숫자를 입력해주세요");
						System.out.println("------------------------------");
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
				} catch (InputMismatchException e) {
					System.out.println("error : 숫자를 입력해주세요");
					System.out.println("-----------------------");
					scn.nextLine();
				}
			}
			insurant.setRiskOfTripCountry(riskOfTripCountry);
		}
		
		private void setJob(Insurant insurant) {
			System.out.println("직업\n1.사무직\n2.운전자\n3.현장직\n4.학생\n5.교사\n6.군인\n7.기타");
			eJob job = null;
			while (job == null) {
				try {
					System.out.printf("직업을 입력해 주세요 : ");
					switch (scn.nextInt()) {
					default:
						System.out.println("error : 범위 내의 숫자를 입력해주세요");
						System.out.println("------------------------------");
						break;
					case 1:
						job = eJob.officeWorker;
						break;
					case 2:
						job = eJob.driver;
						break;
					case 3:
						job = eJob.factoryWorker;
						break;
					case 4:
						job = eJob.student;
						break;
					case 5:
						job = eJob.teacher;
						break;
					case 6:
						job = eJob.soldier;
						break;
					case 7:
						job = eJob.etc;
						break;
					}
				} catch (InputMismatchException e) {
					System.out.println("error : 숫자를 입력해주세요");
					System.out.println("-----------------------");
					scn.nextLine();
				}
				insurant.setJob(job);
			}
		}
		
		private void setPostedPriceAndUseage(Insurant insurant) {
			long postedPriceOfStructure = Long.MIN_VALUE;
			try {
				while(postedPriceOfStructure < 0) {
					System.out.print("건물가격 : ");
					postedPriceOfStructure = scn.nextLong();
					if(postedPriceOfStructure < 0) {
						System.out.println("error : 양의 정수를 입력해주세요");
						System.out.println("-----------------------");
					}
				}
				insurant.setPostedPriceOfStructure(postedPriceOfStructure);
			} catch (InputMismatchException e) {
				System.out.println("error : 숫자를 입력해주세요");
				System.out.println("-----------------------");
				scn.nextLine();
			}
			
			System.out.println("건물용도\n1.집\n2.학원\n3.공장\n4.창고\n5.사무실\n6.공공시설");
			eUsageOfStructure usageOfStructure = null;
			while (usageOfStructure == null) {
				try {
					System.out.printf("건물의 용도를 입력해주세요 : ");
					switch (scn.nextInt()) {
					default:
						System.out.println("error : 범위 내의 숫자를 입력해주세요");
						System.out.println("------------------------------");					
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
				} catch (InputMismatchException e) {
					System.out.println("error : 숫자를 입력해주세요");
					System.out.println("-----------------------");
					scn.nextLine();
				}
			}
			insurant.setUsageOfStructure(usageOfStructure);
		}
		
		private void setDiseaseAndRelationship(Insurant insurant) {
			while(true) {
				System.out.printf("가족 중에 암에 걸린사람이 있습니까?(y/n) : ");
				String input = this.scn.next();
				if(input.equals("y")) {
					eFamilyMedicalDisease familyMedicalDisease = null;
					while(familyMedicalDisease == null) {
						System.out.println("암 종류\n1.갑상선암\n2.고환암\n3.난소암\n4.식도암\n5.폐암");
						System.out.printf("암 종류를 입력해 주세요 : ");
						try {
							switch(scn.nextInt()) {
							default :
								System.out.println("error : 범위 내의 숫자를 입력해주세요");
								System.out.println("------------------------------");
								break;
							case 1:
								familyMedicalDisease = eFamilyMedicalDisease.thyroid;
								break;
							case 2:
								familyMedicalDisease = eFamilyMedicalDisease.testicular;
								break;
							case 3:
								familyMedicalDisease = eFamilyMedicalDisease.ovarian;
								break;
							case 4:
								familyMedicalDisease = eFamilyMedicalDisease.esophageal;
								break;
							case 5:
								familyMedicalDisease = eFamilyMedicalDisease.lung;
								break;
							}
						} catch (InputMismatchException e) {
							System.out.println("error : 숫자를 입력해주세요");
							System.out.println("-----------------------");
							scn.nextLine();
						}
					}
					insurant.setFamilyMedicalDisease(familyMedicalDisease);
					
					eFamilyMedicalRelationship familyMedicalRelationship = null;
					while(familyMedicalRelationship == null) {
						System.out.println("가족 촌수\n1.1촌\n2.2촌\n3.3촌\n4.4촌");
						System.out.printf("촌수를 입력해 주세요 : ");
						try {
							switch(scn.nextInt()) {
							default :
								System.out.println("error : 범위 내의 숫자를 입력해주세요");
								System.out.println("------------------------------");
								break;
							case 1:
								familyMedicalRelationship = eFamilyMedicalRelationship.one;
								break;
							case 2:
								familyMedicalRelationship = eFamilyMedicalRelationship.two;
								break;
							case 3:
								familyMedicalRelationship = eFamilyMedicalRelationship.three;
								break;
							case 4:
								familyMedicalRelationship = eFamilyMedicalRelationship.four;
								break;
							}
						} catch (InputMismatchException e) {
							System.out.println("error : 숫자를 입력해주세요");
							System.out.println("-----------------------");
							scn.nextLine();
						}
					}
					insurant.setFamilyMedicalRelationship(familyMedicalRelationship);
					break;
				} else if(input.equals("n")) {
					insurant.setFamilyMedicalDisease(eFamilyMedicalDisease.none);
					insurant.setFamilyMedicalRelationship(eFamilyMedicalRelationship.none);
					break;
				} else {
					System.out.println("error : 정해진 문자를 사용해주세요");
					System.out.println("------------------------------");	
				}
			}
		}

		private Insurant createInsurant(Customer customer, Insurance insurance) {
			Insurant insurant = new Insurant();
			
			System.out.print("이름 : ");
			String name = scn.next();
			insurant.setName(name);
			
			int age = Integer.MIN_VALUE;
			while(age < 0) {
				try {
					System.out.print("나이 : ");
					age = scn.nextInt();
					if(age < 0) {
						System.out.print("설정할 수 없는 나이입니다");
					}
				} catch (InputMismatchException e) {
					System.out.println("error : 숫자를 입력해주세요");
					System.out.println("-----------------------");
					scn.nextLine();
				}
			}
			insurant.setAge(age);
			
			scn.nextLine();
			System.out.print("주소 : ");
			String address = scn.nextLine();
			insurant.setAddress(address);

			
			if(this.insurantDAO.select().isEmpty()) {
				insurant.setInsurantId("1");
			} else {
				int max = 0;
				for(Insurant temp : this.insurantDAO.select()) {
					if(max < Integer.parseInt(temp.getInsurantId())) {
						max = Integer.parseInt(temp.getInsurantId());
					}
				}
				insurant.setInsurantId(Integer.toString(max + 1));
			}
			
			System.out.print("전화번호 : ");
			String phoneNum = scn.next();
			insurant.setPhoneNumber(phoneNum);
			
			System.out.println("성별\n1.남자\n2.여자");
			eGender gender = null;
			while(gender == null) {
				try {
					System.out.printf("성별을 입력해주세요 : ");
					switch(scn.nextInt()) {
						default :
							System.out.println("error : 범위 내의 숫자를 입력해주세요");
							System.out.println("------------------------------");
							break;
						case 1 :
							gender = eGender.male;
							break;
						case 2 :
							gender = eGender.female;
							break;
					}
				} catch (InputMismatchException e) {
					System.out.println("error : 숫자를 입력해주세요");
					System.out.println("-----------------------");
					scn.nextLine();
				}
			}
			insurant.setGender(gender);
			
			if(insurance.getType() == eInsuranceType.fireInsurance) {
				this.setPostedPriceAndUseage(insurant);
			} else {
				insurant.setPostedPriceOfStructure(0);
				insurant.setUsageOfStructure(eUsageOfStructure.none);
			}

			if (insurance.getType() != eInsuranceType.fireInsurance) {
				this.setJob(insurant);
			} else {
				insurant.setJob(eJob.none);
			}
			
			if(insurance.getType() == eInsuranceType.cancerInsurance) {
				this.setDiseaseAndRelationship(insurant);
			} else {
				insurant.setFamilyMedicalDisease(eFamilyMedicalDisease.none);
				insurant.setFamilyMedicalRelationship(eFamilyMedicalRelationship.none);
			}
			
			if(insurance.getType() == eInsuranceType.driverInsurance) {
				this.setRankOfCar(insurant);
				this.setTypeOfCar(insurant);
				this.setAccidentHistory(insurant);
			} else {
				insurant.setRankOfCar(eRankOfCar.none);
				insurant.setTypeOfCar(eTypeOfCar.none);
				insurant.setAccidentHistory(0);
			}
			
			if(insurance.getType() == eInsuranceType.tripInsurance) {
				this.setRiskOfTripCountry(insurant);
			} else {
				insurant.setRiskOfTripCountry(eRiskOfTripCountry.none);
			}
			insurant.setCustomerId(customer.getCustomerId());
			this.insurantDAO.insert(insurant);
			return insurant;
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
			if(this.customerDAO.selectCustomer(input) != null) {
				System.out.println("이미 존재하는 ID입니다!(사용불가)");
				System.out.println("--------------------------");
				continue check;
			}
			customer.setCustomerId(input);
			break;
		}

		System.out.println("비밀번호를 입력해주세요.");
		customer.setPassword(scn.next());
		
		if(customerDAO.insert(customer)) {
			System.out.println("!!!회원가입이 완료되었습니다!!!!");
		}
	}
	// 고객 로그인하기
	private Customer loginCustomer(String id, String pw) {
		Customer customer = this.customerDAO.selectCustomerByIdPw(id, pw);
		if(customer != null) {
			System.out.println("!!!로그인에 성공하였습니다!!!!");
			return customer;
		}else {
			System.out.println("error : ID 또는 비밀번호가 틀립니다!");
			return null;
		}
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
					scn.nextLine();
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
			if(this.employeeDAO.selectEmployee(input) != null) {
				System.out.println("이미 존재하는 ID입니다!(사용불가)");
				System.out.println("--------------------------");
				continue check;
			}
			employee.setEmployeeId(input);
			break;
		}

		System.out.println("비밀번호를 입력해주세요.");
		employee.setPassword(scn.next());

		if (employeeDAO.insert(employee)) {
			System.out.println("!!!회원가입이 완료되었습니다!!!!");
		}
	}

	// 직원 로그인하기
	private Employee loginEmployee(String id, String pw) {
		Employee employee = this.employeeDAO.selectEmployeeByIdPw(id, pw);
		if(employee != null) {
			System.out.println("!!!로그인에 성공하였습니다!!!!");
			return employee;
		}else {
			System.out.println("error : ID 또는 비밀번호가 틀립니다!");
			return null;
		}
	}

	// 보험 만들기
	private void createInsurance() {
		Insurance insurance = null;
		while (true) {
			System.out.println("\n1.운전자 보험\n2.치아 보험\n3.실비 보험\n4.화재 보험\n5.암 보험\n6.여행 보험\n0.돌아가기");
			System.out.println("어떤 보험을 기획하시겠습니까?");
			int inputMenu = 0;
			try {
				inputMenu = scn.nextInt();
				if (inputMenu == 0) {
					System.out.println("이전으로 돌아갑니다");
					return;
				} else if (inputMenu > 6) {
					System.out.println("범위 내의 숫자를 입력해주세요.");
					continue;
				}
				insurance = this.insuranceDeveloper.designInsurance(insurance, inputMenu);
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
			if (this.createDetailInsurance(insurance)) {
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
			System.out.println("가입 가능 성별을 입력하세요.");
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
					System.out.println("이전으로 돌아갑니다.");
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
			
			returnInsurance:while (true) {
				System.out.printf("동일한 조건의 보험들을 확인하시겠습니까?(y/n) : ");
				String inputCondition = scn.next();
				if (inputCondition.equals("y")) {
					ArrayList<Insurance> insuranceList = this.insuranceDAO.select();
					boolean isEmpty = true;
					for (Insurance insurance: insuranceList) {
						if ((insurance.getType() != newInsurance.getType()) || (insurance.getGender() != newInsurance.getGender())) {
							continue;
						} else if ((!insurance.isConfirmedStatus()) || (insurance.isDel())) {
							continue;
						}
						
						System.out.println(insurance.getInsuranceId()+". "+insurance.getName());
						System.out.println("  기본보험료 : "+insurance.getBasicFee());
						System.out.println("-------------------");
						isEmpty = false;
					}
					if (isEmpty) {
						System.out.println("확인할 수 있는 보험이 존재하지 않습니다. 세부설정 단계로 넘어갑니다.");
						break returnInsurance;
					} else{
						while (true) {
							System.out.println("(적용을 원하는 보험이 없으면 0을 입력하세요)");
							System.out.printf("세부설정을 확인하고싶은 보험의 ID를 입력하세요 : ");
							String inputIndex = scn.next();
							if (inputIndex.equals("0")) {
								System.out.println("보험 설계가 완료되었습니다. 세부설정 단계로 넘어갑니다.");
								break returnInsurance;
							}
							boolean isExist = false;
							Insurance tmpInsurance = null;
							for (Insurance insurance: insuranceList) {
								if (inputIndex.equals(insurance.getInsuranceId()) && !insurance.isDel()) {
									if (insurance.isConfirmedStatus() && insurance.getType() == newInsurance.getType()) {
										isExist = true;
										tmpInsurance = insurance;
									}
								}
							}
							if (isExist) {
								this.showInsuranceData(tmpInsurance);
								System.out.printf("해당 보험의 기본요율정보(나이,성별,직업)로 설정하시겠습니까?(y/n) : ");
								String inputDecision = scn.next();
								if (inputDecision.equals("y")) {
									newInsurance.setRateOfAge(tmpInsurance.getRateOfAge());
									newInsurance.setRateOfGender(tmpInsurance.getRateOfGender());
									newInsurance.setRateOfJob(tmpInsurance.getRateOfJob());
									newInsurance.setClone(true);
									System.out.println("선택한 보험의 기본요율정보로 설정했습니다. 세부설정 단계로 넘어갑니다.");
									break returnInsurance;
								} else if (inputDecision.equals("n")) {
									continue;
								} else {
									System.out.println("잘못 입력하셨습니다.");
									continue;
								}
							} else {
								System.out.println("존재하지 않는 ID입니다. 다시 입력해주세요.");
								continue;
							}
						}
					}
				} else if (inputCondition.equals("n")) {
					System.out.println("보험 설계가 완료되었습니다. 세부설정 단계로 넘어갑니다.");
					break returnInsurance;
				} else {
					System.out.println("잘못 입력하셨습니다.");
					continue returnInsurance;
				}
			}
			return newInsurance;
		}
	}
	
	// 보험 세부설정하기
	private boolean createDetailInsurance(Insurance newInsurance) {
		String newId;
		int max = 0;
		ArrayList<String> insuranceIdList = this.insuranceDAO.selectInsuranceId();
		if (insuranceIdList.isEmpty()) {
			newId = "1";
		} else {
			for(String insuranceId : insuranceIdList) {
				if(max < Integer.parseInt(insuranceId)) {
					max = Integer.parseInt(insuranceId);
				}
			}
			newId = Integer.toString(max+1);
		}
		newInsurance.setInsuranceId(newId);

		
		while (true) {
			System.out.printf("만드실 보험의 이름을 입력해주세요 : ");
			scn.nextLine();
			newInsurance.setName(scn.nextLine());
			while(true) {
				try {
					int inputFee = 0;
					System.out.printf("기본 보험료를 입력해주세요 : ");
					inputFee = scn.nextInt();
					if (inputFee < 0) {
						System.out.println("0이상의 숫자를 입력해주세요.");
						continue;
					}
					newInsurance.setBasicFee(inputFee);
					break;
				} catch (InputMismatchException e) {
					System.out.println("error : 숫자를 입력해주세요");
					System.out.println("-----------------------");
					scn.nextLine();
					continue;
				}
			}
			if (newInsurance.getType() != eInsuranceType.actualCostInsurance) {
				special: while (true) {
					System.out.printf("특약가입이 가능한 보험입니까?(y/n) : ");
					String inputSpecial = scn.next();
					if (inputSpecial.equals("y")) {
						newInsurance.setSpecialContract(true);
						while (true) {
							try {
								int inputFee = 0;
								System.out.printf("특약 보험료를 입력해주세요 : ");
								inputFee = scn.nextInt();
								if (inputFee < 0) {
									System.out.println("0이상의 숫자를 입력해주세요.");
									continue;
								}
								newInsurance.setSpecialContractFee(inputFee);
							} catch (InputMismatchException e) {
								System.out.println("error : 숫자를 입력해주세요");
								System.out.println("-----------------------");
								scn.nextLine();
								continue;
							}
							break;
						}
					} else if (inputSpecial.equals("n")) {
						newInsurance.setSpecialContract(false);
						newInsurance.setSpecialContractFee(0);
					} else {
						System.out.println("error : 정해진 문자를 사용해주세요");
						System.out.println("-----------------------");
						continue special;
					}
					break;
				}
			}
	
			while (true) {
				System.out.printf("보장 기간을 입력해주세요(연단위) : ");
				try {
					int inputPeriod = scn.nextInt();
					if (inputPeriod >= 80 || inputPeriod <= 0) {
						System.out.println("보장기간은 1~79년 사이에서 설정해주세요");
						continue;
					}
					newInsurance.setWarrantyPeriod(inputPeriod * 12);
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
			double inputDouble = 0;
			for (int i = 0; i < eAge.values().length; i++) {
				try {
					System.out.printf(eAge.values()[i].getName() + " : ");
					inputDouble = scn.nextDouble();
					if (inputDouble < 0) {
						System.out.println("0이상의 숫자를 입력해주세요.");
						scn.nextLine();
						i--;
						continue;
					}
					tmpRateOfAge[i] = inputDouble;
				} catch (InputMismatchException e) {
					System.out.println("error : 숫자를 입력해주세요");
					System.out.println("-----------------------");
					scn.nextLine();
					i--;
				}
			}
			newInsurance = this.insuranceDeveloper.setDetailOfInsurance(newInsurance, "age", tmpRateOfAge);
			
			double[] tmpRateOfGender = new double[eGender.values().length - 1];
			System.out.println("성별에 대한 요율을 설정합니다. ex) 남자 : 1.0)");
			for (int i = 0; i < eGender.values().length - 1; i++) {
				try {
					System.out.printf(eGender.values()[i].getName() + " : ");
					inputDouble = scn.nextDouble();
					if (inputDouble < 0) {
						System.out.println("0이상의 숫자를 입력해주세요.");
						scn.nextLine();
						i--;
						continue;
					}
					tmpRateOfGender[i] = inputDouble;
				} catch (InputMismatchException e) {
					System.out.println("error : 숫자를 입력해주세요");
					System.out.println("-----------------------");
					scn.nextLine();
					i--;
				}
			}
			newInsurance = this.insuranceDeveloper.setDetailOfInsurance(newInsurance, "gender", tmpRateOfGender);
			
			double[] tmpRateOfJob = new double[eJob.values().length];
			System.out.println("직업에 대한 요율을 설정합니다. ex) 직장 : 1.0)");
			
			for (int i = 1; i < eJob.values().length; i++) {
				try {
					System.out.printf(eJob.values()[i].getName() + " : ");
					inputDouble = scn.nextDouble();
					if (inputDouble < 0) {
						System.out.println("0이상의 숫자를 입력해주세요.");
						scn.nextLine();
						i--;
						continue;
					}
					tmpRateOfJob[i - 1] = inputDouble;
				} catch (InputMismatchException e) {
					System.out.println("error : 숫자를 입력해주세요");
					System.out.println("-----------------------");
					scn.nextLine();
					i--;
				}
			}
			newInsurance = this.insuranceDeveloper.setDetailOfInsurance(newInsurance, "job", tmpRateOfJob);
		} 
		
		while (true) {
			double inputDouble = 0;
			switch (newInsurance.getType()) {
			case cancerInsurance:
				System.out.println("가족 병력에 따른 요율을 설정합니다.");
				double[] tmpRateOfFamilyDisease = new double[eFamilyMedicalDisease.values().length - 1];
				for (int i = 0; i < eFamilyMedicalDisease.values().length - 1; i++) {
					System.out.printf(eFamilyMedicalDisease.values()[i + 1].getName()+" : ");
					try {
						inputDouble = scn.nextDouble();
						if (inputDouble < 0) {
							System.out.println("0이상의 숫자를 입력해주세요.");
							scn.nextLine();
							i--;
							continue;
						}
						tmpRateOfFamilyDisease[i] = inputDouble;
					} catch (InputMismatchException e) {
						System.out.println("error : 숫자를 입력해주세요");
						System.out.println("-----------------------");
						scn.nextLine();
						i--;
					}
				}
				System.out.println("병력이 있는 가족과의 관계에 따른 요율을 설정합니다.");
				double[] tmpRateOfFamilyRelationship = new double[Constants.eFamilyMedicalRelationship.values().length - 1];
				for (int i = 0; i < Constants.eFamilyMedicalRelationship.values().length - 1; i++) {
					System.out.printf(Constants.eFamilyMedicalRelationship.values()[i + 1].getName()+" : ");
					try {
						inputDouble = scn.nextDouble();
						if (inputDouble < 0) {
							System.out.println("0이상의 숫자를 입력해주세요.");
							scn.nextLine();
							i--;
							continue;
						}
						tmpRateOfFamilyRelationship[i] = inputDouble;
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
						if (annuallimit < 0) {
							System.out.println("0이상의 숫자를 입력해주세요.");
							continue;
						}
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
				System.out.println("자동차 종류에 따른 요율을 설정합니다.");
				double[] tmpRateOfCarType = new double[eTypeOfCar.values().length-1];
				for (int i = 1; i < eTypeOfCar.values().length-1; i++) {
					System.out.printf(eTypeOfCar.values()[i+1].getName()+" : ");
					try {
						inputDouble = scn.nextDouble();
						if (inputDouble < 0) {
							System.out.println("0이상의 숫자를 입력해주세요.");
							scn.nextLine();
							i--;
							continue;
						}
						tmpRateOfCarType[i-1] = inputDouble;
					} catch (InputMismatchException e) {
						System.out.println("error : 숫자를 입력해주세요");
						System.out.println("-----------------------");
						scn.nextLine();
						i--;
					}
				}
				System.out.println("자동차 등급에 따른 요율을 설정합니다.");
				double[] tmpRateOfRankOfCar = new double[eRankOfCar.values().length-1];
				for (int i = 1; i < eRankOfCar.values().length; i++) {
					System.out.printf(eRankOfCar.values()[i].getName()+" : ");
					try {
						inputDouble = scn.nextDouble();
						if (inputDouble < 0) {
							System.out.println("0이상의 숫자를 입력해주세요.");
							scn.nextLine();
							i--;
							continue;
						}
						tmpRateOfRankOfCar[i-1] = inputDouble;
					} catch (InputMismatchException e) {
						System.out.println("error : 숫자를 입력해주세요");
						System.out.println("-----------------------");
						scn.nextLine();
						i--;
					}
				}
				System.out.println("사고횟수에 따른 요율을 설정합니다.");
				double[] tmpRateOfAccidentHistory = new double[Constants.accidentHistory.length];
				for (int i = 0; i < Constants.accidentHistory.length; i++) {
					System.out.printf(Constants.accidentHistory[i]+" : ");
					try {
						inputDouble = scn.nextDouble();
						if (inputDouble < 0) {
							System.out.println("0이상의 숫자를 입력해주세요.");
							scn.nextLine();
							i--;
							continue;
						}
						tmpRateOfAccidentHistory[i] = inputDouble;
					} catch (InputMismatchException e) {
						System.out.println("error : 숫자를 입력해주세요");
						System.out.println("-----------------------");
						scn.nextLine();
						i--;
					}
				}
				((DriverInsurance)newInsurance).setRateOfCarType(tmpRateOfCarType);
				((DriverInsurance)newInsurance).setRateOfCarRank(tmpRateOfRankOfCar);
				((DriverInsurance)newInsurance).setRateOfAccidentHistory(tmpRateOfAccidentHistory);
				break;
			case fireInsurance:
				System.out.println("공시가격에 따른 요율을 설정합니다.");
				double[] tmpRateOfPostedPrice = new double[Constants.postedPrice.length];
				for (int i = 0; i < Constants.postedPrice.length; i++) {
					System.out.printf(Constants.postedPrice[i]+" : ");
					try {
						inputDouble = scn.nextDouble();
						if (inputDouble < 0) {
							System.out.println("0이상의 숫자를 입력해주세요.");
							scn.nextLine();
							i--;
							continue;
						}
						tmpRateOfPostedPrice[i] = inputDouble;
					} catch (InputMismatchException e) {
						System.out.println("error : 숫자를 입력해주세요");
						System.out.println("-----------------------");
						scn.nextLine();
						i--;
					}
				}
				System.out.println("건물용도에 따른 요율을 설정합니다.");
				double[] tmpRateOfUsageOfStructure = new double[eUsageOfStructure.values().length-1];
				for (int i = 1; i < eUsageOfStructure.values().length; i++) {
					System.out.printf(eUsageOfStructure.values()[i].getName()+" : ");
					try {
						inputDouble = scn.nextDouble();
						if (inputDouble < 0) {
							System.out.println("0이상의 숫자를 입력해주세요.");
							scn.nextLine();
							i--;
							continue;
						}
						tmpRateOfUsageOfStructure[i - 1] = inputDouble;
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
				double[] tmpRateOfRiskOfTripCountry = new double[eRiskOfTripCountry.values().length-1];
				for (int i = 1; i < eRiskOfTripCountry.values().length; i++) {
					System.out.printf(eRiskOfTripCountry.values()[i].getName()+" : ");
					try {
						inputDouble = scn.nextDouble();
						if (inputDouble < 0) {
							System.out.println("0이상의 숫자를 입력해주세요.");
							scn.nextLine();
							i--;
							continue;
						}
						tmpRateOfRiskOfTripCountry[i - 1] = inputDouble;
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
				int rate = 0;
				while (true) {
					try {
						System.out.println("자기부담율을 입력해주세요(1% ~ 99% 중 숫자만 입력 / ex) 자기부담 30% -> 30)");
						rate = scn.nextInt();
						if (rate > 0 && rate < 100) {
							break;
						} else {
							System.out.println("error : 범위내의 숫자를 입력해주세요");
							System.out.println("-----------------------------");
						}
					} catch (InputMismatchException e) {
						System.out.println("error : 숫자를 입력해주세요");
						System.out.println("-----------------------");
						scn.nextLine();
					}
				}
				((ActualCostInsurance) newInsurance).setSelfBurdenRate((double)rate/100);
				break;
			default:
				break;
			}
			break;
		}
		
		this.insuranceDeveloper.finishInsurance(newInsurance);
		if (newInsurance.getType() == eInsuranceType.actualCostInsurance) {
			return true;
		}
		// 보장내역 설정
		boolean[] tmpt = new boolean[10];
		System.out.println("보장 내역을 설정합니다.");
		makeGuaranteePlan(newInsurance, false, tmpt);
		
		// 특약 보장내역 설정
		if(newInsurance.isSpecialContract()) {
			System.out.println("특약 보장 내역을 설정합니다.");
			makeGuaranteePlan(newInsurance, true, tmpt);
		}
		return true;
	}

	// 보장내역 설정하기
	private void makeGuaranteePlan(Insurance newInsurance, boolean special, boolean[] tmpt) {
		while (true) {
			try {
				System.out.println("보장을 원하시는 항목을 선택해주세요.");
				for (int i = 1; i <= newInsurance.getType().getGuaranteePlan().length; i++) {
					System.out.println(i + "." + newInsurance.getType().getGuaranteePlan()[i - 1]);
				}
				System.out.println("0.보장내역 설정 완료하기");
				int input = scn.nextInt();
				
				if (input > 0 && input <= newInsurance.getType().getGuaranteePlan().length) {
					if(tmpt[input]) {
						System.out.println("error : 이미 추가한 보장내역 입니다.");
						System.out.println("-------------------------------");
						continue;
					}
					int compensation;
					switch(newInsurance.getType()) {
					case dentalInsurance:
					case cancerInsurance:
						while (true) {
							try {
								System.out.println("해당 항목의 보상금액을 입력해주세요.");
								System.out.print(newInsurance.getType().getGuaranteePlan()[input - 1] + "의 보상금액 : ");
								compensation = scn.nextInt();
								tmpt[input] = true;
								break;
							} catch (InputMismatchException e) {
								System.out.println("error : 숫자를 입력해주세요");
								System.out.println("-----------------------");
							}
						}
						newInsurance.addGuaranteePlan(newInsurance.getType().getGuaranteePlan()[input - 1], compensation, special, 1);
						break;
					case fireInsurance:
					case tripInsurance:
					case driverInsurance:
						while (true) {
							try {
								System.out.println("해당 항목의 최대 보상 금액을 입력해주세요.");
								System.out.print(newInsurance.getType().getGuaranteePlan()[input - 1] + "의 최대 보상금액 : ");
								compensation = scn.nextInt();
								tmpt[input] = true;
								break;
							} catch (InputMismatchException e) {
								System.out.println("error : 숫자를 입력해주세요");
								System.out.println("-----------------------");
								scn.nextLine();
							}
						}
						int rate = 0;
						while(true) {
							try {
									System.out.println("해당 항목의 자기부담율을 입력해주세요(0% ~ 99% 중 숫자만 입력 / ex) 자기부담 30% -> 30)");
									System.out.print("자기부담율 : ");
									rate = scn.nextInt();
									if(rate >= 0 && rate < 100) {
										break;
									}else {
										System.out.println("error : 범위내의 숫자를 입력해주세요");
										System.out.println("-----------------------------");
									}
							} catch(InputMismatchException e){
								System.out.println("error : 숫자를 입력해주세요");
								System.out.println("-----------------------");
								scn.nextLine();
							}
						}
						newInsurance.addGuaranteePlan(newInsurance.getType().getGuaranteePlan()[input - 1], compensation, special, (1-((double)rate/100)));
						break;
					default:
						break;
					}
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

	// 보험 확정하기
	private void confirmInsurance() {
		this.showInsurance(false);
		confirm:while (true) {
			System.out.println("\n(이전으로 돌아가려면 0을 입력하세요)");
			System.out.printf("상세정보를 확인할 보험의 ID를 입력하세요 : ");
			String inputIndex = scn.next();
			if (inputIndex.equals("0")) {
				return;
			}
			boolean isExist = false;
			Insurance tmpInsurance = null;
			for (Insurance insurance : this.insuranceDAO.selectForConfirm()) {
				if (inputIndex.equals(insurance.getInsuranceId()) && !insurance.isDel()) {
					isExist = true;
					tmpInsurance = insurance;
				}
			}
			
			if (isExist && tmpInsurance.isConfirmedStatus()) {
				System.out.println("이미 확정된 보험입니다. 다시 입력해주세요.");
				continue;
			} else if (isExist && !tmpInsurance.isConfirmedStatus()) {
				this.showInsuranceData(tmpInsurance);
				while(true) {
					System.out.printf("해당 보험을 확정하시겠습니까?(y/n) : ");
					String inputDecision = scn.next();
					if (inputDecision.equals("y")) {
						this.insuranceConfirmer.confirmInsurance(tmpInsurance);
						System.out.println("선택한 보험이 확정되었습니다!!!");
						return;
					} else if (inputDecision.equals("n")) {
						continue confirm;
					} else {
						System.out.println("잘못 입력하셨습니다.");
						continue;
					}
				}
			} else {
				System.out.println("존재하지 않는 ID입니다. 다시 입력해주세요.");
				continue;
			}
		}
	}
	
	// 보상 처리
	private void handleCompensation() {
		this.compensationHandler = new CompensationHandler();
		menu : while (true) {
			int cnt = 0;
			for(Accident accident : this.accidentDAO.select()) {
				Contract contract = this.contractDAO.selectContract(accident.getContractId());
				Insurant insurant = this.insurantDAO.selectInsurant(contract.getInsurantId());
				if(!accident.isHandlingStatus()) {
					System.out.println(accident.getAccidentId() + "." + insurant.getName() + " "
							+ insurant.getAge() + " " + insurant.getGender().getName()
							+ " " + insurant.getName() + " " + accident.getContent() + " " + accident.getDamageCost());
					cnt++;
				}
			}
			if(cnt == 0) {
				System.out.println("현재 보상처리를 해야하는 사고 건이 존재하지 않습니다.");
				break menu;
			}
			
			System.out.println("---------------------------");
			System.out.println("(이전으로 돌아가려면 0을 입력하세요)");
			System.out.println("처리를 원하시는 계약의 ID를 입력해주세요.");
			while (true) {
				String input = scn.next();
				if (input.equals("0")) {
					System.out.println("보상 처리를 종료합니다.");
					break menu;
				}
				
				Accident accident = this.accidentDAO.selectAccident(input);
				
				if (!accident.isHandlingStatus()) {
					Contract contract = this.contractDAO.selectContract(accident.getContractId());
					Insurant insurant = this.insurantDAO.selectInsurant(contract.getInsurantId());
					Insurance insurance = insuranceDAO.selectInsurance(contract.getInsuranceId());
					System.out.println("------계약 상세정보------");
					System.out.println("가입자 이름 : " + insurant.getName());
					System.out.println("가입자 나이 : " + insurant.getAge());
					System.out.println("가입자 성별 : " + insurant.getGender().getName());
					System.out.println("가입자 직업 : " + insurant.getJob().getName());
					System.out.println("보험 이름 : " + insurance.getName());
					System.out.println("손해정보 : " + accident.getContent());
					System.out.println("요청 보상금 : " + accident.getDamageCost());
					System.out.println("| 근거 | ");
					showSelectedGuaranteePlan(insurance, accident.getContent());
					System.out.println("-----------------------");
					int tmptCompensation = accident.getDamageCost();
					String tmptCause = null;
					menu2: while (true) {
						System.out.println("1.보상금 조정하기");
						System.out.println("2.보험료 갱신하기");
						System.out.println("3.보상금 확정,지급하기");
						System.out.println("0.돌아가기");
						try {
							int input2 = scn.nextInt();
							switch (input2) {
							case 1:
								while (true) {
									System.out.println("현재 보상금 : " + tmptCompensation);
									System.out.println("원하시는 보상금과 사유를 적어주세요.");
									System.out.print("보상금 : ");
									try {
										tmptCompensation = scn.nextInt();
										scn.nextLine();
										break;
									} catch (InputMismatchException e) {
										System.out.println("error : 숫자를 입력해주세요");
										System.out.println("-----------------------");
										scn.nextLine();
									}
								}
								System.out.println("사유 :");
								tmptCause = scn.nextLine() + "\n";
								System.out.println("보상금이 변경되었습니다!");
								break;
							case 2:
								renewInsuranceFee(contract);
								break;
							case 3:
								System.out.print("이대로 보상금을 확정하시고 지급하시겠습니까?(y/n) : ");
								String input3 = scn.next();
								if (input3.equals("y")) {
									compensationHandler.confirmCompensation(accident, tmptCompensation);
									accident.confirmCompensation();
									accident.setCause(tmptCause);
									System.out.println("보상금 " + tmptCompensation + "원을 " + insurant.getName() + "님께 지급하였습니다!");
									break menu;
								} else if (input3.equals("n")) {
								} else {
									System.out.println("잘못 입력하셨습니다. 이전으로 돌아갑니다.");
								}
								break;
							case 0:
								break menu2;
							default:
								System.out.println("error : 범위 내의 숫자를 입력해주세요");
								System.out.println("-----------------------");
								break;
							}
						} catch (InputMismatchException e) {
							System.out.println("error : 숫자를 입력해주세요");
							System.out.println("-----------------------");
							scn.nextLine();
						}
					}
				}
				System.out.println("(이전으로 돌아가려면 0을 입력하세요)");
				System.out.println("찾으시는 ID를 갖는 계약이 없습니다. 처리를 원하시는 보상처리건의 ID를 입력해주세요.");
			}
		}
	}
		
		// 보장내역 단일 출력 : 보상 처리
		private void showSelectedGuaranteePlan(Insurance insurance, String content) {
			insurance.birngGuaranteePlan();
			switch (insurance.getType()) {
			case dentalInsurance:
			case cancerInsurance:
				for (GuaranteePlan guaranteePlan : insurance.getGuaranteePlanList()) {
					if (guaranteePlan.getContent().equals(content)) {
						System.out.println("  보장금액 : " + guaranteePlan.getCompensation());
						System.out.print("특약여부 : ");
						if(guaranteePlan.isSpecial()) System.out.println("O");
						else System.out.println("X");
					}
				}
				break;
			case fireInsurance:
			case tripInsurance:
			case driverInsurance:
				for (GuaranteePlan guaranteePlan : insurance.getGuaranteePlanList()) {
					if (guaranteePlan.getContent().equals(content)) {
						System.out.println("  최대 보장금액 : " + guaranteePlan.getCompensation());
						System.out.println("  보장비율 : 피해액의 " + (int) (100 * guaranteePlan.getRate()) + "%");
						System.out.print("특약여부 : ");
						if(guaranteePlan.isSpecial()) System.out.println("O");
						else System.out.println("X");
					}
				}
				break;
			case actualCostInsurance:
				System.out.println("병ㆍ의원 및 약국에서 실제로 지출한 의료비의 "
						+ (int) (100 * (1 - ((ActualCostInsurance) insurance).getSelfBurdenRate())) + "%");
				break;
			}
		}
		
		// 보험료 갱신하기
		private void renewInsuranceFee(Contract contract) {
			Insurant insurant = insurantDAO.selectInsurant(contract.getInsurantId());
			System.out.println("----<보험 가입자 정보>----");
			System.out.println("이름 : " + insurant.getName());
			System.out.println("나이 : " + insurant.getAge());
			System.out.println("성별 : " + insurant.getGender().getName());
			System.out.println("직업 : " + insurant.getJob().getName());
			System.out.println("-------<계약 정보>-------");
			System.out.println("보험료 : " + contract.getFee() + "원");
			System.out.print("특약여부 : ");
			if(contract.isSpecial()) System.out.println("O");
			else System.out.println("X");
			System.out.println("-----<보상처리 리스트>-----");
			contract.bringAccident();
			int cnt = 0;
			for(Accident accident : contract.getAccidentList()) {
				if(accident.isHandlingStatus()) {
					if(cnt == 0)System.out.println("ID     보상정보            보상금액");
					System.out.printf("%-7s%-14s%d원\n",accident.getAccidentId(),accident.getContent(),accident.getCompensation());
					cnt++;
				}
			}
			if(cnt == 0) System.out.println("해당 고객의 보상처리 정보가 존재하지 않습니다.");
			System.out.println("-----------------------");
			
			while (true) {
				System.out.print("해당 가입자의 보험료를 갱신하시겠습니까?(y/n) : ");
				String input = scn.next();
				if (input.equals("y")) {
					System.out.println("보험료를 입력해주세요.");
					System.out.print("보험료 : ");
					try {
						contract.setFee(scn.nextInt());
						System.out.println("보험료가 갱신되었습니다!");
						this.contractDAO.updateFee(contract.getContractId(), contract.getFee());
						break;
					} catch (InputMismatchException e) {
						System.out.println("error : 숫자를 입력해주세요");
						System.out.println("-----------------------");
						scn.nextLine();
					}
				} else if (input.equals("n")) {
					break;
				} else {
					System.out.println("error : 정해진 문자를 사용해주세요");
				}
			}
			
		}
		
		// 협력업체 비용 지불
		private void payForCooperative() {
			int fee = 0;
			menu : while(true) {
				try {
					System.out.println("협력 비용을 지불할 업체를 골라주세요.");
					System.out.println("1.병원");
					System.out.println("2.신용정보회사");
					System.out.println("3.긴급출동업체");
					System.out.println("0.돌아가기");
					switch(scn.nextInt()) {
					case 1:
						fee = showCooperative("제일병원", "서울특별시 서대문구", "010-4354-4353", 1000000);
						break;
					case 2:
						fee = showCooperative("GOOD지키미 신용정보회사", "서울특별시 마포구", "010-1010-1112", 2000000);
						break;
					case 3:
						fee = showCooperative("고고카 서비스", "서울특별시 종로구", "010-1566-3000", 30000);
						break;
					case 0:
						break menu;
					default:
						System.out.println("error : 범위 내의 숫자를 입력해주세요");
						System.out.println("-----------------------");
						continue menu;
					}
					System.out.print("비용 " + fee + "원을 협력업체에 지불 하시겠습니까? (y/n) : ");
					String input = scn.next();
					if (input.equals("y")) {
						System.out.println("비용 총 " + fee + "원이 지불되었습니다!");
					} else if (input.equals("n")) {
						System.out.println("취소되었습니다.");
						break;
					} else {
						System.out.println("error : 정해진 문자를 사용해주세요");
					}
					break menu;
				}catch(InputMismatchException e) {
					System.out.println("error : 숫자를 입력해주세요");
					System.out.println("-----------------------");
					scn.nextLine();
				}
			}
		}
		
		// 협력업체 정보 출력 : 협력업체 비용 지불
		private int showCooperative(String name, String address, String phoneNum, int fee) {
			System.out.println("------<협력업체 정보>------");
			System.out.println("업체명 : " + name);
			System.out.println("주소 : " + address);
			System.out.println("대표 전화번호 : " + phoneNum);
			System.out.println("비용 : " + fee);
			System.out.println("-----------------------");
			return fee;
		}
		
		// 사고 접수
		private void submitAccident(Contract contract) {
			Insurance insurance = insuranceDAO.selectInsurance(contract.getInsuranceId());
			System.out.println(insurance.getInsuranceId() + ". " + insurance.getName());
			showGuaranteePlan(insurance, contract.isSpecial());
			System.out.println("-----------------");
			String accidentId;
			
			contract.bringAccident();
			if(contract.getAccidentList().size() == 0) accidentId = contract.getContractId() + "001";
			else accidentId = String.valueOf(Integer.parseInt(contract.getAccidentList().get(contract.getAccidentList().size() - 1).getAccidentId()) + 1);
			switch(insurance.getType()) {
			case actualCostInsurance:
				System.out.println("병ㆍ의원 및 약국에서 지출하신 의료비를 입력해주세요");
				while (true) {
					try {
						System.out.println("의료비 : ");
						int damageCost = scn.nextInt();
						contract.addAccident(accidentId, "의료실손", damageCost, false);
						break;
					} catch(InputMismatchException e) {
						System.out.println("error : 숫자를 입력해주세요");
						System.out.println("-----------------------");
						scn.nextLine();
					}
				}
				break;
				default:
				roop : while (true) {
					System.out.println("(이전으로 돌아가려면 0을 입력하세요)");
					System.out.println("보험금을 청구하실 보장내역의 항목 번호를 입력해주세요.");
					
					try {
						int input = scn.nextInt();
						int index = 0;
						if(input == 0) break roop;
						for (GuaranteePlan guaranteePlan : insurance.getGuaranteePlanList()) {
							if(index == input - 1 && (contract.isSpecial() == guaranteePlan.isSpecial() || guaranteePlan.isSpecial() == false)) {
								Accident accident = new Accident();
								int damageCost = 0;
								switch(insurance.getType()) {
								case cancerInsurance:
								case dentalInsurance:
									damageCost = guaranteePlan.getCompensation();
									break;
								case driverInsurance:
								case fireInsurance:
								case tripInsurance:
									roop2 : while (true) {
										try {
											System.out.println("해당 항목에 대한 피해금액을 입력해주세요.");
											damageCost = scn.nextInt();
											damageCost *= guaranteePlan.getRate();
											if (damageCost > guaranteePlan.getCompensation()) {
												System.out.println("*입력하신 피해금액에 대한 보장금액(" + damageCost + "원)이 최대 보장금액("
														+ guaranteePlan.getCompensation() + "원)을 넘겨 최대 보장금액으로 청구가 진행됩니다.");
												damageCost = guaranteePlan.getCompensation();
											}
											accident.setDamageCost(damageCost);
											break roop2;
										} catch (InputMismatchException e) {
											System.out.println("error : 숫자를 입력해주세요");
											System.out.println("-----------------------");
											scn.nextLine();
										}
									}
									break;
								default:
									break;
								}
								contract.addAccident(accidentId, guaranteePlan.getContent(), damageCost, false);
								System.out.println("선택하신 항목(" + guaranteePlan.getContent() + ")에 대한 보험금("+ damageCost +"원)이 청구되었습니다!");
								break roop;
							}
							index++;
						}
					} catch(InputMismatchException e) {
						System.out.println("error : 숫자를 입력해주세요");
						System.out.println("-----------------------");
						scn.nextLine();
					}
				}
				break;
			}
		}
		
		// 보장내역 리스트 출력 : 사고접수, 보험 상세정보 출력하기
		private void showGuaranteePlan(Insurance insurance, boolean special) {
			System.out.println("  <보장 내역>");
			switch (insurance.getType()) {
			case dentalInsurance:
			case cancerInsurance:
				int index = 1;
				System.out.println("*기본계약");
				for (GuaranteePlan guaranteePlan : insurance.getGuaranteePlanList()) {
					if (!guaranteePlan.isSpecial()) {
						System.out.println(index + ".보장내용 : " + guaranteePlan.getContent());
						System.out.println("  보장금액 : " + guaranteePlan.getCompensation());
						index++;
					}
				}
				System.out.println("*선택특약");
				for (GuaranteePlan guaranteePlan : insurance.getGuaranteePlanList()) {
					if (guaranteePlan.isSpecial() && special) {
						System.out.println(index + ".보장내용 : " + guaranteePlan.getContent());
						System.out.println("  보장금액 : " + guaranteePlan.getCompensation());
						index++;
					}
				}
				break;
			case fireInsurance:
			case tripInsurance:
			case driverInsurance:
				int index2 = 1;
				System.out.println("*기본계약");
				for (GuaranteePlan guaranteePlan : insurance.getGuaranteePlanList()) {
					if (!guaranteePlan.isSpecial()) {
						System.out.println(index2 + ".보장내용 : " + guaranteePlan.getContent());
						System.out.println("  최대 보장금액 : " + guaranteePlan.getCompensation());
						System.out.println("  보장비율 : 피해액의 " + (int) (100 * guaranteePlan.getRate()) + "%");
						index2++;
					}
				}
				System.out.println("*선택특약");
				for (GuaranteePlan guaranteePlan : insurance.getGuaranteePlanList()) {
					if (guaranteePlan.isSpecial() && special) {
						System.out.println(index2 + ".보장내용 : " + guaranteePlan.getContent());
						System.out.println("  최대 보장금액 : " + guaranteePlan.getCompensation());
						System.out.println("  보장비율 : 피해액의 " + (int) (100 * guaranteePlan.getRate()) + "%");
						index2++;
					}
				}
				break;
			case actualCostInsurance:
				System.out.println("병ㆍ의원 및 약국에서 실제로 지출한 의료비의 "
						+ (int) (100 * (1 - ((ActualCostInsurance) insurance).getSelfBurdenRate())) + "%");
				break;
			}
		}
		
	
	// 보험 정보 출력
	private void showInsurance(boolean confirmStatus) {
		System.out.println("------보험 정보------");
		for (Insurance insurance: this.insuranceDAO.select()) {
			if (confirmStatus == insurance.isConfirmedStatus() && !insurance.isDel()) {
				System.out.println(insurance.getInsuranceId()+". "+insurance.getName());
				System.out.println("  기본보험료 : "+insurance.getBasicFee());
				System.out.println("-------------------");
			}
		}
	}
		
	// 보험 상세정보 출력하기
	private void showInsuranceData(Insurance insurance) {
		System.out.println("ID : " + insurance.getInsuranceId()+"     이름 :"+insurance.getName());
		System.out.println("  기본보험료 : "+insurance.getBasicFee());
		System.out.println("  <나이 요율표>");
		for (int i = 0; i < eAge.values().length; i++) {
			System.out.println(eAge.values()[i].getName() + " : " + insurance.getRateOfAge()[i]);
		}
		System.out.println("\n  <성별 요율표>");
		for (int i = 0; i < eGender.values().length-1; i++) {
			System.out.println(eGender.values()[i].getName() + " : " + insurance.getRateOfGender()[i]);
		}
		System.out.println("\n  <직업 요율표>");
		for (int i = 0; i < eJob.values().length-1; i++) {
			System.out.println(eJob.values()[i + 1].getName() + " : " + insurance.getRateOfJob()[i]);
		}
		switch (insurance.getType()) {
		case driverInsurance:
			System.out.println("\n  <자동차종류 요율표>");
			for (int i = 0; i < eTypeOfCar.values().length - 1; i++) {
				System.out.println(eTypeOfCar.values()[i + 1].getName()+" : "+((DriverInsurance)insurance).getRateOfCarType()[i]);
			}
			System.out.println("\n  <자동차등급 요율표>");
			for (int i = 0; i < eRankOfCar.values().length - 1; i++) {
				System.out.println(eRankOfCar.values()[i + 1].getName()+" : "+((DriverInsurance)insurance).getRateOfCarRank()[i]);
			}
			System.out.println("\n  <사고횟수 요율표>");
			for (int i = 0; i < Constants.accidentHistory.length; i++) {
				System.out.println(Constants.accidentHistory[i]+" : "+((DriverInsurance)insurance).getRateOfAccidentHistory()[i]);
			}
			break;
		case dentalInsurance:
			System.out.println("\n  <연간 한도 횟수>");
			System.out.println(((DentalInsurance)insurance).getAnnualLimitCount()+"회");
			break;
		case actualCostInsurance:
			System.out.println("\n  <자기부담 비율>");
			System.out.println(((ActualCostInsurance)insurance).getSelfBurdenRate());
			break;
		case fireInsurance:
			System.out.println("\n  <공시가격 요율표>");
			for (int i = 0; i < Constants.postedPrice.length; i++) {
				System.out.println(Constants.postedPrice[i]+" : "+((FireInsurance)insurance).getRateOfPostedPrice()[i]);
			}
			System.out.println("\n  <건축물 용도 요율표>");
			for (int i = 0; i < eUsageOfStructure.values().length - 1; i++) {
				System.out.println(eUsageOfStructure.values()[i + 1].getName()+" : "+((FireInsurance)insurance).getRateOfStructureUsage()[i]);
			}
			break;
		case cancerInsurance:
			System.out.println("\n  <가족병력 요율표>");
			for (int i = 0; i <	eFamilyMedicalDisease.values().length - 1; i++) {
				System.out.println(eFamilyMedicalDisease.values()[i + 1].getName()+" : "+((CancerInsurance)insurance).getRateOfFamilyMedicalDisease()[i]);
			}
			System.out.println("\n  <병력이 있는 가족과의 관계 요율표>");
			for (int i = 0; i < eFamilyMedicalRelationship.values().length - 1; i++) {
				System.out.println(eFamilyMedicalRelationship.values()[i + 1].getName()+" : "+((CancerInsurance)insurance).getRateOfFamilyMedicalRelationship()[i]);
			}
			break;
		case tripInsurance:
			System.out.println("\n  <여행지역 안전도 요율표>");
			for (int i = 0; i <	eRiskOfTripCountry.values().length - 1; i++) {
				System.out.println(eRiskOfTripCountry.values()[i + 1].getName()+" : "+((TripInsurance)insurance).getRateOfCountryRank()[i]);
			}
			break;
		default:
			break;
		}
		// 보장 내역 조회하기 코딩해야함
		System.out.println();
		showGuaranteePlan(insurance, true);
		System.out.println("----------------------");		
	}
	
	// 납부내역 확인하기
	private void payFee(String contractId) {
		Contract contract = this.contractDAO.selectContract(contractId);
		contract.connectContractDAO(this.contractDAO);
		System.out.println("<" + Constants.thisYear + "년의 납부 내역>");
		for (int i = 0; i < contract.getPayHistory().length; i++) {
			if (contract.getPayHistory()[i]) {
				System.out.println((i + 1) + "월 : O");
			} else {
				System.out.println((i + 1) + "월 : X");
			}
		}
		menu: while (true) {
			System.out.println("\n1.일괄 납부하기");
			System.out.println("2.선택한 월의 보험료 납부하기");
			System.out.println("0.돌아가기");
			System.out.println("원하는 메뉴를 입력해주세요");
			int inputMenu = 0;
			try {
				inputMenu = scn.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("error : 숫자를 입력해주세요");
				System.out.println("-----------------------");
				scn.nextLine();
				continue;
			}
			switch (inputMenu) {
			case 0:
				return;
			case 1:
				while (true) {
					System.out.printf(Constants.thisYear + "년의 납부되지 않은 보험료를 일괄납부 하시겠습니까?(y/n) : ");
					String inputCheck = scn.next();
					int unpaiedCount = 0;
					if (inputCheck.equals("y")) {
						for (int i = 0; i < contract.getPayHistory().length; i++) {
							if (!contract.getPayHistory()[i]) {
								unpaiedCount += 1;
								contract.payFee(contract, i);
							}
						}
						if (unpaiedCount == 0) {
							System.out.println("납부할 보험료가 없습니다! 이전으로 돌아갑니다.");
							continue menu;
						}
						System.out.println("\n"+unpaiedCount * contract.getFee() + "원의 보험료를 납부하셨습니다.");
						break;
					} else if (inputCheck.equals("n")) {
						continue menu;
					} else {
						System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
						continue;
					}
				}
				break;
			case 2:
				while (true) {
					System.out.println("\n(이전으로 돌아가려면 0을 입력하세요)");
					System.out.printf("납부를 원하는 월을 입력해주세요 : ");
					int inputMonth = 0;
					try {
						inputMonth = scn.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("error : 숫자를 입력해주세요");
						System.out.println("-----------------------");
						scn.nextLine();
						continue;
					}
					if (inputMonth == 0) {
						continue menu;
					}
					if (inputMonth < 0 || inputMonth > 12) {
						System.out.println("1~12 사이의 숫자를 입력해주세요.");
						continue;
					}
					if (contract.getPayHistory()[inputMonth - 1]) {
						System.out.println("이미 보험료가 납부돼있는 월입니다. 다시 입력해주세요.");
						continue;
					} else {
						contract.payFee(contract, inputMonth - 1);
						System.out.println("\n"+inputMonth + "월의 보험료" + contract.getFee() + "원이 납부되었습니다.");
						break;
					}
				}
				break;
			default:
				System.out.println("error : 범위 내의 숫자를 입력해주세요");
				System.out.println("-----------------------");	
				break;
			}
			break;
		}
	}
	
	// 실적 확인하기
	private void showPerformance(Employee employee) {
		System.out.println("----------<영업사원 리스트>-------------");
		System.out.println("순위   직원ID   이름     영업실적   전화번호");
		int cnt = 0;
		for(Employee salesPerson : this.employeeDAO.selectSlaesPersons()) {
			cnt++;
			System.out.printf("%-6d%-8s%-7s%-8d%s\n", cnt, salesPerson.getEmployeeId(), salesPerson.getName(), salesPerson.getSaleHistory(), salesPerson.getPhoneNumber());
		}

		System.out.println("------------------------------------");
		if(cnt == 0) {
			System.out.println("현재 회사에 재직중인 영업사원의 데이터가 없습니다");
			return;
		}
		System.out.println("1.영업사원 실적 보기");
		System.out.println("2.내 실적 확인하기");
		System.out.println("0.돌아가기");
		roop : while(true) {
			try {
				int input = scn.nextInt();
				switch(input) {
				case 1:
					while (true) {
						System.out.println("(이전으로 돌아가려면 0을 입력하세요)");
						System.out.printf("찾으시는 영업사원의 ID를 입력해주세요 : ");
						String input2 = scn.next();
						Employee salesperson = this.employeeDAO.selectSlaesPerson(input2);
						if(input2.equals("0")) break;
						if (salesperson != null) {
							int cnt2 = 0;
							System.out.println("-------<면담실적 리스트>--------");
							for(Interview interview : this.interviewDAO.select()) {
								if(salesperson.getEmployeeId().equals(interview.getSalespersonId())) {
									cnt2++;
									Customer customer = this.customerDAO.selectCustomer(interview.getCustomerId());
									System.out.println("면담 ID : " + interview.getInterviewId());
									System.out.println("면담일자 : " + interview.getDate());
									System.out.println("고객 ID : " + customer.getCustomerId());
									System.out.println("전화번호 : " + customer.getPhoneNumber());
									System.out.println("주소 : " + customer.getAddress());
									System.out.print("면담내용 : " + interview.getContent());
									System.out.println("-----------------------------");
								}
							}
							if(cnt2==0) {
								System.out.println("찾으신 영업사원의 실적이 존재하지 않습니다");
							}
							break;
						} else {
							System.out.println("찾으시는 ID를 갖는 영업사원이 없습니다.");
						}
					}
					break roop;
				case 2:
					int cnt2 = 0;
					System.out.println("-------<면담실적 리스트>--------");
					for(Interview interview : this.interviewDAO.select()) {
						if(employee.getEmployeeId().equals(interview.getSalespersonId())) {
							cnt2++;
							Customer customer = this.customerDAO.selectCustomer(interview.getCustomerId());
							System.out.println("면담 ID : " + interview.getInterviewId());
							System.out.println("면담일자 : " + interview.getDate());
							System.out.println("고객 ID : " + customer.getCustomerId());
							System.out.println("전화번호 : " + customer.getPhoneNumber());
							System.out.println("주소 : " + customer.getAddress());
							System.out.print("면담내용 : " + interview.getContent());
							System.out.println("-----------------------------");
						}
					}
					if(cnt2==0) {
						System.out.println("실적이 존재하지 않습니다");
					}
					break roop;
				case 0:
					break roop;
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
	// 삭제된 보험 보기
	private boolean showDeletedInsurance(boolean del) {
		System.out.println("------보험 정보------");	
		boolean isExist = false;
		for (Insurance insurance: this.insuranceDAO.select()) {
			if (insurance.isDel() == del){
				System.out.println(insurance.getInsuranceId() + ". " + insurance.getName());
				System.out.println("  기본보험료 : " + insurance.getBasicFee());
				System.out.println("-------------------");
				isExist = true;
			}
		}
		return isExist;
	}
	// 보험 사후관리하기
	private void postManageInsurance() {
		menu:while (true) {
			System.out.println("\n1.보험 상품 삭제하기");
			System.out.println("2.보험 상품 복구하기");
			System.out.println("0.돌아가기");
			System.out.println("원하는 메뉴를 입력해주세요");
			int inputMenu = 0;
			try {
				inputMenu = scn.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("error : 숫자를 입력해주세요");
				System.out.println("-----------------------");
				scn.nextLine();
				continue menu;
			}
			
			String inputId = "";
			boolean isDelExist;
			switch (inputMenu) {
			case 0:
				return;
			case 1:
				this.showDeletedInsurance(false);
				isDelExist = true;
				for (Insurance insurance: this.insuranceDAO.select()) {
					if (!insurance.isDel()) {
						isDelExist = false;
					}
				}
				if (isDelExist) {
					System.out.println("삭제를 요청할 보험이 존재하지 않습니다. 이전으로 돌아갑니다.");
					continue menu;
				}
				id: while (true) {
					System.out.println("(이전으로 돌아가려면 0을 입력하세요)");
					System.out.printf("상세정보를 확인할 보험의 ID를 입력하세요 : ");
					boolean isExist = false;
					Insurance tmpInsurance = null;
					inputId = scn.next();
					if (inputId.equals("0")) {
						continue menu;
					}
					for (Insurance insurance: this.insuranceDAO.select()) {
						if (insurance.getInsuranceId().equals(inputId)) {
							if (!insurance.isDel()) {
								isExist = true;
								tmpInsurance = insurance;
							}
						}
					}
					
					if (isExist) {
						if (tmpInsurance.isDel()) {
							System.out.println("이미 삭제요청된 보험입니다! 이전으로 돌아갑니다.");
							continue id;
						}
						this.showInsuranceData(tmpInsurance);
						while (true) {
							System.out.printf("해당 보험을 삭제하시겠습니까?(y/n) : ");
							String inputDelete = scn.next();
							if (inputDelete.equals("y")) {
								if (this.insuranceDeveloper.postManageInsurance(tmpInsurance, true)) {
									System.out.println("보험 삭제요청이 완료되었습니다.");
									break id;
								} else {
									System.out.println("시스템에 오류가 발생하여 요청이 취소되었습니다.");
								}
								continue menu;
							} else if (inputDelete.equals("n")) {
								System.out.println("보험 삭제를 취소합니다. 이전으로 돌아갑니다.");
								continue id;
							} else {
								System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
								continue;
							}
						}
					} else {
						System.out.println("존재하지 않는 ID입니다. 다시 입력해주세요.");
						continue id;
					}	
				}
				break;
			case 2:
				this.showDeletedInsurance(true);
				isDelExist = false;
				for (Insurance insurance: this.insuranceDAO.select()) {
					if (insurance.isDel()) {
						isDelExist = true;
					}
				}
				if (!isDelExist) {
					System.out.println("복구를 요청할 보험이 존재하지 않습니다. 이전으로 돌아갑니다.");
					continue menu;
				}
				id: while (true) {
					System.out.println("(이전으로 돌아가려면 0을 입력하세요)");
					System.out.printf("상세정보를 확인할 보험의 ID를 입력하세요 : ");
					boolean isExist = false;
					Insurance tmpInsurance = null;
					inputId = scn.next();
					if (inputId.equals("0")) {
						continue menu;
					}
					for (Insurance insurance: this.insuranceDAO.select()) {
						if (insurance.getInsuranceId().equals(inputId)) {
							isExist = true;
							tmpInsurance = insurance;
						}
					}
					
					if (isExist) {
						if (!tmpInsurance.isDel()) {
							System.out.println("삭제요청이 되지 않은 보험입니다! 이전으로 돌아갑니다.");
							continue id;
						}
						this.showInsuranceData(tmpInsurance);
						while (true) {
							System.out.printf("해당 보험을 복구하시겠습니까?(y/n) : ");
							String inputDelete = scn.next();
							if (inputDelete.equals("y")) {	
								if (this.insuranceDeveloper.postManageInsurance(tmpInsurance, false)) {
									System.out.println("보험 복구 요청이 완료되었습니다.");
									break id;
								} else {
									System.out.println("시스템에 오류가 발생하여 요청이 취소되었습니다.");
								}
								continue menu;
							} else if (inputDelete.equals("n")) {
								System.out.println("보험 복구를 취소하셨습니다. 이전으로 돌아갑니다.");
								continue id;
							} else {
								System.out.println("질못 입력하셨습니다. 다시 입력해주세요.");
								continue;
							}
						}
					} else {
						System.out.println("존재하지 않는 ID입니다. 다시 입력해주세요.");
						continue id;
					}	
				}
				break;
			default:
				System.out.println("error : 잘못 입력하셨습니다. 다시 입력해주세요");
				break;
			}
		}
	}
	
	// 보험 재계약하기
	private void requestReConract(Contract contract) {
		int tmptTime = contract.getLifespan() - this.time;
		if (tmptTime >= 100) tmptTime = ((tmptTime / 100) * 12) + (tmptTime % 100);
		if (tmptTime >= 3) {
			System.out.println("아직 계약 만료기간이 아닙니다.");
			System.out.println("계약 만료 2개월 전부터 재계약 신청이 가능합니다.");
			return;
		}
		menu : while(true) {
			System.out.print("해당 계약에 대한 정보를 수정하시고 계약 신청을 계속 하시겠습니까? (y/n) : ");
			String input = scn.next();
			Contract tmptContract = contract;
			Insurant insurant = insurantDAO.selectInsurant(contract.getInsurantId());
			if(input.equals("y")) {
				int cnt = 0;
				menu2 : while (true) {
					System.out.println("수정하실 정보를 선택해주세요.");
					System.out.println("1.이름");
					System.out.println("2.전화번호");
					System.out.println("3.주소");
					System.out.println("4.특약여부");
					System.out.println("5.수정내용 저장하고 다음 단계로");
					System.out.println("0.돌아가기");
					switch (scn.nextInt()) {
					case 1:
						System.out.print("새로운 이름을 입력해주세요.\n이름 :");
						insurant.setName(scn.next());
						cnt++;
						break;
					case 2:
						System.out.print("새로운 전화번호를 입력해주세요.\n전화번호 :");
						insurant.setPhoneNumber(scn.next());
						cnt++;
						break;
					case 3:
						System.out.print("새로운 주소를 입력해주세요.\n주소 : ");
						insurant.setAddress(scn.next());
						cnt++;
						break;
					case 4:
						if(tmptContract.isSpecial()) {
							System.out.print("특약가입을 취소하시겠습니까?(y/n) : ");
							roop : while(true) {
								input = scn.next();
								if(input.equals("y")) {
									tmptContract.setSpecial(false);
									cnt++;
									break roop;
								}
								else if(input.equals("n")) break roop;
								else System.out.println("error : 정해진 문자를 사용해주세요");
							}
						}else {
							System.out.print("특약에 가입하시겠습니까?(y/n) : ");
							roop : while(true) {
								input = scn.next();
								if(input.equals("y")) {
									tmptContract.setSpecial(true);
									cnt++;
									break roop;
								}
								else if(input.equals("n")) break roop;
								else System.out.println("error : 정해진 문자를 사용해주세요");
							}
						}
						break;
					case 5:
						if(cnt==0) {
							System.out.println("수정하신 정보가 없습니다.");
							System.out.print("정보 수정 없이 다음 단계를 진행하시겠습까? (y/n) : ");
							input = scn.next();
							if(input.equals("y")) break menu;
							else if(input.equals("n")) break;
							else System.out.println("error : 정해진 문자를 사용해주세요");
						}else {
							System.out.println("--------<수정된 계약 정보>--------");
							System.out.println("가입자 이름 : " + insurant.getName());
							System.out.println("가입자 전화번호 : " + insurant.getPhoneNumber());
							System.out.println("가입자 주소 : " + insurant.getAddress());
							System.out.print("특약여부 : ");
							if(tmptContract.isSpecial()) System.out.println("O");
							else System.out.println("X");
							System.out.println("------------------------------");
							System.out.print("이대로 저장을 하고 다음 단계를 진행하시겠습니까? (y/n) : ");
							input = scn.next();
							if(input.equals("y")) {
								if(insurantDAO.updateForRecontract(insurant) && 
								contractDAO.updateSpecial(tmptContract.getContractId(), tmptContract.isSpecial())) {
									System.out.println("정보 수정이 완료되었습니다!");
								}
								break menu;
							}
							else if(input.equals("n")) break;
							else System.out.println("error : 정해진 문자를 사용해주세요");
						}
						break;
					case 0:
						break menu2;
					default:
						break;
					}
				}
			}else if(input.equals("n")) {
				break menu;
			}else {
				System.out.println("error : 정해진 문자를 사용해주세요");
			}
		}
		while(true) {
			Insurance insurance = this.insuranceDAO.selectInsurance(contract.getInsuranceId());
			this.showInsuranceData(insurance);
			System.out.print("보험 재계약을 신청하시겠습니까? (y/n) : ");
			String input = scn.next();
			if (input.equals("y")) {
				tmptTime = ((insurance.getWarrantyPeriod() / 12) * 100) + (insurance.getWarrantyPeriod() % 12);
				contractDAO.updateLifespan(contract.getContractId(), contract.getLifespan() + tmptTime);
				System.out.println("!!!!보험 재계약이 완료되었습니다!!!!");
				return;
			} else if (input.equals("n")) {
				return;
			} else {
				System.out.println("error : 정해진 문자를 사용해주세요");
			}
		}
	}
	
	// 계약 부활신청하기
	public void reviveContract(Customer customer) {
		ArrayList<Contract> contractList = this.contractDAO.selectIds();
		ArrayList<Contract> targetList = new ArrayList<Contract>();
		
		for(Contract contract: contractList) {
			if (!contract.isEffectiveness() && customer.getCustomerId().equals(this.insurantDAO.selectCustomerId(contract.getInsurantId()))) {
				this.showSimpleContract(contract, true);
				targetList.add(contract);
			}
		}
		menu:while (true) {
			System.out.println("(이전으로 돌아가려면 0을 입력하세요)");
			System.out.printf("부활신청할 계약의 ID를 입력해주세요 : ");
			String inputId = scn.next();
			if(inputId.equals("0")) {
				return;
			}
			boolean isExist = false;
			for(Contract contract: targetList) {
				if (contract.getContractId().equals(inputId)) {
					isExist = true;
				}
			}
			if (isExist) {
				Contract contract = this.contractDAO.selectUnpaidAndFee(inputId);
				System.out.println(contract.getUnpaidPeriod() * contract.getFee() + "원의 미납 보험금이 있습니다.");
				System.out.printf("해당 계약을 부활신청하시겠습니까?(y/n) : ");
				while (true) {
					String inputDecision = scn.next();
					if (inputDecision.equals("y")) {
						customer.reviveContract(contract.getContractId(), contractDAO);
						System.out.println("해당 계약의 부활신청이 완료되었습니다.");
						return;
					} else if (inputDecision.equals("n")) {
						continue menu;
					} else {
						System.out.println("정해진 문자를 입력해주세요.");
						continue;
					}
				}
			} else {
				System.out.println("부활신청 대상이 아닌 계약ID입니다. 다시 입력해주세요.");
				continue menu;
			}
		}
		
	}
}
