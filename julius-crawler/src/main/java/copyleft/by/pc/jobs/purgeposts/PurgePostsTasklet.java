package copyleft.by.pc.jobs.purgeposts;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import copyleft.by.pc.common.dao.GenericDao;

public class PurgePostsTasklet implements Tasklet {

	@Autowired
	private GenericDao dao;

	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1)
			throws Exception {

		dao.backupPostsBySources();
		
		return RepeatStatus.FINISHED;
	}
 

}
