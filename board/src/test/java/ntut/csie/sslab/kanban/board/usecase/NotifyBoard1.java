package ntut.csie.sslab.kanban.board.usecase;

import com.google.common.eventbus.Subscribe;
import ntut.csie.sslab.ddd.usecase.DomainEventBus;
import ntut.csie.sslab.kanban.board.entity.Board;
import ntut.csie.sslab.kanban.board.usecase.port.out.BoardRepository;
import ntut.csie.sslab.kanban.workflow.entity.event.Workflow1Created;

import java.util.Optional;

public class NotifyBoard1 {
    private  BoardRepository boardRepository;
    private  DomainEventBus domainEventBus;
    public NotifyBoard1(BoardRepository boardRepository, DomainEventBus domainEventBus) {
        this.boardRepository = boardRepository;
        this.domainEventBus = domainEventBus;
    }
    @Subscribe
    public void whenWorkflowCreated(Workflow1Created workflow1Created){
        Optional<Board> board =  boardRepository.findById(workflow1Created.boardId());
        board.get().commitWorkflow(workflow1Created.workflowId());
        boardRepository.save(board.get());
        domainEventBus.postAll(board.get());
        System.out.println("Finally Done.");
    }
}
