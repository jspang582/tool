package cn.sgst.tool.common.util;


import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 判断是否是ajax请求
 * @author: fli
 * @email: fli@sstir.cn
 * @date: 2019/7/26 14:22
 */
public class HttpServletUtil {
    public static boolean isAjax(HttpServletRequest req){
        //判断是否为ajax请求，默认不是
        boolean isAjaxRequest = false;
        if(!StringUtils.isBlank(req.getHeader("x-requested-with")) && req.getHeader("x-requested-with").equals("XMLHttpRequest")){
            isAjaxRequest = true;
        }
        return isAjaxRequest;
    }
}
