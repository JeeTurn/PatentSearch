<%@page import="pojo.IpcClass"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>专利检索</title>

<script type="text/javascript">
function submitform()
{
	var words = document.getElementById("words").value;
	var warning = document.getElementById("warning");
	if(words.length==0){
		window.location.href="index.jsp"; 
	}
	else{
        window.location.href="PatentAction_ListPatentByWords?page=1&words="+words; 
	}
}
</script>

<style type="text/css" media="screen">
      body {
        background-color: #f1f1f1;
        margin: 0;
      }
      body,
      input,
      button {
        font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
      }
      .container { margin: 15px auto 20px auto; width: 1200px; text-align: center; }

      a { color: #4183c4; text-decoration: none; font-weight: bold; }
      a:hover { text-decoration: underline; }
      ul li{list-style-type:none;margin-top: 10px}
      h3 { color: #666; }

      input[type=text],
      input[type=password] {
        font-size: 13px;
        min-height: 32px;
        margin: 0;
        padding: 7px 8px;
        outline: none;
        color: #333;
        background-color: #fff;
        background-repeat: no-repeat;
        background-position: right center;
        border: 1px solid #ccc;
        border-radius: 3px;
        box-shadow: inset 0 1px 2px rgba(0,0,0,0.075);
        -moz-box-sizing: border-box;
        box-sizing: border-box;
        transition: all 0.15s ease-in;
        -webkit-transition: all 0.15s ease-in 0;
        vertical-align: middle;
      }
      .button {
        position: relative;
        display: inline-block;
        margin: 0;
        padding: 8px 15px;
        font-size: 13px;
        font-weight: bold;
        color: #333;
        text-shadow: 0 1px 0 rgba(255,255,255,0.9);
        white-space: nowrap;
        background-color: #eaeaea;
        background-image: -moz-linear-gradient(#fafafa, #eaeaea);
        background-image: -webkit-linear-gradient(#fafafa, #eaeaea);
        background-image: linear-gradient(#fafafa, #eaeaea);
        background-repeat: repeat-x;
        border-radius: 3px;
        border: 1px solid #ddd;
        border-bottom-color: #c5c5c5;
        box-shadow: 0 1px 3px rgba(0,0,0,.05);
        vertical-align: middle;
        cursor: pointer;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
        -webkit-touch-callout: none;
        -webkit-user-select: none;
        -khtml-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
        -webkit-appearance: none;
      }
      .button:hover,
      .button:active {
        background-position: 0 -15px;
        border-color: #ccc #ccc #b5b5b5;
      }
      .button:active {
        background-color: #dadada;
        border-color: #b5b5b5;
        background-image: none;
        box-shadow: inset 0 3px 5px rgba(0,0,0,.15);
      }
      .button:focus,
      input[type=text]:focus,
      input[type=password]:focus {
        outline: none;
        border-color: #51a7e8;
        box-shadow: inset 0 1px 2px rgba(0,0,0,.075), 0 0 5px rgba(81,167,232,.5);
      }
     
      label[for=search] {
        display: block;
        text-align: left;
      }
      #search label {
        font-weight: 200;
        padding: 5px 0;
      }
      #search input[type=text] {
        font-size: 18px;
        width: 705px;
      }
      #search .button {
        padding: 10px;
        width: 90px;
      }

    </style>

</head>
<body>

<s:if test="#session.first==null">
   <script language="javascript" type="text/javascript"> 
   window.location.href='IpcAction_QueryFirst.do';
</script>
</s:if>

<div style="width: 1200px;margin: 0 auto;">
<div style="height: 100px;background-color: #9DD5CB;">
</div>
<div class="container">

    <input type="text" id="words">
    <input class="button" type="submit" value="Search" onclick=submitform() >

</div>
<div style="width: 1200px;">
   <ul>
      <s:iterator value="#session.first" var="f">   
        <li>
        <a href="IpcAction_QuerySecond.do?IpcText=<s:property value="#f.IpcText" />">
        <s:property value="#f.IpcText" />&nbsp;&nbsp;&nbsp; <s:property value="#f.IpcMean" />
        </a>
        <s:if test="#session.parent==#f.IpcText">
           <ul>
           <s:iterator value="#session.second" var="s">
              <li>
              <a href="IpcAction_QueryThird.do?IpcText=<s:property value="#s.IpcText" />">
              <s:property value="#s.IpcText" />&nbsp;&nbsp;&nbsp; <s:property value="#s.IpcMean" />
              </a>
              
              <s:if test="#session.sparent==#s.IpcText">
           <ul>
           <s:iterator value="#session.third" var="t">
              <li>
              <a href="PatentAction_ListPatentByIpc?ipcName=<s:property value="#t.IpcText" />&page=1">
              <s:property value="#t.IpcText" />&nbsp;&nbsp;&nbsp; <s:property value="#t.IpcMean" />
              </a>
              </li>
           </s:iterator>
           </ul>
        </s:if>
              
              
              </li>
           </s:iterator>
           </ul>
        </s:if>
        
         </li>
      </s:iterator>
   </ul>
</div>
</div>

</body>
</html>