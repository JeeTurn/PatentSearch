package action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.IpcDao;

public class IpcAction extends ActionSupport {
	private IpcDao ipcDao;
	
	private Map request;
	private Map session;
	
	private String IpcText;
	


	public String QueryFirst(){
		session = ActionContext.getContext().getSession();
		request = (Map) ActionContext.getContext().get("request");
		ipcDao = new IpcDao();
		List iList = ipcDao.GetParent();
		session.put("first", iList);
		session.remove("second");
		session.remove("third");
		session.remove("parent");
		session.remove("sparent");
		return "ToIndex";
	}
	
	public String QuerySecond(){
		session = ActionContext.getContext().getSession();
		request = (Map) ActionContext.getContext().get("request");
		ipcDao = new IpcDao();
		List iList = ipcDao.GetChild(IpcText);
		session.put("second", iList);
		session.put("parent", IpcText);
		return "ToIndex";
	}
	
	public String QueryThird(){
		session = ActionContext.getContext().getSession();
		request = (Map) ActionContext.getContext().get("request");
		ipcDao = new IpcDao();
		List iList = ipcDao.GetChild(IpcText);
		session.put("third", iList);
		session.put("sparent", IpcText);
		return "ToIndex";
	}
	
	
	
	
	public Map getRequest() {
		return request;
	}

	public void setRequest(Map request) {
		this.request = request;
	}

	public Map getSession() {
		return session;
	}

	public void setSession(Map session) {
		this.session = session;
	}

	public String getIpcText() {
		return IpcText;
	}

	public void setIpcText(String ipcText) {
		IpcText = ipcText;
	}
	
	
	
}
