package model;

class TitanicModel {
	private static int nextID = 0;
	private int id;
	private DSMModel dsmModel;
	private CLSXModel clsxModel;
	
	public TitanicModel(){
		this.id = TitanicModel.nextID();
	}
    
	public int getID(){
		return this.id;
	}
	
	static private int nextID(){
		return TitanicModel.nextID++;
	}
}
