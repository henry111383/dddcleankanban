package ntut.csie.sslab.kanban.workflow.entity.event;

import ntut.csie.sslab.ddd.model.DomainEvent;
import ntut.csie.sslab.ddd.model.common.DateProvider;

public class Workflow1Created extends DomainEvent{

    private final String workflowId;
    private final String workflowName;

    public Workflow1Created(String id, String workflowName) {
        super(DateProvider.now());
        this.workflowId = id;
        this.workflowName = workflowName;

    }


    public String workflowId() {
        return workflowId;
    }

    public String workflowName() {
        return workflowName;
    }

}
