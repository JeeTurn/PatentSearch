package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import pojo.IpcClass;

public class IpcDao {
	
	public List<IpcClass> GetParent(){
		Connection connection  = DBFactory.GetConnection();
		List<IpcClass> iList = new ArrayList<>();
		if(connection==null)
			return null;
		try {
			Statement statement = connection.createStatement();
			String sql = "select IpcText,IpcMean from IpcClass where Parent = '0'";
			ResultSet rSet = statement.executeQuery(sql);
			while(rSet.next()){
			IpcClass ipcClass = new IpcClass();
			ipcClass.setIpcText(rSet.getString(1));
			ipcClass.setIpcMean(rSet.getString(2));
			iList.add(ipcClass);
			}
			rSet.close();
			statement.close();
			DBFactory.CloseConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return iList;
	}
	
	public List<IpcClass> GetChild(String IpcText){
		Connection connection  = DBFactory.GetConnection();
		List<IpcClass> iList = new ArrayList<>();
		if(connection==null)
			return null;
		try {
			Statement statement = connection.createStatement();
			String sql = "select IpcText,IpcMean from IpcClass where Parent = '"+IpcText+"'";
			ResultSet rSet = statement.executeQuery(sql);
			while(rSet.next()){
			IpcClass ipcClass = new IpcClass();
			ipcClass.setIpcText(rSet.getString(1));
			ipcClass.setIpcMean(rSet.getString(2));
			iList.add(ipcClass);
			
			}
			rSet.close();
			statement.close();
			DBFactory.CloseConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return iList;
	}
}
