package DataStorage;

enum FilePath {
	MEDICINES("C:\\Users\\Canno\\eclipse-workspace\\AssignmentSC2005\\dataFiles\\Medicine_List.xlsx"),
	;
	
	private final String path;
	
	FilePath(String path){
		this.path = path;
	}
	
	String getPath(){
		return path;
	}
}
