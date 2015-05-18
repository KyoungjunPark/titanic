package model;

class TitanicModel {
	static private int nextID = 0;
	private int id;
	private DSMModel dsmModel;
	private CLSXModel clsxModel;
	
	public TitanicModel(){
		this.id = TitanicModel.nextID();
	}
    public TitanicModel(String dsmString)throws CreateException{
        this();
        this.dsmModel = new DSMModel(dsmString);
    }
    public TitanicModel(String dsmString, String clsxString)throws CreateException{
        this(dsmString);
        this.clsxModel = new CLSXModel(clsxString);
    }
	public int getID(){
		return this.id;
	}
	
	static private int nextID(){
		return TitanicModel.nextID++;
	}
}
