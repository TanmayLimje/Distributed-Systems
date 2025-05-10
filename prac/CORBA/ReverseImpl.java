public class ReverseImpl extends ReverseModule.ReversePOA {

	ReverseImpl(){
		super();
		System.out.println("ReverseImpl Object Created.");
	}
	
	@Override 
	public String reverse (String input){
		return new StringBuilder(input).reverse().toString();
	}

}
