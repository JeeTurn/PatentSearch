package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

import pojo.Patent;

public class PatentDao {
	public String[] ShowPatent(String PatentId){
		String sql = "select PatentId,ApplyId,Title,Proposer,Author,Abstract,Claim,PatentText,Quote,BeQuote,Keywords from Patent where PatentId = '"+PatentId+"'";
		Connection connection  = DBFactory.GetConnection();
		if(connection==null)
			return null;
		try {
			Statement statement = connection.createStatement();
			ResultSet rSet = statement.executeQuery(sql);
			String s[] = new String[11];
			rSet.next();
			for(int i=1;i<12;i++){
				s[i-1]=rSet.getString(i);
			}
			rSet.close();
			statement.close();
			DBFactory.CloseConn();
			return s;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String ShowPatentTitle(String PatentId){
		String sql = "select Title from Patent where PatentId = '"+PatentId+"'";
		Connection connection  = DBFactory.GetConnection();
		String result = "";
		if(connection==null)
			return null;
		try {
			Statement statement = connection.createStatement();
			ResultSet rSet = statement.executeQuery(sql);
		    rSet.next();
		    result = rSet.getString(1);
		    rSet.close();
		    statement.close();
		    DBFactory.CloseConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public List<String[]> QueryByIpc(String ipcName,int page){
		page--;
		String sql = "select PatentId,Title,Abstract from Patent where PatentId in(select PatentId from IpcToPatent where IpcName='"+ipcName+"') limit "+(0+page*10)+","+10;
		System.out.println(sql);
		Connection connection  = DBFactory.GetConnection();
		if(connection==null)
			return null;
		try {
			List<String[]> sList = new ArrayList<>();
			Statement statement = connection.createStatement();
			ResultSet rSet = statement.executeQuery(sql);
			
			while(rSet.next()){
			   String s[] = new String[3];
			   for(int i=1;i<4;i++){
				  s[i-1]=rSet.getString(i);
			   }
			   sList.add(s);
			}
			rSet.close();
			statement.close();
			DBFactory.CloseConn();
			return sList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int QueryPage(String ipcName){
		String sql = "select count(*) from Patent where PatentId in(select PatentId from IpcToPatent where IpcName='"+ipcName+"')";
		Connection connection  = DBFactory.GetConnection();
		if(connection==null)
			return 0;
		try {
			Statement statement = connection.createStatement();
			ResultSet rSet = statement.executeQuery(sql);
			String s[] = new String[8];
			rSet.next();
			int sum = rSet.getInt(1);
			rSet.close();
			statement.close();
			DBFactory.CloseConn();
			int pages = 0;
			if(sum%10==0)
				if(sum!=0)
				   pages = sum/10;
				else 
				   pages=1;
			else
				pages = sum/10+1;
			return pages;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public int QueryNamePages(String text){
		Result textSegResult = ToAnalysis.parse(text);
		String seg = "";
		Iterator<Term> teIterator = textSegResult.iterator();
		while (teIterator.hasNext()) {
			Term term = teIterator.next();
			seg += term.getName() + " ";
		}
		String sql = "select count(*) from Patent where match(TitleSeg,PatentTextSeg) against('"+seg.trim()+"')";
		System.out.println(sql);
		Connection connection  = DBFactory.GetConnection();
		if(connection==null)
			return 0;
		try {
			Statement statement = connection.createStatement();
			ResultSet rSet = statement.executeQuery(sql);
			String s[] = new String[8];
			rSet.next();
			int sum = rSet.getInt(1);
			rSet.close();
			statement.close();
			DBFactory.CloseConn();
			int pages = 0;
			if(sum%10==0)
				if(sum!=0)
				   pages = sum/10;
				else 
				   pages=1;
			else
				pages = sum/10+1;
			return pages;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<String[]> QueryByText(String text,int page){
		Result textSegResult = ToAnalysis.parse(text);
		String seg = "";
		Iterator<Term> teIterator = textSegResult.iterator();
		while (teIterator.hasNext()) {
			Term term = teIterator.next();
			seg += term.getName() + " ";
		}
		page--;
		String sql = "select PatentId,Title,Abstract from Patent where match(TitleSeg,PatentTextSeg) against('"+seg.trim()+"*' IN BOOLEAN MODE) limit "+(0+page*10)+","+10;
		System.out.println(sql);
		Connection connection  = DBFactory.GetConnection();
		if(connection==null)
			return null;
		try {
			List<String[]> sList = new ArrayList<>();
			Statement statement = connection.createStatement();
			ResultSet rSet = statement.executeQuery(sql);
			
			while(rSet.next()){
			   String s[] = new String[3];
			   for(int i=1;i<4;i++){
				  s[i-1]=rSet.getString(i);
			   }
			   sList.add(s);
			}
			rSet.close();
			statement.close();
			DBFactory.CloseConn();
			return sList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Map<String, String> GetALlKeys(){
		Map map = new HashMap<>();
		String sql = "select PatentId,Keywords from Patent";
		Connection connection  = DBFactory.GetConnection();
		if(connection==null)
			return null;
		try {
			Statement statement = connection.createStatement();
			ResultSet rSet = statement.executeQuery(sql);
			while(rSet.next()){
				map.put(rSet.getString(1), rSet.getString(2));
			}
			rSet.close();
			statement.close();
			DBFactory.CloseConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	

}

