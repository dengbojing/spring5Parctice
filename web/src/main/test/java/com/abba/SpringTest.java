package com.abba;


import com.abba.config.HibernateConfig;
import com.abba.config.RootConfig;
import com.abba.config.ServletConfig;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = { HibernateConfig.class, RootConfig.class, ServletConfig.class },
        loader = AnnotationConfigContextLoader.class )
public class SpringTest {
}
