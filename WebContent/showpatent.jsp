<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<div style="width: 1000px;margin: 0 auto;">
    <div style="text-align: left;padding: 20px 0px 0px 30px;">
    <h2>${request.patent[2]}</h2>
    </div>
    <div style="height: 2px;width: 1000px;background-color: #9DD5CB;"></div>
    
    <div style="width: 900px;margin:0 auto;">
        <div style="width: 900px;padding:20px 0px 0 12px;">
          <h4>摘要：</h4>
        </div>
        <div style="width: 900px;margin:10px 12px;">
          <div style="width: 250px;float: right;margin-left:20px;padding:20px 20px;text-align:left;border: thin solid #9DD5CB;">
          专利号:${request.patent[0]}<br>
          申请号:${request.patent[1]}<br>
          申请人:${request.patent[3]}<br>
          作者： ${request.patent[4]}<br>
          </div>
          ${request.patent[5]}
        </div>
        <div style="width: 900px;padding:20px 0px 0 12px;">
          <h4>简介：</h4>
        </div>
        <div style="width: 900px;margin:10px 12px;">
         ${request.patent[7]}
       </div>
       <div style="padding:20px 0px 0 12px;">
          <h4>专利要求：</h4>
        </div>
        <div style="margin:10px 12px;">
        ${request.patent[6]}
        </div>
       
        <div style="width: 900px;padding:20px 0px 0 12px;">
          <h4>相似专利：</h4>
        </div>
        
       
        <div style="margin:10px 12px; ">
        <table>
            <tr><td>相似专利</td><td>标题</td></tr>
            <s:iterator value="#request.simi" var="s">
            <tr><td><a href="PatentAction_ShowPatent?patentId=<s:property value="key"/>"><s:property value="key"/></a></td>
            <td><s:property value="value"/></td></tr>
            </s:iterator>
        </table>
        </div>
      
       
       
        </div>

    
        
    
    
      
      
     <!--   <s:if test="#request.simi.length>0"> -->
      
       
     <!-- </s:if> -->
    
</div>



</body>
</html>