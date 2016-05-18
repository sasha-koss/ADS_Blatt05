package Praktikum5ADS;


public class Student {
private int MatNr;
private String Name;
private String Adresse;
private boolean deleted;
	
	public Student(){}
	
	public Student(int MatNr, String Name, String Adresse) {
		this.MatNr = MatNr;
		this.Name = Name;
		this.Adresse = Adresse;
		this.deleted = false;
	}

	public int getMatNr() {
		return MatNr;
	}

	public void setMatNr(int matNr) {
		MatNr = matNr;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getAdresse() {
		return Adresse;
	}

	public void setAdresse(String adresse) {
		Adresse = adresse;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	

}
