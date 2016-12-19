package code.service;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by Knight_JXNU on 2016/11/25.
 */
public class BaseService {
    @Value("#{mysettings.BT_URL}")
    protected String BT_URL;
    @Value("#{mysettings.THREAD_NUM}")
    protected int THREAD_NUM;
}
