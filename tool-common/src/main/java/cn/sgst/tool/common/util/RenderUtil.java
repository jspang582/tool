package cn.sgst.tool.common.util;

import cn.sgst.tool.common.exception.CoreExceptionEnum;
import cn.sgst.tool.common.exception.ServiceException;
import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * Web渲染
 * @author: fli
 * @email: fli@sstir.cn
 * @date: 2019/7/26 10:02
 */
public class RenderUtil {

    /**
     * 渲染json对象
     */
    public static void renderJson(HttpServletResponse response, Object jsonObject) {
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(jsonObject));
        } catch (IOException e) {
            throw new ServiceException(CoreExceptionEnum.WRITE_ERROR);
        }
    }
}
