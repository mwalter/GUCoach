/*
 * GUCoach - your personal coach for Goalunited (tm).
 * Licensed under General Public License v3 (GPLv3)
 * newInstance.org, 2012-2013
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.newinstance.gucoach.dataimport;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Tests the batch import job.
 *
 * @author mwalter
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/batch/batchContext-test.xml"})
public class PlayerDataImportTest {

    /** Input file for tests. */
    private static final String INPUT_FILE = "classpath:/gu_2011-11-20_team.csv";

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @Test
    public void importPlayerData() throws Exception {
        final JobExecution jobExecution = jobLauncher.run(job, new JobParametersBuilder().addString("inputFile", INPUT_FILE).addLong("timestamp", System.currentTimeMillis()).toJobParameters());
        Assert.assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
    }

}
