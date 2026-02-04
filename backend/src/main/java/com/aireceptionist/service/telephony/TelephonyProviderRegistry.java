package com.aireceptionist.service.telephony;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class TelephonyProviderRegistry {
    private final ApplicationContext applicationContext;

    public TelephonyProviderRegistry(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public TelephonyProvider getProvider(String providerType) {
        return (TelephonyProvider) applicationContext.getBean(providerType);
    }
}
