package com.icekredit.utils;

//import com.alibaba.fastjson.JSONObject;
//import com.icekredit.model.models.*;
//import com.icekredit.model.models.DataFormat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
//import com.icekredit.model.models.*;
//import com.icekredit.model.models.DataFormat;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 动态加载模型jar包以及动态调用模型方法
 */
public class LoadJar {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(LoadJar.class);
    private static final String MODEL = "com.icekredit.model.models.impl.";
    private static final String DATAFORMATPACKAGE = "com.icekredit.model.models.";
    private static final String GETRESULTFORCOMPANY = "getResultForCompany";

    public static URLClassLoader getUrlClassLoader(String jarPath){
        URL url1 = null;
        URLClassLoader urlClassLoader = null;
        try {
            File file=new File(jarPath);//jar包的路径
            url1=file.toURI().toURL();
            //url1 = new URL(jarPath);
            urlClassLoader = new URLClassLoader(new URL[] { url1 }, LoadJar.class.getClassLoader());
        } catch (MalformedURLException e) {
        }
        return urlClassLoader;
    }

    public static JSONObject getModelResult(URLClassLoader urlClassLoader, String packageName, String className, String function,JSONObject dataFormat) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        logger.debug(dataFormat.toString());
        Class<?> classType = urlClassLoader.loadClass(packageName + className);
        Class<?> classDataFormat = urlClassLoader.loadClass(DATAFORMATPACKAGE + "DataFormat");
        Method[] m = classType.getMethods();
        Method m2 = null;
        for (int i=0; i< m.length; ++i){
            if (function.equals(m[i].getName())){
                m2 = m[i];
            }
        }
        Constructor c = classDataFormat.getConstructor(JSONObject.class);
        Object o = classType.newInstance();
        Method m1 = classType.getDeclaredMethod("init", classDataFormat);
        m1.invoke(o, c.newInstance(dataFormat));
        JSONObject result = JSON.parseObject(JSON.toJSONString(m2.invoke(o)));
        logger.info(result.toString());
        return result;
    }
    public static void  main(String args[]){
        ClassLoader loadJarClassLoader = LoadJar.class.getClassLoader();
        String data = "{\"sysInfo\":{\"applicationForm\":{\"loan_code\":\"20170505977526\",\"education\":\"3\",\"work_name\":\" \",\"store_no\":\"SXB100017169\",\"work_phone\":\" \",\"loan_limit\":\"8000\",\"work_area\":\"310105\",\"loan_amt_applied\":7580,\"seller_name\":\"\",\"custom_level\":\"A\",\"seller_phone\":\"\",\"id\":\"610582199008116523\",\"fam_income\":100000,\"workmate_name\":\" \",\"reg_area\":\"610582\",\"agric_reg\":\"1\",\"work_prove\":\"8\",\"fam_addr\":\"陕西渭南市华阴市岳庙办亭子村六组\",\"workmate_phone\":\" \",\"contact1_phone\":\"13572329844\",\"work_addr\":\"\",\"work_type\":\" \",\"loan_time_applied\":12,\"product_name\":\"豪爵\",\"income_work\":80000,\"store_bank\":\"中国工商银行渭南潼关县支行\",\"contract_num\":\"1493965343550\",\"fam_num\":3,\"name\":\"张沛\",\"land_square\":3,\"application_date\":\"2017/05/05\",\"contact3_name\":\" \",\"birthday\":\"1990/08/11\",\"gender\":\"0\",\"fam_area\":\"610582\",\"contact2_rel\":\"3\",\"contact2_phone\":\"13335336788\",\"cell\":\"13991681791\",\"income_farm\":20000,\"contact2_name\":\"彭爱莲\",\"title\":\"\",\"loan_type\":\"shenma_001\",\"income_farm_type\":\"1\",\"dealer_province\":\"陕西\",\"dealer_addr\":\"陕西渭南市潼关县和 平路   \",\"marriage\":\"2\",\"comp_popu\":\" \",\"contact3_rel\":\"\",\"income_work_type\":\"1,4\",\"earning\":0,\"contact1_name\":\"陈鸽鸽\",\"work_years\":0,\"contact3_phone\":\" \",\"contact1_rel\":\"1\",\"product_type\":\"002\",\"money\":7980,\"account_branch_1\":\"中国邮政储蓄银行\",\"dealer_name\":\"潼关县百信商贸城\",\"reg_addr\":\"陕西省华阴市岳庙办事子村六组\",\"account_num_1\":\"6217997900014347504\",\"downpayment\":400}},\"base_info\":{\"service\":\"ick_shenma_t\",\"name\":\"张沛\",\"id\":\"610582199008116523\",\"cell\":\"13991681791\"}}";
        JSONObject object = JSON.parseObject(data);
        URLClassLoader classLoader = LoadJar.getUrlClassLoader( "/upload/" + "Model.jar");//+ "Model.jar"
        JSONObject modelResult = null;
        try {
            modelResult = LoadJar.getModelResult(classLoader, "com.icekredit.model.models.impl.", "ShenMaToC", "getResultForCompany", object);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(modelResult.toString());
    }
}
