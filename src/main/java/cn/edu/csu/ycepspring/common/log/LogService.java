package cn.edu.csu.ycepspring.common.log;

import cn.edu.csu.ycepspring.common.utils.IpUtils;
import cn.edu.csu.ycepspring.common.utils.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LogService {
    public void saveLog(int account, String path) {
        String time = TimeUtils.getTimeNow();
        String ip = IpUtils.getIpAddr();
        String s = String.format("时间：%s，源IP：%s，用户Account：%d，请求地址：%s", time, ip, account, path);
        log.info(s);
    }
}
