package ntut.csie.sslab.kanban.workflow.entity;

import ntut.csie.sslab.ddd.model.AggregateRoot;
import ntut.csie.sslab.kanban.workflow.entity.event.Workflow1Created;

public class Workflow1 extends AggregateRoot<String> {
    private final String id;
    private final String Name;
    private final String BoardId;

    public Workflow1(String id, String name,String BoardId) {
        super(id);
        this.id = id;
        Name = name;
        this.BoardId = BoardId;
        addDomainEvent(new Workflow1Created(id, name,BoardId));
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return Name;
    }
}
