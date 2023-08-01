package com.sap.prd.jenkins.plugins.agent_maintenance;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import hudson.model.Node;
import hudson.slaves.RetentionStrategy;
import java.io.IOException;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;
import org.jvnet.hudson.test.recipes.LocalData;

/**
 * Tests that the old data format for maintenance windows is properly read.
 */
public class MigrationTest {

  @Rule
  public JenkinsRule rule = new JenkinsRule();

  @Test
  @LocalData
  public void readOldData() throws IOException {
    Node agent = rule.jenkins.getNode("agent");
    RetentionStrategy retentionStrategy = agent.toComputer().getRetentionStrategy();
    assertThat(retentionStrategy, instanceOf(AgentMaintenanceRetentionStrategy.class));
    assertThat(MaintenanceHelper.getInstance().hasMaintenanceWindows("agent"), is(true));
  }
}
