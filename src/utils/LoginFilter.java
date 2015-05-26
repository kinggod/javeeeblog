/**
 * 登陆过滤器，过滤非动作
 * 锦瀚
 * 时间：2015.5.26
 */
package utils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
public class LoginFilter implements Filter
{
    protected FilterConfig filterConfig = null;
    private String redirectURL = null;
    private List<String> notCheckURLList = new ArrayList<String>();
    private String loginName = null;
    private String root=null;
 
    public void destroy()
    {
        notCheckURLList.clear(); // 清楚list里面的所有元素
        System.out.println("过滤器销毁");
    }
 
    public void doFilter(ServletRequest servletRequest,
            ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException
    {
    	System.out.println("登录过滤开始");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
 
        HttpSession session = request.getSession();
        if (loginName == null)//对用户不验证
        {
            filterChain.doFilter(request, response);
            System.out.println("不需要登陆");
            return;
        }
        if(checkRequestURIIntNotFilterList(request)){
            filterChain.doFilter(servletRequest, servletResponse);
            System.out.println("白页面");
            return;
        }
        if (session.getAttribute(loginName) == null)//没有登录
        {
            System.out.println("没有登陆");
      /*      response.sendRedirect(request.getContextPath()+"/401error.html");*/
      /*      response.sendRedirect(request.getContextPath()+"/login.html");*/
         	response.sendRedirect(request.getContextPath()+ redirectURL);
            return;
        }
        if(root!=null&&!((session.getAttribute(loginName)).equals(root))){//root登录
            System.out.println("非指定用户");
        	response.sendRedirect(request.getContextPath()+ redirectURL);
            return;
        }
        System.out.println("用户后台");
        filterChain.doFilter(request, response);
    }
 
    private boolean checkRequestURIIntNotFilterList(HttpServletRequest request)
    {
        String uri = request.getServletPath()
                + (request.getPathInfo() == null ? "" : request.getPathInfo());//相对于根目录的路径
        return notCheckURLList.contains(uri);
    }
 
    public void init(FilterConfig filterConfig) throws ServletException
    {
        System.out.println("登录过滤器start");
        this.filterConfig = filterConfig;
        redirectURL = filterConfig.getInitParameter("redirectURL");
        loginName = filterConfig.getInitParameter("checkSessionKey");
        root = filterConfig.getInitParameter("root");
        String notCheckURLListStr = filterConfig
                .getInitParameter("notCheckURLList");
        if (notCheckURLListStr != null)//如果存在不过滤的页面，加入数组
        {
            StringTokenizer st = new StringTokenizer(notCheckURLListStr, ";");
            notCheckURLList.clear();
            while (st.hasMoreTokens())
            {
                notCheckURLList.add(st.nextToken());
            }
        }
    }
}