package com.kilogod.code.util;

/**
 * @author anding
 * @Description:
 */

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.lionsoul.ip2region.Util;
import org.springframework.core.io.ClassPathResource;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Ip工具类，获取ip
 */
@Slf4j
public class IpUtils {

    // 获取ip地址
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            // "***.***.***.***".length()
            if (ipAddress != null && ipAddress.length() > 15) {
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }

            if(StringUtils.isNotBlank(ipAddress) && ipAddress.contains(",")){
                ipAddress=ipAddress.split(",")[0];
            }

            String ipInfo = StringUtils.isNotBlank(ipAddress)?ipAddress.trim():null;
            log.info("访问ip为："+ipInfo);
            return ipInfo;
        } catch (Exception e) {
            ipAddress=null;
        }
        return ipAddress;
    }

    // 根据ip地址获取城市
    public static String getCityInfo(String ip) throws IOException {
        File file;
        String dbPath;
        ClassPathResource resource = new ClassPathResource("static/ip2region.db");
        if(!resource.exists()){
            log.error("static/ip2region.db 文件不存在");
            return null;
        }else{
            try {
                file=resource.getFile();
                log.info("获取文件路径为："+file.getPath());
            } catch (IOException e) {
                log.error("获取文件失败！");

                log.info("地址库文件不存在,进行其他处理");
                String tmpDir = System.getProperties().getProperty("java.io.tmpdir");
                dbPath = tmpDir + "ip.db";
                log.info("临时文件路径:"+dbPath);
                file = new File(dbPath);
                if (!file.exists() || (System.currentTimeMillis() - file.lastModified() > 86400000L)){
                    log.info("文件不存在或者文件存在时间超过1天进入...");
                    FileUtils.copyInputStreamToFile(new ClassPathResource("static/ip2region.db").getInputStream(), file);
                }
                log.info("获取文件路径为："+file.getPath());
            }
        }


        //查询算法
        //B-tree
        int algorithm = DbSearcher.BTREE_ALGORITHM;
        //DbSearcher.BINARY_ALGORITHM //Binary
        //DbSearcher.MEMORY_ALGORITYM //Memory
        try {
            DbConfig config = new DbConfig();
            DbSearcher searcher = new DbSearcher(config, file.getPath());
            //define the method
            Method method = null;
            switch ( algorithm )
            {
                case DbSearcher.BTREE_ALGORITHM:
                    method = searcher.getClass().getMethod("btreeSearch", String.class);
                    break;
                case DbSearcher.BINARY_ALGORITHM:
                    method = searcher.getClass().getMethod("binarySearch", String.class);
                    break;
                case DbSearcher.MEMORY_ALGORITYM:
                    method = searcher.getClass().getMethod("memorySearch", String.class);
                    break;
            }
            DataBlock dataBlock = null;
            if ( Util.isIpAddress(ip) == false ) {
                log.error("Error: Invalid ip address");
            }
            dataBlock  = (DataBlock) method.invoke(searcher, ip);
            return dataBlock.getRegion();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
