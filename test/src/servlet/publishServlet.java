package servlet;

import java.sql.*;


import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.gson.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

public class publishServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //�����������
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        /* ������Ӧͷ����ajax������� */
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* �Ǻű�ʾ���е��������󶼿��Խ��ܣ� */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        //��ȡ΢��С����get�Ĳ���ֵ����ӡ
        try{
            String topicid=request.getParameter("topicid");
            String tagid=request.getParameter("tagid");
            String saying=request.getParameter("saying");
            String date1="2018-09-09 12:00:00";
            System.out.println(saying);
            int tgid=Integer.parseInt(tagid);
            if (topicid.equals("tp01")) {
            	tgid=tgid+1;
            }
			if (topicid.equals("tp02")) {
			    tgid=tgid+5;
			            }
			if (topicid.equals("tp03")) {
				tgid=tgid+7;   	
			            }
			if (topicid.equals("tp04")) {
				tgid=tgid+11;
			}
			if (topicid.equals("tp05")) {
				tgid=tgid+17;
			}
			if (tgid<10) {
				tagid="tg0"+tgid; 
			}
			else {
				tagid="tg"+tgid; 
			}
			String driverName = "com.mysql.jdbc.Driver";  
        	String url = "jdbc:mysql://localhost/tsh?user=root&password=pop966100";  
        	Class.forName(driverName);  	
        	Connection conn = DriverManager.getConnection(url);     
        	Statement stmt = conn.createStatement();  
        	String str1= "insert into moment(uid,mcontent,mtime,tid,tagid) values('001',?,?,?,?)";
            PreparedStatement pstmt1 = conn.prepareStatement(str1);
            pstmt1.setString(1, saying);
            pstmt1.setString(2, date1);
            pstmt1.setString(3, topicid);
            pstmt1.setString(4, tagid);
            int x= pstmt1.executeUpdate();
            if(x>0){
      	    pstmt1.close();
            //����ֵ��΢��С����
	          Writer out = response.getWriter();
	          out.write("�����Ѿ����뵽���ݿ��У�");
	          out.flush();
	        stmt.close();
	        conn.close();
            }
        }
        catch (Exception e) 
        {
        	System.out.println(e);  
            e.printStackTrace();
       	}
        //result.put("data", user);
        //result.put("msg", "��̨���յ�");
        //ʹ��Gson����Ҫ����gson-2.8.0.jar
        //String json = new Gson().toJson(result);

        //����ֵ��΢��С����
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
