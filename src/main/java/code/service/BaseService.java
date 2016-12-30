package code.service;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by Knight_JXNU on 2016/12/28.
 */
public class BaseService {
    @Value("#{mysettings.BT_URL}")
    protected String BT_URL;
    @Value("#{mysettings.THREAD_NUM}")
    protected int THREAD_NUM;
    @Value("#{mysettings.ADMIN_NAME}")
    protected String ADMIN_NAME;
    @Value("#{mysettings.ADMIN_PASS}")
    protected String ADMIN_PASS;
}
