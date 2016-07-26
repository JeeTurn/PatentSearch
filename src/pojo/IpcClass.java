package pojo;

import java.io.Serializable;
import java.util.Set;

public class IpcClass implements Serializable{
	private String IpcText;
	private String IpcMean;
	private String Parent;
	private Set patentSet;
	public String getIpcText() {
		return IpcText;
	}
	public void setIpcText(String ipcText) {
		IpcText = ipcText;
	}
	public String getIpcMean() {
		return IpcMean;
	}
	public void setIpcMean(String ipcMean) {
		IpcMean = ipcMean;
	}
	public String getParent() {
		return Parent;
	}
	public void setParent(String parent) {
		Parent = parent;
	}
	public Set getPatentSet() {
		return patentSet;
	}
	public void setPatentSet(Set patentSet) {
		this.patentSet = patentSet;
	}
	
}
