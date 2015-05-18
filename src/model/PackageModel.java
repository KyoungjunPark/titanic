package model;

class PackageModel{

	private DSMModel dsm;
	private CLSXModel clsx;
	private final int id;
	private MetaModel metaData;
	
	public PackageModel(int id){
		this.id = id;
		dsm = new DSMModel();
		clsx = new CLSXModel();
		metaData =  new MetaModel();
	}
	

}
