package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UsersAPI") 
public class UserAPI  extends HttpServlet {
	
	User userObj = new User();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException
			{
			 String output = userObj.insertUser(request.getParameter("userName"),
			request.getParameter("Email"),
			request.getParameter("Age"),
			request.getParameter("PhoneNumber"),
			request.getParameter("Password"));
			response.getWriter().write(output);
			}
	
	
	
	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request)
	{
	 Map<String, String> map = new HashMap<String, String>();
	try
	 {
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
	 String queryString = scanner.hasNext() ?
	 scanner.useDelimiter("\\A").next() : "";
	 scanner.close();
	 String[] params = queryString.split("&");
	 for (String param : params)
	 { 
		 String[] p = param.split("=");
		 map.put(p[0], p[1]);
		 }
		 }
		catch (Exception e)
		 {
		 }
		return map;
		}
	
	
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException
			{
			 Map paras = getParasMap(request);
			 String output = userObj.updateUser(paras.get("hidUserIDSave").toString(),
			paras.get("userName").toString(),
			paras.get("Email").toString(),
			paras.get("Age").toString(),
			paras.get("PhoneNumber").toString(),
			paras.get("Password").toString());
			response.getWriter().write(output);
			} 
	
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException
			{
			 Map paras = getParasMap(request);
			 String output = userObj.deleteUser(paras.get("userID").toString());
			response.getWriter().write(output);
			}
}
