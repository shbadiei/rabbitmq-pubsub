package com.codechallenge.rabbitpubsub.pub.config;

import com.codechallenge.rabbitpubsub.common.Branch;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.*;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Slf4j
@Profile("pub")
public class PubConfiguration implements BeanFactoryAware, InitializingBean {

    private final String exchangeBeanName = "sohaDirectExchange";
    private final String exchangeName = "soha-direct-exchange";

    private final String dlxExchangeBeanName = "sohaDirectDLX";
    private final String dlxExchangeName = "soha-direct-dlx";

    private ConfigurableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableBeanFactory) beanFactory;
    }

    @Override
    public void afterPropertiesSet() {
        makeBranchRoutingPath();
    }

    private void makeBranchRoutingPath() {
        //EXCHANGE
        beanFactory.registerSingleton(
                exchangeBeanName
                , new DirectExchange(exchangeName));

        beanFactory.registerSingleton(
                dlxExchangeBeanName
                , new DirectExchange(dlxExchangeName));

        for (Branch branch : Branch.values()) {
            log.info("### making {} routing path ... ###", branch.name());
            //QUEUEs
            String queueBeanName = StringUtils.uncapitalize(branch.name() + "Queue");
            beanFactory.registerSingleton(
                    queueBeanName,
                    QueueBuilder.durable(branch.getQueueName())
                            .deadLetterExchange(dlxExchangeName)
                            .deadLetterRoutingKey(branch.getDLQRoutingKey())
                            .build());

            String dlqQueueBeanName = StringUtils.uncapitalize(branch.name() + "DLQQueue");
            beanFactory.registerSingleton(
                    dlqQueueBeanName,
                    new Queue(branch.getDLQQueueName())
            );

            //BINDs
            String queueBindBeanName = StringUtils.uncapitalize(branch.name() + "Bind");
            beanFactory.registerSingleton(
                    queueBindBeanName,
                    BindingBuilder
                            .bind((Queue) beanFactory.getBean(queueBeanName))
                            .to((DirectExchange) beanFactory.getBean(exchangeBeanName))
                            .with(branch.getRoutingKey())
            );

            String dlqQueueBindBeanName = StringUtils.uncapitalize(branch.name() + "DLQBind");
            beanFactory.registerSingleton(
                    dlqQueueBindBeanName,
                    BindingBuilder
                            .bind((Queue) beanFactory.getBean(dlqQueueBeanName))
                            .to((DirectExchange) beanFactory.getBean(dlxExchangeBeanName))
                            .with(branch.getDLQRoutingKey())
            );

        }
    }

}