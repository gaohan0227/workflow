package org.bigant.fw.dingtalk;

import com.aliyun.dingtalkstorage_1_0.models.CommitFileResponseBody;
import com.aliyun.dingtalkworkflow_1_0.Client;
import com.aliyun.teaopenapi.models.Config;
import org.bigant.fw.dingtalk.instances.DingTalkInstancesService;
import org.bigant.fw.dingtalk.instances.form.convert.DingTalkAttachmentFDC;
import org.junit.Test;

/**
 * @author galen
 * @date 2024/3/2517:35
 */
public class DingTalkFileTest extends BaseTest {

    @Test
    public void putFile() throws Exception {

        Config config = new Config();
        config.protocol = "https";
        config.regionId = "central";

        DingTalkUser talkUser = new DingTalkUser(dingTalkConfig, cache);
        DingTalkAttachmentFDC dingTalkAttachmentFDC = new DingTalkAttachmentFDC(cache, dingTalkConfig, talkUser, new Client(config));
        String dingtalkUserId = userService.getOtherUserIdByUserId("1", "dingtalk");

        for (int i = 0; i < 10; i++) {
            CommitFileResponseBody commitFileResponseBody =
                    DingTalkFile.uploadFile(talkUser.getUnionId(dingtalkUserId),
                            dingTalkAttachmentFDC.getProcessInstancesSpaces(dingtalkUserId).toString(),
                            "测试文件.xlsx",
                            null,
                            "https://klwk-test.kuailework.com/upload/20240325/25aa342e9c00b5f209bfd514b2e74b22.xlsx",
                            dingTalkConfig);

            System.out.println(commitFileResponseBody);
        }


    }

    public DingTalkInstancesService getDingTalkInstancesService() {
        try {
            return new DingTalkInstancesService(dingTalkConfig, ccf, userService, new Client(getConfig()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}