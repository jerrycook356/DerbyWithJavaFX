package javafxtext.business;

public class Validator {

	public Validator(){
		
	}
	public boolean isValid(String check){		
	if(check.length() == 0){
		return false;
	}
	char[] letters = check.toCharArray();
	for(char c: letters){
		if(!Character.isLetter(c)){
			return false;
		}
			
		}
		return true;
	}
}
	
					 

