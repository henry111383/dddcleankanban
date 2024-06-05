package ntut.csie.sslab.kanban.workflow.usecase;

import com.google.common.eventbus.Subscribe;
import ntut.csie.sslab.ddd.adapter.gateway.GoogleEventBusAdapter;
import ntut.csie.sslab.ddd.usecase.DomainEventBus;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsOutput;
import ntut.csie.sslab.kanban.workflow.adapter.out.repository.Workflow1RepositoryImpl;
import ntut.csie.sslab.kanban.workflow.entity.Workflow1;
import ntut.csie.sslab.kanban.workflow.entity.event.Workflow1Created;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.create.CreateWorkflow1Input;
import ntut.csie.sslab.kanban.workflow.usecase.port.out.Workflow1Repository;
import ntut.csie.sslab.kanban.workflow.usecase.service.CreateWorkflow1Service;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CreateWorkflow1UseCaseTest {
    @Test
    public void  create_workflow_use_case(){
        Workflow1Repository workflow1Repository = new Workflow1RepositoryImpl();
        DomainEventBus domainEventBus = new GoogleEventBusAdapter();
        NotifyWorkflow1GoogleEventBusAdapter notifyBoardGoogleEventBusAdapter = new NotifyWorkflow1GoogleEventBusAdapter();
        domainEventBus.register(notifyBoardGoogleEventBusAdapter);

        CreateWorkflow1Service useCase = new CreateWorkflow1Service(workflow1Repository,domainEventBus);
        CreateWorkflow1Input input = new CreateWorkflow1Input();



        input.id = "001";
        input.Name = "workflow test";

        CqrsOutput output = useCase.execute(input);

        assertNotNull(output.getId());
        assertEquals(workflow1Repository.findById("001").get().getClass(), Workflow1.class);
    }
    public class NotifyWorkflow1GoogleEventBusAdapter {
        @Subscribe
        public void whenWorkflow1Created(Workflow1Created event) {
            System.out.println("Hello World");
        }

    }

    /*
    public class NotifyWorkflow1GoogleEventBusAdapter {
        private final NotifyWorkflow1 notifyWorkflow;
        @Autowired
        public NotifyWorkflow1GoogleEventBusAdapter(NotifyWorkflow1 notifyWorkflow) {
            this.notifyWorkflow = notifyWorkflow;
        }

        @Subscribe
        public void whenCardCreated(CardCreated event) {
            notifyWorkflow.whenCardCreated(event);
        }

    }
    */


}
//class
