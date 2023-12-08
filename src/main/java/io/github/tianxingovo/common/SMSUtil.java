package io.github.tianxingovo.common;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.tea.TeaException;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import lombok.SneakyThrows;

import static com.aliyun.teautil.Common.assertAsString;

/**
 * 阿里云短信验证码工具类
 */
public class SMSUtil {

    /**
     * 发送短信验证码
     */
    public static void sendMessage(String signName, String templateCode, String phoneNumbers, int code) {
        // 从环境变量中读取ak和sk
        String accessKeyId = System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID");
        String accessKeySecret = System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET");
        Client client = createClient(accessKeyId, accessKeySecret);
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setSignName(signName)
                .setTemplateCode(templateCode)
                .setPhoneNumbers(phoneNumbers)
                .setTemplateParam("{\"code\":\"" + code + "\"}");
        RuntimeOptions runtimeOptions = new RuntimeOptions();
        try {
            client.sendSmsWithOptions(sendSmsRequest, runtimeOptions);
        } catch (TeaException teaException) {
            assertAsString(teaException.message);
        } catch (Exception exception) {
            TeaException teaException = new TeaException(exception.getMessage(), exception);
            assertAsString(teaException.message);
        }
    }

    @SneakyThrows
    public static Client createClient(String accessKeyId, String accessKeySecret) {
        Config config = new Config()
                .setAccessKeyId(accessKeyId)
                .setAccessKeySecret(accessKeySecret)
                .setEndpoint("dysmsapi.aliyuncs.com");
        return new Client(config);
    }
}
