package ntut.csie.sslab.kanban.board.usecase;

import ntut.csie.sslab.ddd.adapter.gateway.GoogleEventBusAdapter;
import ntut.csie.sslab.ddd.usecase.DomainEventBus;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsOutput;
import ntut.csie.sslab.kanban.board.usecase.port.in.create.CreateBoardInput;
import ntut.csie.sslab.kanban.board.usecase.port.in.create.CreateBoardUseCase;
import ntut.csie.sslab.kanban.board.usecase.port.out.BoardRepository;
import ntut.csie.sslab.kanban.board.usecase.service.CreateBoardService;
import ntut.csie.sslab.kanban.workflow.adapter.out.repository.InMemoryWorkflow1Repository;
import ntut.csie.sslab.kanban.board.adapter.out.repository.InMemoryBoardRepository;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.create.CreateWorkflow1Input;
import ntut.csie.sslab.kanban.workflow.usecase.port.out.Workflow1Repository;
import ntut.csie.sslab.kanban.workflow.usecase.service.CreateWorkflow1Service;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class NotifyBoard1UseCaseTest {
    private BoardRepository boardRepository;
    private Workflow1Repository workflowRepository;
    private DomainEventBus domainEventBus;

    @Test
    public void create_a_workflow_should_commit_to_Board(){
        // 0. Create Notify
        boardRepository = new InMemoryBoardRepository();
        workflowRepository =new InMemoryWorkflow1Repository();
        domainEventBus = new GoogleEventBusAdapter();
        NotifyBoard1 notifyBoard1 = new NotifyBoard1(boardRepository, domainEventBus);
        domainEventBus.register(notifyBoard1);
        // 1. Create Board
        String boardId = create_board_use_case();

        // 2. Create Wolkflow
        String workflowId = create_workflow_use_case(boardId);

        assertEquals(1, boardRepository.findById(boardId).get().getCommittedWorkflows().size());
    }
    public String create_board_use_case(){
        CreateBoardUseCase useCase = new CreateBoardService(boardRepository,domainEventBus);
        CreateBoardInput input = new CreateBoardInput();
        input.setUserId("001");
        input.setName("Hello World");
        CqrsOutput cqrsOutput =  useCase.execute(input);
        return cqrsOutput.getId();
    }
    public String create_workflow_use_case(String BoardId){
        CreateWorkflow1Service useCase = new CreateWorkflow1Service(workflowRepository,domainEventBus);
        CreateWorkflow1Input input = new CreateWorkflow1Input();
        input.id = UUID.randomUUID().toString();
        input.Name = "AAAA";
        input.BoardId = BoardId;
        CqrsOutput cqrsOutput =  useCase.execute(input);
        return cqrsOutput.getId();
    }
}
