package ntut.csie.sslab.kanban.workflow.usecase.service;

import ntut.csie.sslab.ddd.usecase.DomainEventBus;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import ntut.csie.sslab.kanban.workflow.entity.Workflow1;
import ntut.csie.sslab.kanban.workflow.entity.event.Workflow1Created;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.create.CreateWorkflow1Input;
import ntut.csie.sslab.kanban.workflow.usecase.port.out.Workflow1Repository;

public class CreateWorkflow1Service {
    private final Workflow1Repository workflow1Repository;
    private final DomainEventBus domainEventBus;
    public CreateWorkflow1Service(Workflow1Repository workflow1Repository, DomainEventBus domainEventBus) {
        this.workflow1Repository = workflow1Repository;
        this.domainEventBus = domainEventBus;
    }

    public CqrsOutput execute(CreateWorkflow1Input input){
        Workflow1 workflow = new Workflow1(input.id,input.Name);
        Workflow1Created domainEvent = new Workflow1Created(input.id,input.Name);

        workflow1Repository.save(workflow);
        domainEventBus.postAll(workflow);
        return  CqrsOutput.create().
                setId(workflow.getId()).
                setExitCode(ExitCode.SUCCESS);
    }

}
