package cn.sgst.tool.web.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: fli
 * @email: fli@sstir.cn
 * @date: 2019/10/23 9:25
 */
@ConfigurationProperties(prefix = "xss")
public class XssProperties {


    private List<String> exclusionUrls = Collections.emptyList();


    // ===============================================================================

    public List<String> getExclusionUrls() {
        return exclusionUrls;
    }

    public void setExclusionUrls(List<String> exclusionUrls) {
        this.exclusionUrls = exclusionUrls;
    }

}
