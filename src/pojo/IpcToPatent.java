package pojo;

import java.io.Serializable;

public class IpcToPatent implements Serializable{
   private String PatentId;
   private String IpcName;
public String getPatentId() {
	return PatentId;
}
public void setPatentId(String patentId) {
	PatentId = patentId;
}
public String getIpcName() {
	return IpcName;
}
public void setIpcName(String ipcName) {
	IpcName = ipcName;
}
   
}
