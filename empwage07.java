 java.util.*;

public class EmpWageBuilderInterface implements EmpComputeWage {

	public static final int IS_PART_TIME = 1;
	public static final int IS_FULL_TIME = 2;
	//private static Company [] companies = new Company[5];

	private List<Company> list = new ArrayList<>();

	@Override
	public int getWagesByCompanyName(String name){
		int totalWage = 0;
		for(int i = 0; i< list.size(); i++){
			Company  company = list.get(i);
			if(name.equals(company.getName())){
				totalWage = company.getTotalEmpWage();
				break;
			}
		}

		return totalWage;
	}
	@Override
	public void addCompany(final Company company){
		list.add(company);
	}

	@Override
	public void print(){
		list.forEach(System.out::println);
	}
	@Override
	public void calculateWage(){
		// for(int i= 0; i< list.size(); i++){
		// 	calculate(list.get(i));
		// }

		list.forEach(this::calculate);

	}
	private void calculate(final Company company) {
		int totalEmpHours = 0;
		int empHours = 0;
		int totalWorkingDays = 0;

		while (totalEmpHours <= company.getMaxHrsInMonth() 
			&& totalWorkingDays <= company.getNoOfWorkingDays()) {

			totalWorkingDays++;

			int empCheck = ((int) Math.floor(Math.random() * 10)) % 3;

			switch (empCheck) {
				case IS_FULL_TIME:
					empHours = 8;
					//System.out.println("Employee is present full-time on day " + totalWorkingDays);
				break;
				case IS_PART_TIME:
					empHours = 4;
					//System.out.println("Employee is present part-time on day " + totalWorkingDays);
				break;
				default:
					empHours = 0;
					//System.out.println("Employee is abscent on day " + totalWorkingDays);
			}
			totalEmpHours += empHours;
			//System.out.println("Day# : "+totalWorkingDays+ " Emp hrs : " +  empHours);
			
		}
		int totalEmpWage = totalEmpHours * company.getEmpRatePerHrs();
		company.setTotalEmpWage(totalEmpWage);
	}

	public static void main(String[] args) {
		// Directly calling function without creating obj 'cause its a static one.
		EmpComputeWage ebi = new EmpWageBuilderInterface();
		Company company1 = new Company("Dmart", 20, 10, 100);
		ebi.addCompany(company1);
		Company company2 = new Company("JioMart", 20, 5, 100);
		ebi.addCompany(company2);

		//Calculate
		ebi.calculateWage();
		ebi.print();
		int totalWages = ebi.getWagesByCompanyName("Dmart");
		System.out.println("Wage : " + totalWages);

		int totalWages1 = ebi.getWagesByCompanyName("Dmart1");
		System.out.println("Wage : " + totalWages1);
	}
}

interface EmpComputeWage {
	void addCompany(Company company);
	void calculateWage();
	void print();
	int getWagesByCompanyName(String name);

}

class Company {

	private String name;
	private int empRatePerHrs;
	private int noOfWorkingDays;
	private int maxHrsInMonth;
	private int totalEmpWage;

	public Company(final String name, final int empRatePerHrs, final int noOfWorkingDays, 
		final int maxHrsInMonth){
		this.name = name;
		this.empRatePerHrs = empRatePerHrs;
		this.noOfWorkingDays = noOfWorkingDays;
		this.maxHrsInMonth = maxHrsInMonth;
	}

	public String getName(){
		return name;
	}

	public int getMaxHrsInMonth(){
		return maxHrsInMonth;
	}

	public int getNoOfWorkingDays(){
		return noOfWorkingDays;
	}
  
	public int getEmpRatePerHrs(){
		return empRatePerHrs;
	}

	public void setTotalEmpWage(int totalEmpWage){
		this.totalEmpWage = totalEmpWage;
	}

	public int getTotalEmpWage(){
		return this.totalEmpWage;
	}

	@Override
	public String toString(){
		return "Total Employee wages for comapny "+this.name+ " is : "+this.totalEmpWage;
	}
}