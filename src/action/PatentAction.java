package action;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.PatentDao;
import service.PatentService;

public class PatentAction extends ActionSupport {
	private PatentDao patentDao;
	private PatentService patentService;

	private Map request;
	private Map session;

	private String patentId;
	private String ipcName;
	private int page;
	private String words;

	public String ShowPatent() {
		session = ActionContext.getContext().getSession();
		request = (Map) ActionContext.getContext().get("request");
		patentService = new PatentService();
		patentDao = new PatentDao();
		String s[] = patentDao.ShowPatent(patentId);
		request.put("patent", s);
		
		
		
	    LinkedHashMap map = patentService.GetSimi(patentId,s[10]);
		request.put("simi", map);

		return "ShowPatent";
	}

	public String ListPatentByIpc() {
		request = (Map) ActionContext.getContext().get("request");
		patentDao = new PatentDao();
		int pagesum = patentDao.QueryPage(ipcName);
		List<String[]> sList = patentDao.QueryByIpc(ipcName, page);
		for (String[] strings : sList) {
			if (strings[2].length() > 137)
				strings[2] = strings[2].substring(0, 135) + ".....";
		}
		int low = 1;
		int high = pagesum;
		if (page - 5 > 1) {
			low = page - 5;
		}
		if (page + 5 <= pagesum) {
			high = page + 5;
		}
		request.put("result", sList);
		request.put("pagesum", pagesum);
		request.put("ipcName", ipcName);
		request.put("low", low);
		request.put("high", high);
		request.put("type", "ipc");
		return "ListPatent";
	}

	public String ListPatentByWords() {
		request = (Map) ActionContext.getContext().get("request");
		patentDao = new PatentDao();
		words = DealWords(words);
		if (words.length() == 0)
			return "index";
		int pagesum = patentDao.QueryNamePages(words);
		System.out.println(pagesum);

		List<String[]> sList = patentDao.QueryByText(words, page);
		for (String[] strings : sList) {
			if (strings[2].length() > 137)
				strings[2] = strings[2].substring(0, 135) + ".....";
		}
		int low = 1;
		int high = pagesum;
		if (page - 5 > 1) {
			low = page - 5;
		}
		if (page + 5 <= pagesum) {
			high = page + 5;
		}
		request.put("result", sList);
		request.put("pagesum", pagesum);
		request.put("words", words);
		request.put("low", low);
		request.put("high", high);
		request.put("type", "words");
		return "ListPatent";
	}

	private String DealWords(String words) {
		String nwords = "";
		try {
			nwords = new String(words.getBytes("ISO8859-1"), "utf-8");
			nwords = nwords.replaceAll(";", "");
			nwords = nwords.replaceAll("&", "&amp;");
			nwords = nwords.replaceAll("<", "&lt;");
			nwords = nwords.replaceAll(">", "&gt;");
			nwords = nwords.replaceAll("'", "");
			nwords = nwords.replaceAll("--", "");
			nwords = nwords.replaceAll("/", "");
			nwords = nwords.replaceAll("%", "");
			if (nwords.length() > 20)
				nwords = nwords.substring(0, 20);

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nwords;
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

	public String getPatentId() {
		return patentId;
	}

	public void setPatentId(String patentId) {
		this.patentId = patentId;
	}

	public String getIpcName() {
		return ipcName;
	}

	public void setIpcName(String ipcName) {
		this.ipcName = ipcName;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getWords() {
		return words;
	}

	public void setWords(String words) {
		this.words = words;
	}

}
