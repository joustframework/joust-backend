package com.joust.backend.web.mvc.controller;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.joust.backend.data.spring.DataConfiguration;
import com.joust.backend.web.SecurityConfiguration;
import com.joust.backend.web.WebConfiguration;
import com.joust.backend.web.mvc.MvcConfiguration;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextHierarchy({
    @ContextConfiguration(classes = { WebConfiguration.class, SecurityConfiguration.class, DataConfiguration.class }),
    @ContextConfiguration(classes = MvcConfiguration.class) })
public abstract class AbstractWebIT {

  @Autowired
  private WebApplicationContext wac;

  protected MockMvc withoutSecurity() {
    return MockMvcBuilders.webAppContextSetup(this.wac).build();

  }

  protected MockMvc withSecurity() {
    return MockMvcBuilders.webAppContextSetup(this.wac).apply(springSecurity()).build();
  }

}