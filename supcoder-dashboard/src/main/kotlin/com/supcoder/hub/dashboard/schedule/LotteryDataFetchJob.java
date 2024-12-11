package com.supcoder.hub.dashboard.schedule;

import com.supcoder.hub.db.domain.LotteryType;
import com.supcoder.hub.db.model.LotteryVo;
import com.supcoder.hub.db.service.LotteryService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;

/**
 * LotteryDataFetchJob
 *
 * @author lee
 * @date 2024/12/11
 */
@Component
public class LotteryDataFetchJob {
    private final Log logger = LogFactory.getLog(LotteryDataFetchJob.class);

    @Autowired
    private LotteryService lotteryService;

    /**
     * 定时任务，每天凌晨4点获取彩票数据
     */
    @Scheduled(cron = "0 0 4 * * ?")
    public void fetchLotteryData() {
        logger.info("系统开启定时任务获取彩票数据");
        try {
            // 获取支持的彩票类型
            List<LotteryType> supportedLotteryTypes = lotteryService.getSupportedLotteryTypes();
            for (LotteryType lotteryType : supportedLotteryTypes) {
                // 调用Python脚本获取最新的彩票结果
                LotteryVo latestLotteryResult = fetchLotteryResultFromPython(lotteryType.getName());
                if (latestLotteryResult != null) {
                    logger.info("获取到最新的 " + lotteryType.getName() + " 彩票结果: " + latestLotteryResult);
                    // 这里可以添加保存或处理最新彩票结果的逻辑
                } else {
                    logger.warn("未获取到 " + lotteryType.getName() + " 彩票结果");
                }
            }
        } catch (Exception e) {
            logger.error("获取彩票数据时发生错误", e);
        }

        logger.info("系统结束定时任务获取彩票数据");
    }

    private LotteryVo fetchLotteryResultFromPython(String lotteryType) throws Exception {
        ProcessBuilder processBuilder = new ProcessBuilder("venv/bin/python", "scripts/fetch_lottery_data.py", lotteryType);
//        processBuilder.directory(new File("/path/to/your/project"));

        Process process = processBuilder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line);
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            logger.error("Python脚本执行失败，退出码: " + exitCode);
            return null;
        }

        String resultJson = output.toString().trim();
        if ("None".equals(resultJson)) {
            return null;
        }

        // 将JSON字符串转换为LotteryVo对象
        return convertJsonToLotteryVo(resultJson);
    }


    private LotteryVo convertJsonToLotteryVo(String json) {
        // 使用Jackson或其他JSON库将JSON字符串转换为LotteryVo对象
        // 这里假设你已经有一个方法来实现这一功能
        // 例如使用Jackson:
        // ObjectMapper objectMapper = new ObjectMapper();
        // return objectMapper.readValue(json, LotteryVo.class);
        return null; // 需要实现具体的转换逻辑
    }
}
