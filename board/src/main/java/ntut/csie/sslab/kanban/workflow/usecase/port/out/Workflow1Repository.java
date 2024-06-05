package ntut.csie.sslab.kanban.workflow.usecase.port.out;

import ntut.csie.sslab.ddd.usecase.AbstractRepository;
import ntut.csie.sslab.kanban.workflow.entity.Workflow1;

import java.util.List;

public interface Workflow1Repository extends AbstractRepository<Workflow1,String> {
    List<Workflow1> getWorkflowsByBoardId(String boardId);
}