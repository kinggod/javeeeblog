/**
 * 
 * 登陆拦截器
 * 作者：锦瀚
 * 时间：2015.5.26
 * 
 * */
package interceptor;
import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = -2796326480994355318L;
	private String userName;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String,Object>  session = invocation.getInvocationContext().getSession();
       String user =(String) session.get("userName");
/*       System.out.println(user+"-----------------");*/
/*       System.out.println(userName);*/
       if(user==null||!(user.equals(userName))){
/*           System.out.println("nooo");*/
           return "login";
  
         }else {
        /*     System.out.println("yyyy");*/
           return invocation.invoke();
         }
	}

}
