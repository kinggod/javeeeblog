package test;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONObjectSample {

 //创建JSONObject对象   
    private static JSONObject createJSONObject(){   
        JSONObject jsonObject = new JSONObject();   
        jsonObject.put("username","天涯草");   
        jsonObject.put("sex", "男");   
        jsonObject.put("QQ", "1");   
        jsonObject.put("Min.score", new Integer(99));   
        jsonObject.put("nickname", "天涯草");   
        return jsonObject;   
    }   
    public static void main(String[] args) {   
        JSONObject jsonObject = JSONObjectSample.createJSONObject();   
        //输出jsonobject对象   
        System.out.println("jsonObject==>"+jsonObject);   
           
        //判读输出对象的类型   
        boolean isArray = jsonObject.isArray();   
        boolean isEmpty = jsonObject.isEmpty();   
        boolean isNullObject = jsonObject.isNullObject();   
        System.out.println("isArray:"+isArray+" isEmpty:"+isEmpty+" isNullObject:"+isNullObject);   
           
        //添加属性   
        jsonObject.element("address", "天涯草");   
        System.out.println("添加属性后的对象==>"+jsonObject);   
           
        //返回一个JSONArray对象   
        JSONArray jsonArray = new JSONArray();   
        jsonArray.add(0, "this is a jsonArray value");   
        jsonArray.add(1,"another jsonArray value");   
        jsonObject.element("jsonArray", jsonArray);   
        JSONArray array = jsonObject.getJSONArray("jsonArray");   
        System.out.println("返回一个JSONArray对象："+array);   
        //添加JSONArray后的值   
//        {"username":"天涯草","sex":"男","QQ":"天涯草","Min.score":99,"天涯草":"天涯草","address":"天涯草","jsonArray":["this is a jsonArray value","another jsonArray value"]}  
        System.out.println("结果="+jsonObject);   
           
        //根据key返回一个字符串   
        String username = jsonObject.getString("username");   
        System.out.println("username==>"+username);  
        
        //把字符转换为 JSONObject
        String temp=jsonObject.toString();
        JSONObject object = JSONObject.fromObject(temp);
        //转换后根据Key返回值
        System.out.println("qq="+object.get("QQ"));
        
    }  

}