package ntut.csie.sslab.kanban.workflow.entity.event;

import ntut.csie.sslab.ddd.model.DomainEvent;
import ntut.csie.sslab.ddd.model.common.DateProvider;
import ntut.csie.sslab.kanban.board.entity.Board;

public class Workflow1Created extends DomainEvent{

    private final String workflowId;
    private final String workflowName;
    private final String boardId;
    public Workflow1Created(String id, String workflowName,String boardId) {
        super(DateProvider.now());
        this.workflowId = id;
        this.workflowName = workflowName;
        this.boardId = boardId;
    }


    public String workflowId() {
        return workflowId;
    }

    public String workflowName() {
        return workflowName;
    }

    public String boardId(){ return boardId;}

}
